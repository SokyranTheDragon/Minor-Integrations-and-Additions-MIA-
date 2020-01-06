package com.github.exploder1531.mia.integrations.dungeontactics;

import com.github.exploder1531.mia.integrations.ModIds;
import com.github.exploder1531.mia.integrations.futuremc.IFutureMcIntegration;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.oredict.OreDictionary;
import pegbeard.dungeontactics.handlers.DTBlocks;
import pegbeard.dungeontactics.handlers.DTItems;

import javax.annotation.Nonnull;

import static com.github.exploder1531.mia.integrations.futuremc.FutureMc.addFoodRecipe;
import static thedarkcolour.futuremc.block.BlockFurnaceAdvanced.Recipes.blastFurnaceRecipe;

class FutureMcDungeonTacticsIntegration implements IFutureMcIntegration
{
    @Override
    public void addRecipes()
    {
        // Same order as mod IDs
        blastFurnaceRecipe(new ItemStack(DTBlocks.ORE_SILVER), new ItemStack(DTItems.INGOT_SILVER));
        blastFurnaceRecipe(new ItemStack(DTBlocks.ORE_MITHRIL), new ItemStack(DTItems.INGOT_MITHRIL));
        blastFurnaceRecipe(new ItemStack(DTBlocks.NETHER_GOLD), new ItemStack(Items.GOLD_INGOT));
        blastFurnaceRecipe(new ItemStack(DTBlocks.STONE_QUARTZ), new ItemStack(Items.QUARTZ));
        blastFurnaceRecipe(new ItemStack(DTBlocks.END_DIAMOND), new ItemStack(Items.DIAMOND));
        blastFurnaceRecipe(new ItemStack(DTBlocks.END_LAPIS), new ItemStack(Items.DYE, 1, 4));
        
        // Same order as mod IDs
        blastFurnaceRecipe(new ItemStack(DTItems.CLUSTER_IRON), new ItemStack(Items.IRON_INGOT));
        blastFurnaceRecipe(new ItemStack(DTItems.CLUSTER_SILVER), new ItemStack(DTItems.INGOT_SILVER));
        blastFurnaceRecipe(new ItemStack(DTItems.CLUSTER_GOLD), new ItemStack(Items.GOLD_INGOT));
        blastFurnaceRecipe(new ItemStack(DTItems.CLUSTER_MITHRIL), new ItemStack(DTItems.INGOT_MITHRIL));
        oreDictBlastFurnace(DTItems.CLUSTER_TIN, "ingotTin");
        oreDictBlastFurnace(DTItems.CLUSTER_COPPER, "ingotCopper");
        oreDictBlastFurnace(DTItems.CLUSTER_ALUMINIUM, "ingotAluminium");
        oreDictBlastFurnace(DTItems.CLUSTER_NICKEL, "ingotNickel");
        oreDictBlastFurnace(DTItems.CLUSTER_LEAD, "ingotLead");
        oreDictBlastFurnace(DTItems.CLUSTER_PLATINUM, "ingotPlatinum");
        oreDictBlastFurnace(DTItems.CLUSTER_TUNGSTEN, "ingotTungsten");
        oreDictBlastFurnace(DTItems.CLUSTER_TITANIUM, "ingotTitanium");
        oreDictBlastFurnace(DTItems.CLUSTER_OSMIUM, "ingotOsmium");
        
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
    
    private static void oreDictBlastFurnace(Item input, String output)
    {
        NonNullList<ItemStack> ores = OreDictionary.getOres(output);
        if (!ores.isEmpty())
            blastFurnaceRecipe(new ItemStack(input), ores.get(0));
    }
    
    @Override
    @Nonnull
    public ModIds getModId()
    {
        return ModIds.DUNGEON_TACTICS;
    }
}
