package com.github.sokyranthedragon.mia.integrations.natura;

import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.integrations.futuremc.IFutureMcIntegration;
import com.progwml6.natura.entities.NaturaEntities;
import com.progwml6.natura.shared.NaturaCommons;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.item.ItemStack;

import static com.github.sokyranthedragon.mia.integrations.futuremc.FutureMc.addFoodRecipe;
import static com.progwml6.natura.Natura.pulseManager;

@MethodsReturnNonnullByDefault
class FutureMcNaturaIntegration implements IFutureMcIntegration
{
    @Override
    public void addRecipes()
    {
        if (pulseManager.isPulseLoaded(NaturaEntities.PulseId) && pulseManager.isPulseLoaded(NaturaCommons.PulseId))
            addFoodRecipe(new ItemStack(NaturaCommons.edibles, 1, 0), new ItemStack(NaturaCommons.edibles, 1, 1));
    }
    
    @Override
    public ModIds getModId()
    {
        return ModIds.NATURA;
    }
}
