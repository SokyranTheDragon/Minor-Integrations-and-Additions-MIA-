package com.github.sokyranthedragon.mia.gui.client;

import com.github.sokyranthedragon.mia.config.MiaConfig;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.client.gui.toasts.GuiToast;
import net.minecraft.client.gui.toasts.IToast;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class ErrorToast implements IToast
{
    private long firstDrawTime = -1L;
    
    @Override
    public Visibility draw(GuiToast toastGui, long delta)
    {
        if (firstDrawTime == -1L) this.firstDrawTime = delta;
        
        toastGui.getMinecraft().getTextureManager().bindTexture(TEXTURE_TOASTS);
        GlStateManager.color(1.0F, 1.0F, 1.0F);
        toastGui.drawTexturedModalRect(0, 0, 0, 64, 160, 32);
        
        toastGui.getMinecraft().fontRenderer.drawString(I18n.format("mia.gui.toast.loading_error.title"), 18, 7, -256);
        toastGui.getMinecraft().fontRenderer.drawString(I18n.format("mia.gui.toast.loading_error.subtitle"), 18, 18, -1);
        
        return delta - this.firstDrawTime < 15000L ? Visibility.SHOW : Visibility.HIDE;
    }
    
    @Override
    public Object getType()
    {
        return this;
    }
    
    @Override
    public boolean equals(Object o)
    {
        return o instanceof ErrorToast;
    }
    
    public static void tryAddToast(GuiToast toast)
    {
        if (!MiaConfig.showErrorToasts) return;
        ErrorToast errorToast = new ErrorToast();
        if (toast.getToast(ErrorToast.class, errorToast) == null) toast.add(errorToast);
    }
}
