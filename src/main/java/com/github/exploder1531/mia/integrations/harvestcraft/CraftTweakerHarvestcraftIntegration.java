package com.github.exploder1531.mia.integrations.harvestcraft;

import com.github.exploder1531.mia.Mia;
import com.github.exploder1531.mia.integrations.ModIds;
import com.github.exploder1531.mia.utilities.ItemStackUtils;
import com.pam.harvestcraft.item.GrinderRecipes;
import com.pam.harvestcraft.item.PresserRecipes;
import com.pam.harvestcraft.item.WaterFilterRecipes;
import com.pam.harvestcraft.tileentities.MarketData;
import com.pam.harvestcraft.tileentities.MarketItems;
import com.pam.harvestcraft.tileentities.ShippingBinData;
import com.pam.harvestcraft.tileentities.ShippingBinItems;
import crafttweaker.CraftTweakerAPI;
import crafttweaker.annotations.ModOnly;
import crafttweaker.annotations.ZenDoc;
import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.minecraft.CraftTweakerMC;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import stanhebben.zenscript.annotations.Optional;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@ZenClass("mods.mia.harvestcraft")
@ZenRegister
@ModOnly(ModIds.ConstantIds.HARVESTCRAFT)
public class CraftTweakerHarvestcraftIntegration
{
    public static final Collection<MarketData> marketRecipes;
    public static final Collection<ShippingBinData> shippingRecipes;
    public static final Map<ItemStack, ItemStack[]> pressingRecipes;
    public static final Map<ItemStack, ItemStack[]> grindingRecipes;
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
    
    private static Set<ItemStack> marketRemove = new HashSet<>();
    private static Set<ItemStack> shippingBinRemove = new HashSet<>();
    private static Set<ItemStack> pressingRemove = new HashSet<>();
    private static Set<ItemStack> grindingRemove = new HashSet<>();
    private static Set<ItemStack> waterFilteringRemove = new HashSet<>();
    
    public static void applyRemovals()
    {
        for (ItemStack item : marketRemove)
            marketRecipes.removeIf(recipe -> ItemStackUtils.areItemStackEqualIgnoreCount(item, recipe.getItem()));
        for (ItemStack item : shippingBinRemove)
            shippingRecipes.removeIf(recipe -> ItemStackUtils.areItemStackEqualIgnoreCount(item, recipe.getCurrency()));
        for (ItemStack item : pressingRemove)
        {
            java.util.Optional<ItemStack> result = pressingRecipes.keySet().stream().filter(entry -> isSameItem(item, entry)).findAny();
            if (result.isPresent())
                pressingRecipes.remove(result.get());
            else
                CraftTweakerAPI.logInfo("Cannot remove non-existent recipe, input: " + item.toString());
        }
        for (ItemStack item : grindingRemove)
        {
            java.util.Optional<ItemStack> result = grindingRecipes.keySet().stream().filter(entry -> isSameItem(item, entry)).findAny();
            if (result.isPresent())
                grindingRecipes.remove(result.get());
            else
                CraftTweakerAPI.logInfo("Cannot remove non-existent recipe, input: " + item.toString());
        }
        for (ItemStack item : waterFilteringRemove)
        {
            java.util.Optional<ItemStack> result = waterFilterRecipes.keySet().stream().filter(entry -> isSameItem(item, entry)).findAny();
            if (result.isPresent())
                waterFilterRecipes.remove(result.get());
            else
                CraftTweakerAPI.logInfo("Cannot remove non-existent recipe, input: " + item.toString());
        }
    
        marketRemove = null;
        shippingBinRemove = null;
        pressingRemove = null;
        grindingRemove = null;
        waterFilteringRemove = null;
    }
    
    @ZenMethod
    @ZenDoc("Check whether or not new market recipes can be added using 'addMarketRecipe'.\r\n" +
            "This should be checked before adding any recipes to prevent (potential) error spam.")
    public static boolean canAccessMarketRecipes()
    {
        return marketRecipes != null;
    }
    
