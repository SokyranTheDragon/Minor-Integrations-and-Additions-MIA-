package com.github.exploder1531.mia.integrations.dungeontactics;

import com.gendeathrow.hatchery.core.config.ConfigLootHandler;
import com.github.exploder1531.mia.integrations.ModIds;
import com.github.exploder1531.mia.integrations.hatchery.IHatcheryIntegration;
import mcp.MethodsReturnNonnullByDefault;
import pegbeard.dungeontactics.handlers.DTItems;

import javax.annotation.Nonnull;
import java.util.LinkedList;
import java.util.List;

import static com.github.exploder1531.mia.integrations.hatchery.Hatchery.getDrop;

@MethodsReturnNonnullByDefault
class HatcheryDungeonTacticsIntegration implements IHatcheryIntegration
{
    private final boolean modEnabled;
    
    HatcheryDungeonTacticsIntegration(boolean modEnabled)
    {
        this.modEnabled = modEnabled;
    }
    
    @Override
    public boolean isModEnabled()
    {
        return modEnabled;
    }
    
    @Override
    public int getCurrentLootVersion()
    {
        return 0;
    }
    
    @Nonnull
    @Override
    public List<ConfigLootHandler.ItemDrop> getDefaultEggDrops()
    {
        List<ConfigLootHandler.ItemDrop> drops = new LinkedList<>();
    
        drops.add(getDrop(DTItems.INGOT_MITHRIL, 1));
        drops.add(getDrop(DTItems.INGOT_STEEL, 2));
        drops.add(getDrop(DTItems.INGOT_SILVER, 3));
        drops.add(getDrop(DTItems.MAGIC_POWDER, 1, 1, 4));
        
        return drops;
    }
    
    @Override
    public ModIds getModId()
    {
        return ModIds.DUNGEON_TACTICS;
    }
}
