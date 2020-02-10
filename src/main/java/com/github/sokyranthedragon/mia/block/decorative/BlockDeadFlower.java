package com.github.sokyranthedragon.mia.block.decorative;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.common.block.BlockBOPDirt;
import biomesoplenty.common.block.BlockBOPGrass;
import com.github.sokyranthedragon.mia.block.base.BlockBaseFlower;
import com.github.sokyranthedragon.mia.integrations.ModIds;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.fml.common.Optional;

import javax.annotation.ParametersAreNonnullByDefault;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class BlockDeadFlower extends BlockBaseFlower
{
    public BlockDeadFlower()
    {
        super("flower_dead");
    }
    
    @Override
    public EnumPlantType getPlantType(IBlockAccess world, BlockPos pos)
    {
        return EnumPlantType.Desert;
    }
    
    @Override
    protected boolean canSustainBush(IBlockState state)
    {
        if (state.getBlock() == Blocks.SAND || state.getBlock() == Blocks.HARDENED_CLAY || state.getBlock() == Blocks.STAINED_HARDENED_CLAY)
            return true;
        else
            return ModIds.BIOMES_O_PLENTY.isLoaded && isBopSand(state);
    }
    
    @Optional.Method(modid = ModIds.ConstantIds.BIOMES_O_PLENTY)
    private boolean isBopSand(IBlockState state)
    {
        return state.getBlock() == BOPBlocks.sand
                || state.getBlock() == BOPBlocks.white_sand
                || (state.getBlock() == BOPBlocks.dirt && state.getValue(BlockBOPDirt.VARIANT) == BlockBOPDirt.BOPDirtType.SANDY)
                || (state.getBlock() == BOPBlocks.grass && state.getValue(BlockBOPGrass.VARIANT) == BlockBOPGrass.BOPGrassType.SANDY);
    }
}
