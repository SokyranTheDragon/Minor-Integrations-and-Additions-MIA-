package com.github.sokyranthedragon.mia.integrations.mocreatures;

import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.integrations.futuremc.IFutureMcIntegration;
import drzhark.mocreatures.init.MoCItems;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

import static com.github.sokyranthedragon.mia.integrations.futuremc.FutureMc.addCampfireRecipe;

class FutureMcMoCreaturesIntegration implements IFutureMcIntegration
{
    @Override
    public void addRecipes()
    {
        addCampfireRecipe(new ItemStack(MoCItems.crabraw), new ItemStack(MoCItems.crabcooked));
    }
    
    @Override
    @Nonnull
    public ModIds getModId()
    {
        return ModIds.MO_CREATURES;
    }
}
