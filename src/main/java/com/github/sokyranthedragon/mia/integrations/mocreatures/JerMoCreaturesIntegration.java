package com.github.sokyranthedragon.mia.integrations.mocreatures;

import com.github.sokyranthedragon.mia.config.MiaConfig;
import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.integrations.jer.ExtraConditional;
import com.github.sokyranthedragon.mia.integrations.jer.IJerIntegration;
import com.github.sokyranthedragon.mia.integrations.jer.JustEnoughResources;
import com.pam.harvestcraft.item.ItemRegistry;
import drzhark.mocreatures.MoCreatures;
import drzhark.mocreatures.entity.IMoCEntity;
import drzhark.mocreatures.entity.MoCEntityAquatic;
import drzhark.mocreatures.entity.MoCEntityMob;
import drzhark.mocreatures.entity.ambient.*;
import drzhark.mocreatures.entity.aquatic.*;
import drzhark.mocreatures.entity.monster.*;
import drzhark.mocreatures.entity.passive.*;
import drzhark.mocreatures.init.MoCItems;
import jeresources.api.IMobRegistry;
import jeresources.api.conditionals.Conditional;
import jeresources.api.conditionals.ExtendedConditional;
import jeresources.api.conditionals.LightLevel;
import jeresources.api.drop.LootDrop;
import jeresources.api.render.IMobRenderHook;
import jeresources.util.LootTableHelper;
import jeresources.util.MobTableBuilder;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.storage.loot.LootTableManager;
import net.minecraftforge.common.BiomeDictionary;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.github.sokyranthedragon.mia.config.MoCreaturesConfiguration.replaceFishDrops;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
class JerMoCreaturesIntegration implements IJerIntegration
{
    @SuppressWarnings("unchecked")
    @Override
    public void addMobs(JustEnoughResources.CustomMobTableBuilder builder)
    {
        // Horses
        for (int i = 21; i <= 26; i++)
            builder.add(ModIds.MIA.loadSimple("mocreatures/horse/horse_" + i), MoCEntityHorse.class, new GenericVariantSetter(i));
        for (int i = 38; i <= 40; i++)
            builder.add(ModIds.MIA.loadSimple("mocreatures/horse/horse_" + i), MoCEntityHorse.class, new GenericVariantSetter(i));
        builder.add(ModIds.MIA.loadSimple("mocreatures/horse/horse_32"), MoCEntityHorse.class, new GenericVariantSetter(32));
        builder.add(ModIds.MIA.loadSimple("mocreatures/horse/horse_36"), MoCEntityHorse.class, new GenericVariantSetter(36));
        // Passive entities
        builder.add(ModIds.MIA.loadSimple("mocreatures/passive/bird"), MoCEntityBird.class);
        builder.add(ModIds.MIA.loadSimple("mocreatures/passive/boar"), MoCEntityBoar.class);
        builder.add(ModIds.MIA.loadSimple("mocreatures/passive/crocodile"), MoCEntityCrocodile.class);
        builder.add(ModIds.MIA.loadSimple("mocreatures/passive/deer"), MoCEntityDeer.class);
        builder.add(ModIds.MIA.loadSimple("mocreatures/passive/duck"), MoCEntityDuck.class);
        builder.add(ModIds.MIA.loadSimple("mocreatures/passive/ent"), MoCEntityEnt.class);
        builder.add(ModIds.MIA.loadSimple("mocreatures/passive/fox"), MoCEntityFox.class);
        builder.add(ModIds.MIA.loadSimple("mocreatures/passive/goat"), MoCEntityGoat.class);
        builder.add(ModIds.MIA.loadSimple("mocreatures/passive/komodo"), MoCEntityKomodo.class);
        builder.add(ModIds.MIA.loadSimple("mocreatures/passive/mole"), MoCEntityMole.class);
        builder.add(ModIds.MIA.loadSimple("mocreatures/passive/mouse"), MoCEntityMouse.class);
        builder.add(ModIds.MIA.loadSimple("mocreatures/passive/raccoon"), MoCEntityRaccoon.class);
        builder.add(ModIds.MIA.loadSimple("mocreatures/passive/turkey"), MoCEntityTurkey.class);
        builder.add(ModIds.MIA.loadSimple("mocreatures/passive/turtle"), MoCEntityTurtle.class);
        // Bears
        builder.add(ModIds.MIA.loadSimple("mocreatures/passive/bear/black"), MoCEntityBlackBear.class);
        builder.add(ModIds.MIA.loadSimple("mocreatures/passive/bear/grizzly"), MoCEntityGrizzlyBear.class);
        builder.add(ModIds.MIA.loadSimple("mocreatures/passive/bear/panda"), MoCEntityPandaBear.class);
        builder.add(ModIds.MIA.loadSimple("mocreatures/passive/bear/polar"), MoCEntityPolarBear.class);
        // Big cats
        builder.add(ModIds.MIA.loadSimple("mocreatures/passive/big_cat/leopard"), MoCEntityLeopard.class);
        builder.add(ModIds.MIA.loadSimple("mocreatures/passive/big_cat/lion"), MoCEntityLion.class);
        builder.add(ModIds.MIA.loadSimple("mocreatures/passive/big_cat/panther"), MoCEntityPanther.class);
        builder.add(ModIds.MIA.loadSimple("mocreatures/passive/big_cat/tiger"), MoCEntityTiger.class);
        // Ostrich
        for (int i = 4; i <= 8; i++)
            builder.add(ModIds.MIA.loadSimple("mocreatures/passive/ostrich/ostrich_" + i), MoCEntityOstrich.class, new GenericVariantSetter(i));
        // Pet scorpion
        for (int i = 1; i <= 5; i++)
            builder.add(ModIds.MIA.loadSimple("mocreatures/passive/scorpion/scorpion_" + i), MoCEntityPetScorpion.class, new GenericVariantSetter(i));
        // Snake
        for (int i = 1; i <= 5; i++)
            builder.add(ModIds.MIA.loadSimple("mocreatures/passive/snake/snake_" + i), MoCEntitySnake.class, new GenericVariantSetter(i));
        
        
        // Hostile entities
        // Ogres
        builder.add(ModIds.MIA.loadSimple("mocreatures/hostile/ogre_cave"), MoCEntityCaveOgre.class);
        builder.add(ModIds.MIA.loadSimple("mocreatures/hostile/ogre_fire"), MoCEntityFireOgre.class);
        builder.add(ModIds.MIA.loadSimple("mocreatures/hostile/ogre_green"), MoCEntityGreenOgre.class);
        // Wraiths
        builder.add(ModIds.MIA.loadSimple("mocreatures/hostile/wraith"), MoCEntityWraith.class);
        builder.add(ModIds.MIA.loadSimple("mocreatures/hostile/wraith_flame"), MoCEntityFlameWraith.class);
        // Manticores
        for (int i = 1; i <= 4; i++)
            builder.add(ModIds.MIA.loadSimple("mocreatures/hostile/manticore/manticore_" + i), MoCEntityManticore.class, new GenericVariantSetter(i));
        // Rats
        builder.add(ModIds.MIA.loadSimple("mocreatures/hostile/rat"), MoCEntityRat.class);
        builder.add(ModIds.MIA.loadSimple("mocreatures/hostile/rat_hell"), MoCEntityHellRat.class);
        // Scorpions
        for (int i = 1; i <= 4; i++)
            builder.add(ModIds.MIA.loadSimple("mocreatures/hostile/scorpion/scorpion_" + i), MoCEntityScorpion.class, new GenericVariantSetter(i));
        // Silver skeletons
        builder.add(ModIds.MIA.loadSimple("mocreatures/hostile/skeleton_silver"), MoCEntitySilverSkeleton.class);
        // Werewolf
        builder.add(ModIds.MIA.loadSimple("mocreatures/hostile/werewolf_human"), MoCEntityWerewolf.class, new WerewolfSetter(true));
        builder.add(ModIds.MIA.loadSimple("mocreatures/hostile/werewolf_wild"), MoCEntityWerewolf.class, new WerewolfSetter(false));
        // Wild wolf
        builder.add(ModIds.MIA.loadSimple("mocreatures/hostile/wolf_wild"), MoCEntityWWolf.class);
        
        
        // Ambient
        builder.add(ModIds.MIA.loadSimple("mocreatures/ambient/crab"), MoCEntityCrab.class);
        builder.add(ModIds.MIA.loadSimple("mocreatures/ambient/snail"), MoCEntitySnail.class);
        builder.add(ModIds.MIA.loadSimple("mocreatures/ambient/maggot"), MoCEntityMaggot.class);
        
        
        // Aquatic
        builder.add(ModIds.MIA.loadSimple("mocreatures/aquatic/dolphin"), MoCEntityDolphin.class);
        builder.add(ModIds.MIA.loadSimple("mocreatures/aquatic/jellyfish"), MoCEntityJellyFish.class);
        builder.add(ModIds.MIA.loadSimple("mocreatures/aquatic/shark"), MoCEntityShark.class);
        // Medium fish
        builder.add(ModIds.MIA.loadSimple("mocreatures/aquatic/fish_medium/bass"), MoCEntityBass.class);
        builder.add(ModIds.MIA.loadSimple("mocreatures/aquatic/fish_medium/cod"), MoCEntityCod.class);
        builder.add(ModIds.MIA.loadSimple("mocreatures/aquatic/fish_medium/salmon"), MoCEntitySalmon.class);
        // Small fish
        builder.add(ModIds.MIA.loadSimple("mocreatures/aquatic/fish_small/anchovy"), MoCEntityAnchovy.class);
        builder.add(ModIds.MIA.loadSimple("mocreatures/aquatic/fish_small/angel_fish"), MoCEntityAngelFish.class);
        builder.add(ModIds.MIA.loadSimple("mocreatures/aquatic/fish_small/angler"), MoCEntityAngler.class);
        builder.add(ModIds.MIA.loadSimple("mocreatures/aquatic/fish_small/clownfish"), MoCEntityClownFish.class);
        builder.add(ModIds.MIA.loadSimple("mocreatures/aquatic/fish_small/goldfish"), MoCEntityGoldFish.class);
        builder.add(ModIds.MIA.loadSimple("mocreatures/aquatic/fish_small/hippo_tang"), MoCEntityHippoTang.class);
        builder.add(ModIds.MIA.loadSimple("mocreatures/aquatic/fish_small/manderin"), MoCEntityManderin.class);
        builder.add(ModIds.MIA.loadSimple("mocreatures/aquatic/fish_small/piranha"), MoCEntityPiranha.class);
    }
    
