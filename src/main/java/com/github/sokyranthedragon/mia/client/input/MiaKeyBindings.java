package com.github.sokyranthedragon.mia.client.input;

import com.github.sokyranthedragon.mia.Mia;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class MiaKeyBindings
{
    private MiaKeyBindings()
    {
    }
    
    public static KeyBinding openGui;
    public static KeyBinding musicToggle;
    public static KeyBinding nextSong;
    public static KeyBinding previousSong;
    
    public static boolean isAnyPressed()
    {
        return openGui.isPressed() || musicToggle.isPressed() || nextSong.isPressed() || previousSong.isPressed();
    }
    
    public static void register()
    {
        if (openGui != null)
        {
            Mia.LOGGER.error("KeyBindings registering was called more than once");
            return;
        }
        
        openGui = new KeyBinding("key.music_player_gui", 82, "key.categories.music_player");
        musicToggle = new KeyBinding("key.music_player_toggle", 80, "key.categories.music_player");
        nextSong = new KeyBinding("key.music_player_next", 81, "key.categories.music_player");
        previousSong = new KeyBinding("key.music_player_previous", 79, "key.categories.music_player");
        
        ClientRegistry.registerKeyBinding(openGui);
        ClientRegistry.registerKeyBinding(musicToggle);
        ClientRegistry.registerKeyBinding(nextSong);
        ClientRegistry.registerKeyBinding(previousSong);
    }
}
