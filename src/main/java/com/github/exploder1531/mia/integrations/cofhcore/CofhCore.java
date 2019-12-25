package com.github.exploder1531.mia.integrations.cofhcore;

import com.github.exploder1531.mia.integrations.ModIds;
import com.github.exploder1531.mia.integrations.base.IBaseMod;
import com.github.exploder1531.mia.integrations.base.IModIntegration;

import java.util.function.BiConsumer;

import static com.github.exploder1531.mia.config.CofhCoreConfiguration.enableQuarkIntegration;
import static com.github.exploder1531.mia.integrations.ModLoadStatus.quarkLoaded;

public class CofhCore implements IBaseMod
{
    @Override
    public void register(BiConsumer<String, IModIntegration> modIntegration)
    {
        if (enableQuarkIntegration && quarkLoaded)
            modIntegration.accept(ModIds.QUARK, new QuarkCofhCoreIntegration());
    }
}
