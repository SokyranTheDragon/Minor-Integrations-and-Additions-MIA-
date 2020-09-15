package com.github.sokyranthedragon.mia.integrations.aether;

import com.gildedgames.the_aether.api.player.util.IAetherBoss;
import com.gildedgames.the_aether.blocks.BlocksAether;
import com.gildedgames.the_aether.entities.bosses.EntityValkyrie;
import com.gildedgames.the_aether.entities.bosses.slider.EntitySlider;
import com.gildedgames.the_aether.entities.bosses.sun_spirit.EntitySunSpirit;
import com.gildedgames.the_aether.entities.bosses.valkyrie_queen.EntityValkyrieQueen;
import com.gildedgames.the_aether.entities.hostile.*;
import com.gildedgames.the_aether.entities.passive.EntityAerwhale;
import com.gildedgames.the_aether.entities.passive.EntityAetherAnimal;
import com.gildedgames.the_aether.entities.passive.EntitySheepuff;
import com.gildedgames.the_aether.entities.passive.mountable.EntityAerbunny;
import com.gildedgames.the_aether.entities.passive.mountable.EntityMoa;
import com.gildedgames.the_aether.entities.passive.mountable.EntityPhyg;
import com.gildedgames.the_aether.entities.passive.mountable.EntitySwet;
import com.gildedgames.the_aether.items.ItemsAether;
import com.gildedgames.the_aether.registry.AetherLootTables;
import com.gildedgames.the_aether.world.AetherWorld;
import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.integrations.jer.ExtraConditional;
import com.github.sokyranthedragon.mia.integrations.jer.IJerIntegration;
import com.github.sokyranthedragon.mia.integrations.jer.JerLightHelper;
import com.github.sokyranthedragon.mia.integrations.jer.JustEnoughResources;
import com.github.sokyranthedragon.mia.utilities.LootTableUtils;
import jeresources.api.IDungeonRegistry;
import jeresources.api.IMobRegistry;
import jeresources.api.IPlantRegistry;
import jeresources.api.conditionals.LightLevel;
import jeresources.api.drop.LootDrop;
import jeresources.api.drop.PlantDrop;
import jeresources.entry.PlantEntry;
import jeresources.util.LootTableHelper;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.EntityFlying;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTableManager;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.github.sokyranthedragon.mia.integrations.jer.JerHelpers.addDungeonLootCategory;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
class JerAetherIntegration implements IJerIntegration
{
    @Override
    public void addMobs(JustEnoughResources.CustomMobTableBuilder builder)
    {
        // Passive
        builder.add(AetherLootTables.aerwhale, EntityAerwhale.class);
        builder.add(AetherLootTables.sheepuff, EntitySheepuff.class);
        // Mountable
        builder.add(AetherLootTables.aerbunny, EntityAerbunny.class);
        builder.add(AetherLootTables.moa, EntityMoa.class);
        builder.add(AetherLootTables.phyg, EntityPhyg.class);
        builder.add(LootTableUtils.loadUniqueEmptyLootTable(), EntitySwet.class);
        
        // Hostile
        builder.add(AetherLootTables.aechor_plant, EntityAechorPlant.class);
        builder.add(AetherLootTables.cockatrice, EntityCockatrice.class);
        builder.add(AetherLootTables.chest_mimic, EntityMimic.class);
        builder.add(LootTableUtils.loadUniqueEmptyLootTable(), EntitySentry.class);
        builder.add(AetherLootTables.zephyr, EntityZephyr.class);
        
        // Bosses
        builder.add(AetherLootTables.slider, EntitySlider.class);
        builder.add(AetherLootTables.sun_spirit, EntitySunSpirit.class);
        builder.add(AetherLootTables.valkyrie, EntityValkyrie.class);
        builder.add(AetherLootTables.valkyrie_queen, EntityValkyrieQueen.class);
    }
    
