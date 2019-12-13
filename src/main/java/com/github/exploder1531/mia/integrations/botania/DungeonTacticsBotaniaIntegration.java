package com.github.exploder1531.mia.integrations.botania;

import com.github.exploder1531.mia.integrations.ModIds;
import com.github.exploder1531.mia.integrations.dungeontactics.IDungeonTacticsIntegration;
import com.github.exploder1531.mia.utilities.LootUtils;
import net.minecraft.world.storage.loot.LootPool;
import vazkii.botania.common.block.ModBlocks;
import vazkii.botania.common.item.ModItems;

import javax.annotation.Nonnull;

public class DungeonTacticsBotaniaIntegration implements IDungeonTacticsIntegration
{
    @Override
    public void insertBagLoot(BagTypes type, LootPool loot)
    {
        switch (type)
        {
            case ARBOUR:
                LootUtils.addDtLoot(loot, ModBlocks.livingwood, "Botania:LivingWood", 1);
                break;
            case MAGIC:
                LootUtils.addDtLoot(loot, ModItems.petal, "Botania:Petal", LootUtils.setMetadataFunction(0, 15));
                break;
            case RECORD:
                LootUtils.addDtLoot(loot, ModItems.recordGaia1, "Botania:RecordGaia1", 1);
                LootUtils.addDtLoot(loot, ModItems.recordGaia2, "Botania:RecordGaia2", 1);
                break;
        }
    }
    
    @Nonnull
    @Override
    public String getModId()
    {
        return ModIds.BOTANIA;
    }
}
