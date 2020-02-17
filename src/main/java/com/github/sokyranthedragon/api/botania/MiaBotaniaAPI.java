package com.github.sokyranthedragon.api.botania;

import com.github.sokyranthedragon.mia.integrations.ModIds;
import net.minecraftforge.fml.common.Loader;

import java.util.HashMap;
import java.util.Map;

public class MiaBotaniaAPI
{
    public static final Map<String, Integer> oreWeightsEnd;
    
    public static void addOreWeightEnd(String ore, int weight)
    {
        if (oreWeightsEnd == null)
            return;
        
        if (ore.contains("Ender"))
            oreWeightsEnd.put(ore.replace("Ender", "End"), weight);
        oreWeightsEnd.put(ore, weight);
    }
    
    public static int getOreWeight(String ore)
    {
        return oreWeightsEnd.get(ore);
    }
    
    static
    {
        if (Loader.isModLoaded(ModIds.ConstantIds.BOTANIA))
            oreWeightsEnd = new HashMap<>();
        else
            oreWeightsEnd = null;
    }
}
