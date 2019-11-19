package com.github.exploder1531.mia.utilities;

import com.gendeathrow.hatchery.core.init.ModItems;
import com.github.exploder1531.mia.integrations.ModLoadStatus;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

@SuppressWarnings("WeakerAccess")
public class HatcheryUtils
{
    public static boolean isItemAnimalNet(ItemStack item)
    {
        if (!ModLoadStatus.hatcheryLoaded)
            return false;
        return item.getItem() == ModItems.animalNet;
    }
    
    @SuppressWarnings("ConstantConditions")
    public static boolean isItemAnimalNetWithMob(ItemStack item, String... mobIds)
    {
        if (!isItemAnimalNet(item))
            return false;
        if (!item.hasTagCompound())
            return false;
        NBTTagCompound nbt = item.getTagCompound();
        if (!nbt.hasKey("storedEntity", 10))
            return false;
        nbt = nbt.getCompoundTag("storedEntity");
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
