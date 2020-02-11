package com.github.sokyranthedragon.mia.config;

import com.github.sokyranthedragon.mia.Mia;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import static net.minecraftforge.common.config.Config.*;
import static net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@Config(modid = Mia.MODID, name = "mia/chisel")
@LangKey("mia.config.chisel.title")
@EventBusSubscriber(modid = Mia.MODID)
public class ChiselConfiguration
{
    @Name("Enable external integrations")
    @Comment("Set to false to prevent other mods from integrating with Chisel")
    @LangKey("mia.config.shared.enable_external_integrations")
    @RequiresMcRestart
    public static boolean externalIntegrationsEnabled = true;
    
    
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
            ConfigManager.sync(Mia.MODID, Type.INSTANCE);
        }
    }
}
