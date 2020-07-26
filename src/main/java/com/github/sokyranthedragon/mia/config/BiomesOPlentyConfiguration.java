package com.github.sokyranthedragon.mia.config;

import com.github.sokyranthedragon.mia.Mia;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.fml.common.Mod;

import static net.minecraftforge.common.config.Config.*;

@Config(modid = Mia.MODID, name = "mia/biomes_o_plenty")
@LangKey("mia.config.biomes_o_plenty.title")
@Mod.EventBusSubscriber(modid = Mia.MODID)
public class BiomesOPlentyConfiguration
{
    @Name("Enable Natura additions")
    @Comment("Set to false to completely disable new Biomes O' Plenty additions")
    @LangKey("mia.config.biomes_o_plenty.additions_enabled")
    @RequiresMcRestart
    public static boolean bopAdditionsEnabled = true;
    
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
    
    @Name("Enable Hatchery integration")
    @Comment("Set to false to completely disable integration with Hatchery")
    @LangKey("mia.config.shared.enable_hatchery_integration")
    @RequiresMcRestart
    public static boolean enableHatcheryIntegration = true;
    
    @Name("Enable XU2 integration")
    @Comment("Set to false to completely disable integration with XU2")
    @LangKey("mia.config.shared.enable_extra_utils_integration")
    @RequiresMcRestart
    public static boolean enableXu2Integration = true;
    
    @Name("Enable Botania integration")
    @Comment("Set to false to completely disable integration with Botania")
    @LangKey("mia.config.shared.enable_botania_integration")
    @RequiresMcRestart
    public static boolean enableBotaniaIntegration = true;
    
    @Name("Enable Chisel integration")
    @Comment("Set to false to completely disable integration with Chisel")
    @LangKey("mia.config.shared.enable_chisel_integration")
    @RequiresMcRestart
    public static boolean enableChiselIntegration = true;
    
    @Name("Enable Industrial Foregoing integration")
    @Comment("Set to false to completely disable integration with Industrial Foregoing")
    @LangKey("mia.config.shared.enable_if_integration")
    @RequiresMcRestart
    public static boolean enableIFIntegration = true;
}
