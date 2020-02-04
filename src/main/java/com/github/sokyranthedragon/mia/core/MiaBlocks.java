package com.github.sokyranthedragon.mia.core;

import com.github.sokyranthedragon.mia.block.decorative.SandstoneEntry;
import com.github.sokyranthedragon.mia.utilities.annotations.FieldsAreNullableByDefault;
import net.minecraft.block.Block;
import net.minecraftforge.registries.IForgeRegistry;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.LinkedList;
import java.util.List;

@FieldsAreNullableByDefault
public class MiaBlocks
{
    private MiaBlocks()
    {
    }
    
    @Nonnull
    public static final List<Block> blocks = new LinkedList<>();
    
    // Hatchery
    public static Block eggSorter = null;
    
    // Ice and Fire
    public static Block pixieDustExtractor = null;
    
    // Thaumcraft
    public static Block voidCreator = null;
    
    // Botania
    public static Block blockBotaniaSpecialFlower = null;
    
    // Biomes O' Plenty
    public static SandstoneEntry whiteSandstone = null;
    
    public static <T extends Block> T registerBlock(T block)
    {
        return registerBlock(block, null);
    }
    
    public static <T extends Block> T registerBlock(T block, @Nullable IForgeRegistry<Block> registry)
    {
        if (registry != null)
            registry.register(block);
        blocks.add(block);
        return block;
    }
}
