package com.github.exploder1531.mia.integrations.dungeontactics;

import com.github.exploder1531.mia.integrations.ModIds;
import com.github.exploder1531.mia.integrations.quark.IQuarkIntegration;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.item.ItemStack;
import pegbeard.dungeontactics.handlers.DTItems;

import java.util.Arrays;
import java.util.Collection;

@MethodsReturnNonnullByDefault
class QuarkDungeonTacticsIntegration implements IQuarkIntegration
{
    @Override
    public Collection<ItemStack> getItemsToShowEnchantmentsFor()
    {
        return Arrays.asList(
                new ItemStack(DTItems.TUNNELER),
                new ItemStack(DTItems.POTSHOT),
                new ItemStack(DTItems.MAGIC_SCROLL),
                new ItemStack(DTItems.DIAMONDRING));
    }
    
    @Override
    public ModIds getModId()
    {
        return ModIds.DUNGEON_TACTICS;
    }
}
