package com.github.exploder1531.mia.integrations.harvestcraft;

import cofh.thermalexpansion.util.managers.device.FactorizerManager;
import com.github.exploder1531.mia.integrations.ModIds;
import com.github.exploder1531.mia.integrations.thermalexpansion.IThermalExpansionIntegration;
import com.pam.harvestcraft.blocks.BlockRegistry;
import com.pam.harvestcraft.item.ItemRegistry;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

class ThermalExpansionHarvestcraftIntegration implements IThermalExpansionIntegration
{
    @Override
    public void addRecipes()
    {
        FactorizerManager.addDefaultRecipe(new ItemStack(ItemRegistry.beeswaxItem), new ItemStack(BlockRegistry.pressedwax));
    }
    
    @Nonnull
    @Override
    public String getModId()
    {
        return ModIds.HARVESTCRAFT;
    }
}
