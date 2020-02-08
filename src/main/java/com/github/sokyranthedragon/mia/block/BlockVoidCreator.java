package com.github.sokyranthedragon.mia.block;

import com.github.sokyranthedragon.mia.Mia;
import com.github.sokyranthedragon.mia.block.base.BlockBase;
import com.github.sokyranthedragon.mia.gui.GuiHandler;
import com.github.sokyranthedragon.mia.integrations.botania.wiki.IWikiProvider;
import com.github.sokyranthedragon.mia.tile.TileVoidCreator;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import thaumcraft.common.config.ConfigItems;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

@SuppressWarnings({"deprecation", "WeakerAccess"})
public class BlockVoidCreator extends BlockBase implements ITileEntityProvider, IWikiProvider
{
    protected static final AxisAlignedBB AABB_MAIN = new AxisAlignedBB(0.1875D, 0.0D, 0.1875D, 0.8125D, 1.0D, 0.8125D);
    protected static final AxisAlignedBB AABB_BASE = new AxisAlignedBB(0.1875D, 0.0D, 0.1875D, 0.8125D, 0.125D, 0.8125D);
    protected static final AxisAlignedBB AABB_TOP = new AxisAlignedBB(0.25D, 0.125D, 0.25D, 0.75D, 0.6875D, 0.75D);
    protected static final AxisAlignedBB AABB_ORB = new AxisAlignedBB(0.3125D, 0.75D, 0.3125D, 0.625D, 1.0D, 0.625D);
    
    public BlockVoidCreator()
    {
        super(Material.IRON, "void_creator", ConfigItems.TABTC);
        setHardness(2.5f);
        setHarvestLevel("pickaxe", 0);
    }
    
    @Nullable
    @Override
    public TileEntity createNewTileEntity(@Nonnull World worldIn, int meta)
    {
        return new TileVoidCreator();
    }
    
    @Override
    public boolean hasTileEntity(IBlockState state)
    {
        return true;
    }
    
    @Override
    public void breakBlock(@Nonnull World worldIn, @Nonnull BlockPos pos, @Nonnull IBlockState state)
    {
        TileEntity tile = worldIn.getTileEntity(pos);
        
        if (tile instanceof IInventory)
            InventoryHelper.dropInventoryItems(worldIn, pos, ((IInventory) tile));
        
        super.breakBlock(worldIn, pos, state);
        worldIn.removeTileEntity(pos);
    }
    
    @Override
    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }
    
    @Override
    public boolean isFullCube(IBlockState state)
    {
        return false;
    }
    
    @Nonnull
    @Override
    public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face)
    {
        return BlockFaceShape.UNDEFINED;
    }
    
    @Override
    public boolean isSideSolid(IBlockState state, @Nonnull IBlockAccess world, @Nonnull BlockPos pos, EnumFacing side)
    {
        return false;
    }
    
    @Override
    public void addCollisionBoxToList(IBlockState state, @Nonnull World worldIn, @Nonnull BlockPos pos, @Nonnull AxisAlignedBB AABB, @Nonnull List<AxisAlignedBB> list, Entity entity, boolean isActualState)
    {
        addCollisionBoxToList(pos, AABB, list, AABB_BASE);
        addCollisionBoxToList(pos, AABB, list, AABB_TOP);
        addCollisionBoxToList(pos, AABB, list, AABB_ORB);
    }
    
    @Nonnull
    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return AABB_MAIN;
    }
    
    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ)
    {
        if (!world.isRemote)
            player.openGui(Mia.instance, GuiHandler.VOID_CREATOR, world, pos.getX(), pos.getY(), pos.getZ());
        
        return true;
    }
}
