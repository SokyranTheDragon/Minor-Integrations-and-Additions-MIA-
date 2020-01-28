package com.github.sokyranthedragon.mia.integrations.natura;

import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.integrations.dungeontactics.DungeonTactics;
import com.github.sokyranthedragon.mia.integrations.dungeontactics.IDungeonTacticsIntegration;
import com.github.sokyranthedragon.mia.utilities.LootUtils;
import com.progwml6.natura.common.config.Config;
import com.progwml6.natura.entities.NaturaEntities;
import com.progwml6.natura.nether.NaturaNether;
import com.progwml6.natura.overworld.NaturaOverworld;
import com.progwml6.natura.shared.NaturaCommons;
import com.progwml6.natura.tools.NaturaTools;
import mcp.MethodsReturnNonnullByDefault;

import javax.annotation.Nullable;

import static com.progwml6.natura.Natura.pulseManager;

@MethodsReturnNonnullByDefault
class DungeonTacticsNaturaIntegration implements IDungeonTacticsIntegration
{
    @Nullable
    @Override
    public DungeonTactics.ILootBagListener registerLootBagListener()
    {
        return (type, loot) ->
        {
            switch (type)
            {
                case ARBOUR:
                    if (pulseManager.isPulseLoaded(NaturaOverworld.PulseId))
                    {
                        LootUtils.addDtLoot(loot, NaturaOverworld.overworldLog, LootUtils.setMetadataFunction(0, 3));
                        LootUtils.addDtLoot(loot, NaturaOverworld.overworldLog2, LootUtils.setMetadataFunction(0, 3));
                        LootUtils.addDtLoot(loot, NaturaOverworld.redwoodLog, LootUtils.setMetadataFunction(0, 2));
                        LootUtils.addDtLoot(loot, NaturaOverworld.overworldSapling, LootUtils.setMetadataFunction(0, 3));
                        LootUtils.addDtLoot(loot, NaturaOverworld.overworldSapling2, LootUtils.setMetadataFunction(0, 3));
                        if (Config.generateRedwood)
                            LootUtils.addDtLoot(loot, NaturaOverworld.redwoodSapling, 1);
                    }
                    if (pulseManager.isPulseLoaded(NaturaNether.PulseId))
                    {
                        LootUtils.addDtLoot(loot, NaturaNether.netherLog, LootUtils.setMetadataFunction(0, 2));
                        LootUtils.addDtLoot(loot, NaturaNether.netherLog2);
                        LootUtils.addDtLoot(loot, NaturaNether.netherSapling, LootUtils.setMetadataFunction(0, 2));
                        LootUtils.addDtLoot(loot, NaturaNether.netherSapling2);
                    }
                    break;
                case FOOD:
                    if (pulseManager.isPulseLoaded(NaturaCommons.PulseId))
                    {
                        if (pulseManager.isPulseLoaded(NaturaEntities.PulseId))
                        {
                            LootUtils.addDtLoot(loot, NaturaCommons.edibles, "_imp_meat_raw", 4, LootUtils.setMetadataFunction(0));
                            LootUtils.addDtLoot(loot, NaturaCommons.edibles, "_imp_meat_cooked", 4, LootUtils.setMetadataFunction(1));
                        }
                        if (pulseManager.isPulseLoaded(NaturaOverworld.PulseId))
                        {
                            LootUtils.addDtLoot(loot, NaturaOverworld.saguaroFruitItem);
                            LootUtils.addDtLoot(loot, NaturaCommons.soups, "_berry_medley", LootUtils.setMetadataFunction(9));
                            LootUtils.addDtLoot(loot, NaturaCommons.edibles, "_overworld_berries", LootUtils.setMetadataFunction(2, 5));
                        }
                        if (pulseManager.isPulseLoaded(NaturaNether.PulseId))
                        {
                            LootUtils.addDtLoot(loot, NaturaCommons.edibles, "_nether_berries", 4, LootUtils.setMetadataFunction(6, 9));
                            LootUtils.addDtLoot(loot, NaturaCommons.edibles, "_potash_apple", 4, LootUtils.setMetadataFunction(10));
                            LootUtils.addDtLoot(loot, NaturaCommons.soups, "_glowshroom_soup", 4, LootUtils.setMetadataFunction(4, 8));
                        }
                        
                        LootUtils.addDtLoot(loot, NaturaCommons.edibles, "_cactus_juice", LootUtils.setMetadataFunction(11));
                    }
                    break;
                case TOOL:
                    if (pulseManager.isPulseLoaded(NaturaTools.PulseId) && pulseManager.isPulseLoaded(NaturaNether.PulseId))
                        LootUtils.addDtLoot(loot, NaturaTools.flintAndBlaze, 2);
                    break;
            }
        };
    }
    
    @Override
    public ModIds getModId()
    {
        return ModIds.NATURA;
    }
}
