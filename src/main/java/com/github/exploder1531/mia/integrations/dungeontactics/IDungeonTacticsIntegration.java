package com.github.exploder1531.mia.integrations.dungeontactics;

import com.github.exploder1531.mia.integrations.base.IModIntegration;
import net.minecraft.world.storage.loot.LootPool;

public interface IDungeonTacticsIntegration extends IModIntegration
{
    enum BagTypes
    {
        ARBOUR,
        BOOK,
        FOOD,
        MAGIC,
        ORE,
        POTION,
        QUIVER,
        RECORD,
        SAMHAIN,
        SOLSTICE,
        TOOL
    }
    
    void insertBagLoot(BagTypes type, LootPool loot);
}
