package com.github.exploder1531.mia.integrations.thaumcraft;

import cofh.thermalexpansion.util.managers.device.FactorizerManager;
import cofh.thermalexpansion.util.managers.device.TapperManager;
import cofh.thermalexpansion.util.managers.machine.SawmillManager;
import cofh.thermalexpansion.util.managers.machine.SmelterManager;
import cofh.thermalfoundation.init.TFFluids;
import cofh.thermalfoundation.item.ItemMaterial;
import com.github.exploder1531.mia.integrations.ModIds;
import com.github.exploder1531.mia.integrations.thermalexpansion.IThermalExpansionIntegration;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import thaumcraft.api.blocks.BlocksTC;
import thaumcraft.api.items.ItemsTC;

import javax.annotation.Nonnull;

class ThermalExpansionThaumcraftIntegration implements IThermalExpansionIntegration
{
    @Override
    public void addRecipes()
    {
        // Factorizer
        FactorizerManager.addDefaultRecipe(new ItemStack(ItemsTC.amber), new ItemStack(BlocksTC.amberBlock), 4);
        FactorizerManager.addDefaultRecipe(new ItemStack(BlocksTC.amberBlock), new ItemStack(BlocksTC.amberBrick), 4);
        FactorizerManager.addDefaultRecipe(new ItemStack(Items.ROTTEN_FLESH), new ItemStack(BlocksTC.fleshBlock));
        FactorizerManager.addDefaultRecipe(new ItemStack(ItemsTC.ingots, 1, 2), new ItemStack(BlocksTC.metalBlockBrass));
        FactorizerManager.addDefaultRecipe(new ItemStack(ItemsTC.ingots, 1, 0), new ItemStack(BlocksTC.metalBlockThaumium));
        FactorizerManager.addDefaultRecipe(new ItemStack(ItemsTC.ingots, 1, 1), new ItemStack(BlocksTC.metalBlockVoid));
        FactorizerManager.addDefaultRecipe(new ItemStack(ItemsTC.nuggets, 1, 8), new ItemStack(ItemsTC.ingots, 1, 2));
        FactorizerManager.addDefaultRecipe(new ItemStack(ItemsTC.nuggets, 1, 6), new ItemStack(ItemsTC.ingots, 1, 0));
        FactorizerManager.addDefaultRecipe(new ItemStack(ItemsTC.nuggets, 1, 7), new ItemStack(ItemsTC.ingots, 1, 1));
        FactorizerManager.addDefaultRecipe(new ItemStack(ItemsTC.nuggets, 1, 5), new ItemStack(ItemsTC.quicksilver));
        
        
        // Induction smelter
        int energy = 6_000;
        // Thaumium
        ItemStack ingot = new ItemStack(ItemsTC.ingots, 1, 0);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(ItemsTC.thaumiumSword), ingot, 1);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(ItemsTC.thaumiumPick), ingot, 1);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(ItemsTC.thaumiumAxe), ingot, 1);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(ItemsTC.thaumiumHoe), ingot, 1);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(ItemsTC.thaumiumShovel), ingot, 1);
        
        SmelterManager.addRecycleRecipe(energy, new ItemStack(ItemsTC.thaumiumHelm), ingot, 2);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(ItemsTC.thaumiumChest), ingot, 4);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(ItemsTC.thaumiumLegs), ingot, 3);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(ItemsTC.thaumiumBoots), ingot, 2);
        
        // Void
        ingot = new ItemStack(ItemsTC.ingots, 1, 1);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(ItemsTC.voidSword), ingot, 1);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(ItemsTC.voidPick), ingot, 1);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(ItemsTC.voidAxe), ingot, 1);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(ItemsTC.voidHoe), ingot, 1);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(ItemsTC.voidShovel), ingot, 1);
        
        SmelterManager.addRecycleRecipe(energy, new ItemStack(ItemsTC.voidHelm), ingot, 2);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(ItemsTC.voidChest), ingot, 4);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(ItemsTC.voidLegs), ingot, 3);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(ItemsTC.voidBoots), ingot, 2);
        
        // Recycling stuff
        SmelterManager.addRecycleRecipe(energy, new ItemStack(ItemsTC.thaumometer), new ItemStack(Items.GOLD_INGOT), 2);
        
        
        // Sawmill
        TapperManager.addStandardMapping(new ItemStack(BlocksTC.logGreatwood), new FluidStack(TFFluids.fluidExperience, 25));
        TapperManager.addStandardMapping(new ItemStack(BlocksTC.logSilverwood), new FluidStack(TFFluids.fluidExperience, 25));
        
        energy = 1_500;
        SawmillManager.addRecycleRecipe(energy, new ItemStack(ItemsTC.clothChest), new ItemStack(ItemsTC.fabric), 4, true);
        SawmillManager.addRecycleRecipe(energy, new ItemStack(ItemsTC.clothLegs), new ItemStack(ItemsTC.fabric), 3, true);
        SawmillManager.addRecycleRecipe(energy, new ItemStack(ItemsTC.clothBoots), new ItemStack(ItemsTC.fabric), 2, true);
        
        SawmillManager.addRecycleRecipe(energy, new ItemStack(ItemsTC.golemBell), new ItemStack(Items.QUARTZ), 2);
        
        energy = 10_000;
        SawmillManager.addRecipe(energy, new ItemStack(BlocksTC.stairsGreatwood, 2), new ItemStack(BlocksTC.plankGreatwood), ItemMaterial.dustWood.copy(), 25);
        SawmillManager.addRecipe(energy, new ItemStack(BlocksTC.stairsSilverwood, 2), new ItemStack(BlocksTC.plankSilverwood), ItemMaterial.dustWood.copy(), 25);
    }
    
    @Nonnull
    @Override
    public String getModId()
    {
        return ModIds.THAUMCRAFT;
    }
}
