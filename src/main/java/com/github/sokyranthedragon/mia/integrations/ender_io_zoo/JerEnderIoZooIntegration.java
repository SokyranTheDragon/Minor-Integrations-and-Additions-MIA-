package com.github.sokyranthedragon.mia.integrations.ender_io_zoo;

import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.integrations.jer.IJerIntegration;
import com.github.sokyranthedragon.mia.integrations.jer.JustEnoughResources;
import crazypants.enderio.zoo.entity.*;
import crazypants.enderio.zoo.spawn.IBiomeFilter;
import crazypants.enderio.zoo.spawn.ISpawnEntry;
import crazypants.enderio.zoo.spawn.MobSpawns;
import crazypants.enderio.zoo.spawn.SpawnConfig;
import jeresources.api.IMobRegistry;
import jeresources.api.conditionals.LightLevel;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.storage.loot.LootTableManager;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityRegistry;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static com.github.sokyranthedragon.mia.integrations.ModIds.ENDER_IO;
import static com.github.sokyranthedragon.mia.integrations.ModIds.ENDER_IO_ZOO;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
class JerEnderIoZooIntegration implements IJerIntegration
{
    private Collection<? extends ISpawnEntry> entries;
    
    @Override
    public void addMobs(JustEnoughResources.CustomMobTableBuilder builder)
    {
        entries = MobSpawns.instance.getEntries();
        if (entries == null || entries.isEmpty())
            entries = SpawnConfig.loadSpawnConfig();
        
        builder.add(ENDER_IO.loadSimple("concussioncreeper"), EntityConcussionCreeper.class);
//        builder.add(ENDER_IO.loadSimple("direslime_1"), EntityDireSlime.class);
        builder.add(ENDER_IO.loadSimple("direwolf"), EntityDireWolf.class);
        builder.add(ENDER_IO.loadSimple("enderminy"), EntityEnderminy.class);
        builder.add(ENDER_IO.loadSimple("epicsquid"), EntityEpicSquid.class);
        builder.add(ENDER_IO.loadSimple("fallenknight"), EntityFallenKnight.class);
        builder.add(ENDER_IO.loadSimple("fallenmount"), EntityFallenMount.class);
        builder.add(ENDER_IO.loadSimple("lovechild"), EntityLoveChild.class);
        builder.add(ENDER_IO.loadSimple("owl"), EntityOwl.class);
        builder.add(ENDER_IO.loadSimple("withercat"), EntityWitherCat.class);
        builder.add(ENDER_IO.loadSimple("witherwitch"), EntityWitherWitch.class);
    }
    
    @Override
    public void configureMob(ResourceLocation resource, EntityLivingBase entity, @Nullable LootTableManager manager, IMobRegistry mobRegistry)
    {
        EntityEntry entry = EntityRegistry.getEntry(entity.getClass());
        Set<Biome> biomes = null;
        
        if (entry != null && entry.getRegistryName() != null)
        {
            ISpawnEntry iSpawnEntry = entries
                .stream()
                .filter(e -> e.getMobName().equals(entry.getRegistryName().toString()))
                .findFirst()
                .orElse(null);
            
            if (iSpawnEntry != null)
            {
                biomes = new HashSet<>();
                for (IBiomeFilter filter : iSpawnEntry.getFilters())
                    biomes.addAll(Arrays.asList(filter.getMatchedBiomes()));
            }
        }
        
        if (biomes == null || biomes.isEmpty())
            mobRegistry.register(entity, LightLevel.hostile, 5, resource);
        else
            mobRegistry.register(entity, LightLevel.any, 5, biomes.stream().map(Biome::getBiomeName).toArray(String[]::new), resource);
    }
    
    @Override
    public ModIds getModId()
    {
        return ENDER_IO_ZOO;
    }
}
