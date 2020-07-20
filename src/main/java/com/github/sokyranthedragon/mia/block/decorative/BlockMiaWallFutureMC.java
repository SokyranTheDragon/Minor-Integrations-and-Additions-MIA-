package com.github.sokyranthedragon.mia.block.decorative;

import com.github.sokyranthedragon.mia.MiaCreativeTab;
import com.github.sokyranthedragon.mia.block.IAutoRegisterBlock;
import com.github.sokyranthedragon.mia.config.MiaConfig;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFenceGate;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class BlockMiaWallFutureMC extends Block implements IAutoRegisterBlock
{
    private static final PropertyBool UP = PropertyBool.create("up");
    private static final PropertyBool NORTH = PropertyBool.create("north");
    private static final PropertyBool EAST = PropertyBool.create("east");
    private static final PropertyBool SOUTH = PropertyBool.create("south");
    private static final PropertyBool WEST = PropertyBool.create("west");
    private static final AxisAlignedBB[] AABB_BY_INDEX;
    private static final AxisAlignedBB[] CLIP_AABB_BY_INDEX;
    private final IBlockState parent;
    
    public BlockMiaWallFutureMC(String variant, @Nullable CreativeTabs creativeTab, IBlockState parent, MapColor mapColor)
    {
        super(parent.getMaterial(), mapColor);
        this.parent = parent;
        
        if (MiaConfig.miaCreativeTab)
            setCreativeTab(MiaCreativeTab.INSTANCE);
        else if (creativeTab != null)
            setCreativeTab(creativeTab);
        
        setDefaultState(getDefaultState()
            .withProperty(UP, false)
            .withProperty(NORTH, false)
            .withProperty(EAST, false)
            .withProperty(SOUTH, false)
            .withProperty(WEST, false));
        
        setRegistryName(new ResourceLocation("mia", variant));
        setTranslationKey(variant);
    }
    
    static
    {
        AABB_BY_INDEX = new AxisAlignedBB[]
            {
                new AxisAlignedBB(0.25D, 0.0D, 0.25D, 0.75D, 1.0D, 0.75D),
                new AxisAlignedBB(0.25D, 0.0D, 0.25D, 0.75D, 1.0D, 1.0D),
                new AxisAlignedBB(0.0D, 0.0D, 0.25D, 0.75D, 1.0D, 0.75D),
                new AxisAlignedBB(0.0D, 0.0D, 0.25D, 0.75D, 1.0D, 1.0D),
                new AxisAlignedBB(0.25D, 0.0D, 0.0D, 0.75D, 1.0D, 0.75D),
                new AxisAlignedBB(0.3125D, 0.0D, 0.0D, 0.6875D, 0.875D, 1.0D),
                new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.75D, 1.0D, 0.75D),
                new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.75D, 1.0D, 1.0D),
                new AxisAlignedBB(0.25D, 0.0D, 0.25D, 1.0D, 1.0D, 0.75D),
                new AxisAlignedBB(0.25D, 0.0D, 0.25D, 1.0D, 1.0D, 1.0D),
                new AxisAlignedBB(0.0D, 0.0D, 0.3125D, 1.0D, 0.875D, 0.6875D),
                new AxisAlignedBB(0.0D, 0.0D, 0.25D, 1.0D, 1.0D, 1.0D),
                new AxisAlignedBB(0.25D, 0.0D, 0.0D, 1.0D, 1.0D, 0.75D),
                new AxisAlignedBB(0.25D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D),
                new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 0.75D),
                new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D)
            };
        
        CLIP_AABB_BY_INDEX = new AxisAlignedBB[AABB_BY_INDEX.length];
        for (int i = 0; i < AABB_BY_INDEX.length; i++)
            CLIP_AABB_BY_INDEX[i] = AABB_BY_INDEX[i].setMaxY(1.5d);
    }
    
    @Override
    public float getExplosionResistance(World world, BlockPos pos, @Nullable Entity exploder, Explosion explosion) {
        return this.parent.getBlock().getExplosionResistance(world, pos, exploder, explosion) * 5.0F / 3.0F;
    }
    
    @Override
    public float getBlockHardness(IBlockState blockState, World worldIn, BlockPos pos) {
        return this.parent.getBlockHardness(worldIn, pos);
    }
    
    @Override
    public SoundType getSoundType(IBlockState state, World world, BlockPos pos, @Nullable Entity entity) {
        return this.parent.getBlock().getSoundType(this.parent, world, pos, entity);
    }
    
    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return AABB_BY_INDEX[Companion.getAABBIndex(this.getActualState(state, source, pos))];
    }
    
    @Override
    public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, @Nullable Entity entityIn, boolean isActualState)
    {
        IBlockState actualState = state;
        if (!isActualState) {
            actualState = getActualState(state, worldIn, pos);
        }
        Block.addCollisionBoxToList(pos, entityBox, collidingBoxes, CLIP_AABB_BY_INDEX[Companion.getAABBIndex(actualState)]);
    }
    
    @Nullable
    @Override
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
    {
        return CLIP_AABB_BY_INDEX[Companion.getAABBIndex(getActualState(blockState, worldIn, pos))];
    }
    
    @Override
    public boolean isFullCube(IBlockState state)
    {
        return false;
    }
    
    @Override
    public boolean isPassable(IBlockAccess worldIn, BlockPos pos)
    {
        return false;
    }
    
    @Override
    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }
    
    private boolean canConnectTo(IBlockAccess worldIn, BlockPos pos, EnumFacing facing)
    {
        IBlockState state = worldIn.getBlockState(pos);
        Block block = state.getBlock();
        BlockFaceShape shape = state.getBlockFaceShape(worldIn, pos, facing);
        boolean flag = shape == BlockFaceShape.MIDDLE_POLE_THICK || shape == BlockFaceShape.MIDDLE_POLE && block instanceof BlockFenceGate;
        return !Companion.isExceptBlockForAttachWithPiston(block) && shape == BlockFaceShape.SOLID || flag;
    }
    
    @Override
    public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side)
    {
        return side != EnumFacing.DOWN || super.shouldSideBeRendered(blockState, blockAccess, pos, side);
    }
    
    @Override
    public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos)
    {
        boolean isNorth = this.canWallConnectTo(worldIn, pos, EnumFacing.NORTH);
        boolean isEast = this.canWallConnectTo(worldIn, pos, EnumFacing.EAST);
        boolean isSouth = this.canWallConnectTo(worldIn, pos, EnumFacing.SOUTH);
        boolean isWest = this.canWallConnectTo(worldIn, pos, EnumFacing.WEST);
        boolean isPole = isNorth && !isEast && isSouth && !isWest || !isNorth && isEast && !isSouth && isWest;
        return state
            .withProperty(UP, !isPole || !worldIn.isAirBlock(pos.up()))
            .withProperty(NORTH, isNorth)
            .withProperty(EAST, isEast)
            .withProperty(SOUTH, isSouth)
            .withProperty(WEST, isWest);
    }
    
    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, UP, NORTH, EAST, WEST, SOUTH);
    }
    
    @Override
    public int getMetaFromState(IBlockState state)
    {
        return 0;
    }
    
    @Override
    public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face)
    {
        return face != EnumFacing.UP && face != EnumFacing.DOWN ? BlockFaceShape.MIDDLE_POLE_THICK : BlockFaceShape.CENTER_BIG;
    }
    
    @Override
    public boolean canBeConnectedTo(IBlockAccess world, BlockPos pos, EnumFacing facing)
    {
        BlockPos offset = pos.offset(facing);
        EnumFacing opposite = facing.getOpposite();
        return canConnectTo(world, offset, opposite);
    }
    
    private boolean canWallConnectTo(IBlockAccess world, BlockPos pos, EnumFacing facing)
    {
        BlockPos other = pos.offset(facing);
        IBlockState otherState = world.getBlockState(other);
        Block block = otherState.getBlock();
        
        if (!block.canBeConnectedTo(world, other, facing.getOpposite()))
        {
            EnumFacing opposite = facing.getOpposite();
            if (!canConnectTo(world, other, opposite))
                return false;
        }
        
        return true;
    }
    
    private static class Companion
    {
        public static int getAABBIndex(IBlockState state)
        {
            int i = 0;
    
            if (state.getValue(NORTH))
                i |= 1 << EnumFacing.NORTH.getHorizontalIndex();
    
            if (state.getValue(EAST))
                i |= 1 << EnumFacing.EAST.getHorizontalIndex();
    
            if (state.getValue(SOUTH))
                i |= 1 << EnumFacing.SOUTH.getHorizontalIndex();
    
            if (state.getValue(WEST))
                i |= 1 << EnumFacing.WEST.getHorizontalIndex();
            
            return i;
        }
    
        public static boolean isExceptBlockForAttachWithPiston(Block block)
        {
            return Block.isExceptBlockForAttachWithPiston(block) ||
                Blocks.BARRIER.equals(block) ||
                Blocks.MELON_BLOCK.equals(block) ||
                Blocks.PUMPKIN.equals(block) ||
                Blocks.LIT_PUMPKIN.equals(block);
        }
    }
}
