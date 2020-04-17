package com.github.sokyranthedragon.mia.integrations.natura;

import com.buuz135.industrial.api.extractor.ExtractorEntry;
import com.buuz135.industrial.proxy.FluidsRegistry;
import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.integrations.industrialforegoing.IIndustrialForegoingIntegration;
import com.progwml6.natura.nether.NaturaNether;
import com.progwml6.natura.overworld.NaturaOverworld;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import java.util.ArrayList;
import java.util.List;

import static com.progwml6.natura.Natura.pulseManager;

@MethodsReturnNonnullByDefault
class IndustrialForegoingNaturaIntegration implements IIndustrialForegoingIntegration
{
    @Override
    public ExtractorEntry[] getLatexEntries()
    {
        List<ItemStack> entries = new ArrayList<>();
        
        for (int meta = 0; meta <= 3; meta++)
        {
            if (pulseManager.isPulseLoaded(NaturaOverworld.PulseId))
            {
                entries.add(new ItemStack(NaturaOverworld.overworldLog, 1, meta));
                entries.add(new ItemStack(NaturaOverworld.overworldLog2, 1, meta));
                if (meta <= 2)
                    entries.add(new ItemStack(NaturaOverworld.redwoodLog, 1, meta));
            }
            if (meta <= 2 && pulseManager.isPulseLoaded(NaturaNether.PulseId))
                entries.add(new ItemStack(NaturaNether.netherLog, 1, meta));
        }
        
        if (pulseManager.isPulseLoaded(NaturaNether.PulseId))
        {
            entries.add(new ItemStack(NaturaNether.netherLog2, 1, 0));
            entries.add(new ItemStack(NaturaNether.netherLog2, 1, 15));
        }
        
        return entries.stream().map(entry -> new ExtractorEntry(entry, new FluidStack(FluidsRegistry.LATEX, 1))).toArray(ExtractorEntry[]::new);
    }
    
    @Override
    public ModIds getModId()
    {
        return ModIds.NATURA;
    }
}
