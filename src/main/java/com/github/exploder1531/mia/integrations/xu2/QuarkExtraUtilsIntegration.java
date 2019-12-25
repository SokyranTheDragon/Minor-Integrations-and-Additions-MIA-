package com.github.exploder1531.mia.integrations.xu2;

import com.github.exploder1531.mia.integrations.ModIds;
import com.github.exploder1531.mia.integrations.quark.IQuarkIntegration;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.Collection;

class QuarkExtraUtilsIntegration implements IQuarkIntegration
{
    @Override
    public Collection<String> getAllowedAncientTomeEnchants()
    {
        return Arrays.asList(
                "extrautils2:xu.kaboomerang",
                "extrautils2:xu.zoomerang",
                "extrautils2:xu.bladerang",
                "extrautils2:xu.boomereaperang");
    }
    
    @Nonnull
    @Override
    public String getModId()
    {
        return ModIds.EXTRA_UTILITIES;
    }
}
