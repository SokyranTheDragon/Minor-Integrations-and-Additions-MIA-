package com.github.sokyranthedragon.mia.integrations.thaumcraft;

import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.integrations.jer.ExtraConditional;
import com.github.sokyranthedragon.mia.integrations.jer.IJerIntegration;
import jeresources.api.IMobRegistry;
import jeresources.api.conditionals.Conditional;
import jeresources.api.conditionals.LightLevel;
import jeresources.api.drop.LootDrop;
import jeresources.entry.MobEntry;
import jeresources.util.LootTableHelper;
import jeresources.util.MobTableBuilder;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraft.world.storage.loot.LootTableManager;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.items.ItemsTC;
import thaumcraft.common.entities.monster.*;
import thaumcraft.common.entities.monster.boss.*;
import thaumcraft.common.entities.monster.cult.EntityCultist;
import thaumcraft.common.entities.monster.cult.EntityCultistCleric;
import thaumcraft.common.entities.monster.cult.EntityCultistKnight;
import thaumcraft.common.entities.monster.tainted.*;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@ParametersAreNonnullByDefault
class JerThaumcraftIntegration implements IJerIntegration
{
    @Nonnull
    @Override
    public Set<Class<? extends EntityLivingBase>> addMobs(MobTableBuilder builder, Set<Class<? extends EntityLivingBase>> ignoreMobOverrides)
    {
        builder.add(LootTableList.ENTITIES_ZOMBIE, EntityBrainyZombie.class);
        builder.add(EntityPech.LOOT, EntityPech.class);
        builder.add(ModIds.MIA.loadSimple("thaumcraft/firebat"), EntityFireBat.class);
        builder.add(ModIds.MIA.loadSimple("thaumcraft/mind_spider"), EntityMindSpider.class);
        builder.add(ModIds.MIA.loadSimple("thaumcraft/wisp"), EntityWisp.class);
        // Eldritch bosses
        builder.add(ModIds.MIA.loadSimple("thaumcraft/eldritch_golem"), EntityEldritchGolem.class);
        builder.add(ModIds.MIA.loadSimple("thaumcraft/eldritch_warden"), EntityEldritchWarden.class);
        // Taint
        builder.add(ModIds.MIA.loadSimple("thaumcraft/taintancle"), EntityTaintacle.class);
        builder.add(ModIds.MIA.loadSimple("thaumcraft/taintacle_giant"), EntityTaintacleGiant.class);
        builder.add(ModIds.MIA.loadSimple("thaumcraft/taint_crawler"), EntityTaintCrawler.class);
        builder.add(ModIds.MIA.loadSimple("thaumcraft/taint_swarm"), EntityTaintSwarm.class);
        builder.add(ModIds.MIA.loadSimple("thaumcraft/taint_seed"), EntityTaintSeed.class);
        builder.add(ModIds.MIA.loadSimple("thaumcraft/taint_prime"), EntityTaintSeedPrime.class);
        // Cultists
        builder.add(ModIds.MIA.loadSimple("thaumcraft/cultist_cleric"), EntityCultistCleric.class);
        builder.add(ModIds.MIA.loadSimple("thaumcraft/cultist_knight"), EntityCultistKnight.class);
        builder.add(ModIds.MIA.loadSimple("thaumcraft/cultist_leader"), EntityCultistLeader.class);
        
        return Stream.of(
                EntityBrainyZombie.class,
                EntityPech.class,
                EntityFireBat.class,
                EntityMindSpider.class,
                EntityWisp.class,
                EntityThaumicSlime.class,
                
                EntityEldritchGolem.class,
                EntityEldritchWarden.class,
                
                EntityTaintacle.class,
                EntityTaintacleGiant.class,
                EntityTaintCrawler.class,
                EntityTaintSwarm.class,
                EntityTaintSeed.class,
                EntityTaintSeedPrime.class,
                
                EntityCultistLeader.class,
                EntityCultistCleric.class,
                EntityCultistKnight.class
        ).collect(Collectors.toSet());
    }
    
