package com.github.exploder1531.mia.events;

import com.github.exploder1531.mia.Mia;
import com.github.exploder1531.mia.capabilities.MusicPlayerCapabilityProvider;
import com.github.exploder1531.mia.core.MiaItems;
import com.github.exploder1531.mia.gui.GuiHandler;
import com.github.exploder1531.mia.handlers.MusicPlayerStackHandler;
import com.github.exploder1531.mia.utilities.InventoryUtils;
import com.github.exploder1531.mia.utilities.MusicUtils;
import com.google.common.collect.ImmutableSet;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.commons.lang3.tuple.ImmutableTriple;

import static com.github.exploder1531.mia.client.input.MiaKeyBindings.*;

@Mod.EventBusSubscriber(modid = Mia.MODID)
public class ClientEvents
{
    @SideOnly(Side.CLIENT)
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void keyInput(TickEvent.ClientTickEvent event)
    {
        EntityPlayerSP player = Minecraft.getMinecraft().player;
        
        if (player == null)
            return;
        
        if (Minecraft.getMinecraft().gameSettings.keyBindDrop.isKeyDown())
        {
            MusicPlayerStackHandler capability = player.getHeldItemMainhand().getCapability(MusicPlayerCapabilityProvider.ITEM_HANDLER_CAPABILITY, null);
            
            if (capability != null)
                MusicUtils.stopSong(capability);
        }
        
        boolean openGuiPressed = openGui.isPressed();
        boolean musicTogglePressed = musicToggle.isPressed();
        boolean nextSongPressed = nextSong.isPressed();
        boolean previousSongPressed = previousSong.isPressed();
        
        if (openGuiPressed || musicTogglePressed || nextSongPressed || previousSongPressed)
        {
            ImmutableTriple<ItemStack, Integer, Integer> itemInInventory = InventoryUtils.findItemInInventory(player, MiaItems.music_player);
            MusicPlayerStackHandler capability = itemInInventory.left.getCapability(MusicPlayerCapabilityProvider.ITEM_HANDLER_CAPABILITY, null);
            
            if (!itemInInventory.left.isEmpty() && capability != null)
            {
                if (openGuiPressed)
                    //noinspection SuspiciousNameCombination
                    player.openGui(Mia.instance, GuiHandler.MUSIC_PLAYER, player.world, itemInInventory.middle, itemInInventory.right, 0);
                
                if (capability.startedPlaying)
                {
                    if (musicTogglePressed)
                        MusicUtils.toggleSong(capability);
                    else if (nextSongPressed)
                        MusicUtils.playNext(capability);
                    else if (previousSongPressed)
                        MusicUtils.playPrevious(capability);
                }
            }
        }
        
        if (player.ticksExisted % 20 == 0)
            return;
        
        ImmutableSet<ItemStack> musicPlayers = InventoryUtils.getAllItemsOfType(player, MiaItems.music_player);
        
        for (ItemStack item : musicPlayers)
        {
            MusicPlayerStackHandler capability = item.getCapability(MusicPlayerCapabilityProvider.ITEM_HANDLER_CAPABILITY, null);
            
            if (capability != null && capability.startedPlaying)
            {
                if (capability.currentSong != null && !Minecraft.getMinecraft().getSoundHandler().isSoundPlaying(capability.currentSong))
                {
                    capability.currentSong = null;
    
                    if (capability.autoplay)
                    {
                        if (capability.repeat)
                            MusicUtils.toggleSong(capability);
                        else
                            MusicUtils.playNext(capability);
                    }
                }
            }
        }
    }
}