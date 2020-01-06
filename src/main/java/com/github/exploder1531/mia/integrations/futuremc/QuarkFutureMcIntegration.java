package com.github.exploder1531.mia.integrations.futuremc;

import com.github.exploder1531.mia.integrations.ModIds;
import com.github.exploder1531.mia.integrations.quark.IQuarkIntegration;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.item.ItemStack;
import thedarkcolour.futuremc.init.Init;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

@MethodsReturnNonnullByDefault
class QuarkFutureMcIntegration implements IQuarkIntegration
{
    @Override
    public Collection<String> getAllowedAncientTomeEnchants()
    {
        return Arrays.asList(
                "minecraftfuture:impaling",
                "minecraftfuture:riptide");
    }
    
    @Override
    public Collection<ItemStack> getItemsToShowEnchantmentsFor()
    {
        return Collections.singleton(new ItemStack(Init.TRIDENT));
    }
    
    @Override
    public ModIds getModId()
    {
        return ModIds.FUTURE_MC;
    }
}
