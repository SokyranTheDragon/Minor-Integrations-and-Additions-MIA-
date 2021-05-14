package com.github.sokyranthedragon.mia.integrations.aether_continuation;

import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.integrations.dungeontactics.DungeonTactics;
import com.github.sokyranthedragon.mia.integrations.dungeontactics.IDungeonTacticsIntegration;
import com.gildedgames.the_aether.addon.items.ItemsAetherAddon;
import mcp.MethodsReturnNonnullByDefault;

import javax.annotation.Nullable;

import static com.github.sokyranthedragon.mia.utilities.LootUtils.addDtLoot;

@MethodsReturnNonnullByDefault
class DungeonTacticsAetherContinuationIntegration implements IDungeonTacticsIntegration
{
    @Nullable
    @Override
    public DungeonTactics.ILootBagListener registerLootBagListener()
    {
        return (type, loot) ->
        {
            if (type == BagTypes.FOOD)
            {
                addDtLoot(loot, ItemsAetherAddon.cockatrice);
                addDtLoot(loot, ItemsAetherAddon.burnt_cockatrice);
                addDtLoot(loot, ItemsAetherAddon.enchanted_cockatrice, 3);
                addDtLoot(loot, ItemsAetherAddon.cooked_enchanted_cockatrice, 3);
            }
        };
    }

    @Override
    public ModIds getModId()
    {
        return ModIds.AETHER_CONTINUATION;
    }
}
