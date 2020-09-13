package com.github.sokyranthedragon.mia.integrations.botania;

import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.integrations.jer.IJerIntegration;
import com.github.sokyranthedragon.mia.integrations.jer.JustEnoughResources;
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

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
class JerBotaniaIntegration implements IJerIntegration
{
    @Override
    public void addMobs(JustEnoughResources.CustomMobTableBuilder builder)
    {
        builder.add(ModIds.BOTANIA.loadResource("gaia_guardian"), EntityDoppleganger.class, entity -> entity.setCustomNameTag(I18n.format("entity.botania:doppleganger.name") + " I"));
        builder.add(ModIds.BOTANIA.loadResource("gaia_guardian_2"), EntityDoppleganger.class, entity -> entity.setCustomNameTag(I18n.format("entity.botania:doppleganger.name") + " II"));
    }
    
    @Override
    public void configureMob(ResourceLocation resource, EntityLivingBase entity, @Nullable LootTableManager manager, IMobRegistry mobRegistry)
    {
        // We're using the same entity for ExtraBotany's Guardian of Gaia III, so here we're checking if it's the one
        int experience = entity.getCustomNameTag().endsWith(" III") ? 1225 : 825;
        mobRegistry.register(entity, LightLevel.any, experience, resource);
    }
    
    @Override
    public ModIds getModId()
    {
        return ModIds.BOTANIA;
    }
}
