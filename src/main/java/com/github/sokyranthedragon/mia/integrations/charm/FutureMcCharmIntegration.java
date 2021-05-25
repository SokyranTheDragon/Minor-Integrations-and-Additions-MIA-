package com.github.sokyranthedragon.mia.integrations.charm;

import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.integrations.futuremc.FutureMc;
import com.github.sokyranthedragon.mia.integrations.futuremc.IFutureMcIntegration;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import svenhjol.charm.Charm;
import svenhjol.charm.world.feature.NetherGoldDeposits;

import static com.github.sokyranthedragon.mia.integrations.futuremc.FutureMc.addBlastFurnaceRecipe;

class FutureMcCharmIntegration implements IFutureMcIntegration
{
    @Override
    public void addRecipes()
    {
        if (Charm.hasFeature(NetherGoldDeposits.class))
            addBlastFurnaceRecipe(new ItemStack(NetherGoldDeposits.ore), new ItemStack(Items.GOLD_NUGGET, 3));
    }
    
    @NotNull
    @Override
    public ModIds getModId()
    {
        return ModIds.CHARM;
    }
}
