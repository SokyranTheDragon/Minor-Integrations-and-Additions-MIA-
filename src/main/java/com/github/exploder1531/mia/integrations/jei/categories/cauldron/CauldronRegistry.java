package com.github.exploder1531.mia.integrations.jei.categories.cauldron;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.LinkedHashSet;
import java.util.Set;

public class CauldronRegistry
{
    private Set<CauldronEntry> registry;
    private static CauldronRegistry instance;
    
    private CauldronRegistry()
    {
    }
    
    @Nonnull
    public static CauldronRegistry getInstance()
    {
        if (instance != null)
            return instance;
        instance = new CauldronRegistry();
        instance.registry = new LinkedHashSet<>();
        return instance;
    }
    
    @SuppressWarnings("UnusedReturnValue")
    public boolean registerCauldronRecipe(@Nullable CauldronEntry entry)
    {
        return entry != null && registry.add(entry);
    }
    
    @Nonnull
    public Set<CauldronEntry> getRecipes()
    {
        return registry;
    }
    
    @Nonnull
    public static Set<CauldronEntry> getRecipesOrEmpty()
    {
        return getInstance().getRecipes();
    }
    
    public void clear()
    {
        registry.clear();
    }
}
