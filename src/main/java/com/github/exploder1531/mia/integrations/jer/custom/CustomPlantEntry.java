package com.github.exploder1531.mia.integrations.jer.custom;

import jeresources.api.drop.PlantDrop;
import jeresources.entry.PlantEntry;
import net.minecraft.block.properties.IProperty;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.IPlantable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class CustomPlantEntry extends PlantEntry
{
    protected IProperty<?> ageProperty;
    
    public CustomPlantEntry(ItemStack itemStack, @Nullable IPlantable plant, IProperty<?> ageProperty, PlantDrop... drops)
    {
        super(itemStack, plant, drops);
        this.ageProperty = ageProperty;
    }
    
    public CustomPlantEntry(ItemStack itemStack, IProperty<?> ageProperty, PlantDrop... drops)
    {
        this(itemStack, null, ageProperty, drops);
    }
    
    public <T extends Item & IPlantable> CustomPlantEntry(T plant, IProperty<?> ageProperty, PlantDrop... drops)
    {
        this(new ItemStack(plant), plant, ageProperty, drops);
    }
    
    @Nonnull
    public IProperty<?> getAgeProperty()
    {
        return ageProperty;
    }
}
