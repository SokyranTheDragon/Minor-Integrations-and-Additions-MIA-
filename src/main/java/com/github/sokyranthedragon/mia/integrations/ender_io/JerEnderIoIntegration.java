package com.github.sokyranthedragon.mia.integrations.ender_io;

import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.integrations.jer.IJerIntegration;
import crazypants.enderio.base.config.config.BlockConfig;
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
        if (mobEntry.getEntity() instanceof EntityEnderman && ModObject.blockEndermanSkull.getBlock() != null && BlockConfig.vanillaSwordSkullChance.get() > 0)
            mobEntry.addDrop(new LootDrop(new ItemStack(ModObject.blockEndermanSkull.getBlock()), 0, 1, BlockConfig.vanillaSwordSkullLootingModifier.get(), Conditional.affectedByLooting));
    }
    
    @Override
    public ModIds getModId()
    {
        return ModIds.ENDER_IO;
    }
}
