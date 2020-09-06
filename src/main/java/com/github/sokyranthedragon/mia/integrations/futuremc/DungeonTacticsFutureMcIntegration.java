package com.github.sokyranthedragon.mia.integrations.futuremc;

import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.integrations.dungeontactics.DungeonTactics;
import com.github.sokyranthedragon.mia.integrations.dungeontactics.IDungeonTacticsIntegration;
import com.github.sokyranthedragon.mia.utilities.LootUtils;
import mcp.MethodsReturnNonnullByDefault;
import thedarkcolour.futuremc.registry.FItems;

import javax.annotation.Nullable;

@MethodsReturnNonnullByDefault
class DungeonTacticsFutureMcIntegration implements IDungeonTacticsIntegration
{
    @Nullable
    @Override
    public DungeonTactics.ILootBagListener registerLootBagListener()
    {
        return (type, loot) ->
        {
            switch (type)
            {
                case ARBOUR:
                    LootUtils.addDtLoot(loot, FItems.INSTANCE.getBAMBOO(), LootUtils.setCountFunction(4, 8));
                    break;
                case FOOD:
                    LootUtils.addDtLoot(loot, FItems.INSTANCE.getSWEET_BERRIES());
                    LootUtils.addDtLoot(loot, FItems.INSTANCE.getHONEY_BOTTLE(), 1);
                    break;
                case ORE:
                    LootUtils.addDtLoot(loot, FItems.INSTANCE.getNETHERITE_SCRAP(), 3);
                    break;
                case RECORD:
                    LootUtils.addDtLoot(loot, FItems.INSTANCE.getRECORD_PIGSTEP());
                    break;
            }
        };
    }
    
    @Override
    public ModIds getModId()
    {
        return ModIds.FUTURE_MC;
    }
}
