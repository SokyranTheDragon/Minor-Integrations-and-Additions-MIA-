package com.github.sokyranthedragon.mia.integrations.biomesoplenty;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.api.item.BOPItems;
import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.integrations.dungeontactics.DungeonTactics;
import com.github.sokyranthedragon.mia.integrations.dungeontactics.IDungeonTacticsIntegration;
import mcp.MethodsReturnNonnullByDefault;

import javax.annotation.Nullable;

import static com.github.sokyranthedragon.mia.utilities.LootUtils.addDtLoot;
import static com.github.sokyranthedragon.mia.utilities.LootUtils.setMetadataFunction;

@MethodsReturnNonnullByDefault
class DungeonTacticsBopIntegration implements IDungeonTacticsIntegration
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
                    addDtLoot(loot, BOPBlocks.log_0, setMetadataFunction(4, 7));
                    addDtLoot(loot, BOPBlocks.log_1, setMetadataFunction(4, 7));
                    addDtLoot(loot, BOPBlocks.log_2, setMetadataFunction(4, 7));
                    addDtLoot(loot, BOPBlocks.log_3, setMetadataFunction(4, 5));
                    addDtLoot(loot, BOPBlocks.sapling_0, setMetadataFunction(0, 7));
                    addDtLoot(loot, BOPBlocks.sapling_1, setMetadataFunction(0, 7));
                    addDtLoot(loot, BOPBlocks.sapling_2, setMetadataFunction(0, 7));
                    break;
                case FOOD:
                    addDtLoot(loot, BOPItems.berries);
                    addDtLoot(loot, BOPItems.pear);
                    addDtLoot(loot, BOPItems.peach);
                    addDtLoot(loot, BOPItems.persimmon);
                    addDtLoot(loot, BOPItems.shroompowder);
                    addDtLoot(loot, BOPItems.ricebowl);
                    break;
                case MAGIC:
                    addDtLoot(loot, BOPItems.jar_filled, setMetadataFunction(1));
                    break;
                case ORE:
                    for (int meta = 0; meta <= 7; meta++)
                        addDtLoot(loot, BOPBlocks.gem_ore, Integer.toString(meta), setMetadataFunction(meta));
                    break;
                case RECORD:
                    addDtLoot(loot, BOPItems.record_wanderer);
                    break;
            }
        };
    }
    
    @Override
    public ModIds getModId()
    {
        return ModIds.BIOMES_O_PLENTY;
    }
}
