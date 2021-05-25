package com.github.sokyranthedragon.mia.integrations.charm;

import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.integrations.base.IBaseMod;
import com.github.sokyranthedragon.mia.integrations.base.IModIntegration;

import java.util.function.BiConsumer;

import static com.github.sokyranthedragon.mia.config.CharmConfig.*;
import static com.github.sokyranthedragon.mia.integrations.ModIds.*;

public class Charm implements IBaseMod
{
    @Override
    public void register(BiConsumer<ModIds, IModIntegration> modIntegration)
    {
        if (enableXu2Integration && EXTRA_UTILITIES.isLoaded)
            modIntegration.accept(EXTRA_UTILITIES, new ExtraUtilsCharmIntegration());
        if (enableFutureMcIntegration && FUTURE_MC.isLoaded)
            modIntegration.accept(FUTURE_MC, new FutureMcCharmIntegration());
        if (enableTeIntegration && THERMAL_EXPANSION.isLoaded)
            modIntegration.accept(THERMAL_EXPANSION, new ThermalExpansionCharmIntegration());
        if (enableJerIntegration && JER.isLoaded)
            modIntegration.accept(JER, new JerCharmIntegration());
        if (enableDungeonTacticsIntegration && DUNGEON_TACTICS.isLoaded)
            modIntegration.accept(DUNGEON_TACTICS, new DungeonTacticsCharmIntegration());
        if (enableTConstructIntegration && TINKERS_CONSTRUCT.isLoaded)
            modIntegration.accept(TINKERS_CONSTRUCT, new TConstructCharmIntegration());
    }
}
