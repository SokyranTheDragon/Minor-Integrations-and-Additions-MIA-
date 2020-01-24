package com.github.sokyranthedragon.mia.integrations.jei.categories.cauldron;

import com.github.sokyranthedragon.mia.Mia;
import com.github.sokyranthedragon.mia.integrations.jei.BackgroundDrawable;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeCategory;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import pegbeard.dungeontactics.handlers.DTBlocks;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

public class CauldronCategory implements IRecipeCategory<CauldronWrapper>
{
    static final BackgroundDrawable BACKGROUND = new BackgroundDrawable("textures/gui/jei/cauldron.png", 166, 59);
    private IDrawable ICON;
    
    public CauldronCategory(IGuiHelper helper)
    {
        ICON = helper.createDrawableIngredient(new ItemStack(DTBlocks.ALCHEMYCAULDRON));
    }
    
    @Nonnull
    @Override
    public String getUid()
    {
        return "mia.alchemical_cauldron";
    }
    
    @Nonnull
    @Override
    public String getTitle()
    {
        return I18n.format("mia.jei.cauldron.title");
    }
    
    @Nonnull
    @Override
    public String getModName()
    {
        return Mia.NAME;
    }
    
    @Nullable
    @Override
    public IDrawable getIcon()
    {
        return ICON;
    }
    
    @Nonnull
    @Override
    public IDrawable getBackground()
    {
        return BACKGROUND;
    }
    
    @Override
    @ParametersAreNonnullByDefault
    public void setRecipe(IRecipeLayout recipeLayout, CauldronWrapper cauldronWrapper, IIngredients ingredients)
    {
        // Main output
        if (cauldronWrapper.getEntry() instanceof CauldronEntry.CauldronFluidEntry)
        {
            recipeLayout.getFluidStacks().init(1, false, 128, 11);
            recipeLayout.getFluidStacks().set(1, ((CauldronEntry.CauldronFluidEntry) cauldronWrapper.getEntry()).getTrueOutput());
        }
        else
        {
            recipeLayout.getItemStacks().init(0, false, 127, 10);
            recipeLayout.getItemStacks().set(0, cauldronWrapper.getEntry().getOutput());
        }
        
        // Right click item and byproduct
        recipeLayout.getItemStacks().init(1, false, 83, 10);
        recipeLayout.getItemStacks().init(2, false, 147, 10);
        recipeLayout.getItemStacks().set(1, cauldronWrapper.getEntry().getRightClickItem());
        recipeLayout.getItemStacks().set(2, cauldronWrapper.getEntry().getByproduct());
        
        // Inputs
        for (int i = 0; i < Math.min(3, cauldronWrapper.getEntry().getInputs().size()); i++)
        {
            recipeLayout.getItemStacks().init(i + 3, true, 11 + 17 * i, 10);
            recipeLayout.getItemStacks().set(i + 3, cauldronWrapper.getEntry().getInputs().get(i));
        }
        
        // Liquid in cauldron
        FluidStack fluid = cauldronWrapper.getEntry().getFluid();
        if (fluid != null)
        {
            recipeLayout.getFluidStacks().init(0, false, 38, 30);
            recipeLayout.getFluidStacks().set(0, fluid);
        }
        else if (cauldronWrapper.getEntry().getPossibleFluids() == CauldronEntry.PossibleFluids.NoWater)
        {
            recipeLayout.getItemStacks().init(6, false, 37, 29);
            recipeLayout.getItemStacks().set(6, new ItemStack(Blocks.BARRIER));
        }
        
        recipeLayout.getItemStacks().addTooltipCallback(cauldronWrapper);
    }
}
