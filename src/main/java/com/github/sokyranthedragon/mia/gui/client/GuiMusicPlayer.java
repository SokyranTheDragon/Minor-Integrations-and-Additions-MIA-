package com.github.sokyranthedragon.mia.gui.client;

import com.github.sokyranthedragon.mia.gui.client.buttons.GuiButtonImageDisableable;
import com.github.sokyranthedragon.mia.gui.client.buttons.GuiToggleButton;
import com.github.sokyranthedragon.mia.gui.container.ContainerMusicPlayer;
import com.github.sokyranthedragon.mia.inventory.MusicPlayerInventory;
import com.github.sokyranthedragon.mia.utilities.MusicUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiButtonImage;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Arrays;

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
                this.drawHoveringText(Arrays.asList(
                        I18n.format("mia.gui.music_player.tooltip.shuffle_alt"),
                        I18n.format("mia.gui.music_player.tooltip.autoplay_required")), mouseX, mouseY);
            else
                this.drawHoveringText(Arrays.asList(
                        I18n.format("mia.gui.music_player.tooltip.shuffle"),
                        I18n.format("mia.gui.music_player.tooltip.autoplay_required")), mouseX, mouseY);
        }
        else if (repeatButton.isMouseOver())
        {
            if (repeatButton.isAltVariant)
                this.drawHoveringText(Arrays.asList(
                        I18n.format("mia.gui.music_player.tooltip.repeat_alt"),
                        I18n.format("mia.gui.music_player.tooltip.autoplay_required")), mouseX, mouseY);
            else
                this.drawHoveringText(Arrays.asList(
                        I18n.format("mia.gui.music_player.tooltip.repeat"),
                        I18n.format("mia.gui.music_player.tooltip.autoplay_required")), mouseX, mouseY);
        }
        else if (musicToggleButton.isMouseOver() && !MusicUtils.isMusicOn())
            this.drawHoveringText(I18n.format("mia.gui.music_player.tooltip.sound_off"), mouseX, mouseY);
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
        musicToggleButton = addButton(new GuiToggleButton(2, 111 + guiLeft, 28 + guiTop, 20, 20, 0, 198, 19, 19, texture));
        playNextButton = addButton(new GuiButtonImageDisableable(3, 131 + guiLeft, 28 + guiTop, 20, 20, 38, 198, 19, texture));
        playPreviousButton = addButton(new GuiButtonImageDisableable(4, 91 + guiLeft, 28 + guiTop, 20, 20, 57, 198, 19, texture));
        autoplayButton = addButton(new GuiToggleButton(5, 25 + guiLeft, 28 + guiTop, 20, 20, 76, 198, 19, 19, texture));
        shuffleButton = addButton(new GuiToggleButton(6, 45 + guiLeft, 28 + guiTop, 20, 20, 152, 198, 19, 19, texture));
        repeatButton = addButton(new GuiToggleButton(7, 65 + guiLeft, 28 + guiTop, 20, 20, 114, 198, 19, 19, texture));
        
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
        musicToggleButton.enabled = isEnabled && MusicUtils.isMusicOn();
        playNextButton.enabled = isEnabled && inventory.getSizeInventory() > 1;
        playPreviousButton.enabled = isEnabled && inventory.getSizeInventory() > 1;
        
        autoplayButton.isAltVariant = inventory.inventory.autoplay;
        shuffleButton.isAltVariant = inventory.inventory.shuffle;
        repeatButton.isAltVariant = inventory.inventory.repeat;
    }
}
