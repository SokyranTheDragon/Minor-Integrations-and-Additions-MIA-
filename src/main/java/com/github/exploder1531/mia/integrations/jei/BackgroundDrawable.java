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
    private final int padding;
    
    public BackgroundDrawable(String resource, int width, int height)
    {
        this(resource, width, height, 5);
    }
    
    public BackgroundDrawable(String resource, int width, int height, int padding)
    {
        this.resource = new ResourceLocation(Mia.MODID, resource);
        this.width = width;
        this.height = height;
        this.padding = padding;
    }
    
    public int getWidth()
    {
        return this.width + padding * 2;
    }
    
    public int getHeight()
    {
        return this.height + padding * 2;
    }
    
    public void draw(@Nonnull Minecraft minecraft)
    {
        this.draw(minecraft, 0, 0);
    }
    
    public void draw(@Nonnull Minecraft minecraft, int xOffset, int yOffset)
    {
        GlStateManager.resetColor();
        minecraft.getTextureManager().bindTexture(this.resource);
        GuiUtils.drawTexturedModalRect(xOffset + padding, yOffset + padding, 0, 0, this.width, this.height, 0.0F);
    }
    
    public ResourceLocation getResource()
    {
        return this.resource;
    }
}