    @ZenMethod
    @ZenDoc("Adds a new entry to the market.\r\n" +
            "The correct cost is between 1 and max stack size, changed automatically to closest valid value if it's set outside this range.\r\n" +
            "If input item is not set then emeralds will be used by default.")
    public static void addMarketRecipe(IItemStack output, int cost, @Optional IItemStack input)
    {
        if (marketRecipes == null)
        {
            CraftTweakerAPI.logError("Market recipes couldn't be accessed during initialization, it's not possible to add any new ones. This shouldn't happen unless there was a major change in Harvestcraft, or MIA does have some bugs.");
            return;
        }
        if (output == null || output.isEmpty())
        {
            CraftTweakerAPI.logError("Output does not exist!");
            return;
        }
        ItemStack outputStack = CraftTweakerMC.getItemStack(output);
        
        if (cost <= 0)
            cost = 1;
        
        ItemStack inputStack;
        if (input == null || input.isEmpty())
            inputStack = new ItemStack(Items.EMERALD);
        else
            inputStack = CraftTweakerMC.getItemStack(input);
        
        if (cost > inputStack.getMaxStackSize())
            cost = inputStack.getMaxStackSize();
        
        int finalCost = cost;
        if (marketRecipes.stream().noneMatch(recipe ->
                recipe.getPrice() == finalCost
                        && ItemStackUtils.areItemStackEqualIgnoreCount(recipe.getCurrency(), inputStack)
                        && ItemStackUtils.areItemStackEqualIgnoreCount(recipe.getItem(), outputStack)))
            marketRecipes.add(new MarketData(outputStack, inputStack, cost));
        else
        {
            CraftTweakerAPI.logWarning("Market recipe already exists!");
            CraftTweakerAPI.logWarning("Input: " + inputStack.toString());
            CraftTweakerAPI.logWarning("Output: " + outputStack.toString());
            CraftTweakerAPI.logWarning("Cost: " + cost);
        }
    }
    
    @ZenMethod
    @ZenDoc("Removes an entry from the market.")
    public static void removeMarketRecipe(IItemStack output)
    {
        if (marketRecipes == null)
        {
            CraftTweakerAPI.logError("Market recipes couldn't be accessed during initialization, it's not possible to remove any of them. This shouldn't happen unless there was a major change in Harvestcraft, or MIA does have some bugs.");
            return;
        }
        if (output == null || output.isEmpty())
        {
            CraftTweakerAPI.logError("Output does not exist!");
            return;
        }
        
        marketRemove.add(CraftTweakerMC.getItemStack(output));
    }
    
    @ZenMethod
    @ZenDoc("Check whether or not new market recipes can be added using 'addShippingBinRecipe'.\r\n" +
            "This should be checked before adding any recipes to prevent (potential) error spam.")
    public static boolean canAccessShippingBinRecipes()
    {
        return shippingRecipes != null;
    }
    
    @ZenMethod
    @ZenDoc("Adds a new entry to the shipping bin.\r\n" +
            "The correct cost is between 1 and max stack size, changed automatically to closest valid value if it's set outside this range.\r\n" +
            "If output item is not set then emeralds will be used by default.")
    public static void addShippingBinRecipe(IItemStack input, int cost, @Optional IItemStack output)
    {
        if (shippingRecipes == null)
        {
            CraftTweakerAPI.logError("Shipping bin recipes couldn't be accessed during initialization, it's not possible to add any new ones. This shouldn't happen unless there was a major change in Harvestcraft, or MIA does have some bugs.");
            return;
        }
        if (input == null || input.isEmpty())
        {
            CraftTweakerAPI.logError("Input does not exist!");
            return;
        }
        ItemStack inputStack = CraftTweakerMC.getItemStack(input);
        
        if (cost <= 0)
            cost = 1;
        
        ItemStack outputStack;
        if (output == null || output.isEmpty())
            outputStack = new ItemStack(Items.EMERALD);
        else
            outputStack = CraftTweakerMC.getItemStack(output);
        
        if (cost > inputStack.getMaxStackSize())
            cost = inputStack.getMaxStackSize();
        
        int finalCost = cost;
        if (shippingRecipes.stream().noneMatch(recipe ->
                recipe.getPrice() == finalCost
                        && ItemStackUtils.areItemStackEqualIgnoreCount(recipe.getCurrency(), outputStack)
                        && ItemStackUtils.areItemStackEqualIgnoreCount(recipe.getItem(), inputStack)))
            shippingRecipes.add(new ShippingBinData(outputStack, inputStack, cost));
        else
        {
            CraftTweakerAPI.logWarning("Shipping bin recipe already exists!");
            CraftTweakerAPI.logWarning("Input: " + inputStack.toString());
            CraftTweakerAPI.logWarning("Output: " + outputStack.toString());
            CraftTweakerAPI.logWarning("Cost: " + cost);
        }
    }
    
