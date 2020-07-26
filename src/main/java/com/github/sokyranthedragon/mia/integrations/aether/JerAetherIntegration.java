package com.github.sokyranthedragon.mia.integrations.aether;

import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.integrations.jer.IJerIntegration;
import com.github.sokyranthedragon.mia.integrations.jer.JerLightHelper;
import com.legacy.aether.blocks.BlocksAether;
import com.legacy.aether.entities.bosses.EntityValkyrie;
import com.legacy.aether.entities.bosses.slider.EntitySlider;
import com.legacy.aether.entities.bosses.sun_spirit.EntitySunSpirit;
import com.legacy.aether.entities.bosses.valkyrie_queen.EntityValkyrieQueen;
import com.legacy.aether.entities.hostile.*;
import com.legacy.aether.entities.passive.EntityAerwhale;
import com.legacy.aether.entities.passive.EntityAetherAnimal;
import com.legacy.aether.entities.passive.EntitySheepuff;
import com.legacy.aether.entities.passive.mountable.EntityAerbunny;
import com.legacy.aether.entities.passive.mountable.EntityMoa;
import com.legacy.aether.entities.passive.mountable.EntityPhyg;
import com.legacy.aether.entities.passive.mountable.EntitySwet;
import com.legacy.aether.items.ItemsAether;
import com.legacy.aether.registry.AetherLootTables;
import com.legacy.aether.world.AetherWorld;
import jeresources.api.IDungeonRegistry;
import jeresources.api.IMobRegistry;
import jeresources.api.IPlantRegistry;
import jeresources.api.conditionals.LightLevel;
import jeresources.api.drop.PlantDrop;
import jeresources.entry.PlantEntry;
import jeresources.util.MobTableBuilder;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.entity.EntityFlying;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTableManager;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.github.sokyranthedragon.mia.integrations.jer.JerHelpers.addDungeonLootCategory;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
class JerAetherIntegration implements IJerIntegration
{
    @Override
    public Set<Class<? extends EntityLivingBase>> addMobs(MobTableBuilder builder, Set<Class<? extends EntityLivingBase>> ignoreMobOverrides)
    {
        // Passive
        builder.add(AetherLootTables.aerwhale, EntityAerwhale.class);
        builder.add(AetherLootTables.sheepuff, EntitySheepuff.class);
        // Mountable
        builder.add(AetherLootTables.aerbunny, EntityAerbunny.class);
        builder.add(AetherLootTables.moa, EntityMoa.class);
        builder.add(AetherLootTables.phyg, EntityPhyg.class);
        builder.add(AetherLootTables.swet, EntitySwet.class);
        
        // Hostile
        builder.add(AetherLootTables.aechor_plant, EntityAechorPlant.class);
        builder.add(AetherLootTables.cockatrice, EntityCockatrice.class);
        builder.add(AetherLootTables.chest_mimic, EntityMimic.class);
        builder.add(AetherLootTables.sentry, EntitySentry.class);
        builder.add(AetherLootTables.zephyr, EntityZephyr.class);
        
        // Bosses
        builder.add(AetherLootTables.slider, EntitySlider.class);
        builder.add(AetherLootTables.sun_spirit, EntitySunSpirit.class);
        builder.add(AetherLootTables.valkyrie, EntityValkyrie.class);
        builder.add(AetherLootTables.valkyrie_queen, EntityValkyrieQueen.class);
        
        return Stream.of(
            // Passive
            EntityAerwhale.class,
            EntitySheepuff.class,
            // Passive mountable
            EntityAerbunny.class,
            EntityMoa.class,
            EntityPhyg.class,
            EntitySwet.class,
            
            // Hostile
            EntityAechorPlant.class,
            EntityCockatrice.class,
            EntityMimic.class,
            EntitySentry.class,
            EntityZephyr.class,
            
            // Bosses
            EntitySlider.class,
            EntitySunSpirit.class,
            EntityValkyrie.class,
            EntityValkyrieQueen.class,
            
            // Special cases
            EntityWhirlwind.class
        ).collect(Collectors.toSet());
    }
    
    @Override
    public void configureMob(ResourceLocation resource, EntityLivingBase entity, @Nullable LootTableManager manager, IMobRegistry mobRegistry)
    {
        LightLevel lightLevel = LightLevel.any;
        int experienceMin = 1;
        int experienceMax = 3;
        
        if (entity instanceof EntitySentry)
        {
            experienceMin = 0;
            experienceMax = 0;
        }
        else if (entity instanceof EntityAetherAnimal)
            lightLevel = JerLightHelper.getLightLevelAbove(8);
        else if (entity instanceof EntityFlying)
        {
            lightLevel = JerLightHelper.getLightLevelAbove(8);
            experienceMin = 0;
            experienceMax = 0;
        }
        else if (entity instanceof EntityMob)
        {
            if (!(entity instanceof EntityMimic))
                lightLevel = LightLevel.hostile;
            
            experienceMin = 5;
            experienceMax = 5;
        }
        
        mobRegistry.register(entity, lightLevel, experienceMin, experienceMax, new String[]{ AetherWorld.aether_biome.getBiomeName() }, resource);
    }
    
    @Override
    public void addDungeonLoot(IDungeonRegistry dungeonRegistry)
    {
        addDungeonLootCategory(dungeonRegistry, "aether_bronze_dungeon_reward", AetherLootTables.bronze_dungeon_reward);
        addDungeonLootCategory(dungeonRegistry, "aether_bronze_dungeon",
            AetherLootTables.bronze_dungeon_chest,
            AetherLootTables.bronze_dungeon_chest_sub0,
            AetherLootTables.bronze_dungeon_chest_sub1,
            AetherLootTables.bronze_dungeon_chest_sub2,
            AetherLootTables.bronze_dungeon_chest_sub3);
        
        addDungeonLootCategory(dungeonRegistry, "aether_silver_dungeon_reward",
            AetherLootTables.silver_dungeon_reward,
            AetherLootTables.silver_dungeon_reward_sub0);
        addDungeonLootCategory(dungeonRegistry, "aether_silver_dungeon",
            AetherLootTables.silver_dungeon_chest,
            AetherLootTables.silver_dungeon_chest_sub0,
            AetherLootTables.silver_dungeon_chest_sub1,
            AetherLootTables.silver_dungeon_chest_sub2,
            AetherLootTables.silver_dungeon_chest_sub3,
            AetherLootTables.silver_dungeon_chest_sub4,
            AetherLootTables.silver_dungeon_chest_sub5);
        
        addDungeonLootCategory(dungeonRegistry, "aether_gold_dungeon_reward",
            AetherLootTables.silver_dungeon_chest,
            AetherLootTables.silver_dungeon_chest_sub0,
            AetherLootTables.silver_dungeon_chest_sub1,
            AetherLootTables.silver_dungeon_chest_sub2,
            AetherLootTables.silver_dungeon_chest_sub3,
            AetherLootTables.silver_dungeon_chest_sub4,
            AetherLootTables.silver_dungeon_chest_sub5);
    }
    
    @Override
    public void addPlantDrops(IPlantRegistry plantRegistry, @Nullable Collection<PlantEntry> registers)
    {
        plantRegistry.registerWithSoil(
            new ItemStack(BlocksAether.berry_bush_stem),
            BlocksAether.aether_grass.getDefaultState(),
            new PlantDrop(new ItemStack(ItemsAether.blue_berry), 1, 3));
    }
    
    @Override
    public ModIds getModId()
    {
        return ModIds.AETHER;
    }
}
