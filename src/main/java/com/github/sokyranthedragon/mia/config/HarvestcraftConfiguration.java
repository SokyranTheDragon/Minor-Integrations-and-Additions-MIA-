package com.github.sokyranthedragon.mia.config;

import com.github.sokyranthedragon.mia.Mia;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config(modid = Mia.MODID, name = "mia/harvestcraft")
@Config.LangKey("mia.config.harvestcraft.title")
@Mod.EventBusSubscriber(modid = Mia.MODID)
public class HarvestcraftConfiguration
{
    @Config.Name("Enable Thermal Foundation additions")
    @Config.Comment("Set to false to completely disable new Harvestcraft additions")
    @Config.LangKey("mia.config.harvestcraft.additions_enabled")
    @Config.RequiresMcRestart
    public static boolean harvestcraftAdditionsEnabled = true;
    
    @Config.Name("Enable external integrations")
    @Config.Comment("Set to false to prevent other mods from integrating with FutureMC")
    @Config.LangKey("mia.config.shared.enable_external_integrations")
    @Config.RequiresMcRestart
    public static boolean externalIntegrationsEnabled = true;
    
    @Config.Name("Enable Thermal Expansion integration")
    @Config.Comment("Set to false to completely disable integration with Thermal Expansion")
    @Config.LangKey("mia.config.shared.enable_thermal_expansion_integration")
    @Config.RequiresMcRestart
    public static boolean enableTeIntegration = true;
    
    @Config.Name("Squid drops calamari")
    @Config.Comment("If enabled, calamari will be added to possible squid drops")
    @Config.LangKey("mia.config.harvestcraft.squid_drops_calamari")
    @Config.RequiresMcRestart
    public static boolean squidDropsCalamari = true;
    
    @Config.Name("Enable JER integration")
    @Config.Comment("Set to false to completely disable integration with JER")
    @Config.LangKey("mia.config.shared.enable_jer_integration")
    @Config.RequiresMcRestart
    public static boolean enableJerIntegration = true;
    
    @Config.Name("Enable FutureMC integration")
    @Config.Comment("Set to false to completely disable integration with FutureMC")
    @Config.LangKey("mia.config.shared.enable_future_mc_integration")
    @Config.RequiresMcRestart
    public static boolean enableFutureMcIntegration = true;
    
    
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
