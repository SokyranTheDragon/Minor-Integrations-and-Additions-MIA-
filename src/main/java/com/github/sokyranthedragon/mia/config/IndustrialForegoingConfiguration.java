package com.github.sokyranthedragon.mia.config;

import com.github.sokyranthedragon.mia.Mia;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config(modid = Mia.MODID, name = "mia/industrial_foregoing")
@Config.LangKey("mia.config.industrial_foregoing.title")
@Mod.EventBusSubscriber(modid = Mia.MODID)
public class IndustrialForegoingConfiguration
{
    @Config.Name("Enable external integrations")
    @Config.Comment("Set to false to prevent other mods from integrating with Industrial Foregoing")
    @Config.LangKey("mia.config.shared.enable_external_integrations")
    @Config.RequiresMcRestart
    public static boolean externalIntegrationsEnabled = true;
    
    @Config.Name("Enable Froster recipes")
    @Config.Comment("Set to false to prevent other mods from adding new recipes to the Froster")
    @Config.LangKey("mia.config.industrial_foregoing.enable_froster")
    @Config.RequiresMcRestart
    public static boolean enableFrosterRecipes = true;
    
    @Config.Name("Enable Laser Drill entries")
    @Config.Comment("Set to false to prevent other mods from adding new entries to the Laser Drill")
    @Config.LangKey("mia.config.industrial_foregoing.enable_laser_drill")
    @Config.RequiresMcRestart
    public static boolean enableLaserDrillEntries = true;
    
    @Config.Name("Enable Protein Generator entries")
    @Config.Comment("Set to false to prevent other mods from adding new entries to the Protein Generator")
    @Config.LangKey("mia.config.industrial_foregoing.enable_protein_generator")
    @Config.RequiresMcRestart
    public static boolean enableProteinGeneratorEntries = true;
    
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
