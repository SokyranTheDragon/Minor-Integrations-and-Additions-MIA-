package com.github.sokyranthedragon.mia.integrations.futuremc;

import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.integrations.jer.IJerIntegration;
import com.github.sokyranthedragon.mia.integrations.jer.custom.CustomPlantEntry;
import com.github.sokyranthedragon.mia.utilities.LootTableUtils;
import jeresources.api.IMobRegistry;
import jeresources.api.IPlantRegistry;
import jeresources.api.conditionals.LightLevel;
import jeresources.api.drop.PlantDrop;
import jeresources.entry.PlantEntry;
import jeresources.util.MobTableBuilder;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.storage.loot.LootTableManager;
import thedarkcolour.futuremc.config.FConfig;
import thedarkcolour.futuremc.entity.bee.EntityBee;
import thedarkcolour.futuremc.entity.fish.cod.EntityCod;
import thedarkcolour.futuremc.entity.fish.pufferfish.EntityPufferfish;
import thedarkcolour.futuremc.entity.fish.salmon.EntitySalmon;
import thedarkcolour.futuremc.entity.fish.tropical.EntityTropicalFish;
import thedarkcolour.futuremc.entity.panda.EntityPanda;
import thedarkcolour.futuremc.registry.FBlocks;
import thedarkcolour.futuremc.registry.FItems;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
class JerFutureMcIntegration implements IJerIntegration
{
    @Override
    public void addPlantDrops(IPlantRegistry plantRegistry, @Nullable Collection<PlantEntry> registers)
    {
        if (registers != null)
        {
            CustomPlantEntry sweetBerry = new CustomPlantEntry(
                new ItemStack(FItems.INSTANCE.getSWEET_BERRIES()),
                FBlocks.INSTANCE.getSWEET_BERRY_BUSH().getDefaultState(),
                new PlantDrop(new ItemStack(FItems.INSTANCE.getSWEET_BERRIES()), 1, 3));
            sweetBerry.setSoil(Blocks.GRASS.getDefaultState());
            registers.add(sweetBerry);
        }
        else
        {
            plantRegistry.registerWithSoil(
                new ItemStack(FItems.INSTANCE.getSWEET_BERRIES()),
                Blocks.GRASS.getDefaultState(),
                new PlantDrop(new ItemStack(FItems.INSTANCE.getSWEET_BERRIES()), 3, 3));
        }
    }
    
    @Override
    public Set<Class<? extends EntityLivingBase>> addMobs(MobTableBuilder builder, Set<Class<? extends EntityLivingBase>> ignoreMobOverrides)
    {
        Set<Class<? extends EntityLivingBase>> enabledEntities = new HashSet<>();
        
        if (FConfig.INSTANCE.getVillageAndPillage().panda && FConfig.INSTANCE.getVillageAndPillage().bamboo.enabled)
        {
            enabledEntities.add(EntityPanda.class);
            builder.add(LootTableUtils.loadUniqueEmptyLootTable(), EntityPanda.class);
        }
        if (FConfig.INSTANCE.getBuzzyBees().bee.enabled)
        {
            enabledEntities.add(EntityBee.class);
            builder.add(LootTableUtils.loadUniqueEmptyLootTable(), EntityBee.class);
        }
        if (FConfig.INSTANCE.getUpdateAquatic().fish.cod.enabled)
        {
            enabledEntities.add(EntityCod.class);
            builder.add(EntityCod.Companion.getLOOT_TABLE(), EntityCod.class);
        }
        if (FConfig.INSTANCE.getUpdateAquatic().fish.pufferfish.enabled)
        {
            enabledEntities.add(EntityPufferfish.class);
            builder.add(EntityPufferfish.Companion.getLOOT_TABLE(), EntityPufferfish.class);
        }
        if (FConfig.INSTANCE.getUpdateAquatic().fish.salmon.enabled)
        {
            enabledEntities.add(EntitySalmon.class);
            builder.add(EntitySalmon.Companion.getLOOT_TABLE(), EntitySalmon.class);
        }
        if (FConfig.INSTANCE.getUpdateAquatic().fish.tropicalFish.enabled)
        {
            enabledEntities.add(EntityTropicalFish.class);
            builder.add(EntityTropicalFish.Companion.getLOOT_TABLE(), EntityTropicalFish.class);
        }
        
        return enabledEntities;
    }
    
    @Override
    public void configureMob(ResourceLocation resource, EntityLivingBase entity, @Nullable LootTableManager manager, IMobRegistry mobRegistry)
    {
        LightLevel lightLevel = LightLevel.any;
        String[] validBiomes = null;
        int minExp = 1;
        int maxExp = 3;
        
        if (entity instanceof EntityPanda)
            validBiomes = Stream.of(Biomes.JUNGLE, Biomes.JUNGLE_HILLS, Biomes.MUTATED_JUNGLE).map(Biome::getBiomeName).toArray(String[]::new);
        else
        {
            // All config files with format `namespace:name:chances:other:etc`
            // We take only the namespace:name part only
            if (entity instanceof EntityBee)
                validBiomes = FConfig.INSTANCE.getBuzzyBees().validBiomesForBeeNest;
            else if (entity instanceof EntityCod)
                validBiomes = FConfig.INSTANCE.getUpdateAquatic().fish.cod.validBiomes;
            else if (entity instanceof EntityPufferfish)
                validBiomes = FConfig.INSTANCE.getUpdateAquatic().fish.pufferfish.validBiomes;
            else if (entity instanceof EntitySalmon)
                validBiomes = FConfig.INSTANCE.getUpdateAquatic().fish.salmon.validBiomes;
            else if (entity instanceof EntityTropicalFish)
                validBiomes = FConfig.INSTANCE.getUpdateAquatic().fish.tropicalFish.validBiomes;
            
            if (validBiomes != null)
                validBiomes = Arrays.stream(validBiomes)
                                    .map(s ->
                                    {
                                        String[] split = s.split(":", 3);
                                        return split[0] + ":" + split[1];
                                    })
                                    .toArray(String[]::new);
        }
        
        if (validBiomes == null)
            mobRegistry.register(entity, lightLevel, minExp, maxExp, resource);
        else
            mobRegistry.register(entity, lightLevel, minExp, maxExp, validBiomes, resource);
    }
    
    @Nonnull
    @Override
    public ModIds getModId()
    {
        return ModIds.FUTURE_MC;
    }
}
