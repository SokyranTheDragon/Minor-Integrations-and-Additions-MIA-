package com.github.sokyranthedragon.mia.block.decorative;

import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.material.MapColor;
import net.minecraft.creativetab.CreativeTabs;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
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

//    @Override
//    public String getTranslationKey()
//    {
//        return getTranslationKey(0);
//    }
//
//    @Override
//    public String getTranslationKey(int meta)
//    {
//        return super.getTranslationKey() + "_" + getNameFromMeta(meta);
//    }
}
