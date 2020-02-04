package com.github.sokyranthedragon.mia.block;

import com.github.sokyranthedragon.mia.Mia;
import com.github.sokyranthedragon.mia.MiaCreativeTab;
import com.github.sokyranthedragon.mia.config.MiaConfig;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Locale;
import java.util.Random;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class BlockBaseSlab extends BlockSlab
{
    protected static final PropertyEnum<SingletonEnum> SINGLETON = PropertyEnum.create("singleton", SingletonEnum.class);
    protected final BlockBaseSlab single;
    
    public BlockBaseSlab(Material material, @Nonnull String name, @Nullable CreativeTabs creativeTab)
    {
        this(material, name, creativeTab, null);
    }
    
    public BlockBaseSlab(Material material, @Nonnull String name, @Nullable CreativeTabs creativeTab, @Nullable BlockBaseSlab single)
    {
        super(material);
        
        this.single = single;
        setTranslationKey(name);
        setRegistryName(Mia.MODID, name);
        setDefaultState();
        if(!isDouble())
            useNeighborBrightness = true;
        
        if (MiaConfig.miaCreativeTab)
            setCreativeTab(MiaCreativeTab.INSTANCE);
        else if (creativeTab != null)
            setCreativeTab(creativeTab);
    }
    
    protected void setDefaultState()
    {
        if (isDouble())
            setDefaultState(blockState.getBaseState().withProperty(SINGLETON, SingletonEnum.SINGLETON));
        else
            setDefaultState(blockState.getBaseState().withProperty(HALF, EnumBlockHalf.BOTTOM).withProperty(SINGLETON, SingletonEnum.SINGLETON));
    }
    
    @Override
    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items)
    {
        if (!isDouble())
            super.getSubBlocks(itemIn, items);
    }
    
    @Override
    protected BlockStateContainer createBlockState()
    {
        if (isDouble())
            return new BlockStateContainer(this, getVariantProperty());
        else
            return new BlockStateContainer(this, HALF, getVariantProperty());
    }
    
    @Override
    @SuppressWarnings("deprecation")
    public IBlockState getStateFromMeta(int meta)
    {
        return isDouble() ? getDefaultState() : getDefaultState().withProperty(HALF, meta >= 8 ? EnumBlockHalf.TOP : EnumBlockHalf.BOTTOM);
    }
    
    @Override
    public int getMetaFromState(IBlockState state)
    {
        if (isDouble() || state.getValue(HALF) == EnumBlockHalf.BOTTOM)
            return 0;
        else
            return 8;
    }
    
    public int getMetaFromStateBottom(IBlockState state)
    {
        return getMetaFromState(state) & 0b0111;
    }
    
    @Override
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player)
    {
        return new ItemStack(this, 1, getMetaFromStateBottom(state));
    }
    
    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return Item.getItemFromBlock(isDouble() ? single : this);
    }
    
    @Override
    public int damageDropped(IBlockState state)
    {
        return getMetaFromStateBottom(state);
    }
    
    @Override
    public String getTranslationKey(int meta)
    {
        return getTranslationKey();
    }
    
    @Override
    public boolean isDouble()
    {
        return false;
    }
    
    @Override
    public IProperty<?> getVariantProperty()
    {
        return SINGLETON;
    }
    
    @Override
    public Comparable<?> getTypeForItem(ItemStack itemStack)
    {
        return SingletonEnum.SINGLETON;
    }
    
    private enum SingletonEnum implements IStringSerializable
    {
        SINGLETON
                {
                    @Nonnull
                    public String getName()
                    {
                        return this.name().toLowerCase(Locale.ROOT);
                    }
                }
    }
}
