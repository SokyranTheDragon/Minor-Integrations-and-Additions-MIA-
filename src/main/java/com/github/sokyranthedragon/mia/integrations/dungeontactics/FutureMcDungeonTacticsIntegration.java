package com.github.sokyranthedragon.mia.integrations.dungeontactics;

import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.integrations.futuremc.IFutureMcIntegration;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import pegbeard.dungeontactics.handlers.DTBlocks;
import pegbeard.dungeontactics.handlers.DTItems;

import javax.annotation.Nonnull;

import static com.github.sokyranthedragon.mia.integrations.futuremc.FutureMc.*;

class FutureMcDungeonTacticsIntegration implements IFutureMcIntegration
{
    @Override
    public void addRecipes()
    {
        // Same order as mod IDs
        addBlastFurnaceRecipe(new ItemStack(DTBlocks.ORE_SILVER), new ItemStack(DTItems.INGOT_SILVER));
        addBlastFurnaceRecipe(new ItemStack(DTBlocks.ORE_MITHRIL), new ItemStack(DTItems.INGOT_MITHRIL));
        addBlastFurnaceRecipe(new ItemStack(DTBlocks.NETHER_GOLD), new ItemStack(Items.GOLD_INGOT));
        addBlastFurnaceRecipe(new ItemStack(DTBlocks.STONE_QUARTZ), new ItemStack(Items.QUARTZ));
        addBlastFurnaceRecipe(new ItemStack(DTBlocks.END_DIAMOND), new ItemStack(Items.DIAMOND));
        addBlastFurnaceRecipe(new ItemStack(DTBlocks.END_LAPIS), new ItemStack(Items.DYE, 1, 4));
        
        // Same order as mod IDs
        addBlastFurnaceRecipe(new ItemStack(DTItems.CLUSTER_IRON), new ItemStack(Items.IRON_INGOT));
        addBlastFurnaceRecipe(new ItemStack(DTItems.CLUSTER_SILVER), new ItemStack(DTItems.INGOT_SILVER));
        addBlastFurnaceRecipe(new ItemStack(DTItems.CLUSTER_GOLD), new ItemStack(Items.GOLD_INGOT));
        addBlastFurnaceRecipe(new ItemStack(DTItems.CLUSTER_MITHRIL), new ItemStack(DTItems.INGOT_MITHRIL));
        oreDictBlastFurnaceRecipe(DTItems.CLUSTER_TIN, "ingotTin");
        oreDictBlastFurnaceRecipe(DTItems.CLUSTER_COPPER, "ingotCopper");
        oreDictBlastFurnaceRecipe(DTItems.CLUSTER_ALUMINIUM, "ingotAluminium", "ingotAluminum");
        oreDictBlastFurnaceRecipe(DTItems.CLUSTER_NICKEL, "ingotNickel");
        oreDictBlastFurnaceRecipe(DTItems.CLUSTER_LEAD, "ingotLead");
        oreDictBlastFurnaceRecipe(DTItems.CLUSTER_PLATINUM, "ingotPlatinum");
        oreDictBlastFurnaceRecipe(DTItems.CLUSTER_TUNGSTEN, "ingotTungsten");
        oreDictBlastFurnaceRecipe(DTItems.CLUSTER_TITANIUM, "ingotTitanium");
        oreDictBlastFurnaceRecipe(DTItems.CLUSTER_OSMIUM, "ingotOsmium");
        
        addFoodRecipe(new ItemStack(Items.BREAD), new ItemStack(DTItems.TOAST));
        addFoodRecipe(new ItemStack(DTItems.BREADSLICE), new ItemStack(DTItems.TOASTSLICE), 120);
        
        // Same order as mod IDs
        addFoodRecipe(new ItemStack(DTItems.FISH_SWIFT), new ItemStack(DTItems.FISH_SWIFT_COOKED), 1200);
        addFoodRecipe(new ItemStack(DTItems.FISH_FLYING), new ItemStack(DTItems.FISH_FLYING_COOKED), 1200);
        addFoodRecipe(new ItemStack(DTItems.FISH_LAVA), new ItemStack(DTItems.FISH_LAVA_COOKED), 1200);
        addFoodRecipe(new ItemStack(DTItems.FISH_MUSCLE), new ItemStack(DTItems.FISH_MUSCLE_COOKED), 1200);
        addFoodRecipe(new ItemStack(DTItems.FISH_LUNG), new ItemStack(DTItems.FISH_LUNG_COOKED), 1200);
        addFoodRecipe(new ItemStack(DTItems.FISH_OBSIDIAN), new ItemStack(DTItems.FISH_OBSIDIAN_COOKED), 1200);
        addFoodRecipe(new ItemStack(DTItems.FISH_TUNNEL), new ItemStack(DTItems.FISH_TUNNEL_COOKED), 1200);
    }
    
    @Override
    @Nonnull
    public ModIds getModId()
    {
        return ModIds.DUNGEON_TACTICS;
    }
}
