package com.github.exploder1531.mia.integrations.mocreatures;

import com.github.exploder1531.mia.integrations.ModIds;
import com.github.exploder1531.mia.integrations.jer.ExtraConditional;
import com.github.exploder1531.mia.integrations.jer.IJerIntegration;
import com.google.common.collect.Sets;
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
import jeresources.api.conditionals.ExtendedConditional;
import jeresources.api.conditionals.LightLevel;
import jeresources.api.drop.LootDrop;
import jeresources.api.render.IMobRenderHook;
import jeresources.util.LootTableHelper;
import jeresources.util.MobTableBuilder;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.storage.loot.LootTable;
import net.minecraft.world.storage.loot.LootTableManager;
import net.minecraftforge.common.BiomeDictionary;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Set;

import static com.github.exploder1531.mia.integrations.jer.JustEnoughResources.loadResource;

public class JerMoCreaturesIntegration implements IJerIntegration
{
    @SuppressWarnings("unchecked")
    @Override
    public Set<Class> addMobs(MobTableBuilder builder, Set<Class> ignoreMobOverrides)
    {
        // Horses
        for (int i = 21; i <= 26; i++)
            builder.add(loadResource("mocreatures/horse/horse_" + i), MoCEntityHorse.class, new GenericVariantSetter(i));
        for (int i = 38; i <= 40; i++)
            builder.add(loadResource("mocreatures/horse/horse_" + i), MoCEntityHorse.class, new GenericVariantSetter(i));
        builder.add(loadResource("mocreatures/horse/horse_32"), MoCEntityHorse.class, new GenericVariantSetter(32));
        builder.add(loadResource("mocreatures/horse/horse_36"), MoCEntityHorse.class, new GenericVariantSetter(36));
        // Passive entities
        builder.add(loadResource("mocreatures/passive/bird"), MoCEntityBird.class);
        builder.add(loadResource("mocreatures/passive/boar"), MoCEntityBoar.class);
        builder.add(loadResource("mocreatures/passive/crocodile"), MoCEntityCrocodile.class);
        builder.add(loadResource("mocreatures/passive/deer"), MoCEntityDeer.class);
        builder.add(loadResource("mocreatures/passive/duck"), MoCEntityDuck.class);
        builder.add(loadResource("mocreatures/passive/ent"), MoCEntityEnt.class);
        builder.add(loadResource("mocreatures/passive/fox"), MoCEntityFox.class);
        builder.add(loadResource("mocreatures/passive/goat"), MoCEntityGoat.class);
        builder.add(loadResource("mocreatures/passive/komodo"), MoCEntityKomodo.class);
        builder.add(loadResource("mocreatures/passive/mole"), MoCEntityMole.class);
        builder.add(loadResource("mocreatures/passive/mouse"), MoCEntityMouse.class);
        builder.add(loadResource("mocreatures/passive/raccoon"), MoCEntityRaccoon.class);
//        builder.add(loadResource("mocreatures/passive/turtle"), MoCEntityTurtle.class);
        // Bears
        builder.add(loadResource("mocreatures/passive/bear/black"), MoCEntityBlackBear.class);
        builder.add(loadResource("mocreatures/passive/bear/grizzly"), MoCEntityGrizzlyBear.class);
        builder.add(loadResource("mocreatures/passive/bear/panda"), MoCEntityPandaBear.class);
        builder.add(loadResource("mocreatures/passive/bear/polar"), MoCEntityPolarBear.class);
        // Big cats
        builder.add(loadResource("mocreatures/passive/big_cat/leopard"), MoCEntityLeopard.class);
        builder.add(loadResource("mocreatures/passive/big_cat/lion"), MoCEntityLion.class);
        builder.add(loadResource("mocreatures/passive/big_cat/panther"), MoCEntityPanther.class);
        builder.add(loadResource("mocreatures/passive/big_cat/tiger"), MoCEntityTiger.class);
        // Ostrich
        for (int i = 4; i <= 8; i++)
            builder.add(loadResource("mocreatures/passive/ostrich/ostrich_" + i), MoCEntityOstrich.class, new GenericVariantSetter(i));
        // Pet scorpion
        for (int i = 1; i <= 5; i++)
            builder.add(loadResource("mocreatures/passive/scorpion/scorpion_" + i), MoCEntityPetScorpion.class, new GenericVariantSetter(i));
        // Snake, currently disabled due to rendering issues
//        for (int i = 1; i <= 5; i++)
//            builder.add(loadResource("mocreatures/passive/snake/snake_" + i), MoCEntitySnake.class, new GenericVariantSetter(i));
        
        
        // Hostile entities
        // Ogres
        builder.add(loadResource("mocreatures/hostile/ogre_cave"), MoCEntityCaveOgre.class);
        builder.add(loadResource("mocreatures/hostile/ogre_fire"), MoCEntityFireOgre.class);
        builder.add(loadResource("mocreatures/hostile/ogre_green"), MoCEntityGreenOgre.class);
        // Wraiths
        builder.add(loadResource("mocreatures/hostile/wraith"), MoCEntityWraith.class);
        builder.add(loadResource("mocreatures/hostile/wraith_flame"), MoCEntityFlameWraith.class);
        // Manticores
        for (int i = 1; i <= 4; i++)
            builder.add(loadResource("mocreatures/hostile/manticore/manticore_" + i), MoCEntityManticore.class, new GenericVariantSetter(i));
        // Rats
        builder.add(loadResource("mocreatures/hostile/rat"), MoCEntityRat.class);
        builder.add(loadResource("mocreatures/hostile/rat_hell"), MoCEntityHellRat.class);
        // Scorpions
        for (int i = 1; i <= 4; i++)
            builder.add(loadResource("mocreatures/hostile/scorpion/scorpion_" + i), MoCEntityScorpion.class, new GenericVariantSetter(i));
        // Silver skeletons
        builder.add(loadResource("mocreatures/hostile/skeleton_silver"), MoCEntitySilverSkeleton.class);
        // Werewolf
        builder.add(loadResource("mocreatures/hostile/werewolf_human"), MoCEntityWerewolf.class, new WerewolfSetter(true));
        builder.add(loadResource("mocreatures/hostile/werewolf_wild"), MoCEntityWerewolf.class, new WerewolfSetter(false));
        // Wild wolf
        builder.add(loadResource("mocreatures/hostile/wolf_wild"), MoCEntityWWolf.class);
        
        
        // Ambient
        builder.add(loadResource("mocreatures/ambient/crab"), MoCEntityCrab.class);
        builder.add(loadResource("mocreatures/ambient/snail"), MoCEntitySnail.class);
        builder.add(loadResource("mocreatures/ambient/maggot"), MoCEntityMaggot.class);
        
        
        // Aquatic
        builder.add(loadResource("mocreatures/aquatic/dolphin"), MoCEntityDolphin.class);
        builder.add(loadResource("mocreatures/aquatic/jellyfish"), MoCEntityJellyFish.class);
        builder.add(loadResource("mocreatures/aquatic/shark"), MoCEntityShark.class);
        // Medium fish
//        builder.add(loadResource("mocreatures/aquatic/fish_medium/bass"), MoCEntityBass.class);
//        builder.add(loadResource("mocreatures/aquatic/fish_medium/cod"), MoCEntityCod.class);
//        builder.add(loadResource("mocreatures/aquatic/fish_medium/salmon"), MoCEntitySalmon.class);
        // Small fish
        builder.add(loadResource("mocreatures/aquatic/fish_small/anchovy"), MoCEntityAnchovy.class);
        builder.add(loadResource("mocreatures/aquatic/fish_small/angel_fish"), MoCEntityAngelFish.class);
        builder.add(loadResource("mocreatures/aquatic/fish_small/angler"), MoCEntityAngler.class);
        builder.add(loadResource("mocreatures/aquatic/fish_small/clownfish"), MoCEntityClownFish.class);
        builder.add(loadResource("mocreatures/aquatic/fish_small/goldfish"), MoCEntityGoldFish.class);
        builder.add(loadResource("mocreatures/aquatic/fish_small/hippo_tang"), MoCEntityHippoTang.class);
        builder.add(loadResource("mocreatures/aquatic/fish_small/manderin"), MoCEntityManderin.class);
        builder.add(loadResource("mocreatures/aquatic/fish_small/piranha"), MoCEntityPiranha.class);
        
        
        return Sets.newHashSet(
                // Horses
                MoCEntityHorse.class,
                // Passive entities
                MoCEntityBird.class,
                MoCEntityBoar.class,
                MoCEntityCrocodile.class,
                MoCEntityDeer.class,
                MoCEntityDuck.class,
                MoCEntityEnt.class,
                MoCEntityFox.class,
                MoCEntityGoat.class,
                MoCEntityKomodo.class,
                MoCEntityMole.class,
                MoCEntityMouse.class,
                MoCEntityRaccoon.class,
//                MoCEntityTurtle.class,
                MoCEntityOstrich.class,
                MoCEntityPetScorpion.class,
//                MoCEntitySnake.class,
                // Bears
                MoCEntityBlackBear.class,
                MoCEntityGrizzlyBear.class,
                MoCEntityPandaBear.class,
                MoCEntityPolarBear.class,
                // Big cats
                MoCEntityLeopard.class,
                MoCEntityLion.class,
                MoCEntityPanther.class,
                MoCEntityTiger.class,
                // Hostile entities
                // Ogres
                MoCEntityCaveOgre.class,
                MoCEntityFireOgre.class,
                MoCEntityGreenOgre.class,
                // Wraiths
                MoCEntityWraith.class,
                MoCEntityFlameWraith.class,
                // Manticores
                MoCEntityManticore.class,
                // Hell rat
                MoCEntityRat.class,
                MoCEntityHellRat.class,
                // Scorpions
                MoCEntityScorpion.class,
                // Silver skeleton
                MoCEntitySilverSkeleton.class,
                // Werewolf
                MoCEntityWerewolf.class,
                // Wild wolf
                MoCEntityWWolf.class,
                
                // Ambient
                MoCEntityCrab.class,
                MoCEntitySnail.class,
                MoCEntityMaggot.class,
                
                // Aquatic
                MoCEntityDolphin.class,
                MoCEntityJellyFish.class,
                MoCEntityShark.class,
                // Medium fish
//                MoCEntityBass.class,
//                MoCEntityCod.class,
//                MoCEntitySalmon.class,
                // Small fish
                MoCEntityAnchovy.class,
                MoCEntityAngelFish.class,
                MoCEntityAngler.class,
                MoCEntityClownFish.class,
                MoCEntityGoldFish.class,
                MoCEntityHippoTang.class,
                MoCEntityManderin.class,
                MoCEntityPiranha.class
        );
    }
    
