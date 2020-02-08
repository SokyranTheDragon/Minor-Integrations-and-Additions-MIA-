package com.github.sokyranthedragon.mia.block.decorative;

import com.github.sokyranthedragon.mia.MiaCreativeTab;
import com.github.sokyranthedragon.mia.block.IAutoRegisterBlock;
import com.github.sokyranthedragon.mia.config.MiaConfig;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import thedarkcolour.futuremc.block.BlockWall;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class BlockMiaWallFutureMC extends BlockWall implements IAutoRegisterBlock
{
    protected final MapColor mapColor;
    
    public BlockMiaWallFutureMC(String variant, @Nullable CreativeTabs creativeTab, MapColor mapColor)
    {
        super(variant);
        
        this.mapColor = mapColor;
        if (MiaConfig.miaCreativeTab)
            setCreativeTab(MiaCreativeTab.INSTANCE);
        else if (creativeTab != null)
            setCreativeTab(creativeTab);
    }
    
    @Override
    public Block setTranslationKey(String key)
    {
        return super.setTranslationKey(key.substring("minecraftfuture.".length()));
    }
    
    @Override
    @SuppressWarnings("deprecation")
    public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos)
    {
        return super.getMapColor(state, worldIn, pos);
    }
}
