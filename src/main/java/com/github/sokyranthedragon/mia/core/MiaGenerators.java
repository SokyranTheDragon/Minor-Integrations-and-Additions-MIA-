package com.github.sokyranthedragon.mia.core;

import com.github.sokyranthedragon.mia.world.WorldGenDeadFlower;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class MiaGenerators
{
    public static void postInit()
    {
        WorldGenDeadFlower.INSTANCE = new WorldGenDeadFlower();
        GameRegistry.registerWorldGenerator(WorldGenDeadFlower.INSTANCE, 0);
    }
}
