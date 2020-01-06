package com.github.exploder1531.mia.integrations.xu2;

import com.github.exploder1531.mia.integrations.ModIds;
import com.github.exploder1531.mia.integrations.quark.IQuarkIntegration;
import com.rwtema.extrautils2.backend.entries.XU2Entries;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.item.ItemStack;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

@MethodsReturnNonnullByDefault
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
    
    @Override
    public Collection<ItemStack> getItemsToShowEnchantmentsFor()
    {
        return Collections.singleton(XU2Entries.boomerang.newStack());
    }
    
    @Override
    public ModIds getModId()
    {
        return ModIds.EXTRA_UTILITIES;
    }
}
