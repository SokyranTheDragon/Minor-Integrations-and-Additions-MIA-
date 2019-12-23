package com.github.exploder1531.mia.integrations.iceandfire;

import com.github.alexthe666.iceandfire.IceAndFire;
import com.github.alexthe666.iceandfire.core.ModItems;
import com.github.alexthe666.iceandfire.entity.*;
import com.github.alexthe666.iceandfire.enums.EnumTroll;
import com.github.alexthe666.iceandfire.structures.WorldGenCyclopsCave;
import com.github.alexthe666.iceandfire.structures.WorldGenFireDragonCave;
import com.github.alexthe666.iceandfire.structures.WorldGenIceDragonCave;
import com.github.alexthe666.iceandfire.structures.WorldGenMyrmexDecoration;
import com.github.exploder1531.mia.integrations.ModIds;
import com.github.exploder1531.mia.integrations.jer.IJerIntegration;
import com.google.common.collect.Sets;
import jeresources.api.IDungeonRegistry;
import jeresources.api.IMobRegistry;
import jeresources.api.conditionals.LightLevel;
import jeresources.api.drop.LootDrop;
import jeresources.api.render.IMobRenderHook;
import jeresources.entry.MobEntry;
import jeresources.util.LootTableHelper;
import jeresources.util.MobTableBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityWitherSkeleton;
import net.minecraft.init.Biomes;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.storage.loot.LootTableManager;
import net.minecraftforge.common.BiomeDictionary;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.github.exploder1531.mia.integrations.jer.JustEnoughResources.loadResource;

@ParametersAreNonnullByDefault
class JerIceAndFireIntegration implements IJerIntegration
{
    // We're not checking the setters for Myrmex and Dragons, as we're using the same class for more than a single mob type.
    // Using their base class did not work as it wasn't considered the exact class that was required, but it accepted no class.
    @SuppressWarnings("unchecked")
    @Nonnull
    @Override
    public Set<Class<? extends EntityLivingBase>> addMobs(MobTableBuilder builder, Set<Class<? extends EntityLivingBase>> ignoreMobOverrides)
    {
        builder.add(EntityAmphithere.LOOT, EntityAmphithere.class);
        builder.add(EntityCockatrice.LOOT, EntityCockatrice.class);
        builder.add(EntityCyclops.LOOT, EntityCyclops.class);
        // Death worms
        // The giant variation does have too similar loot to normal
        builder.add(EntityDeathWorm.TAN_LOOT, EntityDeathWorm.class, new DeathWormSetter(0));
//        builder.add(EntityDeathWorm.TAN_GIANT_LOOT, EntityDeathWorm.class, new DeathWormSetter(0));
        builder.add(EntityDeathWorm.WHITE_LOOT, EntityDeathWorm.class, new DeathWormSetter(1));
//        builder.add(EntityDeathWorm.WHITE_GIANT_LOOT, EntityDeathWorm.class, new DeathWormSetter(1));
        builder.add(EntityDeathWorm.RED_LOOT, EntityDeathWorm.class, new DeathWormSetter(2));
//        builder.add(EntityDeathWorm.RED_GIANT_LOOT, EntityDeathWorm.class, new DeathWormSetter(2));
        builder.add(EntityGorgon.LOOT, EntityGorgon.class);
        // Dragons
        builder.add(EntityFireDragon.SKELETON_LOOT, EntityFireDragon.class, new DragonSetter(-1));
        builder.add(EntityIceDragon.SKELETON_LOOT, EntityIceDragon.class, new DragonSetter(-1));
        for (int i = 0; i <= 3; i++)
        {
            // ResourceLocation is used as a key in HashMap, so we need to create our own to prevent replacing entries,
            // leaving us with drops for only a single dragon of each type.
            builder.add(loadResource("iceandfire/dragon/fire_dragon_" + i), EntityFireDragon.class, new DragonSetter(i));
            builder.add(loadResource("iceandfire/dragon/ice_dragon_" + i), EntityIceDragon.class, new DragonSetter(i));
        }
        builder.add(EntityHippocampus.LOOT, EntityHippocampus.class);
        builder.add(EntityHippogryph.LOOT, EntityHippogryph.class);
        // Myrmex
        builder.add(EntityMyrmexQueen.DESERT_LOOT, EntityMyrmexQueen.class, new MyrmexSetter(false));
        builder.add(EntityMyrmexQueen.JUNGLE_LOOT, EntityMyrmexQueen.class, new MyrmexSetter(true));
        builder.add(EntityMyrmexRoyal.DESERT_LOOT, EntityMyrmexRoyal.class, new MyrmexSetter(false));
        builder.add(EntityMyrmexRoyal.JUNGLE_LOOT, EntityMyrmexRoyal.class, new MyrmexSetter(true));
        builder.add(EntityMyrmexSentinel.DESERT_LOOT, EntityMyrmexSentinel.class, new MyrmexSetter(false));
        builder.add(EntityMyrmexSentinel.JUNGLE_LOOT, EntityMyrmexSentinel.class, new MyrmexSetter(true));
        builder.add(EntityMyrmexSoldier.DESERT_LOOT, EntityMyrmexSoldier.class, new MyrmexSetter(false));
        builder.add(EntityMyrmexSoldier.JUNGLE_LOOT, EntityMyrmexSoldier.class, new MyrmexSetter(true));
        builder.add(EntityMyrmexWorker.DESERT_LOOT, EntityMyrmexWorker.class, new MyrmexSetter(false));
        builder.add(EntityMyrmexWorker.JUNGLE_LOOT, EntityMyrmexWorker.class, new MyrmexSetter(true));
        builder.add(EntityPixie.LOOT, EntityPixie.class);
        for (int i = 0; i <= 6; i++)
            builder.add(loadResource("iceandfire/seaserpent/sea_serpent_" + i), EntitySeaSerpent.class, new SeaSerpentSetter(i));
        builder.add(EntitySiren.LOOT, EntitySiren.class);
        builder.add(EntityStymphalianBird.LOOT, EntityStymphalianBird.class);
        builder.add(EntityTroll.FOREST_LOOT, EntityTroll.class, new TrollSetter(EnumTroll.FOREST));
        builder.add(EntityTroll.FROST_LOOT, EntityTroll.class, new TrollSetter(EnumTroll.FROST));
        builder.add(EntityTroll.MOUNTAIN_LOOT, EntityTroll.class, new TrollSetter(EnumTroll.MOUNTAIN));
        
        ignoreMobOverrides.add(EntityFireDragon.class);
        ignoreMobOverrides.add(EntityIceDragon.class);
        
        return Stream.of(
                EntityAmphithere.class,
                EntityCockatrice.class,
                EntityCyclops.class,
                EntityDeathWorm.class,
                EntityGorgon.class,
                EntityFireDragon.class,
                EntityHippocampus.class,
                EntityHippogryph.class,
                EntityIceDragon.class,
                EntityMyrmexQueen.class,
                EntityMyrmexRoyal.class,
                EntityMyrmexSentinel.class,
                EntityMyrmexSoldier.class,
                EntityMyrmexWorker.class,
                EntityPixie.class,
                EntitySeaSerpent.class,
                EntitySiren.class,
                EntityStymphalianBird.class,
                EntityTroll.class
        ).collect(Collectors.toSet());
    }
    
