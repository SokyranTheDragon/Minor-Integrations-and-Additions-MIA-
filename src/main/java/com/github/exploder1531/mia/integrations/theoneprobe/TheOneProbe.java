package com.github.exploder1531.mia.integrations.theoneprobe;

import com.github.exploder1531.mia.integrations.base.IBaseMod;
import net.minecraftforge.fml.common.event.FMLInterModComms;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class TheOneProbe implements IBaseMod
{
    @Override
    public void preInit(FMLPreInitializationEvent event)
    {
        FMLInterModComms.sendFunctionMessage("theoneprobe", "getTheOneProbe", "com.github.exploder1531.mia.integrations.theoneprobe.GetTheOneProbe");
    }
}
