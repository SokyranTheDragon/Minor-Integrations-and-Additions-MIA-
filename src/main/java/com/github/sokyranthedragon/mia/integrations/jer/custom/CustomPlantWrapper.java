package com.github.sokyranthedragon.mia.integrations.jer.custom;

import jeresources.entry.PlantEntry;
import jeresources.jei.plant.PlantWrapper;
import jeresources.util.RenderHelper;
import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Blocks;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Optional;

@ParametersAreNonnullByDefault
public class CustomPlantWrapper extends PlantWrapper
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
            if (ageProperty != null)
                timer = System.currentTimeMillis() + TICKS;
        }
        
        if (timer != -1L && System.currentTimeMillis() > timer)
        {
            state = state.cycleProperty(ageProperty);
            timer = System.currentTimeMillis() + TICKS;
        }
        
        return state;
    }
    
    private IBlockState getFarmland()
    {
        return plantEntry.getSoil() != null ? plantEntry.getSoil() : Blocks.FARMLAND.getDefaultState();
    }
}