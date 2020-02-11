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
    
    @Name("Enable EVTP")
    @Comment("If set to false then any blocks added by EVTP will be disabled")
    @LangKey("mia.config.base_additions.evtp.enabled")
    @RequiresMcRestart
    public static boolean enableEvtp = true;
    
    @Name("More Sandstone")
    @Comment("Shared More Sandstone settings")
    @LangKey("mia.config.base_additions.more_sandstone_shared")
    public static MoreSandstone moreSandstone = new MoreSandstone();
    
    @Name("EVTP")
    @Comment("EVTP settings")
    @LangKey("mia.config.base_additions.evtp")
    public static EVTP evtp = new EVTP();
    
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
        private MoreSandstone()
        {
        }
        
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
    
    public static class EVTP
    {
        private EVTP()
        {
        }
        
        @Name("Enable Armored Glass")
        @Comment("Determines if Armored Glass is going to be enabled")
        @LangKey("mia.config.base_additions.evtp.armored_glass_enabled")
        @RequiresMcRestart
        public boolean armoredGlassEnabled = true;
        
        @Name("Enable packed paper block")
        @Comment("Determines if Packed Paper is going to be enabled")
        @LangKey("mia.config.base_additions.evtp.packed_paper_enabled")
        @RequiresMcRestart
        public boolean packedPaperEnabled = true;
        
        @Name("Enable golden torch")
        @Comment("Determines if Golden Torch is going to be enabled")
        @LangKey("mia.config.base_additions.evtp.golden_torch_enabled")
        @RequiresMcRestart
        public boolean goldenTorchEnabled = true;
        
        @Name("Enable dead flowers")
        @Comment("Determines if Dead Flower is going to be enabled")
        @LangKey("mia.config.base_additions.evtp.dead_flower_enabled")
        @RequiresMcRestart
        public boolean deadFlowerEnabled = true;
        
        @Name("Dead flower spawn chance")
        @Comment({ "Determines percentage chance to generate a dead flower",
                   "A value of 0 is 0%, a value of 100 is 100%",
                   "The chance itself is also affected by biome temperature (higher temperature means higher chance, unless the chance is 0)" })
        @LangKey("mia.config.base_additions.evtp.dead_flower_chance")
        @RangeDouble(min = 0, max = 100)
        public double deadFlowerSpawnChance = 1.5d;
        
        @Name("Enable stone doors")
        @Comment("Determines if Stone Doors are going to be enabled")
        @LangKey("mia.config.base_additions.evtp.stone_doors_enabled")
        @RequiresMcRestart
        public boolean stoneDoorsEnabled = true;
        
        @Name("Enable redstone lantern")
        @Comment("Determines if Redstone Lantern is going to be enabled")
        @LangKey("mia.config.base_additions.evtp.redstone_lantern_enabled")
        @RequiresMcRestart
        public boolean redstoneLanternEnabled = true;
    }
}
