package com.github.sokyranthedragon.mia.integrations.harvestcraft;

import com.github.sokyranthedragon.mia.Mia;
import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.integrations.jei.IJeiIntegration;
import com.github.sokyranthedragon.mia.integrations.jei.MiaJeiPlugin;
import com.github.sokyranthedragon.mia.integrations.jei.categories.lootbag.LootBagCategory;
import com.github.sokyranthedragon.mia.integrations.jei.categories.lootbag.LootBagEntry;
import com.github.sokyranthedragon.mia.integrations.jei.categories.lootbag.LootBagRegistry;
import com.github.sokyranthedragon.mia.integrations.jei.categories.lootbag.LootBagWrapper;
import com.pam.harvestcraft.blocks.BlockRegistry;
import com.pam.harvestcraft.blocks.blocks.BlockBaseGarden;
import mcp.MethodsReturnNonnullByDefault;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import net.minecraft.item.ItemStack;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.*;
import java.util.stream.Collectors;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
class JeiHarvestcraftIntegration implements IJeiIntegration
{
    @Override
    public void register(IModRegistry registry, Collection<String> registeredCategories)
    {
        if (registeredCategories.add(MiaJeiPlugin.Categories.LOOT_BAG))
        {
            registry.handleRecipes(LootBagEntry.class, LootBagWrapper::new, MiaJeiPlugin.Categories.LOOT_BAG);
            registry.addRecipes(LootBagRegistry.getRecipesOrEmpty(), MiaJeiPlugin.Categories.LOOT_BAG);
        }
        
        for (BlockBaseGarden garden : BlockRegistry.gardens.values())
            registry.addRecipeCatalyst(new ItemStack(garden), MiaJeiPlugin.Categories.LOOT_BAG);
    }
    
    @Override
    public void registerCategories(IRecipeCategoryRegistration registry, Collection<String> registeredCategories)
    {
        if (registeredCategories.add(MiaJeiPlugin.Categories.LOOT_BAG))
            registry.addRecipeCategories(new LootBagCategory(registry.getJeiHelpers().getGuiHelper(), new ItemStack(BlockRegistry.getGarden(BlockRegistry.shadedGarden))));
    }
    
    @Override
    public void registerRecipes()
    {
        LootBagRegistry registry = LootBagRegistry.getInstance();
        
        if (registry != null)
        {
            for (Map.Entry<String, BlockBaseGarden> gardenEntries : BlockRegistry.gardens.entrySet())
            {
                List<ItemStack> possibleOutputs = BlockBaseGarden.drops.get(gardenEntries.getKey());
                
                if (possibleOutputs != null && !possibleOutputs.isEmpty())
                {
                    final float totalWeight = possibleOutputs.size();
                    Set<LootBagEntry.BagOutputEntry> bagOutputs =
                            possibleOutputs.stream()
                                           .map(entry -> new LootBagEntry.BagOutputEntry(Collections.singletonList(entry), 1f / totalWeight * 100f))
                                           .collect(Collectors.toSet());
                    
                    Collection<LootBagEntry> entries = LootBagEntry.getEntries(new ItemStack(gardenEntries.getValue()), bagOutputs);
                    for (LootBagEntry entry : entries)
                        registry.registerLootBagRecipe(entry);
                }
            }
        }
        else
            Mia.LOGGER.error("Could not access Loot Bag recipe registry, this shouldn't have happened as Thaumcraft and JER are loaded. Something is very wrong.");
    }
    
    @Override
    public ModIds getModId()
    {
        return ModIds.HARVESTCRAFT;
    }
}
