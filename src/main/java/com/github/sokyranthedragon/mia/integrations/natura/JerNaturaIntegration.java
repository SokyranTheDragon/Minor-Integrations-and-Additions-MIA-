package com.github.sokyranthedragon.mia.integrations.natura;

import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.integrations.jer.IJerIntegration;
import com.github.sokyranthedragon.mia.integrations.jer.JustEnoughResources;
import com.github.sokyranthedragon.mia.integrations.jer.custom.CustomPlantEntry;
import com.github.sokyranthedragon.mia.utilities.LootTableUtils;
import com.progwml6.natura.common.config.Config;
import com.progwml6.natura.entities.NaturaEntities;
import com.progwml6.natura.entities.entity.monster.EntityHeatscarSpider;
import com.progwml6.natura.entities.entity.monster.EntityNitroCreeper;
import com.progwml6.natura.entities.entity.passive.EntityImp;
import com.progwml6.natura.nether.NaturaNether;
import com.progwml6.natura.overworld.NaturaOverworld;
import com.progwml6.natura.shared.NaturaCommons;
import jeresources.api.IMobRegistry;
import jeresources.api.IPlantRegistry;
import jeresources.api.conditionals.LightLevel;
import jeresources.api.drop.PlantDrop;
import jeresources.entry.PlantEntry;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.storage.loot.LootTableManager;
import net.minecraftforge.common.BiomeDictionary;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.*;

import static com.progwml6.natura.Natura.pulseManager;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
class JerNaturaIntegration implements IJerIntegration
{
    @Override
    public void addMobs(JustEnoughResources.CustomMobTableBuilder builder)
    {
        if (pulseManager.isPulseLoaded(NaturaEntities.PulseId))
        {
            builder.add(EntityImp.LOOT_TABLE, EntityImp.class);
            builder.add(LootTableUtils.loadUniqueEmptyLootTable(), EntityNitroCreeper.class);
            
            if (Config.enableHeatscarSpider)
                builder.add(EntityHeatscarSpider.LOOT_TABLE, EntityHeatscarSpider.class);
        }
    }
    
    @Override
    public void configureMob(ResourceLocation resource, EntityLivingBase entity, @Nullable LootTableManager manager, IMobRegistry mobRegistry)
    {
        Set<Biome> validBiomes = new HashSet<>(BiomeDictionary.getBiomes(BiomeDictionary.Type.NETHER));
        int experienceMin = 5;
        int experienceMax = 5;
        
        if (entity instanceof EntityImp)
        {
            experienceMin = 1;
            experienceMax = 3;
        }
//        else if (entity instanceof EntityNitroCreeper)
//        {
//            // TODO: Use reflection for this to check if it exists
//            entity.getDropItem();
//        }
        
        mobRegistry.register(entity, LightLevel.any, experienceMin, experienceMax, validBiomes.stream().map(Biome::getBiomeName).toArray(String[]::new), resource);
    }
    
