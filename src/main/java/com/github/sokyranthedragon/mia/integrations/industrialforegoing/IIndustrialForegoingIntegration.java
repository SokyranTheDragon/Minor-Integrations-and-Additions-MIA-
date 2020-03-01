package com.github.sokyranthedragon.mia.integrations.industrialforegoing;

import com.github.sokyranthedragon.mia.integrations.base.IModIntegration;
import net.minecraft.item.ItemStack;
import org.apache.logging.log4j.util.TriConsumer;

public interface IIndustrialForegoingIntegration extends IModIntegration
{
    default void addGenericIntegrations()
    {
    }
    
    default void addFrosterRecipe(TriConsumer<String, ItemStack, Integer> frosterEntry)
    {
    }
}
