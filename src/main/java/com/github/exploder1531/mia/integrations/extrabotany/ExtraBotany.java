package com.github.exploder1531.mia.integrations.extrabotany;

import com.github.exploder1531.mia.integrations.ModIds;
import com.github.exploder1531.mia.integrations.base.IBaseMod;
import com.github.exploder1531.mia.integrations.base.IModIntegration;

import java.util.function.BiConsumer;

import static com.github.exploder1531.mia.integrations.ModLoadStatus.dungeonTacticsLoaded;
import static com.github.exploder1531.mia.integrations.ModLoadStatus.jeiLoaded;

public class ExtraBotany implements IBaseMod
{
    @Override
    public void register(BiConsumer<String, IModIntegration> modIntegration)
    {
        if (jeiLoaded)
            modIntegration.accept(ModIds.JEI, new JeiExtraBotany());
        if (dungeonTacticsLoaded)
            modIntegration.accept(ModIds.DUNGEON_TACTICS, new DungeonTacticsExtraBotanyIntegration());
    }
}
