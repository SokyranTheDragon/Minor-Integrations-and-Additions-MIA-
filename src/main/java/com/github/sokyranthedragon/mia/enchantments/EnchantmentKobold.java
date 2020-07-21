package com.github.sokyranthedragon.mia.enchantments;

import com.github.sokyranthedragon.mia.utilities.size.SizeUtils;
import com.google.common.collect.Sets;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemElytra;
import net.minecraft.item.ItemStack;

import java.util.Set;
import java.util.UUID;

public class EnchantmentKobold extends Enchantment
{
    public static Set<UUID> koboldPlayers = Sets.newHashSet();
    
    protected EnchantmentKobold()
    {
        super(Rarity.RARE, EnumEnchantmentType.ARMOR_CHEST, new EntityEquipmentSlot[]{ EntityEquipmentSlot.CHEST });
    }
    
    @Override
    public int getMaxLevel()
    {
        return 5;
    }
    
    @Override
    public int getMinEnchantability(int level)
    {
        return 5 + 5 * level;
    }
    
    @Override
    public int getMaxEnchantability(int level)
    {
        return 100;
    }
    
    @Override
    public boolean canApply(ItemStack stack)
    {
        return stack.getItem() instanceof ItemElytra || super.canApply(stack);
    }
    
    public static float getSizeModifier(EntityPlayer player, float size)
    {
        ItemStack armor = player.inventory.armorInventory.get(EntityEquipmentSlot.CHEST.getIndex());
        int level = EnchantmentHelper.getEnchantmentLevel(ModEnchantments.KOBOLD, armor);
        
        if (level == 0)
            return size;
        return Math.min(size + (1 - size) * 0.2f * level, 1f);
    }
    
    public static boolean checkKoboldEnchantment(EntityPlayer player)
    {
        ItemStack chest = player.inventory.armorInventory.get(EntityEquipmentSlot.CHEST.getIndex());
        
        if (chest.isItemEnchanted())
        {
            if (EnchantmentHelper.getEnchantmentLevel(ModEnchantments.KOBOLD, chest) > 0 && !EnchantmentKobold.koboldPlayers.contains(player.getGameProfile().getId()))
            {
                EnchantmentKobold.koboldPlayers.add(player.getGameProfile().getId());
                SizeUtils.validateEntitySize(player);
                return true;
            }
        }
        else
        {
            if (EnchantmentHelper.getEnchantmentLevel(ModEnchantments.KOBOLD, chest) == 0 && EnchantmentKobold.koboldPlayers.contains(player.getGameProfile().getId()))
            {
                EnchantmentKobold.koboldPlayers.remove(player.getGameProfile().getId());
                SizeUtils.validateEntitySize(player);
                return true;
            }
        }
        
        return false;
    }
}
