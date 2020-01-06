package com.github.exploder1531.mia.integrations.thermalexpansion;

import cofh.thermalexpansion.init.TEBlocks;
import cofh.thermalexpansion.init.TEItems;
import com.github.exploder1531.mia.integrations.ModIds;
import com.github.exploder1531.mia.integrations.quark.IQuarkIntegration;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.item.ItemStack;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

@MethodsReturnNonnullByDefault
class QuarkTEIntegration implements IQuarkIntegration
{
    @Override
    public Collection<String> getAllowedAncientTomeEnchants()
    {
        return new HashSet<>();
    }
    
    @Override
    public Collection<ItemStack> getItemsToShowEnchantmentsFor()
    {
        return Arrays.asList(
                new ItemStack(TEBlocks.blockCell),
                new ItemStack(TEBlocks.blockTank),
                new ItemStack(TEBlocks.blockCache),
                new ItemStack(TEBlocks.blockStrongbox),
                new ItemStack(TEItems.itemCapacitor),
                new ItemStack(TEItems.itemReservoir),
                new ItemStack(TEItems.itemSatchel));
    }
    
    @Override
    public ModIds getModId()
    {
        return ModIds.THERMAL_EXPANSION;
    }
}
