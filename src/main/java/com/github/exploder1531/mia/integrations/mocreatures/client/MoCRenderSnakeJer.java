package com.github.exploder1531.mia.integrations.mocreatures.client;

import drzhark.mocreatures.client.model.MoCModelSnake;
import drzhark.mocreatures.client.renderer.entity.MoCRenderSnake;
import drzhark.mocreatures.entity.IMoCEntity;
import drzhark.mocreatures.entity.passive.MoCEntitySnake;
import jeresources.util.FakeClientWorld;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class MoCRenderSnakeJer extends MoCRenderSnake
{
    public MoCRenderSnakeJer()
    {
        super(new MoCModelSnake(), 0.0f);
    }
    
    @Override
    protected void preRenderCallback(MoCEntitySnake entitySnake, float f)
    {
        if (entitySnake.world instanceof FakeClientWorld)
        {
            this.adjustPitch(entitySnake);
            this.adjustRoll(entitySnake);
            this.adjustYaw(entitySnake);
            this.stretch((IMoCEntity)entitySnake);
        }
        else
            super.preRenderCallback(entitySnake, f);
    }
}
