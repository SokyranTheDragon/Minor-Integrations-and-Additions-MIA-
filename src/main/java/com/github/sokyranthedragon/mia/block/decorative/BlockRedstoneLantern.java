package com.github.sokyranthedragon.mia.block.decorative;

import com.github.sokyranthedragon.mia.block.IAutoRegisterBlock;
import com.github.sokyranthedragon.mia.block.IMetaBlock;
import com.github.sokyranthedragon.mia.block.base.BlockBase;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Random;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class BlockRedstoneLantern extends BlockBase implements IAutoRegisterBlock, IMetaBlock
{
    public static final PropertyBool MANUAL_STATE = PropertyBool.create("manual_state");
    public static final PropertyBool REDSTONE_STATE = PropertyBool.create("redstone_state");
    public static final PropertyDirection FACING = BlockHorizontal.FACING;
    
    public BlockRedstoneLantern()
    {
        super(Material.ROCK, "redstone_lantern", CreativeTabs.REDSTONE);
        setLightLevel(1.0f);
        setDefaultState(blockState.getBaseState().withProperty(REDSTONE_STATE, false).withProperty(MANUAL_STATE, false).withProperty(FACING, EnumFacing.NORTH));
        setHardness(3.0f);
        setResistance(5.0f);
    }
    
    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, MANUAL_STATE, REDSTONE_STATE, FACING);
    }
    
    @Override
    @SuppressWarnings("deprecation")
    public IBlockState getStateFromMeta(int meta)
    {
        return getDefaultState()
                .withProperty(MANUAL_STATE, (meta & 0b1000) != 0)
                .withProperty(REDSTONE_STATE, (meta & 0b0100) != 0)
                .withProperty(FACING, EnumFacing.byHorizontalIndex(meta & 0b0011));
    }
    
    @Override
    public int getMetaFromState(IBlockState state)
    {
        int meta = state.getValue(FACING).getHorizontalIndex();
        if (state.getValue(MANUAL_STATE))
            meta |= 0b1000;
        if (state.getValue(REDSTONE_STATE))
            meta |= 0b0100;
        return meta;
    }
    
//    @Override
//    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
//    {
//        worldIn.setBlockState(pos, state.withProperty(ROTATION, placer.getHorizontalFacing()), 2);
//    }
    
    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        if (!world.isRemote)
        {
            if (player.isSneaking())
                world.setBlockState(pos, state.cycleProperty(FACING), 2);
            else
            {
                world.setBlockState(pos, state.cycleProperty(MANUAL_STATE), 2);
                
                float pitch = state.getValue(MANUAL_STATE) ? 0.6f : 0.5f;
                world.playSound(null, pos, SoundEvents.BLOCK_LEVER_CLICK, SoundCategory.BLOCKS, 0.3f, pitch);
            }
        }
        
        return true;
    }
    
    @Override
    public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand)
    {
        return getDefaultState().withProperty(FACING, placer.getHorizontalFacing());
    }
    
    @Override
    public void onBlockAdded(World world, BlockPos pos, IBlockState state)
    {
        updateBlockPowerState(world, pos, state);
    }
    
    @Override
    @SuppressWarnings("deprecation")
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos)
    {
        if (worldIn.isBlockPowered(pos))
            updateBlockPowerState(worldIn, pos, state);
        else
            worldIn.scheduleUpdate(pos, this, 4);
    }
    
    @Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
        updateBlockPowerState(worldIn, pos, state);
    }
    
    @Override
    public int getLightValue(IBlockState state, IBlockAccess world, BlockPos pos)
    {
        boolean redstoneState = state.getValue(REDSTONE_STATE);
        boolean manualState = state.getValue(MANUAL_STATE);
        
        if (redstoneState ^ manualState)
            //noinspection deprecation
            return state.getLightValue();
        return 0;
    }
    
    protected void updateBlockPowerState(World world, BlockPos pos, IBlockState state)
    {
        if (!world.isRemote)
        {
            boolean redstoneState = state.getValue(REDSTONE_STATE);
            boolean powered = world.isBlockPowered(pos);
            
            if (powered ^ redstoneState)
                world.setBlockState(pos, state.cycleProperty(REDSTONE_STATE), 2);
        }
    }
    
    @Override
    @SuppressWarnings("deprecation")
    public IBlockState withRotation(IBlockState state, Rotation rot)
    {
        return state.withProperty(FACING, rot.rotate(state.getValue(FACING)));
    }
    
    @Override
    @SuppressWarnings("deprecation")
    public IBlockState withMirror(IBlockState state, Mirror mirrorIn)
    {
        return state.withRotation(mirrorIn.toRotation(state.getValue(FACING)));
    }
    
    @Override
    public int damageDropped(IBlockState state)
    {
        return 0;
    }
    
    @Override
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player)
    {
        return new ItemStack(this, 1, 0);
    }
    
    @Override
    protected ItemStack getSilkTouchDrop(IBlockState state)
    {
        return new ItemStack(this, 1, 0);
    }
    
    @Override
    public int getMaxMeta()
    {
        return 0;
    }
    
    @Override
    public String getNameFromMeta(int i)
    {
        return "";
    }
    
    @Nullable
    @Override
    public String getVariantName()
    {
        return null;
    }
    
    @Nullable
    @Override
    public String getDefaultVariantValue()
    {
        return "facing=north,manual_state=false,redstone_state=false";
    }
}
