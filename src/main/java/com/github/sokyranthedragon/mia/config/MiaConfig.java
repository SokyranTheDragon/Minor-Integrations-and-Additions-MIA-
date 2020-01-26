package com.github.sokyranthedragon.mia.config;

import com.github.sokyranthedragon.mia.Mia;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import static net.minecraftforge.common.config.Config.*;

@Config(modid = Mia.MODID, name = "mia/base")
@LangKey("mia.config.base.title")
@Mod.EventBusSubscriber(modid = Mia.MODID)
public class MiaConfig
{
    @Name("Enable music player")
    @Comment("Set to false to completely disable the music player item")
    @LangKey("mia.config.base.music_player")
    @RequiresMcRestart
    public static boolean musicPlayerEnabled = true;
    
    @Name("Music player volume")
    @Comment("Volume of the songs played by music player")
    @LangKey("mia.config.base.music_player_volume")
    @RangeInt(min = 0, max = 100)
    @SlidingOption
    public static int musicPlayerVolume = 30;
    
    @Name("Replaces all raw meat drops with cooked ones")
    @Comment({ "Replaces raw meat dropped by mobs on fire with their cooked version (if possible)",
               "This is done to match modded mobs with vanilla behavior, as not all modded mobs do this" })
    @LangKey("mia.config.base.add_cooked_drops")
    public static boolean addCookedDrops = true;
    
    @Name("Disable all recipes")
    @Comment("Completely disables any new recipes added by this mod")
    @LangKey("mia.config.base.disable_recipes")
    @RequiresMcRestart
    public static boolean disableAllRecipes = false;
    
    @Name("Disable ore dictionary registration")
    @Comment("Completely removes any ore dictionary entries added for other modded items")
    @LangKey("mia.config.base.disable_ore_dict")
    public static boolean disableOreDict = false;
    
    
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
