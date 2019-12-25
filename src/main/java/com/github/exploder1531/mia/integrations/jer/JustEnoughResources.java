package com.github.exploder1531.mia.integrations.jer;

import com.github.exploder1531.mia.Mia;
import com.github.exploder1531.mia.integrations.base.IBaseMod;
import com.github.exploder1531.mia.integrations.base.IModIntegration;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import jeresources.api.IDungeonRegistry;
import jeresources.api.IMobRegistry;
import jeresources.api.IPlantRegistry;
import jeresources.compatibility.JERAPI;
import jeresources.entry.MobEntry;
import jeresources.registry.MobRegistry;
import jeresources.util.FakeClientWorld;
import jeresources.util.LootTableHelper;
import jeresources.util.MobTableBuilder;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTableManager;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Set;

import static com.github.exploder1531.mia.config.JerConfiguration.externalIntegrationsEnabled;

public class JustEnoughResources implements IBaseMod
{
    private final Map<String, IJerIntegration> modIntegrations = Maps.newHashMap();
    private final Set<Class<? extends EntityLivingBase>> ignoreMobOverrides = Sets.newHashSet();
    
    public JustEnoughResources()
    {
        try
        {
            Field field = MobRegistry.getInstance().getClass().getDeclaredField("registry");
            field.setAccessible(true);
            
            CustomLinkedHashSet<MobEntry> set = new CustomLinkedHashSet<>();
            set.jer = this;
            
            field.set(MobRegistry.getInstance(), set);
        } catch (NoSuchFieldException | IllegalAccessException e)
        {
            e.printStackTrace();
        }
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
    public void init(FMLInitializationEvent event)
    {
        if (modIntegrations.size() > 0)
        {
            World world = Minecraft.getMinecraft().world;
            
            if (world == null)
            {
                world = new FakeClientWorld();
            }
            
            LootTableManager manager = LootTableHelper.getManager(world);
            MobTableBuilder mobTableBuilder = new MobTableBuilder(world);
            IMobRegistry mobRegistry = JERAPI.getInstance().getMobRegistry();
            IDungeonRegistry dungeonRegistry = JERAPI.getInstance().getDungeonRegistry();
            IPlantRegistry plantRegistry = JERAPI.getInstance().getPlantRegistry();
            
            Map<Object, String> allMobs = Maps.newHashMap();
            for (IJerIntegration mod : modIntegrations.values())
            {
                Set<Class<? extends EntityLivingBase>> modMobs = mod.addMobs(mobTableBuilder, ignoreMobOverrides);
                for (Object modMob : modMobs)
                    allMobs.put(modMob, mod.getModId());
                mod.addMobRenderHooks(mobRegistry);
                
                mod.addDungeonLoot(dungeonRegistry);
                mod.addPlantDrops(plantRegistry);
            }
            
            for (Map.Entry<ResourceLocation, EntityLivingBase> entry : mobTableBuilder.getMobTables().entrySet())
            {
                ResourceLocation resource = entry.getKey();
                EntityLivingBase entity = entry.getValue();
                
                modIntegrations.get(allMobs.get(entity.getClass())).configureMob(resource, entity, manager, mobRegistry);
            }
        }
    }
    
    void overrideMobDrop(MobEntry entry)
    {
        if (ignoreMobOverrides.contains(entry.getEntity().getClass()))
            return;
        
        for (IJerIntegration mod : modIntegrations.values())
            mod.overrideExistingMobDrops(entry);
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
    
    public static ResourceLocation loadResource(String path)
    {
        return new ResourceLocation("mia", path);
    }
}
