package com.github.sokyranthedragon.mia.integrations.tconstruct;

import com.github.sokyranthedragon.mia.integrations.base.IModIntegration;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

public interface ITConstructIntegration extends IModIntegration
{
    void init(FMLInitializationEvent event);
}
