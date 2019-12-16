package com.github.exploder1531.mia.integrations.jei.categories.cauldron;

import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Arrays;
import java.util.List;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public abstract class CauldronEntry
{
    protected final ItemStack rightClickItem;
    protected final PossibleFluids fluid;
    protected final List<ItemStack> input;
    
    public CauldronEntry(ItemStack rightClickItem, ItemStack... input)
    {
        this(rightClickItem, PossibleFluids.Water, input);
    }
    
    public CauldronEntry(ItemStack rightClickItem, PossibleFluids fluid, ItemStack... input)
    {
        this.rightClickItem = rightClickItem;
        this.fluid = fluid;
        this.input = Arrays.asList(input);
    }
    
    public ItemStack getRightClickItem()
    {
        return rightClickItem;
    }
    
    public abstract ItemStack getOutput();
    
    public ItemStack getByproduct()
    {
        return ItemStack.EMPTY;
    }
    
    public List<ItemStack> getInputs()
    {
        return input;
    }
    
    public PossibleFluids getPossibleFluids()
    {
        return fluid;
    }
    
    @Nullable
    public FluidStack getFluid()
    {
        if (fluid == PossibleFluids.Water || fluid == PossibleFluids.NoWater)
            return FluidRegistry.getFluidStack("water", 1000);
        else if (fluid == PossibleFluids.Lava)
            return FluidRegistry.getFluidStack("lava", 1000);
        else
            return null;
    }
    
    @SuppressWarnings("unused")
    public abstract boolean isRecipeValid();
    
    public static class CauldronImbuingEntry extends CauldronEntry
    {
        protected Enchantment enchantment;
        
        public CauldronImbuingEntry(ItemStack rightClickItem, Enchantment enchantment, ItemStack... input)
        {
            super(rightClickItem, input);
            this.enchantment = enchantment;
        }
        
        @Override
        public ItemStack getOutput()
        {
            ItemStack stack = rightClickItem.copy();
            stack.addEnchantment(enchantment, 1);
            return stack;
        }
        
        @Override
        public boolean isRecipeValid()
        {
            return enchantment != null;
        }
    }
    
    public static class CauldronCookingEntry extends CauldronEntry
    {
        protected final ItemStack output;
        protected final ItemStack byproduct;
        
        public CauldronCookingEntry(ItemStack rightClickItem, PossibleFluids fluid, ItemStack output, ItemStack byproduct, ItemStack... input)
        {
            super(rightClickItem, fluid, input);
            this.output = output;
            this.byproduct = byproduct;
        }
    
        @Override
        public ItemStack getOutput()
        {
            return output;
        }
    
        @Override
        public ItemStack getByproduct()
        {
            return byproduct;
        }
    
        @Override
        public boolean isRecipeValid()
        {
            return !output.isEmpty();
        }
    }
    
    @SuppressWarnings("unused")
    public static class SimpleCauldronCookingEntry extends CauldronCookingEntry
    {
        protected final int maxOutput;
        
        public SimpleCauldronCookingEntry(ItemStack rightClickItem, ItemStack output, ItemStack byproduct, int max, ItemStack... input)
        {
            super(rightClickItem, PossibleFluids.Water, output, byproduct, input);
            maxOutput = Math.max(output.getCount(), max);
        }
        
        public SimpleCauldronCookingEntry(ItemStack rightClickItem, ItemStack output, int max, ItemStack... input)
        {
            this(rightClickItem, output, ItemStack.EMPTY, max, input);
        }
        
        public SimpleCauldronCookingEntry(ItemStack rightClickItem, ItemStack output, ItemStack byproduct, ItemStack... input)
        {
            this(rightClickItem, output, byproduct, 1, input);
        }
        
        public SimpleCauldronCookingEntry(ItemStack rightClickItem, ItemStack output, ItemStack... input)
        {
            this(rightClickItem, output, 1, input);
        }
        
        public int getMaxOutput()
        {
            return maxOutput;
        }
    }
    
    public static class CauldronObsidianEntry extends CauldronEntry
    {
        public CauldronObsidianEntry(boolean lavaInCauldron)
        {
            //noinspection ConstantConditions
            super(
                    FluidUtil.getFilledBucket(FluidRegistry.getFluidStack(lavaInCauldron ? "water" : "lava", 1000)),
                    lavaInCauldron ? PossibleFluids.Lava : PossibleFluids.Water);
        }
        
        @Override
        public ItemStack getOutput()
        {
            return new ItemStack(Blocks.OBSIDIAN);
        }
        
        @Override
        public boolean isRecipeValid()
        {
            return true;
        }
    }
    
    public static class CauldronFluidEntry extends CauldronEntry
    {
        protected final FluidStack output;
        
        public CauldronFluidEntry(ItemStack rightClickItem, PossibleFluids fluid, FluidStack output, ItemStack... input)
        {
            super(rightClickItem, fluid, input);
            this.output = output;
        }
        
        @Override
        public ItemStack getOutput()
        {
            return ItemStack.EMPTY;
        }
        
        public FluidStack getTrueOutput()
        {
            return output;
        }
        
        @Override
        public boolean isRecipeValid()
        {
            return output != null;
        }
    }
    
    public enum PossibleFluids
    {
        Water,
        Lava,
        NoWater,
        Any
    }
}
