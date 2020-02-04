package com.github.sokyranthedragon.mia.block.decorative;

import com.github.sokyranthedragon.mia.Mia;
import com.github.sokyranthedragon.mia.MiaCreativeTab;
import com.github.sokyranthedragon.mia.config.MiaConfig;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import vazkii.quark.base.block.BlockQuarkWall;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class BlockMiaWallQuark extends BlockQuarkWall
{
    private MapColor mapColor;
    
    public BlockMiaWallQuark(String name, IBlockState state, @Nullable MapColor mapColor)
    {
        super(name, state);
        if (MiaConfig.miaCreativeTab)
            setCreativeTab(MiaCreativeTab.INSTANCE);
        this.mapColor = mapColor;
    }
    
    @Override
    public String getModNamespace()
    {
        return Mia.MODID;
    }
    
    @Override
    @SuppressWarnings("deprecation")
    public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos)
    {
        return mapColor;
    }
}
