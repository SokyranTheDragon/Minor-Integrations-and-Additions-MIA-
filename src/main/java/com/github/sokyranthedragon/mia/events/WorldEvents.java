package com.github.sokyranthedragon.mia.events;

import com.github.sokyranthedragon.mia.config.GenericAdditionsConfig;
import com.github.sokyranthedragon.mia.core.MiaBlocks;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import javax.annotation.Nullable;
import java.util.Set;

public class WorldEvents
{
    @SubscribeEvent
    public static void onWorldDecoration(DecorateBiomeEvent.Decorate event)
    {
        World world = event.getWorld();
    
        if (event.getType() != DecorateBiomeEvent.Decorate.EventType.DEAD_BUSH)
            return;
        if (MiaBlocks.flowerDead == null)
            return;
        if (world.provider.getDimensionType() != DimensionType.OVERWORLD)
            return;
        if ((event.getResult() != Event.Result.ALLOW && event.getResult() != Event.Result.DEFAULT))
            return;
        
        BlockPos pos = event.getPlacementPos() != null ? event.getPlacementPos() : event.getChunkPos().getBlock(0, 0, 0);
        Biome biome = world.getBiomeForCoordsBody(pos);
        
        Set<BiomeDictionary.Type> biomeTypes = BiomeDictionary.getTypes(biome);
        if (world.getWorldType() != WorldType.FLAT && (biomeTypes.contains(BiomeDictionary.Type.SANDY) || biomeTypes.contains(BiomeDictionary.Type.DRY) || biomeTypes.contains(BiomeDictionary.Type.DEAD) || biomeTypes.contains(BiomeDictionary.Type.WASTELAND)))
        {
            int max;
            if (biome.getDefaultTemperature() >= 1)
                max = world.rand.nextInt(6);
            else
                max = world.rand.nextInt(4);
            
            for (int i = 0; i < max; i++)
            {
                int xPos = pos.getX() + world.rand.nextInt(16);
                int zPos = pos.getZ() + world.rand.nextInt(16);
                generate(world, biome, new BlockPos(xPos, 0, zPos));
            }
        }
    }
    
    private static void generate(World world, Biome biome, BlockPos pos)
    {
        assert MiaBlocks.flowerDead != null;
        pos = findGround(world, pos);
        if (pos != null && pos.getY() >= 1)
        {
            float temperature = Math.max(0.1f, biome.getTemperature(pos));
            if (world.rand.nextInt() <= GenericAdditionsConfig.evtp.deadFlowerSpawnChance * temperature)
                world.setBlockState(pos, MiaBlocks.flowerDead.getDefaultState(), 2);
        }
    }
    
    @Nullable
    private static BlockPos findGround(World world, BlockPos pos)
    {
        assert MiaBlocks.flowerDead != null;
        for (pos = world.getTopSolidOrLiquidBlock(pos); pos.getY() >= 55; pos = pos.down())
        {
            IBlockState stateDown = world.getBlockState(pos);
            
            if (stateDown.isOpaqueCube() && !(stateDown.getBlock() instanceof BlockLeaves))
            {
                if (MiaBlocks.flowerDead.canSustainPlant(stateDown, world, pos, EnumFacing.DOWN, MiaBlocks.flowerDead))
                {
                    BlockPos up = pos.up();
                    if (world.getBlockState(up).getBlock() instanceof BlockLeaves)
                        return null;
                    return up;
                }
                return null;
            }
        }
        
        return null;
    }
}
