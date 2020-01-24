package com.github.sokyranthedragon.mia.integrations.xu2;

import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.integrations.futuremc.IFutureMcIntegration;
import com.rwtema.extrautils2.backend.entries.XU2Entries;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

import static thedarkcolour.futuremc.recipe.StonecutterRecipes.addOrCreateRecipe;

class FutureMcExtraUtilsIntegration implements IFutureMcIntegration
{
    @Override
    public void addRecipes()
    {
        addOrCreateRecipe(new ItemStack(Blocks.STONE),
                XU2Entries.decorativeSolid.newStackMeta(0),
                XU2Entries.decorativeSolid.newStackMeta(1),
                XU2Entries.decorativeSolid.newStackMeta(2),
                XU2Entries.decorativeSolid.newStackMeta(5));
        addOrCreateRecipe(new ItemStack(Blocks.STONEBRICK),
                XU2Entries.decorativeSolid.newStackMeta(0),
                XU2Entries.decorativeSolid.newStackMeta(1),
                XU2Entries.decorativeSolid.newStackMeta(2),
                XU2Entries.decorativeSolid.newStackMeta(5));
        addOrCreateRecipe(XU2Entries.decorativeSolid.newStackMeta(0),
                XU2Entries.decorativeSolid.newStackMeta(1),
                XU2Entries.decorativeSolid.newStackMeta(5));
        addOrCreateRecipe(XU2Entries.decorativeSolid.newStackMeta(2),
                XU2Entries.decorativeSolid.newStackMeta(5));
    }
    
    @Override
    @Nonnull
    public ModIds getModId()
    {
        return ModIds.EXTRA_UTILITIES;
    }
}
