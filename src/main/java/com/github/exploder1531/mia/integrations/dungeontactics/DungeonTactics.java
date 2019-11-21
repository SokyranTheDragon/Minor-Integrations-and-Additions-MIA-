package com.github.exploder1531.mia.integrations.dungeontactics;

import com.github.exploder1531.mia.integrations.ModIds;
import com.github.exploder1531.mia.integrations.base.IBaseMod;
import com.github.exploder1531.mia.integrations.base.IModIntegration;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import pegbeard.dungeontactics.handlers.DTBlocks;
import pegbeard.dungeontactics.handlers.DTItems;
import pegbeard.dungeontactics.items.unique.DTIcarusRing;

import java.util.function.BiConsumer;

import static com.github.exploder1531.mia.integrations.ModLoadStatus.thermalExpansionLoaded;
import static com.github.exploder1531.mia.integrations.ModLoadStatus.tinkersConstructLoaded;

public class DungeonTactics implements IBaseMod
{
    @Override
    public void register(BiConsumer<String, IModIntegration> modIntegration)
    {
        if (tinkersConstructLoaded)
            modIntegration.accept(ModIds.TINKERS_CONSTRUCT, new TConstructDungeonTacticsIntegration());
        if (thermalExpansionLoaded)
            modIntegration.accept(ModIds.THERMAL_EXPANSION, new ThermalExpansionDungeonTacticsIntegration());
    }
    
    @Override
    public void init(FMLInitializationEvent event)
    {
        OreDictionary.registerOre("blockGlass", DTBlocks.DUNGEON_GLASS);
        OreDictionary.registerOre("blockGlassBlack", DTBlocks.DUNGEON_GLASS);
    
        OreDictionary.registerOre("listAllberry", DTItems.CHERRYBOMB);
        OreDictionary.registerOre("listAllfruit", DTItems.CHERRYBOMB);
        OreDictionary.registerOre("listAllberry", DTItems.INCINDIBERRY);
        OreDictionary.registerOre("listAllfruit", DTItems.INCINDIBERRY);
        OreDictionary.registerOre("listAllberry", DTItems.GLOWCURRENT);
        OreDictionary.registerOre("listAllfruit", DTItems.GLOWCURRENT);
    
        OreDictionary.registerOre("foodToast", DTItems.TOAST);
        OreDictionary.registerOre("foodToastslice", DTItems.TOASTSLICE);
        OreDictionary.registerOre("foodJamtoast", DTItems.JAMSLICE);
        OreDictionary.registerOre("foodBreadslice", DTItems.BREADSLICE);
    }
}
