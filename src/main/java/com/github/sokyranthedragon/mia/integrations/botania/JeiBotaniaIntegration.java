package com.github.sokyranthedragon.mia.integrations.botania;

import com.github.sokyranthedragon.api.botania.MiaBotaniaAPI;
import com.github.sokyranthedragon.mia.config.BotaniaConfiguration;
import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.integrations.jei.IJeiIntegration;
import com.github.sokyranthedragon.mia.integrations.jei.MiaJeiPlugin;
import com.github.sokyranthedragon.mia.integrations.jei.categories.orechidvacuam.OrechidVacuamRecipeCategory;
import com.github.sokyranthedragon.mia.integrations.jei.categories.orechidvacuam.OrechidVacuamRecipeWrapper;
import mcp.MethodsReturnNonnullByDefault;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import net.minecraft.item.ItemStack;
import vazkii.botania.client.integration.jei.JEIBotaniaPlugin;
import vazkii.botania.common.block.ModBlocks;
import vazkii.botania.common.item.block.ItemBlockSpecialFlower;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Collection;
import java.util.stream.Collectors;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
class JeiBotaniaIntegration implements IJeiIntegration
{
    @Override
    public void register(IModRegistry registry, Collection<String> registeredCategories)
    {
        if (BotaniaConfiguration.botaniaAdditionsEnabled)
        {
            if (registeredCategories.add(MiaJeiPlugin.Categories.ORECHID_VACUAM))
            {
                registry.addRecipes(MiaBotaniaAPI.oreWeightsEnd.entrySet().stream().filter(e ->
                                JEIBotaniaPlugin.doesOreExist(e.getKey())).map(OrechidVacuamRecipeWrapper::new).sorted().collect(Collectors.toList()),
                        MiaJeiPlugin.Categories.ORECHID_VACUAM);
            }
        
            registry.addRecipeCatalyst(ItemBlockSpecialFlower.ofType("orechidVacuam"), MiaJeiPlugin.Categories.ORECHID_VACUAM);
            registry.addRecipeCatalyst(ItemBlockSpecialFlower.ofType(new ItemStack(ModBlocks.floatingSpecialFlower), "orechidVacuam"), MiaJeiPlugin.Categories.ORECHID_VACUAM);
        }
    }
    
    @Override
    public void registerCategories(IRecipeCategoryRegistration registry, Collection<String> registeredCategories)
    {
        if (BotaniaConfiguration.botaniaAdditionsEnabled && registeredCategories.add(MiaJeiPlugin.Categories.ORECHID_VACUAM))
            registry.addRecipeCategories(new OrechidVacuamRecipeCategory(registry.getJeiHelpers().getGuiHelper()));
    }
    
    @Override
    public ModIds getModId()
    {
        return ModIds.BOTANIA;
    }
}
