package com.github.exploder1531.mia.utilities;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
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
    
    public static boolean areItemStackEqualIgnoreCount(ItemStack first, ItemStack second)
    {
        if (first.isEmpty() && second.isEmpty())
            return true;
        if (first.isEmpty() || second.isEmpty())
            return false;
        if (first.getItem() != second.getItem())
            return false;
        if (first.getItemDamage() != second.getItemDamage())
            return false;
        NBTTagCompound tagFirst = first.getTagCompound();
        NBTTagCompound tagSecond = second.getTagCompound();
        if (tagFirst == null && tagSecond != null)
            return false;
        return (tagFirst == null || tagFirst.equals(tagSecond)) && first.areCapsCompatible(second);
    }
}
