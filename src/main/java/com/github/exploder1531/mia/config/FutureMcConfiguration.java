package com.github.exploder1531.mia.config;

import com.github.exploder1531.mia.Mia;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config(modid = Mia.MODID, name = "mia/future_mc")
@Config.LangKey("mia.config.future_mc.title")
@Mod.EventBusSubscriber(modid = Mia.MODID)
public class FutureMcConfiguration
{
    @Config.Name("Enable Thermal Expansion additions")
    @Config.Comment("Set to false to completely disable Thermal Expansion additions")
    @Config.LangKey("mia.config.future_mc.additions_enabled")
    @Config.RequiresMcRestart
    public static boolean futureMcAdditionsEnabled = true;
    
    @Config.Name("Enable external integrations")
    @Config.Comment("Set to false to prevent other mods from integrating with FutureMC")
    @Config.LangKey("mia.config.shared.enable_external_integrations")
    @Config.RequiresMcRestart
    public static boolean externalIntegrationsEnabled = true;
    
    @Config.Name("Enable Quark integration")
    @Config.Comment("Set to false to completely disable integration with Quark")
    @Config.LangKey("mia.config.shared.enable_quark_integration")
    @Config.RequiresMcRestart
    public static boolean enableQuarkIntegration = true;
    
    @Config.Name("Enable Thermal Expansion integration")
    @Config.Comment("Set to false to completely disable integration with Thermal Expansion")
    @Config.LangKey("mia.config.shared.enable_thermal_expansion_integration")
    @Config.RequiresMcRestart
    public static boolean enableTeIntegration = true;
    
    
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
