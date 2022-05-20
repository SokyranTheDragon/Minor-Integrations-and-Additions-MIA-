package com.github.sokyranthedragon.mia.integrations.tconstruct;

import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.integrations.futuremc.IFutureMcIntegration;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import slimeknights.tconstruct.TConstruct;
import slimeknights.tconstruct.gadgets.TinkerGadgets;
import slimeknights.tconstruct.shared.TinkerCommons;
import slimeknights.tconstruct.smeltery.TinkerSmeltery;

import javax.annotation.Nonnull;

import static com.github.sokyranthedragon.mia.integrations.futuremc.FutureMc.addStonecutterRecipes;

class FutureMcTConstructIntegration implements IFutureMcIntegration
{
    @Override
    public void addRecipes()
    {
        if (TConstruct.pulseManager.isPulseLoaded(TinkerCommons.PulseId))
            addStonecutterRecipes(TinkerCommons.mudBrickBlock, new ItemStack(TinkerCommons.slabDecoGround, 2), new ItemStack(TinkerCommons.stairsMudBrick));

        if (TConstruct.pulseManager.isPulseLoaded(TinkerGadgets.PulseId))
        {
            addStonecutterRecipes(new ItemStack(TinkerGadgets.driedClay, 1, 0),
                new ItemStack(TinkerGadgets.driedClaySlab, 2, 0),
                new ItemStack(TinkerGadgets.driedClayStairs));
            addStonecutterRecipes(new ItemStack(TinkerGadgets.driedClay, 1, 1),
                new ItemStack(TinkerGadgets.driedClaySlab, 2, 1),
                new ItemStack(TinkerGadgets.driedBrickStairs));
            
            registerBrownstone(0,
                new ItemStack(TinkerGadgets.brownstoneSlab, 2, 0),
                new ItemStack(TinkerGadgets.brownstoneStairsSmooth),
                new ItemStack(TinkerGadgets.brownstone, 1, 2),
                new ItemStack(TinkerGadgets.brownstone, 1, 3),
                new ItemStack(TinkerGadgets.brownstone, 1, 5),
                new ItemStack(TinkerGadgets.brownstone, 1, 6),
                new ItemStack(TinkerGadgets.brownstone, 1, 7),
                new ItemStack(TinkerGadgets.brownstone, 1, 8),
                new ItemStack(TinkerGadgets.brownstone, 1, 9),
                new ItemStack(TinkerGadgets.brownstone, 1, 10),
                new ItemStack(TinkerGadgets.brownstone, 1, 11));
            registerBrownstone(1, new ItemStack(TinkerGadgets.brownstoneSlab, 2, 1), new ItemStack(TinkerGadgets.brownstoneStairsRough));
            registerDoubleBrownstone(2, new ItemStack(TinkerGadgets.brownstoneSlab, 2, 2), new ItemStack(TinkerGadgets.brownstoneStairsPaver));
            registerDoubleBrownstone(3, new ItemStack(TinkerGadgets.brownstoneSlab, 2, 3), new ItemStack(TinkerGadgets.brownstoneStairsBrick));
            registerBrownstone(4, new ItemStack(TinkerGadgets.brownstoneSlab, 2, 4), new ItemStack(TinkerGadgets.brownstoneStairsBrickCracked));
            registerDoubleBrownstone(5, new ItemStack(TinkerGadgets.brownstoneSlab, 2, 5), new ItemStack(TinkerGadgets.brownstoneStairsBrickFancy));
            registerDoubleBrownstone(6, new ItemStack(TinkerGadgets.brownstoneSlab, 2, 6), new ItemStack(TinkerGadgets.brownstoneStairsBrickSquare));
            registerDoubleBrownstone(7, new ItemStack(TinkerGadgets.brownstoneSlab, 2, 7), new ItemStack(TinkerGadgets.brownstoneStairsRoad));
            registerDoubleBrownstone(8, new ItemStack(TinkerGadgets.brownstoneSlab2, 2, 0), new ItemStack(TinkerGadgets.brownstoneStairsCreeper));
            registerDoubleBrownstone(9, new ItemStack(TinkerGadgets.brownstoneSlab2, 2, 1), new ItemStack(TinkerGadgets.brownstoneStairsBrickTriangle));
            registerDoubleBrownstone(10, new ItemStack(TinkerGadgets.brownstoneSlab2, 2, 2), new ItemStack(TinkerGadgets.brownstoneStairsBrickSmall));
            registerDoubleBrownstone(11, new ItemStack(TinkerGadgets.brownstoneSlab2, 2, 3), new ItemStack(TinkerGadgets.brownstoneStairsTile));
        }
    }
    
    private static void registerRecipe(Block block, int meta, ItemStack... outputs)
    {
        addStonecutterRecipes(new ItemStack(block, 1, meta), outputs);
    }
    
    private static void registerSeared(int meta, ItemStack... outputs)
    {
        registerRecipe(TinkerSmeltery.searedBlock, meta, outputs);
    }
    
    private static void registerDoubleSeared(int meta, ItemStack... outputs)
    {
        registerRecipe(TinkerSmeltery.searedBlock, 0, outputs);
        registerRecipe(TinkerSmeltery.searedBlock, meta, outputs);
    }
    
    private static void registerBrownstone(int meta, ItemStack... outputs)
    {
        registerRecipe(TinkerGadgets.brownstone, meta, outputs);
    }
    
    private static void registerDoubleBrownstone(int meta, ItemStack... outputs)
    {
        registerRecipe(TinkerGadgets.brownstone, 0, outputs);
        registerRecipe(TinkerGadgets.brownstone, meta, outputs);
    }
    
    @Override
    @Nonnull
    public ModIds getModId()
    {
        return ModIds.TINKERS_CONSTRUCT;
    }
}