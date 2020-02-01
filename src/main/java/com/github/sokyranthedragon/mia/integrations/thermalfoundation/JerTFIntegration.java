package com.github.sokyranthedragon.mia.integrations.thermalfoundation;

import cofh.thermalfoundation.ThermalFoundation;
import cofh.thermalfoundation.entity.monster.EntityBasalz;
import cofh.thermalfoundation.entity.monster.EntityBlitz;
import cofh.thermalfoundation.entity.monster.EntityBlizz;
import cofh.thermalfoundation.init.TFProps;
import cofh.thermalfoundation.item.ItemMaterial;
import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.integrations.jer.IJerIntegration;
import com.github.sokyranthedragon.mia.integrations.jer.JerLightHelper;
import jeresources.api.IMobRegistry;
import jeresources.api.conditionals.LightLevel;
import jeresources.api.drop.LootDrop;
import jeresources.entry.MobEntry;
import jeresources.util.MobTableBuilder;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.storage.loot.LootTableManager;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.Configuration;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@ParametersAreNonnullByDefault
class JerTFIntegration implements IJerIntegration
{
    @Nonnull
    @Override
    public Set<Class<? extends EntityLivingBase>> addMobs(MobTableBuilder mobTableBuilder, Set<Class<? extends EntityLivingBase>> ignoreMobOverrides)
    {
        mobTableBuilder.add(ModIds.THERMAL_FOUNDATION.loadResource("entities/basalz"), EntityBasalz.class);
        mobTableBuilder.add(ModIds.THERMAL_FOUNDATION.loadResource("entities/blitz"), EntityBlitz.class);
        mobTableBuilder.add(ModIds.THERMAL_FOUNDATION.loadResource("entities/blizz"), EntityBlizz.class);
        
        return Stream.of(EntityBasalz.class, EntityBlitz.class, EntityBlizz.class).collect(Collectors.toSet());
    }
    
    @Override
    public void configureMob(ResourceLocation resource, EntityLivingBase entity, @Nullable LootTableManager manager, IMobRegistry mobRegistry)
    {
        Configuration config = ThermalFoundation.CONFIG.getConfiguration();
        LightLevel lightLevel;
        ConfigCategory category;
        Set<Biome> validBiomes = new HashSet<>();
        
        if (entity instanceof EntityBasalz)
        {
            category = config.getCategory("Mob.Basalz");
            
            validBiomes.addAll(BiomeDictionary.getBiomes(BiomeDictionary.Type.MOUNTAIN));
            validBiomes.addAll(BiomeDictionary.getBiomes(BiomeDictionary.Type.WASTELAND));
        }
        else if (entity instanceof EntityBlitz)
        {
            category = config.getCategory("Mob.Blitz");
            
            validBiomes.addAll(BiomeDictionary.getBiomes(BiomeDictionary.Type.SANDY));
            validBiomes.addAll(BiomeDictionary.getBiomes(BiomeDictionary.Type.SAVANNA));
        }
        else
        {
            category = config.getCategory("Mob.Blizz");
            
            validBiomes.addAll(BiomeDictionary.getBiomes(BiomeDictionary.Type.COLD));
            validBiomes.addAll(BiomeDictionary.getBiomes(BiomeDictionary.Type.SNOWY));
        }
        
        if (category.get("LightLevelRestriction").getBoolean(true))
            lightLevel = JerLightHelper.getLightLevelBelow(category.get("LightLevel").getInt(8));
        else
            lightLevel = LightLevel.any;
        
        validBiomes.removeAll(BiomeDictionary.getBiomes(BiomeDictionary.Type.NETHER));
        validBiomes.removeAll(BiomeDictionary.getBiomes(BiomeDictionary.Type.END));
        
        String[] biomes = validBiomes.stream().map(Biome::getBiomeName).toArray(String[]::new);
        mobRegistry.register(entity, lightLevel, 10, biomes, resource);
    }
    
    @Override
    public void overrideExistingMobDrops(MobEntry mobEntry)
    {
        if (TFProps.dropSulfurFireImmuneMobs && mobEntry.getEntity().isImmuneToFire())
        {
            if (mobEntry.getEntity() instanceof EntitySlime)
                mobEntry.addDrop(new LootDrop(ItemMaterial.dustSulfur, 0, 1, 1f / 22f));
            else
                mobEntry.addDrop(new LootDrop(ItemMaterial.dustSulfur, 0, 1, 1f / 6f));
        }
    }
    
    @Nonnull
    @Override
    public ModIds getModId()
    {
        return ModIds.THERMAL_FOUNDATION;
    }
}
