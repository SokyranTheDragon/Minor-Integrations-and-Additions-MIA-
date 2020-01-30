package com.github.sokyranthedragon.mia.core;

import com.github.sokyranthedragon.mia.config.MiaConfig;
import com.github.sokyranthedragon.mia.items.ItemMusicPlayer;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.LinkedList;
import java.util.List;

public class MiaItems
{
    private MiaItems()
    {
    }
    
    public static final List<Item> items = new LinkedList<>();
    
    // Mia
    public static ItemMusicPlayer musicPlayer = null;
    
    public static void registerItems(RegistryEvent.Register<Item> event)
    {
        IForgeRegistry<Item> registry = event.getRegistry();
        
        if (MiaConfig.musicPlayerEnabled)
            musicPlayer = registerItem(new ItemMusicPlayer(), registry);
    }
    
    public static <T extends Item> T registerItem(T item, IForgeRegistry<Item> registry)
    {
        registry.register(item);
        items.add(item);
        return item;
    }
}
