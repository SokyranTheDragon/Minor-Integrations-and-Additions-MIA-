package com.github.sokyranthedragon.mia.integrations.iceandfire.client;

import com.github.alexthe666.iceandfire.client.model.ModelHippocampus;
import com.github.alexthe666.iceandfire.entity.EntityHippocampus;
import jeresources.util.FakeClientWorld;
import net.ilexiconn.llibrary.client.model.tools.AdvancedModelRenderer;
import net.minecraft.entity.Entity;

public class ModelHippocampusJer extends ModelHippocampus
{
    @Override
    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
    {
        if (entity.world instanceof FakeClientWorld)
        {
            EntityHippocampus hippo = (EntityHippocampus) entity;
            float speed_idle = 0.05F;
            float speed_swim = 0.35F;
            float degree_idle = 0.5F;
            float degree_swim = 0.75F;
            this.progressRotation(this.Body, hippo.onLandProgress, (float) Math.toRadians(-5.0D), 0.0F, 0.0F);
            this.progressRotation(this.FrontThighL, Math.max(0.0F, hippo.onLandProgress), 0.0F, 0.0F, (float) Math.toRadians(-65.0D));
            this.progressRotation(this.FrontThighR, Math.max(0.0F, hippo.onLandProgress), 0.0F, 0.0F, (float) Math.toRadians(65.0D));
            this.progressPosition(this.Body, hippo.onLandProgress, 0.0F, 20.0F, 0.0F);
            this.progressRotation(this.Body, hippo.sitProgress, (float) Math.toRadians(-5.0D), 0.0F, 0.0F);
            this.progressRotation(this.Tail_1, hippo.sitProgress, (float) Math.toRadians(55.0D), 0.0F, 0.0F);
            this.progressRotation(this.Tail_2, hippo.sitProgress, (float) Math.toRadians(-26.0D), 0.0F, 0.0F);
            this.progressRotation(this.Tail_3, hippo.sitProgress, (float) Math.toRadians(-33.0D), 0.0F, 0.0F);
            this.progressRotation(this.FlukeR, Math.max(0.0F, hippo.sitProgress), (float) Math.toRadians(-50.0D), (float) Math.toRadians(5.0D), (float) Math.toRadians(30.0D));
            this.progressRotation(this.FlukeL, Math.max(0.0F, hippo.sitProgress), (float) Math.toRadians(-50.0D), (float) Math.toRadians(-5.0D), (float) Math.toRadians(-30.0D));
            this.progressRotation(this.Body, hippo.sitProgress * hippo.onLandProgress * 0.05F, (float) Math.toRadians(-5.0D), (float) Math.toRadians(-5.0D), (float) Math.toRadians(85.0D));
            this.progressPosition(this.Body, hippo.sitProgress * hippo.onLandProgress * 0.05F, 0.0F, 10.0F, 0.0F);
            
            this.progressRotation(this.Tail_2, hippo.sitProgress * hippo.onLandProgress * 0.05F, (float) Math.toRadians(-7.0D), (float) Math.toRadians(-25.0D), (float) Math.toRadians(1.0D));
            this.progressRotation(this.Tail_3, hippo.sitProgress * hippo.onLandProgress * 0.05F, (float) Math.toRadians(20.0D), (float) Math.toRadians(-36.0D), (float) Math.toRadians(36.0D));
            AdvancedModelRenderer[] TAIL = new AdvancedModelRenderer[]{ this.Tail_1, this.Tail_2, this.Tail_3 };
            AdvancedModelRenderer[] TAIL_W_BODY = new AdvancedModelRenderer[]{ this.Body, this.Tail_1, this.Tail_2, this.Tail_3 };
            AdvancedModelRenderer[] LEG_L = new AdvancedModelRenderer[]{ this.FrontThighL, this.FrontLegL };
            AdvancedModelRenderer[] LEG_R = new AdvancedModelRenderer[]{ this.FrontThighR, this.FrontLegR };
            AdvancedModelRenderer[] NECK = new AdvancedModelRenderer[]{ this.Neck, this.Head };
            
            this.chainWave(NECK, speed_swim, degree_swim * 0.15F, -2.0D, f, f1);
            this.chainWave(TAIL_W_BODY, speed_swim, degree_swim * 0.15F, -3.0D, f, f1);
            this.walk(this.Tail_3, speed_swim, degree_swim * -0.5F, false, 0.0F, 0.0F, f, f1);
            this.chainWave(LEG_L, speed_swim, degree_swim * 0.75F, 1.0D, f, f1);
            this.chainWave(LEG_R, speed_swim, degree_swim * 0.75F, 1.0D, f, f1);
            this.walk(this.Tail_1, speed_idle, degree_idle * 0.15F, false, 0.0F, 0.0F, (float) entity.ticksExisted, 1.0F);
            this.walk(this.Tail_2, speed_idle, degree_idle * 0.25F, false, 0.0F, 0.0F, (float) entity.ticksExisted, 1.0F);
            
            this.chainWave(NECK, speed_idle, degree_idle * 0.15F, -2.0D, (float) entity.ticksExisted, 1.0F);
            hippo.tail_buffer.applyChainSwingBuffer(TAIL);
        }
        else
            super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    }
}
