package com.github.sokyranthedragon.mia.integrations.mia.modifiers;

import com.github.sokyranthedragon.mia.enchantments.ModEnchantments;
import com.github.sokyranthedragon.mia.integrations.mia.MiaTConstructIntegration;
import com.github.sokyranthedragon.mia.potions.ModPotions;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.common.util.FakePlayer;
import slimeknights.tconstruct.library.modifiers.IToolMod;
import slimeknights.tconstruct.library.modifiers.ModifierTrait;

public class ModifierShrinking extends ModifierTrait
{
    public ModifierShrinking()
    {
        super("shrinking", 0xFF9999, 2, 50);
    }
    
    @Override
    public boolean canApplyTogether(Enchantment enchantment)
    {
        return enchantment != ModEnchantments.SHRINKING;
    }
    
    @Override
    public boolean canApplyTogether(IToolMod otherModifier)
    {
        return otherModifier != MiaTConstructIntegration.modifierSizeSteal;
    }
    
    @Override
    public void onHit(ItemStack tool, EntityLivingBase player, EntityLivingBase target, float damage, boolean isCritical)
    {
        if (!(target instanceof FakePlayer) && !(player instanceof FakePlayer))
            target.addPotionEffect(new PotionEffect(ModPotions.shrinkingPotion, 100, this.getData(tool).level));
        
        super.onHit(tool, player, target, damage, isCritical);
    }
}
