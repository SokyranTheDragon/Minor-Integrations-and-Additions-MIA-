package com.github.sokyranthedragon.mia.integrations.aether;

import com.gildedgames.the_aether.blocks.BlocksAether;
import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.integrations.futuremc.IFutureMcIntegration;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;

import static com.github.sokyranthedragon.mia.integrations.futuremc.FutureMc.addStonecutterRecipes;

@MethodsReturnNonnullByDefault
class FutureMcAetherIntegration implements IFutureMcIntegration
{
    @Override
    public void addRecipes()
    {
        addStonecutterRecipes(
            new ItemStack(BlocksAether.holystone),
            new ItemStack(BlocksAether.holystone_wall),
            new ItemStack(BlocksAether.holystone_stairs),
            new ItemStack(BlocksAether.holystone_slab, 2),
            new ItemStack(BlocksAether.holystone_brick),
            new ItemStack(BlocksAether.holystone_brick_wall),
            new ItemStack(BlocksAether.holystone_brick_slab, 2),
            new ItemStack(BlocksAether.holystone_stairs));
        addStonecutterRecipes(
            new ItemStack(BlocksAether.holystone_brick),
            new ItemStack(BlocksAether.holystone_brick_wall),
            new ItemStack(BlocksAether.holystone_brick_slab, 2),
            new ItemStack(BlocksAether.holystone_stairs));
        addStonecutterRecipes(
            new ItemStack(BlocksAether.mossy_holystone),
            new ItemStack(BlocksAether.mossy_holystone_wall),
            new ItemStack(BlocksAether.mossy_holystone_slab, 2),
            new ItemStack(BlocksAether.mossy_holystone_stairs));
        addStonecutterRecipes(
            new ItemStack(BlocksAether.dungeon_block, 1, 0),
            new ItemStack(BlocksAether.carved_wall),
            new ItemStack(BlocksAether.carved_slab, 2),
            new ItemStack(BlocksAether.carved_stairs));
        addStonecutterRecipes(
            new ItemStack(BlocksAether.dungeon_block, 1, 2),
            new ItemStack(BlocksAether.angelic_wall),
            new ItemStack(BlocksAether.angelic_slab, 2),
            new ItemStack(BlocksAether.angelic_stairs));
        addStonecutterRecipes(
            new ItemStack(BlocksAether.dungeon_block, 1, 4),
            new ItemStack(BlocksAether.hellfire_wall),
            new ItemStack(BlocksAether.hellfire_slab, 2),
            new ItemStack(BlocksAether.hellfire_stairs));
    }
    
    @Override
    public IBlockState[] registerPollinationFlowers()
    {
        return new IBlockState[]
            {
                BlocksAether.purple_flower.getDefaultState(),
                BlocksAether.white_flower.getDefaultState()
            };
    }
    
    @Override
    public ModIds getModId()
    {
        return ModIds.AETHER;
    }
}
