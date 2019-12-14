package com.github.exploder1531.mia.integrations.jer;

import com.github.exploder1531.mia.integrations.base.IModIntegration;
import com.google.common.collect.Sets;
import jeresources.api.IDungeonRegistry;
import jeresources.api.IMobRegistry;
import jeresources.entry.MobEntry;
import jeresources.util.MobTableBuilder;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTableManager;

import java.util.Set;

public interface IJerIntegration extends IModIntegration
{
    default Set<Object> addMobs(MobTableBuilder builder, Set<Object> ignoreMobOverrides)
    {
        return Sets.newHashSet();
    }
    
    default void configureMob(ResourceLocation resource, EntityLivingBase entity, LootTableManager manager, IMobRegistry mobRegistry)
    {
    }
    
    default void overrideExistingMobDrops(MobEntry mobEntry)
    {
    }
    
    default void addMobRenderHooks(IMobRegistry mobRegistry)
    {
    }
    
    default void addDungeonLoot(IDungeonRegistry dungeonRegistry)
    {
    }
}
