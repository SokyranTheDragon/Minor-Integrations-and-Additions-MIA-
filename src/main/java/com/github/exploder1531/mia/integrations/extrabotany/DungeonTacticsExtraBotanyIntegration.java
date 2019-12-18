package com.github.exploder1531.mia.integrations.extrabotany;

import com.github.exploder1531.mia.integrations.ModIds;
import com.github.exploder1531.mia.integrations.dungeontactics.IDungeonTacticsIntegration;
import com.github.exploder1531.mia.utilities.LootUtils;
import com.meteor.extrabotany.common.item.ModItems;
import net.minecraft.world.storage.loot.LootPool;

import javax.annotation.Nonnull;

class DungeonTacticsExtraBotanyIntegration implements IDungeonTacticsIntegration
{
    @Override
    public void insertBagLoot(BagTypes type, LootPool loot)
    {
        switch (type)
        {
            case FOOD:
                LootUtils.addDtLoot(loot, ModItems.friedchicken);
                LootUtils.addDtLoot(loot, ModItems.gildedmashedpotato, 1);
                LootUtils.addDtLoot(loot, ModItems.manadrink, 1);
                break;
            case RECORD:
                LootUtils.addDtLoot(loot, ModItems.gaiarecord, 1);
                LootUtils.addDtLoot(loot, ModItems.herrscherrecord, 1);
                break;
        }
    }
    
    @Nonnull
    @Override
    public String getModId()
    {
        return ModIds.EXTRABOTANY;
    }
}
