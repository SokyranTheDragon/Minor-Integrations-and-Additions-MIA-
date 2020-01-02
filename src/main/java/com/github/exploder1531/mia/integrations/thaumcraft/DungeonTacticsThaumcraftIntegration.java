package com.github.exploder1531.mia.integrations.thaumcraft;

import com.github.exploder1531.mia.integrations.ModIds;
import com.github.exploder1531.mia.integrations.dungeontactics.IDungeonTacticsIntegration;
import com.github.exploder1531.mia.utilities.LootUtils;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.world.storage.loot.LootPool;
import thaumcraft.api.blocks.BlocksTC;
import thaumcraft.api.items.ItemsTC;

import javax.annotation.Nonnull;

class DungeonTacticsThaumcraftIntegration implements IDungeonTacticsIntegration
{
    @Override
    public void insertBagLoot(BagTypes type, LootPool loot)
    {
        switch (type)
        {
            case ARBOUR:
                LootUtils.addDtLoot(loot, BlocksTC.logGreatwood, 2);
                LootUtils.addDtLoot(loot, BlocksTC.logSilverwood, 2);
                LootUtils.addDtLoot(loot, BlocksTC.saplingGreatwood, 2);
                LootUtils.addDtLoot(loot, BlocksTC.saplingSilverwood, 2);
                break;
            case BOOK:
                LootUtils.addDtLoot(loot, ItemsTC.celestialNotes, 2, LootUtils.setMetadataFunction(0, 12));
                LootUtils.addDtLoot(loot, ItemsTC.label, 2);
                break;
            case FOOD:
                LootUtils.addDtLoot(loot, ItemsTC.chunks, LootUtils.setMetadataFunction(0, 5), LootUtils.setCountFunction(1, 3));
                LootUtils.addDtLoot(loot, ItemsTC.tripleMeatTreat);
                break;
            case MAGIC:
                LootUtils.addDtLoot(loot, ItemsTC.celestialNotes, 4, LootUtils.setMetadataFunction(0, 12));
                LootUtils.addDtLoot(loot, ItemsTC.salisMundus, 4);
                LootUtils.addDtLoot(loot, BlocksTC.nitor.get(EnumDyeColor.YELLOW), 1);
                LootUtils.addDtLoot(loot, ItemsTC.alumentum, 1);
                LootUtils.addDtLoot(loot, ItemsTC.brain, 2);
                
                // TODO: add a custom loot function (?) to create a random Vis Crystal (with option to choose primal, non-primal, any), add it here
                break;
            case ORE:
                LootUtils.addDtLoot(loot, BlocksTC.oreAmber, 2);
                LootUtils.addDtLoot(loot, BlocksTC.oreCinnabar, 2);
                break;
            case TOOL:
                LootUtils.addDtLoot(loot, ItemsTC.thaumometer, 1);
                LootUtils.addDtLoot(loot, ItemsTC.golemBell, 1);
                LootUtils.addDtLoot(loot, ItemsTC.scribingTools, 3);
                break;
        }
    }
    
    @Nonnull
    @Override
    public ModIds getModId()
    {
        return ModIds.THAUMCRAFT;
    }
}
