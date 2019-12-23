package com.github.exploder1531.mia.integrations.dungeontactics;

import com.github.exploder1531.mia.integrations.ModIds;
import com.github.exploder1531.mia.integrations.jer.IJerIntegration;
import jeresources.api.IMobRegistry;
import jeresources.api.conditionals.LightLevel;
import jeresources.api.drop.LootDrop;
import jeresources.entry.MobEntry;
import jeresources.util.LootTableHelper;
import jeresources.util.MobTableBuilder;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.monster.EntityGuardian;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTable;
import net.minecraft.world.storage.loot.LootTableManager;
import pegbeard.dungeontactics.entities.DTEntityTowerGuardian;
import pegbeard.dungeontactics.handlers.DTConfigHandler;
import pegbeard.dungeontactics.handlers.DTItems;
import pegbeard.dungeontactics.handlers.DTLoots;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Collections;
import java.util.Set;

@ParametersAreNonnullByDefault
class JerDungeonTacticsIntegration implements IJerIntegration
{
    @Nonnull
    @Override
    public Set<Class<? extends EntityLivingBase>> addMobs(MobTableBuilder builder, Set<Class<? extends EntityLivingBase>> ignoreMobOverrides)
    {
        builder.add(DTLoots.TOWERGUARDIAN_LOOT, DTEntityTowerGuardian.class);
        
        return Collections.singleton(
                DTEntityTowerGuardian.class
        );
    }
    
    @Override
    public void configureMob(ResourceLocation resource, EntityLivingBase entity, LootTableManager manager, IMobRegistry mobRegistry)
    {
        LootTable loot = manager.getLootTableFromLocation(resource);
        LootDrop[] drops = LootTableHelper.toDrops(loot).toArray(new LootDrop[0]);
        if (entity instanceof DTEntityTowerGuardian)
            mobRegistry.register(entity, LightLevel.any, drops);
    }
    
    @Override
    public void overrideExistingMobDrops(MobEntry mobEntry)
    {
        if (mobEntry.getEntity() instanceof EntityWither || mobEntry.getEntity() instanceof EntityDragon || mobEntry.getEntity() instanceof EntityGuardian)
        {
            if (DTConfigHandler.hearts != 0)
            {
                mobEntry.addDrops(new LootDrop(DTItems.HEART_JAR, 0, 2));
                mobEntry.addDrops(new LootDrop(DTItems.HEART_GOLDEN, 0, 1));
            }
            
            if (mobEntry.getEntity() instanceof EntityWither)
            {
                float chance = DTConfigHandler.boneCharms / 100f;
                float charmChance = chance * 2 / 10f;
                
                mobEntry.addDrop(new LootDrop(DTItems.PHYLACTERY, chance));
                mobEntry.addDrop(new LootDrop(DTItems.CHARM_BARREN, charmChance));
                mobEntry.addDrop(new LootDrop(DTItems.CHARM_BLEAK, charmChance));
                mobEntry.addDrop(new LootDrop(DTItems.CHARM_DARKENED, charmChance));
                mobEntry.addDrop(new LootDrop(DTItems.CHARM_EMACIATED, charmChance));
                mobEntry.addDrop(new LootDrop(DTItems.CHARM_FAMINE, charmChance));
                mobEntry.addDrop(new LootDrop(DTItems.CHARM_HEAVY, charmChance));
                mobEntry.addDrop(new LootDrop(DTItems.CHARM_SAPPING, charmChance));
                mobEntry.addDrop(new LootDrop(DTItems.CHARM_SEARING, charmChance));
                mobEntry.addDrop(new LootDrop(DTItems.CHARM_TOXIC, charmChance));
                mobEntry.addDrop(new LootDrop(DTItems.CHARM_UNINTELLIGIBLE, charmChance));
            }
        }
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
