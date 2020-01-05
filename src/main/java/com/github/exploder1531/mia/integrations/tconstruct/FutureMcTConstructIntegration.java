package com.github.exploder1531.mia.integrations.tconstruct;

import com.github.exploder1531.mia.integrations.ModIds;
import com.github.exploder1531.mia.integrations.futuremc.FutureMc;
import com.github.exploder1531.mia.integrations.futuremc.IFutureMcIntegration;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import slimeknights.tconstruct.TConstruct;
import slimeknights.tconstruct.gadgets.TinkerGadgets;
import slimeknights.tconstruct.shared.TinkerCommons;
import slimeknights.tconstruct.smeltery.TinkerSmeltery;
import thedarkcolour.futuremc.tile.TileCampfire;

import javax.annotation.Nonnull;

import static thedarkcolour.futuremc.block.BlockFurnaceAdvanced.Recipes.blastFurnaceRecipe;
import static thedarkcolour.futuremc.recipe.StonecutterRecipes.addOrCreateRecipe;

class FutureMcTConstructIntegration implements IFutureMcIntegration
{
    @Override
    public void addRecipes()
    {
        if (TConstruct.pulseManager.isPulseLoaded(TinkerCommons.PulseId))
        {
            blastFurnaceRecipe(TinkerCommons.oreCobalt, TinkerCommons.ingotCobalt);
            blastFurnaceRecipe(TinkerCommons.oreArdite, TinkerCommons.ingotArdite);
            
            addOrCreateRecipe(TinkerCommons.mudBrickBlock, new ItemStack(TinkerCommons.slabDecoGround, 2), new ItemStack(TinkerCommons.stairsMudBrick));
        }
        
        if (TConstruct.pulseManager.isPulseLoaded(TinkerSmeltery.PulseId))
        {
            registerSeared(0,
                    new ItemStack(TinkerSmeltery.searedStairsStone),
                    new ItemStack(TinkerSmeltery.searedSlab, 2, 0),
                    new ItemStack(TinkerSmeltery.searedBlock, 1, 2),
                    new ItemStack(TinkerSmeltery.searedBlock, 1, 3),
                    new ItemStack(TinkerSmeltery.searedBlock, 1, 5),
                    new ItemStack(TinkerSmeltery.searedBlock, 1, 6),
                    new ItemStack(TinkerSmeltery.searedBlock, 1, 7),
                    new ItemStack(TinkerSmeltery.searedBlock, 1, 8),
                    new ItemStack(TinkerSmeltery.searedBlock, 1, 9),
                    new ItemStack(TinkerSmeltery.searedBlock, 1, 10),
                    new ItemStack(TinkerSmeltery.searedBlock, 1, 11));
            registerSeared(1, new ItemStack(TinkerSmeltery.searedSlab, 2, 1), new ItemStack(TinkerSmeltery.searedStairsCobble));
            registerDoubleSeared(2, new ItemStack(TinkerSmeltery.searedSlab, 2, 2), new ItemStack(TinkerSmeltery.searedStairsPaver));
            registerDoubleSeared(3, new ItemStack(TinkerSmeltery.searedSlab, 2, 3), new ItemStack(TinkerSmeltery.searedStairsBrick));
            registerSeared(4, new ItemStack(TinkerSmeltery.searedSlab, 2, 4), new ItemStack(TinkerSmeltery.searedStairsBrickCracked));
            registerDoubleSeared(5, new ItemStack(TinkerSmeltery.searedSlab, 2, 5), new ItemStack(TinkerSmeltery.searedStairsBrickFancy));
            registerDoubleSeared(6, new ItemStack(TinkerSmeltery.searedSlab, 2, 6), new ItemStack(TinkerSmeltery.searedStairsBrickSquare));
            registerDoubleSeared(7, new ItemStack(TinkerSmeltery.searedSlab, 2, 7), new ItemStack(TinkerSmeltery.searedStairsRoad));
            registerDoubleSeared(8, new ItemStack(TinkerSmeltery.searedSlab2, 2, 0), new ItemStack(TinkerSmeltery.searedStairsCreeper));
            registerDoubleSeared(9, new ItemStack(TinkerSmeltery.searedSlab2, 2, 1), new ItemStack(TinkerSmeltery.searedStairsBrickTriangle));
            registerDoubleSeared(10, new ItemStack(TinkerSmeltery.searedSlab2, 2, 2), new ItemStack(TinkerSmeltery.searedStairsBrickSmall));
            registerDoubleSeared(11, new ItemStack(TinkerSmeltery.searedSlab2, 2, 3), new ItemStack(TinkerSmeltery.searedStairsTile));
        }
        
        if (TConstruct.pulseManager.isPulseLoaded(TinkerGadgets.PulseId))
        {
            TileCampfire.Recipes.recipe(new ItemStack(TinkerGadgets.spaghetti, 1, 1), new ItemStack(TinkerGadgets.spaghetti, 1, 2), 20 * 60 * 15);
            FutureMc.addFoodRecipe(new ItemStack(TinkerGadgets.spaghetti, 1, 2), new ItemStack(TinkerGadgets.momsSpaghetti));
            
            addOrCreateRecipe(new ItemStack(TinkerGadgets.driedClay, 1, 0),
                    new ItemStack(TinkerGadgets.driedClaySlab, 2, 0),
                    new ItemStack(TinkerGadgets.driedClayStairs));
            addOrCreateRecipe(new ItemStack(TinkerGadgets.driedClay, 1, 1),
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
        addOrCreateRecipe(new ItemStack(block, meta), outputs);
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