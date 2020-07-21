package com.github.sokyranthedragon.mia.potions;

import com.github.sokyranthedragon.mia.Mia;
import com.github.sokyranthedragon.mia.utilities.size.SizeUtils;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import javax.annotation.ParametersAreNonnullByDefault;

public class ShrinkingPotion extends BasePotion
{
    public ShrinkingPotion() {
        super(false, 0xFF9999);
        setRegistryName(new ResourceLocation(Mia.MODID, "shrinking_potion"));
        setPotionName("effect.shrinking");
        setEffectIcon(new ResourceLocation(Mia.MODID, "textures/gui/mia/size_down.png"));
    }

    // Called every tick that isReady returns true
    @Override
    @ParametersAreNonnullByDefault
    public void performEffect(EntityLivingBase entityLivingBaseIn, int amplifier)
    {
        SizeUtils.changeEntitySizeBy(entityLivingBaseIn, (amplifier + 1) * -0.00125f);
    }
}
