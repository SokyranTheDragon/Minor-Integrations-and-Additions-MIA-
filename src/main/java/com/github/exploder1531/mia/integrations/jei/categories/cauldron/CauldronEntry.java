package com.github.exploder1531.mia.integrations.jei.categories.cauldron;

import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.List;

public class CauldronEntry
{
    private ItemStack rightClickItem;
    private ItemStack output;
    private ItemStack byproduct;
    private List<ItemStack> input;
    
    public CauldronEntry(ItemStack rightClickItem, ItemStack output, ItemStack byproduct, @Nonnull List<ItemStack> input)
    {
        this.rightClickItem = rightClickItem;
        this.output = output;
        this.byproduct = byproduct;
        this.input = input;
    }
    
    public CauldronEntry(ItemStack rightClickItem, ItemStack output, ItemStack byproduct, ItemStack... input)
    {
        this(rightClickItem, output, byproduct, Arrays.asList(input));
    }
    
    public CauldronEntry(ItemStack rightClickItem, ItemStack output, @Nonnull List<ItemStack> input)
    {
        this(rightClickItem, output, ItemStack.EMPTY, input);
    }
    
    public CauldronEntry(ItemStack rightClickItem, ItemStack output, ItemStack... input)
    {
        this(rightClickItem, output, ItemStack.EMPTY, Arrays.asList(input));
    }
    
    public ItemStack getRightClickItem()
    {
        return rightClickItem;
    }
    
    public ItemStack getByproduct()
    {
        return byproduct;
    }
    
    public ItemStack getOutput()
    {
        return output;
    }
    
    public List<ItemStack> getInputs()
    {
        return input;
    }
    
    public boolean isRecipeValid()
    {
        return !output.isEmpty();
    }
}
