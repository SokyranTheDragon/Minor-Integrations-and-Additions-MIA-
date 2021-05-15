package com.github.sokyranthedragon.mia.integrations.futuremc;

import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.integrations.harvestcraft.HarvestcraftRecipes;
import com.github.sokyranthedragon.mia.integrations.harvestcraft.IHarvestcraftIntegration;
import com.pam.harvestcraft.item.ItemRegistry;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.item.ItemStack;
import thedarkcolour.futuremc.registry.FBlocks;
import thedarkcolour.futuremc.registry.FItems;

import static com.pam.harvestcraft.HarvestCraft.config;

@MethodsReturnNonnullByDefault
class HarvestcraftFutureMcIntegration implements IHarvestcraftIntegration
{
    @Override
    public void addRecipes()
    {
        ItemStack seedCropCurrency = HarvestcraftRecipes.getCurrency(config.marketcurrencySeeds, HarvestcraftRecipes.CurrencyType.SEEDS);
        
        HarvestcraftRecipes.addPressingRecipe(new ItemStack(FItems.INSTANCE.getHONEYCOMB()), new ItemStack(ItemRegistry.honeyItem), new ItemStack(ItemRegistry.beeswaxItem));
        HarvestcraftRecipes.addPressingRecipe(new ItemStack(FItems.INSTANCE.getSWEET_BERRIES()), new ItemStack(ItemRegistry.fruitbaitItem), new ItemStack(ItemRegistry.fruitbaitItem));
        
        if (config.marketsellSeeds)
            HarvestcraftRecipes.addMarketRecipe(new ItemStack(FItems.INSTANCE.getSWEET_BERRIES()), seedCropCurrency, config.marketseedPrice);
        if (config.marketselltropicalSaplings)
            HarvestcraftRecipes.addMarketRecipe(new ItemStack(FItems.INSTANCE.getBAMBOO()), HarvestcraftRecipes.getCurrency(config.marketcurrencytropicalSaplings, HarvestcraftRecipes.CurrencyType.SAPLING), config.marketsaplingPrice);
        
        if (config.shippingbinbuyCrops)
            HarvestcraftRecipes.addShippingRecipe(new ItemStack(FItems.INSTANCE.getSWEET_BERRIES(), config.shippingbincropPrice), seedCropCurrency);
        if (config.shippingbinbuyBees)
        {
            ItemStack beeCurrency = HarvestcraftRecipes.getCurrency(config.marketcurrencyBees, HarvestcraftRecipes.CurrencyType.BEES);
            HarvestcraftRecipes.addShippingRecipe(new ItemStack(FItems.INSTANCE.getHONEYCOMB(), config.shippingbinbeesPrice), beeCurrency);
            HarvestcraftRecipes.addShippingRecipe(new ItemStack(FBlocks.INSTANCE.getHONEY_BLOCK(), Math.min(config.shippingbinbeesPrice / 4, 1)), beeCurrency);
        }
        
        // TODO: add panda/bee egg purchases, as well as config for stuff like price, etc.
    }
    
    @Override
    public ModIds getModId()
    {
        return ModIds.FUTURE_MC;
    }
}
