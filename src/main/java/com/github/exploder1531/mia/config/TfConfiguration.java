package com.github.exploder1531.mia.config;

import com.github.exploder1531.mia.Mia;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config(modid = Mia.MODID, name = "mia/thermalfoundation")
@Config.LangKey("mia.config.thermalfoundation.title")
@Mod.EventBusSubscriber(modid = Mia.MODID)
public class TfConfiguration
{
    @Config.Name("Enable Thermal Foundation additions")
    @Config.Comment("Set to false to completely disable new Thermal Foundation additions")
    @Config.LangKey("mia.config.thermalfoundation.additions_enabled")
    @Config.RequiresMcRestart
    public static boolean tfAdditionsEnabled = true;
    
    @Config.Name("Enable JER integration")
    @Config.Comment("Set to false to completely disable integration with JER")
    @Config.LangKey("mia.config.shared.enable_jer_integration")
    @Config.RequiresMcRestart
    public static boolean enableJerIntegration = true;
    
//    @Config.Name("Enable TConstruct integration")
//    @Config.Comment("Set to false to completely disable integration with TConstruct")
//    public static boolean enableTConstructIntegration = true;
    
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
    
    @Config.Name("Enable Hatchery integration")
    @Config.Comment("Set to false to completely disable integration with Dungeon Tactics")
    @Config.LangKey("mia.config.shared.enable_dungeon_tactics_integration")
    @Config.RequiresMcRestart
    public static boolean enableDungeonTacticsIntegration = true;
    
    @Config.Name("Enable FutureMC integration")
    @Config.Comment("Set to false to completely disable integration with FutureMC")
    @Config.LangKey("mia.config.shared.enable_future_mc_integration")
    @Config.RequiresMcRestart
    public static boolean enableFutureMcIntegration = true;
    
    @Config.Name("Enable Quark integration")
    @Config.Comment("Set to false to completely disable integration with Quark")
    @Config.LangKey("mia.config.shared.enable_quark_integration")
    @Config.RequiresMcRestart
    public static boolean enableQuarkIntegration = true;
    
    
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
