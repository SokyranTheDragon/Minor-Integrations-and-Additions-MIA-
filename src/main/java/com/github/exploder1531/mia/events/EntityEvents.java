package com.github.exploder1531.mia.events;

import com.github.exploder1531.mia.Mia;
import com.github.exploder1531.mia.config.HarvestcraftConfiguration;
import com.github.exploder1531.mia.config.MiaConfig;
import com.github.exploder1531.mia.config.MoCreaturesConfiguration;
import com.pam.harvestcraft.item.ItemRegistry;
import drzhark.mocreatures.entity.MoCEntityAquatic;
import drzhark.mocreatures.entity.ambient.MoCEntityCrab;
import drzhark.mocreatures.entity.aquatic.*;
import drzhark.mocreatures.entity.monster.MoCEntityRat;
import drzhark.mocreatures.entity.monster.MoCEntityWerewolf;
import drzhark.mocreatures.entity.passive.*;
import drzhark.mocreatures.init.MoCItems;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.EntityDamageSource;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import vazkii.quark.world.entity.EntityCrab;
import vazkii.quark.world.entity.EntityFrog;
import vazkii.quark.world.feature.Crabs;
import vazkii.quark.world.feature.Frogs;

import java.util.List;

import static com.github.exploder1531.mia.integrations.ModIds.MO_CREATURES;
import static com.github.exploder1531.mia.integrations.ModIds.QUARK;
import static com.github.exploder1531.mia.integrations.ModLoadStatus.*;

@Mod.EventBusSubscriber(modid = Mia.MODID)
public class EntityEvents
{
    @SubscribeEvent
    public static void onEntityHit(LivingHurtEvent event)
    {
        if (moCreaturesLoaded && MoCreaturesConfiguration.buffOtherModSilverWeapons)
        {
            // Make it stronger when Tinker's weapon is holy?
            if (event.getAmount() < 10f && event.getSource() instanceof EntityDamageSource && event.getEntityLiving() instanceof MoCEntityWerewolf && !((MoCEntityWerewolf) event.getEntityLiving()).getIsHumanForm())
            {
                if (event.getSource().damageType.equals("player") && event.getSource().getImmediateSource() instanceof EntityPlayer)
                {
                    EntityPlayer player = ((EntityPlayer) event.getSource().getImmediateSource());
                    
                    ItemStack heldItem = player.getHeldItemMainhand();
                    
                    if (!heldItem.isEmpty() && heldItem.getItem() instanceof ItemSword)
                    {
                        ItemSword sword = (ItemSword) heldItem.getItem();
                        
                        if (sword.getToolMaterialName().toLowerCase().contains("silver") || sword.getTranslationKey().toLowerCase().contains("silver"))
                        {
                            event.setAmount(10f);
                        }
                    }
                }
            }
        }
    }
    
    @SuppressWarnings("UnnecessaryReturnStatement")
    @SubscribeEvent
    public static void onEntityDrops(LivingDropsEvent event)
    {
        if (!event.getEntityLiving().world.getGameRules().getBoolean("doMobLoot"))
            return;
        
        if (harvestcraftLoaded)
        {
            if (HarvestcraftConfiguration.squidDropsCalamari && event.getEntityLiving() instanceof EntitySquid)
            {
                dropFewItems(ItemRegistry.calamaricookedItem, ItemRegistry.calamaricookedItem, event);
                return;
            }
            else if (moCreaturesLoaded)
            {
                if (event.getEntityLiving() instanceof MoCEntityAnchovy)
                {
                    replaceItemDrop(event.getDrops(), Items.FISH, ItemRegistry.anchovyrawItem);
                    return;
                }
                else if (event.getEntityLiving() instanceof MoCEntityBass)
                {
                    replaceItemDrop(event.getDrops(), Items.FISH, ItemRegistry.bassrawItem);
                    return;
                }
                // Add new loot
                else if (event.getEntityLiving() instanceof MoCEntityJellyFish)
                {
                    dropFewItems(ItemRegistry.jellyfishrawItem, event);
                    return;
                }
                else if (event.getEntityLiving() instanceof MoCEntityDuck)
                {
                    if (MiaConfig.addCookedDrops)
                        dropFewItems(ItemRegistry.duckrawItem, ItemRegistry.duckcookedItem, event);
                    else
                        dropFewItems(ItemRegistry.duckrawItem, event);
                    return;
                }
                else if (event.getEntityLiving() instanceof MoCEntityDeer)
                {
                    if (MiaConfig.addCookedDrops)
                        dropFewItems(ItemRegistry.venisonrawItem, ItemRegistry.venisoncookedItem, event);
                    else
                        dropFewItems(ItemRegistry.venisonrawItem, event);
                    return;
                }
            }
        }
        
        if (moCreaturesLoaded && registerMoCreaturesDrops(event))
            return;
        
        if (quarkLoaded && registerQuarkDrops(event))
            return;
    }
    
