package com.github.sokyranthedragon.mia.integrations.charm;

import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.integrations.dungeontactics.DungeonTactics;
import com.github.sokyranthedragon.mia.integrations.dungeontactics.IDungeonTacticsIntegration;
import com.github.sokyranthedragon.mia.utilities.LootUtils;
import org.jetbrains.annotations.NotNull;
import svenhjol.charm.Charm;
import svenhjol.charm.brewing.block.BlockFlavoredCake;
import svenhjol.charm.brewing.feature.FlavoredCake;
import svenhjol.charm.crafting.feature.SuspiciousSoup;
import svenhjol.charm.tweaks.feature.ExtraRecords;
import svenhjol.charm.tweaks.item.CharmItemRecord;

import javax.annotation.Nullable;

class DungeonTacticsCharmIntegration implements IDungeonTacticsIntegration
{
    @Nullable
    @Override
    public DungeonTactics.ILootBagListener registerLootBagListener()
    {
        return (type, loot) ->
        {
            switch (type)
            {
                case FOOD:
                    if (Charm.hasFeature(SuspiciousSoup.class))
                        LootUtils.addDtLoot(loot, SuspiciousSoup.suspiciousSoup, LootUtils.setMetadataFunction(0, SuspiciousSoup.maxTypes - 1));
                    if (Charm.hasFeature(FlavoredCake.class))
                    {
                        for (BlockFlavoredCake cake : FlavoredCake.cakes.values())
                            LootUtils.addDtLoot(loot, cake, 1);
                    }
                    break;
                case RECORD:
                    if (Charm.hasFeature(ExtraRecords.class))
                    {
                        for (CharmItemRecord record : ExtraRecords.records)
                            LootUtils.addDtLoot(loot, record);
                    }
                    break;
            }
        };
    }
    
    @NotNull
    @Override
    public ModIds getModId()
    {
        return ModIds.CHARM;
    }
}
