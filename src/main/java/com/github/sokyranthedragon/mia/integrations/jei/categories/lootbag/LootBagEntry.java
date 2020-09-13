package com.github.sokyranthedragon.mia.integrations.jei.categories.lootbag;

import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.utilities.LootTableUtils;
import com.google.common.collect.Lists;
import jeresources.util.LootTableHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.storage.loot.LootEntryItem;
import net.minecraft.world.storage.loot.LootEntryTable;
import net.minecraft.world.storage.loot.LootTableManager;
import net.minecraft.world.storage.loot.RandomValueRange;
import net.minecraft.world.storage.loot.functions.LootFunction;
import net.minecraft.world.storage.loot.functions.SetMetadata;
import net.minecraft.world.storage.loot.functions.SetNBT;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static com.github.sokyranthedragon.mia.utilities.LootTableUtils.*;

public class LootBagEntry
{
    private List<ItemStack> lootBag;
    private List<BagOutputEntry> possibleOutput;
    
    private LootBagEntry(List<ItemStack> lootBag, List<BagOutputEntry> possibleOutput)
    {
        this.lootBag = lootBag;
        this.possibleOutput = possibleOutput;
    }
    
    @Nonnull
    public static List<LootBagEntry> getEntries(List<ItemStack> lootBags, @Nonnull Collection<BagOutputEntry> possibleOutputs)
    {
        if (possibleOutputs.size() == 0 || lootBags.isEmpty())
            return new ArrayList<>();
        
        List<LootBagEntry> pages = new ArrayList<>(MathHelper.ceil((float) possibleOutputs.size() / 36)); // 4 * 9
        List<BagOutputEntry> items = new ArrayList<>(36);
        
        for (BagOutputEntry possibleOutput : possibleOutputs)
        {
            if (!possibleOutput.items.isEmpty())
            {
                items.add(possibleOutput);
                if (items.size() == 36)
                {
                    pages.add(new LootBagEntry(lootBags, items));
                    items = new ArrayList<>();
                }
            }
        }
        
        if (items.size() > 0)
            pages.add(new LootBagEntry(lootBags, items));
        
        return pages;
    }
    
    @Nonnull
    public static List<LootBagEntry> getEntries(ItemStack lootBag, @Nonnull Collection<BagOutputEntry> possibleOutputs)
    {
        return getEntries(Lists.newArrayList(lootBag), possibleOutputs);
    }
    
    @Nonnull
    public static List<LootBagEntry> getEntries(ItemStack lootBag, @Nonnull ResourceLocation resourceLocation)
    {
        if (!ModIds.JER.isLoaded || lootBag.isEmpty())
            return new ArrayList<>();
        
        List<BagOutputEntry> drops = new ArrayList<>();
        LootTableManager manager = LootTableHelper.getManager();
        toDrops(manager, resourceLocation, drops);
        
        return getEntries(lootBag, drops);
    }
    
    public List<ItemStack> getInput()
    {
        return lootBag;
    }
    
    public List<BagOutputEntry> getOutputs()
    {
        return possibleOutput;
    }
    
    public static class BagOutputEntry
    {
        private List<ItemStack> items;
        private float chance;
        
        public BagOutputEntry(List<ItemStack> items, float chance)
        {
            this.items = items.stream().filter(stack -> stack != null && !stack.isEmpty()).collect(Collectors.toList());
            this.chance = chance;
        }
        
        public List<ItemStack> getItems()
        {
            return items;
        }
        
        public float getChance()
        {
            return chance;
        }
    }
    
    // Stuff related to changing ResourceLocation to BagOutputEntry
    
    public static void toDrops(LootTableManager manager, ResourceLocation resourceLocation, Collection<LootBagEntry.BagOutputEntry> possibleOutputs)
    {
        getPools(manager.getLootTableFromLocation(resourceLocation)).forEach(pool ->
            {
                float totalWeight = (float) LootTableUtils.getEntries(pool).stream().mapToInt((entry) -> entry.getEffectiveWeight(0.0F)).sum();
                LootTableUtils.getEntries(pool).stream()
                    .filter(entry -> entry instanceof LootEntryItem)
                    .map(entry -> (LootEntryItem) entry)
                    .map(entry -> new LootBagEntry.BagOutputEntry(applyFunction(getItem(entry), getFunctions(entry)), (float) entry.getEffectiveWeight(0.0f) / totalWeight * 100f))
                    .forEach(possibleOutputs::add);
                LootTableUtils.getEntries(pool).stream()
                    .filter(entry -> entry instanceof LootEntryTable)
                    .map(entry -> (LootEntryTable) entry)
                    .forEach(entry -> toDrops(manager, getTable(entry), possibleOutputs));
            }
        );
        possibleOutputs.removeIf(entry -> entry.items.size() == 0);
    }
    
    private static List<ItemStack> applyFunction(Item item, LootFunction[] functions)
    {
        int min = 0;
        int max = 0;
//        boolean enchanted = false;
        SetNBT tag = null;
        
        for (LootFunction function : functions)
        {
            if (function instanceof SetMetadata)
            {
                RandomValueRange range = getMetaRange((SetMetadata) function);
                min = (int) range.getMin();
                max = (int) range.getMax();
            }
            else if (function instanceof SetNBT)
                tag = (SetNBT) function;
//            else if (function instanceof EnchantRandomly || function instanceof EnchantWithLevels)
//                enchanted = true;
        }
        
        List<ItemStack> items = new ArrayList<>();
        
        for (int i = min; i <= max; i++)
            items.add(createItem(item, i, tag));
        
        if (items.size() == 0)
            items.add(createItem(item, 0, tag));
        
        return items;
    }
    
    private static ItemStack createItem(Item item, int meta, SetNBT tag)
    {
        ItemStack stack = new ItemStack(item, 1, meta);
        if (tag != null)
            tag.apply(stack, null, null);
        return stack;
    }
}