    private static void replaceItemDrop(List<EntityItem> drops, Item itemToReplace, Item targetItem)
    {
        replaceItemDrop(drops, itemToReplace, Items.AIR, targetItem, 0, false);
    }
    
    private static void replaceItemDrop(List<EntityItem> drops, Item itemToReplace, Item targetItem, Item cookedItem, int meta, boolean onFire)
    {
        if (onFire)
            targetItem = cookedItem;
        
        final Item finalTargetItem = targetItem;
        
        drops.forEach((i) ->
        {
            if (i.getItem().getItem() == itemToReplace)
            {
                i.setItem(new ItemStack(finalTargetItem, i.getItem().getCount(), meta));
            }
        });
    }
    
    private static void dropFewItems(Item item, LivingDropsEvent event)
    {
        dropFewItems(item, Items.AIR, false, event.getEntityLiving(), event.getLootingLevel());
    }
    
    private static void dropFewItems(Item item, Item cooked, LivingDropsEvent event)
    {
        dropFewItems(item, cooked, event.getEntityLiving().isBurning(), event.getEntityLiving(), event.getLootingLevel());
    }
    
    private static void dropFewItems(Item item, Item cooked, boolean onFire, EntityLivingBase entity, int lootingLevel)
    {
        int i = entity.world.rand.nextInt(3);
        if (lootingLevel > 0)
        {
            i += entity.world.rand.nextInt(lootingLevel + 1);
        }
        
        if (onFire)
            item = cooked;
        
        for (int j = 0; j < i; ++j)
        {
            entity.dropItem(item, 1);
        }
    }
    
    @Optional.Method(modid = MO_CREATURES)
    private static boolean registerMoCreaturesDrops(LivingDropsEvent event)
    {
        if (MoCreaturesConfiguration.replaceFishDrops)
        {
            if (event.getEntityLiving() instanceof MoCEntityCod)
            {
                replaceItemDrop(event.getDrops(), Items.FISH, Items.FISH, Items.COOKED_FISH, 1, MiaConfig.addCookedDrops && event.getEntityLiving().isBurning());
                return true;
            }
            else if (event.getEntityLiving() instanceof MoCEntityClownFish)
            {
                replaceItemDrop(event.getDrops(), Items.FISH, Items.FISH, Items.FISH, 2, false);
                return true;
            }
        }
        
        if (MiaConfig.addCookedDrops && event.getEntityLiving().isBurning())
        {
            if (event.getEntityLiving() instanceof MoCEntityAquatic)
            {
                replaceItemDrop(event.getDrops(), Items.FISH, Items.COOKED_FISH);
                return true;
            }
            else if (event.getEntityLiving() instanceof MoCEntityDuck)
            {
                replaceItemDrop(event.getDrops(), MoCItems.crabraw, MoCItems.ostrichcooked);
                return true;
            }
            else if (event.getEntityLiving() instanceof MoCEntityCrab)
            {
                replaceItemDrop(event.getDrops(), MoCItems.crabraw, MoCItems.crabcooked);
                return true;
            }
            else if (event.getEntityLiving() instanceof MoCEntityRat)
            {
                replaceItemDrop(event.getDrops(), MoCItems.ratRaw, MoCItems.ratCooked);
                return true;
            }
            else if (event.getEntityLiving() instanceof MoCEntityTurkey)
            {
                replaceItemDrop(event.getDrops(), MoCItems.rawTurkey, MoCItems.cookedTurkey);
                return true;
            }
            else if (event.getEntityLiving() instanceof MoCEntityBoar)
            {
                replaceItemDrop(event.getDrops(), Items.PORKCHOP, Items.COOKED_PORKCHOP);
                return true;
            }
            else if (harvestcraftLoaded && event.getEntityLiving() instanceof MoCEntityTurtle)
            {
                replaceItemDrop(event.getDrops(), MoCItems.turtleraw, ItemRegistry.turtlecookedItem);
                return true;
            }
        }
        
        return false;
    }
    
    @Optional.Method(modid = QUARK)
    private static boolean registerQuarkDrops(LivingDropsEvent event)
    {
        if (MiaConfig.addCookedDrops)
        {
            if (Crabs.crabLeg != null && event.getEntity() instanceof EntityCrab)
            {
                replaceItemDrop(event.getDrops(), Crabs.crabLeg, Crabs.cookedCrabLeg);
                return true;
            }
            else if (Frogs.frogLeg != null && event.getEntity() instanceof EntityFrog)
            {
                replaceItemDrop(event.getDrops(), Frogs.frogLeg, Frogs.cookedFrogLeg);
                return true;
            }
        }
        
        return false;
    }
}