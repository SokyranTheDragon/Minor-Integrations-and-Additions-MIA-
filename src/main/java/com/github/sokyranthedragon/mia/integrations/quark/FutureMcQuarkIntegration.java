package com.github.sokyranthedragon.mia.integrations.quark;

import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.integrations.futuremc.IFutureMcIntegration;
import com.github.sokyranthedragon.mia.utilities.ItemStackUtils;
import com.github.sokyranthedragon.mia.utilities.QuarkUtils;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import vazkii.quark.building.block.BlockWorldStoneBricks;
import vazkii.quark.building.block.BlockWorldStonePavement;
import vazkii.quark.building.feature.*;
import vazkii.quark.world.block.BlockElderPrismarine;
import vazkii.quark.world.feature.Basalt;
import vazkii.quark.world.feature.Biotite;
import vazkii.quark.world.feature.RevampStoneGen;
import vazkii.quark.world.feature.UndergroundBiomes;

import javax.annotation.Nonnull;

import static com.github.sokyranthedragon.mia.integrations.ModIds.FUTURE_MC;
import static com.github.sokyranthedragon.mia.integrations.ModIds.QUARK;
import static com.github.sokyranthedragon.mia.integrations.futuremc.FutureMc.addBlastFurnaceRecipe;
import static com.github.sokyranthedragon.mia.integrations.futuremc.FutureMc.addStonecutterRecipes;

