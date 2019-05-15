package com.github.exploder1531.mia.utilities;

import cofh.thermalexpansion.init.TEItems;
import com.github.exploder1531.mia.integrations.ModIds;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.Loader;

@SuppressWarnings("WeakerAccess")
public class ThermalExpansionUtils
{
    private ThermalExpansionUtils()
    {
    }
    
    public static boolean isItemStackMorb(ItemStack item)
    {
        if (!Loader.isModLoaded(ModIds.THERMAL_EXPANSION))
            return false;
        return item.getItem() == TEItems.itemMorb;
    }
    
    @SuppressWarnings("ConstantConditions")
    public static boolean isItemStackMorbWithMob(ItemStack item, String mobId)
    {
        if (!isItemStackMorb(item))
            return false;
        if (!item.hasTagCompound())
            return false;
        NBTTagCompound nbt = item.getTagCompound();
        if (!nbt.hasKey("id"))
            return false;
        
        return nbt.getString("id").equals(mobId);
    }
}
