package com.github.sokyranthedragon.mia.config;

import com.github.sokyranthedragon.mia.Mia;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import static net.minecraftforge.common.config.Config.*;

@Config(modid = Mia.MODID, name = "mia/natura")
@LangKey("mia.config.natura.title")
@Mod.EventBusSubscriber(modid = Mia.MODID)
public class NaturaConfiguration
{
    @Name("Enable Natura additions")
    @Comment("Set to false to completely disable new Natura additions")
    @LangKey("mia.config.natura.additions_enabled")
    @RequiresMcRestart
    public static boolean naturaAdditionsEnabled = true;

    @Name("Enable JER integration")
    @Comment("Set to false to completely disable integration with JER")
    @LangKey("mia.config.shared.enable_jer_integration")
    @RequiresMcRestart
    public static boolean enableJerIntegration = true;

    @Name("Enable Thermal Expansion integration")
    @Comment("Set to false to completely disable integration with Thermal Expansion")
    @LangKey("mia.config.shared.enable_thermal_expansion_integration")
    @RequiresMcRestart
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
    
    @Config.Name("Enable Hatchery integration")
    @Config.Comment("Set to false to completely disable integration with Hatchery")
    @Config.LangKey("mia.config.shared.enable_hatchery_integration")
    @Config.RequiresMcRestart
    public static boolean enableHatcheryIntegration = true;
    
    @Config.Name("Enable XU2 integration")
    @Config.Comment("Set to false to completely disable integration with XU2")
    @Config.LangKey("mia.config.shared.enable_extra_utils_integration")
    @Config.RequiresMcRestart
    public static boolean enableXu2Integration = true;


    /**
     * Inject the new values and save to the config file when the config has been changed from the GUI.
     *
     * @param event The event
     */
    @SubscribeEvent
    public static void onConfigChanged(final ConfigChangedEvent.OnConfigChangedEvent event)
    {
        if (event.getModID().equals(Mia.MODID))
            ConfigManager.sync(Mia.MODID, Type.INSTANCE);
    }
}
