package com.github.exploder1531.mia.integrations.jei;

import com.github.exploder1531.mia.Mia;
import mezz.jei.api.gui.IDrawable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.config.GuiUtils;

import javax.annotation.Nonnull;

public class BackgroundDrawable implements IDrawable
{
    private final int width;
    private final int height;
    private final ResourceLocation resource;
    private static final int PADDING = 5;
    
    public BackgroundDrawable(String resource, int width, int height)
    {
        this.resource = new ResourceLocation(Mia.MODID, resource);
        this.width = width;
        this.height = height;
    }
    
    public int getWidth()
    {
        return this.width + 10;
    }
    
    public int getHeight()
    {
        return this.height + 10;
    }
    
    public void draw(@Nonnull Minecraft minecraft)
    {
        this.draw(minecraft, 0, 0);
    }
    
    public void draw(@Nonnull Minecraft minecraft, int xOffset, int yOffset)
    {
        GlStateManager.resetColor();
        minecraft.getTextureManager().bindTexture(this.resource);
        GuiUtils.drawTexturedModalRect(xOffset + PADDING, yOffset + PADDING, 0, 0, this.width, this.height, 0.0F);
    }
    
    public ResourceLocation getResource()
    {
        return this.resource;
    }
}
