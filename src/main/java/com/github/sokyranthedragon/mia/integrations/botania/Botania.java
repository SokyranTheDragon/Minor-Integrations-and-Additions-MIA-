package com.github.sokyranthedragon.mia.integrations.botania;

import com.github.sokyranthedragon.mia.config.BotaniaConfiguration;
import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.integrations.base.IBaseMod;
import com.github.sokyranthedragon.mia.integrations.base.IModIntegration;

import java.util.function.BiConsumer;

public class Botania implements IBaseMod
{
    @Override
    public void register(BiConsumer<ModIds, IModIntegration> modIntegration)
    {
        if (BotaniaConfiguration.enableDungeonTacticsIntegration && ModIds.DUNGEON_TACTICS.isLoaded)
            modIntegration.accept(ModIds.DUNGEON_TACTICS, new DungeonTacticsBotaniaIntegration());
        if (BotaniaConfiguration.enableTeIntegration && ModIds.THERMAL_EXPANSION.isLoaded)
            modIntegration.accept(ModIds.THERMAL_EXPANSION, new ThermalExpansionBotaniaIntegration());
        if (ModIds.JER.isLoaded)
            modIntegration.accept(ModIds.JER, new JerBotaniaIntegration());
        if (BotaniaConfiguration.enableFutureMcIntegration && ModIds.FUTURE_MC.isLoaded)
            modIntegration.accept(ModIds.FUTURE_MC, new FutureMcBotaniaIntegration());
        if (ModIds.HATCHERY.isLoaded)
            modIntegration.accept(ModIds.HATCHERY, new HatcheryBotaniaIntegration(BotaniaConfiguration.enableHatcheryIntegration));
    }
}
