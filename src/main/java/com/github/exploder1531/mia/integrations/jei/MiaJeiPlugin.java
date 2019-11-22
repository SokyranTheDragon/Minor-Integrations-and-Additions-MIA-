package com.github.exploder1531.mia.integrations.jei;

import com.github.exploder1531.mia.integrations.ModLoadStatus;
import com.github.exploder1531.mia.integrations.dungeontactics.jei.CauldronCategory;
import com.github.exploder1531.mia.integrations.dungeontactics.jei.CauldronEntry;
import com.github.exploder1531.mia.integrations.dungeontactics.jei.CauldronRegistry;
import com.github.exploder1531.mia.integrations.dungeontactics.jei.CauldronWrapper;
import mezz.jei.api.IJeiRuntime;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;

@SuppressWarnings("unused")
@JEIPlugin
public class MiaJeiPlugin implements IModPlugin
{
    @Override
    public void register(IModRegistry registry)
    {
        if (ModLoadStatus.dungeonTacticsLoaded)
        {
            registry.handleRecipes(CauldronEntry.class, CauldronWrapper::new, "mia.alchemical_cauldron");
            registry.addRecipes(CauldronRegistry.getRecipesOrEmpty(), "mia.alchemical_cauldron");
        }
    }
    
    @Override
    public void onRuntimeAvailable(IJeiRuntime jeiRuntime)
    {
        // Hide categories here
    }
    
    @Override
    public void registerCategories(IRecipeCategoryRegistration registry)
    {
        if (ModLoadStatus.dungeonTacticsLoaded)
            registry.addRecipeCategories(new CauldronCategory(registry.getJeiHelpers().getGuiHelper()));
    }
}
