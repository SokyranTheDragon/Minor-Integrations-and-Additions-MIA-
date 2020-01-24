package com.github.sokyranthedragon.mia.utilities;

import vazkii.quark.base.module.Feature;
import vazkii.quark.base.module.ModuleLoader;

public class QuarkUtils
{
    private QuarkUtils()
    {
    }
    
    public static boolean isFeatureEnabled(Class<? extends Feature> featureClass)
    {
        Feature feature = ModuleLoader.featureInstances.get(featureClass);
        return feature != null && feature.isEnabled();
    }
}
