package com.github.exploder1531.mia.utilities;

import baubles.api.BaublesApi;
import baubles.api.cap.IBaublesItemHandler;
import com.github.exploder1531.mia.Mia;
import com.github.exploder1531.mia.capabilities.MusicPlayerCapabilityProvider;
import com.github.exploder1531.mia.handlers.MusicPlayerStackHandler;
import com.github.exploder1531.mia.integrations.ModIds;
import com.github.exploder1531.mia.network.MessageSyncMusicPlayer;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.Maps;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemRecord;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Map;
import java.util.UUID;

@SideOnly(Side.CLIENT)
@ParametersAreNonnullByDefault
public class MusicUtils
{
    public static SoundEffectListener listener = new SoundEffectListener();
    public static Map<UUID, PositionedSoundRecord> currentlyPlayedSongs = Maps.newHashMap();
    
    static
    {
        Minecraft.getMinecraft().getSoundHandler().addListener(listener);
    }
    
    private MusicUtils()
    {
    }
    
    public static void toggleSong(MusicPlayerStackHandler musicPlayer)
    {
        SoundHandler soundHandler = Minecraft.getMinecraft().getSoundHandler();
        
        PositionedSoundRecord currentSong = currentlyPlayedSongs.remove(musicPlayer.itemUuid);
        
        if (currentSong != null && soundHandler.isSoundPlaying(currentSong))
            soundHandler.stopSound(currentSong);
        else
            playSong(musicPlayer, soundHandler);
    }
    
    public static void playSong(MusicPlayerStackHandler musicPlayer)
    {
        SoundHandler soundHandler = Minecraft.getMinecraft().getSoundHandler();
        
        stopSong(musicPlayer, soundHandler);
        playSong(musicPlayer, soundHandler);
    }
    
    private static void playSong(MusicPlayerStackHandler musicPlayer, SoundHandler soundHandler)
    {
        if (Minecraft.getMinecraft().gameSettings.getSoundLevel(SoundCategory.MASTER) <= 0 || Minecraft.getMinecraft().gameSettings.getSoundLevel(SoundCategory.MUSIC) <= 0)
        {
            Mia.LOGGER.info("Master or music volume is set to 0, no music will be played.");
            return;
        }
        
        ItemStack record = musicPlayer.getCurrentSong();
        if (!record.isEmpty() && record.getItem() instanceof ItemRecord)
        {
            PositionedSoundRecord currentSong = new PositionedSoundRecord(((ItemRecord) record.getItem()).getSound().getSoundName(), SoundCategory.MUSIC, 4.0f, 1.0f, false, 0, ISound.AttenuationType.NONE, 0, 0, 0);
            listener.addListener(musicPlayer.itemUuid, currentSong);
            currentlyPlayedSongs.put(musicPlayer.itemUuid, currentSong);
            soundHandler.playSound(currentSong);
        }
    }
    
    public static void stopSong(MusicPlayerStackHandler musicPlayer)
    {
        stopSong(musicPlayer, Minecraft.getMinecraft().getSoundHandler());
    }
    
    private static void stopSong(MusicPlayerStackHandler musicPlayer, SoundHandler soundHandler)
    {
        PositionedSoundRecord currentSong = currentlyPlayedSongs.remove(musicPlayer.itemUuid);
        
        if (currentSong != null)
            soundHandler.stopSound(currentSong);
    }
    
    public static void playNext(MusicPlayerStackHandler musicPlayer)
    {
        if (musicPlayer.getSlots() > 0)
        {
            ItemStack currentSong = musicPlayer.getCurrentSong();
            
            if (currentSong.isEmpty())
                musicPlayer.setCurrentSongSlot(0);
            else
                musicPlayer.nextSong();
            
            playSong(musicPlayer);
        }
    }
    
    public static void playPrevious(MusicPlayerStackHandler musicPlayer)
    {
        if (musicPlayer.getSlots() > 0)
        {
            ItemStack currentSong = musicPlayer.getCurrentSong();
            
            if (currentSong.isEmpty())
                musicPlayer.setCurrentSongSlot(0);
            else
                musicPlayer.previousSong();
            
            playSong(musicPlayer);
        }
    }
    
    public static void randomNext(MusicPlayerStackHandler musicPlayer)
    {
        int slots = musicPlayer.getSlots();
        
        if (slots >= 1)
        {
            if (slots >= 2)
            {
                int newSlot = Minecraft.getMinecraft().world.rand.nextInt(slots - 1);
                if (newSlot == musicPlayer.getCurrentSongSlot())
                    newSlot++;
                musicPlayer.setCurrentSongSlot(newSlot);
            }
            playSong(musicPlayer);
        }
    }
    
    public static void updateMusicPlayerWithUuid(EntityPlayer player, MusicPlayerStackHandler musicPlayer)
    {
        if (ModIds.BAUBLES.isLoaded)
        {
            IBaublesItemHandler baubles = BaublesApi.getBaublesHandler(player);
            
            for (int i = 0; i < baubles.getSlots(); i++)
            {
                ItemStack stack = baubles.getStackInSlot(i);
                MusicPlayerStackHandler capability = stack.getCapability(MusicPlayerCapabilityProvider.ITEM_HANDLER_CAPABILITY, null);
                if (capability != null && capability.itemUuid.equals(musicPlayer.itemUuid))
                {
                    Mia.network.sendToServer(new MessageSyncMusicPlayer(3, i, musicPlayer, false));
                    return;
                }
            }
        }
        
        for (int i = 0; i < player.inventory.mainInventory.size(); i++)
        {
            ItemStack stack = player.inventory.mainInventory.get(i);
            MusicPlayerStackHandler capability = stack.getCapability(MusicPlayerCapabilityProvider.ITEM_HANDLER_CAPABILITY, null);
            if (capability != null && capability.itemUuid.equals(musicPlayer.itemUuid))
            {
                Mia.network.sendToServer(new MessageSyncMusicPlayer(2, i, musicPlayer, false));
                return;
            }
        }
        
        MusicPlayerStackHandler capability = player.getHeldItemOffhand().getCapability(MusicPlayerCapabilityProvider.ITEM_HANDLER_CAPABILITY, null);
        if (capability != null && capability.itemUuid.equals(musicPlayer.itemUuid))
            Mia.network.sendToServer(new MessageSyncMusicPlayer(1, 0, musicPlayer, false));
    }
    
    public static class SoundEffectListener implements ISoundEventListener
    {
        private BiMap<ISound, UUID> listeners = HashBiMap.create();
        
        private SoundEffectListener()
        {
        }
        
        @Override
        public void soundPlay(ISound sound, SoundEventAccessor soundEventAccessor)
        {
            listeners.remove(sound);
        }
        
        public void addListener(UUID id, ISound sound)
        {
            listeners.put(sound, id);
        }
        
        public boolean startedPlaying(UUID id)
        {
            return !listeners.inverse().containsKey(id);
        }
    }
}