    @Override
    public void configureMob(ResourceLocation resource, EntityLivingBase entity, LootTableManager manager, IMobRegistry mobRegistry)
    {
        LightLevel lightLevel = LightLevel.any;
        Set<Biome> validBiomes = Sets.newHashSet();
        List<LootDrop> loot = LootTableHelper.toDrops(manager.getLootTableFromLocation(resource));
        int experienceMin = 0;
        int experienceMax = 0;
        
        if (entity instanceof EntityAmphithere)
        {
            validBiomes.addAll(BiomeDictionary.getBiomes(BiomeDictionary.Type.JUNGLE));
            experienceMin = 15;
            experienceMax = 24;
        }
        else if (entity instanceof EntityCockatrice)
        {
            validBiomes.addAll(BiomeDictionary.getBiomes(BiomeDictionary.Type.SAVANNA));
            validBiomes.addAll(BiomeDictionary.getBiomes(BiomeDictionary.Type.SPARSE));
            experienceMin = 10;
            experienceMax = 14;
        }
        else if (entity instanceof EntityDeathWorm)
        {
            for (Biome biome : Biome.REGISTRY)
            {
                if (biome != null && BiomeDictionary.hasType(biome, BiomeDictionary.Type.SANDY) && BiomeDictionary.hasType(biome, BiomeDictionary.Type.DRY) && !BiomeDictionary.hasType(biome, BiomeDictionary.Type.BEACH) && !BiomeDictionary.hasType(biome, BiomeDictionary.Type.MESA))
                    validBiomes.add(biome);
            }
            experienceMin = 2;
            experienceMax = 44;
        }
        else if (entity instanceof EntityHippogryph)
        {
            validBiomes.addAll(BiomeDictionary.getBiomes(BiomeDictionary.Type.HILLS));
            experienceMin = 7;
            experienceMax = 16;
        }
        else if (entity instanceof EntityHippocampus || entity instanceof EntitySeaSerpent)
        {
            validBiomes.addAll(BiomeDictionary.getBiomes(BiomeDictionary.Type.OCEAN));
            experienceMin = 1;
            experienceMax = 3;
        }
        else if (entity instanceof EntityMyrmexBase)
        {
            EntityMyrmexBase myrmex = (EntityMyrmexBase) entity;
            
            if (myrmex.isJungle())
                validBiomes.addAll(BiomeDictionary.getBiomes(BiomeDictionary.Type.JUNGLE));
            else
            {
                for (Biome biome : Biome.REGISTRY)
                {
                    if (BiomeDictionary.hasType(biome, BiomeDictionary.Type.HOT) && BiomeDictionary.hasType(biome, BiomeDictionary.Type.DRY) && BiomeDictionary.hasType(biome, BiomeDictionary.Type.SANDY))
                        validBiomes.add(biome);
                }
            }
            
            experienceMin = myrmex.getCasteImportance() * 7;
            experienceMax = experienceMin + 2;
        }
        else if (entity instanceof EntityPixie)
        {
            for (Biome biome : Biome.REGISTRY)
            {
                if (BiomeDictionary.hasType(biome, BiomeDictionary.Type.FOREST) && BiomeDictionary.hasType(biome, BiomeDictionary.Type.SPOOKY) || BiomeDictionary.hasType(biome, BiomeDictionary.Type.MAGICAL))
                    validBiomes.add(biome);
            }
            experienceMin = experienceMax = 3;
        }
        else if (entity instanceof EntitySiren)
        {
            for (Biome biome : Biome.REGISTRY)
            {
                if (BiomeDictionary.hasType(biome, BiomeDictionary.Type.OCEAN) && !BiomeDictionary.hasType(biome, BiomeDictionary.Type.COLD))
                    validBiomes.add(biome);
            }
            experienceMin = 10;
            experienceMax = 19;
        }
        else if (entity instanceof EntityStymphalianBird)
        {
            validBiomes.addAll(BiomeDictionary.getBiomes(BiomeDictionary.Type.SWAMP));
            experienceMin = 10;
            experienceMax = 14;
        }
        // Gorgon and Cyclops share XP and spawn biome
        else if (entity instanceof EntityGorgon || entity instanceof EntityCyclops)
        {
            validBiomes.addAll(BiomeDictionary.getBiomes(BiomeDictionary.Type.BEACH));
            experienceMin = 20;
            experienceMax = 34;
        }
        else if (entity instanceof EntityDragonBase)
        {
            EntityDragonBase dragon = (EntityDragonBase) entity;
            
            for (Biome biome : Biome.REGISTRY)
            {
                if (dragon.isModelDead())
                {
                    if (dragon.isFire)
                    {
                        if (BiomeDictionary.hasType(biome, BiomeDictionary.Type.DRY) && BiomeDictionary.hasType(biome, BiomeDictionary.Type.SANDY))
                            validBiomes.add(biome);
                    }
                    else
                    {
                        if (BiomeDictionary.hasType(biome, BiomeDictionary.Type.COLD) && BiomeDictionary.hasType(biome, BiomeDictionary.Type.SNOWY))
                            validBiomes.add(biome);
                    }
                }
                else
                {
                    if (dragon.isFire)
                    {
                        if (!biome.getEnableSnow() && (double) biome.getDefaultTemperature() > -0.5D &&
                                biome != Biomes.ICE_PLAINS && !BiomeDictionary.hasType(biome, BiomeDictionary.Type.COLD) &&
                                !BiomeDictionary.hasType(biome, BiomeDictionary.Type.SNOWY) && !BiomeDictionary.hasType(biome, BiomeDictionary.Type.WET) &&
                                !BiomeDictionary.hasType(biome, BiomeDictionary.Type.OCEAN) && !BiomeDictionary.hasType(biome, BiomeDictionary.Type.RIVER))
                            validBiomes.add(biome);
                    }
                    else
                    {
                        if (BiomeDictionary.hasType(biome, BiomeDictionary.Type.COLD) && BiomeDictionary.hasType(biome, BiomeDictionary.Type.SNOWY))
                            validBiomes.add(biome);
                    }
                }
            }
            
            if (dragon.isModelDead())
            {
                if (IceAndFire.CONFIG.dragonDropSkull)
                    loot.add(new LootDrop(new ItemStack(ModItems.dragon_skull, 1, dragon.isFire ? 0 : 1)));
            }
            else
            {
                experienceMin = 15;
                experienceMax = 190;
                
                if (IceAndFire.CONFIG.dragonDropHeart)
                    loot.add(new LootDrop(new ItemStack(dragon.isFire ? ModItems.fire_dragon_heart : ModItems.ice_dragon_heart)));
                if (IceAndFire.CONFIG.dragonDropBlood)
                    loot.add(new LootDrop(new ItemStack(dragon.isFire ? ModItems.fire_dragon_blood : ModItems.ice_dragon_blood)));
            }
        }
        else if (entity instanceof EntityTroll)
        {
            EnumTroll troll = ((EntityTroll) entity).getType();
            
            validBiomes.addAll(BiomeDictionary.getBiomes(troll.spawnBiome));
            experienceMin = 15;
            experienceMax = 24;
            
            if (troll == EnumTroll.FOREST)
            {
                loot.add(new LootDrop(EnumTroll.Weapon.COLUMN_FOREST.item, 0, 1, 0.25f));
                loot.add(new LootDrop(EnumTroll.Weapon.TRUNK.item, 0, 1, 0.25f));
                loot.add(new LootDrop(EnumTroll.Weapon.AXE.item, 0, 1, 0.25f));
                loot.add(new LootDrop(EnumTroll.Weapon.HAMMER.item, 0, 1, 0.25f));
            }
            else if (troll == EnumTroll.FROST)
            {
                loot.add(new LootDrop(EnumTroll.Weapon.COLUMN_FROST.item, 0, 1, 0.25f));
                loot.add(new LootDrop(EnumTroll.Weapon.TRUNK_FROST.item, 0, 1, 0.25f));
                loot.add(new LootDrop(EnumTroll.Weapon.AXE.item, 0, 1, 0.25f));
                loot.add(new LootDrop(EnumTroll.Weapon.HAMMER.item, 0, 1, 0.25f));
            }
            else
            {
                loot.add(new LootDrop(EnumTroll.Weapon.TRUNK.item, 0, 1, 0.33f));
                loot.add(new LootDrop(EnumTroll.Weapon.AXE.item, 0, 1, 0.33f));
                loot.add(new LootDrop(EnumTroll.Weapon.HAMMER.item, 0, 1, 0.33f));
            }
        }
        
        LootDrop[] drops = loot.toArray(new LootDrop[0]);
        
        if (validBiomes.isEmpty())
            mobRegistry.register(entity, lightLevel, experienceMin, experienceMax, drops);
        else
            mobRegistry.register(entity, lightLevel, experienceMin, experienceMax, validBiomes.stream().map(Biome::getBiomeName).toArray(String[]::new), drops);
    }
    
