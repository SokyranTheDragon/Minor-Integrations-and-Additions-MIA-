package com.github.exploder1531.mia.integrations.jei.categories.cauldron;

import com.github.exploder1531.mia.integrations.ModLoadStatus;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class CauldronRegistry
{
    private Set<CauldronEntry> registry;
    private static CauldronRegistry instance;
    
    private CauldronRegistry()
    {
    }
    
    @Nullable
    public static CauldronRegistry getInstance()
    {
        if (ModLoadStatus.dungeonTacticsLoaded)
        {
            if (instance != null)
                return instance;
            instance = new CauldronRegistry();
            instance.registry = new LinkedHashSet<>();
            return instance;
        }
        else
            return null;
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
        return getInstance() != null ? getInstance().getRecipes() : new HashSet<>();
    }
    
    public void clear()
    {
        registry.clear();
    }
}
