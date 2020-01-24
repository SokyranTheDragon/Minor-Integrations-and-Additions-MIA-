package com.github.sokyranthedragon.mia.integrations.jer;

import net.minecraft.item.ItemStack;

import java.util.List;

public class JerOverrideHelper
{
    private JerOverrideHelper()
    {
    }
    
    public static void removeDuplicateEntries(List<ItemStack> injectedDrops, List<ItemStack> baseDrops)
    {
        for (ItemStack item : baseDrops)
        {
            if (injectedDrops.remove(item) && injectedDrops.size() == 0)
                break;
        }
    }
}
