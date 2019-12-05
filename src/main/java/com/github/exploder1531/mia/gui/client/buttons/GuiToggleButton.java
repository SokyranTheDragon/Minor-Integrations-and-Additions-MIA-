package com.github.exploder1531.mia.gui.client.buttons;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;

@SideOnly(Side.CLIENT)
public class GuiToggleButton extends GuiButton
{
    private final ResourceLocation resource;
    private final int textureOffsetX;
    private final int textureOffsetY;
    private final int variantOffsetX;
    private final int variantOffsetY;
    public boolean isAltVariant = false;
    
    public GuiToggleButton(int buttonId, int x, int y, int width, int height, int textureOffsetX, int textureOffsetY, int variantOffsetX, int variantOffsetY, ResourceLocation resource)
    {
        super(buttonId, x, y, width, height, "");
        this.textureOffsetX = textureOffsetX;
        this.textureOffsetY = textureOffsetY;
        this.variantOffsetX = variantOffsetX;
        this.variantOffsetY = variantOffsetY;
        this.resource = resource;
    }
    
    @Override
    public void drawButton(@Nonnull Minecraft mc, int mouseX, int mouseY, float partialTicks)
    {
        if (visible)
        {
            hovered = mouseX >= x && mouseY >= y && mouseX < x + width && mouseY < y + height;
            mc.getTextureManager().bindTexture(resource);
            GlStateManager.disableDepth();
            int textureOffsetX = this.textureOffsetX;
            int textureOffsetY = this.textureOffsetY;
            
            if (isAltVariant)
                textureOffsetX += variantOffsetX;
            if (!enabled)
                textureOffsetY += variantOffsetY * 2;
            else if (hovered)
                textureOffsetY += variantOffsetY;
            
            this.drawTexturedModalRect(x, y, textureOffsetX, textureOffsetY, width, height);
            GlStateManager.enableDepth();
        }
    }
}
