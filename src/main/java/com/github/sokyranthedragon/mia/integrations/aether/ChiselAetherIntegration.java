package com.github.sokyranthedragon.mia.integrations.aether;

import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.integrations.chisel.IChiselIntegration;
import com.legacy.aether.blocks.BlocksAether;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import org.apache.logging.log4j.util.TriConsumer;
import team.chisel.common.config.Configurations;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.function.BiConsumer;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
class ChiselAetherIntegration implements IChiselIntegration
{
    @Override
    public void sendChiselMessages(BiConsumer<String, ItemStack[]> messageStackSender, TriConsumer<String, Block, Integer> messageBlockSender)
    {
        messageBlockSender.accept("holystone", BlocksAether.holystone, 0);
        if (Configurations.allowMossy)
            messageBlockSender.accept("holystone", BlocksAether.mossy_holystone, 0);
        messageBlockSender.accept("holystone", BlocksAether.holystone_brick, 0);
        messageBlockSender.accept("holystone", BlocksAether.holystone, 0);
        messageBlockSender.accept("holystone", BlocksAether.holystone, 0);
    }
    
    @Override
    public ModIds getModId()
    {
        return ModIds.AETHER;
    }
}
