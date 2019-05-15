package com.github.exploder1531.mia.gui.client;

import com.github.exploder1531.mia.gui.container.ContainerEggSorter;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiEggSorter extends GuiContainer
{
    private static final ResourceLocation texture = new ResourceLocation("mia:textures/gui/container/egg_sorter.png");
    private final InventoryPlayer playerInventory;
    private final IInventory eggSorter;
    
    public GuiEggSorter(InventoryPlayer playerInventory, IInventory eggSorter)
    {
        super(new ContainerEggSorter(playerInventory, eggSorter));
        this.playerInventory = playerInventory;
        this.eggSorter = eggSorter;
    }
    
    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
    }
    
    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
        String name = eggSorter.getDisplayName().getUnformattedText();
        fontRenderer.drawString(name, this.xSize / 2 - this.fontRenderer.getStringWidth(name) / 2, 6, 4210752);
        fontRenderer.drawString(playerInventory.getDisplayName().getUnformattedText(), 8, this.ySize - 96 + 2, 4210752);
    }
    
    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
    {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(texture);
        int posX = (width - xSize) / 2;
        int posY = (height - ySize) / 2;
        this.drawTexturedModalRect(posX, posY, 0, 0, xSize, ySize);
    }
}
