package com.github.sokyranthedragon.mia.integrations.jer;

import com.github.sokyranthedragon.mia.integrations.base.IModIntegration;
import jeresources.api.IDungeonRegistry;
import jeresources.api.IMobRegistry;
import jeresources.api.IPlantRegistry;
import jeresources.api.conditionals.LightLevel;
import jeresources.entry.MobEntry;
import jeresources.entry.PlantEntry;
import jeresources.entry.VillagerEntry;
import jeresources.registry.VillagerRegistry;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTableManager;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Collection;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public interface IJerIntegration extends IModIntegration
{
    default void addMobs(JustEnoughResources.CustomMobTableBuilder builder)
    {
    }
    
    default void configureMob(ResourceLocation resource, EntityLivingBase entity, @Nullable LootTableManager manager, IMobRegistry mobRegistry)
    {
        int min = 1;
        int max = 3;
        
        if (entity instanceof EntityMob)
            min = max = 5;
        else if (!(entity instanceof EntityAnimal))
            min = max = 0;
        
        mobRegistry.register(entity, LightLevel.any, min, max, resource);
    }
    
    default void overrideExistingMobDrops(MobEntry mobEntry)
    {
    }
    
    default void addMobRenderHooks(IMobRegistry mobRegistry)
    {
    }
    
    default void addDungeonLoot(IDungeonRegistry dungeonRegistry, World world)
    {
    }
    
    default void addPlantDrops(IPlantRegistry plantRegistry, @Nullable Collection<PlantEntry> registers)
    {
    }
    
    default void addVillagerTrades(VillagerRegistry villagerRegistry, boolean acceptsCustomEntries)
    {
    }
    
    default void overrideExistingVillagerTrades(VillagerEntry villagerEntry)
    {
    }
}
