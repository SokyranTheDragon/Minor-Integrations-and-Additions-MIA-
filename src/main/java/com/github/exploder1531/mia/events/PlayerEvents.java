package com.github.exploder1531.mia.events;

import com.github.exploder1531.mia.Mia;
import com.github.exploder1531.mia.config.ThaumcraftConfiguration;
import com.github.exploder1531.mia.integrations.ModIds;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import thaumcraft.api.capabilities.IPlayerKnowledge;
import thaumcraft.api.capabilities.ThaumcraftCapabilities;
import thaumcraft.common.config.ModConfig;

@Mod.EventBusSubscriber(modid = Mia.MODID)
public class PlayerEvents
{
//    // Hatchery
//    @SubscribeEvent
//    public static void onPlayerInteract(PlayerInteractEvent event)
//    {
//        if (!event.getWorld().isRemote)
//            return;
//
//        final TileEntity entity = event.getWorld().getTileEntity(event.getPos());
//
//        if (entity instanceof NestPenTileEntity)
//        {
//            final NestPenTileEntity pen = (NestPenTileEntity) entity;
//
//            final EntityPlayer player = event.getEntityPlayer();
//            final ItemStack item = player.getHeldItemMainhand();
//
//            if (pen.storedEntity() != null)
//            {
//
//
//            }
//        }
//    }
    
    // Thaumcraft
    @SubscribeEvent
    public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event)
    {
        if (!Loader.isModLoaded(ModIds.THAUMCRAFT) && !ThaumcraftConfiguration.thaumcraftAdditionsEnabled)
            return;
        
        handleWussResearch(event.player);
    }
    
    // Thaumcraft
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onConfigChanged(final ConfigChangedEvent.OnConfigChangedEvent event)
    {
        if (!Loader.isModLoaded(ModIds.THAUMCRAFT) && !ThaumcraftConfiguration.thaumcraftAdditionsEnabled)
            return;
        
        if (event.getModID().equals(ModIds.THAUMCRAFT))
        {
            final WorldServer[] worlds = FMLCommonHandler.instance().getMinecraftServerInstance().worlds;
            
            for (WorldServer world : worlds)
            {
                if (world != null)
                {
                    for (EntityPlayer player : world.playerEntities)
                        handleWussResearch(player);
                }
            }
        }
    }
    
    // Thaumcraft
    private static void handleWussResearch(EntityPlayer player)
    {
        if (player instanceof FakePlayer)
            return;
        
        IPlayerKnowledge knowledge = ThaumcraftCapabilities.getKnowledge(player);
        
        if (ModConfig.CONFIG_MISC.wussMode)
        {
            if (!knowledge.isResearchKnown("!WussMode"))
                knowledge.addResearch("!WussMode");
        }
        else
        {
            if (knowledge.isResearchKnown("!WussMode"))
                knowledge.removeResearch("!WussMode");
        }
    }
}
