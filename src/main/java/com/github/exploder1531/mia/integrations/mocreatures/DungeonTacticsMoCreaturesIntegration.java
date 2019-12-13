package com.github.exploder1531.mia.integrations.mocreatures;

import com.github.exploder1531.mia.integrations.ModIds;
import com.github.exploder1531.mia.integrations.dungeontactics.IDungeonTacticsIntegration;
import com.github.exploder1531.mia.utilities.LootUtils;
import drzhark.mocreatures.init.MoCBlocks;
import drzhark.mocreatures.init.MoCItems;
import net.minecraft.world.storage.loot.LootPool;
import net.minecraft.world.storage.loot.RandomValueRange;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraft.world.storage.loot.functions.SetMetadata;

import javax.annotation.Nonnull;

class DungeonTacticsMoCreaturesIntegration implements IDungeonTacticsIntegration
{
    @Override
    public void insertBagLoot(BagTypes type, LootPool loot)
    {
        switch (type)
        {
            case ARBOUR:
                LootUtils.addDtLoot(loot, MoCBlocks.mocLog, "MoC:Log", 1, new SetMetadata(new LootCondition[0], new RandomValueRange(0, 1)));
                break;
            case RECORD:
                LootUtils.addDtLoot(loot, MoCItems.recordshuffle, "MoC:Record", 1);
                break;
            case TOOL:
                LootUtils.addDtLoot(loot, MoCItems.whip, "MoC:Whip", LootUtils.getDtToolFunctions());
                LootUtils.addDtLoot(loot, MoCItems.fishnet, "MoC:Fishnet");
                break;
            case FOOD:
                LootUtils.addDtLoot(loot, MoCItems.omelet, "MoC:Omelet");
                LootUtils.addDtLoot(loot, MoCItems.turtleraw, "MoC:TurtleRaw");
                LootUtils.addDtLoot(loot, MoCItems.turtlesoup, "MoC:TurtleSoup");
                LootUtils.addDtLoot(loot, MoCItems.ostrichraw, "MoC:OstrichRaw");
                LootUtils.addDtLoot(loot, MoCItems.ostrichcooked, "MoC:OstrichCooked");
                LootUtils.addDtLoot(loot, MoCItems.rawTurkey, "MoC:TurkeyRaw");
                LootUtils.addDtLoot(loot, MoCItems.cookedTurkey, "MoC:TurkeyCooked");
                LootUtils.addDtLoot(loot, MoCItems.ratRaw, "MoC:RatRaw");
                LootUtils.addDtLoot(loot, MoCItems.ratCooked, "MoC:RatCooked");
                LootUtils.addDtLoot(loot, MoCItems.ratBurger, "MoC:RatBurger");
                LootUtils.addDtLoot(loot, MoCItems.crabraw, "MoC:CrabRaw");
                LootUtils.addDtLoot(loot, MoCItems.crabcooked, "MoC:CrabCooked");
                break;
        }
    }
    
    @Nonnull
    @Override
    public String getModId()
    {
        return ModIds.MO_CREATURES;
    }
}
