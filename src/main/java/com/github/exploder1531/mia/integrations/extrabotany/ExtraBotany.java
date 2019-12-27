package com.github.exploder1531.mia.integrations.extrabotany;

import com.github.exploder1531.mia.integrations.ModIds;
import com.github.exploder1531.mia.integrations.base.IBaseMod;
import com.github.exploder1531.mia.integrations.base.IModIntegration;

import java.util.function.BiConsumer;

import static com.github.exploder1531.mia.config.ExtraBotanyConfig.enableDungeonTacticsIntegration;
import static com.github.exploder1531.mia.config.ExtraBotanyConfig.enableTeIntegration;
import static com.github.exploder1531.mia.integrations.ModLoadStatus.*;

public class ExtraBotany implements IBaseMod
{
    @Override
    public void register(BiConsumer<String, IModIntegration> modIntegration)
    {
        if (jeiLoaded)
            modIntegration.accept(ModIds.JEI, new JeiExtraBotany());
        if (enableDungeonTacticsIntegration && dungeonTacticsLoaded)
            modIntegration.accept(ModIds.DUNGEON_TACTICS, new DungeonTacticsExtraBotanyIntegration());
        if (enableTeIntegration && thermalExpansionLoaded)
            modIntegration.accept(ModIds.THERMAL_EXPANSION, new ThermalExpansionExtraBotanyIntegration());
        if (jerLoaded)
            modIntegration.accept(ModIds.JER, new JerExtraBotanyIntegration());
    }
}
