package com.github.sokyranthedragon.mia.integrations.iceandfire;

import com.github.alexthe666.iceandfire.block.IafBlockRegistry;
import com.github.alexthe666.iceandfire.item.IafItemRegistry;
import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.integrations.futuremc.IFutureMcIntegration;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

import static com.github.sokyranthedragon.mia.integrations.futuremc.FutureMc.addBlastFurnaceRecipe;
import static com.github.sokyranthedragon.mia.integrations.futuremc.FutureMc.addStonecutterRecipes;

class FutureMcIceAndFireIntegration implements IFutureMcIntegration
{
    @Override
    public void addRecipes()
    {
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
    public IBlockState[] registerPollinationFlowers()
    {
        return new IBlockState[]
            {
                IafBlockRegistry.fire_lily.getDefaultState(),
                IafBlockRegistry.frost_lily.getDefaultState()
            };
    }
    
    @Override
    @Nonnull
    public ModIds getModId()
    {
        return ModIds.ICE_AND_FIRE;
    }
}
