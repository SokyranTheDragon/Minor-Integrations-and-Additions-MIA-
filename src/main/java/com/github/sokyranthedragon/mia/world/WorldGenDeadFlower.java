package com.github.sokyranthedragon.mia.world;

import com.github.sokyranthedragon.mia.Mia;
import com.github.sokyranthedragon.mia.config.GenericAdditionsConfig;
import com.github.sokyranthedragon.mia.core.MiaBlocks;
import com.github.sokyranthedragon.mia.utilities.annotations.FieldsAreNullableByDefault;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.fml.common.IWorldGenerator;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Random;
import java.util.Set;

@ParametersAreNonnullByDefault
@FieldsAreNullableByDefault
public class WorldGenDeadFlower implements IWorldGenerator
{
    public static WorldGenDeadFlower INSTANCE = null;
    
    private void generate(World world, Biome biome, Random random, BlockPos pos)
    {
        assert MiaBlocks.flowerDead != null;
        pos = findGround(world, pos);
        if (pos != null && pos.getY() >= 1)
        {
            float temperature = Math.max(0.05f, biome.getTemperature(pos));
            if (random.nextInt() <= GenericAdditionsConfig.evtp.deadFlowerSpawnChance * temperature)
                world.setBlockState(pos, MiaBlocks.flowerDead.getDefaultState(), 2);
        }
    }
    
    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator iChunkGenerator, IChunkProvider iChunkProvider)
    {
        if (MiaBlocks.flowerDead == null)
        {
            Mia.LOGGER.error("Dead flower generator is enabled while the dead flower isn't! Please report this to the mod developer.");
            return;
        }
        
        if (world.provider.getDimensionType() != DimensionType.OVERWORLD)
            return;
        
        int xPos = chunkX * 16 + 8;
        int zPos = chunkZ * 16 + 8;
        
        Biome biome = world.getBiomeForCoordsBody(new BlockPos(xPos, 0, zPos));
        ChunkPos chunkPos = world.getChunk(chunkX, chunkZ).getPos();
        
        Set<BiomeDictionary.Type> biomeTypes = BiomeDictionary.getTypes(biome);
        if (world.getWorldType() != WorldType.FLAT && (biomeTypes.contains(BiomeDictionary.Type.SANDY) || biomeTypes.contains(BiomeDictionary.Type.DRY) || biomeTypes.contains(BiomeDictionary.Type.DEAD) || biomeTypes.contains(BiomeDictionary.Type.WASTELAND)))
        {
            int max = random.nextInt(4);
            if (biome.getDefaultTemperature() >= 1)
                max += random.nextInt(2);
            
            for (int i = 0; i < max; i++)
            {
                int xSpawn = xPos + random.nextInt(16);
                int zSpawn = zPos + random.nextInt(16);
                int ySpawn = random.nextInt(world.getHeight(chunkPos.getBlock(0, 0, 0).add(xSpawn, 0, zSpawn)).getY() + 32);
                BlockPos pos = new BlockPos(xSpawn, ySpawn, zSpawn);
                generate(world, biome, random, pos);
            }
        }
    }
    
    @Nullable
    private BlockPos findGround(World world, BlockPos pos)
    {
        assert MiaBlocks.flowerDead != null;
        for (pos = new BlockPos(pos.getX(), 95, pos.getY()); pos.getY() >= 55; pos = pos.down())
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
