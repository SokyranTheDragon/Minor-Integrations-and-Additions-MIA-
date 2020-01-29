package com.github.sokyranthedragon.mia.integrations.biomesoplenty;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.api.item.BOPItems;
import com.github.sokyranthedragon.mia.config.MiaConfig;
import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.integrations.base.IBaseMod;
import com.github.sokyranthedragon.mia.integrations.base.IModIntegration;
import net.minecraft.item.ItemStack;
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
        if (bopAdditionsEnabled && !MiaConfig.disableOreDict)
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