package com.github.exploder1531.mia.integrations.dungeontactics.jei;

import com.github.exploder1531.mia.integrations.ModLoadStatus;
import jeresources.entry.MobEntry;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
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
    
    public boolean registerCauldronRecipe(CauldronEntry entry)
    {
        return entry != null && registry.add(entry);
    }
    
    @SuppressWarnings("unchecked")
    public List<CauldronEntry> getRecipes()
    {
        return new ArrayList(registry);
    }
    
    public static List<CauldronEntry> getRecipesOrEmpty()
    {
        return getInstance() != null ? getInstance().getRecipes() : new ArrayList<>();
    }
    
    public void clear()
    {
        registry.clear();
    }
}
