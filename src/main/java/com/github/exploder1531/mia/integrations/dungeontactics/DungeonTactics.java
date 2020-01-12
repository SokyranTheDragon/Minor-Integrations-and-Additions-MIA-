package com.github.exploder1531.mia.integrations.dungeontactics;

import com.github.exploder1531.mia.Mia;
import com.github.exploder1531.mia.config.MiaConfig;
import com.github.exploder1531.mia.integrations.ModIds;
import com.github.exploder1531.mia.integrations.base.IBaseMod;
import com.github.exploder1531.mia.integrations.base.IModIntegration;
import com.github.exploder1531.mia.integrations.base.LootTableIntegrator.LootTableListener;
import net.minecraft.world.storage.loot.LootPool;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.fml.common.ProgressManager;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.oredict.OreDictionary;
import pegbeard.dungeontactics.handlers.DTBlocks;
import pegbeard.dungeontactics.handlers.DTItems;
import pegbeard.dungeontactics.handlers.DTLoots;

import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.function.BiConsumer;

import static com.github.exploder1531.mia.config.DungeonTacticsConfiguration.*;
import static com.github.exploder1531.mia.integrations.ModIds.*;

public class DungeonTactics implements IBaseMod
{
    private final List<IDungeonTacticsIntegration> modIntegrations = new LinkedList<>();
    
    @Override
    public void register(BiConsumer<ModIds, IModIntegration> modIntegration)
    {
        if (enableTConstructIntegration && TINKERS_CONSTRUCT.isLoaded)
            modIntegration.accept(TINKERS_CONSTRUCT, new TConstructDungeonTacticsIntegration());
        if (enableTeIntegration && THERMAL_EXPANSION.isLoaded)
            modIntegration.accept(THERMAL_EXPANSION, new ThermalExpansionDungeonTacticsIntegration());
        if (JEI.isLoaded)
            modIntegration.accept(JEI, new JeiDungeonTacticsIntegration());
        if (enableJerIntegration && JER.isLoaded)
            modIntegration.accept(JER, new JerDungeonTacticsIntegration());
        if (enableFutureMcIntegration && FUTURE_MC.isLoaded)
            modIntegration.accept(FUTURE_MC, new FutureMcDungeonTacticsIntegration());
        if (enableQuarkIntegration && QUARK.isLoaded)
            modIntegration.accept(QUARK, new QuarkDungeonTacticsIntegration());
        if (HATCHERY.isLoaded)
            modIntegration.accept(HATCHERY, new HatcheryDungeonTacticsIntegration(enableHatcheryIntegration));
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
    @Nullable
    public LootTableListener registerLootListener()
    {
        if (!registerCustomBagLoot || modIntegrations.isEmpty())
            return null;
        
        LootBagLootTableHandler lootBag = new LootBagLootTableHandler();
        
        boolean anyListener = false;
        ProgressManager.ProgressBar progressBar = ProgressManager.push("DungeonTactics registerLootBagListener", modIntegrations.size());
        for (IDungeonTacticsIntegration integration : modIntegrations)
        {
            progressBar.step(integration.getModId().modId);
            ILootBagListener listener = integration.registerLootBagListener();
            if (listener != null)
            {
                anyListener = true;
                lootBag.addIntegration(listener);
            }
        }
        
        ProgressManager.pop(progressBar);
        
        if (anyListener)
            return null;
        else
            return lootBag::insertBagLoot;
    }
    
    @Override
    public void init(FMLInitializationEvent event)
    {
        if (!dungeonTacticsAdditionsEnabled)
            return;
        
        if (!MiaConfig.disableOreDict)
        {
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
            
            OreDictionary.registerOre("flourEqualswheat", DTItems.FLOUR);
        }
    }
    
    @FunctionalInterface
    public interface ILootBagListener
    {
        void insertBagLoot(IDungeonTacticsIntegration.BagTypes type, LootPool loot);
    }
    
    private static class LootBagLootTableHandler
    {
        private HashSet<ILootBagListener> integrations = new HashSet<>();
        
        public LootBagLootTableHandler()
        {
        }
        
        public void addIntegration(ILootBagListener integration)
        {
            integrations.add(integration);
        }
        
        public void insertBagLoot(LootTableLoadEvent event)
        {
            LootPool main = event.getTable().getPool("main");
            IDungeonTacticsIntegration.BagTypes bagTypes = null;
            
            if (event.getName().equals(DTLoots.ARBOUR_LOOT))
                bagTypes = IDungeonTacticsIntegration.BagTypes.ARBOUR;
            else if (event.getName().equals(DTLoots.BOOK_LOOT))
                bagTypes = IDungeonTacticsIntegration.BagTypes.BOOK;
            else if (event.getName().equals(DTLoots.FOOD_LOOT))
                bagTypes = IDungeonTacticsIntegration.BagTypes.FOOD;
            else if (event.getName().equals(DTLoots.MAGIC_LOOT))
                bagTypes = IDungeonTacticsIntegration.BagTypes.MAGIC;
            else if (event.getName().equals(DTLoots.ORE_LOOT))
                bagTypes = IDungeonTacticsIntegration.BagTypes.ORE;
            else if (event.getName().equals(DTLoots.POTION_LOOT))
                bagTypes = IDungeonTacticsIntegration.BagTypes.POTION;
            else if (event.getName().equals(DTLoots.QUIVER_LOOT))
                bagTypes = IDungeonTacticsIntegration.BagTypes.QUIVER;
            else if (event.getName().equals(DTLoots.RECORD_LOOT))
                bagTypes = IDungeonTacticsIntegration.BagTypes.RECORD;
            else if (event.getName().equals(DTLoots.SAMHAIN_LOOT))
                bagTypes = IDungeonTacticsIntegration.BagTypes.SAMHAIN;
            else if (event.getName().equals(DTLoots.SOLSTICE_LOOT))
                bagTypes = IDungeonTacticsIntegration.BagTypes.SOLSTICE;
            else if (event.getName().equals(DTLoots.TOOL_LOOT))
                bagTypes = IDungeonTacticsIntegration.BagTypes.TOOL;
            
            if (bagTypes != null)
            {
                for (ILootBagListener integration : integrations)
                    integration.insertBagLoot(bagTypes, main);
            }
        }
    }
}
