package com.github.sokyranthedragon.mia.integrations.iceandfire;

import com.github.alexthe666.iceandfire.block.IafBlockRegistry;
import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.integrations.chisel.IChiselIntegration;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import org.apache.logging.log4j.util.TriConsumer;
import team.chisel.common.config.Configurations;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.function.BiConsumer;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
class ChiselIceAndFireIntegration implements IChiselIntegration
{
    @Override
    public void sendChiselMessages(BiConsumer<String, ItemStack[]> messageStackSender, TriConsumer<String, Block, Integer> messageBlockSender)
    {
        messageStackSender.accept("dreadstone", new ItemStack[]
            {
                new ItemStack(IafBlockRegistry.dread_stone),
                new ItemStack(IafBlockRegistry.dread_stone_bricks),
                new ItemStack(IafBlockRegistry.dread_stone_bricks_chiseled),
                new ItemStack(IafBlockRegistry.dread_stone_bricks_cracked)
            });
        if (Configurations.allowSmoothStone)
            messageBlockSender.accept("dreadstone", IafBlockRegistry.dread_stone_tile, 0);
        if (Configurations.allowMossy)
            messageBlockSender.accept("dreadstone", IafBlockRegistry.dread_stone_bricks_mossy, 0);
    }
    
    @Override
    public ModIds getModId()
    {
        return ModIds.ICE_AND_FIRE;
    }
}
