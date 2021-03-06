package com.github.sokyranthedragon.mia.integrations.harvestcraft;

import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.utilities.ItemStackUtils;
import com.pam.harvestcraft.tileentities.MarketData;
import com.pam.harvestcraft.tileentities.ShippingBinData;
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

import java.util.HashSet;
import java.util.Set;

@ZenClass("mods.mia.harvestcraft")
@ZenRegister
@ModOnly(ModIds.ConstantIds.HARVESTCRAFT)
public class CraftTweakerHarvestcraftIntegration
{
    private static Set<ItemStack> marketRemove = new HashSet<>();
    private static Set<ItemStack> shippingBinRemove = new HashSet<>();
    private static Set<ItemStack> pressingRemove = new HashSet<>();
    private static Set<ItemStack> grindingRemove = new HashSet<>();
    private static Set<ItemStack> waterFilteringRemove = new HashSet<>();
    
    public static void applyRemovals()
    {
        if (HarvestcraftRecipes.marketRecipes != null)
        {
            for (ItemStack item : marketRemove)
                HarvestcraftRecipes.marketRecipes.removeIf(recipe -> ItemStackUtils.areItemStackEqualIgnoreCount(item, recipe.getItem()));
        }
        if (HarvestcraftRecipes.shippingRecipes != null)
        {
            for (ItemStack item : shippingBinRemove)
                HarvestcraftRecipes.shippingRecipes.removeIf(recipe -> ItemStackUtils.areItemStackEqualIgnoreCount(item, recipe.getCurrency()));
        }
        if (HarvestcraftRecipes.pressingRecipes != null)
        {
            for (ItemStack item : pressingRemove)
            {
                java.util.Optional<ItemStack> result = HarvestcraftRecipes.pressingRecipes.keySet().stream().filter(entry -> isSameItem(item, entry)).findAny();
                if (result.isPresent())
                    HarvestcraftRecipes.pressingRecipes.remove(result.get());
                else
                    CraftTweakerAPI.logInfo("Cannot remove non-existent recipe, input: " + item.toString());
            }
        }
        if (HarvestcraftRecipes.grindingRecipes != null)
        {
            for (ItemStack item : grindingRemove)
            {
                java.util.Optional<ItemStack> result = HarvestcraftRecipes.grindingRecipes.keySet().stream().filter(entry -> isSameItem(item, entry)).findAny();
                if (result.isPresent())
                    HarvestcraftRecipes.grindingRecipes.remove(result.get());
                else
                    CraftTweakerAPI.logInfo("Cannot remove non-existent recipe, input: " + item.toString());
            }
        }
        if (HarvestcraftRecipes.waterFilterRecipes != null)
        {
            for (ItemStack item : waterFilteringRemove)
            {
                java.util.Optional<ItemStack> result = HarvestcraftRecipes.waterFilterRecipes.keySet().stream().filter(entry -> isSameItem(item, entry)).findAny();
                if (result.isPresent())
                    HarvestcraftRecipes.waterFilterRecipes.remove(result.get());
                else
                    CraftTweakerAPI.logInfo("Cannot remove non-existent recipe, input: " + item.toString());
            }
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
        return HarvestcraftRecipes.marketRecipes != null;
    }
    
    @ZenMethod
    @ZenDoc("Adds a new entry to the market.\r\n" +
            "The correct cost is between 1 and max stack size, changed automatically to closest valid value if it's set outside this range.\r\n" +
            "If input item is not set then emeralds will be used by default.")
    public static void addMarketRecipe(IItemStack output, int cost, @Optional IItemStack input)
    {
        if (HarvestcraftRecipes.marketRecipes == null)
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
        
        if (!HarvestcraftRecipes.doesMarketRecipeExist(outputStack, inputStack))
            HarvestcraftRecipes.marketRecipes.add(new MarketData(outputStack, inputStack, cost));
        else
        {
            CraftTweakerAPI.logWarning("Market recipe already exists!");
            CraftTweakerAPI.logWarning("Input: " + inputStack.toString());
            CraftTweakerAPI.logWarning("Output: " + outputStack.toString());
        }
    }
    
    @ZenMethod
    @ZenDoc("Removes an entry from the market.")
    public static void removeMarketRecipe(IItemStack output)
    {
        if (HarvestcraftRecipes.marketRecipes == null)
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
        return HarvestcraftRecipes.shippingRecipes != null;
    }
    
    @ZenMethod
    @ZenDoc("Adds a new entry to the shipping bin.\r\n" +
            "The correct cost is between 1 and max stack size, changed automatically to closest valid value if it's set outside this range.\r\n" +
            "If output item is not set then emeralds will be used by default.")
    public static void addShippingBinRecipe(IItemStack input, int cost, @Optional IItemStack output)
    {
        if (HarvestcraftRecipes.shippingRecipes == null)
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
        
        if (!HarvestcraftRecipes.doesShippingRecipeExist(inputStack, outputStack))
            HarvestcraftRecipes.shippingRecipes.add(new ShippingBinData(outputStack, inputStack, cost));
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
        if (HarvestcraftRecipes.shippingRecipes == null)
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
        return HarvestcraftRecipes.pressingRecipes != null;
    }
    
    @ZenMethod
    @ZenDoc("Adds a new entry to the presser.\r\n" +
            "Input stack accepts wildcard metadata value (32767).")
    public static void addPressingRecipe(IItemStack input, IItemStack firstOutput, @Optional IItemStack secondOutput)
    {
        if (HarvestcraftRecipes.pressingRecipes == null)
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
        
        if (HarvestcraftRecipes.doesPressingRecipeExist(stackInput))
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
        
        HarvestcraftRecipes.pressingRecipes.put(stackInput, new ItemStack[]{ stackOutputFirst, stackOutputSecond });
    }
    
    @ZenMethod
    @ZenDoc("Removes an entry from the presser.")
    public static void removePressingRecipe(IItemStack input)
    {
        if (HarvestcraftRecipes.pressingRecipes == null)
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
        return HarvestcraftRecipes.grindingRecipes != null;
    }
    
    @ZenMethod
    @ZenDoc("Adds a new entry to the presser.\r\n" +
            "Input stack accepts wildcard metadata value (32767).")
    public static void addGrindingRecipe(IItemStack input, IItemStack firstOutput, @Optional IItemStack secondOutput)
    {
        if (HarvestcraftRecipes.grindingRecipes == null)
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
        
        if (HarvestcraftRecipes.doesGrindingRecipeExist(stackInput))
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
        
        HarvestcraftRecipes.grindingRecipes.put(stackInput, new ItemStack[]{ stackOutputFirst, stackOutputSecond });
    }
    
    @ZenMethod
    @ZenDoc("Removes an entry from the grinder.")
    public static void removeGrindingRecipe(IItemStack input)
    {
        if (HarvestcraftRecipes.grindingRecipes == null)
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
        return HarvestcraftRecipes.waterFilterRecipes != null;
    }
    
    @ZenMethod
    @ZenDoc("Adds a new entry to the presser.\r\n" +
            "Input stack accepts wildcard metadata value (32767).")
    public static void addWaterFilteringRecipe(IItemStack input, IItemStack firstOutput, @Optional IItemStack secondOutput)
    {
        if (HarvestcraftRecipes.waterFilterRecipes == null)
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
        
        if (HarvestcraftRecipes.doesFilteringRecipeExist(stackInput))
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
        
        HarvestcraftRecipes.waterFilterRecipes.put(stackInput, new ItemStack[]{ stackOutputFirst, stackOutputSecond });
    }
    
    @ZenMethod
    @ZenDoc("Removes an entry from the grinder.")
    public static void removeWaterFilteringRecipe(IItemStack input)
    {
        if (HarvestcraftRecipes.waterFilterRecipes == null)
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