    @Override
    public void configureMob(ResourceLocation resource, EntityLivingBase entity, @Nullable LootTableManager manager, IMobRegistry mobRegistry)
    {
        LightLevel lightLevel = LightLevel.any;
        int experienceMin = 1;
        int experienceMax = 3;
        List<LootDrop> loot = null;
        
        if (entity instanceof IAetherBoss)
        {
            if (manager != null && entity instanceof EntitySunSpirit)
            {
                loot = LootTableHelper.toDrops(manager.getLootTableFromLocation(resource));
                loot.add(new LootDrop(new ItemStack(ItemsAether.dungeon_key, 1, 2)));
                loot.add(new LootDrop(new ItemStack(BlocksAether.sun_altar)));
            }
            
            experienceMin = 0;
            experienceMax = 0;
        }
        if (entity instanceof EntitySentry)
        {
            loot = new ArrayList<>();
            // Technically, I believe this doesn't work
//            Block block = BlocksAether.dungeon_block.getDefaultState().withProperty(BlockDungeonBase.dungeon_stone, EnumStoneType.Sentry).getBlock();
//            loot.add(new LootDrop(new ItemStack(block), 0.2f));
            loot.add(new LootDrop(new ItemStack(BlocksAether.dungeon_block), 0.8f));
            
            experienceMin = 0;
            experienceMax = 0;
        }
        else if (entity instanceof EntitySwet)
        {
            loot = new ArrayList<>();
            loot.add(new LootDrop(ItemsAether.swetty_ball, 1));
            loot.add(new LootDrop(new ItemStack(BlocksAether.aercloud, 1, 1), 1, 1, 1f, ExtraConditional.dependsOnVariant));
            loot.add(new LootDrop(new ItemStack(Blocks.GLOWSTONE), 1, 1, 1f, ExtraConditional.dependsOnVariant));
            
            lightLevel = JerLightHelper.getLightLevelAbove(8);
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
        
        if (loot == null)
            mobRegistry.register(entity, lightLevel, experienceMin, experienceMax, new String[]{ AetherWorld.aether_biome.getBiomeName() }, resource);
        else
            mobRegistry.register(entity, lightLevel, experienceMin, experienceMax, new String[]{ AetherWorld.aether_biome.getBiomeName() }, loot.toArray(new LootDrop[0]));
    }
    
    @Override
    public void addDungeonLoot(IDungeonRegistry dungeonRegistry, World world)
    {
        addDungeonLootCategory(world, dungeonRegistry, "aether_bronze_dungeon_reward", AetherLootTables.bronze_dungeon_reward);
        addDungeonLootCategory(world, dungeonRegistry, "aether_bronze_dungeon",
            AetherLootTables.bronze_dungeon_chest,
            AetherLootTables.bronze_dungeon_chest_sub0,
            AetherLootTables.bronze_dungeon_chest_sub1,
            AetherLootTables.bronze_dungeon_chest_sub2,
            AetherLootTables.bronze_dungeon_chest_sub3);
        
        addDungeonLootCategory(world, dungeonRegistry, "aether_silver_dungeon_reward",
            AetherLootTables.silver_dungeon_reward,
            AetherLootTables.silver_dungeon_reward_sub0);
        addDungeonLootCategory(world, dungeonRegistry, "aether_silver_dungeon",
            AetherLootTables.silver_dungeon_chest,
            AetherLootTables.silver_dungeon_chest_sub0,
            AetherLootTables.silver_dungeon_chest_sub1,
            AetherLootTables.silver_dungeon_chest_sub2,
            AetherLootTables.silver_dungeon_chest_sub3,
            AetherLootTables.silver_dungeon_chest_sub4,
            AetherLootTables.silver_dungeon_chest_sub5);
        
        addDungeonLootCategory(world, dungeonRegistry, "aether_gold_dungeon_reward",
            AetherLootTables.gold_dungeon_reward,
            AetherLootTables.gold_dungeon_reward_sub0,
            AetherLootTables.gold_dungeon_reward_sub1,
            AetherLootTables.gold_dungeon_reward_sub2,
            AetherLootTables.gold_dungeon_reward_sub3,
            AetherLootTables.gold_dungeon_reward_sub4);
    }
    
    @Override
    public void addMobRenderHooks(IMobRegistry mobRegistry)
    {
        mobRegistry.registerRenderHook(EntitySunSpirit.class, (renderInfo, entityLivingBase) ->
        {
            GlStateManager.scale(0.75f, 0.75f, 0.75f);
            return renderInfo;
        });
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
