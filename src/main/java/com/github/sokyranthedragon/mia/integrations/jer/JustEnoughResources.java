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
import jeresources.entry.PlantEntry;
import jeresources.entry.VillagerEntry;
import jeresources.registry.MobRegistry;
import jeresources.registry.VillagerRegistry;
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
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLLoadCompleteEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;

import javax.annotation.Nullable;
import java.lang.reflect.Field;
import java.util.*;
import java.util.function.BiConsumer;

import static com.github.sokyranthedragon.mia.config.JerConfiguration.externalIntegrationsEnabled;

public class JustEnoughResources implements IBaseMod
{
    private final Map<ModIds, IJerIntegration> modIntegrations = new HashMap<>();
    private final Set<Class<? extends EntityLivingBase>> ignoreMobOverrides = new HashSet<>();
    private CustomLinkedHashSet<MobEntry> mobOverrideSet;
    private CustomLinkedList<MobEntry> villagerOverrideList;
    private JeiJerIntegration jeiIntegration;
    private boolean insertedEarly;
    
    public JustEnoughResources()
    {
        try
        {
            Field field = MobRegistry.getInstance().getClass().getDeclaredField("registry");
            field.setAccessible(true);
            
            mobOverrideSet = new CustomLinkedHashSet<>();
            mobOverrideSet.jer = this;
            
            field.set(MobRegistry.getInstance(), mobOverrideSet);
        } catch (NoSuchFieldException | IllegalAccessException e)
        {
            Mia.LOGGER.error("Could not access MobRegistry.registry, mob loot overrides won't work.");
        }
        
        try
        {
            Field field = VillagerRegistry.getInstance().getClass().getDeclaredField("villagers");
            field.setAccessible(true);
    
            villagerOverrideList = new CustomLinkedList<>();
            villagerOverrideList.jer = this;
    
            field.set(VillagerRegistry.getInstance(), villagerOverrideList);
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
        {
            jeiIntegration = new JeiJerIntegration();
            modIntegration.accept(ModIds.JEI, jeiIntegration);
        }
    }
    
    @Override
    public void addIntegration(IModIntegration integration)
    {
        if (!externalIntegrationsEnabled)
            return;
        
        if (integration instanceof IJerIntegration)
            modIntegrations.put(integration.getModId(), (IJerIntegration) integration);
        else
            Mia.LOGGER.warn("Incorrect JER integration with id of " + integration.getModId() + ": " + integration.toString());
    }
    
    @Override
    public void init(FMLInitializationEvent event)
    {
        insertedEarly = jeiIntegration.initializePlugins(this);
    }
    
    @Override
    public void postInit(FMLPostInitializationEvent event)
    {
        if (!insertedEarly)
        {
            try
            {
                initJerIntegration();
            } catch (Exception e)
            {
                Mia.LOGGER.error("Encountered an issue registering JER entries! (Post-init registration)");
                Mia.LOGGER.error(e);
            }
        }
    }
    
    void initJerIntegration()
    {
        ProgressManager.ProgressBar progressBar = ProgressManager.push("JustEnoughResources entry registration", modIntegrations.size() + 1);
        progressBar.step("setting up");
        
        World world = Minecraft.getMinecraft().world;
        
        if (world == null)
            world = new FakeClientWorld();
        MobTableBuilder vanillaMobTableBuilder = new MobTableBuilder(world);
        IMobRegistry mobRegistry = JERAPI.getInstance().getMobRegistry();
        
        IPlantRegistry plantRegistry = JERAPI.getInstance().getPlantRegistry();
        Collection<PlantEntry> registers = null;
        
        if (jeiIntegration.registeredPlants)
        {
            try
            {
                Field registersField = plantRegistry.getClass().getDeclaredField("registers");
                registersField.setAccessible(true);
                //noinspection unchecked
                registers = (Collection<PlantEntry>) registersField.get(plantRegistry);
            } catch (IllegalAccessException | NoSuchFieldException e)
            {
                Mia.LOGGER.error("Could not access IPlantRegistry.registers, some plants might not have growing display in JER.");
            }
        }
        
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
        
        vanillaMobTableBuilder.add(loadResource("minecraft/wither"), EntityWither.class);
        Optional<Map.Entry<ResourceLocation, EntityLivingBase>> wither = vanillaMobTableBuilder.getMobTables().entrySet().stream().findAny();
        wither.ifPresent(entry -> mobRegistry.register(entry.getValue(), LightLevel.any, 50, entry.getKey()));
        
        LootTableManager manager = null;
        try
        {
            // It caused a bunch of issues in the past.
            manager = LootTableHelper.getManager(world);
        } catch (Exception e)
        {
            Mia.LOGGER.error("Encountered an issue registering JER loot table helper! A lot of mob drops might be broken!");
            e.printStackTrace();
        }
        IDungeonRegistry dungeonRegistry = JERAPI.getInstance().getDungeonRegistry();
    
        Map<Object, ModIds> allMobs = new HashMap<>();
        CustomMobTableBuilder mobTableBuilder = new CustomMobTableBuilder(world, allMobs, ignoreMobOverrides);
        
        for (IJerIntegration mod : modIntegrations.values())
        {
            progressBar.step(mod.getModId().modId);
            
            mobTableBuilder.currentId = mod.getModId();
            mod.addMobs(mobTableBuilder);
            mod.addMobRenderHooks(mobRegistry);
            
            mod.addPlantDrops(plantRegistry, registers);
            mod.addDungeonLoot(dungeonRegistry, world);
            mod.addVillagerTrades(VillagerRegistry.getInstance(), jeiIntegration.registeredVillagers);
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
        mobOverrideSet.jer = null;
        villagerOverrideList.jer = null;
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
    
    void overrideVillagerTrades(VillagerEntry entry)
    {
        for (IJerIntegration mod : modIntegrations.values())
            mod.overrideExistingVillagerTrades(entry);
    }
    
    public static class CustomMobTableBuilder extends MobTableBuilder
    {
        ModIds currentId = null;
        private Map<Object, ModIds> allMobs;
        private Set<Class<? extends EntityLivingBase>> ignoreMobOverrides;
        
        public CustomMobTableBuilder(World world, Map<Object, ModIds> allMobs, Set<Class<? extends EntityLivingBase>> ignoreMobOverrides)
        {
            super(world);
            this.allMobs = allMobs;
            this.ignoreMobOverrides = ignoreMobOverrides;
        }
    
        @Override
        public <T extends EntityLivingBase> void add(ResourceLocation resourceLocation, Class<T> entityClass)
        {
            assert currentId != null;
            allMobs.put(entityClass, currentId);
            super.add(resourceLocation, entityClass);
        }
    
        @Override
        public <T extends EntityLivingBase> void add(ResourceLocation resourceLocation, Class<T> entityClass, @Nullable EntityPropertySetter<T> entityPropertySetter)
        {
            assert currentId != null;
            allMobs.put(entityClass, currentId);
            super.add(resourceLocation, entityClass, entityPropertySetter);
        }
    
        public <T extends EntityLivingBase> void addWithIgnore(ResourceLocation resourceLocation, Class<T> entityClass)
        {
            ignoreMobOverrides.add(entityClass);
            add(resourceLocation, entityClass);
        }
    
        public <T extends EntityLivingBase> void addWithIgnore(ResourceLocation resourceLocation, Class<T> entityClass, @Nullable EntityPropertySetter<T> entityPropertySetter)
        {
            ignoreMobOverrides.add(entityClass);
            add(resourceLocation, entityClass, entityPropertySetter);
        }
    
        public <T extends EntityLivingBase> void addNoConfigure(ResourceLocation resourceLocation, Class<T> entityClass)
        {
            super.add(resourceLocation, entityClass);
        }
    
        public <T extends EntityLivingBase> void addNoConfigure(ResourceLocation resourceLocation, Class<T> entityClass, @Nullable EntityPropertySetter<T> entityPropertySetter)
        {
            super.add(resourceLocation, entityClass, entityPropertySetter);
        }
    }
}
