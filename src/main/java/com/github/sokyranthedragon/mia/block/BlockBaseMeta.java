package com.github.sokyranthedragon.mia.block;

import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public abstract class BlockBaseMeta<T extends IProperty<V>, V extends Comparable<V>> extends BlockBase implements IMetaBlock, IAutoRegisterBlock
{
    public static final String DEFAULT_VARIANT_NAME = "variant";
    
    public BlockBaseMeta(Material material, String name, @Nullable CreativeTabs creativeTab)
    {
        super(material, name, creativeTab);
        setDefaultState(blockState.getBaseState().withProperty(getProperty(), getValue(0)));
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
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, getProperty());
    }
    
    @Override
    @SuppressWarnings("deprecation")
    public IBlockState getStateFromMeta(int meta)
    {
        return getDefaultState().withProperty(getProperty(), getValue(meta));
    }
    
    @Override
    public int getMetaFromState(IBlockState state)
    {
        return getMeta(state.getValue(getProperty()));
    }
    
    @Override
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player)
    {
        return new ItemStack(this, 1, getMetaFromState(state));
    }
    
    @Override
    public int damageDropped(IBlockState state)
    {
        return getMetaFromState(state);
    }
}