    @Override
    public void configureMob(ResourceLocation resource, EntityLivingBase entity, @Nullable LootTableManager manager, IMobRegistry mobRegistry)
    {
        Set<Biome> validBiomes = new HashSet<>();
        List<LootDrop> drops = null;
        if (manager != null)
            drops = LootTableHelper.toDrops(manager.getLootTableFromLocation(resource));
        
        LightLevel lightLevel = LightLevel.any;
        int experienceMin = 5;
        int experienceMax = 5;
        
        if (entity instanceof EntityAnimal || entity instanceof MoCEntityAquatic)
        {
            experienceMin = 1;
            experienceMax = 3;
        }
        
        if (drops != null)
        {
            drops.forEach(lootDrop ->
            {
                Item item = lootDrop.item.getItem();
                Block block = Block.getBlockFromItem(item);
                
                if (item == Items.FISH)
                {
                    if (replaceFishDrops && entity instanceof MoCEntityCod)
                    {
                        lootDrop.item = new ItemStack(Items.FISH, lootDrop.item.getCount(), 1);
                        if (MiaConfig.addCookedDrops)
                            lootDrop.smeltedItem = new ItemStack(Items.COOKED_FISH, lootDrop.item.getCount(), 1);
                    }
                    else if (replaceFishDrops && entity instanceof MoCEntityClownFish)
                        lootDrop.item = new ItemStack(Items.FISH, lootDrop.item.getCount(), 2);
                    else if (ModIds.HARVESTCRAFT.isLoaded && entity instanceof MoCEntityAnchovy)
                        lootDrop.item = new ItemStack(ItemRegistry.anchovyrawItem);
                    else if (ModIds.HARVESTCRAFT.isLoaded && entity instanceof MoCEntityBass)
                        lootDrop.item = new ItemStack(ItemRegistry.bassrawItem);
                    else
                        lootDrop.smeltedItem = new ItemStack(Items.COOKED_FISH);
                    
                }
                else if (item == MoCItems.rawTurkey && MiaConfig.addCookedDrops)
                    lootDrop.smeltedItem = new ItemStack(MoCItems.cookedTurkey);
                else if (item == MoCItems.ratRaw && MiaConfig.addCookedDrops)
                    lootDrop.smeltedItem = new ItemStack(MoCItems.ratCooked);
                else if (item == MoCItems.ostrichraw && MiaConfig.addCookedDrops)
                    lootDrop.smeltedItem = new ItemStack(MoCItems.ostrichcooked);
                else if (item == MoCItems.crabraw && MiaConfig.addCookedDrops)
                    lootDrop.smeltedItem = new ItemStack(MoCItems.crabcooked);
                else if (item == Items.PORKCHOP && MiaConfig.addCookedDrops)
                    lootDrop.smeltedItem = new ItemStack(Items.COOKED_PORKCHOP);
                else if (item == MoCItems.turtleraw && MiaConfig.addCookedDrops && ModIds.HARVESTCRAFT.isLoaded)
                    lootDrop.smeltedItem = new ItemStack(ItemRegistry.turtlecookedItem);
                else if (item == MoCItems.bo && entity instanceof MoCEntityTurtle)
                    lootDrop.addConditional(new ExtendedConditional(ExtraConditional.named, "'Donatello', 'donatello'"));
                else if (item == MoCItems.katana && entity instanceof MoCEntityTurtle)
                    lootDrop.addConditional(new ExtendedConditional(ExtraConditional.named, "'Leonardo', 'leonardo'"));
                else if (item == MoCItems.sai && entity instanceof MoCEntityTurtle)
                    lootDrop.addConditional(new ExtendedConditional(ExtraConditional.named, "'Rafael', 'rafael', 'Raphael', 'raphael'"));
                else if (item == MoCItems.nunchaku && entity instanceof MoCEntityTurtle)
                    lootDrop.addConditional(new ExtendedConditional(ExtraConditional.named, "'Michelangelo', 'michelangelo', 'Michaelangelo', 'michaelangelo'"));
                else if (item == Items.STRING && (entity instanceof MoCEntityScorpion || entity instanceof MoCEntityPetScorpion))
                    lootDrop.addConditional(ExtraConditional.isNotAdult);
                else if (item == MoCItems.mocegg)
                {
                    if (entity instanceof MoCEntityWyvern)
                    {
                        // Mother Wyvern
                        if (lootDrop.item.getMetadata() == 54)
                            lootDrop.chance = MoCreatures.proxy.motherWyvernEggDropChance;
                        else
                            lootDrop.chance = MoCreatures.proxy.wyvernEggDropChance;
                    }
                    
                    lootDrop.chance = MoCreatures.proxy.rareItemDropChance / 100f;
                    lootDrop.addConditional(ExtraConditional.isAdult);
                }
                else if (item == MoCItems.heartdarkness ||
                    item == MoCItems.heartfire ||
                    item == MoCItems.heartundead ||
                    item == MoCItems.scorpStingNether ||
                    item == MoCItems.scorpStingCave ||
                    item == MoCItems.scorpStingFrost ||
                    item == MoCItems.scorpStingDirt ||
                    block == Blocks.FIRE)
                    lootDrop.chance = MoCreatures.proxy.rareItemDropChance / 100f;
            });
            
            if (ModIds.HARVESTCRAFT.isLoaded)
            {
                if (entity instanceof MoCEntityDeer)
                {
                    LootDrop drop = new LootDrop(new ItemStack(ItemRegistry.venisonrawItem), 0, 2, Conditional.affectedByLooting);
                    if (MiaConfig.addCookedDrops)
                        drop.smeltedItem = new ItemStack(ItemRegistry.venisoncookedItem);
                    drops.add(drop);
                }
                else if (entity instanceof MoCEntityDuck)
                {
                    LootDrop drop = new LootDrop(new ItemStack(ItemRegistry.duckrawItem), 0, 2, Conditional.affectedByLooting);
                    if (MiaConfig.addCookedDrops)
                        drop.smeltedItem = new ItemStack(ItemRegistry.duckcookedItem);
                    drops.add(drop);
                }
                else if (entity instanceof MoCEntityJellyFish)
                    drops.add(new LootDrop(new ItemStack(ItemRegistry.jellyfishrawItem), 0, 2, Conditional.affectedByLooting));
            }
        }
        
        if (entity instanceof MoCEntityMob)
            lightLevel = LightLevel.hostile;
        
        // TODO: Passive mobs
        // Aquatic mobs
        if (entity instanceof MoCEntityMantaRay)
            validBiomes.addAll(BiomeDictionary.getBiomes(BiomeDictionary.Type.OCEAN));
        else if (entity instanceof MoCEntityAquatic)
        {
            boolean noSwamp = entity instanceof MoCEntityDolphin;
            boolean noBeachSwamp = entity instanceof MoCEntityJellyFish || entity instanceof MoCEntityShark;
            boolean noRiver = entity instanceof MoCEntityAngler || entity instanceof MoCEntityClownFish || entity instanceof MoCEntityHippoTang || entity instanceof MoCEntityManderin;
            boolean noOcean = entity instanceof MoCEntityGoldFish;
            
            validBiomes.addAll(BiomeDictionary.getBiomes(BiomeDictionary.Type.WATER));
            if (!noBeachSwamp)
                validBiomes.addAll(BiomeDictionary.getBiomes(BiomeDictionary.Type.BEACH));
            if (!noSwamp && !noBeachSwamp)
                validBiomes.addAll(BiomeDictionary.getBiomes(BiomeDictionary.Type.SPOOKY));
            if (!noOcean)
                validBiomes.addAll(BiomeDictionary.getBiomes(BiomeDictionary.Type.OCEAN));
            if (!noRiver)
                validBiomes.addAll(BiomeDictionary.getBiomes(BiomeDictionary.Type.RIVER));
        }
        // Hostile mobs
        else if (entity instanceof MoCEntityHellRat)
        {
            validBiomes.addAll(BiomeDictionary.getBiomes(BiomeDictionary.Type.NETHER));
            validBiomes.addAll(BiomeDictionary.getBiomes(BiomeDictionary.Type.DEAD));
            validBiomes.addAll(BiomeDictionary.getBiomes(BiomeDictionary.Type.SPOOKY));
        }
        else if (entity instanceof MoCEntityMob)
        {
            boolean fireOgre = entity instanceof MoCEntityFireOgre;
            boolean greenOgre = entity instanceof MoCEntityGreenOgre;
            boolean manticore = entity instanceof MoCEntityManticore;
            
            validBiomes.addAll(BiomeDictionary.getBiomes(BiomeDictionary.Type.SANDY));
            validBiomes.addAll(BiomeDictionary.getBiomes(BiomeDictionary.Type.FOREST));
            if (!greenOgre && !fireOgre)
                validBiomes.addAll(BiomeDictionary.getBiomes(BiomeDictionary.Type.SNOWY));
            validBiomes.addAll(BiomeDictionary.getBiomes(BiomeDictionary.Type.JUNGLE));
            validBiomes.addAll(BiomeDictionary.getBiomes(BiomeDictionary.Type.HILLS));
            if (!manticore)
                validBiomes.addAll(BiomeDictionary.getBiomes(BiomeDictionary.Type.MESA));
            validBiomes.addAll(BiomeDictionary.getBiomes(BiomeDictionary.Type.MOUNTAIN));
            validBiomes.addAll(BiomeDictionary.getBiomes(BiomeDictionary.Type.PLAINS));
            validBiomes.addAll(BiomeDictionary.getBiomes(BiomeDictionary.Type.SWAMP));
            validBiomes.addAll(BiomeDictionary.getBiomes(BiomeDictionary.Type.WASTELAND));
            
            if (entity instanceof MoCEntityHorseMob
                || fireOgre
                || manticore)
            {
                validBiomes.addAll(BiomeDictionary.getBiomes(BiomeDictionary.Type.NETHER));
                validBiomes.addAll(BiomeDictionary.getBiomes(BiomeDictionary.Type.DEAD));
                validBiomes.addAll(BiomeDictionary.getBiomes(BiomeDictionary.Type.SPOOKY));
            }
            else if (entity instanceof MoCEntityCaveOgre
                || entity instanceof MoCEntitySilverSkeleton
                || entity instanceof MoCEntityWraith
                || greenOgre)
            {
                validBiomes.addAll(BiomeDictionary.getBiomes(BiomeDictionary.Type.DEAD));
                validBiomes.addAll(BiomeDictionary.getBiomes(BiomeDictionary.Type.SPOOKY));
            }
            else if (entity instanceof MoCEntityScorpion)
                validBiomes.addAll(BiomeDictionary.getBiomes(BiomeDictionary.Type.NETHER));
        }
        // Ambient mobs
        else if (entity instanceof MoCEntityCrab)
        {
            validBiomes.addAll(BiomeDictionary.getBiomes(BiomeDictionary.Type.BEACH));
            validBiomes.addAll(BiomeDictionary.getBiomes(BiomeDictionary.Type.WATER));
        }
        else
        {
            validBiomes.addAll(BiomeDictionary.getBiomes(BiomeDictionary.Type.FOREST));
            validBiomes.addAll(BiomeDictionary.getBiomes(BiomeDictionary.Type.JUNGLE));
            validBiomes.addAll(BiomeDictionary.getBiomes(BiomeDictionary.Type.PLAINS));
            
            //noinspection StatementWithEmptyBody
            if (entity instanceof MoCEntitySnail)
            {
                // Do nothing. It's here for readability
            }
            else if (entity instanceof MoCEntityMaggot || entity instanceof MoCEntityRoach)
                validBiomes.addAll(BiomeDictionary.getBiomes(BiomeDictionary.Type.SWAMP));
            else
            {
                validBiomes.addAll(BiomeDictionary.getBiomes(BiomeDictionary.Type.HILLS));
                validBiomes.addAll(BiomeDictionary.getBiomes(BiomeDictionary.Type.MESA));
                validBiomes.addAll(BiomeDictionary.getBiomes(BiomeDictionary.Type.MOUNTAIN));
                
                //noinspection StatementWithEmptyBody
                if (entity instanceof MoCEntityBee || entity instanceof MoCEntityButterfly)
                {
                    // Do nothing. It's here for readability
                }
                else
                {
                    validBiomes.addAll(BiomeDictionary.getBiomes(BiomeDictionary.Type.SWAMP));
                    
                    if (entity instanceof MoCEntityAnt || entity instanceof MoCEntityFly)
                        validBiomes.addAll(BiomeDictionary.getBiomes(BiomeDictionary.Type.WASTELAND));
                    else if (entity instanceof MoCEntityDragonfly)
                        validBiomes.addAll(BiomeDictionary.getBiomes(BiomeDictionary.Type.BEACH));
                }
            }
        }
        
        if (drops == null)
        {
            if (validBiomes.isEmpty())
                mobRegistry.register(entity, lightLevel, experienceMin, experienceMax, resource);
            else
                mobRegistry.register(entity, lightLevel, experienceMin, experienceMax, validBiomes.stream().map(Biome::getBiomeName).toArray(String[]::new), resource);
        }
        else
        {
            LootDrop[] dropsArray = drops.toArray(new LootDrop[0]);
            
            if (validBiomes.isEmpty())
                mobRegistry.register(entity, lightLevel, experienceMin, experienceMax, dropsArray);
            else
                mobRegistry.register(entity, lightLevel, experienceMin, experienceMax, validBiomes.stream().map(Biome::getBiomeName).toArray(String[]::new), dropsArray);
        }
    }
    
