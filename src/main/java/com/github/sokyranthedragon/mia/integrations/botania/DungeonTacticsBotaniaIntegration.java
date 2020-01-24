package com.github.sokyranthedragon.mia.integrations.botania;

import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.integrations.dungeontactics.DungeonTactics;
import com.github.sokyranthedragon.mia.integrations.dungeontactics.IDungeonTacticsIntegration;
import com.github.sokyranthedragon.mia.utilities.LootUtils;
import vazkii.botania.common.block.ModBlocks;
import vazkii.botania.common.item.ModItems;

import javax.annotation.Nonnull;

class DungeonTacticsBotaniaIntegration implements IDungeonTacticsIntegration
{
    @Override
    public DungeonTactics.ILootBagListener registerLootBagListener()
    {
        return (type, loot) ->
        {
            switch (type)
            {
                case ARBOUR:
                    LootUtils.addDtLoot(loot, ModBlocks.livingwood, 1);
                    break;
                case MAGIC:
                    LootUtils.addDtLoot(loot, ModItems.petal, LootUtils.setMetadataFunction(0, 15), LootUtils.setCountFunction(3));
                    break;
                case RECORD:
                    LootUtils.addDtLoot(loot, ModItems.recordGaia1, 1);
                    LootUtils.addDtLoot(loot, ModItems.recordGaia2, 1);
                    break;
            }
        };
    }
    
    @Nonnull
    @Override
    public ModIds getModId()
    {
        return ModIds.BOTANIA;
    }
}
