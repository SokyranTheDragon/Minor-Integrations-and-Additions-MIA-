package com.github.sokyranthedragon.mia.integrations.aether;

import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.integrations.base.IBaseMod;
import com.github.sokyranthedragon.mia.integrations.base.IModIntegration;

import java.util.function.BiConsumer;

import static com.github.sokyranthedragon.mia.config.AetherConfig.*;
import static com.github.sokyranthedragon.mia.integrations.ModIds.*;

public class Aether implements IBaseMod
{
    @Override
    public void register(BiConsumer<ModIds, IModIntegration> modIntegration)
    {
        if (FUTURE_MC.isLoaded && enableFutureMcIntegration)
            modIntegration.accept(FUTURE_MC, new FutureMcAetherIntegration());
        if (DUNGEON_TACTICS.isLoaded && enableDungeonTacticsIntegration)
            modIntegration.accept(DUNGEON_TACTICS, new DungeonTacticsAetherIntegration());
        if (THERMAL_EXPANSION.isLoaded && enableTeIntegration)
            modIntegration.accept(THERMAL_EXPANSION, new ThermalExpansionAetherIntegration());
    }
}
