package com.github.exploder1531.mia.integrations.thermalfoundation;

import cofh.thermalfoundation.init.TFBlocks;
import com.github.exploder1531.mia.integrations.ModIds;
import com.github.exploder1531.mia.integrations.dungeontactics.IDungeonTacticsIntegration;
import com.github.exploder1531.mia.utilities.LootUtils;
import net.minecraft.world.storage.loot.LootPool;

import javax.annotation.Nonnull;

class DungeonTacticsTFIntegration implements IDungeonTacticsIntegration
{
    @Override
    public void insertBagLoot(BagTypes type, LootPool loot)
    {
        if (type == BagTypes.ORE)
        {
            LootUtils.addDtLoot(loot, TFBlocks.blockOre, "TF:OreCommonCopper", LootUtils.setMetadataFunction(0, 0));
            LootUtils.addDtLoot(loot, TFBlocks.blockOre, "TF:OreCommonTin", LootUtils.setMetadataFunction(1, 1));
//            LootUtils.addDtLoot(loot, TFBlocks.blockOre, "TF:OreCommonSilver", LootUtils.setMetadataFunction(2, 2));
            LootUtils.addDtLoot(loot, TFBlocks.blockOre, "TF:OreCommonLead", LootUtils.setMetadataFunction(3, 3));
            LootUtils.addDtLoot(loot, TFBlocks.blockOre, "TF:OreCommonAluminum", LootUtils.setMetadataFunction(4, 4));
            LootUtils.addDtLoot(loot, TFBlocks.blockOre, "TF:OreCommonNickel", LootUtils.setMetadataFunction(5, 5));
            LootUtils.addDtLoot(loot, TFBlocks.blockOre, "TF:OreUncommonPlatinum", 3, LootUtils.setMetadataFunction(6, 6));
            LootUtils.addDtLoot(loot, TFBlocks.blockOre, "TF:OreUncommonIridium", 3, LootUtils.setMetadataFunction(7, 7));
            LootUtils.addDtLoot(loot, TFBlocks.blockOre, "TF:OreRareMithril", 1, LootUtils.setMetadataFunction(8, 8));
            LootUtils.addDtLoot(loot, TFBlocks.blockOreFluid, "TF:OreFluidOil", 5);
            LootUtils.addDtLoot(loot, TFBlocks.blockOreFluid, "TF:OreFluidRedstone", 5, LootUtils.setMetadataFunction(2, 2));
            LootUtils.addDtLoot(loot, TFBlocks.blockOreFluid, "TF:OreFluidGlowstone", 4, LootUtils.setMetadataFunction(3, 3));
            LootUtils.addDtLoot(loot, TFBlocks.blockOreFluid, "TF:OreFluidEnder", 3, LootUtils.setMetadataFunction(4, 4));
        }
    }
    
    @Nonnull
    @Override
    public String getModId()
    {
        return ModIds.THERMAL_FOUNDATION;
    }
}
