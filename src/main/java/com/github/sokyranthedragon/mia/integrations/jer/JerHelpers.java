package com.github.sokyranthedragon.mia.integrations.jer;

import jeresources.api.IDungeonRegistry;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import java.util.List;

public class JerHelpers
{
    private JerHelpers()
    {
    }
    
    public static void addDungeonLootCategory(IDungeonRegistry registry, String name, ResourceLocation... loot)
    {
        addDungeonLootCategory(registry, name, "chests", loot);
    }
    
    public static void addDungeonLootCategory(IDungeonRegistry registry, String name, String prefix, ResourceLocation... loot)
    {
        String category = prefix + "/" + name;
        
        registry.registerCategory(category, "mia.jer.dungeon." + name);
        
        for (ResourceLocation resourceLocation : loot)
            registry.registerChest(category, resourceLocation);
    }
    
    public static void removeDuplicateEntries(List<ItemStack> injectedDrops, List<ItemStack> baseDrops)
    {
        for (ItemStack item : baseDrops)
        {
            if (injectedDrops.remove(item) && injectedDrops.size() == 0)
                break;
        }
    }
}
