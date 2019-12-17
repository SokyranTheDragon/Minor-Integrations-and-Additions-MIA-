package com.github.exploder1531.mia.utilities;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.world.storage.loot.LootEntryItem;
import net.minecraft.world.storage.loot.LootPool;
import net.minecraft.world.storage.loot.RandomValueRange;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraft.world.storage.loot.conditions.RandomChance;
import net.minecraft.world.storage.loot.functions.*;

@SuppressWarnings("unused")
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
                        setDamageFunction(0.5f, 1f),
                        enchantWithLevelsFunction(0.2f, 30, true)
                };
    }
    
    public static LootFunction setDamageFunction(float count)
    {
        return setDamageFunction(count, count);
    }
    
    public static LootFunction setDamageFunction(float min, float max)
    {
        return new SetDamage(new LootCondition[0], new RandomValueRange(min, max));
    }
    
    public static LootFunction enchantWithLevelsFunction(float chance, int level, boolean isTreasure)
    {
        return enchantWithLevelsFunction(chance, level, level, isTreasure);
    }
    
    public static LootFunction enchantWithLevelsFunction(float chance, int minLevel, int maxLevel, boolean isTreasure)
    {
        return new EnchantWithLevels(new LootCondition[]{ new RandomChance(chance) }, new RandomValueRange(minLevel, maxLevel), isTreasure);
    }
    
    public static LootFunction setMetadataFunction(int min, int max)
    {
        return new SetMetadata(new LootCondition[0], new RandomValueRange(min, max));
    }
    
    public static LootFunction setCountFunction(int count)
    {
        return setCountFunction(count, count);
    }
    
    public static LootFunction setCountFunction(int min, int max)
    {
        return new SetCount(new LootCondition[0], new RandomValueRange(min, max));
    }
}
