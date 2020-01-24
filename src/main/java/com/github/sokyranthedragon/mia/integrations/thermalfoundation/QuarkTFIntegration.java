package com.github.sokyranthedragon.mia.integrations.thermalfoundation;

import cofh.thermalfoundation.init.TFItems;
import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.integrations.quark.IQuarkIntegration;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.item.ItemStack;

import java.util.Collection;
import java.util.Collections;

@MethodsReturnNonnullByDefault
class QuarkTFIntegration implements IQuarkIntegration
{
    @Override
    public Collection<ItemStack> getItemsToShowEnchantmentsFor()
    {
        return Collections.singleton(new ItemStack(TFItems.itemTomeExperience));
    }
    
    @Override
    public ModIds getModId()
    {
        return ModIds.THERMAL_FOUNDATION;
    }
}