    @Override
    public void addMobRenderHooks(IMobRegistry mobRegistry)
    {
        mobRegistry.registerRenderHook(MoCEntityWerewolf.class, RENDER_HOOK_GENERIC_LOWER);
        mobRegistry.registerRenderHook(MoCEntityWraith.class, RENDER_HOOK_GENERIC_LOWER);
        mobRegistry.registerRenderHook(MoCEntityOgre.class, RENDER_HOOK_GENERIC_LOWER);
        mobRegistry.registerRenderHook(MoCEntitySilverSkeleton.class, RENDER_HOOK_GENERIC_LOWER);
        
        mobRegistry.registerRenderHook(MoCEntityCrocodile.class, RENDER_HOOK_GENERIC_HIGHER);
        mobRegistry.registerRenderHook(MoCEntityBird.class, RENDER_HOOK_GENERIC_HIGHER);
        mobRegistry.registerRenderHook(MoCEntityPetScorpion.class, RENDER_HOOK_GENERIC_HIGHER);
        mobRegistry.registerRenderHook(MoCEntityCrab.class, RENDER_HOOK_GENERIC_HIGHER);
        mobRegistry.registerRenderHook(MoCEntityKomodo.class, RENDER_HOOK_GENERIC_HIGHER);
    }
    
    @Override
    public ModIds getModId()
    {
        return ModIds.MO_CREATURES;
    }
    
