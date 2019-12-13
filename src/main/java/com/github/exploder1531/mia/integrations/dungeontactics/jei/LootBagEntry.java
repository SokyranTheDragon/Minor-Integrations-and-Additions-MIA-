package com.github.exploder1531.mia.integrations.dungeontactics.jei;

import com.github.exploder1531.mia.integrations.ModLoadStatus;
import jeresources.util.LootTableHelper;
import jeresources.util.ReflectionHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.storage.loot.LootEntryItem;
import net.minecraft.world.storage.loot.LootEntryTable;
import net.minecraft.world.storage.loot.LootTableManager;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class LootBagEntry
{
    private ItemStack lootBag;
    private List<BagOutputEntry> possibleOutput;
    
    private LootBagEntry(ItemStack lootBag, List<BagOutputEntry> possibleOutput)
    {
        this.lootBag = lootBag;
        this.possibleOutput = possibleOutput;
    }
    
    @Nonnull
    public static List<LootBagEntry> getEntries(ItemStack lootBag, @Nonnull Collection<BagOutputEntry> possibleOutputs)
    {
        if (possibleOutputs.size() == 0 || lootBag.isEmpty())
            return new ArrayList<>();
        
        List<LootBagEntry> pages = new ArrayList<>(MathHelper.ceil((float) possibleOutputs.size() / 36)); // 4 * 9
        List<BagOutputEntry> items = new ArrayList<>(36);
        
        for (BagOutputEntry possibleOutput : possibleOutputs)
        {
            if (!possibleOutput.item.isEmpty())
            {
                items.add(possibleOutput);
                if (items.size() == 36)
                {
                    pages.add(new LootBagEntry(lootBag, items));
                    items = new ArrayList<>();
                }
            }
        }
        
        if (items.size() > 0)
            pages.add(new LootBagEntry(lootBag, items));
        
        return pages;
    }
    
    @Nonnull
    public static List<LootBagEntry> getEntries(ItemStack lootBag, @Nonnull ResourceLocation resourceLocation)
    {
        if (!ModLoadStatus.jerLoaded || lootBag.isEmpty())
            return new ArrayList<>();
        
        List<BagOutputEntry> drops = new ArrayList<>();
        LootTableManager manager = LootTableHelper.getManager();
        toDrops(manager, resourceLocation, drops);
        
        return getEntries(lootBag, drops);
    }
    
    public ItemStack getInput()
    {
        return lootBag;
    }
    
    public List<BagOutputEntry> getOutputs()
    {
        return possibleOutput;
    }
    
    public static class BagOutputEntry
    {
        private ItemStack item;
        private float chance;
        
        public BagOutputEntry(ItemStack item, float chance)
        {
            this.item = item;
            this.chance = chance;
        }
        
        public BagOutputEntry(Item item, float chance)
        {
            this(new ItemStack(item), chance);
        }
        
        public ItemStack getItem()
        {
            return item;
        }
        
        public float getChance()
        {
            return chance;
        }
    }
    
    // Stuff related to changing ResourceLocation to BagOutputEntry
    
    private static ResourceLocation getTable(LootEntryTable lootEntry)
    {
        return ReflectionHelper.getPrivateValue(LootEntryTable.class, lootEntry, "field_186371_a");
    }
    
    private static void toDrops(LootTableManager manager, ResourceLocation resourceLocation, Collection<BagOutputEntry> possibleOutputs)
    {
        LootTableHelper.getPools(manager.getLootTableFromLocation(resourceLocation)).forEach(pool ->
                {
                    float totalWeight = (float) LootTableHelper.getEntries(pool).stream().mapToInt((entry) -> entry.getEffectiveWeight(0.0F)).sum();
                    LootTableHelper.getEntries(pool).stream()
                                   .filter(entry -> entry instanceof LootEntryItem)
                                   .map(entry -> (LootEntryItem) entry)
                                   .map(entry -> new BagOutputEntry(LootTableHelper.getItem(entry), (float) entry.getEffectiveWeight(0f) / totalWeight))
                                   .forEach(possibleOutputs::add);
                    LootTableHelper.getEntries(pool).stream()
                                   .filter(entry -> entry instanceof LootEntryTable)
                                   .map(entry -> (LootEntryTable) entry)
                                   .forEach(entry -> toDrops(manager, getTable(entry), possibleOutputs));
                }
        );
    }
}
