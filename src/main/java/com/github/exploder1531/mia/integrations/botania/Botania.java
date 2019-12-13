package com.github.exploder1531.mia.integrations.botania;

import com.github.exploder1531.mia.integrations.ModIds;
import com.github.exploder1531.mia.integrations.base.IBaseMod;
import com.github.exploder1531.mia.integrations.base.IModIntegration;

import java.util.function.BiConsumer;

import static com.github.exploder1531.mia.integrations.ModLoadStatus.dungeonTacticsLoaded;

public class Botania implements IBaseMod
{
    @Override
    public void register(BiConsumer<String, IModIntegration> modIntegration)
    {
        if (dungeonTacticsLoaded)
            modIntegration.accept(ModIds.DUNGEON_TACTICS, new DungeonTacticsBotaniaIntegration());
    }
}
