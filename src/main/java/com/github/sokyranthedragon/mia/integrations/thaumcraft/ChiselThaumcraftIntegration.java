package com.github.sokyranthedragon.mia.integrations.thaumcraft;

import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.integrations.chisel.IChiselIntegration;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import org.apache.logging.log4j.util.TriConsumer;
import thaumcraft.api.blocks.BlocksTC;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.function.BiConsumer;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
class ChiselThaumcraftIntegration implements IChiselIntegration
{
    @Override
    public void sendChiselMessages(BiConsumer<String, ItemStack[]> messageStackSender, TriConsumer<String, Block, Integer> messageBlockSender)
    {
        messageStackSender.accept("amber_block", new ItemStack[]
                {
                        new ItemStack(BlocksTC.amberBlock),
                        new ItemStack(BlocksTC.amberBrick)
                });
    }
    
    @Override
    public ModIds getModId()
    {
        return ModIds.THAUMCRAFT;
    }
}
