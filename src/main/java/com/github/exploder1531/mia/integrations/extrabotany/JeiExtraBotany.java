package com.github.exploder1531.mia.integrations.extrabotany;

import com.github.exploder1531.mia.Mia;
import com.github.exploder1531.mia.integrations.ModIds;
import com.github.exploder1531.mia.integrations.jei.IJeiIntegration;
import com.github.exploder1531.mia.integrations.jei.MiaJeiPlugin;
import com.github.exploder1531.mia.integrations.jei.categories.lootbag.LootBagCategory;
import com.github.exploder1531.mia.integrations.jei.categories.lootbag.LootBagEntry;
import com.github.exploder1531.mia.integrations.jei.categories.lootbag.LootBagRegistry;
import com.github.exploder1531.mia.integrations.jei.categories.lootbag.LootBagWrapper;
import com.meteor.extrabotany.api.item.WeightCategory;
import com.meteor.extrabotany.common.item.ModItems;
import com.meteor.extrabotany.common.item.bonus.ItemBonusBase;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.github.exploder1531.mia.integrations.ModLoadStatus.jerLoaded;

public class JeiExtraBotany implements IJeiIntegration
{
    @Override
    public void register(IModRegistry registry, Collection<String> registeredCategories)
    {
        if (registeredCategories.add(MiaJeiPlugin.Categories.LOOT_BAG))
        {
            registry.handleRecipes(LootBagEntry.class, LootBagWrapper::new, MiaJeiPlugin.Categories.LOOT_BAG);
            registry.addRecipes(LootBagRegistry.getRecipesOrEmpty(), MiaJeiPlugin.Categories.LOOT_BAG);
        }
        
        registry.addRecipeCatalyst(new ItemStack(ModItems.rewardbag, 1, 0), MiaJeiPlugin.Categories.LOOT_BAG);
        registry.addRecipeCatalyst(new ItemStack(ModItems.rewardbag, 1, 1), MiaJeiPlugin.Categories.LOOT_BAG);
        registry.addRecipeCatalyst(new ItemStack(ModItems.rewardbag, 1, 2), MiaJeiPlugin.Categories.LOOT_BAG);
        registry.addRecipeCatalyst(new ItemStack(ModItems.rewardbag, 1, 3), MiaJeiPlugin.Categories.LOOT_BAG);
        registry.addRecipeCatalyst(new ItemStack(ModItems.rewardbag943), MiaJeiPlugin.Categories.LOOT_BAG);
        registry.addRecipeCatalyst(new ItemStack(ModItems.candybag), MiaJeiPlugin.Categories.LOOT_BAG);
    }
    
    @Override
    public void registerCategories(IRecipeCategoryRegistration registry, Collection<String> registeredCategories)
    {
        if (registeredCategories.add(MiaJeiPlugin.Categories.LOOT_BAG))
            registry.addRecipeCategories(new LootBagCategory(registry.getJeiHelpers().getGuiHelper(), ModItems.rewardbag));
    }
    
    @Override
    public void registerRecipes()
    {
        LootBagRegistry lootBagRegistry = LootBagRegistry.getInstance();
        
        if (lootBagRegistry != null)
        {
            registerLootBag(lootBagRegistry, new ItemStack(ModItems.rewardbag, 1, 0));
            registerLootBag(lootBagRegistry, new ItemStack(ModItems.rewardbag, 1, 1));
            registerLootBag(lootBagRegistry, new ItemStack(ModItems.rewardbag, 1, 2));
            registerLootBag(lootBagRegistry, new ItemStack(ModItems.rewardbag, 1, 3));
            registerLootBag(lootBagRegistry, new ItemStack(ModItems.rewardbag943));
            registerLootBag(lootBagRegistry, new ItemStack(ModItems.candybag));
        }
        else if (jerLoaded)
            Mia.LOGGER.error("Could not access Loot Bag recipe registry, this shouldn't have happened as Dungeon Tactics and JER are loaded. Something is very wrong.");
    }
    
    @Nonnull
    @Override
    public String getModId()
    {
        return ModIds.EXTRABOTANY;
    }
    
    private void registerLootBag(LootBagRegistry registry, ItemStack stack)
    {
        if (!(stack.getItem() instanceof ItemBonusBase))
        {
            Mia.LOGGER.error("Tried to register incorrect ExtraBotany loot bag:\n" + stack.toString());
            return;
        }
        
        ItemBonusBase item = (ItemBonusBase) stack.getItem();
        List<WeightCategory> possibleOutputs = item.getWeightCategory(stack);
        
        float totalWeight = (float) possibleOutputs.stream().mapToInt(WeightCategory::getWeight).sum();
        Set<LootBagEntry.BagOutputEntry> bagOutputs =
                possibleOutputs.stream()
                               .map(entry -> new LootBagEntry.BagOutputEntry(Collections.singletonList(entry.getCategory()), (float) entry.getWeight() / totalWeight * 100f))
                               .collect(Collectors.toSet());
        
        Collection<LootBagEntry> entries = LootBagEntry.getEntries(stack, bagOutputs);
        
        for (LootBagEntry entry : entries)
            registry.registerLootBagRecipe(entry);
    }
}
