package com.github.sokyranthedragon.mia.integrations.theoneprobe;

import com.github.sokyranthedragon.mia.integrations.base.IBaseMod;
import net.minecraftforge.fml.common.event.FMLInterModComms;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class TheOneProbe implements IBaseMod
{
    @Override
    public void preInit(FMLPreInitializationEvent event)
    {
        FMLInterModComms.sendFunctionMessage("theoneprobe", "getTheOneProbe", "GetTheOneProbe");
    }
}
