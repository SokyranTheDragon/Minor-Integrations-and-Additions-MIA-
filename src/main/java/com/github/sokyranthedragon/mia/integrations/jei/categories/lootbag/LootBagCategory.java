package com.github.sokyranthedragon.mia.integrations.jei.categories.lootbag;

import com.github.sokyranthedragon.mia.Mia;
import com.github.sokyranthedragon.mia.integrations.jei.BackgroundDrawable;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeCategory;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

public class LootBagCategory implements IRecipeCategory<LootBagWrapper>
{
    private final BackgroundDrawable BACKGROUND = new BackgroundDrawable("textures/gui/jei/loot_bag.png", 169, 119, 0);
    private final IDrawable ICON;
    
    public LootBagCategory(IGuiHelper helper, ItemStack item)
    {
        ICON = helper.createDrawableIngredient(item);
    }
    
    public LootBagCategory(IGuiHelper helper, Item item)
    {
        this(helper, new ItemStack(item));
    }
    
    @Nonnull
    @Override
    public String getUid()
    {
        return "mia.loot_bag";
    }
    
    @Nonnull
    @Override
    public String getTitle()
    {
        return I18n.format("mia.jei.loot_bag.title");
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
    public void setRecipe(IRecipeLayout recipeLayout, LootBagWrapper lootBagWrapper, IIngredients ingredients)
    {
        IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();
        guiItemStacks.init(0, true, 74, 8);
        
        for (int y = 0; y <= 4; ++y)
        {
            for (int x = 0; x <= 9; ++x)
                guiItemStacks.init(1 + y * 9 + x, false, 3 + x * 18, 44 + y * 18);
        }
        
        guiItemStacks.set(ingredients);
        recipeLayout.getItemStacks().addTooltipCallback(lootBagWrapper);
    }
}
