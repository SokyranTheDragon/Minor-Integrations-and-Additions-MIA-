package com.github.sokyranthedragon.mia.integrations.thaumcraft.foci;

import com.github.sokyranthedragon.mia.potions.ModPotions;
import com.github.sokyranthedragon.mia.utilities.size.SizeUtils;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.SoundEvents;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.casters.FocusEffect;
import thaumcraft.api.casters.NodeSetting;
import thaumcraft.api.casters.Trajectory;
import thaumcraft.client.fx.ParticleEngine;
import thaumcraft.client.fx.particles.FXGeneric;

import javax.annotation.Nullable;

public class FocusEffectSizeChange extends FocusEffect
{
    @Override
    public boolean execute(RayTraceResult rayTraceResult, @Nullable Trajectory trajectory, float v, int i)
    {
        if (!SizeUtils.isSizeComponentEnabled)
            return false;
        
        if (rayTraceResult.typeOfHit == RayTraceResult.Type.ENTITY && rayTraceResult.entityHit instanceof EntityLivingBase)
        {
            EntityLivingBase entity = (EntityLivingBase) rayTraceResult.entityHit;
            entity.addPotionEffect(new PotionEffect(getEffect(), getSettingValue("duration") * 20, getSettingValue("power") - 1));
        }
        
        return false;
    }
    
    @Override
    public NodeSetting[] createSettings()
    {
        return new NodeSetting[]{
            new NodeSetting("power", "focus.common.power", new NodeSetting.NodeSettingIntRange(1, 4)),
            new NodeSetting("duration", "focus.common.duration", new NodeSetting.NodeSettingIntRange(2, 10)),
            new NodeSetting("type", "mia.focus.size", new NodeSetting.NodeSettingIntList(new int[]{ 0, 1 }, new String[]{ I18n.format("mia.focus.size_growth"), I18n.format("mia.focus.size_shrinking") })) };
    }
    
    @Override
    public void onCast(Entity caster)
    {
        caster.world.playSound(null, caster.getPosition().up(), SoundEvents.BLOCK_ENCHANTMENT_TABLE_USE, SoundCategory.PLAYERS, 0.2f, 1f);
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void renderParticleFX(World world, double x, double y, double z, double motionX, double motionY, double motionZ)
    {
        FXGeneric fx = new FXGeneric(world, x, y, z, motionX + world.rand.nextGaussian() * 0.01f, motionY + world.rand.nextGaussian() * 0.01f, motionZ + world.rand.nextGaussian() * 0.01f);
        
        fx.setMaxAge(10 + world.rand.nextInt(10));
        setParticleRgb(fx);
        fx.setGravity(0.01f);
        fx.setParticles(4, 16, 4);
        fx.setRandomMovementScale(0.0125F, 0.0125F, 0.0125F);
        fx.setGridSize(32);
        fx.setRotationSpeed(world.rand.nextFloat(), 0.0F);
        fx.setLoop(false);
        
        ParticleEngine.addEffect(world, fx);
    }
    
    @Override
    public int getComplexity()
    {
        return MathHelper.ceil((float) getSettingValue("power") * (float) getSettingValue("duration") * 0.75f) + 2;
    }
    
    @Override
    public Aspect getAspect()
    {
        return Aspect.MAGIC;
    }
    
    @Override
    public String getKey()
    {
        return "mia.focus.size_change";
    }
    
    @Override
    public String getResearch()
    {
        return "MIA.FOCUS_SIZE_CHANGE@2";
    }
    
    protected Potion getEffect()
    {
        if (getSettingValue("type") == 0)
            return ModPotions.growthPotion;
        else
            return ModPotions.shrinkingPotion;
    }
    
    @SideOnly(Side.CLIENT)
    protected void setParticleRgb(FXGeneric fx)
    {
        if (getSettingValue("type") == 0)
            fx.setRBGColorF(0.8f, 1.0f, 0.8f);
        else
            fx.setRBGColorF(1.0f, 0.6f, 0.6f);
    }
}
