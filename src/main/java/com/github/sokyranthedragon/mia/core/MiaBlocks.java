package com.github.sokyranthedragon.mia.core;

import net.minecraft.block.Block;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.LinkedList;
import java.util.List;

public class MiaBlocks
{
    private MiaBlocks()
    {
    }
    
    public static final List<Block> blocks = new LinkedList<>();
    
    // Hatchery
    public static Block eggSorter = null;
    
    // Ice and Fire
    public static Block pixieDustExtractor = null;
    
    // Thaumcraft
    public static Block voidCreator = null;
    
    // Botania
    public static Block blockBotaniaSpecialFlower = null;
    
    public static <T extends Block> T registerBlock(T block, IForgeRegistry<Block> registry)
    {
        registry.register(block);
        blocks.add(block);
        return block;
    }
}
