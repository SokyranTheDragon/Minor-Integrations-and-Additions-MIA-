package com.github.sokyranthedragon.mia.block.decorative;

import com.github.sokyranthedragon.mia.block.BlockBase;
import com.github.sokyranthedragon.mia.block.IMetaBlock;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class BlockNewSandstone extends BlockBase implements IMetaBlock
{
    public static final PropertyEnum<EnumType> VARIANT = PropertyEnum.create("variant", EnumType.class);
    private final MapColor mapColor;
    
    public BlockNewSandstone(String name, @Nullable CreativeTabs creativeTab, MapColor mapColor)
    {
        super(Material.ROCK, name, creativeTab);
        setHardness(0.8F);
        setSoundType(SoundType.STONE);
        setDefaultState(blockState.getBaseState().withProperty(VARIANT, EnumType.SMOOTH));
        this.mapColor = mapColor;
    }
    
    @Override
    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items)
    {
        for (EnumType variant : EnumType.values())
            items.add(new ItemStack(this, 1, variant.ordinal()));
    }
    
    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, VARIANT);
    }
    
    @Override
    @SuppressWarnings("deprecation")
    public MapColor getMapColor(IBlockState state, IBlockAccess world, BlockPos pos)
    {
        return mapColor;
    }
    
    @Override
    @SuppressWarnings("deprecation")
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(VARIANT, EnumType.values()[meta]);
    }
    
    @Override
    public int getMetaFromState(IBlockState state)
    {
        return state.getValue(VARIANT).ordinal();
    }
    
    @Override
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player)
    {
        return new ItemStack(this, 1, this.getMetaFromState(state));
    }
    
    @Override
    public int damageDropped(IBlockState state)
    {
        return getMetaFromState(state);
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
        return VARIANT.getName();
    }
    
    public enum EnumType implements IStringSerializable
    {
        SMOOTH,
        BRICK;
        
        @Override
        public String getName()
        {
            return this.name().toLowerCase();
        }
        
        @Override
        public String toString()
        {
            return this.getName();
        }
    }
}