package com.github.sokyranthedragon.mia.config;

import com.github.sokyranthedragon.mia.Mia;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import static net.minecraftforge.common.config.Config.*;

@Config(modid = Mia.MODID, name = "mia/industrial_foregoing")
@LangKey("mia.config.industrial_foregoing.title")
@Mod.EventBusSubscriber(modid = Mia.MODID)
public class IndustrialForegoingConfiguration
{
    @Name("Enable external integrations")
    @Comment("Set to false to prevent other mods from integrating with Industrial Foregoing")
    @LangKey("mia.config.shared.enable_external_integrations")
    @RequiresMcRestart
    public static boolean externalIntegrationsEnabled = true;
    
    @Name("Enable Froster recipes")
    @Comment("Set to false to prevent other mods from adding new recipes to the Froster")
    @LangKey("mia.config.industrial_foregoing.enable_froster")
    @RequiresMcRestart
    public static boolean enableFrosterRecipes = true;
    
    @Name("Enable Laser Drill entries")
    @Comment("Set to false to prevent other mods from adding new entries to the Laser Drill")
    @LangKey("mia.config.industrial_foregoing.enable_laser_drill")
    @RequiresMcRestart
    public static boolean enableLaserDrillEntries = true;
    
    @Name("Enable Protein Generator entries")
    @Comment("Set to false to prevent other mods from adding new entries to the Protein Generator")
    @LangKey("mia.config.industrial_foregoing.enable_protein_generator")
    @RequiresMcRestart
    public static boolean enableProteinGeneratorEntries = true;
    
    @Name("Enable Tree Fluid Extractor entries")
    @Comment("Set to false to prevent other mods from adding new Tree Fluid Extractor")
    @LangKey("mia.config.industrial_foregoing.enable_tree_fluid_extractor")
    @RequiresMcRestart
    public static boolean enableLogLatexEntries = true;
    
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
