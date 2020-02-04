package com.github.sokyranthedragon.mia.config;

import com.github.sokyranthedragon.mia.Mia;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import static net.minecraftforge.common.config.Config.*;

@Config(modid = Mia.MODID, name = "mia/base_additions")
@LangKey("mia.config.base_additions.title")
@Mod.EventBusSubscriber(modid = Mia.MODID)
public class GenericAdditionsConfig
{
    @Name("Enable more sandstone")
    @Comment("Set to false to completely disable any new sandstone types from being added")
    @LangKey("mia.config.base_additions.more_sandstone")
    @RequiresMcRestart
    public static boolean enableMoreSandstone = true;
    
    @Name("More Sandstone")
    @Comment("Shared More Sandstone settings")
    @LangKey("mia.config.base_additions.more_sandstone_shared")
    public static MoreSandstone moreSandstone = new MoreSandstone();
    
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
    
    public static class MoreSandstone
    {
        @Name("Enable more sandstone Quark blocks")
        @Comment("Set to false to disable Quark's more sandstone block")
        @LangKey("mia.config.base_additions.more_sandstone.quark")
        @RequiresMcRestart
        public boolean moreSandstoneQuarkEnabled = true;
        @Name("Enable more sandstone FutureMC walls")
        @Comment("Set to false to disable FutureMC sandstone walls")
        @LangKey("mia.config.base_additions.more_sandstone.futuremc")
        @RequiresMcRestart
        public boolean sandstoneWallsFutureMcEnabled = true;
    
        @Name("Force more sandstone")
        @Comment("If set to true then Quark's more sandstone will be enabled even if they're disabled in Quark")
        @LangKey("mia.config.base_additions.more_sandstone.forced")
        @RequiresMcRestart
        public boolean forceMoreSandstone = false;
        @Name("Force more sandstone stairs and slabs")
        @Comment("If set to true then Quark's more sandstone stairs and slabs will be enabled even if they're disabled in Quark")
        @LangKey("mia.config.base_additions.more_sandstone.forced_stairs_slabs")
        @RequiresMcRestart
        public boolean forceMoreSandstoneStairsAndSlabs = false;
        @Name("Force more sandstone Quark walls")
        @Comment("If set to true then Quark's more sandstone walls will be enabled even if they're disabled in Quark")
        @LangKey("mia.config.base_additions.more_sandstone.forced_quark_walls")
        @RequiresMcRestart
        public boolean forceMoreSandstoneQuarkWalls = false;
        @Name("Force more sandstone FutureMC walls")
        @Comment("If set to true then FutureMC sandstone walls will be enabled even if walls are disabled in FutureMC")
        @LangKey("mia.config.base_additions.more_sandstone.forced_futuremc_walls")
        @RequiresMcRestart
        public boolean forceMoreSandstoneFutureMcWalls = false;
    
        @Name("Enable white sandstone")
        @Comment("Set to false to disable White Sandstone from receiving any new blocks")
        @LangKey("mia.config.base_additions.more_sandstone.bop_white_sandstone")
        @RequiresMcRestart
        public boolean bopWhiteSandstoneEnabled = true;
        @Name("Enable white sandstone Quark walls")
        @Comment("Set to false to disable Quark's White Sandstone walls")
        @LangKey("mia.config.base_additions.more_sandstone.bop_white_sandstone_quark_walls")
        @RequiresMcRestart
        public boolean bopWhiteSandstoneQuarkWallsEnabled = true;
    }
}
