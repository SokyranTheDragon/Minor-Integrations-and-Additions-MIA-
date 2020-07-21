package com.github.sokyranthedragon.mia.utilities;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionType;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Arrays;
import java.util.List;

@ParametersAreNonnullByDefault
public class PotionUtils
{
    private PotionUtils()
    {
    }
    
    public static boolean arePotionsSame(ItemStack first, ItemStack second)
    {
        if (first.getItem() != second.getItem())
            return false;
        if (!first.hasTagCompound() || !second.hasTagCompound())
            return false;
        
        //noinspection ConstantConditions
        return arePotionsSame(first.getTagCompound(), second.getTagCompound());
    }
    
    public static boolean arePotionsSame(ItemStack item, PotionType potion)
    {
        if (!item.hasTagCompound())
            return false;
        
        //noinspection ConstantConditions
        return arePotionsSame(item.getTagCompound(), potion);
    }
    
    public static boolean arePotionsSame(ItemStack item, Potion potion)
    {
        if (!item.hasTagCompound())
            return false;
        
        //noinspection ConstantConditions
        return arePotionsSame(item.getTagCompound(), potion);
    }
    
    public static boolean arePotionsSame(NBTTagCompound first, NBTTagCompound second)
    {
        if (!first.hasKey("Potion") || !second.hasKey("Potion"))
            return false;
        
        return PotionType.getPotionTypeForName(first.getString("Potion")) == PotionType.getPotionTypeForName(second.getString("Potion"));
    }
    
    public static boolean arePotionsSame(NBTTagCompound item, PotionType potion)
    {
        if (!item.hasKey("Potion"))
            return false;
        
        return PotionType.getPotionTypeForName(item.getString("Potion")) == potion;
    }
    
    public static boolean arePotionsSame(NBTTagCompound item, Potion potion)
    {
        if (!item.hasKey("Potion"))
            return false;
        
        List<PotionEffect> itemPotions = net.minecraft.potion.PotionUtils.getEffectsFromTag(item);
        
        if (itemPotions.size() != 1)
            return false;
        
        return itemPotions.get(0).getPotion() == potion;
    }
    
    public static boolean isPotionAnyOf(ItemStack item, Potion... potions)
    {
        if (!item.hasTagCompound())
            return false;
        
        //noinspection ConstantConditions
        return isPotionAnyOf(item.getTagCompound(), potions);
    }
    
    public static boolean isPotionAnyOf(NBTTagCompound item, Potion... potions)
    {
        if (!item.hasKey("Potion"))
            return false;
        
        List<PotionEffect> itemPotions = net.minecraft.potion.PotionUtils.getEffectsFromTag(item);
        
        if (itemPotions.size() != 1)
            return false;
        
        Potion itemPotion = itemPotions.get(0).getPotion();
        
        return Arrays.asList(potions).contains(itemPotion);
    }
}
