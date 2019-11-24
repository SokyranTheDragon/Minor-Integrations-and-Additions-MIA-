package com.github.exploder1531.mia.utilities;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.world.storage.loot.LootEntryItem;
import net.minecraft.world.storage.loot.LootPool;
import net.minecraft.world.storage.loot.RandomValueRange;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraft.world.storage.loot.conditions.RandomChance;
import net.minecraft.world.storage.loot.functions.EnchantWithLevels;
import net.minecraft.world.storage.loot.functions.LootFunction;
import net.minecraft.world.storage.loot.functions.SetDamage;
import net.minecraft.world.storage.loot.functions.SetMetadata;

public class LootUtils
{
    private LootUtils()
    {
    }
    
    public static void addDtLoot(LootPool loot, Block block, String entryName, LootFunction... functions)
    {
        addDtLoot(loot, Item.getItemFromBlock(block), entryName, 5, functions);
    }
    
    public static void addDtLoot(LootPool loot, Item item, String entryName, LootFunction... functions)
    {
        addDtLoot(loot, item, entryName, 5, functions);
    }
    
    public static void addDtLoot(LootPool loot, Block block, String entryName, int weight, LootFunction... functions)
    {
        addDtLoot(loot, Item.getItemFromBlock(block), entryName, weight, functions);
    }
    
    public static void addDtLoot(LootPool loot, Item item, String entryName, int weight, LootFunction... functions)
    {
        loot.addEntry(new LootEntryItem(item, weight, 0, functions, new LootCondition[0], entryName));
    }
    
    public static LootFunction[] getDtToolFunctions()
    {
        return new LootFunction[]
                {
                        new SetDamage(new LootCondition[0], new RandomValueRange(0.5f, 1f)),
                        new EnchantWithLevels(new LootCondition[]{ new RandomChance(0.2f) }, new RandomValueRange(30, 30), true)
                };
    }
    
    public static LootFunction[] setMetadataFunction(int min, int max)
    {
        return new LootFunction[]
                {
                    new SetMetadata(new LootCondition[0], new RandomValueRange(min, max))
                };
    }
}
