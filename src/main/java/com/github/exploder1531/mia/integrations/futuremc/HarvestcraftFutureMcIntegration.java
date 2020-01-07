package com.github.exploder1531.mia.integrations.futuremc;

import com.github.exploder1531.mia.integrations.ModIds;
import com.github.exploder1531.mia.integrations.harvestcraft.IHarvestcraftIntegration;
import com.pam.harvestcraft.item.ItemRegistry;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.item.ItemStack;
import thedarkcolour.futuremc.init.Init;

import static com.github.exploder1531.mia.integrations.harvestcraft.HarvestcraftRecipes.*;
import static com.pam.harvestcraft.HarvestCraft.config;

@MethodsReturnNonnullByDefault
class HarvestcraftFutureMcIntegration implements IHarvestcraftIntegration
{
    @Override
    public void addRecipes()
    {
        ItemStack seedCropCurrency = getCurrency(config.marketcurrencySeeds, CurrencyType.SEEDS);
        
        addPressingRecipe(new ItemStack(Init.HONEY_COMB), new ItemStack(ItemRegistry.honeyItem), new ItemStack(ItemRegistry.beeswaxItem));
        addPressingRecipe(new ItemStack(Init.SWEET_BERRY), new ItemStack(ItemRegistry.fruitbaitItem), new ItemStack(ItemRegistry.fruitbaitItem));
        
        if (config.marketsellSeeds)
            addMarketRecipe(new ItemStack(Init.SWEET_BERRY), seedCropCurrency, config.marketseedPrice);
        if (config.marketselltropicalSaplings)
            addMarketRecipe(new ItemStack(Init.BAMBOO_ITEM), getCurrency(config.marketcurrencytropicalSaplings, CurrencyType.SAPLING), config.marketsaplingPrice);
        
        if (config.shippingbinbuyCrops)
            addShippingRecipe(new ItemStack(Init.SWEET_BERRY, config.shippingbincropPrice), seedCropCurrency);
        if (config.shippingbinbuyBees)
        {
            ItemStack beeCurrency = getCurrency(config.marketcurrencyBees, CurrencyType.BEES);
            addShippingRecipe(new ItemStack(Init.HONEY_COMB, config.shippingbinbeesPrice), beeCurrency);
            addShippingRecipe(new ItemStack(Init.HONEY_BLOCK, Math.min(config.shippingbinbeesPrice / 4, 1)), beeCurrency);
        }
        
        // TODO: add panda/bee egg purchases, as well as config for stuff like price, etc.
    }
    
    @Override
    public ModIds getModId()
    {
        return ModIds.FUTURE_MC;
    }
}