    @Override
    public void overrideExistingMobDrops(MobEntry mobEntry)
    {
        if (mobEntry.getEntity() instanceof EntityWitherSkeleton)
            mobEntry.addDrop(new LootDrop(ModItems.witherbone, 0, 2));
    }
    
    @Override
    public void addMobRenderHooks(IMobRegistry mobRegistry)
    {
        mobRegistry.registerRenderHook(EntityFireDragon.class, RENDER_HOOK_DRAGON);
        mobRegistry.registerRenderHook(EntityIceDragon.class, RENDER_HOOK_DRAGON);
        mobRegistry.registerRenderHook(EntityAmphithere.class, RENDER_HOOK_DRAGON);
        mobRegistry.registerRenderHook(EntitySeaSerpent.class, RENDER_HOOK_SEA_SERPENT);
        mobRegistry.registerRenderHook(EntityTroll.class, RENDER_HOOK_TROLL);
        mobRegistry.registerRenderHook(EntityCyclops.class, RENDER_HOOK_CYCLOPS);
    }
    
    @Override
    public void addDungeonLoot(IDungeonRegistry dungeonRegistry)
    {
        // Dragons
        final String iceDragon = "chests/ice_dragon";
        final String fireDragon = "chests/fire_dragon";
        // Myrmex
        final String myrmexLoot = "chest/myrmex_loot";
        final String myrmexDesertFood = "chest/myrmex_desert_food";
        final String myrmexJungleFood = "chest/myrmex_jungle_food";
        final String myrmexTrash = "chest/myrmex_trash";
        // Cyclops
        final String cyclopsCave = "chests/cyclops_cave";
        
        // Dragons
        dungeonRegistry.registerCategory(fireDragon, "mia.jer.dungeon.fire_dragon");
        dungeonRegistry.registerCategory(iceDragon, "mia.jer.dungeon.ice_dragon");
        // Myrmex
        dungeonRegistry.registerCategory(myrmexLoot, "mia.jer.dungeon.myrmex_loot");
        dungeonRegistry.registerCategory(myrmexDesertFood, "mia.jer.dungeon.myrmex_desert_food");
        dungeonRegistry.registerCategory(myrmexJungleFood, "mia.jer.dungeon.myrmex_jungle_food");
        dungeonRegistry.registerCategory(myrmexTrash, "mia.jer.dungeon.myrmex_trash");
        // Cyclops
        dungeonRegistry.registerCategory(cyclopsCave, "mia.jer.dungeon.cyclops_cave");
        
        // Dragons
        dungeonRegistry.registerChest(fireDragon, WorldGenFireDragonCave.FIREDRAGON_CHEST);
        dungeonRegistry.registerChest(iceDragon, WorldGenIceDragonCave.ICEDRAGON_CHEST);
        // Myrmex
        dungeonRegistry.registerChest(myrmexLoot, WorldGenMyrmexDecoration.MYRMEX_GOLD_CHEST);
        dungeonRegistry.registerChest(myrmexDesertFood, WorldGenMyrmexDecoration.DESERT_MYRMEX_FOOD_CHEST);
        dungeonRegistry.registerChest(myrmexJungleFood, WorldGenMyrmexDecoration.JUNGLE_MYRMEX_FOOD_CHEST);
        dungeonRegistry.registerChest(myrmexTrash, WorldGenMyrmexDecoration.MYRMEX_TRASH_CHEST);
        // Cyclops
        dungeonRegistry.registerChest(cyclopsCave, WorldGenCyclopsCave.CYCLOPS_CHEST);
    }
    
