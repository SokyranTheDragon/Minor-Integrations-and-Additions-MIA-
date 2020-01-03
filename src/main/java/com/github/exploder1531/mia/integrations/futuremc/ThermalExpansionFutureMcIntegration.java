package com.github.exploder1531.mia.integrations.futuremc;

import cofh.thermalexpansion.util.managers.device.FactorizerManager;
import cofh.thermalexpansion.util.managers.machine.InsolatorManager;
import cofh.thermalexpansion.util.managers.machine.PrecipitatorManager;
import com.github.exploder1531.mia.integrations.ModIds;
import com.github.exploder1531.mia.integrations.thermalexpansion.IThermalExpansionIntegration;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import thedarkcolour.futuremc.init.Init;

import javax.annotation.Nonnull;

class ThermalExpansionFutureMcIntegration implements IThermalExpansionIntegration
{
    @Override
    public void addRecipes()
    {
        // Glacial Precipitator
        PrecipitatorManager.addRecipe(1_600 * 9, new ItemStack(Init.BLUE_ICE), new FluidStack(FluidRegistry.WATER, 10_000));
        
        // Phytogenic Insolator
        InsolatorManager.addDefaultRecipe(new ItemStack(Init.BAMBOO_ITEM), new ItemStack(Init.BAMBOO_ITEM, 3), ItemStack.EMPTY, 0);
        InsolatorManager.addDefaultRecipe(new ItemStack(Init.LILY_OF_VALLEY), new ItemStack(Init.LILY_OF_VALLEY, 2), ItemStack.EMPTY, 0);
        InsolatorManager.addDefaultRecipe(new ItemStack(Init.CORNFLOWER), new ItemStack(Init.CORNFLOWER, 2), ItemStack.EMPTY, 0);
        InsolatorManager.addDefaultRecipe(new ItemStack(Init.WITHER_ROSE), new ItemStack(Init.WITHER_ROSE, 2), ItemStack.EMPTY, 0);
        
        // Factorizer
        FactorizerManager.addDefaultRecipe(new ItemStack(Init.HONEY_COMB), new ItemStack(Init.HONEYCOMB_BLOCK), 4);
        FactorizerManager.addDefaultRecipe(new ItemStack(Init.HONEY_BOTTLE), new ItemStack(Init.HONEY_BLOCK), 4);
    }
    
    @Nonnull
    @Override
    public ModIds getModId()
    {
        return ModIds.FUTURE_MC;
    }
}
