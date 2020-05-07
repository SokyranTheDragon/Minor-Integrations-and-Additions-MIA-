package com.github.sokyranthedragon.mia.integrations.iceandfire;

import cofh.thermalexpansion.util.managers.dynamo.NumismaticManager;
import cofh.thermalexpansion.util.managers.machine.CrucibleManager;
import cofh.thermalexpansion.util.managers.machine.PulverizerManager;
import cofh.thermalexpansion.util.managers.machine.SawmillManager;
import cofh.thermalexpansion.util.managers.machine.SmelterManager;
import cofh.thermalfoundation.item.ItemMaterial;
import com.github.alexthe666.iceandfire.block.IafBlockRegistry;
import com.github.alexthe666.iceandfire.enums.EnumTroll;
import com.github.alexthe666.iceandfire.item.IafItemRegistry;
import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.integrations.thermalexpansion.IThermalExpansionIntegration;
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
        ItemStack ingot = new ItemStack(IafItemRegistry.silverIngot);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(IafItemRegistry.silver_sword), ingot, 1);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(IafItemRegistry.silver_pickaxe), ingot, 1);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(IafItemRegistry.silver_axe), ingot, 1);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(IafItemRegistry.silver_hoe), ingot, 1);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(IafItemRegistry.silver_shovel), ingot, 1);
        
        SmelterManager.addRecycleRecipe(energy, new ItemStack(IafItemRegistry.silver_helmet), ingot, 2);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(IafItemRegistry.silver_chestplate), ingot, 4);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(IafItemRegistry.silver_leggings), ingot, 3);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(IafItemRegistry.silver_boots), ingot, 2);
        
        // Fire dragonsteel
        ingot = new ItemStack(IafItemRegistry.dragonsteel_fire_ingot);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(IafItemRegistry.dragonsteel_fire_sword), ingot, 1);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(IafItemRegistry.dragonsteel_fire_pickaxe), ingot, 1);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(IafItemRegistry.dragonsteel_fire_axe), ingot, 1);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(IafItemRegistry.dragonsteel_fire_hoe), ingot, 1);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(IafItemRegistry.dragonsteel_fire_shovel), ingot, 1);
        
        SmelterManager.addRecycleRecipe(energy, new ItemStack(IafItemRegistry.dragonsteel_fire_helmet), ingot, 2);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(IafItemRegistry.dragonsteel_fire_chestplate), ingot, 4);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(IafItemRegistry.dragonsteel_fire_leggings), ingot, 3);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(IafItemRegistry.dragonsteel_fire_boots), ingot, 2);
        
        // Ice dragonsteel
        ingot = new ItemStack(IafItemRegistry.dragonsteel_ice_ingot);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(IafItemRegistry.dragonsteel_ice_sword), ingot, 1);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(IafItemRegistry.dragonsteel_ice_pickaxe), ingot, 1);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(IafItemRegistry.dragonsteel_ice_axe), ingot, 1);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(IafItemRegistry.dragonsteel_ice_hoe), ingot, 1);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(IafItemRegistry.dragonsteel_ice_shovel), ingot, 1);
        
        SmelterManager.addRecycleRecipe(energy, new ItemStack(IafItemRegistry.dragonsteel_ice_helmet), ingot, 2);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(IafItemRegistry.dragonsteel_ice_chestplate), ingot, 4);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(IafItemRegistry.dragonsteel_ice_leggings), ingot, 3);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(IafItemRegistry.dragonsteel_ice_boots), ingot, 2);
        
        
        // Pulverizer
        energy = 3_000;
        PulverizerManager.addRecipe(energy, new ItemStack(IafItemRegistry.troll_tusk), new ItemStack(Items.DYE, 8, 15));
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(IafBlockRegistry.lectern), ItemMaterial.dustWood, 4);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(IafBlockRegistry.podium, 1, OreDictionary.WILDCARD_VALUE), ItemMaterial.dustWood, 6);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(IafItemRegistry.earplugs), ItemMaterial.dustWood, 1);
        // Chitin
        // Myrmex armor
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(IafItemRegistry.myrmex_desert_helmet), new ItemStack(IafItemRegistry.myrmex_desert_chitin), 2);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(IafItemRegistry.myrmex_desert_chestplate), new ItemStack(IafItemRegistry.myrmex_desert_chitin), 4);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(IafItemRegistry.myrmex_desert_leggings), new ItemStack(IafItemRegistry.myrmex_desert_chitin), 3);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(IafItemRegistry.myrmex_desert_boots), new ItemStack(IafItemRegistry.myrmex_desert_chitin), 2);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(IafItemRegistry.myrmex_jungle_helmet), new ItemStack(IafItemRegistry.myrmex_jungle_chitin), 2);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(IafItemRegistry.myrmex_jungle_chestplate), new ItemStack(IafItemRegistry.myrmex_jungle_chitin), 4);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(IafItemRegistry.myrmex_jungle_leggings), new ItemStack(IafItemRegistry.myrmex_jungle_chitin), 3);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(IafItemRegistry.myrmex_jungle_boots), new ItemStack(IafItemRegistry.myrmex_jungle_chitin), 2);
        // Myrmex tools
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(IafItemRegistry.myrmex_desert_axe), new ItemStack(IafItemRegistry.myrmex_desert_chitin), 1);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(IafItemRegistry.myrmex_desert_pickaxe), new ItemStack(IafItemRegistry.myrmex_desert_chitin), 1);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(IafItemRegistry.myrmex_desert_shovel), new ItemStack(IafItemRegistry.myrmex_desert_chitin), 1);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(IafItemRegistry.myrmex_desert_hoe), new ItemStack(IafItemRegistry.myrmex_desert_chitin), 1);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(IafItemRegistry.myrmex_desert_sword), new ItemStack(IafItemRegistry.myrmex_desert_chitin), 1);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(IafItemRegistry.myrmex_desert_sword_venom), new ItemStack(IafItemRegistry.myrmex_desert_chitin), 1);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(IafItemRegistry.myrmex_desert_staff), new ItemStack(IafItemRegistry.myrmex_desert_chitin), 1);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(IafItemRegistry.myrmex_jungle_axe), new ItemStack(IafItemRegistry.myrmex_jungle_chitin), 1);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(IafItemRegistry.myrmex_jungle_pickaxe), new ItemStack(IafItemRegistry.myrmex_jungle_chitin), 1);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(IafItemRegistry.myrmex_jungle_shovel), new ItemStack(IafItemRegistry.myrmex_jungle_chitin), 1);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(IafItemRegistry.myrmex_jungle_hoe), new ItemStack(IafItemRegistry.myrmex_jungle_chitin), 1);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(IafItemRegistry.myrmex_jungle_sword), new ItemStack(IafItemRegistry.myrmex_jungle_chitin), 1);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(IafItemRegistry.myrmex_jungle_sword_venom), new ItemStack(IafItemRegistry.myrmex_jungle_chitin), 1);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(IafItemRegistry.myrmex_jungle_staff), new ItemStack(IafItemRegistry.myrmex_desert_chitin), 1);
        // Death worm
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(IafItemRegistry.deathworm_yellow_helmet), new ItemStack(IafItemRegistry.deathworm_chitin, 2, 0), 2);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(IafItemRegistry.deathworm_yellow_chestplate), new ItemStack(IafItemRegistry.deathworm_chitin, 4, 0), 4);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(IafItemRegistry.deathworm_yellow_leggings), new ItemStack(IafItemRegistry.deathworm_chitin, 3, 0), 3);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(IafItemRegistry.deathworm_yellow_boots), new ItemStack(IafItemRegistry.deathworm_chitin, 2, 0), 2);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(IafItemRegistry.deathworm_white_helmet), new ItemStack(IafItemRegistry.deathworm_chitin, 2, 1), 2);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(IafItemRegistry.deathworm_white_chestplate), new ItemStack(IafItemRegistry.deathworm_chitin, 4, 1), 4);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(IafItemRegistry.deathworm_white_leggings), new ItemStack(IafItemRegistry.deathworm_chitin, 3, 1), 3);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(IafItemRegistry.deathworm_white_boots), new ItemStack(IafItemRegistry.deathworm_chitin, 2, 1), 2);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(IafItemRegistry.deathworm_red_helmet), new ItemStack(IafItemRegistry.deathworm_chitin, 2, 2), 2);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(IafItemRegistry.deathworm_red_chestplate), new ItemStack(IafItemRegistry.deathworm_chitin, 4, 2), 4);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(IafItemRegistry.deathworm_red_leggings), new ItemStack(IafItemRegistry.deathworm_chitin, 3, 2), 3);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(IafItemRegistry.deathworm_red_boots), new ItemStack(IafItemRegistry.deathworm_chitin, 2, 2), 2);
        
        
        // Sawmill
        energy = 1_500;
        SawmillManager.addRecipe(energy, new ItemStack(IafItemRegistry.sheep_helmet, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(Items.LEATHER, 2), new ItemStack(Items.STRING, 8));
        SawmillManager.addRecipe(energy, new ItemStack(IafItemRegistry.sheep_chestplate, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(Items.LEATHER, 4), new ItemStack(Items.STRING, 16));
        SawmillManager.addRecipe(energy, new ItemStack(IafItemRegistry.sheep_leggings, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(Items.LEATHER, 3), new ItemStack(Items.STRING, 12));
        SawmillManager.addRecipe(energy, new ItemStack(IafItemRegistry.sheep_boots, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(Items.LEATHER, 2), new ItemStack(Items.STRING, 8));
        SawmillManager.addRecipe(energy, new ItemStack(IafItemRegistry.blindfold), new ItemStack(Items.STRING), new ItemStack(Items.LEATHER), 50);
        
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
        SmelterManager.addRecycleRecipe(energy, new ItemStack(IafItemRegistry.dragon_armor_iron, 1, 0), new ItemStack(Items.IRON_INGOT), 2 * 9 + 4, false);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(IafItemRegistry.dragon_armor_iron, 1, 1), new ItemStack(Items.IRON_INGOT), 2 * 9 + 4, false);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(IafItemRegistry.dragon_armor_iron, 1, 2), new ItemStack(Items.IRON_INGOT), 4 * 9, false);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(IafItemRegistry.dragon_armor_iron, 1, 3), new ItemStack(Items.IRON_INGOT), 9 + 4, false);
        // Gold
        SmelterManager.addRecycleRecipe(energy, new ItemStack(IafItemRegistry.dragon_armor_gold, 1, 0), new ItemStack(Items.GOLD_INGOT), 2 * 9 + 4, false);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(IafItemRegistry.dragon_armor_gold, 1, 1), new ItemStack(Items.GOLD_INGOT), 2 * 9 + 4, false);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(IafItemRegistry.dragon_armor_gold, 1, 2), new ItemStack(Items.GOLD_INGOT), 4 * 9, false);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(IafItemRegistry.dragon_armor_gold, 1, 3), new ItemStack(Items.GOLD_INGOT), 9 + 4, false);
        // Diamond
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(IafItemRegistry.dragon_armor_diamond, 1, 0), new ItemStack(Items.DIAMOND), 2 * 9 + 4, false);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(IafItemRegistry.dragon_armor_diamond, 1, 1), new ItemStack(Items.DIAMOND), 2 * 9 + 4, false);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(IafItemRegistry.dragon_armor_diamond, 1, 2), new ItemStack(Items.DIAMOND), 4 * 9, false);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(IafItemRegistry.dragon_armor_diamond, 1, 3), new ItemStack(Items.DIAMOND), 9 + 4, false);
        // Silver
        SmelterManager.addRecycleRecipe(energy, new ItemStack(IafItemRegistry.dragon_armor_silver, 1, 0), new ItemStack(IafItemRegistry.silverIngot), 2 * 9 + 4, false);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(IafItemRegistry.dragon_armor_silver, 1, 1), new ItemStack(IafItemRegistry.silverIngot), 2 * 9 + 4, false);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(IafItemRegistry.dragon_armor_silver, 1, 2), new ItemStack(IafItemRegistry.silverIngot), 4 * 9, false);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(IafItemRegistry.dragon_armor_silver, 1, 3), new ItemStack(IafItemRegistry.silverIngot), 9 + 4, false);
        // Fire Dragonsteel
        SmelterManager.addRecycleRecipe(energy, new ItemStack(IafItemRegistry.dragon_armor_dragonsteel_fire, 1, 0), new ItemStack(IafItemRegistry.dragonsteel_fire_ingot), 2 * 9 + 4, false);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(IafItemRegistry.dragon_armor_dragonsteel_fire, 1, 1), new ItemStack(IafItemRegistry.dragonsteel_fire_ingot), 2 * 9 + 4, false);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(IafItemRegistry.dragon_armor_dragonsteel_fire, 1, 2), new ItemStack(IafItemRegistry.dragonsteel_fire_ingot), 4 * 9, false);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(IafItemRegistry.dragon_armor_dragonsteel_fire, 1, 3), new ItemStack(IafItemRegistry.dragonsteel_fire_ingot), 9 + 4, false);
        // Ice Dragonsteel
        SmelterManager.addRecycleRecipe(energy, new ItemStack(IafItemRegistry.dragon_armor_dragonsteel_ice, 1, 0), new ItemStack(IafItemRegistry.dragonsteel_ice_ingot), 2 * 9 + 4, false);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(IafItemRegistry.dragon_armor_dragonsteel_ice, 1, 1), new ItemStack(IafItemRegistry.dragonsteel_ice_ingot), 2 * 9 + 4, false);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(IafItemRegistry.dragon_armor_dragonsteel_ice, 1, 2), new ItemStack(IafItemRegistry.dragonsteel_ice_ingot), 4 * 9, false);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(IafItemRegistry.dragon_armor_dragonsteel_ice, 1, 3), new ItemStack(IafItemRegistry.dragonsteel_ice_ingot), 9 + 4, false);
        
        // Hippogryph armor recycling
        energy = 6_000;
        SmelterManager.addRecycleRecipe(energy, new ItemStack(IafItemRegistry.iron_hippogryph_armor), new ItemStack(Items.IRON_INGOT), 2);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(IafItemRegistry.gold_hippogryph_armor), new ItemStack(Items.GOLD_INGOT), 2);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(IafItemRegistry.diamond_hippogryph_armor), new ItemStack(Items.DIAMOND), 2);
        
        
        // Numismatic dynamo - accept sapphire as currency (no upgrade) as it's used as currency with ice villagers. Matches emerald behaviour.
        NumismaticManager.addFuel(new ItemStack(IafItemRegistry.sapphireGem), 200_000);
        
        
        // Magma Crucible
        CrucibleManager.addRecipe(1_600, new ItemStack(IafBlockRegistry.dragon_ice), new FluidStack(FluidRegistry.WATER, 1000));
        CrucibleManager.addRecipe(1_600, new ItemStack(IafBlockRegistry.dragon_ice_spikes), new FluidStack(FluidRegistry.WATER, 1000));
    }
    
    @Nonnull
    @Override
    public ModIds getModId()
    {
        return ModIds.ICE_AND_FIRE;
    }
}
