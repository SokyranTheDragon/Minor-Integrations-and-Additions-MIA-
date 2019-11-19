package com.github.exploder1531.mia.integrations;

import net.minecraftforge.fml.common.Loader;

public class ModLoadStatus
{
    private ModLoadStatus()
    {
    }
    
    public static boolean extraUtilitiesLoaded = false;
    public static boolean thermalFoundationLoaded = false;
    public static boolean thermalExpansionLoaded = false;
    public static boolean tinkersConstructLoaded = false;
    public static boolean jerLoaded = false;
    public static boolean iceAndFireLoaded = false;
    public static boolean hatcheryLoaded = false;
    public static boolean thaumcraftLoaded = false;
    public static boolean theOneProbeLoaded = false;
    public static boolean moCreaturesLoaded = false;
    public static boolean harvestcraftLoaded = false;
    
    public static void preInit()
    {
        if (Loader.isModLoaded(ModIds.EXTRA_UTILITIES))
            extraUtilitiesLoaded = true;
        if (Loader.isModLoaded(ModIds.THERMAL_FOUNDATION))
            thermalFoundationLoaded = true;
        if (Loader.isModLoaded(ModIds.THERMAL_EXPANSION))
            thermalExpansionLoaded = true;
        if (Loader.isModLoaded(ModIds.TINKERS_CONSTRUCT))
            tinkersConstructLoaded = true;
        if (Loader.isModLoaded(ModIds.JER))
            jerLoaded = true;
        if (Loader.isModLoaded(ModIds.ICE_AND_FIRE))
            iceAndFireLoaded = true;
        if (Loader.isModLoaded(ModIds.HATCHERY))
            hatcheryLoaded = true;
        if (Loader.isModLoaded(ModIds.THAUMCRAFT))
            thaumcraftLoaded = true;
        if (Loader.isModLoaded(ModIds.THE_ONE_PROBE))
            theOneProbeLoaded = true;
        if (Loader.isModLoaded(ModIds.MO_CREATURES))
            moCreaturesLoaded = true;
        if (Loader.isModLoaded(ModIds.HARVESTCRAFT))
            harvestcraftLoaded = true;
    }
}
