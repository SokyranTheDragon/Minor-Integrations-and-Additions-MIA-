package com.github.exploder1531.mia.gui.client;

import com.github.exploder1531.mia.gui.client.buttons.GuiButtonImageDisableable;
import com.github.exploder1531.mia.gui.client.buttons.GuiToggleButton;
import com.github.exploder1531.mia.gui.container.ContainerMusicPlayer;
import com.github.exploder1531.mia.inventory.MusicPlayerInventory;
import com.github.exploder1531.mia.utilities.MusicUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiButtonImage;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StringUtils;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiMusicPlayer extends GuiContainer
{
    private static final ResourceLocation texture = new ResourceLocation("mia:textures/gui/container/music_player.png");
    private final MusicPlayerInventory inventory;
    
    private GuiButtonImage pageLeftButton;
    private GuiButtonImage pageRightButton;
    private GuiToggleButton musicToggleButton;
    private GuiButtonImageDisableable playNextButton;
    private GuiButtonImageDisableable playPreviousButton;
    private GuiToggleButton autoplayButton;
    private GuiToggleButton shuffleButton;
    private GuiToggleButton repeatButton;
    
    private int checkCounter = 20;
    
    public GuiMusicPlayer(InventoryPlayer playerInventory, MusicPlayerInventory inventory)
    {
        super(new ContainerMusicPlayer(playerInventory, inventory));
        this.inventory = inventory;
    }
    
    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
    
        if (autoplayButton.isMouseOver())
        {
            if (autoplayButton.isAltVariant)
                this.drawHoveringText(I18n.format("mia.gui.music_player.tooltip.autoplay_alt"), mouseX, mouseY);
            else
                this.drawHoveringText(I18n.format("mia.gui.music_player.tooltip.autoplay"), mouseX, mouseY);
        }
        else if (shuffleButton.isMouseOver())
        {
            if (shuffleButton.isAltVariant)
                this.drawHoveringText(I18n.format("mia.gui.music_player.tooltip.shuffle_alt"), mouseX, mouseY);
            else
                this.drawHoveringText(I18n.format("mia.gui.music_player.tooltip.shuffle"), mouseX, mouseY);
        }
        else if (repeatButton.isMouseOver())
        {
            if (repeatButton.isAltVariant)
                this.drawHoveringText(I18n.format("mia.gui.music_player.tooltip.repeat_alt"), mouseX, mouseY);
            else
                this.drawHoveringText(I18n.format("mia.gui.music_player.tooltip.repeat"), mouseX, mouseY);
        }
        
        for (GuiButton button : buttonList)
        {
            if (button.isMouseOver())
            {
                if (!StringUtils.isNullOrEmpty(button.displayString))
                    this.drawHoveringText(button.displayString, mouseX, mouseY);
                return;
            }
        }
        
    }
    
    @Override
    protected void drawGuiContainerBackgroundLayer(float v, int i, int i1)
    {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(texture);
        int posX = (width - xSize) / 2;
        int posY = (height - ySize) / 2;
        this.drawTexturedModalRect(posX, posY, 0, 0, xSize, ySize);
        
        int maxSlots = 7;
        if (inventory.openPage * 7 <= inventory.getSizeInventory() && (inventory.openPage + 1) * 7 > inventory.getSizeInventory())
            maxSlots = (inventory.getSizeInventory() % 7) + 1;
        
        // If needed, draw additional inventory slots (the first one is always drawn)
        for (int slot = 1; slot < maxSlots; slot++)
            this.drawTexturedModalRect(posX + 25 + slot * 18, posY + 53, 25, 53, 18, 18);
    }
    
    @Override
    public void updateScreen()
    {
        updateButtons();
        
        super.updateScreen();
    }
    
    @Override
    protected void actionPerformed(GuiButton button)
    {
        switch (button.id)
        {
            case 0:
                if (inventory.openPage > 0)
                    inventory.openPage--;
                break;
            case 1:
                if ((inventory.openPage + 1) * 7 < inventory.getSizeInventory() + 1)
                    inventory.openPage++;
                break;
            case 2:
                MusicUtils.toggleSong(inventory.inventory);
                checkCounter = 20;
                break;
            case 3:
                MusicUtils.playNext(inventory.inventory);
                break;
            case 4:
                MusicUtils.playPrevious(inventory.inventory);
                break;
            case 5:
                inventory.inventory.autoplay ^= true;
                break;
            case 6:
                inventory.inventory.shuffle ^= true;
                break;
            case 7:
                inventory.inventory.repeat ^= true;
                break;
        }
        
        MusicUtils.updateMusicPlayerWithUuid(Minecraft.getMinecraft().player, inventory.inventory);
    }
    
    @Override
    public void initGui()
    {
        super.initGui();
        
        pageLeftButton = addButton(new GuiButtonImage(0, 9 + guiLeft, 53 + guiTop, 10, 18, 189, 0, 19, texture));
        pageRightButton = addButton(new GuiButtonImage(1, 157 + guiLeft, 53 + guiTop, 10, 18, 177, 0, 19, texture));
        musicToggleButton = addButton(new GuiToggleButton(2, 78 + guiLeft, 28 + guiTop, 20, 20, 0, 198, 19, 19, texture));
        playNextButton = addButton(new GuiButtonImageDisableable(3, 98 + guiLeft, 28 + guiTop, 20, 20, 38, 198, 19, texture));
        playPreviousButton = addButton(new GuiButtonImageDisableable(4, 58 + guiLeft, 28 + guiTop, 20, 20, 57, 198, 19, texture));
        autoplayButton = addButton(new GuiToggleButton(5, 6 + guiLeft, 4 + guiTop, 20, 20, 76, 198, 19, 19, texture));
        shuffleButton = addButton(new GuiToggleButton(6, 26 + guiLeft, 4 + guiTop, 20, 20, 152, 198, 19, 19, texture));
        repeatButton = addButton(new GuiToggleButton(7, 46 + guiLeft, 4 + guiTop, 20, 20, 114, 198, 19, 19, texture));
        
        updateButtons();
    }
    
    private void updateButtons()
    {
        boolean leftVisible = inventory.openPage > 0;
        boolean rightVisible = (inventory.openPage + 1) * 7 < inventory.getSizeInventory() + 1;
        
        pageLeftButton.enabled = leftVisible;
        pageLeftButton.visible = leftVisible;
        pageRightButton.enabled = rightVisible;
        pageRightButton.visible = rightVisible;
        
        boolean isEnabled = !inventory.isEmpty() && MusicUtils.listener.startedPlaying(inventory.inventory.itemUuid);
        
        if (++checkCounter >= 20)
        {
            checkCounter = 0;
            PositionedSoundRecord sound = MusicUtils.currentlyPlayedSongs.get(inventory.inventory.itemUuid);
            if (sound != null)
                musicToggleButton.isAltVariant = Minecraft.getMinecraft().getSoundHandler().isSoundPlaying(sound);
            else
                musicToggleButton.isAltVariant = false;
        }
        musicToggleButton.enabled = isEnabled;
        playNextButton.enabled = isEnabled && inventory.getSizeInventory() > 1;
        playPreviousButton.enabled = isEnabled && inventory.getSizeInventory() > 1;
        
        autoplayButton.isAltVariant = inventory.inventory.autoplay;
        shuffleButton.isAltVariant = inventory.inventory.shuffle;
        repeatButton.isAltVariant = inventory.inventory.repeat;
    }
}
