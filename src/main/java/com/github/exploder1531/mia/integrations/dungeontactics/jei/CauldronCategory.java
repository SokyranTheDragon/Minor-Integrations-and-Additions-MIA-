package com.github.exploder1531.mia.integrations.dungeontactics.jei;

import com.github.exploder1531.mia.Mia;
import com.github.exploder1531.mia.integrations.jei.BackgroundDrawable;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeCategory;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import pegbeard.dungeontactics.handlers.DTBlocks;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

public class CauldronCategory implements IRecipeCategory<CauldronWrapper>
{
    private final BackgroundDrawable BACKGROUND = new BackgroundDrawable("textures/gui/jei/cauldron.png", 166, 59);
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
        recipeLayout.getItemStacks().init(0, false, 123 + 4, 6 + 4);
        recipeLayout.getItemStacks().init(1, false, 79 + 4, 6 + 4);
        recipeLayout.getItemStacks().init(2, false, 143 + 4, 6 + 4);
        
        for (int i = 0; i < 3; i++)
            recipeLayout.getItemStacks().init(i + 3, true, 7 + 4 + 17 * i, 6 + 4);

//        recipeLayout.getItemStacks().addTooltipCallback(cauldronWrapper); implements ITooltipCallback<ItemStack>
        
        recipeLayout.getItemStacks().set(0, cauldronWrapper.getEntry().getOutput());
        recipeLayout.getItemStacks().set(1, cauldronWrapper.getEntry().getRightClickItem());
        recipeLayout.getItemStacks().set(2, cauldronWrapper.getEntry().getByproduct());
        for (int i = 0; i < Math.min(3, cauldronWrapper.getEntry().getInputs().size()); i++)
            recipeLayout.getItemStacks().set(i + 3, cauldronWrapper.getEntry().getInputs().get(i));
    }
}
