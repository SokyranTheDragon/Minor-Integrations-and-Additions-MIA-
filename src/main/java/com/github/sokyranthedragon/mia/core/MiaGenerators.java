package com.github.sokyranthedragon.mia.core;

import com.github.sokyranthedragon.mia.config.GenericAdditionsConfig;
import com.github.sokyranthedragon.mia.world.WorldGenDeadFlower;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class MiaGenerators
{
    public static void postInit()
    {
        if (GenericAdditionsConfig.enableEvtp && GenericAdditionsConfig.evtp.deadFlowerEnabled)
        {
            WorldGenDeadFlower.INSTANCE = new WorldGenDeadFlower();
            GameRegistry.registerWorldGenerator(WorldGenDeadFlower.INSTANCE, 0);
        }
    }
}
