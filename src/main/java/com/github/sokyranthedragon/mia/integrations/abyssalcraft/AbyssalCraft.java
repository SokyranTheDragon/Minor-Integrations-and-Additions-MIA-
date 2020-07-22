package com.github.sokyranthedragon.mia.integrations.abyssalcraft;

import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.integrations.base.IBaseMod;
import com.github.sokyranthedragon.mia.integrations.base.IModIntegration;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.function.BiConsumer;

@ParametersAreNonnullByDefault
public class AbyssalCraft implements IBaseMod
{
    @Override
    public void register(BiConsumer<ModIds, IModIntegration> modIntegration)
    {
        if (ModIds.HARVESTCRAFT.isLoaded)
            modIntegration.accept(ModIds.HARVESTCRAFT, new HarvestcraftAbyssalcraftIntegration());
    }
}
