package com.github.sokyranthedragon.mia.integrations.iceandfire;

import com.github.alexthe666.iceandfire.enums.EnumSkullType;
import com.github.alexthe666.iceandfire.item.IafItemRegistry;
import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.integrations.industrialforegoing.IIndustrialForegoingIntegration;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.item.ItemStack;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@MethodsReturnNonnullByDefault
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
