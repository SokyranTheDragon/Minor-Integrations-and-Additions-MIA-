package com.github.exploder1531.mia.config;

import com.github.exploder1531.mia.Mia;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import static net.minecraftforge.common.config.Config.*;

@Config(modid = Mia.MODID, name = "mia/quark")
@LangKey("mia.config.quark.title")
@Mod.EventBusSubscriber(modid = Mia.MODID)
public class QuarkConfiguration
{
    @Name("Enable Ice and Fire additions")
    @Comment("Set to false to completely disable new Quark additions")
    @LangKey("mia.config.quark.additions_enabled")
    @RequiresMcRestart
    public static boolean quarkAdditionsEnabled = true;
    
    @Name("Enable external integrations")
    @Comment("Set to false to prevent other mods from integrating with Quark")
    @LangKey("mia.config.shared.enable_external_integrations")
    @RequiresMcRestart
    public static boolean externalIntegrationsEnabled = true;
    
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
    
    @Name("Enable XU2 integration")
    @Comment("Set to false to completely disable integration with XU2")
    @LangKey("mia.config.shared.enable_extra_utils_integration")
    @RequiresMcRestart
    public static boolean enableXu2Integration = true;
    
    @Name("Enable Hatchery integration")
    @Comment("Set to false to completely disable integration with Dungeon Tactics")
    @LangKey("mia.config.shared.enable_dungeon_tactics_integration")
    @RequiresMcRestart
    public static boolean enableDungeonTacticsIntegration = true;
    
    @Name("Add supported ancient tomes")
    @Comment("If enabled, all enchantments from supported mods will be automatically added as ancient tomes")
    @LangKey("mia.config.quark.add_ancient_tomes")
    @RequiresMcRestart
    public static boolean addAncientTomes = true;
    
    @Name("Add ancient tome crafting in supported mods")
    @Comment("If enabled, all supported mods that allow for enchanted book crafting will have ancient tomes recipes added")
    @LangKey("mia.config.quark.ancient_tomes_crafting")
    public static boolean ancientTomesCrafting = true;
    
    @Config.Name("Enable FutureMC integration")
    @Config.Comment("Set to false to completely disable integration with FutureMC")
    @Config.LangKey("mia.config.shared.enable_future_mc_integration")
    @Config.RequiresMcRestart
    public static boolean enableFutureMcIntegration = true;
    
    
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
