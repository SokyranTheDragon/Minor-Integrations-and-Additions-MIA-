package com.github.sokyranthedragon.mia.core;

import com.github.sokyranthedragon.mia.config.MiaConfig;
import com.github.sokyranthedragon.mia.items.ItemMusicPlayer;
import com.github.sokyranthedragon.mia.utilities.RegisterUtils;
import com.github.sokyranthedragon.mia.utilities.annotations.FieldsAreNullableByDefault;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;

import javax.annotation.Nonnull;
import java.util.LinkedList;
import java.util.List;

@FieldsAreNullableByDefault
public class MiaItems
{
    private MiaItems()
    {
    }
    
    @Nonnull
    public static final List<Item> items = new LinkedList<>();
    
    // Mia
    public static ItemMusicPlayer musicPlayer = null;
    
    public static <T extends Item> T registerItem(T item, IForgeRegistry<Item> registry)
    {
        registry.register(item);
        items.add(item);
        return item;
    }
    
    public static void registerMiaItems(RegistryEvent.Register<Item> event)
    {
        IForgeRegistry<Item> registry = event.getRegistry();
        
        if (MiaConfig.musicPlayerEnabled)
            musicPlayer = registerItem(new ItemMusicPlayer(), registry);
    }
    
    @SideOnly(Side.CLIENT)
    public static void registerMiaItemRenderers()
    {
        RegisterUtils.registerItemRenderer(MiaItems.musicPlayer);
    }
}
