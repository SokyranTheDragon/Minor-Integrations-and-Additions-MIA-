package com.github.exploder1531.mia.integrations.jer.custom;

import jeresources.api.drop.PlantDrop;
import jeresources.entry.PlantEntry;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.IPlantable;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class CustomPlantEntry extends PlantEntry
{
    protected IProperty<?> ageProperty;
    protected IBlockState blockState;
    
    public CustomPlantEntry(ItemStack itemStack, @Nullable IPlantable plant, @Nullable IProperty<?> ageProperty, @Nullable IBlockState blockState, PlantDrop... drops)
    {
        super(itemStack, plant, drops);
        this.ageProperty = ageProperty;
        this.blockState = blockState;
    }
    
    public CustomPlantEntry(ItemStack itemStack, @Nullable IPlantable plant, @Nullable IProperty<?> ageProperty, PlantDrop... drops)
    {
        this(itemStack, plant, ageProperty, null, drops);
    }
    
    public CustomPlantEntry(ItemStack itemStack, @Nullable IBlockState blockState, PlantDrop... drops)
    {
        this(itemStack, null, null, blockState, drops);
    }
    
    @Nullable
    public IProperty<?> getAgeProperty()
    {
        return ageProperty;
    }
    
    @Nullable
    public IBlockState getBlockState()
    {
        return blockState;
    }
}
