package com.github.sokyranthedragon.mia.block.base;

import com.github.sokyranthedragon.mia.Mia;
import com.github.sokyranthedragon.mia.MiaCreativeTab;
import com.github.sokyranthedragon.mia.block.IAutoRegisterBlock;
import com.github.sokyranthedragon.mia.block.IMetaBlock;
import com.github.sokyranthedragon.mia.config.MiaConfig;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.BlockTorch;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public abstract class BlockBaseMetaTorch<T extends IProperty<V>, V extends Comparable<V>> extends BlockTorch implements IMetaBlock, IAutoRegisterBlock
{
    public BlockBaseMetaTorch(String name, @Nullable CreativeTabs creativeTab)
    {
        super();
        
        setHardness(0.0F);
        setLightLevel(0.9375F);
        setTranslationKey(name);
        setRegistryName(Mia.MODID, name);
        
        if (MiaConfig.miaCreativeTab)
            setCreativeTab(MiaCreativeTab.INSTANCE);
        else if (creativeTab != null)
            setCreativeTab(creativeTab);
        
        setDefaultState(blockState.getBaseState().withProperty(FACING, EnumFacing.UP).withProperty(getProperty(), getValue(0)));
    }
    
    protected abstract T getProperty();
    
    protected abstract V getValue(int meta);
    
    protected abstract int getMeta(V value);
    
    @Override
    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items)
    {
        for (int i = 0; i <= getMaxMeta(); i++)
            items.add(new ItemStack(this, 1, i));
    }
    
    @Override
    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
        return super.getStateForPlacement(worldIn, pos, facing, hitX, hitY, hitZ, meta, placer).withProperty(getProperty(), getValue(meta));
    }
    
    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, FACING, getProperty());
    }
    
    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        int typeMeta;
        int torchStateMeta;
        
        if (meta % 2 == 0)
        {
            typeMeta = 0;
            torchStateMeta = meta / 2;
        }
        else
        {
            typeMeta = 1;
            torchStateMeta = (meta - 1) / 2;
        }
        
        return super.getStateFromMeta(torchStateMeta).withProperty(getProperty(), getValue(typeMeta));
    }
    
    @Override
    public int getMetaFromState(IBlockState state)
    {
        int meta = super.getMetaFromState(state);
        
        if (getMeta(state.getValue(getProperty())) == 0)
            meta = meta * 2;
        else
            meta = (meta * 2) + 1;
        
        return meta;
    }
    
    @Override
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player)
    {
        return new ItemStack(this, 1, getMetaFromState(state) % 2);
    }
    
    @Override
    public int damageDropped(IBlockState state)
    {
        return getMetaFromState(state) % 2;
    }
    
    @Override
    public String getVariantName()
    {
        return null;
    }
}
