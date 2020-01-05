package com.github.exploder1531.mia.integrations.thermalfoundation;

import cofh.thermalfoundation.block.BlockOre;
import cofh.thermalfoundation.init.TFBlocks;
import com.github.exploder1531.mia.integrations.ModIds;
import com.github.exploder1531.mia.integrations.futuremc.IFutureMcIntegration;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import thedarkcolour.futuremc.block.BlockFurnaceAdvanced;

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
                BlockFurnaceAdvanced.Recipes.blastFurnaceRecipe(ore, result);
        }
    }
    
    @Override
    @Nonnull
    public ModIds getModId()
    {
        return ModIds.THERMAL_FOUNDATION;
    }
}
