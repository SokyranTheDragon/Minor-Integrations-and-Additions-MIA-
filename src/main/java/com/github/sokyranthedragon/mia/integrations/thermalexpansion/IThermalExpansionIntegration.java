package com.github.sokyranthedragon.mia.integrations.thermalexpansion;

import com.github.sokyranthedragon.mia.integrations.base.IModIntegration;

public interface IThermalExpansionIntegration extends IModIntegration
{
    default void addRecipes()
    {
    }
    
    default void addPostInitRecipes()
    {
    }
}
