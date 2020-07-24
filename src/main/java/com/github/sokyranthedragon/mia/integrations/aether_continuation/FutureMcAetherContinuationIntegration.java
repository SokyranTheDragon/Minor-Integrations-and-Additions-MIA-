package com.github.sokyranthedragon.mia.integrations.aether_continuation;

import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.integrations.futuremc.FutureMc;
import com.github.sokyranthedragon.mia.integrations.futuremc.IFutureMcIntegration;
import com.legacy.aether.addon.items.ItemsAetherAddon;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.item.ItemStack;

@MethodsReturnNonnullByDefault
class FutureMcAetherContinuationIntegration implements IFutureMcIntegration
{
    @Override
    public void addRecipes()
    {
        FutureMc.addFoodRecipe(new ItemStack(ItemsAetherAddon.cockatrice), new ItemStack(ItemsAetherAddon.burnt_cockatrice));
        FutureMc.addFoodRecipe(new ItemStack(ItemsAetherAddon.enchanted_cockatrice), new ItemStack(ItemsAetherAddon.cooked_enchanted_cockatrice));
    }
    
    @Override
    public ModIds getModId()
    {
        return ModIds.AETHER_CONTINUATION;
    }
}
