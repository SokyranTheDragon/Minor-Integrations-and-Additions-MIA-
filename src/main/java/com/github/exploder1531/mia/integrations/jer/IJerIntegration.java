package com.github.exploder1531.mia.integrations.jer;

import com.github.exploder1531.mia.integrations.base.IModIntegration;
import jeresources.api.IDungeonRegistry;
import jeresources.api.IMobRegistry;
import jeresources.entry.MobEntry;
import jeresources.util.MobTableBuilder;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTableManager;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.HashSet;
import java.util.Set;

@ParametersAreNonnullByDefault
public interface IJerIntegration extends IModIntegration
{
    @Nonnull
    default Set<Class<? extends EntityLivingBase>> addMobs(MobTableBuilder builder, Set<Class<? extends EntityLivingBase>> ignoreMobOverrides)
    {
        return new HashSet<>();
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
