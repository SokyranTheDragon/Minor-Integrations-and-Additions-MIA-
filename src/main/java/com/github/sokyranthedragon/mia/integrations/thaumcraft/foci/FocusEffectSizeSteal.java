package com.github.sokyranthedragon.mia.integrations.thaumcraft.foci;

import com.github.sokyranthedragon.mia.potions.ModPotions;
import com.github.sokyranthedragon.mia.utilities.size.SizeUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.SoundEvents;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.common.util.FakePlayer;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.casters.FocusEffect;
import thaumcraft.api.casters.NodeSetting;
import thaumcraft.api.casters.Trajectory;
import thaumcraft.client.fx.ParticleEngine;
import thaumcraft.client.fx.particles.FXGeneric;

import javax.annotation.Nullable;

public class FocusEffectSizeSteal extends FocusEffect
{
    @Override
    public boolean execute(RayTraceResult rayTraceResult, @Nullable Trajectory trajectory, float v, int i)
    {
        if (!SizeUtils.isSizeComponentEnabled)
            return false;
        
        if (rayTraceResult.typeOfHit == RayTraceResult.Type.ENTITY && rayTraceResult.entityHit instanceof EntityLivingBase)
        {
            EntityLivingBase caster = getPackage().getCaster();
            EntityLivingBase target = (EntityLivingBase) rayTraceResult.entityHit;
            
            if (caster instanceof FakePlayer || caster == target || !SizeUtils.canEntityBeScaled(target))
                return false;
            
            target.addPotionEffect(new PotionEffect(ModPotions.shrinkingPotion, 20, 0));
            caster.addPotionEffect(new PotionEffect(ModPotions.growthPotion, 20, 0));
        }
        
        return false;
    }
    
    @Override
    public NodeSetting[] createSettings()
    {
        return new NodeSetting[]{ new NodeSetting("power", "focus.common.power", new NodeSetting.NodeSettingIntRange(1, 2)) };
    }
    
    @Override
    public void onCast(Entity caster)
    {
        caster.world.playSound(null, caster.getPosition().up(), SoundEvents.BLOCK_ENCHANTMENT_TABLE_USE, SoundCategory.PLAYERS, 0.2f, 1f);
    }
    
    @Override
    public void renderParticleFX(World world, double x, double y, double z, double motionX, double motionY, double motionZ)
    {
        FXGeneric fx = new FXGeneric(world, x, y, z, motionX + world.rand.nextGaussian() * 0.01f, motionY + world.rand.nextGaussian() * 0.01f, motionZ + world.rand.nextGaussian() * 0.01f);
        
        fx.setMaxAge(10 + world.rand.nextInt(10));
        fx.setRBGColorF(0.8f, 0.8f, 0.8f);
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
        return getSettingValue("power") * 10 + 5;
    }
    
    @Override
    public Aspect getAspect()
    {
        return Aspect.MAGIC;
    }
    
    @Override
    public String getKey()
    {
        return "mia.focus.size_steal";
    }
    
    @Override
    public String getResearch()
    {
        return "MIA.FOCUS_SIZE_STEAL@2";
    }
}
