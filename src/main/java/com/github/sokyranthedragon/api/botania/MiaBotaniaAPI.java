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
        {
            oreWeightsEnd = new HashMap<>();
            
            // Quark
            addOreWeightEnd("oreEnderBiotite", 2000);
            // Biomes O' Plenty
            addOreWeightEnd("oreEnderAmethyst", 2000);
            // Thermal Foundation
            addOreWeightEnd("oreClathrateEnder", 1250);
            
            addOreWeightEnd("oreEnderAluminum", 2000);
            addOreWeightEnd("oreEnderAmber", 1000);
            addOreWeightEnd("oreEnderApatite", 750);
            addOreWeightEnd("oreEnderBlueTopaz", 2000);
            addOreWeightEnd("oreEnderCertusQuartz", 2000);
            addOreWeightEnd("oreEnderChimerite", 2000);
            addOreWeightEnd("oreEnderCinnabar", 1500);
            addOreWeightEnd("oreEnderDark", 700);
            addOreWeightEnd("oreEnderDarkIron", 800);
            addOreWeightEnd("oreEnderFzDarkIron", 800);
            addOreWeightEnd("oreEnderEmerald", 400);
            addOreWeightEnd("oreEnderGalena", 500);
            addOreWeightEnd("oreEnderInfusedAir", 500);
            addOreWeightEnd("oreEnderInfusedEarth", 500);
            addOreWeightEnd("oreEnderInfusedEntropy", 500);
            addOreWeightEnd("oreEnderInfusedFire", 500);
            addOreWeightEnd("oreEnderInfusedOrder", 500);
            addOreWeightEnd("oreEnderInfusedWater", 500);
            addOreWeightEnd("oreEnderLapis", 700);
            addOreWeightEnd("oreEnderMithril", 8);
            addOreWeightEnd("oreEnderOlivine", 600);
            addOreWeightEnd("oreEnderRuby", 600);
            addOreWeightEnd("oreEnderSapphire", 600);
            addOreWeightEnd("oreEnderUranium", 750);
            addOreWeightEnd("oreEnderVinteum", 300);
            addOreWeightEnd("oreEnderYellorite", 200);
            addOreWeightEnd("oreEnderZinc", 3500);
            addOreWeightEnd("oreEnderMythril", 3500);
            addOreWeightEnd("oreEnderAdamantium", 1300);
            addOreWeightEnd("oreEnderTungsten", 2000);
            addOreWeightEnd("oreEnderOsmium", 3500);
            addOreWeightEnd("oreEnderQuartzBlack", 2500);
            addOreWeightEnd("oreEnderQuartz", 9000);
            addOreWeightEnd("oreEnderCobalt", 250);
            addOreWeightEnd("oreEnderArdite", 250);
            addOreWeightEnd("oreEnderFirestone", 5);
            addOreWeightEnd("oreEnderCoal", 8500);
            addOreWeightEnd("oreEnderCopper", 2500);
            addOreWeightEnd("oreEnderDiamond", 100);
            addOreWeightEnd("oreEnderEssence", 1500);
            addOreWeightEnd("oreEnderGold", 1500);
            addOreWeightEnd("oreEnderIron", 2500);
            addOreWeightEnd("oreEnderLead", 1500);
            addOreWeightEnd("oreEnderNickel", 800);
            addOreWeightEnd("oreEnderPlatinum", 100);
            addOreWeightEnd("oreEnderRedstone", 2500);
            addOreWeightEnd("oreEnderSilver", 750);
            addOreWeightEnd("oreEnderSteel", 800);
            addOreWeightEnd("oreEnderTin", 200);
            addOreWeightEnd("oreEnderFyrite", 500);
            addOreWeightEnd("oreEnderAshstone", 500);
            addOreWeightEnd("oreEnderDragonstone", 100);
            addOreWeightEnd("oreEnderArgonite", 500);
            addOreWeightEnd("oreEnderOnyx", 250);
            addOreWeightEnd("oreEnderHaditeCoal", 250);
        }
        else
            oreWeightsEnd = null;
    }
}
