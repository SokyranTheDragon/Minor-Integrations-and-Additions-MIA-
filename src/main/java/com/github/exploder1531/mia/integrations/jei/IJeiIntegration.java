package com.github.exploder1531.mia.integrations.jei;

import com.github.exploder1531.mia.integrations.base.IModIntegration;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Collection;

@ParametersAreNonnullByDefault
public interface IJeiIntegration extends IModIntegration
{
    default void register(IModRegistry registry, Collection<String> registeredCategories)
    {
    }
    
    default void registerCategories(IRecipeCategoryRegistration registry, Collection<String> registeredCategories)
    {
    }
    
    default void registerRecipes()
    {
    }
}
