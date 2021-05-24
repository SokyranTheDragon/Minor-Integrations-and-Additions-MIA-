package com.github.sokyranthedragon.mia.integrations.biomesoplenty;

import biomesoplenty.api.block.BOPBlocks;
import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.integrations.industrialforegoing.IIndustrialForegoingIntegration;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.item.ItemStack;
import org.apache.logging.log4j.util.TriConsumer;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
class IndustrialForegoingBopIntegration implements IIndustrialForegoingIntegration
{
    @Override
    public void addFrosterRecipe(@Nonnull TriConsumer<String, ItemStack, Integer> frosterEntry)
    {
        frosterEntry.accept("HARDENED_ICE", new ItemStack(BOPBlocks.hard_ice), 1000);
    }
    
    @Override
    public ModIds getModId()
    {
        return ModIds.BIOMES_O_PLENTY;
    }
}
