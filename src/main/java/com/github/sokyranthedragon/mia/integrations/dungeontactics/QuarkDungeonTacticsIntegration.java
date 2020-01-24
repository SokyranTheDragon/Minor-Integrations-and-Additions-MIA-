package com.github.sokyranthedragon.mia.integrations.dungeontactics;

import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.integrations.quark.IQuarkIntegration;
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
