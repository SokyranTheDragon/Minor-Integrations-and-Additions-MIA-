package com.github.sokyranthedragon.mia.integrations.ender_io;

import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.integrations.jer.IJerIntegration;
import crazypants.enderio.base.capacitor.CapacitorKey;
import crazypants.enderio.base.init.ModObject;
import jeresources.api.conditionals.Conditional;
import jeresources.api.drop.LootDrop;
import jeresources.entry.MobEntry;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.item.ItemStack;

import javax.annotation.ParametersAreNonnullByDefault;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
class JerEnderIoIntegration implements IJerIntegration
{
    @Override
    public void overrideExistingMobDrops(MobEntry mobEntry)
    {
        if (mobEntry.getEntity() instanceof  EntityEnderman && ModObject.blockEndermanSkull.getBlock() != null)
        {
            int baseValue = CapacitorKey.HEAD_VANILLA_CHANCE_ENDERMAN.getBaseValue();
            int lootingValue = CapacitorKey.HEAD_VANILLA_CHANCE_ENDERMAN.get(5);
            
            if (baseValue > 0 || lootingValue > baseValue)
                mobEntry.addDrop(new LootDrop(new ItemStack(ModObject.blockEndermanSkull.getBlock()), 0, 1, Conditional.affectedByLooting));
        }
    }
    
    @Override
    public ModIds getModId()
    {
        return ModIds.ENDER_IO;
    }
}
