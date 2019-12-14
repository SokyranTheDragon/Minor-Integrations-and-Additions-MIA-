package com.github.exploder1531.mia.integrations.mocreatures.client;

import drzhark.mocreatures.client.model.MoCModelMediumFish;
import jeresources.util.FakeClientWorld;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

import java.lang.reflect.Field;

public class MoCModelMediumFishJer extends MoCModelMediumFish
{
    protected static final Field HeadField;
    protected static final Field LowerHeadField;
    protected static final Field NoseField;
    protected static final Field MouthBottomField;
    protected static final Field MouthBottomBField;
    protected static final Field BodyField;
    protected static final Field BackUpField;
    protected static final Field BackDownField;
    protected static final Field TailField;
    protected static final Field TailFinField;
    protected static final Field RightPectoralFinField;
    protected static final Field LeftPectoralFinField;
    protected static final Field UpperFinField;
    protected static final Field LowerFinField;
    protected static final Field RightLowerFinField;
    protected static final Field LeftLowerFinField;
    
    static
    {
        Field tempHeadField = null;
        Field tempLowerHeadField = null;
        Field tempNoseField = null;
        Field tempMouthBottomField = null;
        Field tempMouthBottomBField = null;
        Field tempBodyField = null;
        Field tempBackUpField = null;
        Field tempBackDownField = null;
        Field tempTailField = null;
        Field tempTailFinField = null;
        Field tempRightPectoralFinField = null;
        Field tempLeftPectoralFinField = null;
        Field tempUpperFinField = null;
        Field tempLowerFinField = null;
        Field tempRightLowerFinField = null;
        Field tempLeftLowerFinField = null;
        
        try
        {
            tempHeadField = MoCModelMediumFish.class.getDeclaredField("Head");
            tempHeadField.setAccessible(true);
            tempLowerHeadField = MoCModelMediumFish.class.getDeclaredField("LowerHead");
            tempLowerHeadField.setAccessible(true);
            tempNoseField = MoCModelMediumFish.class.getDeclaredField("Nose");
            tempNoseField.setAccessible(true);
            tempMouthBottomField = MoCModelMediumFish.class.getDeclaredField("MouthBottom");
            tempMouthBottomField.setAccessible(true);
            tempMouthBottomBField = MoCModelMediumFish.class.getDeclaredField("MouthBottomB");
            tempMouthBottomBField.setAccessible(true);
            tempBodyField = MoCModelMediumFish.class.getDeclaredField("Body");
            tempBodyField.setAccessible(true);
            tempBackUpField = MoCModelMediumFish.class.getDeclaredField("BackUp");
            tempBackUpField.setAccessible(true);
            tempBackDownField = MoCModelMediumFish.class.getDeclaredField("BackDown");
            tempBackDownField.setAccessible(true);
            tempTailField = MoCModelMediumFish.class.getDeclaredField("Tail");
            tempTailField.setAccessible(true);
            tempTailFinField = MoCModelMediumFish.class.getDeclaredField("TailFin");
            tempTailFinField.setAccessible(true);
            tempRightPectoralFinField = MoCModelMediumFish.class.getDeclaredField("RightPectoralFin");
            tempRightPectoralFinField.setAccessible(true);
            tempLeftPectoralFinField = MoCModelMediumFish.class.getDeclaredField("LeftPectoralFin");
            tempLeftPectoralFinField.setAccessible(true);
            tempUpperFinField = MoCModelMediumFish.class.getDeclaredField("UpperFin");
            tempUpperFinField.setAccessible(true);
            tempLowerFinField = MoCModelMediumFish.class.getDeclaredField("LowerFin");
            tempLowerFinField.setAccessible(true);
            tempRightLowerFinField = MoCModelMediumFish.class.getDeclaredField("RightLowerFin");
            tempRightLowerFinField.setAccessible(true);
            tempLeftLowerFinField = MoCModelMediumFish.class.getDeclaredField("LeftLowerFin");
            tempLeftLowerFinField.setAccessible(true);
        } catch (NoSuchFieldException e)
        {
            e.printStackTrace();
        }
        
        HeadField = tempHeadField;
        LowerHeadField = tempLowerHeadField;
        NoseField = tempNoseField;
        MouthBottomField = tempMouthBottomField;
        MouthBottomBField = tempMouthBottomBField;
        BodyField = tempBodyField;
        BackUpField = tempBackUpField;
        BackDownField = tempBackDownField;
        TailField = tempTailField;
        TailFinField = tempTailFinField;
        RightPectoralFinField = tempRightPectoralFinField;
        LeftPectoralFinField = tempLeftPectoralFinField;
        UpperFinField = tempUpperFinField;
        LowerFinField = tempLowerFinField;
        RightLowerFinField = tempRightLowerFinField;
        LeftLowerFinField = tempLeftLowerFinField;
    }
    
    @Override
    public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
    {
        if (entity.world instanceof FakeClientWorld)
        {
            this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
            GL11.glPushMatrix();
            GL11.glTranslatef(0.0f, 1.0f, 0.2f);
            try
            {
                ((ModelRenderer) HeadField.get(this)).render(scale);
                ((ModelRenderer) LowerHeadField.get(this)).render(scale);
                ((ModelRenderer) NoseField.get(this)).render(scale);
                ((ModelRenderer) MouthBottomField.get(this)).render(scale);
                ((ModelRenderer) MouthBottomBField.get(this)).render(scale);
                ((ModelRenderer) BodyField.get(this)).render(scale);
                ((ModelRenderer) BackUpField.get(this)).render(scale);
                ((ModelRenderer) BackDownField.get(this)).render(scale);
                ((ModelRenderer) TailField.get(this)).render(scale);
                ((ModelRenderer) TailFinField.get(this)).render(scale);
                ((ModelRenderer) RightPectoralFinField.get(this)).render(scale);
                ((ModelRenderer) LeftPectoralFinField.get(this)).render(scale);
                ((ModelRenderer) UpperFinField.get(this)).render(scale);
                ((ModelRenderer) LowerFinField.get(this)).render(scale);
                ((ModelRenderer) RightLowerFinField.get(this)).render(scale);
                ((ModelRenderer) LeftLowerFinField.get(this)).render(scale);
            } catch (IllegalAccessException e)
            {
                e.printStackTrace();
            }
            GL11.glPopMatrix();
        }
        else
            super.render(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
        
    }
}
