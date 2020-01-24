package com.github.sokyranthedragon.mia.integrations.mocreatures;

import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.integrations.dungeontactics.DungeonTactics;
import com.github.sokyranthedragon.mia.integrations.dungeontactics.IDungeonTacticsIntegration;
import com.github.sokyranthedragon.mia.utilities.LootUtils;
import drzhark.mocreatures.init.MoCBlocks;
import drzhark.mocreatures.init.MoCItems;
import net.minecraft.world.storage.loot.RandomValueRange;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraft.world.storage.loot.functions.SetMetadata;

import javax.annotation.Nonnull;

class DungeonTacticsMoCreaturesIntegration implements IDungeonTacticsIntegration
{
    @Override
    public DungeonTactics.ILootBagListener registerLootBagListener()
    {
        return (type, loot) ->
        {
            switch (type)
            {
                case ARBOUR:
                    LootUtils.addDtLoot(loot, MoCBlocks.mocLog, 1, new SetMetadata(new LootCondition[0], new RandomValueRange(0, 1)));
                    break;
                case RECORD:
                    LootUtils.addDtLoot(loot, MoCItems.recordshuffle, 1);
                    break;
                case TOOL:
                    LootUtils.addDtLoot(loot, MoCItems.whip, LootUtils.getDtToolFunctions());
                    LootUtils.addDtLoot(loot, MoCItems.fishnet);
                    break;
                case FOOD:
                    LootUtils.addDtLoot(loot, MoCItems.omelet);
                    LootUtils.addDtLoot(loot, MoCItems.turtleraw);
                    LootUtils.addDtLoot(loot, MoCItems.turtlesoup);
                    LootUtils.addDtLoot(loot, MoCItems.ostrichraw);
                    LootUtils.addDtLoot(loot, MoCItems.ostrichcooked);
                    LootUtils.addDtLoot(loot, MoCItems.rawTurkey);
                    LootUtils.addDtLoot(loot, MoCItems.cookedTurkey);
                    LootUtils.addDtLoot(loot, MoCItems.ratRaw);
                    LootUtils.addDtLoot(loot, MoCItems.ratCooked);
                    LootUtils.addDtLoot(loot, MoCItems.ratBurger);
                    LootUtils.addDtLoot(loot, MoCItems.crabraw);
                    LootUtils.addDtLoot(loot, MoCItems.crabcooked);
                    break;
            }
        };
    }
    
    @Nonnull
    @Override
    public ModIds getModId()
    {
        return ModIds.MO_CREATURES;
    }
}
