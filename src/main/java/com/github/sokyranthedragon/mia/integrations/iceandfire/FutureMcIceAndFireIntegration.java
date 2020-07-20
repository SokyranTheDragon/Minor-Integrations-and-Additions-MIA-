package com.github.sokyranthedragon.mia.integrations.iceandfire;

import com.github.alexthe666.iceandfire.block.IafBlockRegistry;
import com.github.alexthe666.iceandfire.item.IafItemRegistry;
import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.integrations.futuremc.IFutureMcIntegration;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

import static com.github.sokyranthedragon.mia.integrations.futuremc.FutureMc.addBlastFurnaceRecipe;
import static com.github.sokyranthedragon.mia.integrations.futuremc.FutureMc.addStonecutterRecipes;

class FutureMcIceAndFireIntegration implements IFutureMcIntegration
{
    @Override
    public void addRecipes()
    {
        addBlastFurnaceRecipe(new ItemStack(IafBlockRegistry.silverOre), new ItemStack(IafItemRegistry.silverIngot));
        addBlastFurnaceRecipe(new ItemStack(IafBlockRegistry.sapphireOre), new ItemStack(IafItemRegistry.sapphireGem));
        
        addStonecutterRecipes(new ItemStack(IafBlockRegistry.dread_stone),
            new ItemStack(IafBlockRegistry.dread_stone_bricks),
            new ItemStack(IafBlockRegistry.dread_stone_bricks_chiseled),
            new ItemStack(IafBlockRegistry.dread_stone_tile),
            new ItemStack(IafBlockRegistry.dread_stone_bricks_stairs),
            new ItemStack(IafBlockRegistry.dread_stone_bricks_slab, 2));
        addStonecutterRecipes(new ItemStack(IafBlockRegistry.dread_stone_bricks),
            new ItemStack(IafBlockRegistry.dread_stone_bricks_chiseled),
            new ItemStack(IafBlockRegistry.dread_stone_tile),
            new ItemStack(IafBlockRegistry.dread_stone_bricks_stairs),
            new ItemStack(IafBlockRegistry.dread_stone_bricks_slab, 2));
    }
    
    @Override
    @Nonnull
    public ModIds getModId()
    {
        return ModIds.ICE_AND_FIRE;
    }
}
