package com.github.sokyranthedragon.mia.integrations.futuremc;

import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.integrations.industrialforegoing.IIndustrialForegoingIntegration;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.item.ItemStack;
import org.apache.logging.log4j.util.TriConsumer;
import thedarkcolour.futuremc.init.Init;

import javax.annotation.ParametersAreNonnullByDefault;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
class IndustrialForegoingFutureMcIntegration implements IIndustrialForegoingIntegration
{
    @Override
    public void addFrosterRecipe(TriConsumer<String, ItemStack, Integer> frosterEntry)
    {
        frosterEntry.accept("BLUE_ICE", new ItemStack(Init.BLUE_ICE), 8000);
    }
    
    @Override
    public ModIds getModId()
    {
        return ModIds.FUTURE_MC;
    }
}
