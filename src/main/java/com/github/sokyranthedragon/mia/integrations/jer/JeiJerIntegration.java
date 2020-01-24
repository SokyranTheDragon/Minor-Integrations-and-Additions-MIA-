package com.github.sokyranthedragon.mia.integrations.jer;

import com.github.sokyranthedragon.mia.Mia;
import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.integrations.jei.IJeiIntegration;
import com.github.sokyranthedragon.mia.integrations.jer.custom.CustomPlantEntry;
import jeresources.entry.PlantEntry;
import jeresources.jei.JEIConfig;
import jeresources.jei.plant.PlantWrapper;
import jeresources.util.RenderHelper;
import mcp.MethodsReturnNonnullByDefault;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.recipe.IRecipeHandler;
import mezz.jei.api.recipe.IRecipeWrapper;
import mezz.jei.collect.ListMultiMap;
import mezz.jei.collect.SetMultiMap;
import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Blocks;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
class JeiJerIntegration implements IJeiIntegration
{
    @SuppressWarnings({ "unchecked", "rawtypes", "deprecation" })
    @Override
    public void register(IModRegistry registry, Collection<String> registeredCategories)
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
        } catch (NoSuchFieldException | IllegalAccessException e)
        {
            Mia.LOGGER.error("Could not access ModRegistry, custom plan drops won't work properly.");
        }
    }
    
    @Override
    public ModIds getModId()
    {
        return ModIds.JER;
    }
    
    @ParametersAreNonnullByDefault
    public static class CustomPlantWrapper extends PlantWrapper
    {
        private final PlantEntry plantEntry;
        private IBlockState state;
        private IProperty<?> ageProperty;
        private long timer = -1L;
        private static final int TICKS = 500;
        
        public CustomPlantWrapper(PlantEntry entry)
        {
            super(entry);
            plantEntry = entry;
        }
        
        @Override
        public void drawInfo(@Nonnull Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX, int mouseY)
        {
            RenderHelper.renderBlock(getFarmland(), 26.0F, 50.0F, -10.0F, 20.0F, 0.4F);
            RenderHelper.renderBlock(getBlockState(), 26.0F, 32.0F, 10.0F, 20.0F, 0.4F);
        }
        
        private IBlockState getBlockState()
        {
            if (state == null)
            {
                if (plantEntry instanceof CustomPlantEntry)
                {
                    CustomPlantEntry plant = (CustomPlantEntry) plantEntry;
                    if (plant.getBlockState() != null)
                        state = plant.getBlockState();
                    if (plant.getAgeProperty() != null)
                        ageProperty = plant.getAgeProperty();
                }
                if (state == null)
                {
                    if (plantEntry.getPlant() != null)
                        state = plantEntry.getPlant().getPlant(null, null);
                    else
                        state = Block.getBlockFromItem(plantEntry.getPlantItemStack().getItem()).getDefaultState();
                }
                if (ageProperty == null)
                {
                    Optional<IProperty<?>> ageProperty = state.getPropertyKeys().stream().filter(property -> property.getName().equals("age")).findAny();
                    ageProperty.ifPresent(property -> this.ageProperty = property);
                }
            }
            
            if (ageProperty != null)
            {
                if (timer == -1L)
                    timer = System.currentTimeMillis() + TICKS;
                else if (System.currentTimeMillis() > timer)
                {
                    state = state.cycleProperty(ageProperty);
                    timer = System.currentTimeMillis() + TICKS;
                }
            }
            
            return state;
        }
        
        private IBlockState getFarmland()
        {
            return plantEntry.getSoil() != null ? plantEntry.getSoil() : Blocks.FARMLAND.getDefaultState();
        }
    }
}
