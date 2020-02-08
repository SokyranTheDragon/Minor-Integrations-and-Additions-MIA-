package com.github.sokyranthedragon.mia.block.base;

import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class BlockBaseFlower extends BlockBaseBush
{
    public BlockBaseFlower(String name)
    {
        this(name, CreativeTabs.DECORATIONS);
    }
    
    public BlockBaseFlower(String name, @Nullable CreativeTabs creativeTab)
    {
        super(name, creativeTab);
    }
    
    @SuppressWarnings("deprecation")
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return super.getBoundingBox(state, source, pos).offset(state.getOffset(source, pos));
    }
    
    @Override
    public EnumOffsetType getOffsetType()
    {
        return EnumOffsetType.XZ;
    }
}