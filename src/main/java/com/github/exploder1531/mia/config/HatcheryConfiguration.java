package com.github.exploder1531.mia.config;

import com.github.exploder1531.mia.Mia;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config(modid = Mia.MODID, name = "mia/hatchery")
@Config.LangKey("mia.config.hatchery.title")
@Mod.EventBusSubscriber(modid = Mia.MODID)
public class HatcheryConfiguration
{
    @Config.Name("Enable Hatchery additions")
    @Config.Comment("Set to false to completely disable new Hatchery additions")
    @Config.LangKey("mia.config.hatchery.additions_enabled")
    @Config.RequiresMcRestart
    public static boolean hatcheryAdditionsEnabled = true;
    
    @Config.Name("Disable chicken display in nesting pen")
    @Config.Comment(
            {"Set to true to disable chickens from being rendered in nesting pens",
                    "This should hopefully improve performance issues that I've encountered myself with a big chicken farm"})
    public static boolean disableNestingPenChickenDisplay = false;
    
    @Config.Name("Enable external integrations")
    @Config.Comment("Set to false to prevent other mods from integrating with Hatchery")
    @Config.LangKey("mia.config.shared.enable_external_integrations")
    @Config.RequiresMcRestart
    public static boolean externalIntegrationsEnabled = true;
    
    @Config.Name("Enable external lucky egg loot")
    @Config.Comment(
            {"Set to true to enable any lucky egg loot file unrelated to integrated mods to be loaded from inside the config folder",
                    "Even if this is option is enabled the configuration for integrated mods won't be if they themselves are disabled",
                    "Using this instead of editing the loot in Hatchery config allows you to keep it unchanged while still adding your own items"})
    @Config.LangKey("mia.config.hatchery.register_custom_eggs")
    @Config.RequiresMcRestart
    public static boolean registerCustomLuckyEggLoot = false;
    
    
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
            ConfigManager.sync(Mia.MODID, Config.Type.INSTANCE);
        }
    }
}
