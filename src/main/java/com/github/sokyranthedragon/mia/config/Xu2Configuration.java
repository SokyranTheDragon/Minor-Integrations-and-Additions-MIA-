package com.github.sokyranthedragon.mia.config;

import com.github.sokyranthedragon.mia.Mia;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.*;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import static net.minecraftforge.common.config.Config.*;

@Config(modid = Mia.MODID, name = "mia/xu2")
@LangKey("mia.config.xu2.title")
@Mod.EventBusSubscriber(modid = Mia.MODID)
public class Xu2Configuration
{
    @Name("Enable Extra Utilities 2 additions")
    @Comment("Set to false to completely disable new Extra Utilities 2 additions")
    @LangKey("mia.config.xu2.additions_enabled")
    @RequiresMcRestart
    public static boolean xu2AdditionsEnabled = true;
    
    @Name("Enable external integrations")
    @Comment("Set to false to prevent other mods from integrating with XU2")
    @LangKey("mia.config.shared.enable_external_integrations")
    @RequiresMcRestart
    public static boolean externalIntegrationsEnabled = true;
    
    @Name("Enable Hatchery integration")
    @Comment("Set to false to completely disable integration with Hatchery")
    @LangKey("mia.config.shared.enable_hatchery_integration")
    @RequiresMcRestart
    public static boolean enableHatcheryIntegration = true;
    
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
    
    @Name("Enable Quark integration")
    @Comment("Set to false to completely disable integration with Quark")
    @LangKey("mia.config.shared.enable_quark_integration")
    @RequiresMcRestart
    public static boolean enableQuarkIntegration = true;
    
    @Name("Enable FutureMC integration")
    @Comment("Set to false to completely disable integration with FutureMC")
    @LangKey("mia.config.shared.enable_future_mc_integration")
    @RequiresMcRestart
    public static boolean enableFutureMcIntegration = true;
    
    @Name("Enable Chisel integration")
    @Comment("Set to false to completely disable integration with Chisel")
    @LangKey("mia.config.shared.enable_chisel_integration")
    @RequiresMcRestart
    public static boolean enableChiselIntegration = true;
    
    @Name("Register shady merchant trades in JER")
    @Comment("Set to false to prevent the completely legitimate trades from appearing in JER")
    @LangKey("mia.config.xu2.register_shady_merchant_jer")
    @RequiresMcRestart
    public static boolean enableShadyMerchantJer = true;
    
    
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
