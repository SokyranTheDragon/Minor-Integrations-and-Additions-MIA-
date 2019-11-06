package com.github.exploder1531.mia.config;

import com.github.exploder1531.mia.Mia;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config(modid = Mia.MODID, name = "mia/iceandfire")
@Config.LangKey("mia.config.iceandfire.title")
@Mod.EventBusSubscriber(modid = Mia.MODID)
public class IceAndFireConfiguration
{
    @Config.Name("Enable Ice and Fire additions")
    @Config.Comment("Set to false to completely disable new Ice and Fire additions")
    @Config.LangKey("mia.config.iceandfire.additions_enabled")
    @Config.RequiresMcRestart
    public static boolean iceandfireAdditionsEnabled = true;
    
    @Config.Name("Enable JER integration")
    @Config.Comment("Set to false to completely disable integration with JER")
    @Config.LangKey("mia.config.shared.enable_jer_integration")
    @Config.RequiresMcRestart
    public static boolean enableJerIntegration = true;
    
    @Config.Name("Enable Thermal Expansion integration")
    @Config.Comment("Set to false to completely disable integration with Thermal Expansion")
    @Config.LangKey("mia.config.shared.enable_thermal_expansion_integration")
    @Config.RequiresMcRestart
    public static boolean enableTeIntegration = true;
    
    @Config.Name("Enable XU2 integration")
    @Config.Comment("Set to false to completely disable integration with XU2")
    @Config.LangKey("mia.config.shared.enable_extra_utils_integration")
    @Config.RequiresMcRestart
    public static boolean enableXu2Integration = true;

    @Config.Name("Enable Hatchery integration")
    @Config.Comment("Set to false to completely disable integration with Hatchery")
    @Config.LangKey("mia.config.shared.enable_hatchery_integration")
    @Config.RequiresMcRestart
    public static boolean enableHatcheryIntegration = true;

    @Config.Name("Enable Tinker's Construct integration")
    @Config.Comment("Set to false to completely disable integration with Tinker's Construct")
    @Config.LangKey("mia.config.shared.enable_tconstruct_integration")
    @Config.RequiresMcRestart
    public static boolean enableTConstructIntegration = true;
    
    
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
