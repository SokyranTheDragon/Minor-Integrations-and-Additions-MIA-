package com.github.exploder1531.mia.utilities;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class ItemStackUtils
{
    private ItemStackUtils()
    {
    }
    
    public static ItemStack getStack(String id, String name, int amount, int meta)
    {
        Item item = ForgeRegistries.ITEMS.getValue(new ResourceLocation(id + ":" + name));
        return item != null ? new ItemStack(item, amount, meta) : ItemStack.EMPTY;
    }
    
    public static ItemStack getStack(String id, String name, int amount)
    {
        return getStack(id, name, amount, 0);
    }
    
    public static ItemStack getStack(String id, String name)
    {
        return getStack(id, name, 1);
    }
}