class FutureMcQuarkIntegration implements IFutureMcIntegration
{
    @Override
    public void addRecipes()
    {
        boolean soulSandstoneEnabled = QuarkUtils.isFeatureEnabled(SoulSandstone.class);
        
        if (soulSandstoneEnabled)
        {
            addStonecutterRecipes(new ItemStack(SoulSandstone.soul_sandstone),
                new ItemStack(SoulSandstone.soul_sandstone, 1, 1),
                new ItemStack(SoulSandstone.soul_sandstone, 1, 2));

//            registerSlabs(new ItemStack(SoulSandstone.soul_sandstone), "soul_sandstone");
            if (SoulSandstone.enableStairs)
                registerStairs(new ItemStack(SoulSandstone.soul_sandstone), "soul_sandstone");
            if (SoulSandstone.enableWalls)
                registerWalls(new ItemStack(SoulSandstone.soul_sandstone), "soul_sandstone");
        }
        
        if (QuarkUtils.isFeatureEnabled(MoreSandstone.class))
        {
            addStonecutterRecipes(new ItemStack(Blocks.SANDSTONE),
                new ItemStack(MoreSandstone.sandstone_new, 1, 0),
                new ItemStack(MoreSandstone.sandstone_new, 1, 1));
            addStonecutterRecipes(new ItemStack(Blocks.RED_SANDSTONE),
                new ItemStack(MoreSandstone.sandstone_new, 1, 2),
                new ItemStack(MoreSandstone.sandstone_new, 1, 3));
            if (soulSandstoneEnabled)
            {
                addStonecutterRecipes(new ItemStack(SoulSandstone.soul_sandstone),
                    new ItemStack(MoreSandstone.sandstone_new, 1, 4),
                    new ItemStack(MoreSandstone.sandstone_new, 1, 5));
            }
            
            if (MoreSandstone.enableStairsAndSlabs)
            {
                registerStairsAndSlabs(new ItemStack(MoreSandstone.sandstone_new, 1, 1), "sandstone_bricks");
                registerStairsAndSlabs(new ItemStack(MoreSandstone.sandstone_new, 1, 3), "red_sandstone_bricks");
                registerSlabs(new ItemStack(MoreSandstone.sandstone_new, 1, 0), "sandstone_smooth");
                registerSlabs(new ItemStack(MoreSandstone.sandstone_new, 1, 1), "sandstone_smooth");
                registerSlabs(new ItemStack(MoreSandstone.sandstone_new, 1, 2), "red_sandstone_smooth");
                registerSlabs(new ItemStack(MoreSandstone.sandstone_new, 1, 3), "red_sandstone_smooth");
                
                if (soulSandstoneEnabled)
                {
                    registerStairsAndSlabs(new ItemStack(MoreSandstone.sandstone_new, 1, 5), "soul_sandstone_bricks");
                    registerSlabs(new ItemStack(MoreSandstone.sandstone_new, 1, 4), "soul_sandstone_smooth");
                }
            }
        }
        
        if (QuarkUtils.isFeatureEnabled(VanillaWalls.class))
        {
            if (VanillaWalls.stone)
                registerWalls(new ItemStack(Blocks.STONE), "stone");
            if (VanillaWalls.granite)
                registerWalls(new ItemStack(Blocks.STONE, 1, 1), "stone_granite");
            if (VanillaWalls.diorite)
                registerWalls(new ItemStack(Blocks.STONE, 1, 3), "stone_diorite");
            if (VanillaWalls.andesite)
                registerWalls(new ItemStack(Blocks.STONE, 1, 5), "stone_andesite");
            if (VanillaWalls.sandstone)
                registerWalls(new ItemStack(Blocks.SANDSTONE), "sandstone");
            if (VanillaWalls.redSandstone)
                registerWalls(new ItemStack(Blocks.RED_SANDSTONE), "red_sandstone");
            if (VanillaWalls.stoneBricks)
                registerWalls(new ItemStack(Blocks.STONEBRICK), "stonebrick");
            if (VanillaWalls.bricks)
                registerWalls(new ItemStack(Blocks.BRICK_BLOCK), "brick");
            if (VanillaWalls.quartz)
                registerWalls(new ItemStack(Blocks.QUARTZ_BLOCK), "quartz");
            if (VanillaWalls.prismarine)
                registerWalls(new ItemStack(Blocks.PRISMARINE), "prismarine_rough");
            if (VanillaWalls.prismarineBricks)
                registerWalls(new ItemStack(Blocks.PRISMARINE, 1, 1), "prismarine_bricks");
            if (VanillaWalls.darkPrismarine)
                registerWalls(new ItemStack(Blocks.PRISMARINE, 1, 2), "dark_prismarine");
            if (VanillaWalls.purpurBlock)
                registerWalls(new ItemStack(Blocks.PURPUR_BLOCK), "purpur_block");
            if (VanillaWalls.endBricks)
                registerWalls(new ItemStack(Blocks.END_BRICKS), "end_bricks");
            if (VanillaWalls.mossBricks)
                registerWalls(new ItemStack(Blocks.STONEBRICK, 1, 1), "stonebrick_mossy");
        }
        
        if (QuarkUtils.isFeatureEnabled(VanillaStairsAndSlabs.class))
        {
            if (VanillaStairsAndSlabs.stone)
                registerStairsAndSlabs(new ItemStack(Blocks.STONE), "stone");
            if (VanillaStairsAndSlabs.granite)
                registerStairsAndSlabs(new ItemStack(Blocks.STONE, 1, 1), "stone_granite");
            if (VanillaStairsAndSlabs.diorite)
                registerStairsAndSlabs(new ItemStack(Blocks.STONE, 1, 3), "stone_diorite");
            if (VanillaStairsAndSlabs.andesite)
                registerStairsAndSlabs(new ItemStack(Blocks.STONE, 1, 5), "stone_andesite");
            if (VanillaStairsAndSlabs.prismarine)
                registerStairsAndSlabs(new ItemStack(Blocks.PRISMARINE), "prismarine");
            if (VanillaStairsAndSlabs.prismarineBricks)
                registerStairsAndSlabs(new ItemStack(Blocks.PRISMARINE, 1, 1), "prismarine_bricks");
            if (VanillaStairsAndSlabs.darkPrismarine)
                registerStairsAndSlabs(new ItemStack(Blocks.PRISMARINE, 1, 2), "prismarine_dark");
            if (VanillaStairsAndSlabs.endBricks)
                registerStairsAndSlabs(new ItemStack(Blocks.END_BRICKS), "end_bricks");
            if (VanillaStairsAndSlabs.mossStone)
                registerStairsAndSlabs(new ItemStack(Blocks.STONEBRICK, 1, 1), "cobblestone_mossy");
            if (VanillaStairsAndSlabs.mossBricks)
                registerStairsAndSlabs(new ItemStack(Blocks.STONEBRICK, 1, 1), "stonebrick_mossy");
        }
        
        if (QuarkUtils.isFeatureEnabled(Biotite.class))
        {
            addBlastFurnaceRecipe(new ItemStack(Biotite.biotite_ore), new ItemStack(Biotite.biotite));
            
            addStonecutterRecipes(new ItemStack(Biotite.biotite_block),
                new ItemStack(Biotite.biotite_block, 1, 1),
                new ItemStack(Biotite.biotite_block, 1, 2));
            
            registerStairsAndSlabs(new ItemStack(Biotite.biotite_block), "biotite");
            if (Biotite.enableWalls)
                registerWalls(new ItemStack(Biotite.biotite_block), "biotite");
        }
        
        if (QuarkUtils.isFeatureEnabled(PolishedStone.class))
        {
            addStonecutterRecipes(new ItemStack(Blocks.STONE), new ItemStack(PolishedStone.polished_stone));
            addStonecutterRecipes(new ItemStack(PolishedStone.polished_stone), new ItemStack(Blocks.STONE_SLAB, 2));
        }
        
        if (QuarkUtils.isFeatureEnabled(PolishedNetherrack.class))
        {
            addStonecutterRecipes(new ItemStack(PolishedNetherrack.polished_netherrack), new ItemStack(PolishedNetherrack.polished_netherrack, 1, 1));
            if (PolishedNetherrack.enableStairsAndSlabs)
            {
                registerStairsAndSlabs(new ItemStack(PolishedNetherrack.polished_netherrack), "polished_netherrack_bricks");
                registerStairsAndSlabs(new ItemStack(PolishedNetherrack.polished_netherrack, 1, 1), "polished_netherrack_bricks");
            }
            if (PolishedNetherrack.enableWalls)
            {
                registerWalls(new ItemStack(PolishedNetherrack.polished_netherrack), "polished_netherrack_bricks");
                registerWalls(new ItemStack(PolishedNetherrack.polished_netherrack, 1, 1), "polished_netherrack_bricks");
            }
        }
        
        if (QuarkUtils.isFeatureEnabled(RevampStoneGen.class))
        {
            if (RevampStoneGen.enableMarble)
            {
                addStonecutterRecipes(new ItemStack(RevampStoneGen.marble), new ItemStack(RevampStoneGen.marble, 1, 1));
                if (RevampStoneGen.enableStairsAndSlabs)
                    registerStairsAndSlabs(new ItemStack(RevampStoneGen.marble), "stone_marble");
                if (RevampStoneGen.enableWalls)
                    registerWalls(new ItemStack(RevampStoneGen.marble), "marble");
            }
            if (RevampStoneGen.enableLimestone)
            {
                addStonecutterRecipes(new ItemStack(RevampStoneGen.limestone), new ItemStack(RevampStoneGen.limestone, 1, 1));
                if (RevampStoneGen.enableStairsAndSlabs)
                    registerStairsAndSlabs(new ItemStack(RevampStoneGen.limestone), "stone_limestone");
                if (RevampStoneGen.enableWalls)
                    registerWalls(new ItemStack(RevampStoneGen.limestone), "limestone");
            }
            if (RevampStoneGen.enableJasper)
            {
                addStonecutterRecipes(new ItemStack(RevampStoneGen.jasper), new ItemStack(RevampStoneGen.jasper, 1, 1));
                if (RevampStoneGen.enableStairsAndSlabs)
                    registerStairsAndSlabs(new ItemStack(RevampStoneGen.jasper), "stone_jasper");
                if (RevampStoneGen.enableWalls)
                    registerWalls(new ItemStack(RevampStoneGen.jasper), "jasper");
            }
            if (RevampStoneGen.enableSlate)
            {
                addStonecutterRecipes(new ItemStack(RevampStoneGen.slate), new ItemStack(RevampStoneGen.slate, 1, 1));
                if (RevampStoneGen.enableStairsAndSlabs)
                    registerStairsAndSlabs(new ItemStack(RevampStoneGen.slate), "stone_slate");
                if (RevampStoneGen.enableWalls)
                    registerWalls(new ItemStack(RevampStoneGen.slate), "slate");
            }
        }
        
        if (QuarkUtils.isFeatureEnabled(UndergroundBiomes.class))
        {
            if (UndergroundBiomes.firestoneEnabled)
            {
                addStonecutterRecipes(new ItemStack(UndergroundBiomes.biome_cobblestone), new ItemStack(UndergroundBiomes.biome_brick));
                if (UndergroundBiomes.enableStairsAndSlabs)
                {
                    registerStairsAndSlabs(new ItemStack(UndergroundBiomes.biome_cobblestone), "fire_stone");
                    registerStairsAndSlabs(new ItemStack(UndergroundBiomes.biome_cobblestone), "fire_stone_brick");
                    registerStairsAndSlabs(new ItemStack(UndergroundBiomes.biome_brick), "fire_stone_brick");
                }
                if (UndergroundBiomes.enableWalls)
                {
                    registerWalls(new ItemStack(UndergroundBiomes.biome_cobblestone), "fire_stone");
                    registerWalls(new ItemStack(UndergroundBiomes.biome_cobblestone), "fire_stone_brick");
                    registerWalls(new ItemStack(UndergroundBiomes.biome_brick), "fire_stone_brick");
                }
            }
            if (UndergroundBiomes.icystoneEnabled)
            {
                addStonecutterRecipes(new ItemStack(UndergroundBiomes.biome_cobblestone, 1, 1), new ItemStack(UndergroundBiomes.biome_brick, 1, 1));
                if (UndergroundBiomes.enableStairsAndSlabs)
                {
                    registerStairsAndSlabs(new ItemStack(UndergroundBiomes.biome_cobblestone, 1, 1), "icy_stone");
                    registerStairsAndSlabs(new ItemStack(UndergroundBiomes.biome_cobblestone, 1, 1), "icy_stone_brick");
                    registerStairsAndSlabs(new ItemStack(UndergroundBiomes.biome_brick, 1, 1), "icy_stone_brick");
                }
                if (UndergroundBiomes.enableWalls)
                {
                    registerWalls(new ItemStack(UndergroundBiomes.biome_cobblestone, 1, 1), "icy_stone");
                    registerWalls(new ItemStack(UndergroundBiomes.biome_cobblestone, 1, 1), "icy_stone_brick");
                    registerWalls(new ItemStack(UndergroundBiomes.biome_brick, 1, 1), "icy_stone_brick");
                }
            }
            if (UndergroundBiomes.cobbedstoneEnabled)
            {
                if (UndergroundBiomes.enableStairsAndSlabs)
                    registerStairsAndSlabs(new ItemStack(UndergroundBiomes.biome_cobblestone, 1, 2), "cobbed_stone");
                if (UndergroundBiomes.enableWalls)
                    registerWalls(new ItemStack(UndergroundBiomes.biome_cobblestone, 1, 2), "cobbed_stone");
            }
            if (UndergroundBiomes.elderPrismarineEnabled)
            {
                for (BlockElderPrismarine.Variants value : BlockElderPrismarine.Variants.values())
                {
                    if (UndergroundBiomes.enableStairsAndSlabs)
                        registerStairsAndSlabs(new ItemStack(UndergroundBiomes.elder_prismarine, 1, value.ordinal()), value.getName());
                    if (UndergroundBiomes.enableWalls)
                        registerWalls(new ItemStack(UndergroundBiomes.elder_prismarine, 1, value.ordinal()), value.getName());
                }
            }
        }
        
        if (QuarkUtils.isFeatureEnabled(Basalt.class))
        {
            addStonecutterRecipes(new ItemStack(Basalt.basalt), new ItemStack(Basalt.basalt, 1, 1));
            
            if (Basalt.enableStairsAndSlabs)
                registerStairsAndSlabs(new ItemStack(Basalt.basalt), "stone_basalt");
            if (Basalt.enableWalls)
                registerWalls(new ItemStack(Basalt.basalt), "basalt");
        }
        
        if (QuarkUtils.isFeatureEnabled(SandyBricks.class))
        {
            if (SandyBricks.enableStairsAndSlabs)
                registerStairsAndSlabs(new ItemStack(SandyBricks.sandy_bricks), "sandy_bricks");
            if (SandyBricks.enableWalls)
                registerWalls(new ItemStack(SandyBricks.sandy_bricks), "sandy_bricks");
        }
        
        if (QuarkUtils.isFeatureEnabled(SnowBricks.class))
        {
            if (SnowBricks.enableStairsAndSlabs)
                registerStairsAndSlabs(new ItemStack(SnowBricks.snow_bricks), "snow_bricks");
            if (SnowBricks.enableWalls)
                registerWalls(new ItemStack(SnowBricks.snow_bricks), "snow_bricks");
        }
        
        if (QuarkUtils.isFeatureEnabled(MidoriBlocks.class))
        {
            addStonecutterRecipes(new ItemStack(MidoriBlocks.midori_block), new ItemStack(MidoriBlocks.midori_pillar));
            if (MidoriBlocks.enableStairsAndSlabs)
                registerStairsAndSlabs(new ItemStack(MidoriBlocks.midori_block), "midori_block");
            if (MidoriBlocks.enableWalls)
                registerWalls(new ItemStack(MidoriBlocks.midori_block), "midori_block");
        }
        
        if (QuarkUtils.isFeatureEnabled(MagmaBricks.class) && MagmaBricks.enableStairsAndSlabs)
        {
            registerStairsAndSlabs(new ItemStack(MagmaBricks.magma_bricks), "magma_bricks");
        }
        
        if (QuarkUtils.isFeatureEnabled(DuskboundBlocks.class))
        {
            if (DuskboundBlocks.enableStairsAndSlabs)
                registerStairsAndSlabs(new ItemStack(DuskboundBlocks.duskbound_block), "duskbound_block");
            if (DuskboundBlocks.enableWalls)
                registerWalls(new ItemStack(DuskboundBlocks.duskbound_block), "duskbound_block");
        }
        
        if (QuarkUtils.isFeatureEnabled(WorldStonePavement.class))
        {
            for (BlockWorldStonePavement.Variants value : BlockWorldStonePavement.Variants.values())
            {
                if (value.isEnabled())
                {
                    ItemStack input = ItemStack.EMPTY;
                    
                    switch (value)
                    {
                        case STONE_GRANITE_PAVEMENT:
                            input = new ItemStack(Blocks.STONE, 1, 1);
                            break;
                        case STONE_DIORITE_PAVEMENT:
                            input = new ItemStack(Blocks.STONE, 1, 3);
                            break;
                        case STONE_ANDESITE_PAVEMENT:
                            input = new ItemStack(Blocks.STONE, 1, 5);
                            break;
                        case STONE_BASALT_PAVEMENT:
                            input = new ItemStack(Basalt.basalt);
                            break;
                        case STONE_MARBLE_PAVEMENT:
                            input = new ItemStack(RevampStoneGen.marble);
                            break;
                        case STONE_LIMESTONE_PAVEMENT:
                            input = new ItemStack(RevampStoneGen.limestone);
                            break;
                        case STONE_JASPER_PAVEMENT:
                            input = new ItemStack(RevampStoneGen.jasper);
                            break;
                        case STONE_SLATE_PAVEMENT:
                            input = new ItemStack(RevampStoneGen.slate);
                            break;
                    }
                    
                    if (!input.isEmpty())
                        addStonecutterRecipes(input, new ItemStack(WorldStonePavement.world_stone_pavement, 1, value.ordinal()));
                }
            }
        }
        
        if (QuarkUtils.isFeatureEnabled(WorldStoneBricks.class))
        {
            for (BlockWorldStoneBricks.Variants value : BlockWorldStoneBricks.Variants.values())
            {
                ItemStack input = ItemStack.EMPTY;
                ItemStack inputSecond = ItemStack.EMPTY;
                
                if (value.isEnabled())
                {
                    switch (value)
                    {
                        case STONE_GRANITE_BRICKS:
                            input = new ItemStack(Blocks.STONE, 1, 1);
                            inputSecond = new ItemStack(Blocks.STONE, 1, 2);
                            break;
                        case STONE_DIORITE_BRICKS:
                            input = new ItemStack(Blocks.STONE, 1, 3);
                            inputSecond = new ItemStack(Blocks.STONE, 1, 4);
                            break;
                        case STONE_ANDESITE_BRICKS:
                            input = new ItemStack(Blocks.STONE, 1, 5);
                            inputSecond = new ItemStack(Blocks.STONE, 1, 6);
                            break;
                        case STONE_BASALT_BRICKS:
                            if (Basalt.basalt != null) // I'm not 100% sure it's actually checked in Quark for that
                            {
                                input = new ItemStack(Basalt.basalt);
                                inputSecond = new ItemStack(Basalt.basalt, 1, 1);
                            }
                            break;
                        case STONE_MARBLE_BRICKS:
                            if (RevampStoneGen.enableMarble)
                            {
                                input = new ItemStack(RevampStoneGen.marble);
                                inputSecond = new ItemStack(RevampStoneGen.marble, 1, 1);
                            }
                            break;
                        case STONE_LIMESTONE_BRICKS:
                            if (RevampStoneGen.enableLimestone)
                            {
                                input = new ItemStack(RevampStoneGen.limestone);
                                inputSecond = new ItemStack(RevampStoneGen.limestone, 1, 1);
                            }
                            break;
                        case STONE_JASPER_BRICKS:
                            if (RevampStoneGen.enableJasper)
                            {
                                input = new ItemStack(RevampStoneGen.jasper);
                                inputSecond = new ItemStack(RevampStoneGen.jasper, 1, 1);
                            }
                            break;
                        case STONE_SLATE_BRICKS:
                            if (RevampStoneGen.enableSlate)
                            {
                                input = new ItemStack(RevampStoneGen.slate);
                                inputSecond = new ItemStack(RevampStoneGen.slate, 1, 1);
                            }
                            break;
                    }
                    
                    ItemStack brick = new ItemStack(WorldStoneBricks.world_stone_bricks, 1, value.ordinal());
                    if (!input.isEmpty())
                        addStonecutterRecipes(input, brick);
                    if (!inputSecond.isEmpty())
                        addStonecutterRecipes(inputSecond, brick);
                    addStonecutterRecipes(brick, new ItemStack(WorldStoneBricks.world_stone_chiseled, 1, value.ordinal()));
                    if (WorldStoneBricks.enableStairsAndSlabs)
                    {
                        if (!input.isEmpty())
                            registerStairsAndSlabs(input, value.getName());
                        if (!inputSecond.isEmpty())
                            registerStairsAndSlabs(input, value.getName());
                        registerStairsAndSlabs(brick, value.getName());
                    }
                    if (WorldStoneBricks.enableWalls)
                    {
                        if (!input.isEmpty())
                            registerWalls(input, value.getName());
                        if (!inputSecond.isEmpty())
                            registerWalls(inputSecond, value.getName());
                        registerWalls(brick, value.getName());
                    }
                }
            }
        }
    }
    
    private static void registerStairsAndSlabs(ItemStack input, String name)
    {
        registerSlabs(input, name);
        registerStairs(input, name);
    }
    
    private static void registerSlabs(ItemStack input, String name)
    {
        ItemStackUtils.getStack(QUARK, name + "_slab", 2)
                      .ifPresent(slab ->
                          addStonecutterRecipes(input, slab));
    }
    
    private static void registerStairs(ItemStack input, String name)
    {
        ItemStackUtils.getStack(QUARK, name + "_stairs")
                      .ifPresent(stairs ->
                          addStonecutterRecipes(input, stairs));
    }
    
    private static void registerWalls(ItemStack input, String name)
    {
        ItemStackUtils.getStack(QUARK, name + "_wall").ifPresent(wall -> addStonecutterRecipes(input, wall));
    }
    
    @Override
    @Nonnull
    public ModIds getModId()
    {
        return FUTURE_MC;
    }
}
