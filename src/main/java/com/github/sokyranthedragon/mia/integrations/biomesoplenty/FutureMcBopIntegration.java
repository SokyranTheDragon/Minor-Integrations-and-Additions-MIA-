package com.github.sokyranthedragon.mia.integrations.biomesoplenty;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.api.item.BOPItems;
import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.integrations.futuremc.IFutureMcIntegration;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.item.ItemStack;

import static thedarkcolour.futuremc.block.BlockFurnaceAdvanced.Recipes.blastFurnaceRecipe;
import static thedarkcolour.futuremc.recipe.StonecutterRecipes.addOrCreateRecipe;

@MethodsReturnNonnullByDefault
class FutureMcBopIntegration implements IFutureMcIntegration
{
    @Override
    public void addRecipes()
    {
        for (int meta = 0; meta <= 7; meta++)
            blastFurnaceRecipe(new ItemStack(BOPBlocks.gem_ore, 1, meta), new ItemStack(BOPItems.gem, 1, meta));
        
        addOrCreateRecipe(new ItemStack(BOPBlocks.white_sandstone, 1, 0),
                new ItemStack(BOPBlocks.white_sandstone, 1, 1),
                new ItemStack(BOPBlocks.white_sandstone, 1, 1),
                new ItemStack(BOPBlocks.white_sandstone_stairs, 1),
                new ItemStack(BOPBlocks.other_slab, 2, 1));
        addOrCreateRecipe(new ItemStack(BOPBlocks.mud_brick_block),
                new ItemStack(BOPBlocks.mud_brick_stairs),
                new ItemStack(BOPBlocks.other_slab, 2, 0));
    }
    
    @Override
    public ModIds getModId()
    {
        return ModIds.BIOMES_O_PLENTY;
    }
}
