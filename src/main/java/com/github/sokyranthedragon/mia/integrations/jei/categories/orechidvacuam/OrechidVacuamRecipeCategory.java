package com.github.sokyranthedragon.mia.integrations.jei.categories.orechidvacuam;

import com.github.sokyranthedragon.mia.Mia;
import com.github.sokyranthedragon.mia.integrations.jei.MiaJeiPlugin;
import mcp.MethodsReturnNonnullByDefault;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IDrawableStatic;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import vazkii.botania.common.item.block.ItemBlockSpecialFlower;

import javax.annotation.ParametersAreNonnullByDefault;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class OrechidVacuamRecipeCategory implements IRecipeCategory<OrechidVacuamRecipeWrapper>
{
    private final IDrawableStatic background;
    private final String localizedName;
    private final IDrawableStatic overlay;
    
    public OrechidVacuamRecipeCategory(IGuiHelper guiHelper)
    {
        this.background = guiHelper.createBlankDrawable(168, 64);
        this.localizedName = I18n.format("mia.jei.botania.orechid_vacuam");
        this.overlay = guiHelper.createDrawable(new ResourceLocation("botania", "textures/gui/pureDaisyOverlay.png"), 0, 0, 64, 46);
    }
    
    @Override
    public String getUid()
    {
        return MiaJeiPlugin.Categories.ORECHID_VACUAM;
    }
    
    @Override
    public String getTitle()
    {
        return localizedName;
    }
    
    @Override
    public String getModName()
    {
        return Mia.NAME;
    }
    
    @Override
    public IDrawable getBackground()
    {
        return background;
    }
    
    @Override
    public void setRecipe(IRecipeLayout recipeLayout, OrechidVacuamRecipeWrapper orechidVacuamRecipeWrapper, IIngredients ingredients)
    {
        IGuiItemStackGroup itemStacks = recipeLayout.getItemStacks();
        itemStacks.init(0, true, 40, 12);
        itemStacks.set(0, ingredients.getInputs(VanillaTypes.ITEM).get(0));
        itemStacks.init(1, true, 70, 12);
        itemStacks.set(1, ItemBlockSpecialFlower.ofType("orechidVacuam"));
        itemStacks.init(2, true, 99, 12);
        itemStacks.set(2, ingredients.getOutputs(VanillaTypes.ITEM).get(0));
    }
    
    @Override
    public void drawExtras(Minecraft minecraft)
    {
        GlStateManager.enableAlpha();
        GlStateManager.enableBlend();
        this.overlay.draw(minecraft, 48, 0);
        GlStateManager.disableBlend();
        GlStateManager.disableAlpha();
    }
}
