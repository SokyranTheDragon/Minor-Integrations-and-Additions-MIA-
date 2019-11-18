package com.github.exploder1531.mia.integrations.mocreatures;

import com.github.exploder1531.mia.integrations.ModIds;
import com.github.exploder1531.mia.integrations.base.IBaseMod;
import com.github.exploder1531.mia.integrations.base.IModIntegration;
import net.minecraftforge.fml.common.Loader;

import java.util.function.BiConsumer;

public class MoCreatures implements IBaseMod
{
    @Override
    public void register(BiConsumer<String, IModIntegration> modIntegration)
    {
        if (Loader.isModLoaded(ModIds.JER))
            modIntegration.accept(ModIds.JER, new JerMoCreaturesIntegration());
    }
}
