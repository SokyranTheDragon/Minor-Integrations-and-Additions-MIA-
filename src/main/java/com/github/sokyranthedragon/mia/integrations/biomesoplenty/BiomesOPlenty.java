package com.github.sokyranthedragon.mia.integrations.biomesoplenty;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.api.item.BOPItems;
import com.github.sokyranthedragon.mia.block.decorative.SandstoneEntry;
import com.github.sokyranthedragon.mia.config.GenericAdditionsConfig;
import com.github.sokyranthedragon.mia.config.MiaConfig;
import com.github.sokyranthedragon.mia.core.MiaBlocks;
import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.integrations.base.IBaseMod;
import com.github.sokyranthedragon.mia.integrations.base.IModIntegration;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

import java.util.function.BiConsumer;

import static com.github.sokyranthedragon.mia.config.BiomesOPlentyConfiguration.*;
import static net.minecraftforge.oredict.OreDictionary.registerOre;

public class BiomesOPlenty implements IBaseMod
{
    @Override
    public void register(BiConsumer<ModIds, IModIntegration> modIntegration)
    {
        if (enableDungeonTacticsIntegration && ModIds.DUNGEON_TACTICS.isLoaded)
            modIntegration.accept(ModIds.DUNGEON_TACTICS, new DungeonTacticsBopIntegration());
        if (enableFutureMcIntegration && ModIds.FUTURE_MC.isLoaded)
            modIntegration.accept(ModIds.FUTURE_MC, new FutureMcBopIntegration());
        if (ModIds.HATCHERY.isLoaded)
            modIntegration.accept(ModIds.HATCHERY, new HatcheryBopIntegration(enableHatcheryIntegration));
        if (enableTeIntegration && ModIds.THERMAL_EXPANSION.isLoaded)
            modIntegration.accept(ModIds.THERMAL_EXPANSION, new ThermalExpansionBopIntegration());
        if (enableXu2Integration && ModIds.EXTRA_UTILITIES.isLoaded)
            modIntegration.accept(ModIds.EXTRA_UTILITIES, new ExtraUtilsBopIntegration());
        if (enableBotaniaIntegration && ModIds.BOTANIA.isLoaded)
            modIntegration.accept(ModIds.BOTANIA, new BotaniaBopIntegration());
    }
    
    @Override
    public void init(FMLInitializationEvent event)
    {
        if (bopAdditionsEnabled)
        {
            SandstoneEntry.registerRecipes(MiaBlocks.whiteSandstone, BOPBlocks.white_sandstone);
            
            if (!MiaConfig.disableOreDict)
            {
                registerOre("listAllberry", BOPItems.berries);
                registerOre("listAllfruit", BOPItems.berries);
                registerOre("cropRice", new ItemStack(BOPBlocks.plant_1, 1, 3));
                registerOre("cropBamboo", new ItemStack(BOPBlocks.bamboo));
                registerOre("honeycomb", new ItemStack(BOPItems.honeycomb));
                registerOre("foodHoneydrop", new ItemStack(BOPItems.filled_honeycomb));
                registerOre("dropHoney", new ItemStack(BOPItems.filled_honeycomb));
                registerOre("listAllsugar", new ItemStack(BOPItems.filled_honeycomb));
            }
        }
    }
    
    @Override
    public void registerBlocks(RegistryEvent.Register<Block> event)
    {
        if (!bopAdditionsEnabled)
            return;
        
        if (GenericAdditionsConfig.moreSandstone.bopWhiteSandstoneEnabled)
            MiaBlocks.whiteSandstone = SandstoneEntry.init(BOPBlocks.white_sandstone,
                    "white",
                    CreativeTabs.BUILDING_BLOCKS,
                    MapColor.WHITE_STAINED_HARDENED_CLAY,
                    GenericAdditionsConfig.moreSandstone.bopWhiteSandstoneQuarkWallsEnabled);
    }
    
    @Override
    public void registerItems(RegistryEvent.Register<Item> event)
    {
        if (!bopAdditionsEnabled)
            return;
        
        SandstoneEntry.registerItemblocks(MiaBlocks.whiteSandstone, event.getRegistry());
    }
}