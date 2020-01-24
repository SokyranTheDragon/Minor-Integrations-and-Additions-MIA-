package com.github.sokyranthedragon.mia.integrations.cofhcore;

import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.integrations.base.IBaseMod;
import com.github.sokyranthedragon.mia.integrations.base.IModIntegration;

import java.util.function.BiConsumer;

import static com.github.sokyranthedragon.mia.config.CofhCoreConfiguration.enableQuarkIntegration;

public class CofhCore implements IBaseMod
{
    @Override
    public void register(BiConsumer<ModIds, IModIntegration> modIntegration)
    {
        if (enableQuarkIntegration && ModIds.QUARK.isLoaded)
            modIntegration.accept(ModIds.QUARK, new QuarkCofhCoreIntegration());
    }
}
