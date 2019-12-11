package com.github.exploder1531.mia.config;

import com.github.exploder1531.mia.Mia;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config(modid = Mia.MODID, name = "mia/base")
@Config.LangKey("mia.config.base.title")
@Mod.EventBusSubscriber(modid = Mia.MODID)
public class MiaConfig
{
    @Config.Name("Enable music player")
    @Config.Comment("Set to false to completely disable the music player item")
    @Config.LangKey("mia.config.base.music_player")
    @Config.RequiresMcRestart
    public static boolean musicPlayerEnabled = true;
    
    
    /**
     * Inject the new values and save to the config file when the config has been changed from the GUI.
     *
     * @param event The event
     */
    @SubscribeEvent
    public static void onConfigChanged(final ConfigChangedEvent.OnConfigChangedEvent event)
    {
        if (event.getModID().equals(Mia.MODID))
        {
            ConfigManager.sync(Mia.MODID, Config.Type.INSTANCE);
        }
    }
}
