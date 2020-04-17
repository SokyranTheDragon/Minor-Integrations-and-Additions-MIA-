package com.github.sokyranthedragon.mia.integrations.thaumcraft;

import com.buuz135.industrial.api.extractor.ExtractorEntry;
import com.buuz135.industrial.proxy.FluidsRegistry;
import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.integrations.industrialforegoing.IIndustrialForegoingIntegration;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import thaumcraft.api.blocks.BlocksTC;
import thaumcraft.api.items.ItemsTC;

@MethodsReturnNonnullByDefault
class IndustrialForegoingThaumcraftIntegration implements IIndustrialForegoingIntegration
{
    @Override
    public ItemStack[] getBasicProteinGeneratorEntries()
    {
        return new ItemStack[]
            {
                new ItemStack(ItemsTC.brain),
                new ItemStack(ItemsTC.tripleMeatTreat)
            };
    }
    
    @Override
    public ExtractorEntry[] getLatexEntries()
    {
        return new ExtractorEntry[]
            {
                new ExtractorEntry(new ItemStack(BlocksTC.logSilverwood), new FluidStack(FluidsRegistry.LATEX, 1)),
                new ExtractorEntry(new ItemStack(BlocksTC.logGreatwood), new FluidStack(FluidsRegistry.LATEX, 1))
            };
    }
    
    @Override
    public boolean loadLaserDrillEntries()
    {
        return false;
    }
    
    @Override
    public ModIds getModId()
    {
        return ModIds.THAUMCRAFT;
    }
}
