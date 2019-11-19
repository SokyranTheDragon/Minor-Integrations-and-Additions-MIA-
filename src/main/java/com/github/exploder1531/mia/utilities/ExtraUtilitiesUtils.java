package com.github.exploder1531.mia.utilities;

import com.github.exploder1531.mia.integrations.ModLoadStatus;
import com.rwtema.extrautils2.backend.entries.XU2Entries;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class ExtraUtilitiesUtils
{
    public static boolean isItemLasso(ItemStack item)
    {
        if (!ModLoadStatus.extraUtilitiesLoaded)
            return false;
        return item.getItem() == XU2Entries.goldenLasso.value;
    }
    
    @SuppressWarnings("ConstantConditions")
    public static boolean isItemLassoWithMob(ItemStack item, String... mobIds)
    {
        if (!isItemLasso(item))
            return false;
        if (!item.hasTagCompound())
            return false;
        NBTTagCompound nbt = item.getTagCompound();
        if (!nbt.hasKey("Animal", 10))
            return false;
        nbt = nbt.getCompoundTag("Animal");
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
    
    @SuppressWarnings("ConstantConditions")
    public static boolean isItemLassoWithoutMob(ItemStack item)
    {
        if (!isItemLasso(item))
            return false;
        if (!item.hasTagCompound())
            return true;
        NBTTagCompound nbt = item.getTagCompound();
        return !nbt.hasKey("Animal", 10);
    }
}
