package com.github.sokyranthedragon.mia.integrations.mocreatures;

import com.github.sokyranthedragon.mia.config.MiaConfig;
import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.integrations.jer.ExtraConditional;
import com.github.sokyranthedragon.mia.integrations.jer.IJerIntegration;
import com.github.sokyranthedragon.mia.integrations.jer.JustEnoughResources;
import com.github.sokyranthedragon.mia.utilities.LootTableUtils;
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
import jeresources.util.MobTableBuilder;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.github.sokyranthedragon.mia.config.MoCreaturesConfiguration.replaceFishDrops;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
class JerMoCreaturesIntegration implements IJerIntegration
{
    @Override
    public void addMobs(JustEnoughResources.CustomMobTableBuilder builder)
    {
        // Horses
        for (int i = 21; i <= 26; i++)
            builder.add(LootTableUtils.loadUniqueEmptyLootTable(), MoCEntityHorse.class, new GenericVariantSetter<>(i));
        for (int i = 38; i <= 40; i++)
            builder.add(LootTableUtils.loadUniqueEmptyLootTable(), MoCEntityHorse.class, new GenericVariantSetter<>(i));
        builder.add(LootTableUtils.loadUniqueEmptyLootTable(), MoCEntityHorse.class, new GenericVariantSetter<>(32));
        builder.add(LootTableUtils.loadUniqueEmptyLootTable(), MoCEntityHorse.class, new GenericVariantSetter<>(36));
        // Passive entities
        builder.add(LootTableUtils.loadUniqueEmptyLootTable(), MoCEntityBird.class);
        builder.add(LootTableUtils.loadUniqueEmptyLootTable(), MoCEntityBoar.class);
        builder.add(LootTableUtils.loadUniqueEmptyLootTable(), MoCEntityCrocodile.class);
        builder.add(LootTableUtils.loadUniqueEmptyLootTable(), MoCEntityDeer.class);
        builder.add(LootTableUtils.loadUniqueEmptyLootTable(), MoCEntityDuck.class);
        for (int i = 0; i <= 2; i++)
            builder.add(LootTableUtils.loadUniqueEmptyLootTable(), MoCEntityEnt.class, new GenericVariantSetter<>(i));
        builder.add(LootTableUtils.loadUniqueEmptyLootTable(), MoCEntityFox.class);
        builder.add(LootTableUtils.loadUniqueEmptyLootTable(), MoCEntityGoat.class);
        builder.add(LootTableUtils.loadUniqueEmptyLootTable(), MoCEntityKomodo.class);
        builder.add(LootTableUtils.loadUniqueEmptyLootTable(), MoCEntityMole.class);
        builder.add(LootTableUtils.loadUniqueEmptyLootTable(), MoCEntityMouse.class);
        builder.add(LootTableUtils.loadUniqueEmptyLootTable(), MoCEntityRaccoon.class);
        builder.add(LootTableUtils.loadUniqueEmptyLootTable(), MoCEntityTurkey.class);
        builder.add(LootTableUtils.loadUniqueEmptyLootTable(), MoCEntityTurtle.class);
        // Bears
        builder.add(LootTableUtils.loadUniqueEmptyLootTable(), MoCEntityBlackBear.class);
        builder.add(LootTableUtils.loadUniqueEmptyLootTable(), MoCEntityGrizzlyBear.class);
        builder.add(LootTableUtils.loadUniqueEmptyLootTable(), MoCEntityPandaBear.class);
        builder.add(LootTableUtils.loadUniqueEmptyLootTable(), MoCEntityPolarBear.class);
        // Big cats
        builder.add(LootTableUtils.loadUniqueEmptyLootTable(), MoCEntityLeopard.class);
        builder.add(LootTableUtils.loadUniqueEmptyLootTable(), MoCEntityLion.class);
        builder.add(LootTableUtils.loadUniqueEmptyLootTable(), MoCEntityPanther.class);
        builder.add(LootTableUtils.loadUniqueEmptyLootTable(), MoCEntityTiger.class);
        // Ostrich
        for (int i = 4; i <= 8; i++)
            builder.add(LootTableUtils.loadUniqueEmptyLootTable(), MoCEntityOstrich.class, new GenericVariantSetter<>(i));
        // Pet scorpion
        for (int i = 1; i <= 5; i++)
            builder.add(LootTableUtils.loadUniqueEmptyLootTable(), MoCEntityPetScorpion.class, new GenericVariantSetter<>(i));
        // Snake
        for (int i = 1; i <= 5; i++)
            builder.add(LootTableUtils.loadUniqueEmptyLootTable(), MoCEntitySnake.class, new GenericVariantSetter<>(i));
        for (int i = 1; i <= 5; i++)
            builder.add(LootTableUtils.loadUniqueEmptyLootTable(), MoCEntityWyvern.class, new GenericVariantSetter<>(i));


        // Hostile entities
        // Ogres
        builder.add(LootTableUtils.loadUniqueEmptyLootTable(), MoCEntityCaveOgre.class);
        builder.add(LootTableUtils.loadUniqueEmptyLootTable(), MoCEntityFireOgre.class);
        builder.add(LootTableUtils.loadUniqueEmptyLootTable(), MoCEntityGreenOgre.class);
        // Wraiths
        builder.add(LootTableUtils.loadUniqueEmptyLootTable(), MoCEntityWraith.class);
        builder.add(LootTableUtils.loadUniqueEmptyLootTable(), MoCEntityFlameWraith.class);
        // Manticores
        for (int i = 1; i <= 4; i++)
            builder.add(LootTableUtils.loadUniqueEmptyLootTable(), MoCEntityManticore.class, new GenericVariantSetter<>(i));
        // Rats
        builder.add(LootTableUtils.loadUniqueEmptyLootTable(), MoCEntityRat.class);
        builder.add(LootTableUtils.loadUniqueEmptyLootTable(), MoCEntityHellRat.class);
        // Scorpions
        for (int i = 1; i <= 4; i++)
            builder.add(LootTableUtils.loadUniqueEmptyLootTable(), MoCEntityScorpion.class, new GenericVariantSetter<>(i));
        // Silver skeletons
        builder.add(LootTableUtils.loadUniqueEmptyLootTable(), MoCEntitySilverSkeleton.class);
        // Werewolf
        builder.add(LootTableUtils.loadUniqueEmptyLootTable(), MoCEntityWerewolf.class, new WerewolfSetter(true));
        builder.add(LootTableUtils.loadUniqueEmptyLootTable(), MoCEntityWerewolf.class, new WerewolfSetter(false));
        // Wild wolf
        builder.add(LootTableUtils.loadUniqueEmptyLootTable(), MoCEntityWWolf.class);


        // Ambient
        builder.add(LootTableUtils.loadUniqueEmptyLootTable(), MoCEntityCrab.class);
        builder.add(LootTableUtils.loadUniqueEmptyLootTable(), MoCEntitySnail.class);
        builder.add(LootTableUtils.loadUniqueEmptyLootTable(), MoCEntityMaggot.class);


        // Aquatic
        builder.add(LootTableUtils.loadUniqueEmptyLootTable(), MoCEntityDolphin.class);
        builder.add(LootTableUtils.loadUniqueEmptyLootTable(), MoCEntityJellyFish.class);
        builder.add(LootTableUtils.loadUniqueEmptyLootTable(), MoCEntityShark.class);
        // Medium fish
        builder.add(LootTableUtils.loadUniqueEmptyLootTable(), MoCEntityBass.class);
        builder.add(LootTableUtils.loadUniqueEmptyLootTable(), MoCEntityCod.class);
        builder.add(LootTableUtils.loadUniqueEmptyLootTable(), MoCEntitySalmon.class);
        // Small fish
        builder.add(LootTableUtils.loadUniqueEmptyLootTable(), MoCEntityAnchovy.class);
        builder.add(LootTableUtils.loadUniqueEmptyLootTable(), MoCEntityAngelFish.class);
        builder.add(LootTableUtils.loadUniqueEmptyLootTable(), MoCEntityAngler.class);
        builder.add(LootTableUtils.loadUniqueEmptyLootTable(), MoCEntityClownFish.class);
        builder.add(LootTableUtils.loadUniqueEmptyLootTable(), MoCEntityGoldFish.class);
        builder.add(LootTableUtils.loadUniqueEmptyLootTable(), MoCEntityHippoTang.class);
        builder.add(LootTableUtils.loadUniqueEmptyLootTable(), MoCEntityManderin.class);
        builder.add(LootTableUtils.loadUniqueEmptyLootTable(), MoCEntityPiranha.class);
    }

