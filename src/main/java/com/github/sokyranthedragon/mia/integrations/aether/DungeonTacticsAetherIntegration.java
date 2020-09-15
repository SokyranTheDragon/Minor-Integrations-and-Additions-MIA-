package com.github.sokyranthedragon.mia.integrations.aether;

import com.gildedgames.the_aether.blocks.BlocksAether;
import com.gildedgames.the_aether.items.ItemsAether;
import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.integrations.dungeontactics.DungeonTactics;
import com.github.sokyranthedragon.mia.integrations.dungeontactics.IDungeonTacticsIntegration;
import mcp.MethodsReturnNonnullByDefault;

import javax.annotation.Nullable;

import static com.github.sokyranthedragon.mia.utilities.LootUtils.*;

@MethodsReturnNonnullByDefault
class DungeonTacticsAetherIntegration implements IDungeonTacticsIntegration
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
                    addDtLoot(loot, BlocksAether.aether_log, "normal", setMetadataFunction(0));
                    addDtLoot(loot, BlocksAether.aether_log, "golden", 1, setMetadataFunction(1));
                    addDtLoot(loot, BlocksAether.skyroot_sapling);
                    addDtLoot(loot, BlocksAether.golden_oak_sapling, 1);
                    break;
                case FOOD:
                    addDtLoot(loot, ItemsAether.candy_cane);
                    addDtLoot(loot, ItemsAether.ginger_bread_man);
                    addDtLoot(loot, ItemsAether.blue_berry);
                    addDtLoot(loot, ItemsAether.enchanted_blueberry, 1);
                    addDtLoot(loot, ItemsAether.gummy_swet, 1, setMetadataFunction(0, 1));
                    break;
                case ORE:
                    addDtLoot(loot, BlocksAether.ambrosium_ore);
                    addDtLoot(loot, BlocksAether.zanite_ore);
                    addDtLoot(loot, BlocksAether.gravitite_ore);
                    break;
                case QUIVER:
                    addDtLoot(loot, ItemsAether.dart, "basic", setMetadataFunction(0), setCountFunction(1, 16));
                    addDtLoot(loot, ItemsAether.dart, "level2", 3, setMetadataFunction(1), setCountFunction(1, 16));
                    addDtLoot(loot, ItemsAether.dart, "level3", 1, setMetadataFunction(2), setCountFunction(1, 16));
                    break;
                case RECORD:
                    addDtLoot(loot, ItemsAether.aether_tune);
                    addDtLoot(loot, ItemsAether.ascending_dawn);
                    addDtLoot(loot, ItemsAether.welcoming_skies);
                    addDtLoot(loot, ItemsAether.legacy);
                    break;
                case TOOL:
                    addDtLoot(loot, ItemsAether.cloud_parachute);
                    addDtLoot(loot, ItemsAether.golden_parachute, 1);
                    break;
                case SOLSTICE:
                    addDtLoot(loot, ItemsAether.candy_cane);
                    addDtLoot(loot, ItemsAether.ginger_bread_man);
                    addDtLoot(loot, ItemsAether.candy_cane_sword);
                    break;
            }
        };
    }
    
    @Override
    public ModIds getModId()
    {
        return ModIds.AETHER;
    }
}
