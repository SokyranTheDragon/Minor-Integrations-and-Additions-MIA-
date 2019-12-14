package com.github.exploder1531.mia.integrations.mocreatures.client;

import drzhark.mocreatures.client.renderer.entity.MoCRenderMoC;
import drzhark.mocreatures.entity.IMoCEntity;
import jeresources.util.FakeClientWorld;
import net.minecraft.entity.EntityLiving;

public class MoCRenderMediumFishJer<T extends EntityLiving> extends MoCRenderMoC<T>
{
    public MoCRenderMediumFishJer()
    {
        super(new MoCModelMediumFishJer(), 0.2f);
    }
    
    @Override
    protected void preRenderCallback(T entityLiving, float f)
    {
        if (entityLiving.world instanceof FakeClientWorld)
        {
            IMoCEntity moCreature = (IMoCEntity)entityLiving;
            this.adjustPitch(moCreature);
            this.adjustRoll(moCreature);
//            this.adjustYaw(moCreature);
            this.stretch(moCreature);
        }
        else
            super.preRenderCallback(entityLiving, f);
    }
}
