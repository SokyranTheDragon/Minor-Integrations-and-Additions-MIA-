package com.github.exploder1531.mia.integrations.botania;

import com.github.exploder1531.mia.integrations.ModIds;
import com.github.exploder1531.mia.integrations.base.IBaseMod;
import com.github.exploder1531.mia.integrations.base.IModIntegration;

import java.util.function.BiConsumer;

import static com.github.exploder1531.mia.config.BotaniaConfiguration.enableDungeonTacticsIntegration;
import static com.github.exploder1531.mia.config.BotaniaConfiguration.enableTeIntegration;
import static com.github.exploder1531.mia.integrations.ModLoadStatus.dungeonTacticsLoaded;
import static com.github.exploder1531.mia.integrations.ModLoadStatus.thermalExpansionLoaded;

public class Botania implements IBaseMod
{
    @Override
    public void register(BiConsumer<String, IModIntegration> modIntegration)
    {
        if (enableDungeonTacticsIntegration && dungeonTacticsLoaded)
            modIntegration.accept(ModIds.DUNGEON_TACTICS, new DungeonTacticsBotaniaIntegration());
        if (enableTeIntegration && thermalExpansionLoaded)
            modIntegration.accept(ModIds.THERMAL_EXPANSION, new ThermalExpansionBotaniaIntegration());
    }
}
