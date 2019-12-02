package com.github.exploder1531.mia.core;

import com.github.exploder1531.mia.items.ItemMusicPlayer;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;

public class MiaItems
{
    private MiaItems()
    {
    }
    
    public static ItemMusicPlayer music_player = null;
    
    public static void registerItems(RegistryEvent.Register<Item> event)
    {
        music_player = new ItemMusicPlayer();
        
        event.getRegistry().register(music_player);
    }
}
