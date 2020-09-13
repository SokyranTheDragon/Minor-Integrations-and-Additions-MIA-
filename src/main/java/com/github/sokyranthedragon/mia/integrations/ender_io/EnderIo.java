package com.github.sokyranthedragon.mia.integrations.ender_io;

import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.integrations.base.IBaseMod;
import com.github.sokyranthedragon.mia.integrations.base.IModIntegration;

import java.util.function.BiConsumer;

import static com.github.sokyranthedragon.mia.config.EnderIoConfiguration.enableJerIntegration;
import static com.github.sokyranthedragon.mia.integrations.ModIds.JER;

public class EnderIo implements IBaseMod
{
    @Override
    public void register(BiConsumer<ModIds, IModIntegration> modIntegration)
    {
        if (enableJerIntegration && JER.isLoaded)
            modIntegration.accept(JER, new JerEnderIoIntegration());
    }
}
