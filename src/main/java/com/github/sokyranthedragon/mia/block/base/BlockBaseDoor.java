package com.github.sokyranthedragon.mia.block.base;

import com.github.sokyranthedragon.mia.Mia;
import com.github.sokyranthedragon.mia.MiaCreativeTab;
import com.github.sokyranthedragon.mia.block.IAutoRegisterBlock;
import com.github.sokyranthedragon.mia.block.IFurnaceFuel;
import com.github.sokyranthedragon.mia.config.MiaConfig;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Random;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class BlockBaseDoor extends BlockDoor implements IAutoRegisterBlock, IFurnaceFuel
{
    protected Item doors;
    protected int burnTime;
    
    public BlockBaseDoor(Material materialIn, String name, @Nullable CreativeTabs creativeTab)
    {
        this(materialIn, name, creativeTab, null);
    }
    
    public BlockBaseDoor(Material materialIn, String name, @Nullable CreativeTabs creativeTab, @Nullable SoundType soundType)
    {
        this(materialIn, name, creativeTab, soundType, 0);
    }
    
    public BlockBaseDoor(Material materialIn, String name, @Nullable CreativeTabs creativeTab, @Nullable SoundType soundType, int burnTime)
    {
        super(materialIn);
        
        disableStats();
        setTranslationKey(name);
        setRegistryName(Mia.MODID, name);
        this.burnTime = burnTime;
        if (soundType != null)
            setSoundType(SoundType.STONE);
        
        if (MiaConfig.miaCreativeTab)
            setCreativeTab(MiaCreativeTab.INSTANCE);
        else if (creativeTab != null)
            setCreativeTab(creativeTab);
    }
    
    public void setDoorsItem(Item doors)
    {
        this.doors = doors;
    }
    
    public Item getDoorsItem()
    {
        return doors;
    }
    
    @Override
    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items)
    {
    }
    
    @Override
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player)
    {
        return new ItemStack(doors);
    }
    
    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return state.getValue(HALF) == EnumDoorHalf.UPPER ? Items.AIR : doors;
    }
    
    @Override
    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state)
    {
        return new ItemStack(doors);
    }
    
    @Override
    public int damageDropped(IBlockState state)
    {
        return 0;
    }
    
    @Override
    @SuppressWarnings("deprecation")
    public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos)
    {
        return material.getMaterialMapColor();
    }
    
    @Override
    public int getBurnTime(ItemStack stack)
    {
        return burnTime;
    }
}
