package com.github.sokyranthedragon.mia.integrations.aether_continuation;

import com.gildedgames.the_aether.addon.items.ItemsAetherAddon;
import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.integrations.futuremc.IFutureMcIntegration;
import com.github.sokyranthedragon.mia.utilities.annotations.FieldsAreNonnullByDefault;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.item.ItemStack;

import static com.github.sokyranthedragon.mia.integrations.futuremc.FutureMc.addFoodRecipe;

@FieldsAreNonnullByDefault
@MethodsReturnNonnullByDefault
class FutureMcAetherContinuationIntegration implements IFutureMcIntegration
{
    @Override
    public void addRecipes()
    {
        addFoodRecipe(new ItemStack(ItemsAetherAddon.cockatrice), new ItemStack(ItemsAetherAddon.burnt_cockatrice));
        addFoodRecipe(new ItemStack(ItemsAetherAddon.enchanted_cockatrice), new ItemStack(ItemsAetherAddon.cooked_enchanted_cockatrice));
    }

    @Override
    public ModIds getModId()
    {
        return ModIds.AETHER_CONTINUATION;
    }
}
