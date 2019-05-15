package com.github.exploder1531.mia.utilities;

import com.github.exploder1531.mia.integrations.ModIds;
import com.rwtema.extrautils2.backend.entries.XU2Entries;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.Loader;

@SuppressWarnings("WeakerAccess")
public class ExtraUtilitiesUtils
{
    public static boolean isItemLasso(ItemStack item)
    {
        if (!Loader.isModLoaded(ModIds.EXTRA_UTILITIES))
            return false;
        return item.getItem() == XU2Entries.goldenLasso.value;
    }
    
    @SuppressWarnings("ConstantConditions")
    public static boolean isItemLassoWithMob(ItemStack item, String mobId)
    {
        if (!isItemLasso(item))
            return false;
        if (!item.hasTagCompound())
            return false;
        NBTTagCompound nbt = item.getTagCompound();
        if (!nbt.hasKey("Animal"))
            return false;
        nbt = nbt.getCompoundTag("Animal");
        if (!nbt.hasKey("id"))
            return false;
        return nbt.getString("id").equals(mobId);
    }
}
