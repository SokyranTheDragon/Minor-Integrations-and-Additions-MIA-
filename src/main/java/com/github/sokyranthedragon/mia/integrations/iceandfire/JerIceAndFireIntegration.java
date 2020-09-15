package com.github.sokyranthedragon.mia.integrations.iceandfire;

import com.github.alexthe666.iceandfire.IceAndFire;
import com.github.alexthe666.iceandfire.entity.*;
import com.github.alexthe666.iceandfire.enums.EnumTroll;
import com.github.alexthe666.iceandfire.item.IafItemRegistry;
import com.github.alexthe666.iceandfire.world.gen.*;
import com.github.sokyranthedragon.mia.Mia;
import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.integrations.iceandfire.client.EntityCustomSnowVillager;
import com.github.sokyranthedragon.mia.integrations.jer.*;
import com.github.sokyranthedragon.mia.integrations.jer.custom.CustomVillagerEntry;
import com.github.sokyranthedragon.mia.utilities.LootTableUtils;
import jeresources.api.IDungeonRegistry;
import jeresources.api.IMobRegistry;
import jeresources.api.conditionals.LightLevel;
import jeresources.api.drop.LootDrop;
import jeresources.api.render.IMobRenderHook;
import jeresources.entry.MobEntry;
import jeresources.registry.VillagerRegistry;
import jeresources.util.LootTableHelper;
import jeresources.util.MobTableBuilder;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityWitherSkeleton;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.init.Biomes;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Tuple;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.storage.loot.LootTableManager;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.fml.common.registry.VillagerRegistry.VillagerCareer;
import net.minecraftforge.fml.common.registry.VillagerRegistry.VillagerProfession;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.Collectors;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
class JerIceAndFireIntegration implements IJerIntegration
{
    // We're not checking the setters for Myrmex and Dragons, as we're using the same class for more than a single mob type.
    // Using their base class did not work as it wasn't considered the exact class that was required, but it accepted no class.
    @SuppressWarnings("unchecked")
    @Override
    public void addMobs(JustEnoughResources.CustomMobTableBuilder builder)
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
        builder.addWithIgnore(EntityFireDragon.SKELETON_LOOT, EntityFireDragon.class, new DragonSetter(-1));
        builder.addWithIgnore(EntityIceDragon.SKELETON_LOOT, EntityIceDragon.class, new DragonSetter(-1));
        for (int i = 0; i <= 3; i++)
        {
            // ResourceLocation is used as a key in HashMap, so we need to create our own to prevent replacing entries,
            // leaving us with drops for only a single dragon of each type.
            builder.addWithIgnore(new ResourceLocationWrapper(EntityFireDragon.FEMALE_LOOT, i), EntityFireDragon.class, new DragonSetter(i));
            builder.addWithIgnore(new ResourceLocationWrapper(EntityIceDragon.FEMALE_LOOT, i), EntityIceDragon.class, new DragonSetter(i));
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
            builder.add(new ResourceLocationWrapper(EntitySeaSerpent.LOOT, i), EntitySeaSerpent.class, new SeaSerpentSetter(i));
        builder.add(EntitySiren.LOOT, EntitySiren.class);
        builder.add(EntityStymphalianBird.LOOT, EntityStymphalianBird.class);
        builder.add(EntityTroll.FOREST_LOOT, EntityTroll.class, new TrollSetter(EnumTroll.FOREST));
        builder.add(EntityTroll.FROST_LOOT, EntityTroll.class, new TrollSetter(EnumTroll.FROST));
        builder.add(EntityTroll.MOUNTAIN_LOOT, EntityTroll.class, new TrollSetter(EnumTroll.MOUNTAIN));
        builder.add(EntityHydra.LOOT, EntityHydra.class);
        builder.add(EntityDreadBeast.LOOT, EntityDreadBeast.class);
        builder.add(EntityDreadGhoul.LOOT, EntityDreadGhoul.class);
        builder.add(LootTableUtils.loadUniqueEmptyLootTable(), EntityDreadHorse.class);
        builder.add(EntityDreadKnight.LOOT, EntityDreadKnight.class);
        builder.add(EntityDreadLich.LOOT, EntityDreadLich.class);
        builder.add(LootTableUtils.loadUniqueEmptyLootTable(), EntityDreadQueen.class);
        builder.add(EntityDreadScuttler.LOOT, EntityDreadScuttler.class);
        builder.add(EntityDreadThrall.LOOT, EntityDreadThrall.class);
    }
    
