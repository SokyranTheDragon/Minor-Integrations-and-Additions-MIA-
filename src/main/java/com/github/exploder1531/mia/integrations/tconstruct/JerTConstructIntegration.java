package com.github.exploder1531.mia.integrations.tconstruct;

import com.github.exploder1531.mia.integrations.ModIds;
import com.github.exploder1531.mia.integrations.jer.IJerIntegration;
import com.google.common.collect.Sets;
import jeresources.api.IMobRegistry;
import jeresources.api.conditionals.LightLevel;
import jeresources.api.drop.LootDrop;
import jeresources.util.LootTableHelper;
import jeresources.util.MobTableBuilder;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTable;
import net.minecraft.world.storage.loot.LootTableManager;
import slimeknights.tconstruct.world.entity.EntityBlueSlime;

import javax.annotation.Nonnull;
import java.util.Set;

class JerTConstructIntegration implements IJerIntegration
{
    @Override
    public Set<Class> addMobs(MobTableBuilder builder, Set<Class> ignoreMobOverrides)
    {
        builder.add(EntityBlueSlime.LOOT_TABLE, EntityBlueSlime.class);
        
        return Sets.newHashSet(EntityBlueSlime.class);
    }
    
    @Override
    public void configureMob(ResourceLocation resource, EntityLivingBase entity, LootTableManager manager, IMobRegistry mobRegistry)
    {
        LootTable loot = manager.getLootTableFromLocation(resource);
        LootDrop[] drops = LootTableHelper.toDrops(loot).toArray(new LootDrop[0]);
        mobRegistry.register(entity, LightLevel.any, 10, drops);
    }
    
    @Nonnull
    @Override
    public String getModId()
    {
        return ModIds.TINKERS_CONSTRUCT;
    }
}