    @SuppressWarnings("rawtypes")
    private static class GenericVariantSetter implements MobTableBuilder.EntityPropertySetter
    {
        private final int variant;
        
        GenericVariantSetter(int variant)
        {
            this.variant = variant;
        }
        
        @Override
        public void setProperties(EntityLivingBase entity)
        {
            if (entity instanceof IMoCEntity)
                ((IMoCEntity) entity).setType(variant);
        }
    }
    
    private static class WerewolfSetter implements MobTableBuilder.EntityPropertySetter<MoCEntityWerewolf>
    {
        private final boolean human;
        
        WerewolfSetter(boolean human)
        {
            this.human = human;
        }
        
        @Override
        public void setProperties(MoCEntityWerewolf entity)
        {
            entity.setHumanForm(human);
        }
    }
    
    @SuppressWarnings("rawtypes")
    private static final IMobRenderHook RENDER_HOOK_GENERIC_HIGHER = ((renderInfo, entityLivingBase) ->
    {
        GlStateManager.translate(0f, 0.5f, 0f);
        return renderInfo;
    });
    
    @SuppressWarnings("rawtypes")
    private static final IMobRenderHook RENDER_HOOK_GENERIC_LOWER = ((renderInfo, entityLivingBase) ->
    {
        GlStateManager.translate(0f, -0.5f, 0f);
        return renderInfo;
    });
}
