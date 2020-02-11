package com.github.sokyranthedragon.mia.integrations.biomesoplenty;

import biomesoplenty.api.block.BOPBlocks;
import com.github.sokyranthedragon.mia.block.decorative.SandstoneEntry;
import com.github.sokyranthedragon.mia.core.MiaBlocks;
import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.integrations.chisel.IChiselIntegration;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import org.apache.logging.log4j.util.TriConsumer;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.function.BiConsumer;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
class ChiselBopIntegration implements IChiselIntegration
{
    @Override
    public void sendChiselMessages(BiConsumer<String, ItemStack[]> messageStackSender, TriConsumer<String, Block, Integer> messageBlockSender)
    {
        messageStackSender.accept("sandstonewhite", new ItemStack[]
                {
                        new ItemStack(BOPBlocks.white_sandstone, 1, 0),
                        new ItemStack(BOPBlocks.white_sandstone, 1, 1),
                        new ItemStack(BOPBlocks.white_sandstone, 1, 2)
                });
        
        SandstoneEntry.registerChiselRecipes(MiaBlocks.whiteSandstone, "sandstonewhite", messageStackSender);
    }
    
    @Override
    public ModIds getModId()
    {
        return ModIds.BIOMES_O_PLENTY;
    }
}
