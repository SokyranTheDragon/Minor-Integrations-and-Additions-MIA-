package com.github.sokyranthedragon.mia.integrations.futuremc;

import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.integrations.quark.IQuarkIntegration;
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
