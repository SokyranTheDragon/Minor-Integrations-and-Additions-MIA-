package com.github.sokyranthedragon.mia.integrations.xu2;

import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.integrations.chisel.IChiselIntegration;
import com.rwtema.extrautils2.backend.entries.XU2Entries;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import org.apache.logging.log4j.util.TriConsumer;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.function.BiConsumer;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
class ChiselExtraUtilsIntegration implements IChiselIntegration
{
    @Override
    public void sendChiselMessages(BiConsumer<String, ItemStack[]> messageStackSender, TriConsumer<String, Block, Integer> messageBlockSender)
    {
        messageStackSender.accept("stonebrick", new ItemStack[]
                {
                        XU2Entries.decorativeSolid.newStackMeta(0),
                        XU2Entries.decorativeSolid.newStackMeta(1),
                        XU2Entries.decorativeSolid.newStackMeta(2),
                        XU2Entries.decorativeSolid.newStackMeta(5)
                });
        messageStackSender.accept("glass", new ItemStack[]
                {
                        XU2Entries.decorativeGlass.newStackMeta(0),
                        XU2Entries.decorativeGlass.newStackMeta(1),
                        XU2Entries.decorativeGlass.newStackMeta(2)
                });
    }
    
    @Override
    public ModIds getModId()
    {
        return ModIds.EXTRA_UTILITIES;
    }
}
