package com.github.sokyranthedragon.mia.integrations.harvestcraft;

import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.integrations.futuremc.FutureMc;
import com.github.sokyranthedragon.mia.integrations.futuremc.IFutureMcIntegration;
import com.pam.harvestcraft.HarvestCraft;
import com.pam.harvestcraft.item.ItemRegistry;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

import static com.github.sokyranthedragon.mia.integrations.futuremc.FutureMc.*;

class FutureMcHarvestcraftIntegration implements IFutureMcIntegration
{
    @Override
    public void addRecipes()
    {
        if (HarvestCraft.config.smeltsquidintocookedFish)
            addSmokerRecipe(new ItemStack(ItemRegistry.calamarirawItem), new ItemStack(Items.COOKED_FISH));
        else
            addSmokerRecipe(new ItemStack(ItemRegistry.calamarirawItem), new ItemStack(ItemRegistry.calamaricookedItem));
        addSmokerRecipe(new ItemStack(ItemRegistry.clamrawItem), new ItemStack(ItemRegistry.clamcookedItem));
        addSmokerRecipe(new ItemStack(ItemRegistry.crabrawItem), new ItemStack(ItemRegistry.crabcookedItem));
        addSmokerRecipe(new ItemStack(ItemRegistry.crayfishrawItem), new ItemStack(ItemRegistry.crayfishcookedItem));
        addSmokerRecipe(new ItemStack(ItemRegistry.frograwItem), new ItemStack(ItemRegistry.frogcookedItem));
        addSmokerRecipe(new ItemStack(ItemRegistry.scalloprawItem), new ItemStack(ItemRegistry.scallopcookedItem));
        addSmokerRecipe(new ItemStack(ItemRegistry.shrimprawItem), new ItemStack(ItemRegistry.shrimpcookedItem));
        addSmokerRecipe(new ItemStack(ItemRegistry.snailrawItem), new ItemStack(ItemRegistry.snailcookedItem));
        addSmokerRecipe(new ItemStack(ItemRegistry.turtlerawItem), new ItemStack(ItemRegistry.turtlecookedItem));
        addSmokerRecipe(new ItemStack(ItemRegistry.oysterrawItem), new ItemStack(ItemRegistry.oystercookedItem));
        addSmokerRecipe(new ItemStack(ItemRegistry.turkeyrawItem), new ItemStack(ItemRegistry.turkeycookedItem));
        addSmokerRecipe(new ItemStack(ItemRegistry.venisonrawItem), new ItemStack(ItemRegistry.venisoncookedItem));
        addSmokerRecipe(new ItemStack(ItemRegistry.musselrawItem), new ItemStack(ItemRegistry.musselcookedItem));
        addSmokerRecipe(new ItemStack(ItemRegistry.duckrawItem), new ItemStack(ItemRegistry.duckcookedItem));
        addSmokerRecipe(new ItemStack(ItemRegistry.rawtofeakItem), new ItemStack(ItemRegistry.cookedtofeakItem));
        addSmokerRecipe(new ItemStack(ItemRegistry.rawtofaconItem), new ItemStack(ItemRegistry.cookedtofaconItem));
        addSmokerRecipe(new ItemStack(ItemRegistry.rawtofishItem), new ItemStack(ItemRegistry.cookedtofishItem));
        addSmokerRecipe(new ItemStack(ItemRegistry.rawtofeegItem), new ItemStack(ItemRegistry.cookedtofeegItem));
        addSmokerRecipe(new ItemStack(ItemRegistry.rawtofuttonItem), new ItemStack(ItemRegistry.cookedtofuttonItem));
        addSmokerRecipe(new ItemStack(ItemRegistry.rawtofickenItem), new ItemStack(ItemRegistry.cookedtofickenItem));
        addSmokerRecipe(new ItemStack(ItemRegistry.rawtofabbitItem), new ItemStack(ItemRegistry.cookedtofabbitItem));
        addSmokerRecipe(new ItemStack(ItemRegistry.rawtofurkeyItem), new ItemStack(ItemRegistry.cookedtofurkeyItem));
        addSmokerRecipe(new ItemStack(ItemRegistry.rawtofenisonItem), new ItemStack(ItemRegistry.cookedtofenisonItem));
        addSmokerRecipe(new ItemStack(ItemRegistry.rawtofuduckItem), new ItemStack(ItemRegistry.cookedtofuduckItem));
        addSmokerRecipe(new ItemStack(ItemRegistry.grubItem), new ItemStack(ItemRegistry.cookedgrubItem));
        
        addSmokerRecipe(new ItemStack(ItemRegistry.anchovyrawItem), new ItemStack(Items.COOKED_FISH));
        addSmokerRecipe(new ItemStack(ItemRegistry.bassrawItem), new ItemStack(Items.COOKED_FISH));
        addSmokerRecipe(new ItemStack(ItemRegistry.carprawItem), new ItemStack(Items.COOKED_FISH));
        addSmokerRecipe(new ItemStack(ItemRegistry.catfishrawItem), new ItemStack(Items.COOKED_FISH));
        addSmokerRecipe(new ItemStack(ItemRegistry.charrrawItem), new ItemStack(Items.COOKED_FISH));
        addSmokerRecipe(new ItemStack(ItemRegistry.eelrawItem), new ItemStack(Items.COOKED_FISH));
        addSmokerRecipe(new ItemStack(ItemRegistry.grouperrawItem), new ItemStack(Items.COOKED_FISH));
        addSmokerRecipe(new ItemStack(ItemRegistry.herringrawItem), new ItemStack(Items.COOKED_FISH));
        addSmokerRecipe(new ItemStack(ItemRegistry.mudfishrawItem), new ItemStack(Items.COOKED_FISH));
        addSmokerRecipe(new ItemStack(ItemRegistry.perchrawItem), new ItemStack(Items.COOKED_FISH));
        addSmokerRecipe(new ItemStack(ItemRegistry.snapperrawItem), new ItemStack(Items.COOKED_FISH));
        addSmokerRecipe(new ItemStack(ItemRegistry.tilapiarawItem), new ItemStack(Items.COOKED_FISH));
        addSmokerRecipe(new ItemStack(ItemRegistry.troutrawItem), new ItemStack(Items.COOKED_FISH));
        addSmokerRecipe(new ItemStack(ItemRegistry.tunarawItem), new ItemStack(Items.COOKED_FISH));
        addSmokerRecipe(new ItemStack(ItemRegistry.walleyerawItem), new ItemStack(Items.COOKED_FISH));
        addSmokerRecipe(new ItemStack(ItemRegistry.sardinerawItem), new ItemStack(Items.COOKED_FISH));
        addSmokerRecipe(new ItemStack(ItemRegistry.greenheartfishItem), new ItemStack(Items.COOKED_FISH));
    }
    
    @Override
    @Nonnull
    public ModIds getModId()
    {
        return ModIds.HARVESTCRAFT;
    }
}
