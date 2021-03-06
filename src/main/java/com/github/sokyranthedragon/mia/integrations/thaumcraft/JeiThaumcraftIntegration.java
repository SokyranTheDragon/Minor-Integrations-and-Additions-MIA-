package com.github.sokyranthedragon.mia.integrations.thaumcraft;

import com.github.sokyranthedragon.mia.Mia;
import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.integrations.jei.IJeiIntegration;
import com.github.sokyranthedragon.mia.integrations.jei.MiaJeiPlugin;
import com.github.sokyranthedragon.mia.integrations.jei.categories.lootbag.LootBagCategory;
import com.github.sokyranthedragon.mia.integrations.jei.categories.lootbag.LootBagEntry;
import com.github.sokyranthedragon.mia.integrations.jei.categories.lootbag.LootBagRegistry;
import com.github.sokyranthedragon.mia.integrations.jei.categories.lootbag.LootBagWrapper;
import mcp.MethodsReturnNonnullByDefault;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import net.minecraft.item.ItemStack;
import thaumcraft.api.blocks.BlocksTC;
import thaumcraft.api.internal.WeightedRandomLoot;
import thaumcraft.api.items.ItemsTC;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
class JeiThaumcraftIntegration implements IJeiIntegration
{
    @Override
    public void register(IModRegistry registry, Collection<String> registeredCategories)
    {
        if (registeredCategories.add(MiaJeiPlugin.Categories.LOOT_BAG))
        {
            registry.handleRecipes(LootBagEntry.class, LootBagWrapper::new, MiaJeiPlugin.Categories.LOOT_BAG);
            registry.addRecipes(LootBagRegistry.getRecipesOrEmpty(), MiaJeiPlugin.Categories.LOOT_BAG);
        }
        
        for (int i = 0; i < 3; i++)
            registry.addRecipeCatalyst(new ItemStack(ItemsTC.lootBag, 1, i), MiaJeiPlugin.Categories.LOOT_BAG);
        registry.addRecipeCatalyst(new ItemStack(BlocksTC.lootCrateCommon), MiaJeiPlugin.Categories.LOOT_BAG);
        registry.addRecipeCatalyst(new ItemStack(BlocksTC.lootCrateUncommon), MiaJeiPlugin.Categories.LOOT_BAG);
        registry.addRecipeCatalyst(new ItemStack(BlocksTC.lootCrateRare), MiaJeiPlugin.Categories.LOOT_BAG);
        registry.addRecipeCatalyst(new ItemStack(BlocksTC.lootUrnCommon), MiaJeiPlugin.Categories.LOOT_BAG);
        registry.addRecipeCatalyst(new ItemStack(BlocksTC.lootUrnUncommon), MiaJeiPlugin.Categories.LOOT_BAG);
        registry.addRecipeCatalyst(new ItemStack(BlocksTC.lootUrnRare), MiaJeiPlugin.Categories.LOOT_BAG);
    }
    
    @Override
    public void registerCategories(IRecipeCategoryRegistration registry, Collection<String> registeredCategories)
    {
        if (registeredCategories.add(MiaJeiPlugin.Categories.LOOT_BAG))
            registry.addRecipeCategories(new LootBagCategory(registry.getJeiHelpers().getGuiHelper(), new ItemStack(ItemsTC.lootBag, 1, 2)));
    }
    
    @Override
    public void registerRecipes()
    {
        LootBagRegistry lootBagRegistry = LootBagRegistry.getInstance();
        
        if (lootBagRegistry != null)
        {
            registerLootBag(lootBagRegistry, WeightedRandomLoot.lootBagCommon, new ItemStack(ItemsTC.lootBag, 1, 0), new ItemStack(BlocksTC.lootCrateCommon), new ItemStack(BlocksTC.lootUrnCommon));
            registerLootBag(lootBagRegistry, WeightedRandomLoot.lootBagUncommon, new ItemStack(ItemsTC.lootBag, 1, 1), new ItemStack(BlocksTC.lootCrateUncommon), new ItemStack(BlocksTC.lootUrnUncommon));
            registerLootBag(lootBagRegistry, WeightedRandomLoot.lootBagRare, new ItemStack(ItemsTC.lootBag, 1, 2), new ItemStack(BlocksTC.lootCrateRare), new ItemStack(BlocksTC.lootUrnRare));
        }
        else
            Mia.LOGGER.error("Could not access Loot Bag recipe registry, this shouldn't have happened. Something is very wrong.");
    }
    
    @Nonnull
    @Override
    public ModIds getModId()
    {
        return ModIds.THAUMCRAFT;
    }
    
    private void registerLootBag(LootBagRegistry registry, Collection<WeightedRandomLoot> possibleOutputs, ItemStack... stacks)
    {
        float totalWeight = (float) possibleOutputs.stream().mapToInt(entry -> entry.itemWeight).sum();
        Set<LootBagEntry.BagOutputEntry> bagOutputs =
                possibleOutputs.stream()
                               .map(entry -> new LootBagEntry.BagOutputEntry(Collections.singletonList(entry.item), (float) entry.itemWeight / totalWeight * 100f))
                               .collect(Collectors.toSet());
        
        Collection<LootBagEntry> entries = LootBagEntry.getEntries(Arrays.asList(stacks), bagOutputs);
        
        for (LootBagEntry entry : entries)
            registry.registerLootBagRecipe(entry);
    }
}
