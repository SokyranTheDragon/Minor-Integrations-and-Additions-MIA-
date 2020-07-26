package com.github.sokyranthedragon.mia.integrations.biomesoplenty;

import biomesoplenty.api.block.BOPBlocks;
import com.buuz135.industrial.api.extractor.ExtractorEntry;
import com.buuz135.industrial.proxy.FluidsRegistry;
import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.integrations.industrialforegoing.IIndustrialForegoingIntegration;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import org.apache.logging.log4j.util.TriConsumer;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.ArrayList;
import java.util.List;

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
    public ExtractorEntry[] getLatexEntries()
    {
        List<ExtractorEntry> items = new ArrayList<>();
        
        for (int meta = 4; meta <= 7; meta++)
        {
            items.add(new ExtractorEntry(new ItemStack(BOPBlocks.log_0, 1, meta), new FluidStack(FluidsRegistry.LATEX, 1)));
            items.add(new ExtractorEntry(new ItemStack(BOPBlocks.log_1, 1, meta), new FluidStack(FluidsRegistry.LATEX, 1)));
            items.add(new ExtractorEntry(new ItemStack(BOPBlocks.log_2, 1, meta), new FluidStack(FluidsRegistry.LATEX, 1)));
            items.add(new ExtractorEntry(new ItemStack(BOPBlocks.log_3, 1, meta), new FluidStack(FluidsRegistry.LATEX, 1)));
            if (meta <= 5)
                items.add(new ExtractorEntry(new ItemStack(BOPBlocks.log_4, 1, meta), new FluidStack(FluidsRegistry.LATEX, 1), 0.15f));
        }
        
        return items.toArray(new ExtractorEntry[0]);
    }
    
    @Override
    public ModIds getModId()
    {
        return ModIds.BIOMES_O_PLENTY;
    }
}