    @Override
    public void configureMob(ResourceLocation resource, EntityLivingBase entity, @Nullable LootTableManager manager, IMobRegistry mobRegistry)
    {
        Set<Biome> validBiomes = new HashSet<>();
        List<LootDrop> drops = new ArrayList<>();

        LightLevel lightLevel = LightLevel.any;
        int experienceMin = 5;
        int experienceMax = 5;

        if (entity instanceof EntityAnimal || entity instanceof MoCEntityAquatic)
        {
            experienceMin = 1;
            experienceMax = 3;
        }

        // Horses
        if (entity instanceof MoCEntityHorse)
        {
            boolean alwaysRare = MoCreatures.proxy.rareItemDropChance >= 100;
            boolean neverRare = MoCreatures.proxy.rareItemDropChance <= 0;
            float chance = MoCreatures.proxy.rareItemDropChance / 100f;
            Item normal;
            Item rare = null;

            switch (((MoCEntityHorse)entity).getType())
            {
                case 21:
                case 22:
                    normal = Items.GHAST_TEAR;
                    break;
                case 23:
                case 24:
                case 25:
                    rare = MoCItems.heartundead;
                    normal = Items.ROTTEN_FLESH;
                    break;
                case 26:
                    normal = Items.BONE;
                    break;
                case 32:
                    rare = MoCItems.heartdarkness;
                    normal = Items.LEATHER;
                    break;
                case 38:
                    rare = MoCItems.heartfire;
                    normal = Items.LEATHER;
                    break;
                case 39:
                case 40:
                    normal = Items.FEATHER;
                    break;
                case 36:
                case 50:
                case 51:
                case 52:
                case 53:
                case 54:
                case 55:
                case 56:
                case 57:
                case 58:
                case 59:
                    rare = MoCItems.unicornhorn;
                    normal = Items.LEATHER;
                    break;
                default:
                    normal = Items.LEATHER;
                    break;
            }

            if (rare != null && (!neverRare || normal == null))
            {
                if (normal == null)
                    chance = 1f;

                drops.add(new LootDrop(rare, 0, 2, chance, Conditional.affectedByLooting));
            }
            if (normal != null && (!alwaysRare || rare == null))
            {
                if (rare == null)
                    chance = 0f;

                drops.add(new LootDrop(normal, 0, 2, 1f - chance, Conditional.affectedByLooting));
            }
        }

        // Passive
        else if (entity instanceof MoCEntityBear)
            drops.add(new LootDrop(MoCItems.animalHide, 0, 2, 1f, Conditional.affectedByLooting));
        else if (entity instanceof MoCEntityBigCat)
            drops.add(new LootDrop(MoCItems.bigcatclaw, 0, 2, 1f, Conditional.affectedByLooting));
        else if (entity instanceof MoCEntityOstrich)
        {
            boolean alwaysRare = MoCreatures.proxy.rareItemDropChance >= 100;
            boolean neverRare = MoCreatures.proxy.rareItemDropChance <= 0;
            float chance = MoCreatures.proxy.rareItemDropChance / 100f;
            Item meatItem = MoCItems.ostrichraw;
            Item rareItem = null;

            switch (((MoCEntityOstrich)entity).getType())
            {
                case 5:
                    rareItem = MoCItems.heartfire;
                    break;
                case 6:
                    rareItem = MoCItems.heartdarkness;
                    break;
                case 7:
                    rareItem = MoCItems.heartundead;
                    meatItem = Items.ROTTEN_FLESH;
                    break;
                case 8:
                    rareItem = MoCItems.unicornhorn;
                    break;
                default:
                    chance = 0f;
                    break;
            }

            if (!neverRare && rareItem != null)
                drops.add(new LootDrop(rareItem, 0, 2, chance, Conditional.affectedByLooting));
            if (!alwaysRare || rareItem == null)
            {
                LootDrop drop = new LootDrop(MoCItems.ostrichraw, 0, 2, 1f - chance, Conditional.affectedByLooting);
                drops.add(drop);
                if (MiaConfig.addCookedDrops && meatItem == MoCItems.ostrichraw)
                    drop.smeltedItem = new ItemStack(MoCItems.ostrichcooked);
            }
        }
        else if (entity instanceof MoCEntitySnake)
            drops.add(new LootDrop(MoCItems.mocegg, ((MoCEntitySnake)entity).getType() + 20, 0, 2, 1f, ExtraConditional.isAdult));
        else if (entity instanceof MoCEntityBird)
            drops.add(new LootDrop(Items.FEATHER, 0, 2, 1f, Conditional.affectedByLooting));
        else if (entity instanceof MoCEntityDuck)
        {
            drops.add(new LootDrop(Items.FEATHER, 0, 2, 1f, Conditional.affectedByLooting));

            if (ModIds.HARVESTCRAFT.isLoaded)
            {
                LootDrop meat = new LootDrop(new ItemStack(ItemRegistry.duckrawItem), 0, 2, Conditional.affectedByLooting);
                drops.add(meat);
                if (MiaConfig.addCookedDrops)
                    meat.smeltedItem = new ItemStack(ItemRegistry.duckcookedItem);
            }
        }
        else if (entity instanceof MoCEntityBoar)
        {
            LootDrop meat = new LootDrop(Items.PORKCHOP, 0, 2, 0.5f, Conditional.affectedByLooting);
            drops.add(meat);
            drops.add(new LootDrop(MoCItems.animalHide, 0, 2, 0.5f, Conditional.affectedByLooting));
            if (MiaConfig.addCookedDrops)
                meat.smeltedItem = new ItemStack(Items.COOKED_PORKCHOP);
        }
        else if (entity instanceof MoCEntityCrocodile)
            drops.add(new LootDrop(MoCItems.hideCroc, 0, 2, 1f, Conditional.affectedByLooting));
        else if (entity instanceof MoCEntityEnt)
        {
            int type = ((MoCEntityEnt)entity).getType();
            drops.add(new LootDrop(new ItemStack(Blocks.LOG, 1, type), 11, 15, 1f / 3f));
            drops.add(new LootDrop(Items.STICK, 11, 15, 1f / 3f));
            drops.add(new LootDrop(new ItemStack(Blocks.SAPLING, 1, type), 11, 15, 1f / 3f));
        }
        else if (entity instanceof MoCEntityGoat)
            drops.add(new LootDrop(Items.LEATHER, 0, 2, 1f, Conditional.affectedByLooting));
        else if (entity instanceof MoCEntityKomodo)
        {
            drops.add(new LootDrop(MoCItems.hideCroc, 1, 1, 0.8f));
            drops.add(new LootDrop(MoCItems.mocegg, 33, 1, 2, 0.2f, ExtraConditional.isAdult));
        }
        else if (entity instanceof MoCEntityMouse)
            drops.add(new LootDrop(Items.WHEAT_SEEDS, 0, 2, 1f, Conditional.affectedByLooting));
        else if (entity instanceof MoCEntityTurkey)
        {
            LootDrop meat = new LootDrop(MoCItems.rawTurkey, 0, 2, 0.5f, Conditional.affectedByLooting);
            drops.add(meat);
            drops.add(new LootDrop(Items.FEATHER, 0, 2, 0.5f, Conditional.affectedByLooting));
            if (MiaConfig.addCookedDrops)
                meat.smeltedItem = new ItemStack(MoCItems.cookedTurkey);
        }
        else if (entity instanceof MoCEntityTurtle)
        {
            LootDrop meat = new LootDrop(MoCItems.turtleraw, 0, 2, 1f, Conditional.affectedByLooting);

            drops.add(meat);
            drops.add(new LootDrop(MoCItems.bo, 0, 2, 1f, Conditional.affectedByLooting, new ExtendedConditional(ExtraConditional.named, "'Donatello', 'donatello'")));
            drops.add(new LootDrop(MoCItems.katana, 0, 2, 1f, Conditional.affectedByLooting, new ExtendedConditional(ExtraConditional.named, "'Leonardo', 'leonardo'")));
            drops.add(new LootDrop(MoCItems.sai, 0, 2, 1f, Conditional.affectedByLooting, new ExtendedConditional(ExtraConditional.named, "'Rafael', 'rafael', 'Raphael', 'raphael'")));
            drops.add(new LootDrop(MoCItems.nunchaku, 0, 2, 1f, Conditional.affectedByLooting, new ExtendedConditional(ExtraConditional.named, "'Michelangelo', 'michelangelo', 'Michaelangelo', 'michaelangelo'")));

            if (ModIds.HARVESTCRAFT.isLoaded)
                meat.smeltedItem = new ItemStack(ItemRegistry.turtlecookedItem);
        }
        else if (entity instanceof MoCEntityWyvern)
        {
            int type = ((MoCEntityWyvern) entity).getType();
            float chance = type == 5 ? MoCreatures.proxy.motherWyvernEggDropChance : MoCreatures.proxy.rareItemDropChance;
            drops.add(new LootDrop(MoCItems.mocegg, type + 49, 1, 1, chance));
        }


        // Passive and hostile
        else if (entity instanceof MoCEntityFox || entity instanceof MoCEntityMole || entity instanceof MoCEntityRaccoon || entity instanceof MoCEntityWWolf)
            drops.add(new LootDrop(MoCItems.fur, 0, 2, 1f, Conditional.affectedByLooting));
        else if (entity instanceof MoCEntityDeer)
        {
            drops.add(new LootDrop(MoCItems.fur, 0, 2, 1f, Conditional.affectedByLooting));
            if (ModIds.HARVESTCRAFT.isLoaded)
            {
                LootDrop meat = new LootDrop(new ItemStack(ItemRegistry.venisonrawItem), 0, 2, Conditional.affectedByLooting);
                drops.add(meat);
                if (MiaConfig.addCookedDrops)
                    meat.smeltedItem = new ItemStack(ItemRegistry.venisoncookedItem);
            }
        }
        else if (entity instanceof MoCEntityScorpion || entity instanceof MoCEntityPetScorpion)
        {
            boolean alwaysRare = MoCreatures.proxy.rareItemDropChance >= 100;
            boolean neverRare = MoCreatures.proxy.rareItemDropChance <= 0;
            float chance = MoCreatures.proxy.rareItemDropChance / 100f;
            Item sting;
            Item chitin;

            switch (((IMoCEntity)entity).getType())
            {
                case 1:
                    sting = MoCItems.scorpStingDirt;
                    chitin = MoCItems.chitin;
                    break;
                case 2:
                    sting = MoCItems.scorpStingCave;
                    chitin = MoCItems.chitinCave;
                    break;
                case 3:
                    sting = MoCItems.scorpStingNether;
                    chitin = MoCItems.chitinNether;
                    break;
                case 4:
                    sting = MoCItems.scorpStingFrost;
                    chitin = MoCItems.chitinFrost;
                    break;
                case 5:
                    sting = Items.ROTTEN_FLESH;
                    chitin = null;
                    break;
                default:
                    sting = null;
                    chitin = null;
                    break;
            }

            drops.add(new LootDrop(Items.STRING, 0, 2, 1f, Conditional.affectedByLooting, ExtraConditional.isNotAdult));

            if (!neverRare && sting != null)
            {
                if (chitin == null)
                    chance = 1f;
                drops.add(new LootDrop(sting, 0, 2, chance, Conditional.affectedByLooting));
            }
            if (!alwaysRare && chitin != null)
            {
                if (sting == null)
                    chance = 0f;
                drops.add(new LootDrop(chitin, 0, 2, 1f - chance, Conditional.affectedByLooting));
            }
        }


        // Hostile
        else if (entity instanceof MoCEntityManticore)
        {
            int type = ((MoCEntityManticore)entity).getType();
            boolean alwaysRare = MoCreatures.proxy.rareItemDropChance >= 100;
            boolean neverRare = MoCreatures.proxy.rareItemDropChance <= 0;
            float chanceEgg = MoCreatures.proxy.rareItemDropChance / 100f;

            if (!neverRare)
                drops.add(new LootDrop(MoCItems.mocegg, type + 61, 1, 1, chanceEgg));
            if (!alwaysRare)
            {
                float chanceAfterEgg = 1f - chanceEgg;
                float chanceSting = chanceAfterEgg * chanceEgg;
                Item sting;
                Item chitin;

                switch (type)
                {
                    case 1:
                        sting = MoCItems.scorpStingNether;
                        chitin = MoCItems.chitinNether;
                        break;
                    case 2:
                        sting = MoCItems.scorpStingCave;
                        chitin = MoCItems.chitinCave;
                        break;
                    case 3:
                        sting = MoCItems.scorpStingFrost;
                        chitin = MoCItems.chitinFrost;
                        break;
                    case 4:
                        sting = MoCItems.scorpStingDirt;
                        chitin = MoCItems.chitin;
                        break;
                    default:
                        sting = null;
                        chitin = MoCItems.chitin;
                        break;
                }

                if (!neverRare && sting != null)
                    drops.add(new LootDrop(sting, 0, 2, chanceSting, Conditional.affectedByLooting));
                // alwaysRare must be false already, no need to check
                float chanceChitin = sting == null ? chanceAfterEgg : chanceAfterEgg - chanceSting;
                drops.add(new LootDrop(chitin, 0, 2, chanceChitin, Conditional.affectedByLooting));
            }
        }
        else if (entity instanceof MoCEntityCaveOgre)
            drops.add(new LootDrop(Items.DIAMOND, 0, 2, 1f, Conditional.affectedByLooting));
        else if (entity instanceof MoCEntityFireOgre)
        {
            // The ogre should have a common drop of fire (as in, actual fire block), but seems that it's no longer obtainable in 1.12 so it's a leftover form older versions
            // So the lower the rare item drop chance, the higher the redstone drop chance
            if (MoCreatures.proxy.rareItemDropChance > 0)
                drops.add(new LootDrop(MoCItems.heartfire, 0, 2, MoCreatures.proxy.rareItemDropChance / 100f, Conditional.affectedByLooting));
        }
        else if (entity instanceof MoCEntityGreenOgre)
            drops.add(new LootDrop(new ItemStack(Blocks.OBSIDIAN), 0, 2, 1f, Conditional.affectedByLooting));
        else if (entity instanceof MoCEntityHellRat)
        {
            // The rat should have a rare drop of fire (as in, actual fire block), but seems that it's no longer obtainable in 1.12 so it's a leftover form older versions
            // So the higher the rare item drop chance, the lower the redstone drop chance
            if (MoCreatures.proxy.rareItemDropChance < 100)
                drops.add(new LootDrop(Items.REDSTONE, 0, 2, 1 - (MoCreatures.proxy.rareItemDropChance / 100f), Conditional.affectedByLooting));
        }
        else if (entity instanceof MoCEntityRat)
        {
            LootDrop meat = new LootDrop(MoCItems.ratRaw, 0, 2, 1f, Conditional.affectedByLooting);
            drops.add(meat);
            if (MiaConfig.addCookedDrops)
                meat.smeltedItem = new ItemStack(MoCItems.ratCooked);
        }
        else if (entity instanceof MoCEntitySilverSkeleton)
        {
            drops.add(new LootDrop(Items.BONE, 0, 2, 0.9f, Conditional.affectedByLooting));
            drops.add(new LootDrop(MoCItems.silversword, 0, 2, 0.1f, Conditional.affectedByLooting));
        }
        else if (entity instanceof MoCEntityWerewolf)
        {
            float chance = 1f / 12f; // range of 0-11 gives us 12 possible outcomes, each item (besides the final one) has 1 in 12 chance of being picked

            if (((MoCEntityWerewolf) entity).getIsHumanForm())
            {
                drops.add(new LootDrop(Items.WOODEN_SHOVEL, 0, 2, chance, Conditional.affectedByLooting));
                drops.add(new LootDrop(Items.WOODEN_AXE, 0, 2, chance, Conditional.affectedByLooting));
                drops.add(new LootDrop(Items.WOODEN_SWORD, 0, 2, chance, Conditional.affectedByLooting));
                drops.add(new LootDrop(Items.WOODEN_HOE, 0, 2, chance, Conditional.affectedByLooting));
                drops.add(new LootDrop(Items.WOODEN_PICKAXE, 0, 2, chance, Conditional.affectedByLooting));
                drops.add(new LootDrop(Items.STICK, 0, 2, 1f - ((12f - 5f) / 12f), Conditional.affectedByLooting)); // Out of 12 outcomes 5 are already used, giving us 7 in 12 (12-5 in 12) chance
            }
            else
            {
                drops.add(new LootDrop(Items.IRON_HOE, 0, 2, chance, Conditional.affectedByLooting));
                drops.add(new LootDrop(Items.IRON_SHOVEL, 0, 2, chance, Conditional.affectedByLooting));
                drops.add(new LootDrop(Items.IRON_AXE, 0, 2, chance, Conditional.affectedByLooting));
                drops.add(new LootDrop(Items.IRON_PICKAXE, 0, 2, chance, Conditional.affectedByLooting));
                drops.add(new LootDrop(Items.IRON_SWORD, 0, 2, chance, Conditional.affectedByLooting));
                drops.add(new LootDrop(Items.STONE_HOE, 0, 2, chance, Conditional.affectedByLooting));
                drops.add(new LootDrop(Items.STONE_SHOVEL, 0, 2, chance, Conditional.affectedByLooting));
                drops.add(new LootDrop(Items.STONE_AXE, 0, 2, chance, Conditional.affectedByLooting));
                drops.add(new LootDrop(Items.STONE_PICKAXE, 0, 2, chance, Conditional.affectedByLooting));
                drops.add(new LootDrop(Items.STONE_SWORD, 0, 2, chance, Conditional.affectedByLooting));
                drops.add(new LootDrop(Items.GOLDEN_APPLE, 0, 2, 1f - ((12f - 10f) / 12f), Conditional.affectedByLooting)); // Out of 12 outcomes 10 are already used, giving us 2 in 12 (12-10 in 12) chance
            }
        }
        else if (entity instanceof MoCEntityFlameWraith)
            drops.add(new LootDrop(Items.REDSTONE, 0, 2, 1f, Conditional.affectedByLooting));


        // Ambient
        else if (entity instanceof MoCEntityCrab)
        {
            LootDrop drop = new LootDrop(MoCItems.crabraw, 0, 2, Conditional.affectedByLooting);
            drops.add(drop);
            if (MiaConfig.addCookedDrops)
                drop.smeltedItem = new ItemStack(MoCItems.crabcooked);
        }


        // Ambient and aquatic
        else if (entity instanceof MoCEntitySnail || entity instanceof MoCEntityMaggot)
            drops.add(new LootDrop(Items.SLIME_BALL, 0, 2, Conditional.affectedByLooting));
        else if (entity instanceof MoCEntityJellyFish)
        {
            drops.add(new LootDrop(Items.SLIME_BALL, 0, 2, Conditional.affectedByLooting));
            if (ModIds.HARVESTCRAFT.isLoaded)
                drops.add(new LootDrop(new ItemStack(ItemRegistry.jellyfishrawItem), 0, 2, Conditional.affectedByLooting));
        }


        // Aquatic
        else if (entity instanceof MoCEntityDolphin)
        {
            LootDrop drop = new LootDrop(Items.FISH, 0, 2, Conditional.affectedByLooting);
            drops.add(drop);
            if (MiaConfig.addCookedDrops)
                drop.smeltedItem = new ItemStack(Items.COOKED_FISH);
        }
        else if (entity instanceof MoCEntityShark)
        {
            drops.add(new LootDrop(MoCItems.sharkteeth, 1, 3, 0.9f));
            drops.add(new LootDrop(MoCItems.mocegg, 11, 0, 2, 0.1f, new ExtendedConditional(Conditional.pastWorldDifficulty, I18n.format("options.difficulty.peaceful")), ExtraConditional.isAdult)); // Only adult?
        }
        // Medium fish
        else if (entity instanceof MoCEntityBass)
        {
            if (ModIds.HARVESTCRAFT.isLoaded)
                addFishDrops(drops, 72, ItemRegistry.bassrawItem);
            else
                addFishDrops(drops, 72);
        }
        else if (entity instanceof MoCEntityCod)
            addFishDrops(drops, 71);
        else if (entity instanceof MoCEntitySalmon)
            addFishDrops(drops, 70, Items.FISH, Items.COOKED_FISH, 1);
        // Small fish
        else if (entity instanceof MoCEntityAnchovy)
        {
            if (ModIds.HARVESTCRAFT.isLoaded)
                addFishDrops(drops, 80, ItemRegistry.anchovyrawItem);
            else
                addFishDrops(drops, 80);
        }
        else if (entity instanceof MoCEntityAngelFish)
            addFishDrops(drops, 81);
        else if (entity instanceof MoCEntityAngler)
            addFishDrops(drops, 82);
        else if (entity instanceof MoCEntityClownFish)
            addFishDrops(drops, 83, Items.FISH, Items.COOKED_FISH, 2);
        else if (entity instanceof MoCEntityGoldFish)
            addFishDrops(drops, 84);
        else if (entity instanceof MoCEntityHippoTang)
            addFishDrops(drops, 85);
        else if (entity instanceof MoCEntityManderin)
            addFishDrops(drops, 86);
        else if (entity instanceof MoCEntityPiranha)
            addFishDrops(drops, 90);

//        drops.forEach(lootDrop ->
//        {
//            Item item = lootDrop.item.getItem();
//            Block block = Block.getBlockFromItem(item);
//
//            if (item == Items.FISH)
//            {
//                if (replaceFishDrops && entity instanceof MoCEntityCod)
//                {
//                    lootDrop.item = new ItemStack(Items.FISH, lootDrop.item.getCount(), 1);
//                    if (MiaConfig.addCookedDrops)
//                        lootDrop.smeltedItem = new ItemStack(Items.COOKED_FISH, lootDrop.item.getCount(), 1);
//                }
//                else if (replaceFishDrops && entity instanceof MoCEntityClownFish)
//                    lootDrop.item = new ItemStack(Items.FISH, lootDrop.item.getCount(), 2);
//                else if (ModIds.HARVESTCRAFT.isLoaded && entity instanceof MoCEntityAnchovy)
//                    lootDrop.item = new ItemStack(ItemRegistry.anchovyrawItem);
//                else if (ModIds.HARVESTCRAFT.isLoaded && entity instanceof MoCEntityBass)
//                    lootDrop.item = new ItemStack(ItemRegistry.bassrawItem);
//                else
//                    lootDrop.smeltedItem = new ItemStack(Items.COOKED_FISH);
//
//            }
//            else if (item == MoCItems.rawTurkey && MiaConfig.addCookedDrops)
//                lootDrop.smeltedItem = new ItemStack(MoCItems.cookedTurkey);
//            else if (item == MoCItems.ratRaw && MiaConfig.addCookedDrops)
//                lootDrop.smeltedItem = new ItemStack(MoCItems.ratCooked);
//            else if (item == MoCItems.ostrichraw && MiaConfig.addCookedDrops)
//                lootDrop.smeltedItem = new ItemStack(MoCItems.ostrichcooked);
//            else if (item == MoCItems.crabraw && MiaConfig.addCookedDrops)
//                lootDrop.smeltedItem = new ItemStack(MoCItems.crabcooked);
//            else if (item == Items.PORKCHOP && MiaConfig.addCookedDrops)
//                lootDrop.smeltedItem = new ItemStack(Items.COOKED_PORKCHOP);
//            else if (item == MoCItems.turtleraw && MiaConfig.addCookedDrops && ModIds.HARVESTCRAFT.isLoaded)
//                lootDrop.smeltedItem = new ItemStack(ItemRegistry.turtlecookedItem);
////            else if (item == MoCItems.bo && entity instanceof MoCEntityTurtle)
////                lootDrop.addConditional();
////            else if (item == MoCItems.katana && entity instanceof MoCEntityTurtle)
////                lootDrop.addConditional();
////            else if (item == MoCItems.sai && entity instanceof MoCEntityTurtle)
////                lootDrop.addConditional();
////            else if (item == MoCItems.nunchaku && entity instanceof MoCEntityTurtle)
////                lootDrop.addConditional();
//            else if (item == Items.STRING && (entity instanceof MoCEntityScorpion || entity instanceof MoCEntityPetScorpion))
//                lootDrop.addConditional(ExtraConditional.isNotAdult);
//            else if (item == MoCItems.mocegg)
//            {
//                if (entity instanceof MoCEntityWyvern)
//                {
//                    // Mother Wyvern
//                    if (lootDrop.item.getMetadata() == 54)
//                        lootDrop.chance = MoCreatures.proxy.motherWyvernEggDropChance;
//                    else
//                        lootDrop.chance = MoCreatures.proxy.wyvernEggDropChance;
//                }
//
//                lootDrop.chance = MoCreatures.proxy.rareItemDropChance / 100f;
//                lootDrop.addConditional(ExtraConditional.isAdult);
//            }
//            else if (item == MoCItems.heartdarkness ||
//                item == MoCItems.heartfire ||
//                item == MoCItems.heartundead ||
//                item == MoCItems.scorpStingNether ||
//                item == MoCItems.scorpStingCave ||
//                item == MoCItems.scorpStingFrost ||
//                item == MoCItems.scorpStingDirt ||
//                block == Blocks.FIRE)
//                lootDrop.chance = MoCreatures.proxy.rareItemDropChance / 100f;
//        });

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

    @Override
    public ModIds getModId()
    {
        return ModIds.MO_CREATURES;
    }

    private void addFishDrops(List<LootDrop> drops, int eggMeta)
    {
        addFishDrops(drops, eggMeta, Items.AIR);
    }

    private void addFishDrops(List<LootDrop> drops, int eggMeta, Item replacement)
    {
        addFishDrops(drops, eggMeta, replacement, Items.AIR);
    }

    private void addFishDrops(List<LootDrop> drops, int eggMeta, Item replacement, Item cooked)
    {
        addFishDrops(drops, eggMeta, new ItemStack(replacement), new ItemStack(cooked));
    }

    private void addFishDrops(List<LootDrop> drops, int eggMeta, Item replacement, Item cooked, int meta)
    {
        addFishDrops(drops, eggMeta, new ItemStack(replacement, 1, meta), new ItemStack(cooked, 1, meta));
    }

    private void addFishDrops(List<LootDrop> drops, int eggMeta, ItemStack replacement, ItemStack cooked)
    {
        if (replaceFishDrops && !replacement.isEmpty())
        {
            LootDrop drop = new LootDrop(replacement, 1, 1, 0.7f);
            drops.add(drop);
            if (!cooked.isEmpty())
                drop.smeltedItem = cooked;
        }
        else
        {
            LootDrop drop = new LootDrop(Items.FISH, 1, 1, 0.7f);
            drops.add(drop);
            if (MiaConfig.addCookedDrops)
                drop.smeltedItem = new ItemStack(Items.COOKED_FISH);
        }

        drops.add(new LootDrop(MoCItems.mocegg, eggMeta, 0, 1, 0.3f));
    }

    private static class GenericVariantSetter<T extends EntityLivingBase> implements MobTableBuilder.EntityPropertySetter<T>
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

    private static IMobRenderHook<?> RENDER_HOOK_GENERIC_HIGHER = ((renderInfo, entityLivingBase) ->
    {
        GlStateManager.translate(0f, 0.5f, 0f);
        return renderInfo;
    });

    private static final IMobRenderHook<?> RENDER_HOOK_GENERIC_LOWER = ((renderInfo, entityLivingBase) ->
    {
        GlStateManager.translate(0f, -0.5f, 0f);
        return renderInfo;
    });
}
