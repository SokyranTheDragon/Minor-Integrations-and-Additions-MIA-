package com.github.exploder1531.mia.utilities;

import cofh.thermalexpansion.init.TEItems;
import com.github.exploder1531.mia.integrations.ModLoadStatus;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

@SuppressWarnings("WeakerAccess")
public class ThermalExpansionUtils
{
    private ThermalExpansionUtils()
    {
    }
    
    public static boolean isItemStackMorb(ItemStack item)
    {
        if (!ModLoadStatus.thermalExpansionLoaded)
            return false;
        return item.getItem() == TEItems.itemMorb;
    }
    
    @SuppressWarnings("ConstantConditions")
    public static boolean isItemStackMorbWithMob(ItemStack item, String... mobIds)
    {
        if (!isItemStackMorb(item))
            return false;
        if (!item.hasTagCompound())
            return false;
        NBTTagCompound nbt = item.getTagCompound();
        if (!nbt.hasKey("id", 8))
            return false;
        String id = nbt.getString("id");
        for (String mob : mobIds)
        {
            if (id.equals(mob))
                return true;
        }
        return false;
    }
}
