package com.github.sokyranthedragon.mia.block.decorative;

import net.minecraft.block.material.MapColor;
import net.minecraft.creativetab.CreativeTabs;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class BlockNewSandstoneSlabDouble extends BlockNewSandstoneSlab
{
    public BlockNewSandstoneSlabDouble(String name, @Nullable CreativeTabs creativeTab, BlockNewSandstoneSlab single, MapColor mapColor)
    {
        super(name, creativeTab, single, mapColor);
    }
    
    @Override
    public boolean isDouble()
    {
        return true;
    }
}
