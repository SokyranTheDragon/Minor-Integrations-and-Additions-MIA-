package com.github.exploder1531.mia.config;

import com.github.exploder1531.mia.Mia;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config(modid = Mia.MODID, name = "mia/mocreatures")
@Config.LangKey("mia.config.mocreatures.title")
@Mod.EventBusSubscriber(modid = Mia.MODID)
public class MoCreaturesConfiguration
{
    @Config.Name("Enable Ice and Fire additions")
    @Config.Comment("Set to false to completely disable new Ice and Fire additions")
    @Config.LangKey("mia.config.mocreatures.additions_enabled")
    @Config.RequiresMcRestart
    public static boolean moCreaturesAdditionsEnabled = true;
    
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
    
//    @Config.Name("Enable Hatchery integration")
//    @Config.Comment("Set to false to completely disable integration with Hatchery")
//    @Config.LangKey("mia.config.shared.enable_hatchery_integration")
//    @Config.RequiresMcRestart
//    public static boolean enableHatcheryIntegration = true;
    
    @Config.Name("Enable Hatchery integration")
    @Config.Comment("Set to false to completely disable integration with Dungeon Tactics")
    @Config.LangKey("mia.config.shared.enable_dungeon_tactics_integration")
    @Config.RequiresMcRestart
    public static boolean enableDungeonTacticsIntegration = true;
    
    @Config.Name("Increase damage to werewolves from other mod silver weapons")
    @Config.Comment("While dealing damage to werewolves, Mo' Creatures checks other mod silver weapon base damage, but when the damage dealt it doesn't take into" +
            " consideration that the weapons have higher damage than the base damage. Setting this to true ensures that other silver weapons that would " +
            "deal less damage than that from Mo' Creatures have their damage increased to match that value.")
    @Config.LangKey("mia.config.mocreatures.buff_silver_weapons")
    public static boolean buffOtherModSilverWeapons = true;
    
    @Config.Name("Replace cod and clownfish drops with their corresponding item")
    @Config.Comment("Replaces items dropped from cod and clownfish animals from the original, normal fish item, into actual cod and clownfish items")
    @Config.LangKey("mia.config.mocreatures.replace_fish_drops")
    public static boolean replaceFishDrops = true;
    
    
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
