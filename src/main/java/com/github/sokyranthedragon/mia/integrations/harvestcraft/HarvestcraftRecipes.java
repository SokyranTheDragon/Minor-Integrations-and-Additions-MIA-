package com.github.sokyranthedragon.mia.integrations.harvestcraft;

import com.github.sokyranthedragon.mia.Mia;
import com.github.sokyranthedragon.mia.utilities.ItemStackUtils;
import com.pam.harvestcraft.item.GrinderRecipes;
import com.pam.harvestcraft.item.ItemRegistry;
import com.pam.harvestcraft.item.PresserRecipes;
import com.pam.harvestcraft.item.WaterFilterRecipes;
import com.pam.harvestcraft.tileentities.MarketData;
import com.pam.harvestcraft.tileentities.MarketItems;
import com.pam.harvestcraft.tileentities.ShippingBinData;
import com.pam.harvestcraft.tileentities.ShippingBinItems;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import javax.annotation.Nullable;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Map;

@SuppressWarnings({ "BooleanMethodIsAlwaysInverted", "unused" })
public class HarvestcraftRecipes
{
    @Nullable
    public static final Collection<MarketData> marketRecipes;
    @Nullable
    public static final Collection<ShippingBinData> shippingRecipes;
    @Nullable
    public static final Map<ItemStack, ItemStack[]> pressingRecipes;
    @Nullable
    public static final Map<ItemStack, ItemStack[]> grindingRecipes;
    @Nullable
    public static final Map<ItemStack, ItemStack[]> waterFilterRecipes;
    
    static
    {
        Collection<MarketData> tempMarket = null;
        Collection<ShippingBinData> tempShipping = null;
        Map<ItemStack, ItemStack[]> tempPressing = null;
        Map<ItemStack, ItemStack[]> tempGrinding = null;
        Map<ItemStack, ItemStack[]> tempWaterFiltering = null;
        
        try
        {
            Field items = MarketItems.class.getDeclaredField("items");
            items.setAccessible(true);
            Object o = items.get(null);
            
            if (o instanceof Collection<?>)
                //noinspection unchecked
                tempMarket = (Collection<MarketData>) o;
        } catch (NoSuchFieldException | IllegalAccessException e)
        {
            Mia.LOGGER.error("Could not access MarketItems.items, CraftTweaker Harvestcraft integration might not work properly");
        }
        
        try
        {
            Field items = ShippingBinItems.class.getDeclaredField("items");
            items.setAccessible(true);
            Object o = items.get(null);
            
            if (o instanceof Collection<?>)
                //noinspection unchecked
                tempShipping = (Collection<ShippingBinData>) o;
        } catch (NoSuchFieldException | IllegalAccessException e)
        {
            Mia.LOGGER.error("Could not access MarketBin.items, CraftTweaker Harvestcraft integration might not work properly");
        }
        
        try
        {
            Field items = PresserRecipes.class.getDeclaredField("pressingList");
            items.setAccessible(true);
            Object o = items.get(null);
            
            if (o instanceof Map<?, ?>)
                //noinspection unchecked
                tempPressing = (Map<ItemStack, ItemStack[]>) o;
        } catch (NoSuchFieldException | IllegalAccessException e)
        {
            Mia.LOGGER.error("Could not access PressingRecipes.pressingList, CraftTweaker Harvestcraft integration might not work properly");
        }
        
        try
        {
            Field items = GrinderRecipes.class.getDeclaredField("grindingList");
            items.setAccessible(true);
            Object o = items.get(null);
            
            if (o instanceof Map<?, ?>)
                //noinspection unchecked
                tempGrinding = (Map<ItemStack, ItemStack[]>) o;
        } catch (NoSuchFieldException | IllegalAccessException e)
        {
            Mia.LOGGER.error("Could not access GrinderRecipes.grindingList, CraftTweaker Harvestcraft integration might not work properly");
        }
        
        try
        {
            Field items = WaterFilterRecipes.class.getDeclaredField("waterfilterList");
            items.setAccessible(true);
            Object o = items.get(null);
            
            if (o instanceof Map<?, ?>)
                //noinspection unchecked
                tempWaterFiltering = (Map<ItemStack, ItemStack[]>) o;
        } catch (NoSuchFieldException | IllegalAccessException e)
        {
            Mia.LOGGER.error("Could not access WaterFilterRecipes.waterfilterList, CraftTweaker Harvestcraft integration might not work properly");
        }
        
        marketRecipes = tempMarket;
        shippingRecipes = tempShipping;
        pressingRecipes = tempPressing;
        grindingRecipes = tempGrinding;
        waterFilterRecipes = tempWaterFiltering;
    }
    
    private HarvestcraftRecipes()
    {
    }
    
    public static void addMarketRecipe(ItemStack item, int price)
    {
        addMarketRecipe(item, new ItemStack(Items.EMERALD), price);
    }
    
    public static void addMarketRecipe(ItemStack item, ItemStack currency)
    {
        addMarketRecipe(item, currency, 1);
    }
    
