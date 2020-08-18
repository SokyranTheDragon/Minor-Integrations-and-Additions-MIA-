package com.github.sokyranthedragon.mia.integrations.jer;

import com.github.sokyranthedragon.mia.Mia;
import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.integrations.jei.IJeiIntegration;
import com.github.sokyranthedragon.mia.integrations.jer.custom.CustomPlantWrapper;
import com.github.sokyranthedragon.mia.integrations.jer.custom.CustomVillagerWrapper;
import jeresources.entry.PlantEntry;
import jeresources.entry.VillagerEntry;
import jeresources.jei.JEIConfig;
import jeresources.jei.plant.PlantWrapper;
import jeresources.jei.villager.VillagerWrapper;
import mcp.MethodsReturnNonnullByDefault;
import mezz.jei.JustEnoughItems;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.recipe.IRecipeHandler;
import mezz.jei.api.recipe.IRecipeWrapper;
import mezz.jei.collect.ListMultiMap;
import mezz.jei.collect.SetMultiMap;
import mezz.jei.plugins.vanilla.VanillaPlugin;
import mezz.jei.startup.ProxyCommon;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
class JeiJerIntegration implements IJeiIntegration
{
    boolean registeredPlants = false;
    boolean registeredVillagers = false;
    
    @SuppressWarnings("unchecked")
    public boolean initializePlugins(JustEnoughResources jer)
    {
        try
        {
            ProxyCommon proxy = JustEnoughItems.getProxy();
            
            Field pluginsField = proxy.getClass().getDeclaredField("plugins");
            pluginsField.setAccessible(true);
            Object o = pluginsField.get(proxy);
            if (o instanceof List)
            {
                List<IModPlugin> plugins = (List<IModPlugin>) o;
                int safeIndex = 0;
                
                for (IModPlugin plugin : plugins)
                {
                    safeIndex++;
                    if (!(plugin instanceof VanillaPlugin))
                        break;
                }
                
                plugins.add(safeIndex, new IModPlugin()
                {
                    @Override
                    public void register(@Nonnull IModRegistry registry)
                    {
                        try
                        {
                            if (!registeredPlants)
                                registeredPlants = registerCustomPlants(registry);
                            if (!registeredVillagers)
                                registeredVillagers = registerCustomVillagers(registry);
                            jer.initJerIntegration();
                        } catch (Exception e)
                        {
                            Mia.LOGGER.error("Encountered an issue registering JER entries! (Early-insertion registration)");
                            Mia.LOGGER.error(e);
                        }
                    }
                });
                
                return true;
            }
        } catch (NoSuchFieldException | IllegalAccessException e)
        {
            Mia.LOGGER.error("Could not access JEI plugin list, there might be some issues with mob drops not working ");
        }
        
        return false;
    }
    
    @Override
    public void register(IModRegistry registry, Collection<String> registeredCategories)
    {
        if (!registeredPlants)
            registeredPlants = registerCustomPlants(registry);
        if (!registeredVillagers)
            registeredVillagers = registerCustomVillagers(registry);
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes", "deprecation" })
    public static boolean registerCustomPlants(IModRegistry registry)
    {
        try
        {
            Field recipeHandlerClassesField = registry.getClass().getDeclaredField("recipeHandlerClasses");
            Field recipeHandlersField = registry.getClass().getDeclaredField("recipeHandlers");
            
            recipeHandlerClassesField.setAccessible(true);
            recipeHandlersField.setAccessible(true);
            
            SetMultiMap<String, Class> recipeHandlerClasses = (SetMultiMap<String, Class>) recipeHandlerClassesField.get(registry);
            ListMultiMap<String, IRecipeHandler> recipeHandlers = (ListMultiMap<String, IRecipeHandler>) recipeHandlersField.get(registry);
            
            if (!recipeHandlerClasses.contains(JEIConfig.PLANT, PlantEntry.class))
                recipeHandlerClasses.put(JEIConfig.PLANT, PlantEntry.class);
            
            @MethodsReturnNonnullByDefault
            @ParametersAreNonnullByDefault
            IRecipeHandler<PlantEntry> recipeHandler = new IRecipeHandler<PlantEntry>()
            {
                @Override
                public Class<PlantEntry> getRecipeClass()
                {
                    return PlantEntry.class;
                }
                
                @Override
                public String getRecipeCategoryUid(PlantEntry plantEntry)
                {
                    return JEIConfig.PLANT;
                }
                
                @Override
                public IRecipeWrapper getRecipeWrapper(PlantEntry plantEntry)
                {
                    return new CustomPlantWrapper(plantEntry);
                }
                
                @Override
                public boolean isRecipeValid(PlantEntry plantEntry)
                {
                    return true;
                }
            };
            
            List<IRecipeHandler> plantHandlers = recipeHandlers.get(JEIConfig.PLANT);
            
            if (plantHandlers.size() > 0)
                //noinspection ConstantConditions
                plantHandlers.removeIf(handler -> handler.getRecipeWrapper(null) instanceof PlantWrapper);
            plantHandlers.add(recipeHandler);
            
            return true;
        } catch (NoSuchFieldException | IllegalAccessException e)
        {
            Mia.LOGGER.error("Could not access ModRegistry, custom plant drops won't work properly.");
        }
        
        return false;
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes", "deprecation" })
    public static boolean registerCustomVillagers(IModRegistry registry)
    {
        try
        {
            Field recipeHandlerClassesField = registry.getClass().getDeclaredField("recipeHandlerClasses");
            Field recipeHandlersField = registry.getClass().getDeclaredField("recipeHandlers");
            
            recipeHandlerClassesField.setAccessible(true);
            recipeHandlersField.setAccessible(true);
            
            SetMultiMap<String, Class> recipeHandlerClasses = (SetMultiMap<String, Class>) recipeHandlerClassesField.get(registry);
            ListMultiMap<String, IRecipeHandler> recipeHandlers = (ListMultiMap<String, IRecipeHandler>) recipeHandlersField.get(registry);
            
            if (!recipeHandlerClasses.contains(JEIConfig.VILLAGER, VillagerEntry.class))
                recipeHandlerClasses.put(JEIConfig.VILLAGER, VillagerEntry.class);
            
            @MethodsReturnNonnullByDefault
            @ParametersAreNonnullByDefault
            IRecipeHandler<VillagerEntry> recipeHandler = new IRecipeHandler<VillagerEntry>()
            {
                @Override
                public Class<VillagerEntry> getRecipeClass()
                {
                    return VillagerEntry.class;
                }
                
                @Override
                public String getRecipeCategoryUid(VillagerEntry villagerEntry)
                {
                    return JEIConfig.VILLAGER;
                }
                
                @Override
                public IRecipeWrapper getRecipeWrapper(VillagerEntry villagerEntry)
                {
                    return new CustomVillagerWrapper(villagerEntry);
                }
                
                @Override
                public boolean isRecipeValid(VillagerEntry villagerEntry)
                {
                    return true;
                }
            };
            
            List<IRecipeHandler> villagerHandlers = recipeHandlers.get(JEIConfig.VILLAGER);
            
            if (villagerHandlers.size() > 0)
                //noinspection ConstantConditions
                villagerHandlers.removeIf(handler -> handler.getRecipeWrapper(null) instanceof VillagerWrapper);
            villagerHandlers.add(recipeHandler);
            
            return true;
        } catch (NoSuchFieldException | IllegalAccessException e)
        {
            Mia.LOGGER.error("Could not access ModRegistry, custom villager drops won't work properly.");
        }
        
        return false;
    }
    
    @Override
    public ModIds getModId()
    {
        return ModIds.JER;
    }
}
