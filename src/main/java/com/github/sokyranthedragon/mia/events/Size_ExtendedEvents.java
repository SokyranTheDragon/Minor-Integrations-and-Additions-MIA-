package com.github.sokyranthedragon.mia.events;

import com.github.sokyranthedragon.mia.Mia;
import com.github.sokyranthedragon.mia.config.GenericAdditionsConfig;
import com.github.sokyranthedragon.mia.enchantments.EnchantmentKobold;
import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.network.MessageExtendedReachAttack;
import com.github.sokyranthedragon.mia.potions.BasePotion;
import com.github.sokyranthedragon.mia.potions.ModPotions;
import com.github.sokyranthedragon.mia.utilities.size.CollisionUtils;
import com.github.sokyranthedragon.mia.utilities.size.SizeUtils;
import com.legacy.aether.items.ItemsAether;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityTippedArrow;
import net.minecraft.init.Items;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionType;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.WorldServer;
import net.minecraftforge.client.event.MouseEvent;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.event.entity.ProjectileImpactEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.lang.reflect.Field;

@Mod.EventBusSubscriber(modid = Mia.MODID)
public class Size_ExtendedEvents
{
    private static final Field arrowPotionTypeField = ObfuscationReflectionHelper.findField(EntityTippedArrow.class, "field_184560_g"); // Field called 'potion'
    
    @SideOnly(Side.CLIENT)
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onMouse(MouseEvent event)
    {
        if (!SizeUtils.isSizeComponentEnabled)
            return;
        
        if (GenericAdditionsConfig.sizeModule.scaleReachDistance && event.getButton() == 0 && event.isButtonstate())
        {
            EntityPlayer player = Minecraft.getMinecraft().player;
            
            if (player != null)
            {
                float size = SizeUtils.getEntitySize(player);
                
                if (size > 1)
                {
                    RayTraceResult ray = CollisionUtils.getMouseOverExtended(3f * size);
                    
                    if (ray != null && ray.entityHit != null)
                    {
                        Mia.network.sendToServer(new MessageExtendedReachAttack(ray.entityHit.getEntityId()));
                        player.resetCooldown();
                        event.setCanceled(true);
                    }
                }
            }
        }
    }
    
    @SubscribeEvent
    public static void useItemFinishEvent(LivingEntityUseItemEvent.Finish event)
    {
        if (!SizeUtils.isSizeComponentEnabled || event.getEntityLiving() instanceof FakePlayer)
            return;
        
        boolean isMilkBucket = event.getItem().getItem() == Items.MILK_BUCKET;
        
        if (!isMilkBucket && ModIds.AETHER.isLoaded)
            isMilkBucket = event.getItem().getItem() == ItemsAether.skyroot_bucket && event.getItem().getMetadata() == 4;
        
        if (event.getEntityLiving() instanceof EntityPlayer && isMilkBucket && SizeUtils.getEntitySize(event.getEntityLiving()) != 1)
        {
            event.getEntityLiving().removePotionEffect(ModPotions.sizeStabilizationPotion);
            
            PotionEffect effect = new PotionEffect(ModPotions.sizeStabilizationPotion, 2147483647);
            // We set the display to '**:**' for clients only, it crashes on the server as it's client-only method
            if (event.getEntityLiving().world.isRemote)
                effect.setPotionDurationMax(true);
            event.getEntityLiving().addPotionEffect(effect);
        }
    }
    
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onConfigChanged(final ConfigChangedEvent.OnConfigChangedEvent event)
    {
        if (!SizeUtils.isSizeComponentEnabled)
            return;
    
        final WorldServer[] worlds = FMLCommonHandler.instance().getMinecraftServerInstance().worlds;
        
        for (WorldServer world : worlds)
        {
            if (world != null)
            {
                for (Entity entity : world.loadedEntityList)
                {
                    if (entity instanceof EntityLivingBase)
                    {
                        if (entity instanceof EntityPlayer)
                        {
                            if (!EnchantmentKobold.checkKoboldEnchantment((EntityPlayer) entity))
                                SizeUtils.validateEntitySize((EntityLivingBase) entity);
                        }
                        else
                            SizeUtils.validateEntitySize((EntityLivingBase) entity);
                    }
                }
            }
        }
    }
    
    @SubscribeEvent
    public static void onArrowImpact(ProjectileImpactEvent.Arrow event)
    {
        if (!SizeUtils.isSizeComponentEnabled)
            return;
        
        if (event.getArrow() instanceof EntityTippedArrow && event.getRayTraceResult().entityHit instanceof EntityLivingBase)
        {
            EntityTippedArrow arrow = (EntityTippedArrow) event.getArrow();
            
            try
            {
                PotionType potion = (PotionType) arrowPotionTypeField.get(arrow);
                
                PotionType newPotion = new PotionType(potion.getEffects()
                                                            .stream()
                                                            .map((e) ->
                                                            {
                                                                if (e.getPotion() instanceof BasePotion)
                                                                    return new PotionEffect(e.getPotion(), e.getDuration() * 4, e.getAmplifier(), e.getIsAmbient(), e.doesShowParticles());
                                                                return e;
                                                            })
                                                            .toArray(PotionEffect[]::new));
                
                arrowPotionTypeField.set(arrow, newPotion);
            } catch (IllegalAccessException e)
            {
                Mia.LOGGER.error("Could not change tipped arrow value, ignoring");
            }
        }
    }
    
    @SubscribeEvent
    public static void onPlayerLogin(net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent event)
    {
        if (!SizeUtils.isSizeComponentEnabled)
            return;
    
        if (event.player != null)
        {
            if (!EnchantmentKobold.checkKoboldEnchantment(event.player))
                SizeUtils.validateEntitySize(event.player);
        }
    }
}
