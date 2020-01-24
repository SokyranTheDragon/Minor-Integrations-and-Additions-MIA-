package com.github.sokyranthedragon.mia.integrations.futuremc;

import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.integrations.harvestcraft.HarvestcraftRecipes;
import com.github.sokyranthedragon.mia.integrations.harvestcraft.IHarvestcraftIntegration;
import com.pam.harvestcraft.item.ItemRegistry;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.item.ItemStack;
import thedarkcolour.futuremc.init.Init;

import static com.pam.harvestcraft.HarvestCraft.config;

@MethodsReturnNonnullByDefault
class HarvestcraftFutureMcIntegration implements IHarvestcraftIntegration
{
    @Override
    public void addRecipes()
    {
        ItemStack seedCropCurrency = HarvestcraftRecipes.getCurrency(config.marketcurrencySeeds, HarvestcraftRecipes.CurrencyType.SEEDS);
        
        HarvestcraftRecipes.addPressingRecipe(new ItemStack(Init.HONEY_COMB), new ItemStack(ItemRegistry.honeyItem), new ItemStack(ItemRegistry.beeswaxItem));
        HarvestcraftRecipes.addPressingRecipe(new ItemStack(Init.SWEET_BERRY), new ItemStack(ItemRegistry.fruitbaitItem), new ItemStack(ItemRegistry.fruitbaitItem));
        
        if (config.marketsellSeeds)
            HarvestcraftRecipes.addMarketRecipe(new ItemStack(Init.SWEET_BERRY), seedCropCurrency, config.marketseedPrice);
        if (config.marketselltropicalSaplings)
            HarvestcraftRecipes.addMarketRecipe(new ItemStack(Init.BAMBOO_ITEM), HarvestcraftRecipes.getCurrency(config.marketcurrencytropicalSaplings, HarvestcraftRecipes.CurrencyType.SAPLING), config.marketsaplingPrice);
        
        if (config.shippingbinbuyCrops)
            HarvestcraftRecipes.addShippingRecipe(new ItemStack(Init.SWEET_BERRY, config.shippingbincropPrice), seedCropCurrency);
        if (config.shippingbinbuyBees)
        {
            ItemStack beeCurrency = HarvestcraftRecipes.getCurrency(config.marketcurrencyBees, HarvestcraftRecipes.CurrencyType.BEES);
            HarvestcraftRecipes.addShippingRecipe(new ItemStack(Init.HONEY_COMB, config.shippingbinbeesPrice), beeCurrency);
            HarvestcraftRecipes.addShippingRecipe(new ItemStack(Init.HONEY_BLOCK, Math.min(config.shippingbinbeesPrice / 4, 1)), beeCurrency);
        }
        
        // TODO: add panda/bee egg purchases, as well as config for stuff like price, etc.
    }
    
    @Override
    public ModIds getModId()
    {
        return ModIds.FUTURE_MC;
    }
}
