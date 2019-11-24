package com.github.exploder1531.mia.integrations.harvestcraft;

import com.github.exploder1531.mia.integrations.ModIds;
import com.github.exploder1531.mia.integrations.ModLoadStatus;
import com.github.exploder1531.mia.integrations.base.IBaseMod;
import com.github.exploder1531.mia.integrations.base.IModIntegration;

import java.util.function.BiConsumer;

public class Harvestcraft implements IBaseMod
{
    @Override
    public void register(BiConsumer<String, IModIntegration> modIntegration)
    {
        if (ModLoadStatus.thermalExpansionLoaded)
            modIntegration.accept(ModIds.THERMAL_EXPANSION, new ThermalExpansionHarvestcraftIntegration());
        if (ModLoadStatus.jerLoaded)
            modIntegration.accept(ModIds.JER, new JerHarvestcraftIntegration());
    }
}
