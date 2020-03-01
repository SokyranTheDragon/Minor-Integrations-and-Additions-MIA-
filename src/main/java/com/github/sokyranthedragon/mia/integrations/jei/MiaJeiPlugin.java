package com.github.sokyranthedragon.mia.integrations.jei;

import com.github.sokyranthedragon.mia.config.JeiConfiguration;
import com.github.sokyranthedragon.mia.core.MiaBlocks;
import com.github.sokyranthedragon.mia.core.MiaItems;
import com.github.sokyranthedragon.mia.integrations.ModIds;
import mezz.jei.api.*;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;
import java.util.Collection;
import java.util.HashSet;

@JEIPlugin
public class MiaJeiPlugin implements IModPlugin
{
    private static IJeiRuntime jeiRuntime;
    static Jei jeiIntegration = null;
    
    @Override
    public void registerItemSubtypes(ISubtypeRegistry subtypeRegistry)
    {
        if (MiaItems.musicPlayer != null)
        {
            subtypeRegistry.registerSubtypeInterpreter(MiaItems.musicPlayer, new ISubtypeRegistry.ISubtypeInterpreter()
            {
                @Nonnull
                @Override
                public String apply(@Nonnull ItemStack itemStack)
                {
                    return "mia:music_player:0";
                }
            });
        }
    }
    
    @Override
    public void register(IModRegistry registry)
    {
        Collection<String> registeredCategories = new HashSet<>();
        
        for (IJeiIntegration integration : jeiIntegration.modIntegrations)
            integration.register(registry, registeredCategories);
        
        if (ModIds.HATCHERY.isLoaded && MiaBlocks.eggSorter != null)
            registry.addIngredientInfo(new ItemStack(MiaBlocks.eggSorter), VanillaTypes.ITEM, "mia.jei.info.egg_sorter");
        if (ModIds.ICE_AND_FIRE.isLoaded && MiaBlocks.pixieDustExtractor != null)
            registry.addIngredientInfo(new ItemStack(MiaBlocks.pixieDustExtractor), VanillaTypes.ITEM, "mia.jei.info.pixie_dust_extractor");
        if (ModIds.THAUMCRAFT.isLoaded && MiaBlocks.voidCreator != null)
            registry.addIngredientInfo(new ItemStack(MiaBlocks.voidCreator), VanillaTypes.ITEM, "mia.jei.info.void_creator");
    }
    
    @Override
    public void onRuntimeAvailable(IJeiRuntime jeiRuntime)
    {
        MiaJeiPlugin.jeiRuntime = jeiRuntime;
        
        if (!JeiConfiguration.enableLootBagCategory)
            hideCategories(Categories.LOOT_BAG);
        if (!JeiConfiguration.enableAlchemicalCauldronCategory)
            hideCategories(Categories.DUNGEON_TACTICS_CAULDRON);
        if (!JeiConfiguration.enableOrechidVacuamCategory)
            hideCategories(Categories.ORECHID_VACUAM);
    }
    
    @Override
    public void registerCategories(IRecipeCategoryRegistration registry)
    {
        Collection<String> registeredCategories = new HashSet<>();
        
        for (IJeiIntegration integration : jeiIntegration.modIntegrations)
            integration.registerCategories(registry, registeredCategories);
    }
    
    public static void hideCategories(String... categories)
    {
        if (categories != null)
        {
            for (String category : categories)
                jeiRuntime.getRecipeRegistry().hideRecipeCategory(category);
        }
    }
    
    public static void unhideCategories(String... categories)
    {
        if (categories != null)
        {
            for (String category : categories)
                jeiRuntime.getRecipeRegistry().unhideRecipeCategory(category);
        }
    }
    
    public static class Categories
    {
        private Categories()
        {
        }
        
        public static final String DUNGEON_TACTICS_CAULDRON = "mia.alchemical_cauldron";
        public static final String LOOT_BAG = "mia.loot_bag";
        public static final String ORECHID_VACUAM = "mia.orechid_vacuam";
    }
}
