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
                LootUtils.addDtLoot(loot, BlocksTC.logGreatwood, "Thaumcraft:LogGreatwood", 2);
                LootUtils.addDtLoot(loot, BlocksTC.logSilverwood, "Thaumcraft:LogSilverwood", 2);
                LootUtils.addDtLoot(loot, BlocksTC.saplingGreatwood, "Thaumcraft:SaplingGreatwood", 2);
                LootUtils.addDtLoot(loot, BlocksTC.saplingSilverwood, "Thaumcraft:SaplingSilverwood", 2);
                break;
            case BOOK:
                LootUtils.addDtLoot(loot, ItemsTC.celestialNotes, "Thaumcraft:Notes", 2, LootUtils.setMetadataFunction(0, 12));
                LootUtils.addDtLoot(loot, ItemsTC.label, "Thaumcraft:Label", 2);
                break;
            case FOOD:
                LootUtils.addDtLoot(loot, ItemsTC.chunks, "Thaumcraft:EdibleChunks", LootUtils.setMetadataFunction(0, 5), LootUtils.setCountFunction(1, 3));
                LootUtils.addDtLoot(loot, ItemsTC.tripleMeatTreat, "Thaumcraft:TripleMeat");
                break;
            case MAGIC:
                LootUtils.addDtLoot(loot, ItemsTC.celestialNotes, "Thaumcraft:Notes", 4, LootUtils.setMetadataFunction(0, 12));
                LootUtils.addDtLoot(loot, ItemsTC.salisMundus, "Thaumcraft:SalisMundus", 4);
                LootUtils.addDtLoot(loot, BlocksTC.nitor.get(EnumDyeColor.YELLOW), "Thaumcraft:Nitor", 1);
                LootUtils.addDtLoot(loot, ItemsTC.alumentum, "Thaumcraft:Alumentum", 1);
                LootUtils.addDtLoot(loot, ItemsTC.brain, "Thaumcraft:Brain", 2);
                
                // TODO: add a custom loot function (?) to create a random Vis Crystal (with option to choose primal, non-primal, any), add it here
                break;
            case ORE:
                LootUtils.addDtLoot(loot, BlocksTC.oreAmber, "Thaumcraft:AmberOre", 2);
                LootUtils.addDtLoot(loot, BlocksTC.oreCinnabar, "Thaumcraft:CinnabarOre", 2);
                break;
            case TOOL:
                LootUtils.addDtLoot(loot, ItemsTC.thaumometer, "Thaumcraft:Thaumometer", 1);
                LootUtils.addDtLoot(loot, ItemsTC.golemBell, "Thaumcraft:GolemBell", 1);
                LootUtils.addDtLoot(loot, ItemsTC.scribingTools, "Thaumcraft:ScribingTools", 3);
                break;
        }
    }
    
    @Nonnull
    @Override
    public String getModId()
    {
        return ModIds.THAUMCRAFT;
    }
}