    public static void addMarketRecipe(ItemStack item, ItemStack currency, int price)
    {
        if (marketRecipes != null && !doesMarketRecipeExist(item, currency))
            marketRecipes.add(new MarketData(item, currency, price));
    }
    
    public static void addShippingRecipe(ItemStack item, int price)
    {
        addShippingRecipe(item, new ItemStack(Items.EMERALD), price);
    }
    
    public static void addShippingRecipe(ItemStack item, ItemStack currency)
    {
        addShippingRecipe(item, currency, 1);
    }
    
    public static void addShippingRecipe(ItemStack item, ItemStack currency, int price)
    {
        if (shippingRecipes != null && !doesShippingRecipeExist(item, currency))
            shippingRecipes.add(new ShippingBinData(item, currency, price));
    }
    
    public static void addPressingRecipe(ItemStack input, ItemStack outputFirst)
    {
        addPressingRecipe(input, outputFirst, ItemStack.EMPTY);
    }
    
    public static void addPressingRecipe(ItemStack input, ItemStack outputFirst, ItemStack outputSecond)
    {
        if (pressingRecipes != null && !doesPressingRecipeExist(input))
            pressingRecipes.put(input, new ItemStack[]{ outputFirst, outputSecond });
    }
    
    public static void addGrindingRecipe(ItemStack input, ItemStack outputFirst)
    {
        addGrindingRecipe(input, outputFirst, ItemStack.EMPTY);
    }
    
    public static void addGrindingRecipe(ItemStack input, ItemStack outputFirst, ItemStack outputSecond)
    {
        if (grindingRecipes != null && !doesGrindingRecipeExist(input))
            grindingRecipes.put(input, new ItemStack[]{ outputFirst, outputSecond });
    }
    
    public static void addFilteringRecipe(ItemStack input, ItemStack outputFirst)
    {
        addFilteringRecipe(input, outputFirst, ItemStack.EMPTY);
    }
    
    public static void addFilteringRecipe(ItemStack input, ItemStack outputFirst, ItemStack outputSecond)
    {
        if (waterFilterRecipes != null && !doesFilteringRecipeExist(input))
            waterFilterRecipes.put(input, new ItemStack[]{ outputFirst, outputSecond });
    }
    
    public static boolean doesMarketRecipeExist(ItemStack item, ItemStack currency)
    {
        return marketRecipes == null || marketRecipes.stream().anyMatch(entry ->
                ItemStackUtils.areItemStackEqualIgnoreCount(entry.getItem(), item)
                        && ItemStackUtils.areItemStackEqualIgnoreCount(entry.getCurrency(), currency));
    }
    
    public static boolean doesShippingRecipeExist(ItemStack item, ItemStack currency)
    {
        return shippingRecipes == null || shippingRecipes.stream().anyMatch(entry ->
                ItemStackUtils.areItemStackEqualIgnoreCount(entry.getItem(), item)
                        && ItemStackUtils.areItemStackEqualIgnoreCount(entry.getCurrency(), currency));
    }
    
    public static boolean doesPressingRecipeExist(ItemStack input)
    {
        return pressingRecipes == null || pressingRecipes.keySet().stream().anyMatch(entry -> isSameItem(entry, input));
    }
    
    public static boolean doesGrindingRecipeExist(ItemStack input)
    {
        return grindingRecipes == null || grindingRecipes.keySet().stream().anyMatch(stack -> isSameItem(stack, input));
    }
    
    public static boolean doesFilteringRecipeExist(ItemStack input)
    {
        return waterFilterRecipes == null || waterFilterRecipes.keySet().stream().anyMatch(stack -> isSameItem(stack, input));
    }
    
    private static boolean isSameItem(ItemStack stack, ItemStack stack2)
    {
        return stack2.getItem() == stack.getItem() && (stack.getItemDamage() == OreDictionary.WILDCARD_VALUE || stack2.getItemDamage() == OreDictionary.WILDCARD_VALUE || stack2.getItemDamage() == stack.getItemDamage());
    }
    
    public static ItemStack getCurrency(int config, CurrencyType currencyType)
    {
        switch (config)
        {
            case 1:
                return new ItemStack(Items.DIAMOND);
            case 2:
                return new ItemStack(Items.GOLD_INGOT);
            case 3:
                return new ItemStack(Items.GOLD_NUGGET);
            case 4:
                return new ItemStack(Items.IRON_INGOT);
            case 5:
                if (currencyType.equals(CurrencyType.ANIMAL))
                    return new ItemStack(Items.EGG);
                if (currencyType.equals(CurrencyType.SEEDS))
                    return new ItemStack(Items.WHEAT_SEEDS);
                if (currencyType.equals(CurrencyType.SAPLING))
                    return new ItemStack(Blocks.SAPLING);
            case 6:
                return new ItemStack(Items.APPLE);
            case 7:
                return new ItemStack(Items.DYE);
            case 8:
                return new ItemStack(ItemRegistry.garliccoinItem);
        }
        
        // Case 0 and fallback
        return new ItemStack(Items.EMERALD);
    }
    
    public enum CurrencyType
    {
        DEFAULT, SEEDS, SAPLING, ANIMAL, FISH, MEAT, BEES
    }
}
