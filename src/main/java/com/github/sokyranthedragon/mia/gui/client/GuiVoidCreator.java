package com.github.sokyranthedragon.mia.gui.client;

import com.github.sokyranthedragon.mia.gui.container.ContainerVoidCreator;
import com.github.sokyranthedragon.mia.tile.TileVoidCreator;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiVoidCreator extends GuiContainer
{
    private ResourceLocation texture = new ResourceLocation("thaumcraft", "textures/gui/gui_void_siphon.png");
    
    public GuiVoidCreator(InventoryPlayer inventorySlotsIn, TileVoidCreator voidCreator)
    {
        super(new ContainerVoidCreator(inventorySlotsIn, voidCreator));
        xSize = 176;
        ySize = 166;
    }
    
    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
    }
    
    @Override
    protected void drawGuiContainerBackgroundLayer(float par1, int mx, int my)
    {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(texture);
        int posX = (width - xSize) / 2;
        int posY = (height - ySize) / 2;
        this.drawTexturedModalRect(posX, posY, 0, 0, xSize, ySize);
//        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
//        this.mc.renderEngine.bindTexture(this.texture);
//        int k = (this.width - this.xSize) / 2;
//        int l = (this.height - this.ySize) / 2;
//        GL11.glEnable(3042);
//        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
    }
}
