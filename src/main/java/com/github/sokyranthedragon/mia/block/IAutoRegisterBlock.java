package com.github.sokyranthedragon.mia.block;

public interface IAutoRegisterBlock
{
    default boolean registerBlock()
    {
        return true;
    }
    
    default boolean registerItemblock()
    {
        return true;
    }
    
    default boolean registerItemblockRenderer()
    {
        return true;
    }
}
