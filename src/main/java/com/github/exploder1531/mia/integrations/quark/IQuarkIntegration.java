package com.github.exploder1531.mia.integrations.quark;

import com.github.exploder1531.mia.integrations.base.IModIntegration;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.item.ItemStack;

import java.util.Collection;
import java.util.HashSet;

@MethodsReturnNonnullByDefault
public interface IQuarkIntegration extends IModIntegration
{
    default Collection<String> getAllowedAncientTomeEnchants()
    {
        return new HashSet<>();
    }
    
    default Collection<ItemStack> getItemsToShowEnchantmentsFor()
    {
        return new HashSet<>();
    }
}