    @Override
    public void addPlantDrops(IPlantRegistry plantRegistry, @Nullable Collection<PlantEntry> registers)
    {
        if (!pulseManager.isPulseLoaded(NaturaCommons.PulseId))
            return;
        
        if (registers != null)
        {
            List<CustomPlantEntry> entries = new LinkedList<>();
            
            if (pulseManager.isPulseLoaded(NaturaOverworld.PulseId))
            {
                registers.add(new CustomPlantEntry(NaturaOverworld.barley_seeds, NaturaOverworld.barleyCrop.getDefaultState(),
                    new PlantDrop(NaturaCommons.barley, 1, 1),
                    new PlantDrop(NaturaOverworld.barley_seeds, 0, 3)));
                registers.add(new CustomPlantEntry(NaturaOverworld.cotton_seeds, NaturaOverworld.cottonCrop.getDefaultState(),
                    new PlantDrop(NaturaCommons.cotton, 1, 1),
                    new PlantDrop(NaturaOverworld.cotton_seeds, 0, 3)));
                
                entries.add(new CustomPlantEntry(new ItemStack(NaturaOverworld.overworldBerryBushRaspberry), NaturaOverworld.overworldBerryBushRaspberry.getDefaultState(),
                    new PlantDrop(NaturaCommons.raspberry, 1, 1)));
                entries.add(new CustomPlantEntry(new ItemStack(NaturaOverworld.overworldBerryBushBlueberry), NaturaOverworld.overworldBerryBushBlueberry.getDefaultState(),
                    new PlantDrop(NaturaCommons.blueberry, 1, 1)));
                entries.add(new CustomPlantEntry(new ItemStack(NaturaOverworld.overworldBerryBushBlackberry), NaturaOverworld.overworldBerryBushBlackberry.getDefaultState(),
                    new PlantDrop(NaturaCommons.blackberry, 1, 1)));
                entries.add(new CustomPlantEntry(new ItemStack(NaturaOverworld.overworldBerryBushMaloberry), NaturaOverworld.overworldBerryBushMaloberry.getDefaultState(),
                    new PlantDrop(NaturaCommons.maloberry, 1, 1)));
            }
            if (pulseManager.isPulseLoaded(NaturaNether.PulseId))
            {
                entries.add(new CustomPlantEntry(new ItemStack(NaturaNether.netherBerryBushBlightberry), NaturaNether.netherBerryBushBlightberry.getDefaultState(),
                    new PlantDrop(NaturaCommons.blightberry, 1, 1)));
                entries.add(new CustomPlantEntry(new ItemStack(NaturaNether.netherBerryBushDuskberry), NaturaNether.netherBerryBushDuskberry.getDefaultState(),
                    new PlantDrop(NaturaCommons.duskberry, 1, 1)));
                entries.add(new CustomPlantEntry(new ItemStack(NaturaNether.netherBerryBushSkyberry), NaturaNether.netherBerryBushSkyberry.getDefaultState(),
                    new PlantDrop(NaturaCommons.skyberry, 1, 1)));
                entries.add(new CustomPlantEntry(new ItemStack(NaturaNether.netherBerryBushStingberry), NaturaNether.netherBerryBushStingberry.getDefaultState(),
                    new PlantDrop(NaturaCommons.stingberry, 1, 1)));
            }
            
            for (CustomPlantEntry entry : entries)
            {
                entry.setSoil(Blocks.GRASS.getDefaultState());
                registers.add(entry);
            }
        }
        else
        {
            if (pulseManager.isPulseLoaded(NaturaOverworld.PulseId))
            {
                plantRegistry.register(NaturaOverworld.barley_seeds,
                    NaturaOverworld.barleyCrop,
                    new PlantDrop(NaturaCommons.barley, 1, 1),
                    new PlantDrop(NaturaOverworld.barley_seeds, 0, 3));
                plantRegistry.register(NaturaOverworld.cotton_seeds,
                    NaturaOverworld.cottonCrop,
                    new PlantDrop(NaturaCommons.cotton, 1, 1),
                    new PlantDrop(NaturaOverworld.cotton_seeds, 0, 3));
                
                plantRegistry.register(new ItemStack(NaturaOverworld.overworldBerryBushRaspberry),
                    new PlantDrop(NaturaCommons.raspberry, 1, 1));
                plantRegistry.register(new ItemStack(NaturaOverworld.overworldBerryBushBlueberry),
                    new PlantDrop(NaturaCommons.blueberry, 1, 1));
                plantRegistry.register(new ItemStack(NaturaOverworld.overworldBerryBushBlackberry),
                    new PlantDrop(NaturaCommons.blackberry, 1, 1));
                plantRegistry.register(new ItemStack(NaturaOverworld.overworldBerryBushMaloberry),
                    new PlantDrop(NaturaCommons.maloberry, 1, 1));
            }
            if (pulseManager.isPulseLoaded(NaturaNether.PulseId))
            {
                plantRegistry.register(new ItemStack(NaturaNether.netherBerryBushBlightberry),
                    new PlantDrop(NaturaCommons.blightberry, 1, 1));
                plantRegistry.register(new ItemStack(NaturaNether.netherBerryBushDuskberry),
                    new PlantDrop(NaturaCommons.duskberry, 1, 1));
                plantRegistry.register(new ItemStack(NaturaNether.netherBerryBushSkyberry),
                    new PlantDrop(NaturaCommons.skyberry, 1, 1));
                plantRegistry.register(new ItemStack(NaturaNether.netherBerryBushStingberry),
                    new PlantDrop(NaturaCommons.stingberry, 1, 1));
            }
        }
    }
    
    @Override
    public ModIds getModId()
    {
        return ModIds.NATURA;
    }
}