    @Override
    public void configureMob(ResourceLocation resource, EntityLivingBase entity, @Nullable LootTableManager manager, IMobRegistry mobRegistry)
    {
        LightLevel lightLevel = LightLevel.any;
        Set<Biome> validBiomes = new HashSet<>();
        List<LootDrop> loot = null;
        if (manager != null)
        {
            if (entity instanceof EntityDragonBase || entity instanceof EntitySeaSerpent)
                loot = LootTableUtils.toDrops(manager.getLootTableFromLocation(resource), entity);
            else
                loot = LootTableHelper.toDrops(manager.getLootTableFromLocation(resource));
        }
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
            boolean isFire = dragon instanceof EntityFireDragon;
            
            for (Biome biome : Biome.REGISTRY)
            {
                if (dragon.isModelDead())
                {
                    if (isFire)
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
                    if (isFire)
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
                if (loot != null && IceAndFire.CONFIG.dragonDropSkull)
                    loot.add(new LootDrop(new ItemStack(IafItemRegistry.dragon_skull, 1, isFire ? 0 : 1)));
            }
            else
            {
                experienceMin = 15;
                experienceMax = 190;
                
                if (loot != null)
                {
                    List<LootDrop> maleDrops = LootTableUtils.toDrops(manager.getLootTableFromLocation(isFire ? EntityFireDragon.MALE_LOOT : EntityIceDragon.MALE_LOOT), entity);
                    List<LootDrop> finalDrops = new ArrayList<>();
    
                    for (LootDrop femaleDrop : loot)
                    {
                        List<LootDrop> matches = maleDrops.stream().filter(entry -> femaleDrop.compareTo(entry) == 0).collect(Collectors.toList());
                        if (matches.isEmpty())
                            femaleDrop.addConditional(ExtraConditional.femaleOnly);
                        else
                            maleDrops.removeAll(matches);
                        
                        finalDrops.add(femaleDrop);
                    }
                    
                    for (LootDrop maleDrop : maleDrops)
                    {
                        maleDrop.addConditional(ExtraConditional.maleOnly);
                        finalDrops.add(maleDrop);
                    }
                    
                    loot = finalDrops;
                    
                    if (IceAndFire.CONFIG.dragonDropHeart)
                        loot.add(new LootDrop(new ItemStack(isFire ? IafItemRegistry.fire_dragon_heart : IafItemRegistry.ice_dragon_heart)));
                    if (IceAndFire.CONFIG.dragonDropBlood)
                        loot.add(new LootDrop(new ItemStack(isFire ? IafItemRegistry.fire_dragon_blood : IafItemRegistry.ice_dragon_blood)));
                }
            }
        }
        else if (entity instanceof EntityTroll)
        {
            EnumTroll troll = ((EntityTroll) entity).getType();
            
            validBiomes.addAll(BiomeDictionary.getBiomes(troll.spawnBiome));
            experienceMin = 15;
            experienceMax = 24;
            
            if (loot != null)
            {
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
        }
        else if (entity instanceof EntityHydra)
        {
            validBiomes.addAll(BiomeDictionary.getBiomes(BiomeDictionary.Type.SWAMP));
            experienceMin = 5;
            experienceMax = 5;
        }
        else if (entity instanceof EntityDreadMob)
        {
            if (loot != null)
            {
                if (entity instanceof EntityDreadKnight)
                {
                    loot.add(new LootDrop(IafItemRegistry.dread_knight_sword, 0, 1));
                    loot.add(new LootDrop(EntityDreadKnight.SHIELD));
                }
                else if (entity instanceof EntityDreadLich)
                {
                    validBiomes.addAll((BiomeDictionary.getBiomes(BiomeDictionary.Type.SNOWY)));
                    loot.add(new LootDrop(IafItemRegistry.lich_staff, 0, 1));
                }
                else if (entity instanceof EntityDreadQueen)
                {
                    loot.add(new LootDrop(IafItemRegistry.dread_queen_sword, 0, 1));
                    loot.add(new LootDrop(IafItemRegistry.dread_queen_staff, 0, 1));
                }
            }
            else if (entity instanceof EntityDreadKnight)
                validBiomes.addAll((BiomeDictionary.getBiomes(BiomeDictionary.Type.SNOWY)));
            
            experienceMin = 5;
            experienceMax = 5;
        }
        
        if (loot == null)
        {
            if (validBiomes.isEmpty())
                mobRegistry.register(entity, lightLevel, experienceMin, experienceMax, resource);
            else
                mobRegistry.register(entity, lightLevel, experienceMin, experienceMax, validBiomes.stream().map(Biome::getBiomeName).toArray(String[]::new), resource);
        }
        else
        {
            LootDrop[] drops = loot.toArray(new LootDrop[0]);
            
            if (validBiomes.isEmpty())
                mobRegistry.register(entity, lightLevel, experienceMin, experienceMax, drops);
            else
                mobRegistry.register(entity, lightLevel, experienceMin, experienceMax, validBiomes.stream().map(Biome::getBiomeName).toArray(String[]::new), drops);
        }
    }
    
    @Override
    public void overrideExistingMobDrops(MobEntry mobEntry)
    {
        if (mobEntry.getEntity() instanceof EntityWitherSkeleton)
            mobEntry.addDrop(new LootDrop(IafItemRegistry.witherbone, 0, 2));
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
    public void addDungeonLoot(IDungeonRegistry dungeonRegistry, World world)
    {
        // Myrmex
        final String myrmexLoot = "chest/myrmex_loot";
        final String myrmexDesertFood = "chest/myrmex_desert_food";
        final String myrmexJungleFood = "chest/myrmex_jungle_food";
        final String myrmexTrash = "chest/myrmex_trash";
        // Cyclops
        final String cyclopsCave = "chests/cyclops_cave";
        // Hydra
        final String hydraCave = "chests/hydra_cave";
        
        // Myrmex
        dungeonRegistry.registerCategory(myrmexLoot, "mia.jer.dungeon.myrmex_loot");
        dungeonRegistry.registerCategory(myrmexDesertFood, "mia.jer.dungeon.myrmex_desert_food");
        dungeonRegistry.registerCategory(myrmexJungleFood, "mia.jer.dungeon.myrmex_jungle_food");
        dungeonRegistry.registerCategory(myrmexTrash, "mia.jer.dungeon.myrmex_trash");
        // Cyclops
        dungeonRegistry.registerCategory(cyclopsCave, "mia.jer.dungeon.cyclops_cave");
        // Hydra
        dungeonRegistry.registerCategory(hydraCave, "mia.jer.dungeon.hydra_cave");
        
        // Dragons
        JerHelpers.addDungeonLootCategory(world, dungeonRegistry, "fire_dragon",
            WorldGenFireDragonCave.FIREDRAGON_CHEST,
            WorldGenFireDragonCave.FIREDRAGON_MALE_CHEST);
        JerHelpers.addDungeonLootCategory(world, dungeonRegistry, "ice_dragon",
            WorldGenIceDragonCave.ICEDRAGON_CHEST,
            WorldGenIceDragonCave.ICEDRAGON_MALE_CHEST);
        // Myrmex
        dungeonRegistry.registerChest(myrmexLoot, WorldGenMyrmexDecoration.MYRMEX_GOLD_CHEST);
        dungeonRegistry.registerChest(myrmexDesertFood, WorldGenMyrmexDecoration.DESERT_MYRMEX_FOOD_CHEST);
        dungeonRegistry.registerChest(myrmexJungleFood, WorldGenMyrmexDecoration.JUNGLE_MYRMEX_FOOD_CHEST);
        dungeonRegistry.registerChest(myrmexTrash, WorldGenMyrmexDecoration.MYRMEX_TRASH_CHEST);
        // Cyclops
        dungeonRegistry.registerChest(cyclopsCave, WorldGenCyclopsCave.CYCLOPS_CHEST);
        // Hydra
        dungeonRegistry.registerChest(hydraCave, WorldGenHydraCave.HYDRA_CHEST);
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public void addVillagerTrades(VillagerRegistry villagerRegistry, boolean acceptsCustomEntries)
    {
        if (acceptsCustomEntries)
        {
            try
            {
                Field tradesField = VillagerCareer.class.getDeclaredField("trades");
                tradesField.setAccessible(true);
                IafVillagerRegistry iafRegistry = IafVillagerRegistry.INSTANCE;
                
                for (Map.Entry<Integer, VillagerProfession> entry : iafRegistry.professions.entrySet())
                {
                    VillagerCareer career = entry.getValue().getCareer(0);
                    List<List<EntityVillager.ITradeList>> trades = (List<List<EntityVillager.ITradeList>>) tradesField.get(career);
                    villagerRegistry.addVillagerEntry(new SnowVillagerEntry(career.getName(), entry.getKey(), trades));
                }
                
                for (Tuple<VillagerProfession, Class<? extends EntityMyrmexBase>> desertMyrmex : new Tuple[]{
                    new Tuple<>(iafRegistry.desertMyrmexWorker, EntityMyrmexWorker.class),
                    new Tuple<>(iafRegistry.desertMyrmexSoldier, EntityMyrmexSoldier.class),
                    new Tuple<>(iafRegistry.desertMyrmexSentinel, EntityMyrmexSentinel.class),
                    new Tuple<>(iafRegistry.desertMyrmexRoyal, EntityMyrmexRoyal.class),
                    new Tuple<>(iafRegistry.desertMyrmexQueen, EntityMyrmexQueen.class) })
                {
                    VillagerCareer career = desertMyrmex.getFirst().getCareer(0);
                    List<List<EntityVillager.ITradeList>> trades = (List<List<EntityVillager.ITradeList>>) tradesField.get(career);
                    villagerRegistry.addVillagerEntry(new MyrmexVillagerEntry(career.getName().substring("desert_".length()), trades, desertMyrmex.getSecond(), false));
                }
                
                for (Tuple<VillagerProfession, Class<? extends EntityMyrmexBase>> desertMyrmex : new Tuple[]{
                    new Tuple<>(iafRegistry.jungleMyrmexWorker, EntityMyrmexWorker.class),
                    new Tuple<>(iafRegistry.jungleMyrmexSoldier, EntityMyrmexSoldier.class),
                    new Tuple<>(iafRegistry.jungleMyrmexSentinel, EntityMyrmexSentinel.class),
                    new Tuple<>(iafRegistry.jungleMyrmexRoyal, EntityMyrmexRoyal.class),
                    new Tuple<>(iafRegistry.jungleMyrmexQueen, EntityMyrmexQueen.class) })
                {
                    VillagerCareer career = desertMyrmex.getFirst().getCareer(0);
                    List<List<EntityVillager.ITradeList>> trades = (List<List<EntityVillager.ITradeList>>) tradesField.get(career);
                    villagerRegistry.addVillagerEntry(new MyrmexVillagerEntry(career.getName().substring("jungle_".length()), trades, desertMyrmex.getSecond(), true));
                }
            } catch (NoSuchFieldException | IllegalAccessException | NoSuchMethodException e)
            {
                Mia.LOGGER.error("Could not add villager trades for Ice and Fire", e);
            }
        }
    }
    
    @Override
    public ModIds getModId()
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
    
    @SuppressWarnings("rawtypes")
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
    
    @SuppressWarnings("rawtypes")
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
    
    @SuppressWarnings("rawtypes")
    private static final IMobRenderHook RENDER_HOOK_DRAGON = ((renderInfo, entityLivingBase) ->
    {
        GlStateManager.translate(0f, 1f, 0f);
        return renderInfo;
    });
    
    @SuppressWarnings("rawtypes")
    private static final IMobRenderHook RENDER_HOOK_SEA_SERPENT = ((renderInfo, entityLivingBase) ->
    {
        GlStateManager.translate(0f, 0.5f, 0f);
        return renderInfo;
    });
    
    @SuppressWarnings("rawtypes")
    private static final IMobRenderHook RENDER_HOOK_TROLL = (((renderInfo, entityLivingBase) ->
    {
        GlStateManager.translate(0f, -1f, 0f);
        return renderInfo;
    }));
    
    @SuppressWarnings("rawtypes")
    private static final IMobRenderHook RENDER_HOOK_CYCLOPS = (((renderInfo, entityLivingBase) ->
    {
        GlStateManager.translate(0f, -2f, 0f);
        return renderInfo;
    }));
    
    private static class SnowVillagerEntry extends CustomVillagerEntry
    {
        public SnowVillagerEntry(String name, int profession, List<List<EntityVillager.ITradeList>> tradesLists)
        {
            super(name, profession, 0, tradesLists);
        }
        
        @Override
        public EntityLivingBase getEntity(@Nonnull Minecraft minecraft)
        {
            try
            {
                return new EntityCustomSnowVillager(minecraft.world, getProfession());
            } catch (RuntimeException var11)
            {
                return new EntityCustomSnowVillager(minecraft.world);
            }
        }
    }
    
    private static class MyrmexVillagerEntry extends CustomVillagerEntry
    {
        private final Constructor<? extends EntityMyrmexBase> myrmexConstructor;
        private final boolean jungleVariant;
        
        public MyrmexVillagerEntry(String name, List<List<EntityVillager.ITradeList>> tradesLists, Class<? extends EntityMyrmexBase> entityMyrmex, boolean jungleVariant) throws NoSuchMethodException
        {
            super(name, tradesLists);
            this.jungleVariant = jungleVariant;
            myrmexConstructor = entityMyrmex.getConstructor(World.class);
        }
        
        @Override
        public EntityLivingBase getEntity(@Nonnull Minecraft minecraft) throws IllegalAccessException, InvocationTargetException, InstantiationException
        {
            EntityMyrmexBase myrmex = myrmexConstructor.newInstance(minecraft.world);
            myrmex.setJungleVariant(jungleVariant);
            return myrmex;
        }
        
        @Override
        public String getDisplayName()
        {
            return "entity." + getName() + ".name";
        }
        
        @Override
        public float getRenderScale()
        {
            return 20f;
        }
    }
}
