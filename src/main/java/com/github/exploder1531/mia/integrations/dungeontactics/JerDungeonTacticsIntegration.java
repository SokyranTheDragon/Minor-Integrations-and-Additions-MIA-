package com.github.exploder1531.mia.integrations.dungeontactics;

import com.github.exploder1531.mia.integrations.ModIds;
import com.github.exploder1531.mia.integrations.jer.IJerIntegration;
import com.github.exploder1531.mia.integrations.jer.JerOverrideHelper;
import jeresources.api.drop.LootDrop;
import jeresources.entry.MobEntry;
import net.minecraft.entity.monster.EntityEvoker;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.item.ItemStack;
import pegbeard.dungeontactics.handlers.DTItems;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.List;

class JerDungeonTacticsIntegration implements IJerIntegration
{
    @Override
    public void overrideExistingMobDrops(MobEntry mobEntry)
    {
        if (mobEntry.getEntity() instanceof EntityWitch || mobEntry.getEntity() instanceof EntityEvoker)
        {
            List<ItemStack> injected = Arrays.asList(new ItemStack(DTItems.MAGIC_POWDER), new ItemStack(DTItems.MAGIC_SCROLL));
            JerOverrideHelper.removeDuplicateEntries(injected, mobEntry.getDropsItemStacks());
            
            for (ItemStack item : injected)
            {
                mobEntry.addDrops(new LootDrop(item));
            }
        }
    }
    
    @Nonnull
    @Override
    public String getModId()
    {
        return ModIds.DUNGEON_TACTICS;
    }
}
