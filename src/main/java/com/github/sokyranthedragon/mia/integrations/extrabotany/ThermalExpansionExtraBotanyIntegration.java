package com.github.sokyranthedragon.mia.integrations.extrabotany;

import cofh.thermalexpansion.util.managers.device.FactorizerManager;
import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.integrations.thermalexpansion.IThermalExpansionIntegration;
import com.meteor.extrabotany.common.block.ModBlocks;
import com.meteor.extrabotany.common.item.ModItems;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

class ThermalExpansionExtraBotanyIntegration implements IThermalExpansionIntegration
{
    @Override
    public void addRecipes()
    {
        // Factorizer
        FactorizerManager.addDefaultRecipe(new ItemStack(ModItems.material, 1, 1), new ItemStack(ModBlocks.orichalcosblock));
        FactorizerManager.addDefaultRecipe(new ItemStack(ModItems.material, 1, 5), new ItemStack(ModBlocks.shadowium));
        FactorizerManager.addDefaultRecipe(new ItemStack(ModItems.material, 1, 8), new ItemStack(ModBlocks.photonium));
    }
    
    @Nonnull
    @Override
    public ModIds getModId()
    {
        return ModIds.EXTRABOTANY;
    }
}
