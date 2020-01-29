package com.github.sokyranthedragon.mia.config;

import com.github.sokyranthedragon.mia.Mia;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.fml.common.Mod;

@Config(modid = Mia.MODID, name = "mia/biomes_o_plenty")
@Config.LangKey("mia.config.biomes_o_plenty.title")
@Mod.EventBusSubscriber(modid = Mia.MODID)
public class BiomesOPlentyConfiguration
{
    @Config.Name("Enable Natura additions")
    @Config.Comment("Set to false to completely disable new Biomes O' Plenty additions")
    @Config.LangKey("mia.config.biomes_o_plenty.additions_enabled")
    @Config.RequiresMcRestart
    public static boolean bopAdditionsEnabled = true;
    
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
    
    @Config.Name("Enable Botania integration")
    @Config.Comment("Set to false to completely disable integration with Botania")
    @Config.LangKey("mia.config.shared.enable_botania_integration")
    @Config.RequiresMcRestart
    public static boolean enableBotaniaIntegration = true;
}
