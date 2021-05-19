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
    @Name("Enable Botania additions")
    @Comment("Set to false to completely disable new Botania additions")
    @LangKey("mia.config.botania.additions_enabled")
    @RequiresMcRestart
    public static boolean botaniaAdditionsEnabled = true;
    
    @Name("Enable Thermal Expansion integration")
    @Comment("Set to false to completely disable integration with Thermal Expansion")
    @LangKey("mia.config.shared.enable_thermal_expansion_integration")
    @RequiresMcRestart
    public static boolean enableTeIntegration = true;
    
    @Name("Enable external integrations")
    @Comment("Set to false to prevent other mods from integrating with Botania")
    @LangKey("mia.config.shared.enable_external_integrations")
    @RequiresMcRestart
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
    
    @Name("Enable Hatchery integration")
    @Comment("Set to false to completely disable integration with Hatchery")
    @LangKey("mia.config.shared.enable_hatchery_integration")
    @RequiresMcRestart
    public static boolean enableHatcheryIntegration = true;
    
    @Name("Enable Chisel integration")
    @Comment("Set to false to completely disable integration with Chisel")
    @LangKey("mia.config.shared.enable_chisel_integration")
    @RequiresMcRestart
    public static boolean enableChiselIntegration = true;
    
    @Name("Enable JER integration")
    @Comment("Set to false to completely disable integration with JER")
    @LangKey("mia.config.shared.enable_jer_integration")
    @RequiresMcRestart
    public static boolean enableJerIntegration = true;
    
    @Name("Orechid Vacuam ore dictionary")
    @Comment({ "List of all the ore dictionary entries for ores supported by Orechid Vacuam",
               "The ore dictionary entry needs to be followed by a colon : symbol and its weight (lower = rarer)",
               "Any entries containing the word 'Ender' in it will also include the same entries, but with the word 'End' instead for the highest compatibility",
               "Please note, it might get replaced in the future by CraftTweaker or a separate config file" })
    @LangKey("mia.config.botania.orechid_vacuam_oredict")
    @RequiresMcRestart
    public static String[] orechidVacuamOreDict = new String[]
            {   // Quark
                "oreEnderBiotite:2000",
                // Biomes O' Plenty
                "oreEnderAmethyst:2000",
                // Thermal Foundation
                "oreClathrateEnder:1250",
            
                // Other
                "oreEnderAluminum:2000",
                "oreEnderAmber:1000",
                "oreEnderApatite:750",
                "oreEnderBlueTopaz:2000",
                "oreEnderCertusQuartz:2000",
                "oreEnderChimerite:2000",
                "oreEnderCinnabar:1500",
                "oreEnderDark:700",
                "oreEnderDarkIron:800",
                "oreEnderFzDarkIron:800",
                "oreEnderEmerald:400",
                "oreEnderGalena:500",
                "oreEnderInfusedAir:500",
                "oreEnderInfusedEarth:500",
                "oreEnderInfusedEntropy:500",
                "oreEnderInfusedFire:500",
                "oreEnderInfusedOrder:500",
                "oreEnderInfusedWater:500",
                "oreEnderLapis:700",
                "oreEnderMithril:8",
                "oreEnderOlivine:600",
                "oreEnderRuby:600",
                "oreEnderSapphire:600",
                "oreEnderUranium:750",
                "oreEnderVinteum:300",
                "oreEnderYellorite:200",
                "oreEnderZinc:3500",
                "oreEnderMythril:3500",
                "oreEnderAdamantium:1300",
                "oreEnderTungsten:2000",
                "oreEnderOsmium:3500",
                "oreEnderQuartzBlack:2500",
                "oreEnderQuartz:9000",
                "oreEnderCobalt:250",
                "oreEnderArdite:250",
                "oreEnderFirestone:5",
                "oreEnderCoal:8500",
                "oreEnderCopper:2500",
                "oreEnderDiamond:100",
                "oreEnderEssence:1500",
                "oreEnderGold:1500",
                "oreEnderIron:2500",
                "oreEnderLead:1500",
                "oreEnderNickel:800",
                "oreEnderPlatinum:100",
                "oreEnderRedstone:2500",
                "oreEnderSilver:750",
                "oreEnderSteel:800",
                "oreEnderTin:200",
                "oreEnderFyrite:500",
                "oreEnderAshstone:500",
                "oreEnderDragonstone:100",
                "oreEnderArgonite:500",
                "oreEnderOnyx:250",
                "oreEnderHaditeCoal:250"
            };
    
    
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
