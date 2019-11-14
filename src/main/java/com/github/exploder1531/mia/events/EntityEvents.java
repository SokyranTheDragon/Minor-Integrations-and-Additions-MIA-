package com.github.exploder1531.mia.events;

import com.github.exploder1531.mia.Mia;
import com.github.exploder1531.mia.integrations.ModIds;
import drzhark.mocreatures.entity.monster.MoCEntityWerewolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.EntityDamageSource;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = Mia.MODID)
public class EntityEvents
{
    @SubscribeEvent
    public static void onEntityHit(LivingHurtEvent event)
    {
        if (Loader.isModLoaded(ModIds.MO_CREATURES))
        {
            // Make it stronger when Tinker's weapon is holy?
            if (event.getAmount() < 10f && event.getSource() instanceof EntityDamageSource && event.getEntityLiving() instanceof MoCEntityWerewolf && !((MoCEntityWerewolf)event.getEntityLiving()).getIsHumanForm())
            {
                if (event.getSource().damageType.equals("player") && event.getSource().getImmediateSource() instanceof EntityPlayer)
                {
                    EntityPlayer player = ((EntityPlayer) event.getSource().getImmediateSource());
                    
                    ItemStack heldItem = player.getHeldItemMainhand();
                    
                    if (!heldItem.isEmpty() && heldItem.getItem() instanceof ItemSword)
                    {
                        ItemSword sword = (ItemSword)heldItem.getItem();
                        
                        if (sword.getToolMaterialName().toLowerCase().contains("silver") || sword.getTranslationKey().toLowerCase().contains("silver"))
                        {
                            event.setAmount(10f);
                        }
                    }
                }
            }
        }
    }
}