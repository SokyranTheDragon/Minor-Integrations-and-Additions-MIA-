package com.github.sokyranthedragon.mia.config;

import com.github.sokyranthedragon.mia.Mia;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config(modid = Mia.MODID, name = "mia/ender_io")
@Config.LangKey("mia.config.ender_io.title")
@Mod.EventBusSubscriber(modid = Mia.MODID)
public class EnderIoConfiguration
{
//    @Config.Name("Enable Ender IO additions")
//    @Config.Comment("Set to false to completely disable new Ender IO additions")
//    @Config.LangKey("mia.config.ender_io.additions_enabled")
//    @Config.RequiresMcRestart
//    public static boolean enderIoAdditionsEnabled = true;
    
    @Config.Name("Enable JER integration")
    @Config.Comment("Set to false to completely disable integration with JER")
    @Config.LangKey("mia.config.shared.enable_jer_integration")
    @Config.RequiresMcRestart
    public static boolean enableJerIntegration = true;
    
    /**
     * Inject the new values and save to the config file when the config has been changed from the GUI.
     *
     * @param event The event
     */
    @SubscribeEvent
    public static void onConfigChanged(final ConfigChangedEvent.OnConfigChangedEvent event)
    {
        if (event.getModID().equals(Mia.MODID))
            ConfigManager.sync(Mia.MODID, Config.Type.INSTANCE);
    }
}
