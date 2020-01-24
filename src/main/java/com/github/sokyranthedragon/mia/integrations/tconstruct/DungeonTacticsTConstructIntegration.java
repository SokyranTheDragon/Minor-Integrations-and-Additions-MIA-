package com.github.sokyranthedragon.mia.integrations.tconstruct;

import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.integrations.dungeontactics.DungeonTactics;
import com.github.sokyranthedragon.mia.integrations.dungeontactics.IDungeonTacticsIntegration;
import com.github.sokyranthedragon.mia.utilities.LootUtils;
import slimeknights.tconstruct.TConstruct;
import slimeknights.tconstruct.shared.TinkerCommons;
import slimeknights.tconstruct.world.TinkerWorld;

import javax.annotation.Nonnull;

class DungeonTacticsTConstructIntegration implements IDungeonTacticsIntegration
{
    @Override
    public DungeonTactics.ILootBagListener registerLootBagListener()
    {
        return (type, loot) ->
        {
            switch (type)
            {
                case ARBOUR:
                    if (TConstruct.pulseManager.isPulseLoaded(TinkerWorld.PulseId))
                        LootUtils.addDtLoot(loot, TinkerWorld.slimeSapling, 1, LootUtils.setMetadataFunction(0, 2));
                    break;
                case FOOD:
                    if (TConstruct.pulseManager.isPulseLoaded(TinkerCommons.PulseId))
                    {
                        LootUtils.addDtLoot(loot, TinkerCommons.edibles, "Bacon");
                        LootUtils.addDtLoot(loot, TinkerCommons.edibles, "Jerky", LootUtils.setMetadataFunction(10, 15));
                        LootUtils.addDtLoot(loot, TinkerCommons.edibles, "Fish", LootUtils.setMetadataFunction(20, 23));
                        LootUtils.addDtLoot(loot, TinkerCommons.edibles, "Slime", LootUtils.setMetadataFunction(30, 34));
                    }
                    break;
                case ORE:
                    if (TConstruct.pulseManager.isPulseLoaded(TinkerCommons.PulseId))
                    {
                        LootUtils.addDtLoot(loot, TinkerCommons.blockOre, "Ardite", 3);
                        LootUtils.addDtLoot(loot, TinkerCommons.blockOre, "Cobalt", 3, LootUtils.setMetadataFunction(1, 1));
                    }
                    break;
            }
        };
    }
    
    @Nonnull
    @Override
    public ModIds getModId()
    {
        return ModIds.TINKERS_CONSTRUCT;
    }
}