    @ZenMethod
    @ZenDoc("Removes an entry from the shipping bin.")
    public static void removeShippingBinRecipe(IItemStack input)
    {
        if (shippingRecipes == null)
        {
            CraftTweakerAPI.logError("Shipping bin recipes couldn't be accessed during initialization, it's not possible to remove any of them. This shouldn't happen unless there was a major change in Harvestcraft, or MIA does have some bugs.");
            return;
        }
        if (input == null || input.isEmpty())
        {
            CraftTweakerAPI.logError("Input does not exist!");
            return;
        }
        
        shippingBinRemove.add(CraftTweakerMC.getItemStack(input));
    }
    
    @ZenMethod
    @ZenDoc("Check whether or not new market recipes can be added using 'addShippingBinRecipe' or removed using 'removePressingRecipe'.\r\n" +
            "This should be checked before adding any recipes to prevent (potential) error spam.")
    public static boolean canAccessPressingRecipes()
    {
        return pressingRecipes != null;
    }
    
    @ZenMethod
    @ZenDoc("Adds a new entry to the presser.\r\n" +
            "Input stack accepts wildcard metadata value (32767).")
    public static void addPressingRecipe(IItemStack input, IItemStack firstOutput, @Optional IItemStack secondOutput)
    {
        if (pressingRecipes == null)
        {
            CraftTweakerAPI.logError("Presser recipes couldn't be accessed during initialization, it's not possible to remove any of them. This shouldn't happen unless there was a major change in Harvestcraft, or MIA does have some bugs.");
            return;
        }
        if (input == null || input.isEmpty())
        {
            CraftTweakerAPI.logError("Input does not exist!");
            return;
        }
        if (firstOutput == null || firstOutput.isEmpty())
        {
            if (secondOutput != null && !secondOutput.isEmpty())
            {
                firstOutput = secondOutput;
                secondOutput = null;
            }
            else
            {
                CraftTweakerAPI.logError("Output does not exist!");
                return;
            }
        }
        
        ItemStack stackInput = CraftTweakerMC.getItemStack(input);
        
        if (pressingRecipes.keySet().stream().anyMatch(stack -> isSameItem(stack, stackInput)))
        {
            CraftTweakerAPI.logError("Recipe using provided input already exists! Item: " + stackInput.toString());
            return;
        }
        
        ItemStack stackOutputFirst = CraftTweakerMC.getItemStack(firstOutput);
        ItemStack stackOutputSecond;
        if (secondOutput == null || secondOutput.isEmpty())
            stackOutputSecond = ItemStack.EMPTY;
        else
            stackOutputSecond = CraftTweakerMC.getItemStack(secondOutput);
        
        pressingRecipes.put(stackInput, new ItemStack[] { stackOutputFirst, stackOutputSecond });
    }
    
    @ZenMethod
    @ZenDoc("Removes an entry from the presser.")
    public static void removePressingRecipe(IItemStack input)
    {
        if (pressingRecipes == null)
        {
            CraftTweakerAPI.logError("Presser recipes couldn't be accessed during initialization, it's not possible to remove any of them. This shouldn't happen unless there was a major change in Harvestcraft, or MIA does have some bugs.");
            return;
        }
        if (input == null || input.isEmpty())
        {
            CraftTweakerAPI.logError("Input does not exist!");
            return;
        }
        
        pressingRemove.add(CraftTweakerMC.getItemStack(input));
    }
    
    @ZenMethod
    @ZenDoc("Check whether or not new market recipes can be added using 'addGrindingRecipe' or removed using 'removeGrindingRecipe'.\r\n" +
            "This should be checked before adding any recipes to prevent (potential) error spam.")
    public static boolean canAccessGrindingRecipes()
    {
        return grindingRecipes != null;
    }
    
    @ZenMethod
    @ZenDoc("Adds a new entry to the presser.\r\n" +
            "Input stack accepts wildcard metadata value (32767).")
    public static void addGrindingRecipe(IItemStack input, IItemStack firstOutput, @Optional IItemStack secondOutput)
    {
        if (grindingRecipes == null)
        {
            CraftTweakerAPI.logError("Grinding recipes couldn't be accessed during initialization, it's not possible to remove any of them. This shouldn't happen unless there was a major change in Harvestcraft, or MIA does have some bugs.");
            return;
        }
        if (input == null || input.isEmpty())
        {
            CraftTweakerAPI.logError("Input does not exist!");
            return;
        }
        if (firstOutput == null || firstOutput.isEmpty())
        {
            if (secondOutput != null && !secondOutput.isEmpty())
            {
                firstOutput = secondOutput;
                secondOutput = null;
            }
            else
            {
                CraftTweakerAPI.logError("Output does not exist!");
                return;
            }
        }
        
        ItemStack stackInput = CraftTweakerMC.getItemStack(input);
        
        if (grindingRecipes.keySet().stream().anyMatch(stack -> isSameItem(stack, stackInput)))
        {
            CraftTweakerAPI.logError("Recipe using provided input already exists! Item: " + stackInput.toString());
            return;
        }
        
        ItemStack stackOutputFirst = CraftTweakerMC.getItemStack(firstOutput);
        ItemStack stackOutputSecond;
        if (secondOutput == null || secondOutput.isEmpty())
            stackOutputSecond = ItemStack.EMPTY;
        else
            stackOutputSecond = CraftTweakerMC.getItemStack(secondOutput);
        
        grindingRecipes.put(stackInput, new ItemStack[] { stackOutputFirst, stackOutputSecond });
    }
    
