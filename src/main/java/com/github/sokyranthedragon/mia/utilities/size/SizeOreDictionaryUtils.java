package com.github.sokyranthedragon.mia.utilities.size;

import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.oredict.OreDictionary;

public class SizeOreDictionaryUtils
{
    private static NonNullList<ItemStack> slimeBallItems;
    private static NonNullList<ItemStack> slimeBlockItems;
    private static NonNullList<ItemStack> paperItems;
    
    private SizeOreDictionaryUtils()
    {
    }
    
    public static void setupOreDictUtils()
    {
        if (slimeBallItems != null)
            return;
        
        slimeBallItems = OreDictionary.getOres("slimeball");
        slimeBlockItems = OreDictionary.getOres("blockSlime");
        paperItems = OreDictionary.getOres("paper");
    }
    
    public static boolean isItemSlime(ItemStack item)
    {
        return slimeBallItems.stream().anyMatch((s) -> s.isItemEqual(item)) || slimeBlockItems.stream().anyMatch((s) -> s.isItemEqual(item));
    }
    
    public static boolean isItemPaper(ItemStack item)
    {
        return paperItems.stream().anyMatch((s) -> s.isItemEqual(item));
    }
}
