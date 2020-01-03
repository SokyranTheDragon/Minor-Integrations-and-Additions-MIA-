package com.github.exploder1531.mia.integrations.iceandfire;

import com.github.alexthe666.iceandfire.core.ModBlocks;
import com.github.alexthe666.iceandfire.core.ModItems;
import com.github.exploder1531.mia.integrations.ModIds;
import com.github.exploder1531.mia.integrations.dungeontactics.DungeonTactics;
import com.github.exploder1531.mia.integrations.dungeontactics.IDungeonTacticsIntegration;
import com.github.exploder1531.mia.utilities.LootUtils;

import javax.annotation.Nonnull;

class DungeonTacticsIceAndFireIntegration implements IDungeonTacticsIntegration
{
    @Override
    public DungeonTactics.ILootBagListener registerLootBagListener()
    {
        return (type, loot) ->
        {
            switch (type)
            {
                case BOOK:
                    LootUtils.addDtLoot(loot, ModItems.manuscript, LootUtils.setCountFunction(3, 5));
                    break;
                case ORE:
                    LootUtils.addDtLoot(loot, ModBlocks.sapphireOre);
//                LootUtils.addDtLoot(loot, ModBlocks.silverOre);
                    break;
                case FOOD:
                    LootUtils.addDtLoot(loot, ModItems.pixie_dust, 1);
                    LootUtils.addDtLoot(loot, ModItems.ambrosia, 1);
                    LootUtils.addDtLoot(loot, ModItems.fire_dragon_flesh, 1);
                    LootUtils.addDtLoot(loot, ModItems.ice_dragon_flesh, 1);
                    break;
                case QUIVER:
                    LootUtils.addDtLoot(loot, ModItems.amphithere_arrow, 7, LootUtils.setCountFunction(1, 16));
                    LootUtils.addDtLoot(loot, ModItems.dragonbone_arrow, 7, LootUtils.setCountFunction(1, 16));
                    LootUtils.addDtLoot(loot, ModItems.sea_serpent_arrow, 7, LootUtils.setCountFunction(1, 16));
                    LootUtils.addDtLoot(loot, ModItems.stymphalian_arrow, 7, LootUtils.setCountFunction(1, 16));
                    break;
                case TOOL:
                    LootUtils.addDtLoot(loot, ModItems.fishing_spear);
                    break;
            }
        };
    }
    
    @Nonnull
    @Override
    public ModIds getModId()
    {
        return ModIds.ICE_AND_FIRE;
    }
}
