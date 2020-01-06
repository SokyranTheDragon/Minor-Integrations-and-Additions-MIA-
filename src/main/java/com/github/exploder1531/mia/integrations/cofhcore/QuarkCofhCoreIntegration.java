package com.github.exploder1531.mia.integrations.cofhcore;

import com.github.exploder1531.mia.integrations.ModIds;
import com.github.exploder1531.mia.integrations.quark.IQuarkIntegration;
import mcp.MethodsReturnNonnullByDefault;

import java.util.Arrays;
import java.util.Collection;

@MethodsReturnNonnullByDefault
class QuarkCofhCoreIntegration implements IQuarkIntegration
{
    @Override
    public Collection<String> getAllowedAncientTomeEnchants()
    {
        return Arrays.asList(
                "cofhcore:holding", // Upgrades max level satchel from 9x9 to 10x9 inventory and cache from 1,500,000 to 1,750,000 slots
                "cofhcore:insight",
                "cofhcore:leech",
                "cofhcore:multishot",
                "cofhcore:soulbound",
                "cofhcore:vorpal");
    }
    
    @Override
    public ModIds getModId()
    {
        return ModIds.COFH_CORE;
    }
}
