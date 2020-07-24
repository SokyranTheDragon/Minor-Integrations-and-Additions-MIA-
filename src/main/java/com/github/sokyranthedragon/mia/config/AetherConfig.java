package com.github.sokyranthedragon.mia.config;

import com.github.sokyranthedragon.mia.Mia;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config(modid = Mia.MODID, name = "mia/aether")
@Config.LangKey("mia.config.aether.title")
@Mod.EventBusSubscriber(modid = Mia.MODID)
public class AetherConfig
{
//    @Config.Name("Enable Aether additions")
//    @Config.Comment("Set to false to completely disable new Aether additions")
//    @Config.LangKey("mia.config.aether.additions_enabled")
//    @Config.RequiresMcRestart
//    public static boolean aetherAdditionsEnabled = true;
//
//    @Config.Name("Enable Aether Continuation additions")
//    @Config.Comment("Set to false to completely disable new Aether Continuation additions")
//    @Config.LangKey("mia.config.aether_continuation.additions_enabled")
//    @Config.RequiresMcRestart
//    public static boolean aetherContinuationAdditionsEnabled = true;
//
//    @Config.Name("Enable Aether: Lost Content additions")
//    @Config.Comment("Set to false to completely disable new Aether: Lost Content additions")
//    @Config.LangKey("mia.config.aether_lost_content.additions_enabled")
//    @Config.RequiresMcRestart
//    public static boolean aetherLostContentAdditionsEnabled = true;
    
    @Config.Name("Enable Thermal Expansion integration")
    @Config.Comment("Set to false to completely disable integration with Thermal Expansion")
    @Config.LangKey("mia.config.shared.enable_thermal_expansion_integration")
    @Config.RequiresMcRestart
    public static boolean enableTeIntegration = true;
    
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
