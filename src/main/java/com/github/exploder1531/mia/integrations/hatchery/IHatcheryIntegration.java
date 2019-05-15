package com.github.exploder1531.mia.integrations.hatchery;

import com.gendeathrow.hatchery.core.config.ConfigLootHandler;
import com.github.exploder1531.mia.integrations.base.IModIntegration;
import com.google.common.collect.Lists;

import javax.annotation.Nonnull;
import java.util.List;

public interface IHatcheryIntegration extends IModIntegration
{
    default void registerShredder()
    {
    }
    
    default boolean isModEnabled()
    {
        return false;
    }
    
    default int getCurrentLootVersion()
    {
        return 0;
    }
    
    @Nonnull
    default List<ConfigLootHandler.ItemDrop> getDefaultEggDrops()
    {
        return Lists.newLinkedList();
    }
}
