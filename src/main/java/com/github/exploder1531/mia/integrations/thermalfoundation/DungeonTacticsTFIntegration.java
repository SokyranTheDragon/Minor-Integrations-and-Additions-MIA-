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
            LootUtils.addDtLoot(loot, TFBlocks.blockOre, "TF:OreCommon", LootUtils.setMetadataFunction(0, 5));
            LootUtils.addDtLoot(loot, TFBlocks.blockOre, "TF:OreUncommon", 3, LootUtils.setMetadataFunction(6, 7));
            LootUtils.addDtLoot(loot, TFBlocks.blockOre, "TF:OreRare", 1, LootUtils.setMetadataFunction(8, 8));
            LootUtils.addDtLoot(loot, TFBlocks.blockOreFluid, "TF:OreFluidOilSand", 2, LootUtils.setMetadataFunction(0, 0));
            LootUtils.addDtLoot(loot, TFBlocks.blockOreFluid, "TF:OreFluidOilGravel", 2, LootUtils.setMetadataFunction(1, 1));
            LootUtils.addDtLoot(loot, TFBlocks.blockOreFluid, "TF:OreFluidOilRedSand", 2, LootUtils.setMetadataFunction(5, 5));
            LootUtils.addDtLoot(loot, TFBlocks.blockOreFluid, "TF:OreFluidOilRedstone", 5, LootUtils.setMetadataFunction(2, 2));
            LootUtils.addDtLoot(loot, TFBlocks.blockOreFluid, "TF:OreFluidOilGlowstone", 4, LootUtils.setMetadataFunction(3, 3));
            LootUtils.addDtLoot(loot, TFBlocks.blockOreFluid, "TF:OreFluidOilEnder", 3, LootUtils.setMetadataFunction(4, 4));
        }
    }
    
    @Nonnull
    @Override
    public String getModId()
    {
        return ModIds.THERMAL_FOUNDATION;
    }
}