    @Override
    public void configureMob(ResourceLocation resource, EntityLivingBase entity, @Nullable LootTableManager manager, IMobRegistry mobRegistry)
    {
        LightLevel lightLevel = LightLevel.hostile;
        Set<Biome> validBiomes = new HashSet<>();
        List<LootDrop> loot = null;
        if (manager != null)
            loot = LootTableHelper.toDrops(manager.getLootTableFromLocation(resource));
        int minExp = 0;
        int maxExp = 0;
        
        if (entity instanceof EntityBrainyZombie)
        {
            if (loot != null)
                loot.add(new LootDrop(new ItemStack(ItemsTC.brain), 0, 1, 0.5f, Conditional.affectedByLooting));
            
            //noinspection ConstantConditions
            validBiomes = Stream.of(
                    Objects.requireNonNull(BiomeManager.getBiomes(BiomeManager.BiomeType.WARM)).stream(),
                    Objects.requireNonNull(BiomeManager.getBiomes(BiomeManager.BiomeType.COOL)).stream(),
                    Objects.requireNonNull(BiomeManager.getBiomes(BiomeManager.BiomeType.ICY)).stream(),
                    Objects.requireNonNull(BiomeManager.getBiomes(BiomeManager.BiomeType.DESERT)).stream()
            )
                                .reduce(Stream::concat)
                                .orElseGet(Stream::empty)
                                .map(biomeEntry -> biomeEntry.biome)
                                .distinct()
                                .filter(biome -> biome.getSpawnableList(EnumCreatureType.MONSTER) != null && biome.getSpawnableList(EnumCreatureType.MONSTER).size() > 0)
                                .collect(Collectors.toSet());
            
            minExp = 5;
        }
        else if (entity instanceof EntityCultist)
        {
            if (loot != null)
            {
                loot.add(new LootDrop(new ItemStack(ItemsTC.curio, 1, 6), 0, 1, 0.25f, Conditional.playerKill, Conditional.affectedByLooting, ExtraConditional.affectedByResearch, ExtraConditional.lessChanceIfHeld));
                
                if (entity instanceof EntityCultistCleric)
                {
                    loot.add(new LootDrop(new ItemStack(ItemsTC.crimsonRobeHelm), 0, 1, 0.05f));
                    loot.add(new LootDrop(new ItemStack(ItemsTC.crimsonRobeChest), 0, 1, 0.05f));
                    loot.add(new LootDrop(new ItemStack(ItemsTC.crimsonRobeLegs), 0, 1, 0.05f));
                    loot.add(new LootDrop(new ItemStack(ItemsTC.crimsonBoots), 0, 1, 0.05f));
                }
                // Kind of pointless check, but better safe than sorry
                else if (entity instanceof EntityCultistKnight)
                {
                    loot.add(new LootDrop(new ItemStack(ItemsTC.crimsonPlateHelm), 0, 1, 0.05f));
                    loot.add(new LootDrop(new ItemStack(ItemsTC.crimsonRobeHelm), 0, 1, 0.05f));
                    loot.add(new LootDrop(new ItemStack(ItemsTC.crimsonPlateChest), 0, 1, 0.05f));
                    loot.add(new LootDrop(new ItemStack(ItemsTC.crimsonPlateLegs), 0, 1, 0.05f));
                    loot.add(new LootDrop(new ItemStack(ItemsTC.crimsonBoots), 0, 1, 0.05f));
                }
            }
            
            minExp = 10;
        }
        else if (entity instanceof EntityPech)
            minExp = 8;
        else if (entity instanceof EntityMindSpider)
            minExp = 1;
        else if (entity instanceof EntityWisp)
        {
//            List<ItemStack> aspects = Aspect.getPrimalAspects().stream().map(ThaumcraftApiHelper::makeCrystal).collect(Collectors.toList());
//            loot.add(new LootDropRandom(aspects, 1, 1, 0.9f));
//
//            aspects = Aspect.getCompoundAspects().stream().map(ThaumcraftApiHelper::makeCrystal).collect(Collectors.toList());
//            loot.add(new LootDropRandom(aspects, 1, 1, 0.1f));
            
            minExp = 5;
        }
        else if (entity instanceof EntityFireBat)
            validBiomes.addAll(BiomeDictionary.getBiomes(BiomeDictionary.Type.NETHER));
        else if (entity instanceof EntityTaintacleGiant)
        {
            if (loot != null)
                loot.add(new LootDrop(new ItemStack(ItemsTC.primordialPearl), 0, 1, ExtraConditional.ifLastOneKilled));
            minExp = 20;
        }
        else if (entity instanceof EntityTaintacle)
        {
            if (loot != null)
                loot.add(new LootDrop(ThaumcraftApiHelper.makeCrystal(Aspect.FLUX)));
            minExp = 8;
        }
        else if (entity instanceof EntityCultistLeader)
            minExp = 40;
        else if (entity instanceof EntityThaumcraftBoss)
            minExp = 50;
        else if (entity instanceof EntityTaintCrawler)
        {
            if (loot != null)
                loot.add(new LootDrop(ThaumcraftApiHelper.makeCrystal(Aspect.FLUX), 0, 1, 0.125f));
            
            minExp = 3;
        }
        else if (entity instanceof EntityTaintSwarm)
        {
            if (loot != null)
                loot.add(new LootDrop(ThaumcraftApiHelper.makeCrystal(Aspect.FLUX), 0, 1, 0.5f));
            
            minExp = 5;
        }
        else if (entity instanceof EntityTaintSeedPrime)
        {
            if (loot != null)
                loot.add(new LootDrop(ThaumcraftApiHelper.makeCrystal(Aspect.FLUX), 1, 3, new Conditional[0]));
            minExp = 12;
        }
        else if (entity instanceof EntityTaintSeed)
        {
            if (loot != null)
                loot.add(new LootDrop(ThaumcraftApiHelper.makeCrystal(Aspect.FLUX)));
            minExp = 8;
        }
        else if (entity instanceof EntityThaumicSlime)
        {
            minExp = 3;
            maxExp = 10;
        }
        
        if (minExp > maxExp)
            maxExp = minExp;
        
        if (loot == null)
        {
            if (validBiomes.isEmpty())
                mobRegistry.register(entity, lightLevel, minExp, maxExp, resource);
            else
                mobRegistry.register(entity, lightLevel, minExp, maxExp, validBiomes.stream().map(Biome::getBiomeName).toArray(String[]::new), resource);
        }
        else
        {
            LootDrop[] drops = loot.toArray(new LootDrop[0]);
            
            if (validBiomes.isEmpty())
                mobRegistry.register(entity, lightLevel, minExp, maxExp, drops);
            else
                mobRegistry.register(entity, lightLevel, minExp, maxExp, validBiomes.stream().map(Biome::getBiomeName).toArray(String[]::new), drops);
        }
    }
    
    @Override
    public void overrideExistingMobDrops(MobEntry mobEntry)
    {
        if (mobEntry.getEntity() instanceof EntityZombie && !(mobEntry.getEntity() instanceof EntityBrainyZombie))
            mobEntry.addDrop(new LootDrop(new ItemStack(ItemsTC.brain), 0, 1, 0.1f, Conditional.affectedByLooting));
    }
    
    @Nonnull
    @Override
    public ModIds getModId()
    {
        return ModIds.THAUMCRAFT;
    }
}
