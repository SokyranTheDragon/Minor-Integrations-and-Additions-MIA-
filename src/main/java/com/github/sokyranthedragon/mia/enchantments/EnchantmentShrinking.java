package com.github.sokyranthedragon.mia.enchantments;

import com.github.sokyranthedragon.mia.potions.ModPotions;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.common.util.FakePlayer;

public class EnchantmentShrinking extends Enchantment
{
    public EnchantmentShrinking()
    {
        super(Rarity.RARE, EnumEnchantmentType.WEAPON, new EntityEquipmentSlot[]{ EntityEquipmentSlot.MAINHAND });
    }
    
    @Override
    public int getMaxLevel()
    {
        return 3;
    }
    
    @Override
    public int getMinEnchantability(int level)
    {
        return 10 + 3 * level;
    }
    
    @Override
    public int getMaxEnchantability(int level)
    {
        return 100;
    }
    
    @Override
    public void onEntityDamaged(EntityLivingBase user, Entity target, int level)
    {
        if (user instanceof FakePlayer || target instanceof FakePlayer)
            return;
        if (target instanceof EntityLivingBase)
            ((EntityLivingBase) target).addPotionEffect(new PotionEffect(ModPotions.shrinkingPotion, 100, level - 1));
    }
    
    @Override
    public boolean canApply(ItemStack stack)
    {
        return stack.getItem() instanceof ItemAxe || super.canApply(stack);
    }
    
    @Override
    protected boolean canApplyTogether(Enchantment ench)
    {
        return !(ench instanceof EnchantmentSizeSteal);
    }
}