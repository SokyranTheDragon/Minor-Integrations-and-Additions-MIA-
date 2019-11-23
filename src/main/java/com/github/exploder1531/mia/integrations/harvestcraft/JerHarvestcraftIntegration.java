package com.github.exploder1531.mia.integrations.harvestcraft;

import com.github.exploder1531.mia.integrations.ModIds;
import com.github.exploder1531.mia.integrations.jer.IJerIntegration;
import com.pam.harvestcraft.item.ItemRegistry;
import jeresources.api.drop.LootDrop;
import jeresources.entry.MobEntry;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

class JerHarvestcraftIntegration implements IJerIntegration
{
    @Override
    public void overrideExistingMobDrops(MobEntry mobEntry)
    {
        if (mobEntry.getEntity() instanceof EntitySquid)
        {
            LootDrop squidDrop = new LootDrop(new ItemStack(ItemRegistry.calamarirawItem));
            squidDrop.smeltedItem = new ItemStack(ItemRegistry.calamaricookedItem);
            mobEntry.addDrops(squidDrop);
        }
    }
    
    @Nonnull
    @Override
    public String getModId()
    {
        return ModIds.HARVESTCRAFT;
    }
}
