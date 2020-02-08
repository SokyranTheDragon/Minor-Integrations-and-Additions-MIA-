package com.github.sokyranthedragon.mia.block.decorative;

import com.github.sokyranthedragon.mia.block.base.BlockBaseMeta;
import com.github.sokyranthedragon.mia.block.base.BlockBaseMetaTorch;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.creativetab.CreativeTabs;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class BlockGoldenTorch extends BlockBaseMetaTorch<PropertyInteger, Integer>
{
    public static final PropertyInteger VARIANT = PropertyInteger.create(BlockBaseMeta.DEFAULT_VARIANT_NAME, 0, 1);
    
    public BlockGoldenTorch()
    {
        super("torch_gold", CreativeTabs.DECORATIONS);
        setSoundType(SoundType.METAL);
        setLightLevel(1f);
    }
    
    @Override
    protected PropertyInteger getProperty()
    {
        return VARIANT;
    }
    
    @Override
    protected Integer getValue(int meta)
    {
        return meta;
    }
    
    @Override
    protected int getMeta(Integer value)
    {
        return value;
    }
    
    @Override
    public int getMaxMeta()
    {
        return 1;
    }
    
    @Override
    public String getNameFromMeta(int i)
    {
        return Integer.toString(i);
    }
}
