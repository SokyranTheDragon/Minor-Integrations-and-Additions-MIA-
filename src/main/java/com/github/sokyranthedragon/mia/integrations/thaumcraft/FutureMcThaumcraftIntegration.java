package com.github.sokyranthedragon.mia.integrations.thaumcraft;

import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.integrations.futuremc.FutureMc;
import com.github.sokyranthedragon.mia.integrations.futuremc.IFutureMcIntegration;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import thaumcraft.api.blocks.BlocksTC;
import thaumcraft.api.items.ItemsTC;

import javax.annotation.Nonnull;

import static thedarkcolour.futuremc.block.BlockFurnaceAdvanced.Recipes.blastFurnaceRecipe;
import static thedarkcolour.futuremc.recipe.StonecutterRecipes.addOrCreateRecipe;

class FutureMcThaumcraftIntegration implements IFutureMcIntegration
{
    @Override
    public void addRecipes()
    {
        blastFurnaceRecipe(new ItemStack(BlocksTC.oreAmber), new ItemStack(ItemsTC.amber));
        blastFurnaceRecipe(new ItemStack(BlocksTC.oreCinnabar), new ItemStack(ItemsTC.quicksilver));
        blastFurnaceRecipe(new ItemStack(BlocksTC.oreQuartz), new ItemStack(Items.QUARTZ));
        
        blastFurnaceRecipe(new ItemStack(ItemsTC.clusters, 1, 0), new ItemStack(Items.IRON_INGOT, 2));
        blastFurnaceRecipe(new ItemStack(ItemsTC.clusters, 1, 1), new ItemStack(Items.GOLD_INGOT, 2));
        FutureMc.oreDictBlastFurnaceRecipe(ItemsTC.clusters, 2, 2, "ingotCopper");
        FutureMc.oreDictBlastFurnaceRecipe(ItemsTC.clusters, 3, 2, "ingotTin");
        FutureMc.oreDictBlastFurnaceRecipe(ItemsTC.clusters, 4, 2, "ingotSilver");
        FutureMc.oreDictBlastFurnaceRecipe(ItemsTC.clusters, 5, 2, "ingotLead");
        blastFurnaceRecipe(new ItemStack(ItemsTC.clusters, 1, 6), new ItemStack(ItemsTC.quicksilver, 2));
        blastFurnaceRecipe(new ItemStack(ItemsTC.clusters, 1,  7), new ItemStack(Items.QUARTZ, 2));
        
        addOrCreateRecipe(new ItemStack(BlocksTC.stoneArcane),
                new ItemStack(BlocksTC.stoneArcaneBrick),
                new ItemStack(BlocksTC.stairsArcane),
                new ItemStack(BlocksTC.slabArcaneStone, 2),
                new ItemStack(BlocksTC.stairsArcaneBrick),
                new ItemStack(BlocksTC.slabArcaneBrick, 2));
        addOrCreateRecipe(new ItemStack(BlocksTC.stoneArcaneBrick),
                new ItemStack(BlocksTC.stairsArcaneBrick),
                new ItemStack(BlocksTC.slabArcaneBrick, 2));
        addOrCreateRecipe(new ItemStack(BlocksTC.stoneAncient),
                new ItemStack(BlocksTC.stairsAncient),
                new ItemStack(BlocksTC.slabAncient, 2));
        addOrCreateRecipe(new ItemStack(BlocksTC.stoneEldritchTile),
                new ItemStack(BlocksTC.slabEldritch, 2));
    }
    
    @Override
    @Nonnull
    public ModIds getModId()
    {
        return ModIds.THAUMCRAFT;
    }
}
