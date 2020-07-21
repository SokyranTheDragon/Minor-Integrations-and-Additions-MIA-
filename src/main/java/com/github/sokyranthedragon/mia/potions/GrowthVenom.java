package com.github.sokyranthedragon.mia.potions;

import com.github.sokyranthedragon.mia.Mia;
import com.github.sokyranthedragon.mia.utilities.size.SizeUtils;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import javax.annotation.ParametersAreNonnullByDefault;

public class GrowthVenom extends BasePotion
{
    public GrowthVenom() {
        super(false, 0x3FB4A7);
        setRegistryName(new ResourceLocation(Mia.MODID, "growth_venom"));
        setPotionName("effect.growthvenom");
        setEffectIcon(new ResourceLocation(Mia.MODID, "textures/gui/mia/size_up_strong.png"));
    }

    // Called every tick that isReady returns true
    @Override
    @ParametersAreNonnullByDefault
    public void performEffect(EntityLivingBase entityLivingBaseIn, int amplifier)
    {
        SizeUtils.changeEntitySizeBy(entityLivingBaseIn, (amplifier + 4) * 0.00125f);
    }
}
