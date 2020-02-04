package com.github.sokyranthedragon.mia.block.decorative;

import com.github.sokyranthedragon.mia.block.BlockBaseSlab;
import com.github.sokyranthedragon.mia.block.IMetaBlock;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import static com.github.sokyranthedragon.mia.block.decorative.BlockNewSandstone.EnumType;
import static com.github.sokyranthedragon.mia.block.decorative.BlockNewSandstone.VARIANT;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class BlockNewSandstoneSlab extends BlockBaseSlab implements IMetaBlock
{
    protected final MapColor mapColor;
    
    public BlockNewSandstoneSlab(String name, @Nullable CreativeTabs creativeTab, MapColor mapColor)
    {
        this(name, creativeTab, null, mapColor);
    }
    
    public BlockNewSandstoneSlab(String name, @Nullable CreativeTabs creativeTab, @Nullable BlockNewSandstoneSlab single, MapColor mapColor)
    {
        super(Material.ROCK, name, creativeTab, single);
        setHardness(0.8f);
        setSoundType(SoundType.STONE);
        this.mapColor = mapColor;
    }
    
    @Override
    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items)
    {
        if (isDouble())
            return;
        for (EnumType value : EnumType.values())
            items.add(new ItemStack(this, 1, value.ordinal()));
    }
    
    @Override
    protected void setDefaultState()
    {
        if (isDouble())
            setDefaultState(blockState.getBaseState().withProperty(VARIANT, EnumType.SMOOTH));
        else
            setDefaultState(blockState.getBaseState().withProperty(HALF, EnumBlockHalf.BOTTOM).withProperty(VARIANT, EnumType.SMOOTH));
    }
    
    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return super.getStateFromMeta(meta).withProperty(VARIANT, EnumType.values()[meta & 0b0111]);
    }
    
    @Override
    public int getMetaFromState(IBlockState state)
    {
        if (isDouble() || state.getValue(HALF) == EnumBlockHalf.BOTTOM)
            return state.getValue(VARIANT).ordinal();
        else
            return 8 + state.getValue(VARIANT).ordinal();
    }
    
    @Override
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player)
    {
        return new ItemStack(this, 1, this.getMetaFromStateBottom(state));
    }
    
    @Override
    public IProperty<?> getVariantProperty()
    {
        return VARIANT;
    }
    
    @Override
    public Comparable<?> getTypeForItem(ItemStack itemStack)
    {
        return EnumType.values()[itemStack.getMetadata() & 0b0111];
    }
    
    @Override
    public int damageDropped(IBlockState state)
    {
        return getMetaFromStateBottom(state);
    }
    
    @Override
    @SuppressWarnings("deprecation")
    public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos)
    {
        return mapColor;
    }
    
    @Override
    public int getMaxMeta()
    {
        return EnumType.values().length - 1;
    }
    
    @Override
    public String getNameFromMeta(int i)
    {
        return EnumType.values()[i].getName();
    }
    
    @Override
    public String getVariantName()
    {
        if (isDouble())
            return VARIANT.getName();
        else
            return HALF.getName() + "=" + EnumBlockHalf.BOTTOM.getName() + "," + VARIANT.getName();
    }
}