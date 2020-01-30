package com.github.sokyranthedragon.mia.config;

import com.github.sokyranthedragon.mia.Mia;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import static net.minecraftforge.common.config.Config.*;

@Config(modid = Mia.MODID, name = "mia/botania")
@LangKey("mia.config.botania.title")
@Mod.EventBusSubscriber(modid = Mia.MODID)
public class BotaniaConfiguration
{
    @Config.Name("Enable Botania additions")
    @Config.Comment("Set to false to completely disable new Botania additions")
    @Config.LangKey("mia.config.botania.additions_enabled")
    @Config.RequiresMcRestart
    public static boolean botaniaAdditionsEnabled = true;
    
    @Name("Enable Thermal Expansion integration")
    @Comment("Set to false to completely disable integration with Thermal Expansion")
    @LangKey("mia.config.shared.enable_thermal_expansion_integration")
    @RequiresMcRestart
    public static boolean enableTeIntegration = true;
    
    @Config.Name("Enable external integrations")
    @Config.Comment("Set to false to prevent other mods from integrating with Botania")
    @Config.LangKey("mia.config.shared.enable_external_integrations")
    @Config.RequiresMcRestart
    public static boolean externalIntegrationsEnabled = true;
    
    @Name("Enable Hatchery integration")
    @Comment("Set to false to completely disable integration with Dungeon Tactics")
    @LangKey("mia.config.shared.enable_dungeon_tactics_integration")
    @RequiresMcRestart
    public static boolean enableDungeonTacticsIntegration = true;
    
    @Name("Enable FutureMC integration")
    @Comment("Set to false to completely disable integration with FutureMC")
    @LangKey("mia.config.shared.enable_future_mc_integration")
    @RequiresMcRestart
    public static boolean enableFutureMcIntegration = true;
    
    @Config.Name("Enable Hatchery integration")
    @Config.Comment("Set to false to completely disable integration with Hatchery")
    @Config.LangKey("mia.config.shared.enable_hatchery_integration")
    @Config.RequiresMcRestart
    public static boolean enableHatcheryIntegration = true;
    
    
    /**
     * Inject the new values and save to the config file when the config has been changed from the GUI.
     *
     * @param event The event
     */
    @SubscribeEvent
    public static void onConfigChanged(final ConfigChangedEvent.OnConfigChangedEvent event) {
        if (event.getModID().equals(Mia.MODID)) {
            ConfigManager.sync(Mia.MODID, Type.INSTANCE);
        }
    }
}
