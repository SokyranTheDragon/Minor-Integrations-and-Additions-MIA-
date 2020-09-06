package com.github.sokyranthedragon.mia.integrations.quark;

import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.integrations.chisel.IChiselIntegration;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import org.apache.logging.log4j.util.TriConsumer;
import team.chisel.common.config.Configurations;
import vazkii.quark.building.block.BlockWorldStoneBricks;
import vazkii.quark.building.feature.*;
import vazkii.quark.decoration.feature.VariedBookshelves;
import vazkii.quark.world.feature.Biotite;
import vazkii.quark.world.feature.UndergroundBiomes;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.function.BiConsumer;

import static com.github.sokyranthedragon.mia.utilities.QuarkUtils.isFeatureEnabled;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
class ChiselQuarkIntegration implements IChiselIntegration
{
    @Override
    public void sendChiselMessages(BiConsumer<String, ItemStack[]> messageStackSender, TriConsumer<String, Block, Integer> messageBlockSender)
    {
        if (isFeatureEnabled(Biotite.class))
        {
            for (int meta = 0; meta <= 2; meta++)
                messageBlockSender.accept("biotite", Biotite.biotite_block, meta);
        }
        if (isFeatureEnabled(UndergroundBiomes.class))
        {
            if (UndergroundBiomes.firestoneEnabled)
            {
                messageBlockSender.accept("brimstone", UndergroundBiomes.biome_cobblestone, 0);
                messageBlockSender.accept("brimstone", UndergroundBiomes.biome_brick, 0);
            }
            if (UndergroundBiomes.icystoneEnabled)
            {
                messageBlockSender.accept("permafrost", UndergroundBiomes.biome_cobblestone, 1);
                messageBlockSender.accept("permafrost", UndergroundBiomes.biome_brick, 1);
            }
        }
        if (isFeatureEnabled(MoreSandstone.class))
        {
            for (int meta = 0; meta <= 1; meta++)
            {
                messageBlockSender.accept("sandstone", MoreSandstone.sandstone_new, meta);
                messageBlockSender.accept("sandstonered", MoreSandstone.sandstone_new, meta + 2);
                if (isFeatureEnabled(SoulSandstone.class))
                    messageBlockSender.accept("sandstonesoul", MoreSandstone.sandstone_new, meta + 4);
            }
        }
        if (isFeatureEnabled(SoulSandstone.class))
        {
            for (int meta = 0; meta <= 2; meta++)
                messageBlockSender.accept("sandstonesoul", SoulSandstone.soul_sandstone, meta);
        }
        if (isFeatureEnabled(PolishedStone.class) && Configurations.allowSmoothStone)
            messageBlockSender.accept("stonebrick", PolishedStone.polished_stone, 0);
        if (isFeatureEnabled(HardenedClayTiles.class))
        {
            messageBlockSender.accept("hardenedclay", HardenedClayTiles.hardened_clay_tiles, 0);
            if (HardenedClayTiles.enableStainedClay)
            {
                for (int meta = 0; meta < EnumDyeColor.values().length; meta++)
                {
                    EnumDyeColor color = EnumDyeColor.values()[meta];
                    messageBlockSender.accept("hardenedclaydyed_" + color.getName(), Blocks.STAINED_HARDENED_CLAY, meta);
                    messageBlockSender.accept("hardenedclaydyed_" + color.getName(), HardenedClayTiles.stained_clay_tiles, meta);
                }
            }
        }
        if (isFeatureEnabled(WorldStoneBricks.class) || isFeatureEnabled(WorldStonePavement.class))
        {
            for (int meta = 0; meta < BlockWorldStoneBricks.Variants.values().length; meta++)
            {
                // Ude StoneBricks for block variant name
                BlockWorldStoneBricks.Variants variant = BlockWorldStoneBricks.Variants.values()[meta];
                if (variant.isEnabled())
                {
                    if (isFeatureEnabled(WorldStoneBricks.class))
                        messageBlockSender.accept(variant.blockName, WorldStoneBricks.world_stone_chiseled, meta);
                    if (isFeatureEnabled(WorldStonePavement.class))
                        messageBlockSender.accept(variant.blockName, WorldStonePavement.world_stone_pavement, meta);
                }
            }
        }
        if (isFeatureEnabled(MidoriBlocks.class))
        {
            for (int meta = 0; meta <= 1; meta++)
                messageBlockSender.accept("midori", MidoriBlocks.midori_block, meta);
        }
        if (isFeatureEnabled(PolishedNetherrack.class))
        {
            for (int meta = 0; meta <= 1; meta++)
                messageBlockSender.accept("netherrack", PolishedNetherrack.polished_netherrack, meta);
        }
        if (isFeatureEnabled(VerticalWoodPlanks.class))
        {
            String[] types = new String[]{ "oak", "spruce", "birch", "jungle", "acacia", "dark-oak" };
            for (int meta = 0; meta < types.length; meta++)
                messageBlockSender.accept("planks-" + types[meta], VerticalWoodPlanks.vertical_planks, meta);
        }
        if (isFeatureEnabled(CarvedWood.class))
        {
            String[] types = new String[]{ "oak", "spruce", "birch", "jungle", "acacia", "dark-oak" };
            for (int meta = 0; meta < types.length; meta++)
                messageBlockSender.accept("planks-" + types[meta], CarvedWood.carvedWood, meta);
        }
        if (isFeatureEnabled(VariedBookshelves.class))
        {
            String[] types = new String[]{ "spruce", "birch", "jungle", "acacia", "darkoak" };
            for (int meta = 0; meta < types.length; meta++)
                messageBlockSender.accept("bookshelf_" + types[meta], VariedBookshelves.custom_bookshelf, meta);
        }
    }
    
    @Override
    public ModIds getModId()
    {
        return ModIds.QUARK;
    }
}