    @Nonnull
    @Override
    public String getModId()
    {
        return ModIds.ICE_AND_FIRE;
    }
    
    private static class DeathWormSetter implements MobTableBuilder.EntityPropertySetter<EntityDeathWorm>
    {
        private final int variant;
        
        DeathWormSetter(int variant)
        {
            this.variant = variant;
        }
        
        @Override
        public void setProperties(EntityDeathWorm entityDeathWorm)
        {
            entityDeathWorm.setVariant(variant);
        }
    }
    
    private static class DragonSetter implements MobTableBuilder.EntityPropertySetter
    {
        private final int variant;
        
        DragonSetter(int variant)
        {
            this.variant = variant;
        }
        
        @Override
        public void setProperties(EntityLivingBase entity)
        {
            if (entity instanceof EntityDragonBase)
                if (variant >= 0)
                    ((EntityDragonBase) entity).setVariant(variant);
                else
                    ((EntityDragonBase) entity).setModelDead(true);
        }
    }
    
    private static class SeaSerpentSetter implements MobTableBuilder.EntityPropertySetter<EntitySeaSerpent>
    {
        private final int variant;
        
        SeaSerpentSetter(int variant)
        {
            this.variant = variant;
        }
        
        @Override
        public void setProperties(EntitySeaSerpent entity)
        {
            NBTTagCompound tag = new NBTTagCompound();
            tag.setFloat("Scale", 1f);
            entity.readEntityFromNBT(tag);
            entity.setVariant(variant);
        }
    }
    
