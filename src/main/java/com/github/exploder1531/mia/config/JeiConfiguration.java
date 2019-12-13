package com.github.exploder1531.mia.config;

import com.github.exploder1531.mia.Mia;
import com.github.exploder1531.mia.integrations.jei.MiaJeiPlugin;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import static com.github.exploder1531.mia.integrations.jei.MiaJeiPlugin.Categories.DUNGEON_TACTICS_CAULDRON;
import static com.github.exploder1531.mia.integrations.jei.MiaJeiPlugin.Categories.LOOT_BAG;

@Config(modid = Mia.MODID, name = "mia/jei")
@Config.LangKey("mia.config.jei.title")
@Mod.EventBusSubscriber(modid = Mia.MODID)
public class JeiConfiguration
{
    @Config.Name("Enable loot bag category")
    @Config.Comment("If enabled, possible items from loot bags will be added to JEI (for example, from Dungeon Tactics or ExtraBotany)")
    @Config.LangKey("mia.config.jei.loot_bag")
    public static boolean enableLootBagCategory = true;
    
    @Config.Name("Enable alchemical cauldron category")
    @Config.Comment("If enabled, all possible alchemical cauldron recipes will be added to JEI (Dungeon Tactics required)")
    @Config.LangKey("mia.config.jei.alchemical_cauldron")
    public static boolean enableAlchemicalCauldronCategory = true;
    
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
            ConfigManager.sync(Mia.MODID, Config.Type.INSTANCE);
    
            if (enableLootBagCategory)
                MiaJeiPlugin.unhideCategories(LOOT_BAG);
            else
                MiaJeiPlugin.hideCategories(LOOT_BAG);
                
            if (enableAlchemicalCauldronCategory)
                MiaJeiPlugin.unhideCategories(DUNGEON_TACTICS_CAULDRON);
            else
                MiaJeiPlugin.hideCategories(DUNGEON_TACTICS_CAULDRON);
        }
    }
}