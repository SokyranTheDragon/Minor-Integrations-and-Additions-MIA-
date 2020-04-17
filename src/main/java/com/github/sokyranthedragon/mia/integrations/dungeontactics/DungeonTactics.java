package com.github.sokyranthedragon.mia.integrations.dungeontactics;

import com.github.sokyranthedragon.mia.Mia;
import com.github.sokyranthedragon.mia.config.MiaConfig;
import com.github.sokyranthedragon.mia.dispenserbehavior.DispenserLootBag;
import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.integrations.base.IBaseMod;
import com.github.sokyranthedragon.mia.integrations.base.IModIntegration;
import com.github.sokyranthedragon.mia.integrations.base.LootTableIntegrator;
import net.minecraft.block.BlockDispenser;
import net.minecraft.dispenser.IBehaviorDispenseItem;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.WorldServer;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.LootPool;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.fml.common.ProgressManager;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.oredict.OreDictionary;
import pegbeard.dungeontactics.handlers.DTBlocks;
import pegbeard.dungeontactics.handlers.DTFireworkHelper;
import pegbeard.dungeontactics.handlers.DTItems;
import pegbeard.dungeontactics.handlers.DTLoots;
import pegbeard.dungeontactics.items.DTLootBagGeneric;

import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.function.BiConsumer;

import static com.github.sokyranthedragon.mia.config.DungeonTacticsConfiguration.*;
import static com.github.sokyranthedragon.mia.integrations.ModIds.*;

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
        if (enableChiselIntegration && CHISEL.isLoaded)
            modIntegration.accept(CHISEL, new ChiselDungeonTacticsIntegration());
        if (INDUSTRIAL_FOREGOING.isLoaded)
            modIntegration.accept(INDUSTRIAL_FOREGOING, new IndustrialForegoingDungeonTacticsIntegration());
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
    public LootTableIntegrator.LootTableListener registerLootListener()
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
            return lootBag::insertBagLoot;
        else
            return null;
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
            
            OreDictionary.registerOre("oreNetherGold", DTBlocks.NETHER_GOLD);
            OreDictionary.registerOre("oreEndDiamond", DTBlocks.END_DIAMOND);
            OreDictionary.registerOre("oreEndLapis", DTBlocks.END_LAPIS);
            
            OreDictionary.registerOre("obsidian", DTBlocks.OBSIDIAN_BRICK);
        }
    }
    
    @Override
    public void registerDispenserBehaviors()
    {
        DispenserLootBag.getInstance().addListener((source, stack) ->
        {
            if (!(stack.getItem() instanceof DTLootBagGeneric))
                return false;
            
            ResourceLocation drop;
            Item item = stack.getItem();
            IBehaviorDispenseItem defaultDispenserBehavior = BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.getObject(null);
    
            if (item == DTItems.BAG_FOOD) drop = DTLoots.FOOD_LOOT;
            else if (item == DTItems.BAG_ARBOUR) drop = DTLoots.ARBOUR_LOOT;
            else if (item == DTItems.BAG_ORE) drop = DTLoots.ORE_LOOT;
            else if (item == DTItems.BAG_TOOL) drop = DTLoots.TOOL_LOOT;
            else if (item == DTItems.BAG_BOOK) drop = DTLoots.BOOK_LOOT;
            else if (item == DTItems.BAG_QUIVER) drop = DTLoots.QUIVER_LOOT;
            else if (item == DTItems.BAG_MAGIC) drop = DTLoots.MAGIC_LOOT;
            else if (item == DTItems.BAG_POTION) drop = DTLoots.POTION_LOOT;
            else if (item == DTItems.BAG_RECORD) drop = DTLoots.RECORD_LOOT;
            else if (item == DTItems.BAG_SAMHAIN)
            {
                int rand = source.getWorld().rand.nextInt(6);
                
                if (rand == 0)
                {
                    BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.getObject(DTItems.BOMB_FRAG).dispense(source, new ItemStack(DTItems.BOMB_FRAG));
                    stack.shrink(1);
                    return true;
                }
                else if (rand <= 2)
                    drop = DTLoots.SAMHAIN_LOOT;
                else
                {
                    ItemStack firework = DTFireworkHelper.samhainRocket();
                    stack.shrink(1);
                    BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.getObject(firework.getItem()).dispense(source, firework.copy());
                    defaultDispenserBehavior.dispense(source, firework);
                    return true;
                }
            }
            else if (item == DTItems.BAG_SOLSTICE)
            {
                int rand = source.getWorld().rand.nextInt(6);
                
                if (rand == 0)
                {
                    defaultDispenserBehavior.dispense(source, new ItemStack(Items.COAL));
                    stack.shrink(1);
                    return true;
                }
                else if (rand <= 2)
                    drop = DTLoots.SOLSTICE_LOOT;
                else
                {
                    ItemStack firework = DTFireworkHelper.solsticeRocket();
                    stack.shrink(1);
                    BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.getObject(firework.getItem()).dispense(source, firework);
                    defaultDispenserBehavior.dispense(source, firework);
                    return true;
                }
            }
            else return false;
            
            assert source.getWorld() instanceof WorldServer;
            
            LootContext.Builder builder = new LootContext.Builder((WorldServer) source.getWorld());
            List<ItemStack> lootDrops = source.getWorld().getLootTableManager().getLootTableFromLocation(drop).generateLootForPools(source.getWorld().rand, builder.build());
            
            
            for (ItemStack lootDrop : lootDrops)
                defaultDispenserBehavior.dispense(source, lootDrop);
            
            stack.shrink(1);
            return true;
        }, DTItems.BAG_ARBOUR, DTItems.BAG_ORE, DTItems.BAG_TOOL, DTItems.BAG_BOOK, DTItems.BAG_QUIVER, DTItems.BAG_MAGIC, DTItems.BAG_POTION, DTItems.BAG_RECORD, DTItems.BAG_SAMHAIN, DTItems.BAG_SOLSTICE);
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
