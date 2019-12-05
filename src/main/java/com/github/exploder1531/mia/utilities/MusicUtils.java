package com.github.exploder1531.mia.utilities;

import com.github.exploder1531.mia.handlers.MusicPlayerStackHandler;
import com.google.common.collect.Maps;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.*;
import net.minecraft.item.ItemRecord;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Map;

@SideOnly(Side.CLIENT)
@ParametersAreNonnullByDefault
public class MusicUtils
{
    public static SoundEffectListener listener = new SoundEffectListener();
    
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
        
        if (musicPlayer.currentSong != null && soundHandler.isSoundPlaying(musicPlayer.currentSong))
            stopSong(musicPlayer, soundHandler);
        else
            playSong(musicPlayer, soundHandler);
    }
    
    @SuppressWarnings("unused")
    public static void stopSong(MusicPlayerStackHandler musicPlayer)
    {
        stopSong(musicPlayer, Minecraft.getMinecraft().getSoundHandler());
    }
    
    public static void playSong(MusicPlayerStackHandler musicPlayer)
    {
        SoundHandler soundHandler = Minecraft.getMinecraft().getSoundHandler();
        
        stopSong(musicPlayer, soundHandler);
        playSong(musicPlayer, soundHandler);
    }
    
    private static void stopSong(MusicPlayerStackHandler musicPlayer, SoundHandler soundHandler)
    {
        if (musicPlayer.currentSong != null)
        {
            soundHandler.stopSound(musicPlayer.currentSong);
            musicPlayer.currentSong = null;
        }
    }
    
    private static void playSong(MusicPlayerStackHandler musicPlayer, SoundHandler soundHandler)
    {
        ItemStack currentSong = musicPlayer.getCurrentSong();
        if (!currentSong.isEmpty() && currentSong.getItem() instanceof ItemRecord)
        {
            musicPlayer.startedPlaying = false;
            musicPlayer.currentSong = PositionedSoundRecord.getMusicRecord(((ItemRecord) currentSong.getItem()).getSound());
            listener.addListener(musicPlayer, musicPlayer.currentSong);
            soundHandler.playSound(musicPlayer.currentSong);
        }
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
    
    public static class SoundEffectListener implements ISoundEventListener
    {
        private Map<ISound, MusicPlayerStackHandler> listeners = Maps.newHashMap();
        
        private SoundEffectListener()
        {
        }
        
        @Override
        public void soundPlay(ISound sound, SoundEventAccessor soundEventAccessor)
        {
            MusicPlayerStackHandler musicPlayer = listeners.remove(sound);
            
            if (musicPlayer != null)
                musicPlayer.startedPlaying = true;
        }
        
        public void addListener(MusicPlayerStackHandler musicPlayer, ISound sound)
        {
            listeners.put(sound, musicPlayer);
        }
    }
}
