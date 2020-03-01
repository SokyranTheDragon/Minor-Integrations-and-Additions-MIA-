package com.github.sokyranthedragon.mia.integrations.natura;

import com.github.sokyranthedragon.mia.config.MiaConfig;
import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.integrations.base.IBaseMod;
import com.github.sokyranthedragon.mia.integrations.base.IModIntegration;
import com.progwml6.natura.nether.NaturaNether;
import com.progwml6.natura.overworld.NaturaOverworld;
import com.progwml6.natura.shared.NaturaCommons;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.oredict.OreDictionary;

import java.util.function.BiConsumer;

import static com.github.sokyranthedragon.mia.config.NaturaConfiguration.*;
import static com.progwml6.natura.Natura.pulseManager;

public class Natura implements IBaseMod
{
    @Override
    public void register(BiConsumer<ModIds, IModIntegration> modIntegration)
    {
        if (enableJerIntegration && ModIds.JER.isLoaded)
            modIntegration.accept(ModIds.JER, new JerNaturaIntegration());
        if (enableTeIntegration && ModIds.THERMAL_EXPANSION.isLoaded)
            modIntegration.accept(ModIds.THERMAL_EXPANSION, new ThermalExpansionNaturaIntegration());
        if (enableDungeonTacticsIntegration && ModIds.DUNGEON_TACTICS.isLoaded)
            modIntegration.accept(ModIds.DUNGEON_TACTICS, new DungeonTacticsNaturaIntegration());
        if (enableFutureMcIntegration && ModIds.FUTURE_MC.isLoaded)
            modIntegration.accept(ModIds.FUTURE_MC, new FutureMcNaturaIntegration());
        if (enableHatcheryIntegration && ModIds.HATCHERY.isLoaded)
            modIntegration.accept(ModIds.HATCHERY, new HatcheryNaturaIntegration());
        if (enableXu2Integration && ModIds.EXTRA_UTILITIES.isLoaded)
            modIntegration.accept(ModIds.EXTRA_UTILITIES, new ExtraUtilsNaturaIntegration());
    }
    
    @Override
    public void init(FMLInitializationEvent event)
    {
        if (!naturaAdditionsEnabled)
            return;
        
        if (!MiaConfig.disableOreDict)
        {
            if (pulseManager.isPulseLoaded(NaturaOverworld.PulseId))
            {
                OreDictionary.registerOre("foodAllfruit", NaturaOverworld.saguaroFruitItem);
                OreDictionary.registerOre("cropCactusfruit", NaturaOverworld.saguaroFruitItem);
                OreDictionary.registerOre("blockCactus", NaturaOverworld.saguaro);
                if (pulseManager.isPulseLoaded(NaturaCommons.PulseId))
                    for (int meta = 2; meta <= 5; meta++)
                        OreDictionary.registerOre("listAllberry", new ItemStack(NaturaCommons.edibles, 1, meta));
            }
            if (pulseManager.isPulseLoaded(NaturaNether.PulseId))
            {
                if (pulseManager.isPulseLoaded(NaturaCommons.PulseId))
                    for (int meta = 6; meta <= 9; meta++)
                        OreDictionary.registerOre("listAllberry", new ItemStack(NaturaCommons.edibles, 1, meta));
                for (int meta = 0; meta <= 2; meta++)
                {
                    OreDictionary.registerOre("mushroomAny", new ItemStack(NaturaNether.netherGlowshroom, 1, OreDictionary.WILDCARD_VALUE));
                    OreDictionary.registerOre("listAllmushroom", new ItemStack(NaturaNether.netherGlowshroom, 1, OreDictionary.WILDCARD_VALUE));
                }
            }
        }
    }
}
