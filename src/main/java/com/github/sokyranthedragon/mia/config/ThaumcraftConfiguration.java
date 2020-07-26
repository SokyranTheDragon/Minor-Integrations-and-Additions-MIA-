package com.github.sokyranthedragon.mia.config;

import com.github.sokyranthedragon.mia.Mia;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import static net.minecraftforge.common.config.Config.*;

@Config(modid = Mia.MODID, name = "mia/thaumcraft")
@LangKey("mia.config.thaumcraft.title")
@Mod.EventBusSubscriber(modid = Mia.MODID)
public class ThaumcraftConfiguration
{
    @Name("Enable Thaumcraft additions")
    @Comment("Set to false to completely disable new Thaumcraft additions")
    @LangKey("mia.config.thaumcraft.additions_enabled")
    @RequiresMcRestart
    public static boolean thaumcraftAdditionsEnabled = true;
    
    @Name("Register aspects for other mods")
    @Comment("Set to false to not register any aspects for other mods")
    @LangKey("mia.config.thaumcraft.register_aspects")
    @RequiresMcRestart
    public static boolean registerAspects = true;
    
    @Name("Enable JER integration")
    @Comment("Set to false to completely disable integration with TConstruct")
    @LangKey("mia.config.shared.enable_jer_integration")
    @RequiresMcRestart
    public static boolean enableJerIntegration = true;
    
    @Name("Enable Thermal Expansion integration")
    @Comment("Set to false to completely disable integration with Thermal Expansion")
    @LangKey("mia.config.shared.enable_thermal_expansion_integration")
    @RequiresMcRestart
    public static boolean enableTeIntegration = true;
    
    @Name("Enable XU2 integration")
    @Comment("Set to false to completely disable integration with XU2")
    @LangKey("mia.config.shared.enable_extra_utils_integration")
    @RequiresMcRestart
    public static boolean enableXu2Integration = true;
    
    @Name("Enable Hatchery integration")
    @Comment("Set to false to completely disable integration with Hatchery")
    @LangKey("mia.config.shared.enable_hatchery_integration")
    @RequiresMcRestart
    public static boolean enableHatcheryIntegration = true;
    
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
    
    @Config.Name("Enable Chisel integration")
    @Config.Comment("Set to false to completely disable integration with Chisel")
    @Config.LangKey("mia.config.shared.enable_chisel_integration")
    @Config.RequiresMcRestart
    public static boolean enableChiselIntegration = true;
    
    @Name("Enable Industrial Foregoing integration")
    @Comment("Set to false to completely disable integration with Industrial Foregoing")
    @LangKey("mia.config.shared.enable_if_integration")
    @RequiresMcRestart
    public static boolean enableIFIntegration = true;
    
    
    /**
     * Inject the new values and save to the config file when the config has been changed from the GUI.
     *
     * @param event The event
     */
    @SubscribeEvent
    public static void onConfigChanged(final ConfigChangedEvent.OnConfigChangedEvent event)
    {
        if (event.getModID().equals(Mia.MODID))
        {
            ConfigManager.sync(Mia.MODID, Type.INSTANCE);
        }
    }
}
