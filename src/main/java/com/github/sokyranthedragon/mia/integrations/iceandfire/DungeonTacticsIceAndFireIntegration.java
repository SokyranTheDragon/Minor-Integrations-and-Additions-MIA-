package com.github.sokyranthedragon.mia.integrations.iceandfire;

import com.github.alexthe666.iceandfire.block.IafBlockRegistry;
import com.github.alexthe666.iceandfire.item.IafItemRegistry;
import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.integrations.dungeontactics.DungeonTactics;
import com.github.sokyranthedragon.mia.integrations.dungeontactics.IDungeonTacticsIntegration;
import com.github.sokyranthedragon.mia.utilities.LootUtils;

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
                    LootUtils.addDtLoot(loot, IafItemRegistry.manuscript, LootUtils.setCountFunction(3, 5));
                    break;
                case ORE:
                    LootUtils.addDtLoot(loot, IafBlockRegistry.sapphireOre);
//                LootUtils.addDtLoot(loot, ModBlocks.silverOre);
                    break;
                case FOOD:
                    LootUtils.addDtLoot(loot, IafItemRegistry.pixie_dust, 1);
                    LootUtils.addDtLoot(loot, IafItemRegistry.ambrosia, 1);
                    LootUtils.addDtLoot(loot, IafItemRegistry.fire_dragon_flesh, 1);
                    LootUtils.addDtLoot(loot, IafItemRegistry.ice_dragon_flesh, 1);
                    break;
                case QUIVER:
                    LootUtils.addDtLoot(loot, IafItemRegistry.amphithere_arrow, 7, LootUtils.setCountFunction(1, 16));
                    LootUtils.addDtLoot(loot, IafItemRegistry.dragonbone_arrow, 7, LootUtils.setCountFunction(1, 16));
                    LootUtils.addDtLoot(loot, IafItemRegistry.sea_serpent_arrow, 7, LootUtils.setCountFunction(1, 16));
                    LootUtils.addDtLoot(loot, IafItemRegistry.stymphalian_arrow, 7, LootUtils.setCountFunction(1, 16));
                    LootUtils.addDtLoot(loot, IafItemRegistry.hydra_arrow, 7, LootUtils.setCountFunction(1, 16));
                    break;
                case TOOL:
                    LootUtils.addDtLoot(loot, IafItemRegistry.fishing_spear);
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
