package com.github.sokyranthedragon.mia.integrations.harvestcraft;

import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.integrations.futuremc.FutureMc;
import com.github.sokyranthedragon.mia.integrations.futuremc.IFutureMcIntegration;
import com.pam.harvestcraft.HarvestCraft;
import com.pam.harvestcraft.item.ItemRegistry;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

class FutureMcHarvestcraftIntegration implements IFutureMcIntegration
{
    @Override
    public void addRecipes()
    {
        if (HarvestCraft.config.smeltsquidintocookedFish)
            FutureMc.addFoodRecipe(new ItemStack(ItemRegistry.calamarirawItem), new ItemStack(Items.COOKED_FISH));
        else
            FutureMc.addFoodRecipe(new ItemStack(ItemRegistry.calamarirawItem), new ItemStack(ItemRegistry.calamaricookedItem));
        FutureMc.addFoodRecipe(new ItemStack(ItemRegistry.clamrawItem), new ItemStack(ItemRegistry.clamcookedItem));
        FutureMc.addFoodRecipe(new ItemStack(ItemRegistry.crabrawItem), new ItemStack(ItemRegistry.crabcookedItem));
        FutureMc.addFoodRecipe(new ItemStack(ItemRegistry.crayfishrawItem), new ItemStack(ItemRegistry.crayfishcookedItem));
        FutureMc.addFoodRecipe(new ItemStack(ItemRegistry.frograwItem), new ItemStack(ItemRegistry.frogcookedItem));
        FutureMc.addFoodRecipe(new ItemStack(ItemRegistry.scalloprawItem), new ItemStack(ItemRegistry.scallopcookedItem));
        FutureMc.addFoodRecipe(new ItemStack(ItemRegistry.shrimprawItem), new ItemStack(ItemRegistry.shrimpcookedItem));
        FutureMc.addFoodRecipe(new ItemStack(ItemRegistry.snailrawItem), new ItemStack(ItemRegistry.snailcookedItem));
        FutureMc.addFoodRecipe(new ItemStack(ItemRegistry.turtlerawItem), new ItemStack(ItemRegistry.turtlecookedItem));
        FutureMc.addFoodRecipe(new ItemStack(ItemRegistry.oysterrawItem), new ItemStack(ItemRegistry.oystercookedItem));
        FutureMc.addFoodRecipe(new ItemStack(ItemRegistry.turkeyrawItem), new ItemStack(ItemRegistry.turkeycookedItem));
        FutureMc.addFoodRecipe(new ItemStack(ItemRegistry.venisonrawItem), new ItemStack(ItemRegistry.venisoncookedItem));
        FutureMc.addFoodRecipe(new ItemStack(ItemRegistry.musselrawItem), new ItemStack(ItemRegistry.musselcookedItem));
        FutureMc.addFoodRecipe(new ItemStack(ItemRegistry.duckrawItem), new ItemStack(ItemRegistry.duckcookedItem));
        FutureMc.addFoodRecipe(new ItemStack(ItemRegistry.rawtofeakItem), new ItemStack(ItemRegistry.cookedtofeakItem));
        FutureMc.addFoodRecipe(new ItemStack(ItemRegistry.rawtofaconItem), new ItemStack(ItemRegistry.cookedtofaconItem));
        FutureMc.addFoodRecipe(new ItemStack(ItemRegistry.rawtofishItem), new ItemStack(ItemRegistry.cookedtofishItem));
        FutureMc.addFoodRecipe(new ItemStack(ItemRegistry.rawtofeegItem), new ItemStack(ItemRegistry.cookedtofeegItem));
        FutureMc.addFoodRecipe(new ItemStack(ItemRegistry.rawtofuttonItem), new ItemStack(ItemRegistry.cookedtofuttonItem));
        FutureMc.addFoodRecipe(new ItemStack(ItemRegistry.rawtofickenItem), new ItemStack(ItemRegistry.cookedtofickenItem));
        FutureMc.addFoodRecipe(new ItemStack(ItemRegistry.rawtofabbitItem), new ItemStack(ItemRegistry.cookedtofabbitItem));
        FutureMc.addFoodRecipe(new ItemStack(ItemRegistry.rawtofurkeyItem), new ItemStack(ItemRegistry.cookedtofurkeyItem));
        FutureMc.addFoodRecipe(new ItemStack(ItemRegistry.rawtofenisonItem), new ItemStack(ItemRegistry.cookedtofenisonItem));
        FutureMc.addFoodRecipe(new ItemStack(ItemRegistry.rawtofuduckItem), new ItemStack(ItemRegistry.cookedtofuduckItem));
        FutureMc.addFoodRecipe(new ItemStack(ItemRegistry.grubItem), new ItemStack(ItemRegistry.cookedgrubItem));
        
        FutureMc.addFoodRecipe(new ItemStack(ItemRegistry.anchovyrawItem), new ItemStack(Items.COOKED_FISH));
        FutureMc.addFoodRecipe(new ItemStack(ItemRegistry.bassrawItem), new ItemStack(Items.COOKED_FISH));
        FutureMc.addFoodRecipe(new ItemStack(ItemRegistry.carprawItem), new ItemStack(Items.COOKED_FISH));
        FutureMc.addFoodRecipe(new ItemStack(ItemRegistry.catfishrawItem), new ItemStack(Items.COOKED_FISH));
        FutureMc.addFoodRecipe(new ItemStack(ItemRegistry.charrrawItem), new ItemStack(Items.COOKED_FISH));
        FutureMc.addFoodRecipe(new ItemStack(ItemRegistry.eelrawItem), new ItemStack(Items.COOKED_FISH));
        FutureMc.addFoodRecipe(new ItemStack(ItemRegistry.grouperrawItem), new ItemStack(Items.COOKED_FISH));
        FutureMc.addFoodRecipe(new ItemStack(ItemRegistry.herringrawItem), new ItemStack(Items.COOKED_FISH));
        FutureMc.addFoodRecipe(new ItemStack(ItemRegistry.mudfishrawItem), new ItemStack(Items.COOKED_FISH));
        FutureMc.addFoodRecipe(new ItemStack(ItemRegistry.perchrawItem), new ItemStack(Items.COOKED_FISH));
        FutureMc.addFoodRecipe(new ItemStack(ItemRegistry.snapperrawItem), new ItemStack(Items.COOKED_FISH));
        FutureMc.addFoodRecipe(new ItemStack(ItemRegistry.tilapiarawItem), new ItemStack(Items.COOKED_FISH));
        FutureMc.addFoodRecipe(new ItemStack(ItemRegistry.troutrawItem), new ItemStack(Items.COOKED_FISH));
        FutureMc.addFoodRecipe(new ItemStack(ItemRegistry.tunarawItem), new ItemStack(Items.COOKED_FISH));
        FutureMc.addFoodRecipe(new ItemStack(ItemRegistry.walleyerawItem), new ItemStack(Items.COOKED_FISH));
        FutureMc.addFoodRecipe(new ItemStack(ItemRegistry.sardinerawItem), new ItemStack(Items.COOKED_FISH));
        FutureMc.addFoodRecipe(new ItemStack(ItemRegistry.greenheartfishItem), new ItemStack(Items.COOKED_FISH));
    }
    
    @Override
    @Nonnull
    public ModIds getModId()
    {
        return ModIds.HARVESTCRAFT;
    }
}
