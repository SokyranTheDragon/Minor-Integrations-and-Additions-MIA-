package com.github.sokyranthedragon.mia.integrations.tconstruct;

import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.integrations.chisel.IChiselIntegration;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import org.apache.logging.log4j.util.TriConsumer;
import slimeknights.tconstruct.shared.TinkerCommons;
import slimeknights.tconstruct.shared.block.BlockClearStainedGlass;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.function.BiConsumer;

import static slimeknights.tconstruct.TConstruct.pulseManager;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
class ChiselTConstructIntegration implements IChiselIntegration
{
    @Override
    public void sendChiselMessages(BiConsumer<String, ItemStack[]> messageStackSender, TriConsumer<String, Block, Integer> messageBlockSender)
    {
        if (pulseManager.isPulseLoaded(TinkerCommons.PulseId))
        {
            messageBlockSender.accept("glass", TinkerCommons.blockClearGlass, 0);
            for (BlockClearStainedGlass.EnumGlassColor color : BlockClearStainedGlass.EnumGlassColor.values())
                messageBlockSender.accept("glassdyed" + color.getName(), TinkerCommons.blockClearStainedGlass, color.ordinal());
        }
    }
    
    @Override
    public ModIds getModId()
    {
        return ModIds.TINKERS_CONSTRUCT;
    }
}
