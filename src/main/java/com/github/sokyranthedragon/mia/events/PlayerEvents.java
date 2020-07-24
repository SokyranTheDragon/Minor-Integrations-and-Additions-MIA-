package com.github.sokyranthedragon.mia.events;

import com.gendeathrow.hatchery.block.nestpen.NestPenTileEntity;
import com.github.sokyranthedragon.mia.Mia;
import com.github.sokyranthedragon.mia.config.ThaumcraftConfiguration;
import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.utilities.ExtraUtilitiesUtils;
import com.rwtema.extrautils2.backend.entries.XU2Entries;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.relauncher.Side;
import thaumcraft.api.capabilities.IPlayerKnowledge;
import thaumcraft.api.capabilities.ThaumcraftCapabilities;
import thaumcraft.common.config.ModConfig;

@Mod.EventBusSubscriber(modid = Mia.MODID)
public class PlayerEvents
{
    // Hatchery
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onPlayerInteract(PlayerInteractEvent.RightClickBlock event)
    {
        if (event.getSide() == Side.CLIENT)
            return;
        
        final TileEntity tile = event.getWorld().getTileEntity(event.getPos());
        
        if (ModIds.HATCHERY.isLoaded && tile instanceof NestPenTileEntity)
        {
            final NestPenTileEntity pen = (NestPenTileEntity) tile;
            
            final EntityPlayer player = event.getEntityPlayer();
            final ItemStack item = player.getHeldItem(event.getHand());
            
            if (player.isSneaking() && (ExtraUtilitiesUtils.isItemLasso(item))) // || ThermalExpansionUtils.isItemStackMorb(item)
            {
                try
                {
                    event.setCanceled(true);
                    event.setCancellationResult(EnumActionResult.PASS);
                } catch (UnsupportedOperationException e)
                {
                    Mia.LOGGER.warn("Could not cancel nesting pen right click event.");
                }
                
                if (event.isCanceled())
                {
                    if (pen.storedEntity() == null)
                    {
                        if (ExtraUtilitiesUtils.isItemLassoWithMob(item, "chickens:chickenschicken", "minecraft:chicken"))
                        {
                            Mia.LOGGER.info("Trying to put chicken into the pen.");
                            NBTTagCompound nbt = item.getTagCompound();
                            World world = event.getWorld();
                            //noinspection ConstantConditions
                            Entity chicken = EntityList.createEntityFromNBT(nbt.getCompoundTag("Animal"), world);
                            
                            pen.trySetEntity(chicken);
                            world.playSound(null, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_CHICKEN_EGG, SoundCategory.NEUTRAL, 0.5F, 0.4F / (world.rand.nextFloat() * 0.4F + 0.8F));
                            
                            nbt.removeTag("Animal");
                            nbt.removeTag("Animal_Metadata");
                            nbt.removeTag("No_Place");
                            item.clearCustomName();
                            player.inventory.markDirty();
                        }
//                        else if (ThermalExpansionUtils.isItemStackMorbWithMob(item, "minecraft:chicken"))
//                        {
//
//                        }
                    }
                    else
                    {
                        if (ExtraUtilitiesUtils.isItemLassoWithoutMob(item))
                        {
                            EntityAgeable chicken = (EntityAgeable) pen.tryGetRemoveEntity();
                            XU2Entries.goldenLasso.value.addTargetToLasso(item, chicken);
                            player.inventory.markDirty();
                        }
                    }
                }
            }
        }
    }
    
    // Thaumcraft
    @SubscribeEvent
    public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event)
    {
        if (!ModIds.THAUMCRAFT.isLoaded && !ThaumcraftConfiguration.thaumcraftAdditionsEnabled)
            return;
        
        handleWussResearch(event.player);
    }
    
    // Thaumcraft
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onConfigChanged(final ConfigChangedEvent.OnConfigChangedEvent event)
    {
        if (!ModIds.THAUMCRAFT.isLoaded || !ThaumcraftConfiguration.thaumcraftAdditionsEnabled)
            return;
        
        if (event.getModID().equals(ModIds.THAUMCRAFT.modId))
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
        if (!ModIds.THAUMCRAFT.isLoaded || player instanceof FakePlayer)
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
