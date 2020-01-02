package com.github.exploder1531.mia.integrations.jei;

import com.github.exploder1531.mia.config.JeiConfiguration;
import com.github.exploder1531.mia.core.MiaBlocks;
import com.github.exploder1531.mia.core.MiaItems;
import mezz.jei.api.*;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;
import java.util.Collection;
import java.util.HashSet;

import static com.github.exploder1531.mia.integrations.ModIds.*;

@JEIPlugin
public class MiaJeiPlugin implements IModPlugin
{
    private static IJeiRuntime jeiRuntime;
    static Jei jeiIntegration = null;
    
    @Override
    public void registerItemSubtypes(ISubtypeRegistry subtypeRegistry)
    {
        subtypeRegistry.registerSubtypeInterpreter(MiaItems.music_player, new ISubtypeRegistry.ISubtypeInterpreter()
        {
            @Nonnull
            @Override
            public String apply(@Nonnull ItemStack itemStack)
            {
                return "mia:music_player:0";
            }
        });
    }
    
    @Override
    public void register(IModRegistry registry)
    {
        Collection<String> registeredCategories = new HashSet<>();
        
        for (IJeiIntegration integration : jeiIntegration.modIntegrations)
            integration.register(registry, registeredCategories);
        
        if (HATCHERY.isLoaded)
            registry.addIngredientInfo(new ItemStack(MiaBlocks.egg_sorter), VanillaTypes.ITEM, "mia.jei.info.egg_sorter");
        if (ICE_AND_FIRE.isLoaded)
            registry.addIngredientInfo(new ItemStack(MiaBlocks.pixie_dust_extractor), VanillaTypes.ITEM, "mia.jei.info.pixie_dust_extractor");
        if (THAUMCRAFT.isLoaded)
            registry.addIngredientInfo(new ItemStack(MiaBlocks.void_creator), VanillaTypes.ITEM, "mia.jei.info.void_creator");
    }
    
    @Override
    public void onRuntimeAvailable(IJeiRuntime jeiRuntime)
    {
        MiaJeiPlugin.jeiRuntime = jeiRuntime;
        
        if (!JeiConfiguration.enableLootBagCategory)
            hideCategories(Categories.LOOT_BAG);
        if (!JeiConfiguration.enableAlchemicalCauldronCategory)
            hideCategories(Categories.DUNGEON_TACTICS_CAULDRON);
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
