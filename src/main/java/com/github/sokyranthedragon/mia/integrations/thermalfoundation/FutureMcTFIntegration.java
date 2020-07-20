package com.github.sokyranthedragon.mia.integrations.thermalfoundation;

import cofh.thermalfoundation.block.BlockOre;
import cofh.thermalfoundation.init.TFBlocks;
import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.integrations.futuremc.FutureMc;
import com.github.sokyranthedragon.mia.integrations.futuremc.IFutureMcIntegration;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;

import javax.annotation.Nonnull;

class FutureMcTFIntegration implements IFutureMcIntegration
{
    @Override
    public void addRecipes()
    {
        for (BlockOre.Type value : BlockOre.VARIANT.getAllowedValues())
        {
            ItemStack ore = new ItemStack(TFBlocks.blockOre, 1, value.getMetadata());
            ItemStack result = FurnaceRecipes.instance().getSmeltingResult(ore);
            
            if (!result.isEmpty())
                FutureMc.addBlastFurnaceRecipe(ore, result);
        }
    }
    
    @Override
    @Nonnull
    public ModIds getModId()
    {
        return ModIds.THERMAL_FOUNDATION;
    }
}
