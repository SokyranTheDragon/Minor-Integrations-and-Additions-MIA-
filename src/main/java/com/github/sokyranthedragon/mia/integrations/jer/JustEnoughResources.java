package com.github.sokyranthedragon.mia.integrations.jer;

import com.github.sokyranthedragon.mia.Mia;
import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.integrations.base.IBaseMod;
import com.github.sokyranthedragon.mia.integrations.base.IModIntegration;
import jeresources.api.IDungeonRegistry;
import jeresources.api.IMobRegistry;
import jeresources.api.IPlantRegistry;
import jeresources.api.conditionals.LightLevel;
import jeresources.api.drop.PlantDrop;
import jeresources.compatibility.JERAPI;
import jeresources.entry.MobEntry;
import jeresources.registry.MobRegistry;
import jeresources.util.FakeClientWorld;
import jeresources.util.LootTableHelper;
import jeresources.util.MobTableBuilder;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTableManager;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.fml.common.ProgressManager;
import net.minecraftforge.fml.common.event.FMLLoadCompleteEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;

import java.lang.reflect.Field;
import java.util.*;
import java.util.function.BiConsumer;

import static com.github.sokyranthedragon.mia.config.JerConfiguration.externalIntegrationsEnabled;

public class JustEnoughResources implements IBaseMod
{
    private final Map<ModIds, IJerIntegration> modIntegrations = new HashMap<>();
    private final Set<Class<? extends EntityLivingBase>> ignoreMobOverrides = new HashSet<>();
    private CustomLinkedHashSet<MobEntry> set;
    
    public JustEnoughResources()
    {
        try
        {
            Field field = MobRegistry.getInstance().getClass().getDeclaredField("registry");
            field.setAccessible(true);
            
            set = new CustomLinkedHashSet<>();
            set.jer = this;
            
            field.set(MobRegistry.getInstance(), set);
        } catch (NoSuchFieldException | IllegalAccessException e)
        {
            Mia.LOGGER.error("Could not access MobRegistry.registry, mob loot overrides won't work.");
        }
    }
    
    public static ResourceLocation loadResource(String path)
    {
        return new ResourceLocation("mia", path);
    }
    
    @Override
    public void register(BiConsumer<ModIds, IModIntegration> modIntegration)
    {
        if (ModIds.JEI.isLoaded)
            modIntegration.accept(ModIds.JEI, new JeiJerIntegration());
    }
    
    @Override
    public void addIntegration(IModIntegration integration)
    {
        if (!externalIntegrationsEnabled)
            return;
        
        if (integration instanceof IJerIntegration)
        {
            modIntegrations.put(integration.getModId(), (IJerIntegration) integration);
            return;
        }
        
        Mia.LOGGER.warn("Incorrect JER integration with id of " + integration.getModId() + ": " + integration.toString());
    }
    
    @Override
    public void postInit(FMLPostInitializationEvent event)
    {
        ProgressManager.ProgressBar progressBar = ProgressManager.push("JustEnoughResources entry registration", modIntegrations.size() + 1);
        progressBar.step("setting up");
        
        World world = Minecraft.getMinecraft().world;
        
        if (world == null)
            world = new FakeClientWorld();
        
        IPlantRegistry plantRegistry = JERAPI.getInstance().getPlantRegistry();
        MobTableBuilder mobTableBuilder = new MobTableBuilder(world);
        IMobRegistry mobRegistry = JERAPI.getInstance().getMobRegistry();
        
        mobTableBuilder.add(loadResource("minecraft/wither"), EntityWither.class);
        Optional<Map.Entry<ResourceLocation, EntityLivingBase>> wither = mobTableBuilder.getMobTables().entrySet().stream().findAny();
        wither.ifPresent(entry -> mobRegistry.register(entry.getValue(), LightLevel.any, 50, entry.getKey()));
        
        // It's a little redundant, but might as well be 100% sure
        if (Items.BEETROOT_SEEDS instanceof IPlantable)
        {
            plantRegistry.register(
                    (Item & IPlantable) Items.BEETROOT_SEEDS,
                    new PlantDrop(new ItemStack(Items.BEETROOT), 1, 1),
                    new PlantDrop(new ItemStack(Items.BEETROOT_SEEDS), 0, 3));
        }
        if (Items.NETHER_WART instanceof IPlantable)
        {
            plantRegistry.registerWithSoil(
                    (Item & IPlantable) Items.NETHER_WART,
                    Blocks.SOUL_SAND.getDefaultState(),
                    new PlantDrop(new ItemStack(Items.NETHER_WART), 2, 4));
        }
        
        LootTableManager manager = LootTableHelper.getManager(world);
        IDungeonRegistry dungeonRegistry = JERAPI.getInstance().getDungeonRegistry();
        mobTableBuilder = new MobTableBuilder(world);
        
        Map<Object, ModIds> allMobs = new HashMap<>();
        for (IJerIntegration mod : modIntegrations.values())
        {
            progressBar.step(mod.getModId().modId);
            
            Set<Class<? extends EntityLivingBase>> modMobs = mod.addMobs(mobTableBuilder, ignoreMobOverrides);
            for (Object modMob : modMobs)
                allMobs.put(modMob, mod.getModId());
            mod.addMobRenderHooks(mobRegistry);
            
            mod.addDungeonLoot(dungeonRegistry);
            mod.addPlantDrops(plantRegistry);
        }
        
        ProgressManager.pop(progressBar);
        
        Set<Map.Entry<ResourceLocation, EntityLivingBase>> entries = mobTableBuilder.getMobTables().entrySet();
        
        if (!entries.isEmpty())
        {
            progressBar = ProgressManager.push("JustEnoughResources mob configuration", entries.size());
            for (Map.Entry<ResourceLocation, EntityLivingBase> entry : entries)
            {
                ResourceLocation resource = entry.getKey();
                progressBar.step(resource.toString());
                EntityLivingBase entity = entry.getValue();
                
                modIntegrations.get(allMobs.get(entity.getClass())).configureMob(resource, entity, manager, mobRegistry);
            }
            ProgressManager.pop(progressBar);
        }
    }
    
    @Override
    public void loadCompleted(FMLLoadCompleteEvent event)
    {
        set.jer = null;
    }

//    @Override
//    public void loadCompleted(FMLLoadCompleteEvent event)
//    {
//        if (!replacedMobDrops && modIntegrations.size() > 0)
//        {
//            List<MobEntry> mobEntries = MobRegistry.getInstance().getMobs();
//
//            for (MobEntry mobEntry : mobEntries)
//            {
//                if (ignoreMobOverrides.contains(mobEntry.getEntity().getClass()))
//                    continue;
//
//                for (IJerIntegration mod : modIntegrations.values())
//                    mod.overrideExistingMobDrops(mobEntry);
//            }
//        }
//    }
    
    void overrideMobDrop(MobEntry entry)
    {
        if (ignoreMobOverrides.contains(entry.getEntity().getClass()))
            return;
        
        for (IJerIntegration mod : modIntegrations.values())
            mod.overrideExistingMobDrops(entry);
    }
}
