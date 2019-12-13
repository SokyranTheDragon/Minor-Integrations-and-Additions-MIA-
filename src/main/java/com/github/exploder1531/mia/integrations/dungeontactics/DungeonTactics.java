package com.github.exploder1531.mia.integrations.dungeontactics;

import com.github.exploder1531.mia.Mia;
import com.github.exploder1531.mia.integrations.ModIds;
import com.github.exploder1531.mia.integrations.base.IBaseMod;
import com.github.exploder1531.mia.integrations.base.IModIntegration;
import com.google.common.collect.Lists;
import net.minecraft.world.storage.loot.LootPool;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.oredict.OreDictionary;
import pegbeard.dungeontactics.handlers.DTBlocks;
import pegbeard.dungeontactics.handlers.DTItems;
import pegbeard.dungeontactics.handlers.DTLoots;

import java.util.List;
import java.util.function.BiConsumer;

import static com.github.exploder1531.mia.config.DungeonTacticsConfiguration.dungeonTacticsAdditionsEnabled;
import static com.github.exploder1531.mia.config.DungeonTacticsConfiguration.registerCustomBagLoot;
import static com.github.exploder1531.mia.integrations.ModLoadStatus.*;

public class DungeonTactics implements IBaseMod
{
    private final List<IDungeonTacticsIntegration> modIntegrations = Lists.newLinkedList();
    
    @Override
    public void register(BiConsumer<String, IModIntegration> modIntegration)
    {
        if (tinkersConstructLoaded)
            modIntegration.accept(ModIds.TINKERS_CONSTRUCT, new TConstructDungeonTacticsIntegration());
        if (thermalExpansionLoaded)
            modIntegration.accept(ModIds.THERMAL_EXPANSION, new ThermalExpansionDungeonTacticsIntegration());
        if (jeiLoaded)
            modIntegration.accept(ModIds.JEI, new JeiDungeonTacticsIntegration());
        if (jerLoaded)
            modIntegration.accept(ModIds.JER, new JerDungeonTacticsIntegration());
    }
    
    @Override
    public void addIntegration(IModIntegration integration)
    {
        if (integration instanceof IDungeonTacticsIntegration)
        {
            modIntegrations.add((IDungeonTacticsIntegration) integration);
            return;
        }
        
        Mia.LOGGER.warn("Incorrect DT integration with id of " + integration.getModId() + ": " + integration.toString());
    }
    
    @Override
    public void lootLoad(LootTableLoadEvent event)
    {
        if (!dungeonTacticsAdditionsEnabled || !registerCustomBagLoot)
            return;
        
        LootPool main = event.getTable().getPool("main");
        IDungeonTacticsIntegration.BagTypes bagType = null;
        
        if (event.getName().equals(DTLoots.ARBOUR_LOOT))
            bagType = IDungeonTacticsIntegration.BagTypes.ARBOUR;
        else if (event.getName().equals(DTLoots.BOOK_LOOT))
            bagType = IDungeonTacticsIntegration.BagTypes.BOOK;
        else if (event.getName().equals(DTLoots.FOOD_LOOT))
            bagType = IDungeonTacticsIntegration.BagTypes.FOOD;
        else if (event.getName().equals(DTLoots.MAGIC_LOOT))
            bagType = IDungeonTacticsIntegration.BagTypes.MAGIC;
        else if (event.getName().equals(DTLoots.ORE_LOOT))
            bagType = IDungeonTacticsIntegration.BagTypes.ORE;
        else if (event.getName().equals(DTLoots.POTION_LOOT))
            bagType = IDungeonTacticsIntegration.BagTypes.POTION;
        else if (event.getName().equals(DTLoots.QUIVER_LOOT))
            bagType = IDungeonTacticsIntegration.BagTypes.QUIVER;
        else if (event.getName().equals(DTLoots.RECORD_LOOT))
            bagType = IDungeonTacticsIntegration.BagTypes.RECORD;
        else if (event.getName().equals(DTLoots.SAMHAIN_LOOT))
            bagType = IDungeonTacticsIntegration.BagTypes.SAMHAIN;
        else if (event.getName().equals(DTLoots.SOLSTICE_LOOT))
            bagType = IDungeonTacticsIntegration.BagTypes.SOLSTICE;
        else if (event.getName().equals(DTLoots.TOOL_LOOT))
            bagType = IDungeonTacticsIntegration.BagTypes.TOOL;
        
        if (bagType != null)
        {
            for (IDungeonTacticsIntegration integrations : modIntegrations)
                integrations.insertBagLoot(bagType, main);
        }
    }
    
    @Override
    public void init(FMLInitializationEvent event)
    {
        if (!dungeonTacticsAdditionsEnabled)
            return;
        
        OreDictionary.registerOre("blockGlass", DTBlocks.DUNGEON_GLASS);
        
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
