package com.github.sokyranthedragon.mia.utilities;

import com.github.sokyranthedragon.mia.integrations.jer.ResourceLocationWrapper;

public class LootTableUtils
{
    private LootTableUtils()
    {
    }
    
    private static int emptyTableId = 0;
    
    public static ResourceLocationWrapper loadUniqueEmptyLootTable()
    {
        return new ResourceLocationWrapper("mia", "empty", emptyTableId++);
    }
}
