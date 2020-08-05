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
    
    @Name("Enable size module")
    @Comment("If set to false then any size change related content will be disabled (requires ArtemisLib to be installed)")
    @LangKey("mia.config.base_additions.size_component.enabled")
    @RequiresMcRestart
    public static boolean enableSizeComponent = false;
    
    @Name("More Sandstone")
    @Comment("Shared More Sandstone settings")
    @LangKey("mia.config.base_additions.more_sandstone_shared")
    public static MoreSandstone moreSandstone = new MoreSandstone();
    
    @Name("EVTP")
    @Comment("EVTP settings")
    @LangKey("mia.config.base_additions.evtp")
    public static EVTP evtp = new EVTP();
    
    @Name("Size module")
    @Comment("Configuration related to size changing related components")
    @LangKey("mia.config.base_additions.size_component")
    public static SizeModule sizeModule = new SizeModule();
    
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
//        @Name("Force more sandstone FutureMC walls")
//        @Comment("If set to true then FutureMC sandstone walls will be enabled even if walls are disabled in FutureMC")
//        @LangKey("mia.config.base_additions.more_sandstone.forced_futuremc_walls")
//        @RequiresMcRestart
//        public boolean forceMoreSandstoneFutureMcWalls = false;
        
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
        @Comment({
            "Determines percentage chance to generate a dead flower",
            "A value of 0 is 0%, a value of 100 is 100%",
            "The chance itself is also affected by biome temperature (higher temperature means higher chance, unless the chance is 0)"
        })
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
    
    public static class SizeModule
    {
        private SizeModule()
        {
        }
        
        @Name("Minimum player size")
        @Comment("Changes the lowest allowed player size")
        @LangKey("mia.config.base_additions.size_component.min_player_size")
        @RangeDouble(min = 0.125f, max = 1f)
        public float minPlayerSize = 0.125f;
        @Name("Maximum player size")
        @Comment("Changes the highest allowed player size")
        @LangKey("mia.config.base_additions.size_component.max_player_size")
        @RangeDouble(min = 1f, max = 10f)
        public float maxPlayerSize = 10f;
        
        @Name("Can mob size change")
        @Comment("Setting this to true allows the player to change the size of non-player mobs.")
        @LangKey("mia.config.base_additions.size_component.mob_size")
        public boolean canScaleMobs = true;
        
        @Name("Minimum mob size")
        @Comment("Changes the lowest allowed mob size")
        @LangKey("mia.config.base_additions.size_component.min_mob_size")
        @RangeDouble(min = 0.125f, max = 1f)
        public float minMobSize = 0.125f;
        @Name("Maximum mob size")
        @Comment("Changes the highest allowed mob size")
        @LangKey("mia.config.base_additions.size_component.max_mob_size")
        @RangeDouble(min = 1f, max = 10f)
        public float maxMobSize = 10f;
        
        @Name("Scale movement speed")
        @Comment("Scales movement speed based on size")
        @LangKey("mia.config.base_additions.size_component.scale_speed")
        public boolean scaleMovementSpeed = true;
        @Name("Scale strength")
        @Comment("Scales strength based on size")
        @LangKey("mia.config.base_additions.size_component.scale_strength")
        public boolean scaleStrength = true;
        @Name("Scale max health")
        @Comment("Scales max health based on size")
        @LangKey("mia.config.base_additions.size_component.scale_max_health")
        public boolean scaleMaxHealth = true;
        @Name("Scale current health when growing")
        @Comment("Scales current health when max health is increased when growing")
        @LangKey("mia.config.base_additions.size_component.scale_current_health")
        public boolean scaleCurrentHealthWhenGrowing = true;
        @Name("Scale reach distance")
        @Comment("Scales reach distance based on size")
        @LangKey("mia.config.base_additions.size_component.scale_reach")
        public boolean scaleReachDistance = true;
        @Name("Scale swimming speed")
        @Comment("Scales swimming speed based on size")
        @LangKey("mia.config.base_additions.size_component.scale_swim_speed")
        public boolean scaleSwimSpeed = true;
        
        @Name("Health multiplier")
        @Comment("If max health scaling is enabled, this determines how much the health is affected by size")
        @LangKey("mia.config.base_additions.size_component.health_multiplier")
        @RangeDouble(min = 1.4E-45F, max = 3.4028235E38F)
        public float healthMultiplier = 1f;
        
        @Name("Fix small entity rendering")
        @Comment("While entities are small size (below 0.4) they start looking weird, which is fixed by this setting. Could cause issues with other mods. Disabling this could very slightly help performance.")
        @LangKey("mia.config.base_additions.size_component.fix_rendering")
        public boolean fixRendering = true;
        @Name("Fix player rendering in inventory")
        @Comment("While the inventory is open, the player size is scaled to match unchanged player size. Could cause issues with other mods. Disabling this could slightly help performance.")
        @LangKey("mia.config.base_additions.size_component.fix_rendering_inventory")
        public boolean fixInventoryRendering = true;
        
        @Name("Entities banned from size change")
        @Comment("Any entity inside of this list will not be able to change size in any way.")
        @LangKey("mia.config.base_additions.size_component.banned_entities_size")
        public String[] bannedEntitiesSize = new String[]{ "botania:doppleganger", "extrabotany:gaiaIII", "extrabotany:voidherrscher" };
        
        @Name("Can entities crush smaller entities from themselves")
        @Comment("If there's big enough difference between two entities sizes, the bigger one will damage to the smaller one")
        @LangKey("mia.config.base_additions.size_component.giants_crush_entities")
        public boolean giantsCrushEntities = false;
        
        public boolean onlyHostileMobsCanCrush = true;
        @Name("Can bigger players crush smaller players")
        @Comment("If there's big enough difference between two player sizes, and the option for entities crushing smaller ones is enabled, the bigger players will damage the smaller ones")
        @LangKey("mia.config.base_additions.size_component.players_crush_players")
        public boolean canPlayersCrushOtherPlayers = true;
        @Name("Entities being banned from crushing smaller entities or being crushed by them")
        @Comment("If the option for entities to crush smaller ones is enabled, this list will be used to determine which entities are banned from crushing other or being crushed")
        @LangKey("mia.config.base_additions.size_component.banned_entities_crushing")
        public String[] bannedEntitiesCrushing = new String[0];
        
        @Name("Do roses hurt small players")
        @Comment("If this is set to true, small players will take damage if walking through roses")
        @LangKey("mia.config.base_additions.size_component.roses_hurt_player")
        public boolean doRosesHurtSmallPlayer = true;
        @Name("Do bushes slow down small players")
        @Comment("If this is set to true, small players will be slowed down if walking through plants")
        @LangKey("mia.config.base_additions.size_component.plants_slow_player")
        public boolean doPlantsSlowSmallPlayer = true;
        @Name("Can small players climb some blocks")
        @Comment("If this is set to true, small players can climb some blocks without having to use any special means")
        @LangKey("mia.config.base_additions.size_component.climb_some_blocks")
        public boolean canClimbSomeBlocks = true;
        @Name("Can small players climb while holding slime balls/slime blocks")
        @Comment("If this value is set to true, small players can climb blocks while holding either a slime ball, or a slime block")
        @LangKey("mia.config.base_additions.size_component.climb_with_slime")
        public boolean canClimbWithSlime = true;
        @Name("Can small players climb while holding paper")
        @Comment("If this value is set to true, small players can glide while holding paper")
        @LangKey("mia.config.base_additions.size_component.glide_with_paper")
        public boolean canGlideWithPaper = true;
        @Name("Do small blocks give lift while gliding")
        @Comment("If this value is true and while player is gliding with paper over hot blocks, it will increase player's flight height")
        @LangKey("mia.config.base_additions.size_component.hot_blocks_lift")
        public boolean hotBlocksGiveLift = true;
        
        @Name("Thaumcraft integration")
        @Comment("Set to true to add size change foci to Thaumcraft")
        @LangKey("mia.config.base_additions.size_component.thaumcraft_integration")
        @RequiresMcRestart
        public boolean thaumcraftIntegration = true;
    }
}
