package com.github.exploder1531.mia.integrations.dungeontactics;

import com.github.exploder1531.mia.integrations.ModIds;
import com.github.exploder1531.mia.integrations.jer.IJerIntegration;
import com.google.common.collect.Sets;
import jeresources.api.IMobRegistry;
import jeresources.api.conditionals.LightLevel;
import jeresources.entry.MobEntry;
import jeresources.util.MobTableBuilder;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTableManager;
import pegbeard.dungeontactics.entities.DTEntityTowerGuardian;
import pegbeard.dungeontactics.handlers.DTLoots;

import javax.annotation.Nonnull;
import java.util.Set;

class JerDungeonTacticsIntegration implements IJerIntegration
{
    @Override
    public Set<Class> addMobs(MobTableBuilder builder, Set<Class> ignoreMobOverrides)
    {
        builder.add(DTLoots.TOWERGUARDIAN_LOOT, DTEntityTowerGuardian.class);
        
        return Sets.newHashSet(
                DTEntityTowerGuardian.class
        );
    }
    
    @Override
    public void configureMob(ResourceLocation resource, EntityLivingBase entity, LootTableManager manager, IMobRegistry mobRegistry)
    {
        if (entity instanceof DTEntityTowerGuardian)
            mobRegistry.register(entity, LightLevel.any);
    }
    
    @Override
    public void overrideExistingMobDrops(MobEntry mobEntry)
    {
//        if (mobEntry.getEntity() instanceof EntityWitch || mobEntry.getEntity() instanceof EntityEvoker)
//        {
//            List<ItemStack> injected = Arrays.asList(new ItemStack(DTItems.MAGIC_POWDER), new ItemStack(DTItems.MAGIC_SCROLL));
//            JerOverrideHelper.removeDuplicateEntries(injected, mobEntry.getDropsItemStacks());
//
//            for (ItemStack item : injected)
//            {
//                mobEntry.addDrops(new LootDrop(item));
//            }
//        }
    }
    
    @Nonnull
    @Override
    public String getModId()
    {
        return ModIds.DUNGEON_TACTICS;
    }
}