    @Override
    public void configureMob(ResourceLocation resource, EntityLivingBase entity, LootTableManager manager, IMobRegistry mobRegistry)
    {
        LootTable loot = manager.getLootTableFromLocation(resource);
        Set<Biome> validBiomes = Sets.newHashSet();
        List<LootDrop> drops = LootTableHelper.toDrops(loot);
        
        LightLevel lightLevel = LightLevel.any;
        int experienceMin = 5;
        int experienceMax = 5;
        
        if (entity instanceof EntityAnimal)
        {
            experienceMin = 1;
            experienceMax = 3;
        }
        
        drops.forEach(lootDrop ->
        {
            Item item = lootDrop.item.getItem();
            Block block = Block.getBlockFromItem(item);
            
            if (item == MoCItems.bo && entity instanceof MoCEntityTurtle)
                lootDrop.addConditional(new ExtendedConditional(ExtraConditional.named, "'Donatello', 'donatello'"));
            else if (item == MoCItems.katana && entity instanceof MoCEntityTurtle)
                lootDrop.addConditional(new ExtendedConditional(ExtraConditional.named, "'Leonardo', 'leonardo'"));
            else if (item == MoCItems.sai && entity instanceof MoCEntityTurtle)
                lootDrop.addConditional(new ExtendedConditional(ExtraConditional.named, "'Rafael', 'rafael', 'raphael', 'Raphael'"));
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
        
        LootDrop[] dropsArray = drops.toArray(new LootDrop[0]);
        
        if (validBiomes.isEmpty())
            mobRegistry.register(entity, lightLevel, experienceMin, experienceMax, dropsArray);
        else
            mobRegistry.register(entity, lightLevel, experienceMin, experienceMax, validBiomes.stream().map(Biome::getBiomeName).toArray(String[]::new), dropsArray);
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
    
    @Nonnull
    @Override
    public String getModId()
    {
        return ModIds.MO_CREATURES;
    }
    
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
    
    private static final IMobRenderHook RENDER_HOOK_GENERIC_HIGHER = ((renderInfo, entityLivingBase) ->
    {
        GlStateManager.translate(0f, 0.5f, 0f);
        return renderInfo;
    });
    
    private static final IMobRenderHook RENDER_HOOK_GENERIC_LOWER = ((renderInfo, entityLivingBase) ->
    {
        GlStateManager.translate(0f, -0.5f, 0f);
        return renderInfo;
    });
}
