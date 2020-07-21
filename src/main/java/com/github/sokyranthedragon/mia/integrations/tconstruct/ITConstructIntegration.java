package com.github.sokyranthedragon.mia.integrations.tconstruct;

import com.github.sokyranthedragon.mia.integrations.base.IModIntegration;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

public interface ITConstructIntegration extends IModIntegration
{
    default void init(FMLInitializationEvent event)
    {
    }
    
    default String[] registerBookPages()
    {
        return new String[0];
    }
}
