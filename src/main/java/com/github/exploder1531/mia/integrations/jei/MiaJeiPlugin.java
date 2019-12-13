package com.github.exploder1531.mia.integrations.jei;

import com.github.exploder1531.mia.config.DungeonTacticsConfiguration;
import com.github.exploder1531.mia.core.MiaBlocks;
import com.github.exploder1531.mia.integrations.ModLoadStatus;
import mezz.jei.api.IJeiRuntime;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import net.minecraft.item.ItemStack;

import java.util.Collection;
import java.util.HashSet;

@JEIPlugin
public class MiaJeiPlugin implements IModPlugin
{
    private static IJeiRuntime jeiRuntime;
    static Jei jeiIntegration = null;
    
    @Override
    public void register(IModRegistry registry)
    {
        Collection<String> registeredCategories = new HashSet<>();
        
        for (IJeiIntegration integration : jeiIntegration.modIntegrations)
            integration.register(registry, registeredCategories);
        
        if (ModLoadStatus.hatcheryLoaded)
            registry.addIngredientInfo(new ItemStack(MiaBlocks.egg_sorter), VanillaTypes.ITEM, "mia.jei.info.egg_sorter");
        if (ModLoadStatus.iceAndFireLoaded)
            registry.addIngredientInfo(new ItemStack(MiaBlocks.pixie_dust_extractor), VanillaTypes.ITEM, "mia.jei.info.pixie_dust_extractor");
        if (ModLoadStatus.thaumcraftLoaded)
            registry.addIngredientInfo(new ItemStack(MiaBlocks.void_creator), VanillaTypes.ITEM, "mia.jei.info.void_creator");
    }
    
    @Override
    public void onRuntimeAvailable(IJeiRuntime jeiRuntime)
    {
        MiaJeiPlugin.jeiRuntime = jeiRuntime;
        
        if (!DungeonTacticsConfiguration.enableJeiIntegration)
            hideCategories(Categories.DUNGEON_TACTICS_CAULDRON, Categories.LOOT_BAG);
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
    }
}
