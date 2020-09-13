package com.github.sokyranthedragon.mia.integrations.extrabotany;

import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.integrations.jer.IJerIntegration;
import com.github.sokyranthedragon.mia.integrations.jer.JustEnoughResources;
import com.meteor.extrabotany.common.entity.gaia.EntityVoidHerrscher;
import jeresources.api.IMobRegistry;
import jeresources.api.conditionals.LightLevel;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTableManager;
import vazkii.botania.common.entity.EntityDoppleganger;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
class JerExtraBotanyIntegration implements IJerIntegration
{
    @Override
    public void addMobs(JustEnoughResources.CustomMobTableBuilder builder)
    {
        builder.addNoConfigure(ModIds.EXTRABOTANY.loadResource("gaia_guardian_3", 0), EntityDoppleganger.class, entity -> entity.setCustomNameTag(I18n.format("entity.botania:doppleganger.name") + " III"));
        builder.add(ModIds.EXTRABOTANY.loadResource("gaia_guardian_3", 1), EntityVoidHerrscher.class);
    }
    
    @Override
    public void configureMob(ResourceLocation resource, EntityLivingBase entity, @Nullable LootTableManager manager, IMobRegistry mobRegistry)
    {
        mobRegistry.register(entity, LightLevel.any, 1725, resource);
    }
    
    @Override
    public ModIds getModId()
    {
        return ModIds.EXTRABOTANY;
    }
}
