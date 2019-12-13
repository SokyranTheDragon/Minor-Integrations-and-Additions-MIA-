package com.github.exploder1531.mia.integrations.tconstruct;

import com.github.exploder1531.mia.integrations.ModIds;
import com.github.exploder1531.mia.integrations.dungeontactics.IDungeonTacticsIntegration;
import com.github.exploder1531.mia.utilities.LootUtils;
import net.minecraft.world.storage.loot.LootPool;
import slimeknights.tconstruct.shared.TinkerCommons;
import slimeknights.tconstruct.world.TinkerWorld;

import javax.annotation.Nonnull;

class DungeonTacticsTConstructIntegration implements IDungeonTacticsIntegration
{
    @Override
    public void insertBagLoot(BagTypes type, LootPool loot)
    {
        switch (type)
        {
            case ARBOUR:
                LootUtils.addDtLoot(loot, TinkerWorld.slimeSapling, "TCon:SaplingSlime", 1, LootUtils.setMetadataFunction(0, 2));
                break;
            case FOOD:
                LootUtils.addDtLoot(loot, TinkerCommons.edibles, "TCon:Bacon", 5, LootUtils.setMetadataFunction(0, 0));
                LootUtils.addDtLoot(loot, TinkerCommons.edibles, "TCon:MeatJerky", 5, LootUtils.setMetadataFunction(10, 15));
                LootUtils.addDtLoot(loot, TinkerCommons.edibles, "TCon:FishJerky", 5, LootUtils.setMetadataFunction(20, 23));
//                LootUtils.addDtLoot(loot, TinkerCommons.edibles, "TCon:FishJerky", 5, LootUtils.setMetadataFunction(20, 23));
                LootUtils.addDtLoot(loot, TinkerCommons.edibles, "TCon:SlimeCongealed", 5, LootUtils.setMetadataFunction(30, 34));
                break;
            case ORE:
                LootUtils.addDtLoot(loot, TinkerCommons.blockOre, "TCon:Ore", LootUtils.setMetadataFunction(0, 1));
                break;
        }
    }
    
    @Nonnull
    @Override
    public String getModId()
    {
        return ModIds.TINKERS_CONSTRUCT;
    }
}
