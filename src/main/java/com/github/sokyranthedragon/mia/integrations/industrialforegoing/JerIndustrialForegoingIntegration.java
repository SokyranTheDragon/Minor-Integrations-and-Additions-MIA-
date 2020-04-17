package com.github.sokyranthedragon.mia.integrations.industrialforegoing;

import com.buuz135.industrial.entity.EntityPinkSlime;
import com.buuz135.industrial.proxy.CommonProxy;
import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.integrations.jer.IJerIntegration;
import jeresources.api.IMobRegistry;
import jeresources.api.conditionals.LightLevel;
import jeresources.util.MobTableBuilder;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTableManager;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Collections;
import java.util.Set;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
class JerIndustrialForegoingIntegration implements IJerIntegration
{
    @Override
    public Set<Class<? extends EntityLivingBase>> addMobs(MobTableBuilder builder, Set<Class<? extends EntityLivingBase>> ignoreMobOverrides)
    {
        builder.add(CommonProxy.PINK_SLIME_LOOT, EntityPinkSlime.class);
        
        return Collections.singleton(EntityPinkSlime.class);
    }
    
    @Override
    public void configureMob(ResourceLocation resource, EntityLivingBase entity, @Nullable LootTableManager manager, IMobRegistry mobRegistry)
    {
        mobRegistry.register(entity, LightLevel.any, 10, resource);
    }
    
    @Override
    public ModIds getModId()
    {
        return ModIds.INDUSTRIAL_FOREGOING;
    }
}
