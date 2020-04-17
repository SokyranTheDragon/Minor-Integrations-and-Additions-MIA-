package com.github.sokyranthedragon.mia.integrations.mocreatures;

import com.buuz135.industrial.api.extractor.ExtractorEntry;
import com.buuz135.industrial.proxy.FluidsRegistry;
import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.integrations.industrialforegoing.IIndustrialForegoingIntegration;
import drzhark.mocreatures.init.MoCBlocks;
import drzhark.mocreatures.init.MoCItems;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

@MethodsReturnNonnullByDefault
class IndustrialForegoingMoCreaturesIntegration implements IIndustrialForegoingIntegration
{
    @Override
    public ItemStack[] getBasicProteinGeneratorEntries()
    {
        return new ItemStack[]
            {
                new ItemStack(MoCItems.rawTurkey),
                new ItemStack(MoCItems.cookedTurkey),
                new ItemStack(MoCItems.ratRaw),
                new ItemStack(MoCItems.ratCooked),
                new ItemStack(MoCItems.crabraw),
                new ItemStack(MoCItems.crabcooked),
                new ItemStack(MoCItems.ostrichraw),
                new ItemStack(MoCItems.ostrichcooked),
                new ItemStack(MoCItems.turtleraw),
                new ItemStack(MoCItems.heartdarkness),
                new ItemStack(MoCItems.heartfire),
                new ItemStack(MoCItems.heartundead),
                new ItemStack(MoCItems.rawTurkey)
            };
    }
    
    @Override
    public ExtractorEntry[] getLatexEntries()
    {
        return new ExtractorEntry[]
            {
                new ExtractorEntry(new ItemStack(MoCBlocks.mocLog, 1, 0), new FluidStack(FluidsRegistry.LATEX, 1)),
                new ExtractorEntry(new ItemStack(MoCBlocks.mocLog, 1, 1), new FluidStack(FluidsRegistry.LATEX, 1))
            };
    }
    
    @Override
    public ModIds getModId()
    {
        return ModIds.MO_CREATURES;
    }
}
