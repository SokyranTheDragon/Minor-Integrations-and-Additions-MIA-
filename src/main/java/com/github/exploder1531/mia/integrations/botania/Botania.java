package com.github.exploder1531.mia.integrations.botania;

import com.github.exploder1531.mia.integrations.ModIds;
import com.github.exploder1531.mia.integrations.base.IBaseMod;
import com.github.exploder1531.mia.integrations.base.IModIntegration;

import java.util.function.BiConsumer;

import static com.github.exploder1531.mia.config.BotaniaConfiguration.enableDungeonTacticsIntegration;
import static com.github.exploder1531.mia.config.BotaniaConfiguration.enableTeIntegration;
import static com.github.exploder1531.mia.integrations.ModIds.*;

public class Botania implements IBaseMod
{
    @Override
    public void register(BiConsumer<ModIds, IModIntegration> modIntegration)
    {
        if (enableDungeonTacticsIntegration && DUNGEON_TACTICS.isLoaded)
            modIntegration.accept(DUNGEON_TACTICS, new DungeonTacticsBotaniaIntegration());
        if (enableTeIntegration && THERMAL_EXPANSION.isLoaded)
            modIntegration.accept(THERMAL_EXPANSION, new ThermalExpansionBotaniaIntegration());
        if (JER.isLoaded)
            modIntegration.accept(JER, new JerBotaniaIntegration());
    }
}
