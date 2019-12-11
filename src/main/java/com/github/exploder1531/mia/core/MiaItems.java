package com.github.exploder1531.mia.core;

import com.github.exploder1531.mia.config.MiaConfig;
import com.github.exploder1531.mia.items.ItemMusicPlayer;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.IForgeRegistry;

public class MiaItems
{
    private MiaItems()
    {
    }
    
    // Mia
    public static ItemMusicPlayer music_player = null;
    
    public static void registerItems(RegistryEvent.Register<Item> event)
    {
        IForgeRegistry<Item> registry = event.getRegistry();
        
        if (MiaConfig.musicPlayerEnabled)
            music_player = registerItem(new ItemMusicPlayer(), registry);
    }
    
    public static <T extends Item> T registerItem(T item, IForgeRegistry<Item> registry)
    {
        registry.register(item);
        return item;
    }
}
