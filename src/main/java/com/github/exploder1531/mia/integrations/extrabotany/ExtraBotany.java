package com.github.exploder1531.mia.integrations.extrabotany;

import com.github.exploder1531.mia.integrations.ModIds;
import com.github.exploder1531.mia.integrations.base.IBaseMod;
import com.github.exploder1531.mia.integrations.base.IModIntegration;

import java.util.function.BiConsumer;

import static com.github.exploder1531.mia.config.ExtraBotanyConfig.*;
import static com.github.exploder1531.mia.integrations.ModIds.*;

public class ExtraBotany implements IBaseMod
{
    @Override
    public void register(BiConsumer<ModIds, IModIntegration> modIntegration)
    {
        if (JEI.isLoaded)
            modIntegration.accept(JEI, new JeiExtraBotany());
        if (enableDungeonTacticsIntegration && DUNGEON_TACTICS.isLoaded)
            modIntegration.accept(DUNGEON_TACTICS, new DungeonTacticsExtraBotanyIntegration());
        if (enableTeIntegration && THERMAL_EXPANSION.isLoaded)
            modIntegration.accept(THERMAL_EXPANSION, new ThermalExpansionExtraBotanyIntegration());
        if (JER.isLoaded)
            modIntegration.accept(JER, new JerExtraBotanyIntegration());
        if (EXTRABOTANY.isLoaded)
            modIntegration.accept(EXTRABOTANY, new HatcheryExtraBotanyIntegration(enableHatcheryIntegration));
    }
}
