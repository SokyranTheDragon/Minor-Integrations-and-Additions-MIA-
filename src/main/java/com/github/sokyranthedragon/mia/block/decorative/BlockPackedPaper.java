package com.github.sokyranthedragon.mia.block.decorative;

import com.github.sokyranthedragon.mia.block.BlockBaseMeta;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class BlockPackedPaper extends BlockBaseMeta<PropertyInteger, Integer>
{
    public static final PropertyInteger VARIANT = PropertyInteger.create(BlockBaseMeta.DEFAULT_VARIANT_NAME, 0, 3);
    
    public BlockPackedPaper()
    {
        super(Material.CLOTH, "packed_paper", CreativeTabs.BUILDING_BLOCKS);
        setSoundType(SoundType.CLOTH);
        setHardness(0.8f);
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
    public IBlockState getStateFromMeta(int meta)
    {
        return getDefaultState().withProperty(VARIANT, meta);
    }
    
    @Override
    public int getMetaFromState(IBlockState state)
    {
        return state.getValue(VARIANT);
    }
    
    @Override
    public int getMaxMeta()
    {
        return 3;
    }
    
    @Override
    public String getNameFromMeta(int i)
    {
        return Integer.toString(i);
    }
}
