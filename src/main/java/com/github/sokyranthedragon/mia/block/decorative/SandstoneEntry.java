package com.github.sokyranthedragon.mia.block.decorative;

import com.github.sokyranthedragon.mia.block.base.BlockBaseStairs;
import com.github.sokyranthedragon.mia.integrations.ModIds;
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
import net.minecraftforge.fml.common.Optional.Method;
import net.minecraftforge.registries.IForgeRegistry;
import thedarkcolour.futuremc.init.FutureConfig;
import thedarkcolour.futuremc.recipe.StonecutterRecipes;
import vazkii.quark.base.block.BlockQuarkWall;
import vazkii.quark.building.feature.MoreSandstone;
import vazkii.quark.building.feature.VanillaWalls;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collection;
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
    public BlockSlab slab;
    public BlockSlab slabDouble;
    public BlockStairs brickStairs;
    public Block wallQuark;
    
    public Block wallFutureMc;
    
    // For overriding with no default values created
    @SuppressWarnings("unused")
    protected SandstoneEntry()
    {
    }
    
    protected SandstoneEntry(Block sandstone, String sandName, CreativeTabs creativeTab, MapColor mapColor, boolean enableQuarkWalls)
    {
        final String sandstoneName = sandName + "_sandstone_";
        
        if ((QUARK.isLoaded && isFeatureEnabled(MoreSandstone.class) && moreSandstone.moreSandstoneQuarkEnabled) || moreSandstone.forceMoreSandstone)
        {
            this.sandstone = registerBlock(new BlockNewSandstone(sandstoneName + "new", creativeTab, mapColor));
            if ((QUARK.isLoaded && MoreSandstone.enableStairsAndSlabs) || moreSandstone.forceMoreSandstoneStairsAndSlabs)
            {
                BlockNewSandstoneSlab slabs = registerBlock(new BlockNewSandstoneSlab(sandstoneName + "new_slab", creativeTab, mapColor));
                this.slab = slabs;
                slabDouble = registerBlock(new BlockNewSandstoneSlabDouble(sandstoneName + "new_slab_double", creativeTab, slabs, mapColor));
                
                brickStairs = registerBlock(new BlockBaseStairs(sandstone.getDefaultState(), sandstoneName + "stairs_brick", creativeTab));
            }
            if (QUARK.isLoaded && enableQuarkWalls && (isFeatureEnabled(VanillaWalls.class) || moreSandstone.forceMoreSandstoneQuarkWalls))
            {
                wallQuark = registerBlock(getQuarkWall(sandstone, sandstoneName, creativeTab, mapColor));
                BlockQuarkWall.initWall(sandstone, 0, wallQuark);
            }
        }
        
        if (FUTURE_MC.isLoaded && moreSandstone.sandstoneWallsFutureMcEnabled && (FutureConfig.general.newWallVariants || moreSandstone.forceMoreSandstoneFutureMcWalls))
            wallFutureMc = registerBlock(getFutureMcWall(sandstoneName, creativeTab, mapColor));
    }
    
    @Method(modid = ModIds.ConstantIds.QUARK)
    private static Block getQuarkWall(Block sandstone, String sandstoneName, CreativeTabs creativeTab, MapColor mapColor)
    {
        return new BlockMiaWallQuark(sandstoneName + "wall_quark", creativeTab, sandstone.getDefaultState(), mapColor);
    }
    
    @Method(modid = ModIds.ConstantIds.FUTURE_MC)
    private static Block getFutureMcWall(String sandstoneName, CreativeTabs creativeTab, MapColor mapColor)
    {
        return new BlockMiaWallFutureMC(sandstoneName + "futuremc", creativeTab, mapColor);
    }
    
    @Override
    public boolean isEmpty()
    {
        return sandstone == null && slab == null && slabDouble != null && brickStairs == null && wallQuark == null && wallFutureMc == null;
    }
    
    @Nullable
    public static SandstoneEntry init(Block sandstone, String sandName, CreativeTabs creativeTab, MapColor mapColor, boolean enableQuarkWalls)
    {
        SandstoneEntry entry = new SandstoneEntry(sandstone, sandName, creativeTab, mapColor, enableQuarkWalls);
        if (entry.isEmpty())
            return null;
        return entry;
    }
    
    public static void registerItemblocks(@Nullable SandstoneEntry sandstone, IForgeRegistry<Item> registry)
    {
        if (sandstone != null)
            RegisterUtils.registerItemblockSlab(sandstone.slab, sandstone.slabDouble, registry);
    }
    
    public static void registerRecipes(@Nullable SandstoneEntry sandstone, Block source)
    {
        if (sandstone != null && FUTURE_MC.isLoaded)
        {
            List<ItemStack> toRegister = new ArrayList<>(7);
            addIfNotNull(toRegister, sandstone.sandstone, 0);
            addIfNotNull(toRegister, sandstone.sandstone, 1);
            addIfNotNull(toRegister, sandstone.slab, 0);
            addIfNotNull(toRegister, sandstone.slab, 1);
            addIfNotNull(toRegister, sandstone.brickStairs);
            addIfNotNull(toRegister, sandstone.wallQuark);
            addIfNotNull(toRegister, sandstone.wallFutureMc);
            
            
            if (toRegister.size() > 0)
                registerStonecutter(source, toRegister);
        }
    }
    
    @Method(modid = ModIds.ConstantIds.FUTURE_MC)
    private static void registerStonecutter(Block source, Collection<ItemStack> toRegister)
    {
        StonecutterRecipes.addOrCreateRecipe(new ItemStack(source), toRegister.toArray(new ItemStack[0]));
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
