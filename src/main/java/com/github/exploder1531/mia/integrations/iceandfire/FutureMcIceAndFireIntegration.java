package com.github.exploder1531.mia.integrations.iceandfire;

import com.github.alexthe666.iceandfire.core.ModBlocks;
import com.github.alexthe666.iceandfire.core.ModItems;
import com.github.exploder1531.mia.integrations.ModIds;
import com.github.exploder1531.mia.integrations.futuremc.IFutureMcIntegration;
import net.minecraft.item.ItemStack;
import thedarkcolour.futuremc.block.BlockFurnaceAdvanced;

import javax.annotation.Nonnull;

class FutureMcIceAndFireIntegration implements IFutureMcIntegration
{
    @Override
    public void addRecipes()
    {
        BlockFurnaceAdvanced.Recipes.blastFurnaceRecipe(new ItemStack(ModBlocks.silverOre), new ItemStack(ModItems.silverIngot));
        BlockFurnaceAdvanced.Recipes.blastFurnaceRecipe(new ItemStack(ModBlocks.sapphireOre), new ItemStack(ModItems.sapphireGem));
    }
    
    @Override
    @Nonnull
    public ModIds getModId()
    {
        return ModIds.ICE_AND_FIRE;
    }
}
