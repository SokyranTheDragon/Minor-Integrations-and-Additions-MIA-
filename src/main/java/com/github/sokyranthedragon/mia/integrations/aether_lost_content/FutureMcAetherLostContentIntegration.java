package com.github.sokyranthedragon.mia.integrations.aether_lost_content;

import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.integrations.futuremc.IFutureMcIntegration;
import com.legacy.lostaether.blocks.BlocksLostAether;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.item.ItemStack;

import static com.github.sokyranthedragon.mia.integrations.futuremc.FutureMc.addStonecutterRecipes;

@MethodsReturnNonnullByDefault
class FutureMcAetherLostContentIntegration implements IFutureMcIntegration
{
    @Override
    public void addRecipes()
    {
        addStonecutterRecipes(
            new ItemStack(BlocksLostAether.gale_stone, 1, 4),
            new ItemStack(BlocksLostAether.gale_wall),
            new ItemStack(BlocksLostAether.gale_slab, 2),
            new ItemStack(BlocksLostAether.gale_stairs));
    }
    
    @Override
    public ModIds getModId()
    {
        return ModIds.AETHER_LOST_CONTENT;
    }
}
