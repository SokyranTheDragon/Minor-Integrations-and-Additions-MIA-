package com.github.sokyranthedragon.mia.integrations.dungeontactics;

import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.integrations.chisel.IChiselIntegration;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import org.apache.logging.log4j.util.TriConsumer;
import pegbeard.dungeontactics.handlers.DTBlocks;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.function.BiConsumer;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
class ChiselDungeonTacticsIntegration implements IChiselIntegration
{
    @Override
    public void sendChiselMessages(BiConsumer<String, ItemStack[]> messageStackSender, TriConsumer<String, Block, Integer> messageBlockSender)
    {
        messageStackSender.accept("obsidian", new ItemStack[]{ new ItemStack(DTBlocks.OBSIDIAN_BRICK) });
    }
    
    @Override
    public ModIds getModId()
    {
        return ModIds.DUNGEON_TACTICS;
    }
}
