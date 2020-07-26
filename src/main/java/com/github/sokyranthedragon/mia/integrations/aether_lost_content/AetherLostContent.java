package com.github.sokyranthedragon.mia.integrations.aether_lost_content;

import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.integrations.base.IBaseMod;
import com.github.sokyranthedragon.mia.integrations.base.IModIntegration;

import java.util.function.BiConsumer;

import static com.github.sokyranthedragon.mia.config.AetherConfig.enableFutureMcIntegration;
import static com.github.sokyranthedragon.mia.config.AetherConfig.enableTeIntegration;
import static com.github.sokyranthedragon.mia.integrations.ModIds.FUTURE_MC;
import static com.github.sokyranthedragon.mia.integrations.ModIds.THERMAL_EXPANSION;

public class AetherLostContent implements IBaseMod
{
    @Override
    public void register(BiConsumer<ModIds, IModIntegration> modIntegration)
    {
        if (FUTURE_MC.isLoaded && enableFutureMcIntegration)
            modIntegration.accept(FUTURE_MC, new FutureMcAetherLostContentIntegration());
        if (THERMAL_EXPANSION.isLoaded && enableTeIntegration)
            modIntegration.accept(THERMAL_EXPANSION, new ThermalExpansionAetherLostContentIntegration());
//        if (JER.isLoaded && enableJerIntegration)
//            modIntegration.accept(JER, new JerAetherLostContentIntegration());
    }
}
