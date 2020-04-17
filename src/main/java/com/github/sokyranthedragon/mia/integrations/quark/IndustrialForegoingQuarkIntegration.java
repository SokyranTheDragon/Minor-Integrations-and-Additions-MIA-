package com.github.sokyranthedragon.mia.integrations.quark;

import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.integrations.industrialforegoing.IIndustrialForegoingIntegration;
import mcp.MethodsReturnNonnullByDefault;

@MethodsReturnNonnullByDefault
class IndustrialForegoingQuarkIntegration implements IIndustrialForegoingIntegration
{
    @Override
    public boolean loadLaserDrillEntries()
    {
        return true;
    }
    
    @Override
    public ModIds getModId()
    {
        return ModIds.QUARK;
    }
}
