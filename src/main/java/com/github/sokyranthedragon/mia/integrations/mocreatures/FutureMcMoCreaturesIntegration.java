package com.github.sokyranthedragon.mia.integrations.mocreatures;

import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.integrations.futuremc.IFutureMcIntegration;
import drzhark.mocreatures.init.MoCItems;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import javax.annotation.Nonnull;

import static com.github.sokyranthedragon.mia.integrations.futuremc.FutureMc.addFoodRecipe;

class FutureMcMoCreaturesIntegration implements IFutureMcIntegration
{
    @Override
    public void addRecipes()
    {
        addFoodRecipe(new ItemStack(MoCItems.mocegg, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(MoCItems.omelet));
        addFoodRecipe(new ItemStack(MoCItems.ostrichraw), new ItemStack(MoCItems.ostrichcooked));
        addFoodRecipe(new ItemStack(MoCItems.rawTurkey), new ItemStack(MoCItems.cookedTurkey));
        addFoodRecipe(new ItemStack(MoCItems.ratRaw), new ItemStack(MoCItems.ratCooked));
        addFoodRecipe(new ItemStack(MoCItems.crabraw), new ItemStack(MoCItems.crabcooked));
    }
    
    @Override
    @Nonnull
    public ModIds getModId()
    {
        return ModIds.MO_CREATURES;
    }
}
