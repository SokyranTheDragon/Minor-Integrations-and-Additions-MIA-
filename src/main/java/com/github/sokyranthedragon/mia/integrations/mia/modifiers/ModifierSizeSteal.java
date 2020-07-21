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

public class ModifierSizeSteal extends ModifierTrait
{
    public ModifierSizeSteal()
    {
        super("size_steal", 0xCCFFCC, 0, 50);
    }
    
    @Override
    public boolean canApplyTogether(Enchantment enchantment)
    {
        return enchantment != ModEnchantments.SIZE_STEAL;
    }
    
    @Override
    public boolean canApplyTogether(IToolMod otherModifier)
    {
        return otherModifier != MiaTConstructIntegration.modifierShrinking;
    }
    
    @Override
    public void onHit(ItemStack tool, EntityLivingBase player, EntityLivingBase target, float damage, boolean isCritical)
    {
        if (!(target instanceof FakePlayer) && !(player instanceof FakePlayer))
            target.addPotionEffect(new PotionEffect(ModPotions.shrinkingPotion, 100, 1));
        
        super.onHit(tool, player, target, damage, isCritical);
    }
}
