package com.github.exploder1531.mia.integrations.futuremc;

import com.github.exploder1531.mia.integrations.ModIds;
import com.github.exploder1531.mia.integrations.quark.IQuarkIntegration;
import mcp.MethodsReturnNonnullByDefault;

import java.util.Arrays;
import java.util.Collection;

@MethodsReturnNonnullByDefault
class QuarkFutureMcIntegration implements IQuarkIntegration
{
    @Override
    public Collection<String> getAllowedAncientTomeEnchants()
    {
        return Arrays.asList(
                "futuremc:impaling",
                "futuremc:riptide");
    }
    
    @Override
    public ModIds getModId()
    {
        return ModIds.FUTURE_MC;
    }
}
