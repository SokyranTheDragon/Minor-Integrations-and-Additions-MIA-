package com.github.sokyranthedragon.mia.integrations.mocreatures.client;

import drzhark.mocreatures.client.model.MoCModelTurtle;
import drzhark.mocreatures.client.renderer.entity.MoCRenderTurtle;
import drzhark.mocreatures.entity.passive.MoCEntityTurtle;
import jeresources.util.FakeClientWorld;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class MoCRenderTurtleJer extends MoCRenderTurtle
{
    public MoCRenderTurtleJer()
    {
        super(new MoCModelTurtle(), 0.4f);
    }
    
    @Override
    protected void preRenderCallback(MoCEntityTurtle entityTurtle, float f)
    {
        if (entityTurtle.world instanceof FakeClientWorld)
        {
            this.adjustHeight(entityTurtle, 0.05F * (float)entityTurtle.getEdad() * 0.01F);
            this.stretch(entityTurtle);
        }
        else
            super.preRenderCallback(entityTurtle, f);
    }
}