    private static class MyrmexSetter implements MobTableBuilder.EntityPropertySetter
    {
        private final boolean jungle;
        
        MyrmexSetter(boolean jungle)
        {
            this.jungle = jungle;
        }
        
        @Override
        public void setProperties(EntityLivingBase entity)
        {
            if (entity instanceof EntityMyrmexBase)
                ((EntityMyrmexBase) entity).setJungleVariant(jungle);
        }
    }
    
    private static class TrollSetter implements MobTableBuilder.EntityPropertySetter<EntityTroll>
    {
        private final EnumTroll variant;
        
        TrollSetter(EnumTroll variant)
        {
            this.variant = variant;
        }
        
        @Override
        public void setProperties(EntityTroll entity)
        {
            entity.setType(variant);
        }
    }
    
    private static final IMobRenderHook RENDER_HOOK_DRAGON = ((renderInfo, entityLivingBase) ->
    {
        GlStateManager.translate(0f, 1f, 0f);
        return renderInfo;
    });
    
    private static final IMobRenderHook RENDER_HOOK_SEA_SERPENT = ((renderInfo, entityLivingBase) ->
    {
        GlStateManager.translate(0f, 0.5f, 0f);
        return renderInfo;
    });
    
    private static final IMobRenderHook RENDER_HOOK_TROLL = (((renderInfo, entityLivingBase) ->
    {
        GlStateManager.translate(0f, -1f, 0f);
        return renderInfo;
    }));
    
    private static final IMobRenderHook RENDER_HOOK_CYCLOPS = (((renderInfo, entityLivingBase) ->
    {
        GlStateManager.translate(0f, -2f, 0f);
        return renderInfo;
    }));
}
