package com.github.sokyranthedragon.mia.integrations.dungeontactics;

import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.integrations.industrialforegoing.IIndustrialForegoingIntegration;
import mcp.MethodsReturnNonnullByDefault;

@MethodsReturnNonnullByDefault
class IndustrialForegoingDungeonTacticsIntegration implements IIndustrialForegoingIntegration
{
    @Override
    public boolean loadLaserDrillEntries()
    {
        return true;
    }
    
    @Override
    public ModIds getModId()
    {
        return ModIds.DUNGEON_TACTICS;
    }
}
