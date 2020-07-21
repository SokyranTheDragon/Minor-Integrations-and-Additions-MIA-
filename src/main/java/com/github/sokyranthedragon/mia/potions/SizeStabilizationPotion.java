package com.github.sokyranthedragon.mia.potions;

import com.github.sokyranthedragon.mia.Mia;
import com.github.sokyranthedragon.mia.core.MiaItems;
import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.utilities.BaublesUtils;
import com.github.sokyranthedragon.mia.utilities.size.SizeUtils;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;

import javax.annotation.ParametersAreNonnullByDefault;

public class SizeStabilizationPotion extends BasePotion
{
    public SizeStabilizationPotion()
    {
        super(false, 0xE1E1E1);
        setRegistryName(new ResourceLocation(Mia.MODID, "size_stabilization"));
        setPotionName("effect.size_stabilization");
        setEffectIcon(new ResourceLocation(Mia.MODID, "textures/gui/mia/size_neutral.png"));
    }
    
    // Called every tick that isReady returns true
    @Override
    @ParametersAreNonnullByDefault
    public void performEffect(EntityLivingBase entityLivingBaseIn, int amplifier)
    {
        float size = SizeUtils.getEntitySize(entityLivingBaseIn);
        float targetSize = 1f;
        
        if (ModIds.BAUBLES.isLoaded && entityLivingBaseIn instanceof EntityPlayer && BaublesUtils.isBaubleActiveAFO((EntityPlayer) entityLivingBaseIn, MiaItems.koboldRing))
            targetSize = 0.5f;
        
        if (size > targetSize)
            SizeUtils.setEntitySize(entityLivingBaseIn, Math.max(targetSize, size - (float) (amplifier + 1) * 0.0025f));
        else if (size < targetSize)
            SizeUtils.setEntitySize(entityLivingBaseIn, Math.min(targetSize, size + (float) (amplifier + 1) * 0.0025f));
        else
        {
            PotionEffect effect = entityLivingBaseIn.getActivePotionEffect(ModPotions.sizeStabilizationPotion);
            
            // If we finished working and the duration is bigger than 5 seconds, we set it down to 5 seconds
            if (effect == null || effect.getDuration() > 20 * 5)
            {
                entityLivingBaseIn.removePotionEffect(ModPotions.sizeStabilizationPotion);
                entityLivingBaseIn.addPotionEffect(new PotionEffect(ModPotions.sizeStabilizationPotion, 20 * 5));
            }
        }
    }
}
