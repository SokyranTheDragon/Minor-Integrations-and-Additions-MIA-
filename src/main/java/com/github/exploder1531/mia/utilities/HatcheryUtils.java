package com.github.exploder1531.mia.utilities;

import com.gendeathrow.hatchery.core.init.ModItems;
import com.github.exploder1531.mia.integrations.ModIds;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.Loader;

@SuppressWarnings("WeakerAccess")
public class HatcheryUtils
{
    public static boolean isItemAnimalNet(ItemStack item)
    {
        if (!Loader.isModLoaded(ModIds.HATCHERY))
            return false;
        return item.getItem() == ModItems.animalNet;
    }
    
    @SuppressWarnings("ConstantConditions")
    public static boolean isItemAnimalNetWithMob(ItemStack item, String mobId)
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
        return nbt.getString("id").equals(mobId);
    }
}
