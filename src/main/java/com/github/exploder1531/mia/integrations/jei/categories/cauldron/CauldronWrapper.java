package com.github.exploder1531.mia.integrations.jei.categories.cauldron;

import com.google.common.collect.Lists;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeWrapper;

import javax.annotation.Nonnull;

public class CauldronWrapper implements IRecipeWrapper
{
    private final CauldronEntry entry;
    
    public CauldronWrapper(CauldronEntry entry)
    {
        this.entry = entry;
    }
    
    @Override
    public void getIngredients(@Nonnull IIngredients ingredients)
    {
        ingredients.setOutputs(VanillaTypes.ITEM, Lists.newArrayList(entry.getOutput(), entry.getByproduct()));
    }
    
    public CauldronEntry getEntry()
    {
        return entry;
    }
}
