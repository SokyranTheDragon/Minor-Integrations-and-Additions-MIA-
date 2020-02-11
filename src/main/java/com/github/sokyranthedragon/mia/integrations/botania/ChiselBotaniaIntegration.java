package com.github.sokyranthedragon.mia.integrations.botania;

import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.integrations.chisel.IChiselIntegration;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import org.apache.logging.log4j.util.TriConsumer;
import team.chisel.common.config.Configurations;
import vazkii.botania.api.state.enums.BiomeStoneVariant;
import vazkii.botania.common.block.ModBlocks;
import vazkii.botania.common.block.ModFluffBlocks;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.function.BiConsumer;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
class ChiselBotaniaIntegration implements IChiselIntegration
{
    @Override
    public void sendChiselMessages(BiConsumer<String, ItemStack[]> messageStackSender, TriConsumer<String, Block, Integer> messageBlockSender)
    {
        for (int meta = 0; meta <= 15; meta++)
            messageBlockSender.accept("azulejo", ModBlocks.customBrick, meta);
        
        for (int meta = 0; meta <= 4; meta++)
        {
            if (meta != 0)
            {
                messageBlockSender.accept("planks-livingwood", ModBlocks.livingwood, meta);
                messageBlockSender.accept("planks-dreamwood", ModBlocks.dreamwood, meta);
            }
            if (meta != 3 && (meta != 2 || Configurations.allowMossy))
                messageBlockSender.accept("livingrock", ModBlocks.livingrock, meta);
        }
        
        for (int meta = 0; meta <= 2; meta++)
        {
            messageBlockSender.accept("quartz_dark", ModFluffBlocks.darkQuartz, meta);
            messageBlockSender.accept("quartz_mana", ModFluffBlocks.manaQuartz, meta);
            messageBlockSender.accept("quartz_blaze", ModFluffBlocks.blazeQuartz, meta);
            messageBlockSender.accept("quartz_lavender", ModFluffBlocks.lavenderQuartz, meta);
            messageBlockSender.accept("quartz_red", ModFluffBlocks.redQuartz, meta);
            messageBlockSender.accept("quartz_elven", ModFluffBlocks.elfQuartz, meta);
            messageBlockSender.accept("quartz_sunny", ModFluffBlocks.sunnyQuartz, meta);
        }
        
        for (int meta = 0; meta <= 7; meta++)
        {
            String stoneName = "stone_biome_" + BiomeStoneVariant.values()[meta].getName();
            
            if (Configurations.chiselStoneToCobbleBricks)
                messageBlockSender.accept(stoneName, ModFluffBlocks.biomeStoneA, meta);
            messageBlockSender.accept(stoneName, ModFluffBlocks.biomeStoneB, meta);
            messageBlockSender.accept(stoneName, ModFluffBlocks.biomeStoneB, meta + 8);
        }
    }
    
    @Override
    public ModIds getModId()
    {
        return ModIds.BOTANIA;
    }
}
