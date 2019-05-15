package com.github.exploder1531.mia.config;

import com.github.exploder1531.mia.Mia;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config(modid = Mia.MODID, name = "mia/thermalexpansion")
@Config.LangKey("mia.config.thermalexpansion.title")
@Mod.EventBusSubscriber(modid = Mia.MODID)
public class TeConfiguration
{
    @Config.Name("Enable Thermal Expansion additions")
    @Config.Comment("Set to false to completely disable Thermal Expansion additions")
    @Config.LangKey("mia.config.thermalexpansion.additions_enabled")
    @Config.RequiresMcRestart
    public static boolean teAdditionsEnabled = true;
    
    @Config.Name("Enable external integrations")
    @Config.Comment("Set to false to prevent other mods from integrating with TE")
    @Config.LangKey("mia.config.shared.enable_external_integrations")
    @Config.RequiresMcRestart
    public static boolean externalIntegrationsEnabled = true;
    
    
    /**
     * Inject the new values and save to the config file when the config has been changed from the GUI.
     *
     * @param event The event
     */
    @SubscribeEvent
    public static void onConfigChanged(final ConfigChangedEvent.OnConfigChangedEvent event) {
        if (event.getModID().equals(Mia.MODID)) {
            ConfigManager.sync(Mia.MODID, Config.Type.INSTANCE);
        }
    }
}
