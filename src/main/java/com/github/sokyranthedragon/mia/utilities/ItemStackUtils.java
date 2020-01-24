package com.github.sokyranthedragon.mia.utilities;

import com.github.sokyranthedragon.mia.integrations.ModIds;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import java.util.Optional;

public class ItemStackUtils
{
    private ItemStackUtils()
    {
    }
    
    public static Optional<ItemStack> getStack(String id, String name, int amount, int meta)
    {
        Item item = ForgeRegistries.ITEMS.getValue(new ResourceLocation(id + ":" + name));
        return item != null ? Optional.of(new ItemStack(item, amount, meta)) : Optional.empty();
    }
    
    public static Optional<ItemStack> getStack(ModIds id, String name, int amount, int meta)
    {
        return getStack(id.modId, name, amount, meta);
    }
    
    public static Optional<ItemStack> getStack(ModIds id, String name, int amount)
    {
        return getStack(id, name, amount, 0);
    }
    
    public static Optional<ItemStack> getStack(ModIds id, String name)
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
