package com.github.sokyranthedragon.mia.integrations.aether_lost_content;

import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.integrations.jer.IJerIntegration;
import com.github.sokyranthedragon.mia.utilities.LootTableUtils;
import com.legacy.aether.world.AetherWorld;
import com.legacy.lostaether.LostLootTables;
import com.legacy.lostaether.entities.EntityAerwhaleKing;
import com.legacy.lostaether.entities.EntityZephyroo;
import jeresources.api.IMobRegistry;
import jeresources.api.conditionals.LightLevel;
import jeresources.util.MobTableBuilder;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTableManager;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
class JerAetherLostContentIntegration implements IJerIntegration
{
    @Override
    public Set<Class<? extends EntityLivingBase>> addMobs(MobTableBuilder builder, Set<Class<? extends EntityLivingBase>> ignoreMobOverrides)
    {
        builder.add(LootTableUtils.loadUniqueEmptyLootTable(), EntityZephyroo.class);
        builder.add(LostLootTables.king_aerwhale, EntityAerwhaleKing.class);
        
        return Stream.of(EntityZephyroo.class, EntityAerwhaleKing.class).collect(Collectors.toSet());
    }
    
    @Override
    public void configureMob(ResourceLocation resource, EntityLivingBase entity, @Nullable LootTableManager manager, IMobRegistry mobRegistry)
    {
        int experienceMin = 1;
        int experienceMax = 3;
        
        if (entity instanceof EntityAerwhaleKing)
            experienceMin = experienceMax = 30;
    
        mobRegistry.register(entity, LightLevel.any, experienceMin, experienceMax, new String[]{ AetherWorld.aether_biome.getBiomeName() }, resource);
    }
    
    @Override
    public ModIds getModId()
    {
        return ModIds.AETHER_LOST_CONTENT;
    }
}
