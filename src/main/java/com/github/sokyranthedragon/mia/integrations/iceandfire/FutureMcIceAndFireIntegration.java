package com.github.sokyranthedragon.mia.integrations.iceandfire;

import com.github.alexthe666.iceandfire.block.IafBlockRegistry;
import com.github.alexthe666.iceandfire.item.IafItemRegistry;
import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.integrations.futuremc.IFutureMcIntegration;
import net.minecraft.item.ItemStack;
import thedarkcolour.futuremc.block.BlockFurnaceAdvanced;

import javax.annotation.Nonnull;

class FutureMcIceAndFireIntegration implements IFutureMcIntegration
{
    @Override
    public void addRecipes()
    {
        BlockFurnaceAdvanced.Recipes.blastFurnaceRecipe(new ItemStack(IafBlockRegistry.silverOre), new ItemStack(IafItemRegistry.silverIngot));
        BlockFurnaceAdvanced.Recipes.blastFurnaceRecipe(new ItemStack(IafBlockRegistry.sapphireOre), new ItemStack(IafItemRegistry.sapphireGem));
    }
    
    @Override
    @Nonnull
    public ModIds getModId()
    {
        return ModIds.ICE_AND_FIRE;
    }
}
