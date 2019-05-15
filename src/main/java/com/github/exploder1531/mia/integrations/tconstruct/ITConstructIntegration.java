package com.github.exploder1531.mia.integrations.tconstruct;

import com.github.exploder1531.mia.integrations.base.IModIntegration;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

public interface ITConstructIntegration extends IModIntegration
{
    void init(FMLInitializationEvent event);
}
