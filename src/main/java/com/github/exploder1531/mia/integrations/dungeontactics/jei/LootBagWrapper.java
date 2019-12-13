package com.github.exploder1531.mia.integrations.dungeontactics.jei;

import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeWrapper;

import javax.annotation.Nonnull;
import java.util.stream.Collectors;

public class LootBagWrapper implements IRecipeWrapper
{
    private final LootBagEntry entry;
    
    public LootBagWrapper(LootBagEntry entry)
    {
        this.entry = entry;
    }
    
    @Override
    public void getIngredients(@Nonnull IIngredients ingredients)
    {
        ingredients.setInput(VanillaTypes.ITEM, entry.getInput());
        ingredients.setOutputs(VanillaTypes.ITEM, entry.getOutputs().stream().map(LootBagEntry.BagOutputEntry::getItem).collect(Collectors.toList()));
    }
    
    public LootBagEntry getEntry()
    {
        return entry;
    }
}
