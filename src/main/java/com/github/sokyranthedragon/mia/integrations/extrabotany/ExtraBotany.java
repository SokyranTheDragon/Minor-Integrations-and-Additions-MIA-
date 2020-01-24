package com.github.sokyranthedragon.mia.integrations.extrabotany;

import com.github.sokyranthedragon.mia.config.ExtraBotanyConfig;
import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.integrations.base.IBaseMod;
import com.github.sokyranthedragon.mia.integrations.base.IModIntegration;

import java.util.function.BiConsumer;

public class ExtraBotany implements IBaseMod
{
    @Override
    public void register(BiConsumer<ModIds, IModIntegration> modIntegration)
    {
        if (ModIds.JEI.isLoaded)
            modIntegration.accept(ModIds.JEI, new JeiExtraBotany());
        if (ExtraBotanyConfig.enableDungeonTacticsIntegration && ModIds.DUNGEON_TACTICS.isLoaded)
            modIntegration.accept(ModIds.DUNGEON_TACTICS, new DungeonTacticsExtraBotanyIntegration());
        if (ExtraBotanyConfig.enableTeIntegration && ModIds.THERMAL_EXPANSION.isLoaded)
            modIntegration.accept(ModIds.THERMAL_EXPANSION, new ThermalExpansionExtraBotanyIntegration());
        if (ModIds.JER.isLoaded)
            modIntegration.accept(ModIds.JER, new JerExtraBotanyIntegration());
        if (ModIds.EXTRABOTANY.isLoaded)
            modIntegration.accept(ModIds.EXTRABOTANY, new HatcheryExtraBotanyIntegration(ExtraBotanyConfig.enableHatcheryIntegration));
    }
}
