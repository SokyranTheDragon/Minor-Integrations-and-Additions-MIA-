package com.github.sokyranthedragon.mia.integrations.jer;

import gnu.trove.map.hash.TIntObjectHashMap;
import jeresources.api.conditionals.LightLevel;

import java.lang.reflect.Constructor;

public class JerLightHelper
{
    private JerLightHelper() { }
    
    private static final TIntObjectHashMap<LightLevel> lightLevelsBelow = new TIntObjectHashMap<>();
    private static final TIntObjectHashMap<LightLevel> lightLevelsAbove = new TIntObjectHashMap<>();
    
    static
    {
        lightLevelsBelow.put(4, LightLevel.bat);
        lightLevelsBelow.put(8, LightLevel.hostile);
        lightLevelsBelow.put(12, LightLevel.blaze);
        
        lightLevelsAbove.put(-1, LightLevel.any);
    }
    
    public static LightLevel getLightLevelBelow(int value)
    {
        return getLightLevel(value, LightLevel.Relative.below);
    }
    
    public static LightLevel getLightLevelAbove(int value)
    {
        return getLightLevel(value, LightLevel.Relative.above);
    }
    
    private static LightLevel getLightLevel(int value, LightLevel.Relative relative)
    {
        if (relative == LightLevel.Relative.below)
        {
            if (lightLevelsBelow.containsKey(value))
                return lightLevelsBelow.get(value);
        }
        else if (lightLevelsAbove.containsKey(value))
            return lightLevelsAbove.get(value);
        
        LightLevel light = createLightLevel(value, relative);
        
        if (light != null)
        {
            if (relative == LightLevel.Relative.below)
                lightLevelsBelow.put(value, light);
            else
                lightLevelsAbove.put(value, light);
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
