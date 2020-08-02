package com.github.sokyranthedragon.mia.config;

import com.github.sokyranthedragon.mia.Mia;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import static net.minecraftforge.common.config.Config.*;

@Config(modid = Mia.MODID, name = "mia/aether")
@LangKey("mia.config.aether.title")
@Mod.EventBusSubscriber(modid = Mia.MODID)
public class AetherConfig
{
    @Name("Enable external integrations")
    @Comment("Set to false to prevent other mods from integrating with Aether")
    @LangKey("mia.config.shared.enable_external_integrations")
    @RequiresMcRestart
    public static boolean externalIntegrationsEnabled = true;

    @Config.Name("Enable Aether additions")
    @Config.Comment("Set to false to completely disable new Aether additions")
    @Config.LangKey("mia.config.aether.additions_enabled")
    @Config.RequiresMcRestart
    public static boolean aetherAdditionsEnabled = true;

    @Config.Name("Enable Aether Continuation additions")
    @Config.Comment("Set to false to completely disable new Aether Continuation additions")
    @Config.LangKey("mia.config.aether_continuation.additions_enabled")
    @Config.RequiresMcRestart
    public static boolean aetherContinuationAdditionsEnabled = true;

    @Config.Name("Enable Aether: Lost Content additions")
    @Config.Comment("Set to false to completely disable new Aether: Lost Content additions")
    @Config.LangKey("mia.config.aether_lost_content.additions_enabled")
    @Config.RequiresMcRestart
    public static boolean aetherLostContentAdditionsEnabled = true;
    
    @Name("Enable Thermal Expansion integration")
    @Comment("Set to false to completely disable integration with Thermal Expansion")
    @LangKey("mia.config.shared.enable_thermal_expansion_integration")
    @RequiresMcRestart
    public static boolean enableTeIntegration = true;
    
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
    
    @Name("Enable JER integration")
    @Comment("Set to false to completely disable integration with JER")
    @LangKey("mia.config.shared.enable_jer_integration")
    @RequiresMcRestart
    public static boolean enableJerIntegration = true;
    
    @Name("Enable Industrial Foregoing integration")
    @Comment("Set to false to completely disable integration with Industrial Foregoing")
    @LangKey("mia.config.shared.enable_if_integration")
    @RequiresMcRestart
    public static boolean enableIFIntegration = true;

    @Name("Enable Chisel integration")
    @Comment("Set to false to completely disable integration with Chisel")
    @LangKey("mia.config.shared.enable_chisel_integration")
    @RequiresMcRestart
    public static boolean enableChiselIntegration = true;

    @Name("Enable XU2 integration")
    @Comment("Set to false to completely disable integration with XU2")
    @LangKey("mia.config.shared.enable_extra_utils_integration")
    @RequiresMcRestart
    public static boolean enableXu2Integration = true;
    
    @Name("Blacklist laser drill Aether biome for non-Aether ores")
    @Comment("Set to false to allow non-Aether ores to be acquirable from Aether using Industrial Foregoing laser drill")
    @LangKey("mia.config.aether.laser_drill_blacklist_aether")
    @RequiresMcRestart
    public static boolean blacklistAetherFromNonAetherOres = true;
    
    @Name("Allow new Freezer fuels")
    @Comment("Set to false to prevent any non-Aether items to be registered as Freezer fuel")
    @LangKey("mia.config.aether.allow_freezer_fuel")
    @RequiresMcRestart
    public static boolean allowNewFreezerFuel = true;
    
    
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
