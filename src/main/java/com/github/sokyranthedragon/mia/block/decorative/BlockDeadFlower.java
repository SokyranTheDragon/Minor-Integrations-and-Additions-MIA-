package com.github.sokyranthedragon.mia.block.decorative;

import com.github.sokyranthedragon.mia.block.base.BlockBaseFlower;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.EnumPlantType;

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
}
