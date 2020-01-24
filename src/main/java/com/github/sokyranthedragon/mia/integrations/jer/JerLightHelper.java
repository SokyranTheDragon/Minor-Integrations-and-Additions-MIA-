package com.github.sokyranthedragon.mia.integrations.jer;

import gnu.trove.map.hash.TIntObjectHashMap;
import jeresources.api.conditionals.LightLevel;

import java.lang.reflect.Constructor;

public class JerLightHelper
{
    private JerLightHelper() { }
    
    private static final TIntObjectHashMap<LightLevel> lightLevelsBelow = new TIntObjectHashMap<>();
    
    static
    {
        lightLevelsBelow.put(4, LightLevel.bat);
        lightLevelsBelow.put(8, LightLevel.hostile);
        lightLevelsBelow.put(12, LightLevel.blaze);
    }
    
    public static LightLevel getLightLevelBelow(int value)
    {
        return getLightLevelBelow(value, LightLevel.Relative.below);
    }
    
    @SuppressWarnings("WeakerAccess")
    public static LightLevel getLightLevelBelow(int value, LightLevel.Relative relative)
    {
        if (lightLevelsBelow.containsKey(value))
            return lightLevelsBelow.get(value);
        
        LightLevel light = createLightLevel(value, relative);
        
        if (light != null)
        {
            lightLevelsBelow.put(value, light);
            return light;
        }
        
        return LightLevel.any;
    }
    
    private static LightLevel createLightLevel(int value, LightLevel.Relative relative)
    {
        try
        {
            Constructor<LightLevel> constructor = LightLevel.class.getDeclaredConstructor(int.class, LightLevel.Relative.class);
            constructor.setAccessible(true);
            return constructor.newInstance(value, relative);
        }
        catch (Exception e)
        {
            return null;
        }
    }
}
