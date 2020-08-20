package com.github.sokyranthedragon.mia.integrations.jer.custom;

import jeresources.entry.VillagerEntry;
import jeresources.jei.villager.VillagerWrapper;
import jeresources.reference.Resources;
import jeresources.util.Font;
import jeresources.util.RenderHelper;
import jeresources.util.TranslationHelper;
import mezz.jei.api.recipe.IFocus;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;
import java.lang.reflect.InvocationTargetException;

public class CustomVillagerWrapper extends VillagerWrapper
{
    public final VillagerEntry entry;
    public IFocus<ItemStack> focus;
    
    public CustomVillagerWrapper(VillagerEntry entry)
    {
        super(entry);
        this.entry = entry;
    }
    
    @Override
    public void setFocus(IFocus<ItemStack> focus)
    {
        super.setFocus(focus);
        this.focus = focus;
    }
    
    @Override
    public void drawInfo(@Nonnull Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX, int mouseY)
    {
        if (!(entry instanceof CustomVillagerEntry))
        {
            super.drawInfo(minecraft, recipeWidth, recipeHeight, mouseX, mouseY);
            return;
        }
        
        CustomVillagerEntry entry = ((CustomVillagerEntry) this.entry);
        EntityLivingBase entity;
        try
        {
            entity = entry.getEntity(minecraft);
        } catch (IllegalAccessException | InvocationTargetException | InstantiationException ignored)
        {
            return;
        }
        
        RenderHelper.scissor(minecraft, 7, 43, 59, 79);
        
        RenderHelper.renderEntity(37, 118, entry.getRenderScale(), (float) (38 - mouseX), (float) (80 - mouseY), entity);
        RenderHelper.stopScissor();
        int y = 22 * (6 - this.getPossibleLevels(this.focus).size()) / 2;
        
        int i;
        for (i = 0; i < this.getPossibleLevels(this.focus).size(); ++i)
            RenderHelper.drawTexture(130, y + i * 22, 0, 120, 20, 20, Resources.Gui.Jei.VILLAGER.getResource());
        
        i = 0;
        
        for (int level : this.getPossibleLevels(this.focus))
            Font.normal.print("lv. " + (level + 1), 72, y + i++ * 22 + 6);
        
        Font.normal.print(TranslationHelper.translateToLocal(this.entry.getDisplayName()), 10, 25);
    }
}