    @ZenMethod
    @ZenDoc("Removes an entry from the grinder.")
    public static void removeGrindingRecipe(IItemStack input)
    {
        if (grindingRecipes == null)
        {
            CraftTweakerAPI.logError("Presser recipes couldn't be accessed during initialization, it's not possible to remove any of them. This shouldn't happen unless there was a major change in Harvestcraft, or MIA does have some bugs.");
            return;
        }
        if (input == null || input.isEmpty())
        {
            CraftTweakerAPI.logError("Input does not exist!");
            return;
        }
        
        grindingRemove.add(CraftTweakerMC.getItemStack(input));
    }
    
    @ZenMethod
    @ZenDoc("Check whether or not new market recipes can be added using 'addWaterFilteringRecipe' or removed using 'removeWaterFilteringRecipe'.\r\n" +
            "This should be checked before adding any recipes to prevent (potential) error spam.")
    public static boolean canAccessWaterFilteringRecipes()
    {
        return waterFilterRecipes != null;
    }
    
    @ZenMethod
    @ZenDoc("Adds a new entry to the presser.\r\n" +
            "Input stack accepts wildcard metadata value (32767).")
    public static void addWaterFilteringRecipe(IItemStack input, IItemStack firstOutput, @Optional IItemStack secondOutput)
    {
        if (waterFilterRecipes == null)
        {
            CraftTweakerAPI.logError("Grinding recipes couldn't be accessed during initialization, it's not possible to remove any of them. This shouldn't happen unless there was a major change in Harvestcraft, or MIA does have some bugs.");
            return;
        }
        if (input == null || input.isEmpty())
        {
            CraftTweakerAPI.logError("Input does not exist!");
            return;
        }
        if (firstOutput == null || firstOutput.isEmpty())
        {
            if (secondOutput != null && !secondOutput.isEmpty())
            {
                firstOutput = secondOutput;
                secondOutput = null;
            }
            else
            {
                CraftTweakerAPI.logError("Output does not exist!");
                return;
            }
        }
        
        ItemStack stackInput = CraftTweakerMC.getItemStack(input);
        
        if (waterFilterRecipes.keySet().stream().anyMatch(stack -> isSameItem(stack, stackInput)))
        {
            CraftTweakerAPI.logError("Recipe using provided input already exists! Item: " + stackInput.toString());
            return;
        }
        
        ItemStack stackOutputFirst = CraftTweakerMC.getItemStack(firstOutput);
        ItemStack stackOutputSecond;
        if (secondOutput == null || secondOutput.isEmpty())
            stackOutputSecond = ItemStack.EMPTY;
        else
            stackOutputSecond = CraftTweakerMC.getItemStack(secondOutput);
    
        waterFilterRecipes.put(stackInput, new ItemStack[] { stackOutputFirst, stackOutputSecond });
    }
    
    @ZenMethod
    @ZenDoc("Removes an entry from the grinder.")
    public static void removeWaterFilteringRecipe(IItemStack input)
    {
        if (waterFilterRecipes == null)
        {
            CraftTweakerAPI.logError("Presser recipes couldn't be accessed during initialization, it's not possible to remove any of them. This shouldn't happen unless there was a major change in Harvestcraft, or MIA does have some bugs.");
            return;
        }
        if (input == null || input.isEmpty())
        {
            CraftTweakerAPI.logError("Input does not exist!");
            return;
        }
        
        waterFilteringRemove.add(CraftTweakerMC.getItemStack(input));
    }
    
    private static boolean isSameItem(ItemStack stack, ItemStack stack2)
    {
        return stack2.getItem() == stack.getItem() && (stack.getItemDamage() == OreDictionary.WILDCARD_VALUE || stack2.getItemDamage() == OreDictionary.WILDCARD_VALUE || stack2.getItemDamage() == stack.getItemDamage());
    }
}
