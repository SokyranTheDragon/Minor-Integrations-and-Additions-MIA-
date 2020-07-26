package com.github.sokyranthedragon.mia.integrations.iceandfire;

import com.buuz135.industrial.api.extractor.ExtractorEntry;
import com.buuz135.industrial.proxy.FluidsRegistry;
import com.github.alexthe666.iceandfire.block.IafBlockRegistry;
import com.github.alexthe666.iceandfire.enums.EnumSkullType;
import com.github.alexthe666.iceandfire.item.IafItemRegistry;
import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.integrations.industrialforegoing.IIndustrialForegoingIntegration;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import org.apache.logging.log4j.util.TriConsumer;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
class IndustrialForegoingIceAndFireIntegration implements IIndustrialForegoingIntegration
{
    @Override
    public ItemStack[] getBasicProteinGeneratorEntries()
    {
        List<ItemStack> items = Arrays.stream(EnumSkullType.values()).map(e -> new ItemStack(e.skull_item)).collect(Collectors.toList());
        
        items.add(new ItemStack(IafItemRegistry.ice_dragon_flesh));
        items.add(new ItemStack(IafItemRegistry.ice_dragon_heart));
        items.add(new ItemStack(IafItemRegistry.fire_dragon_flesh));
        items.add(new ItemStack(IafItemRegistry.fire_dragon_heart));
        items.add(new ItemStack(IafItemRegistry.dragon_skull, 1, 0));
        items.add(new ItemStack(IafItemRegistry.dragon_skull, 1, 1));
        
        return items.toArray(new ItemStack[0]);
    }
    
    @Override
    public void addFrosterRecipe(TriConsumer<String, ItemStack, Integer> frosterEntry)
    {
        frosterEntry.accept("DRAGON_ICE", new ItemStack(IafBlockRegistry.dragon_ice), 4000);
        frosterEntry.accept("DRAGON_ICE", new ItemStack(IafBlockRegistry.dragon_ice_spikes), 4000);
    }
    
    @Override
    public ExtractorEntry[] getLatexEntries()
    {
        return new ExtractorEntry[] { new ExtractorEntry(new ItemStack(IafBlockRegistry.dreadwood_log), new FluidStack(FluidsRegistry.LATEX, 1)) };
    }
    
    @Override
    public boolean loadLaserDrillEntries()
    {
        return true;
    }
    
    @Override
    public ModIds getModId()
    {
        return ModIds.ICE_AND_FIRE;
    }
}
