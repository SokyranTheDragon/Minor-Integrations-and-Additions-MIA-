package com.github.sokyranthedragon.mia.utilities;

import cofh.thermalexpansion.init.TEItems;
import com.github.sokyranthedragon.mia.integrations.ModIds;
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
        if (!ModIds.THERMAL_EXPANSION.isLoaded)
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
