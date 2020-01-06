package com.github.exploder1531.mia.config;

import com.github.exploder1531.mia.Mia;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config(modid = Mia.MODID, name = "mia/dungeon_tactics")
@Config.LangKey("mia.config.dungeon_tactics.title")
@Mod.EventBusSubscriber(modid = Mia.MODID)
public class DungeonTacticsConfiguration
{
    @Config.Name("Enable Ice and Fire additions")
    @Config.Comment("Set to false to completely disable new Dungeon Tactics additions")
    @Config.LangKey("mia.config.dungeon_tactics.additions_enabled")
    @Config.RequiresMcRestart
    public static boolean dungeonTacticsAdditionsEnabled = true;
    
    @Config.Name("Enable Thermal Expansion integration")
    @Config.Comment("Set to false to completely disable integration with Thermal Expansion")
    @Config.LangKey("mia.config.shared.enable_thermal_expansion_integration")
    @Config.RequiresMcRestart
    public static boolean enableTeIntegration = true;
    
    @Config.Name("Enable Tinker's Construct integration")
    @Config.Comment("Set to false to completely disable integration with Tinker's Construct")
    @Config.LangKey("mia.config.shared.enable_tconstruct_integration")
    @Config.RequiresMcRestart
    public static boolean enableTConstructIntegration = true;
    
    @Config.Name("Enable JER integration")
    @Config.Comment("Set to false to completely disable integration with JER")
    @Config.LangKey("mia.config.shared.enable_jer_integration")
    @Config.RequiresMcRestart
    public static boolean enableJerIntegration = true;
    
    @Config.Name("Enable external loot additions for loot bags")
    @Config.LangKey("mia.config.dungeon_tactics.register_custom_loot")
    @Config.RequiresMcRestart
    public static boolean registerCustomBagLoot = true;
    
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
    public static void onConfigChanged(final ConfigChangedEvent.OnConfigChangedEvent event)
    {
        if (event.getModID().equals(Mia.MODID))
            ConfigManager.sync(Mia.MODID, Config.Type.INSTANCE);
    }
}
