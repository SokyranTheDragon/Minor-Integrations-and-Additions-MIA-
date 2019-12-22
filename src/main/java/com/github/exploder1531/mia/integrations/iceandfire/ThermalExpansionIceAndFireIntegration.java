package com.github.exploder1531.mia.integrations.iceandfire;

import cofh.thermalexpansion.util.managers.dynamo.NumismaticManager;
import cofh.thermalexpansion.util.managers.machine.CrucibleManager;
import cofh.thermalexpansion.util.managers.machine.PulverizerManager;
import cofh.thermalexpansion.util.managers.machine.SawmillManager;
import cofh.thermalexpansion.util.managers.machine.SmelterManager;
import cofh.thermalfoundation.item.ItemMaterial;
import com.github.alexthe666.iceandfire.core.ModBlocks;
import com.github.alexthe666.iceandfire.core.ModItems;
import com.github.alexthe666.iceandfire.enums.EnumTroll;
import com.github.exploder1531.mia.integrations.ModIds;
import com.github.exploder1531.mia.integrations.thermalexpansion.IThermalExpansionIntegration;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.OreDictionary;

import javax.annotation.Nonnull;

class ThermalExpansionIceAndFireIntegration implements IThermalExpansionIntegration
{
    @Override
    public void addRecipes()
    {
        // Silver tools and armor recycling (induction smelter)
        int energy = 6_000;
        // Silver
        ItemStack ingot = new ItemStack(ModItems.silverIngot);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(ModItems.silver_sword), ingot, 1);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(ModItems.silver_pickaxe), ingot, 1);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(ModItems.silver_axe), ingot, 1);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(ModItems.silver_hoe), ingot, 1);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(ModItems.silver_shovel), ingot, 1);
        
        SmelterManager.addRecycleRecipe(energy, new ItemStack(ModItems.silver_helmet), ingot, 2);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(ModItems.silver_chestplate), ingot, 4);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(ModItems.silver_leggings), ingot, 3);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(ModItems.silver_boots), ingot, 2);
        
        // Fire dragonsteel
        ingot = new ItemStack(ModItems.dragonsteel_fire_ingot);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(ModItems.dragonsteel_fire_sword), ingot, 1);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(ModItems.dragonsteel_fire_pickaxe), ingot, 1);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(ModItems.dragonsteel_fire_axe), ingot, 1);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(ModItems.dragonsteel_fire_hoe), ingot, 1);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(ModItems.dragonsteel_fire_shovel), ingot, 1);
        
        SmelterManager.addRecycleRecipe(energy, new ItemStack(ModItems.dragonsteel_fire_helmet), ingot, 2);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(ModItems.dragonsteel_fire_chestplate), ingot, 4);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(ModItems.dragonsteel_fire_leggings), ingot, 3);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(ModItems.dragonsteel_fire_boots), ingot, 2);
        
        // Ice dragonsteel
        ingot = new ItemStack(ModItems.dragonsteel_ice_ingot);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(ModItems.dragonsteel_ice_sword), ingot, 1);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(ModItems.dragonsteel_ice_pickaxe), ingot, 1);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(ModItems.dragonsteel_ice_axe), ingot, 1);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(ModItems.dragonsteel_ice_hoe), ingot, 1);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(ModItems.dragonsteel_ice_shovel), ingot, 1);
        
        SmelterManager.addRecycleRecipe(energy, new ItemStack(ModItems.dragonsteel_ice_helmet), ingot, 2);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(ModItems.dragonsteel_ice_chestplate), ingot, 4);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(ModItems.dragonsteel_ice_leggings), ingot, 3);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(ModItems.dragonsteel_ice_boots), ingot, 2);
        
        
        // Pulverizer
        energy = 3_000;
        PulverizerManager.addRecipe(energy, new ItemStack(ModItems.troll_tusk), new ItemStack(Items.DYE, 8, 15));
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(ModBlocks.lectern), ItemMaterial.dustWood, 4);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(ModBlocks.podium, 1, OreDictionary.WILDCARD_VALUE), ItemMaterial.dustWood, 6);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(ModItems.earplugs), ItemMaterial.dustWood, 1);
        // Chitin
        // Myrmex armor
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(ModItems.myrmex_desert_helmet), new ItemStack(ModItems.myrmex_desert_chitin), 2);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(ModItems.myrmex_desert_chestplate), new ItemStack(ModItems.myrmex_desert_chitin), 4);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(ModItems.myrmex_desert_leggings), new ItemStack(ModItems.myrmex_desert_chitin), 3);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(ModItems.myrmex_desert_boots), new ItemStack(ModItems.myrmex_desert_chitin), 2);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(ModItems.myrmex_jungle_helmet), new ItemStack(ModItems.myrmex_jungle_chitin), 2);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(ModItems.myrmex_jungle_chestplate), new ItemStack(ModItems.myrmex_jungle_chitin), 4);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(ModItems.myrmex_jungle_leggings), new ItemStack(ModItems.myrmex_jungle_chitin), 3);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(ModItems.myrmex_jungle_boots), new ItemStack(ModItems.myrmex_jungle_chitin), 2);
        // Myrmex tools
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(ModItems.myrmex_desert_axe), new ItemStack(ModItems.myrmex_desert_chitin), 1);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(ModItems.myrmex_desert_pickaxe), new ItemStack(ModItems.myrmex_desert_chitin), 1);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(ModItems.myrmex_desert_shovel), new ItemStack(ModItems.myrmex_desert_chitin), 1);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(ModItems.myrmex_desert_hoe), new ItemStack(ModItems.myrmex_desert_chitin), 1);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(ModItems.myrmex_desert_sword), new ItemStack(ModItems.myrmex_desert_chitin), 1);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(ModItems.myrmex_desert_sword_venom), new ItemStack(ModItems.myrmex_desert_chitin), 1);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(ModItems.myrmex_desert_staff), new ItemStack(ModItems.myrmex_desert_chitin), 1);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(ModItems.myrmex_jungle_axe), new ItemStack(ModItems.myrmex_jungle_chitin), 1);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(ModItems.myrmex_jungle_pickaxe), new ItemStack(ModItems.myrmex_jungle_chitin), 1);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(ModItems.myrmex_jungle_shovel), new ItemStack(ModItems.myrmex_jungle_chitin), 1);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(ModItems.myrmex_jungle_hoe), new ItemStack(ModItems.myrmex_jungle_chitin), 1);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(ModItems.myrmex_jungle_sword), new ItemStack(ModItems.myrmex_jungle_chitin), 1);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(ModItems.myrmex_jungle_sword_venom), new ItemStack(ModItems.myrmex_jungle_chitin), 1);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(ModItems.myrmex_jungle_staff), new ItemStack(ModItems.myrmex_desert_chitin), 1);
        // Death worm
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(ModItems.deathworm_yellow_helmet), new ItemStack(ModItems.deathworm_chitin, 2, 0), 2);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(ModItems.deathworm_yellow_chestplate), new ItemStack(ModItems.deathworm_chitin, 4, 0), 4);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(ModItems.deathworm_yellow_leggings), new ItemStack(ModItems.deathworm_chitin, 3, 0), 3);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(ModItems.deathworm_yellow_boots), new ItemStack(ModItems.deathworm_chitin, 2, 0), 2);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(ModItems.deathworm_white_helmet), new ItemStack(ModItems.deathworm_chitin, 2, 1), 2);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(ModItems.deathworm_white_chestplate), new ItemStack(ModItems.deathworm_chitin, 4, 1), 4);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(ModItems.deathworm_white_leggings), new ItemStack(ModItems.deathworm_chitin, 3, 1), 3);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(ModItems.deathworm_white_boots), new ItemStack(ModItems.deathworm_chitin, 2, 1), 2);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(ModItems.deathworm_red_helmet), new ItemStack(ModItems.deathworm_chitin, 2, 2), 2);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(ModItems.deathworm_red_chestplate), new ItemStack(ModItems.deathworm_chitin, 4, 2), 4);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(ModItems.deathworm_red_leggings), new ItemStack(ModItems.deathworm_chitin, 3, 2), 3);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(ModItems.deathworm_red_boots), new ItemStack(ModItems.deathworm_chitin, 2, 2), 2);
        
        
        // Sawmill
        energy = 1_500;
        SawmillManager.addRecipe(energy, new ItemStack(ModItems.sheep_helmet, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(Items.LEATHER, 2), new ItemStack(Items.STRING, 8));
        SawmillManager.addRecipe(energy, new ItemStack(ModItems.sheep_chestplate, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(Items.LEATHER, 4), new ItemStack(Items.STRING, 16));
        SawmillManager.addRecipe(energy, new ItemStack(ModItems.sheep_leggings, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(Items.LEATHER, 3), new ItemStack(Items.STRING, 12));
        SawmillManager.addRecipe(energy, new ItemStack(ModItems.sheep_boots, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(Items.LEATHER, 2), new ItemStack(Items.STRING, 8));
        SawmillManager.addRecipe(energy, new ItemStack(ModItems.blindfold), new ItemStack(Items.STRING), new ItemStack(Items.LEATHER), 50);
        
        for (EnumTroll troll : EnumTroll.values())
        {
            ItemStack trollLeather = new ItemStack(troll.leather);
            SawmillManager.addRecipe(energy, new ItemStack(troll.helmet, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(troll.leather, 1), new ItemStack(Items.DYE, 5, 15));
            SawmillManager.addRecycleRecipe(energy, new ItemStack(troll.chestplate), trollLeather, 4);
            SawmillManager.addRecycleRecipe(energy, new ItemStack(troll.leggings), trollLeather, 3);
            SawmillManager.addRecycleRecipe(energy, new ItemStack(troll.boots), trollLeather, 2);
        }
        
        
        // Dragon armor recycling (Pulverized - diamond, Induction smelter - iron, gold)
        energy = 30_000;
        // Iron
        SmelterManager.addRecycleRecipe(energy, new ItemStack(ModItems.dragon_armor_iron, 1, 0), new ItemStack(Items.IRON_INGOT), 2 * 9 + 4, false);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(ModItems.dragon_armor_iron, 1, 1), new ItemStack(Items.IRON_INGOT), 2 * 9 + 4, false);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(ModItems.dragon_armor_iron, 1, 2), new ItemStack(Items.IRON_INGOT), 4 * 9, false);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(ModItems.dragon_armor_iron, 1, 3), new ItemStack(Items.IRON_INGOT), 9 + 4, false);
        // Gold
        SmelterManager.addRecycleRecipe(energy, new ItemStack(ModItems.dragon_armor_gold, 1, 0), new ItemStack(Items.GOLD_INGOT), 2 * 9 + 4, false);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(ModItems.dragon_armor_gold, 1, 1), new ItemStack(Items.GOLD_INGOT), 2 * 9 + 4, false);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(ModItems.dragon_armor_gold, 1, 2), new ItemStack(Items.GOLD_INGOT), 4 * 9, false);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(ModItems.dragon_armor_gold, 1, 3), new ItemStack(Items.GOLD_INGOT), 9 + 4, false);
        // Diamond
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(ModItems.dragon_armor_diamond, 1, 0), new ItemStack(Items.DIAMOND), 2 * 9 + 4, false);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(ModItems.dragon_armor_diamond, 1, 1), new ItemStack(Items.DIAMOND), 2 * 9 + 4, false);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(ModItems.dragon_armor_diamond, 1, 2), new ItemStack(Items.DIAMOND), 4 * 9, false);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(ModItems.dragon_armor_diamond, 1, 3), new ItemStack(Items.DIAMOND), 9 + 4, false);
        // Silver
        SmelterManager.addRecycleRecipe(energy, new ItemStack(ModItems.dragon_armor_silver, 1, 0), new ItemStack(ModItems.silverIngot), 2 * 9 + 4, false);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(ModItems.dragon_armor_silver, 1, 1), new ItemStack(ModItems.silverIngot), 2 * 9 + 4, false);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(ModItems.dragon_armor_silver, 1, 2), new ItemStack(ModItems.silverIngot), 4 * 9, false);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(ModItems.dragon_armor_silver, 1, 3), new ItemStack(ModItems.silverIngot), 9 + 4, false);
        // Fire Dragonsteel
        SmelterManager.addRecycleRecipe(energy, new ItemStack(ModItems.dragon_armor_dragonsteel_fire, 1, 0), new ItemStack(ModItems.dragonsteel_fire_ingot), 2 * 9 + 4, false);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(ModItems.dragon_armor_dragonsteel_fire, 1, 1), new ItemStack(ModItems.dragonsteel_fire_ingot), 2 * 9 + 4, false);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(ModItems.dragon_armor_dragonsteel_fire, 1, 2), new ItemStack(ModItems.dragonsteel_fire_ingot), 4 * 9, false);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(ModItems.dragon_armor_dragonsteel_fire, 1, 3), new ItemStack(ModItems.dragonsteel_fire_ingot), 9 + 4, false);
        // Ice Dragonsteel
        SmelterManager.addRecycleRecipe(energy, new ItemStack(ModItems.dragon_armor_dragonsteel_ice, 1, 0), new ItemStack(ModItems.dragonsteel_ice_ingot), 2 * 9 + 4, false);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(ModItems.dragon_armor_dragonsteel_ice, 1, 1), new ItemStack(ModItems.dragonsteel_ice_ingot), 2 * 9 + 4, false);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(ModItems.dragon_armor_dragonsteel_ice, 1, 2), new ItemStack(ModItems.dragonsteel_ice_ingot), 4 * 9, false);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(ModItems.dragon_armor_dragonsteel_ice, 1, 3), new ItemStack(ModItems.dragonsteel_ice_ingot), 9 + 4, false);
        
        // Hippogryph armor recycling
        energy = 6_000;
        SmelterManager.addRecycleRecipe(energy, new ItemStack(ModItems.iron_hippogryph_armor), new ItemStack(Items.IRON_INGOT), 2);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(ModItems.gold_hippogryph_armor), new ItemStack(Items.GOLD_INGOT), 2);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(ModItems.diamond_hippogryph_armor), new ItemStack(Items.DIAMOND), 2);
        
        
        // Numismatic dynamo - accept sapphire as currency (no upgrade) as it's used as currency with ice villagers. Matches emerald behaviour.
        NumismaticManager.addFuel(new ItemStack(ModItems.sapphireGem), 200_000);
        
        
        // Magma Crucible
        CrucibleManager.addRecipe(1_600, new ItemStack(ModBlocks.dragon_ice), new FluidStack(FluidRegistry.WATER, 1000));
        CrucibleManager.addRecipe(1_600, new ItemStack(ModBlocks.dragon_ice_spikes), new FluidStack(FluidRegistry.WATER, 1000));
    }
    
    @Nonnull
    @Override
    public String getModId()
    {
        return ModIds.ICE_AND_FIRE;
    }
}
