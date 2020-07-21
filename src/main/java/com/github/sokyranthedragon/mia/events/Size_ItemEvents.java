package com.github.sokyranthedragon.mia.events;

import com.github.sokyranthedragon.mia.Mia;
import com.github.sokyranthedragon.mia.core.MiaItems;
import com.github.sokyranthedragon.mia.potions.ModPotions;
import com.github.sokyranthedragon.mia.utilities.PotionUtils;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.StringUtils;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

@Mod.EventBusSubscriber(modid = Mia.MODID)
public class Size_ItemEvents
{
    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public static void itemTooltipEvent(ItemTooltipEvent event)
    {
        if (event.getToolTip() == null || event.getToolTip().size() < 2)
            return;
        
        List<String> tooltip = event.getToolTip();
        ItemStack stack = event.getItemStack();
        
        if (stack.getItem() == Items.TIPPED_ARROW
            || stack.getItem() == Items.POTIONITEM
            || stack.getItem() == Items.SPLASH_POTION
            || stack.getItem() == Items.LINGERING_POTION)
        {
            if (PotionUtils.arePotionsSame(stack, ModPotions.sizeStabilizationPotion))
            {
                String text = tooltip.get(1);
                tooltip.set(1, text.substring(0, text.lastIndexOf('(')));
                return;
            }
            
//            if (PotionUtils.isPotionAnyOf(stack, ModPotions.growthVenom, ModPotions.shrinkingVenom))
//                tooltip.set(0, EnumRarity.RARE.color + tooltip.get(0));
            if (stack.getItem() != Items.TIPPED_ARROW)
                return;
            
            if (PotionUtils.isPotionAnyOf(stack, ModPotions.growthPotion, ModPotions.shrinkingPotion /*, ModPotions.growthVenom, ModPotions.shrinkingVenom */))
            {
                PotionEffect effect = net.minecraft.potion.PotionUtils.getEffectsFromStack(stack).get(0);
                
                String text = tooltip.get(1);
                tooltip.set(1, text.replaceFirst("\\(.*\\)", "(" + StringUtils.ticksToElapsedTime(MathHelper.floor((float) (effect.getDuration() / 2))) + ")"));
            }
        }
//        else if (stack.getItem() == Items.ENCHANTED_BOOK)
//        {
//            EnchantmentHelper.getEnchantmentLevel(ModEnchantments.KOBOLD, stack);
//        }
        else if (stack.getItem() == MiaItems.koboldRing)
            tooltip.add(I18n.format("tooltip.ring_of_the_kobold"));
    }
}