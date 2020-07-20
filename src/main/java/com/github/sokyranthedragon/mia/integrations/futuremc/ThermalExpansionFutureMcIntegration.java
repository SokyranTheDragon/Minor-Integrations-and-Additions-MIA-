package com.github.sokyranthedragon.mia.integrations.futuremc;

import cofh.thermalexpansion.util.managers.device.FactorizerManager;
import cofh.thermalexpansion.util.managers.machine.EnchanterManager;
import cofh.thermalexpansion.util.managers.machine.InsolatorManager;
import cofh.thermalexpansion.util.managers.machine.PrecipitatorManager;
import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.integrations.thermalexpansion.IThermalExpansionIntegration;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import thedarkcolour.futuremc.registry.FBlocks;
import thedarkcolour.futuremc.registry.FItems;

import javax.annotation.Nonnull;

class ThermalExpansionFutureMcIntegration implements IThermalExpansionIntegration
{
    @Override
    public void addRecipes()
    {
        // Glacial Precipitator
        PrecipitatorManager.addRecipe(1_600 * 9, new ItemStack(FBlocks.INSTANCE.getBLUE_ICE()), new FluidStack(FluidRegistry.WATER, 10_000));
        
        // Phytogenic Insolator
        InsolatorManager.addDefaultRecipe(new ItemStack(FItems.INSTANCE.getBAMBOO()), new ItemStack(FItems.INSTANCE.getBAMBOO(), 3), ItemStack.EMPTY, 0);
        InsolatorManager.addDefaultRecipe(new ItemStack(FBlocks.INSTANCE.getLILY_OF_THE_VALLEY()), new ItemStack(FBlocks.INSTANCE.getLILY_OF_THE_VALLEY(), 2), ItemStack.EMPTY, 0);
        InsolatorManager.addDefaultRecipe(new ItemStack(FBlocks.INSTANCE.getCORNFLOWER()), new ItemStack(FBlocks.INSTANCE.getCORNFLOWER(), 2), ItemStack.EMPTY, 0);
        InsolatorManager.addDefaultRecipe(new ItemStack(FBlocks.INSTANCE.getWITHER_ROSE()), new ItemStack(FBlocks.INSTANCE.getWITHER_ROSE(), 2), ItemStack.EMPTY, 0);
        
        // Factorizer
        FactorizerManager.addDefaultRecipe(new ItemStack(FItems.INSTANCE.getHONEYCOMB()), new ItemStack(FBlocks.INSTANCE.getHONEYCOMB_BLOCK()), 4);
        FactorizerManager.addDefaultRecipe(new ItemStack(FItems.INSTANCE.getHONEY_BOTTLE()), new ItemStack(FBlocks.INSTANCE.getHONEY_BLOCK()), 4);
        
        // Arcane Enscroller
        EnchanterManager.addDefaultEnchantmentRecipe(new ItemStack(FItems.INSTANCE.getBAMBOO(), 64), "futuremc:impaling", 0);
        EnchanterManager.addDefaultEnchantmentRecipe(new ItemStack(FBlocks.INSTANCE.getWITHER_ROSE(), 32), "futuremc:channeling", 3);
        EnchanterManager.addDefaultEnchantmentRecipe(new ItemStack(FBlocks.INSTANCE.getHONEY_BLOCK(), 8), "futuremc:loyalty", 2);
        EnchanterManager.addDefaultEnchantmentRecipe(new ItemStack(FBlocks.INSTANCE.getBLUE_ICE(), 1), "futuremc:riptide", 3);
    }
    
    @Nonnull
    @Override
    public ModIds getModId()
    {
        return ModIds.FUTURE_MC;
    }
}
