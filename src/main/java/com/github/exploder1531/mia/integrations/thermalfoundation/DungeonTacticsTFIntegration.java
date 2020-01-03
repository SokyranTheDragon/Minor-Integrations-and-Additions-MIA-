package com.github.exploder1531.mia.integrations.thermalfoundation;

import cofh.thermalfoundation.init.TFBlocks;
import com.github.exploder1531.mia.integrations.ModIds;
import com.github.exploder1531.mia.integrations.dungeontactics.DungeonTactics;
import com.github.exploder1531.mia.integrations.dungeontactics.IDungeonTacticsIntegration;
import com.github.exploder1531.mia.utilities.LootUtils;

import javax.annotation.Nonnull;

class DungeonTacticsTFIntegration implements IDungeonTacticsIntegration
{
    @Override
    public DungeonTactics.ILootBagListener registerLootBagListener()
    {
        return (type, loot) ->
        {
            if (type == BagTypes.ORE)
            {
                LootUtils.addDtLoot(loot, TFBlocks.blockOre, "Copper", LootUtils.setMetadataFunction(0, 0));
                LootUtils.addDtLoot(loot, TFBlocks.blockOre, "Tin", LootUtils.setMetadataFunction(1, 1));
//            LootUtils.addDtLoot(loot, TFBlocks.blockOre, "Silver", LootUtils.setMetadataFunction(2, 2));
                LootUtils.addDtLoot(loot, TFBlocks.blockOre, "Lead", LootUtils.setMetadataFunction(3, 3));
                LootUtils.addDtLoot(loot, TFBlocks.blockOre, "Aluminum", LootUtils.setMetadataFunction(4, 4));
                LootUtils.addDtLoot(loot, TFBlocks.blockOre, "Nickel", LootUtils.setMetadataFunction(5, 5));
                LootUtils.addDtLoot(loot, TFBlocks.blockOre, "Platinum", 3, LootUtils.setMetadataFunction(6, 6));
                LootUtils.addDtLoot(loot, TFBlocks.blockOre, "Iridium", 3, LootUtils.setMetadataFunction(7, 7));
                LootUtils.addDtLoot(loot, TFBlocks.blockOre, "Mithril", 1, LootUtils.setMetadataFunction(8, 8));
                LootUtils.addDtLoot(loot, TFBlocks.blockOreFluid, "Oil", 5);
                LootUtils.addDtLoot(loot, TFBlocks.blockOreFluid, "Redstone", 5, LootUtils.setMetadataFunction(2, 2));
                LootUtils.addDtLoot(loot, TFBlocks.blockOreFluid, "Glowstone", 4, LootUtils.setMetadataFunction(3, 3));
                LootUtils.addDtLoot(loot, TFBlocks.blockOreFluid, "Ender", 3, LootUtils.setMetadataFunction(4, 4));
            }
        };
    }
    
    @Nonnull
    @Override
    public ModIds getModId()
    {
        return ModIds.THERMAL_FOUNDATION;
    }
}
