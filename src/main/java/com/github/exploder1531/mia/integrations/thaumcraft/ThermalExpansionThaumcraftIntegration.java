package com.github.exploder1531.mia.integrations.thaumcraft;

import cofh.thermalexpansion.util.managers.machine.SawmillManager;
import cofh.thermalexpansion.util.managers.machine.SmelterManager;
import cofh.thermalfoundation.item.ItemMaterial;
import com.github.exploder1531.mia.integrations.ModIds;
import com.github.exploder1531.mia.integrations.thermalexpansion.IThermalExpansionIntegration;
import net.minecraft.item.ItemStack;
import thaumcraft.api.blocks.BlocksTC;
import thaumcraft.api.items.ItemsTC;

import javax.annotation.Nonnull;

class ThermalExpansionThaumcraftIntegration implements IThermalExpansionIntegration
{
    @Override
    public void addRecipes()
    {
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
        
        
        // Sawmill
        energy = 1_500;
        SawmillManager.addRecycleRecipe(energy, new ItemStack(ItemsTC.clothChest), new ItemStack(ItemsTC.fabric), 4, true);
        SawmillManager.addRecycleRecipe(energy, new ItemStack(ItemsTC.clothLegs), new ItemStack(ItemsTC.fabric), 3, true);
        SawmillManager.addRecycleRecipe(energy, new ItemStack(ItemsTC.clothBoots), new ItemStack(ItemsTC.fabric), 2, true);
        
        energy = 2_000;
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
