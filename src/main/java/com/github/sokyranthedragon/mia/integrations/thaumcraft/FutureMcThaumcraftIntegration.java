package com.github.sokyranthedragon.mia.integrations.thaumcraft;

import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.integrations.futuremc.IFutureMcIntegration;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import thaumcraft.api.blocks.BlocksTC;
import thaumcraft.api.items.ItemsTC;

import javax.annotation.Nonnull;

import static com.github.sokyranthedragon.mia.integrations.futuremc.FutureMc.*;

class FutureMcThaumcraftIntegration implements IFutureMcIntegration
{
    @Override
    public void addRecipes()
    {
        addBlastFurnaceRecipe(new ItemStack(BlocksTC.oreAmber), new ItemStack(ItemsTC.amber));
        addBlastFurnaceRecipe(new ItemStack(BlocksTC.oreCinnabar), new ItemStack(ItemsTC.quicksilver));
        addBlastFurnaceRecipe(new ItemStack(BlocksTC.oreQuartz), new ItemStack(Items.QUARTZ));
        
        addBlastFurnaceRecipe(new ItemStack(ItemsTC.clusters, 1, 0), new ItemStack(Items.IRON_INGOT, 2));
        addBlastFurnaceRecipe(new ItemStack(ItemsTC.clusters, 1, 1), new ItemStack(Items.GOLD_INGOT, 2));
        oreDictBlastFurnaceRecipe(ItemsTC.clusters, 2, 2, "ingotCopper");
        oreDictBlastFurnaceRecipe(ItemsTC.clusters, 3, 2, "ingotTin");
        oreDictBlastFurnaceRecipe(ItemsTC.clusters, 4, 2, "ingotSilver");
        oreDictBlastFurnaceRecipe(ItemsTC.clusters, 5, 2, "ingotLead");
        addBlastFurnaceRecipe(new ItemStack(ItemsTC.clusters, 1, 6), new ItemStack(ItemsTC.quicksilver, 2));
        addBlastFurnaceRecipe(new ItemStack(ItemsTC.clusters, 1, 7), new ItemStack(Items.QUARTZ, 2));
        
        addOrCreateStonecutterRecipe(new ItemStack(BlocksTC.stoneArcane),
            new ItemStack(BlocksTC.stoneArcaneBrick),
            new ItemStack(BlocksTC.stairsArcane),
            new ItemStack(BlocksTC.slabArcaneStone, 2),
            new ItemStack(BlocksTC.stairsArcaneBrick),
            new ItemStack(BlocksTC.slabArcaneBrick, 2));
        addOrCreateStonecutterRecipe(new ItemStack(BlocksTC.stoneArcaneBrick),
            new ItemStack(BlocksTC.stairsArcaneBrick),
            new ItemStack(BlocksTC.slabArcaneBrick, 2));
        addOrCreateStonecutterRecipe(new ItemStack(BlocksTC.stoneAncient),
            new ItemStack(BlocksTC.stairsAncient),
            new ItemStack(BlocksTC.slabAncient, 2));
        addOrCreateStonecutterRecipe(new ItemStack(BlocksTC.stoneEldritchTile),
            new ItemStack(BlocksTC.slabEldritch, 2));
    }
    
    @Override
    @Nonnull
    public ModIds getModId()
    {
        return ModIds.THAUMCRAFT;
    }
}
