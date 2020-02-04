package com.github.sokyranthedragon.mia.block.decorative;

import com.github.sokyranthedragon.mia.block.BlockBaseStairs;
import com.github.sokyranthedragon.mia.utilities.RegisterUtils;
import com.github.sokyranthedragon.mia.utilities.annotations.FieldsAreNullableByDefault;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.material.MapColor;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;
import thedarkcolour.futuremc.init.FutureConfig;
import thedarkcolour.futuremc.recipe.StonecutterRecipes;
import vazkii.quark.base.block.BlockQuarkWall;
import vazkii.quark.building.feature.MoreSandstone;
import vazkii.quark.building.feature.VanillaWalls;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

import static com.github.sokyranthedragon.mia.config.GenericAdditionsConfig.moreSandstone;
import static com.github.sokyranthedragon.mia.core.MiaBlocks.registerBlock;
import static com.github.sokyranthedragon.mia.integrations.ModIds.FUTURE_MC;
import static com.github.sokyranthedragon.mia.integrations.ModIds.QUARK;
import static com.github.sokyranthedragon.mia.utilities.QuarkUtils.isFeatureEnabled;

@MethodsReturnNonnullByDefault
@FieldsAreNullableByDefault
public class SandstoneEntry implements IBlockEntry
{
    public Block sandstone;
    public BlockSlab slabs;
    public BlockSlab slabsDouble;
    public BlockStairs brickStairs;
    public Block wallQuark;
    
    public Block wallFutureMc;
    
    protected SandstoneEntry(Block sandstone, String sandName, CreativeTabs creativeTab, MapColor mapColor, IForgeRegistry<Block> registry, boolean enableQuarkWalls)
    {
        final String sandstoneName = sandName + "_sandstone_";
        
        if ((QUARK.isLoaded && isFeatureEnabled(MoreSandstone.class) && moreSandstone.moreSandstoneQuarkEnabled) || moreSandstone.forceMoreSandstone)
        {
            this.sandstone = registerBlock(new BlockNewSandstone(sandstoneName + "new", creativeTab, mapColor), registry);
            if ((QUARK.isLoaded && MoreSandstone.enableStairsAndSlabs) || moreSandstone.forceMoreSandstoneStairsAndSlabs)
            {
                BlockNewSandstoneSlab slabs = registerBlock(new BlockNewSandstoneSlab(sandstoneName + "new_slab", creativeTab, mapColor), registry);
                this.slabs = slabs;
                slabsDouble = registerBlock(new BlockNewSandstoneSlabDouble(sandstoneName + "new_slab_double", creativeTab, slabs, mapColor), registry);
                
                brickStairs = registerBlock(new BlockBaseStairs(sandstone.getDefaultState(), sandstoneName + "stairs_brick", creativeTab), registry);
            }
            if (QUARK.isLoaded && enableQuarkWalls && (isFeatureEnabled(VanillaWalls.class) || moreSandstone.forceMoreSandstoneQuarkWalls))
            {
                wallQuark = registerBlock(new BlockMiaWallQuark(sandstoneName + "wall_quark", sandstone.getDefaultState(), mapColor));
                BlockQuarkWall.initWall(sandstone, 0, wallQuark);
            }
        }
        
        if (FUTURE_MC.isLoaded && moreSandstone.sandstoneWallsFutureMcEnabled && (FutureConfig.general.newWallVariants || moreSandstone.forceMoreSandstoneFutureMcWalls))
            wallFutureMc = registerBlock(new BlockMiaWallFutureMC(sandstoneName + "futuremc"), registry);
    }
    
    @Override
    public boolean isEmpty()
    {
        return sandstone == null && slabs == null && slabsDouble != null && brickStairs == null && wallQuark == null && wallFutureMc == null;
    }
    
    @Nullable
    public static SandstoneEntry init(Block sandstone, String sandName, CreativeTabs creativeTab, MapColor mapColor, IForgeRegistry<Block> registry, boolean enableQuarkWalls)
    {
        SandstoneEntry entry = new SandstoneEntry(sandstone, sandName, creativeTab, mapColor, registry, enableQuarkWalls);
        if (entry.isEmpty())
            return null;
        return entry;
    }
    
    public static void registerItemBlocks(@Nullable SandstoneEntry sandstone, IForgeRegistry<Item> registry)
    {
        if (sandstone != null)
        {
            RegisterUtils.registerItemblock(sandstone.sandstone, registry);
            RegisterUtils.registerItemblockSlab(sandstone.slabs, sandstone.slabsDouble, registry);
            RegisterUtils.registerItemblock(sandstone.wallFutureMc, registry);
            RegisterUtils.registerItemblock(sandstone.brickStairs, registry);
        }
    }
    
    @SideOnly(Side.CLIENT)
    public static void registerRenders(@Nullable SandstoneEntry sandstone)
    {
        if (sandstone != null)
        {
            RegisterUtils.registerItemblockRenderer(sandstone.sandstone, 0);
            RegisterUtils.registerItemblockRenderer(sandstone.sandstone, 1);
            RegisterUtils.registerItemblockRenderer(sandstone.slabs, 0);
            RegisterUtils.registerItemblockRenderer(sandstone.slabs, 1);
            RegisterUtils.registerItemblockRenderer(sandstone.wallFutureMc);
            RegisterUtils.registerItemblockRenderer(sandstone.brickStairs);
        }
    }
    
    public static void registerRecipes(@Nullable SandstoneEntry sandstone, Block source)
    {
        if (sandstone != null)
        {
            List<ItemStack> toRegister = new ArrayList<>(7);
            addIfNotNull(toRegister, sandstone.sandstone, 0);
            addIfNotNull(toRegister, sandstone.sandstone, 1);
            addIfNotNull(toRegister, sandstone.slabs, 0);
            addIfNotNull(toRegister, sandstone.slabs, 1);
            addIfNotNull(toRegister, sandstone.brickStairs);
            addIfNotNull(toRegister, sandstone.wallQuark);
            addIfNotNull(toRegister, sandstone.wallFutureMc);
            
            
            if (toRegister.size() > 0)
                StonecutterRecipes.addOrCreateRecipe(new ItemStack(source), toRegister.toArray(new ItemStack[0]));
        }
    }
    
    private static void addIfNotNull(List<ItemStack> list, Block block)
    {
        addIfNotNull(list, block, 0);
    }
    
    private static void addIfNotNull(List<ItemStack> list, Block block, int meta)
    {
        if (block != null)
        {
            if (block instanceof BlockNewSandstoneSlab)
                list.add(new ItemStack(block, 2, meta));
            else
                list.add(new ItemStack(block, 1, meta));
        }
    }
}
