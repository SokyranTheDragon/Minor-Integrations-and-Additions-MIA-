package com.github.sokyranthedragon.mia.integrations.chisel;

import com.github.sokyranthedragon.mia.integrations.base.IModIntegration;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import org.apache.logging.log4j.util.TriConsumer;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.function.BiConsumer;

@ParametersAreNonnullByDefault
public interface IChiselIntegration extends IModIntegration
{
    void sendChiselMessages(BiConsumer<String, ItemStack[]> messageStackSender, TriConsumer<String, Block, Integer> messageBlockSender);
}
