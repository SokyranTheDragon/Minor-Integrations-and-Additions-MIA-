package com.github.sokyranthedragon.mia.integrations.harvestcraft;

import com.github.sokyranthedragon.mia.Mia;
import com.github.sokyranthedragon.mia.config.HarvestcraftConfiguration;
import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.integrations.jer.IJerIntegration;
import com.github.sokyranthedragon.mia.integrations.jer.custom.CustomPlantEntry;
import com.pam.harvestcraft.blocks.CropRegistry;
import com.pam.harvestcraft.blocks.FruitRegistry;
import com.pam.harvestcraft.blocks.growables.BlockPamCrop;
import com.pam.harvestcraft.blocks.growables.BlockPamFruit;
import com.pam.harvestcraft.blocks.growables.BlockPamFruitLog;
import com.pam.harvestcraft.item.ItemRegistry;
import jeresources.api.IPlantRegistry;
import jeresources.api.drop.LootDrop;
import jeresources.api.drop.PlantDrop;
import jeresources.entry.MobEntry;
import jeresources.entry.PlantEntry;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.IPlantable;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.lang.reflect.Field;
import java.util.Collection;

@ParametersAreNonnullByDefault
class JerHarvestcraftIntegration implements IJerIntegration
{
    @Override
    public void overrideExistingMobDrops(MobEntry mobEntry)
    {
        if (HarvestcraftConfiguration.squidDropsCalamari && mobEntry.getEntity() instanceof EntitySquid)
        {
            LootDrop squidDrop = new LootDrop(new ItemStack(ItemRegistry.calamarirawItem));
            squidDrop.smeltedItem = new ItemStack(ItemRegistry.calamaricookedItem);
            mobEntry.addDrops(squidDrop);
        }
    }
    
    @Override
    public void addPlantDrops(IPlantRegistry plantRegistry)
    {
        if (!HarvestcraftConfiguration.enableJerIntegration)
            return;
        
        for (BlockPamCrop crop : CropRegistry.getCrops().values())
        {
            Item seeds = crop.func_149866_i();
            Item food = crop.func_149865_P();
            
            PlantDrop[] entries;
            
            if (seeds == food)
                entries = new PlantDrop[]{ new PlantDrop(new ItemStack(seeds), 1, 4) };
            else
                entries = new PlantDrop[]{
                        new PlantDrop(new ItemStack(seeds), 1, 1),
                        new PlantDrop(new ItemStack(food), 0, 2) };
            if (seeds instanceof IPlantable)
                plantRegistry.register((Item & IPlantable) seeds, entries);
            else
                plantRegistry.register(new ItemStack(seeds), entries);
        }
        
        try
        {
            Field registersField = plantRegistry.getClass().getDeclaredField("registers");
            registersField.setAccessible(true);
            //noinspection unchecked
            Collection<PlantEntry> registers = (Collection<PlantEntry>) registersField.get(plantRegistry);
            
            for (BlockPamFruit fruit : FruitRegistry.fruits)
            {
                CustomPlantEntry entry = new CustomPlantEntry(
                        new ItemStack(fruit.getSapling()),
                        fruit.getDefaultState(),
                        new PlantDrop(new ItemStack(fruit.getFruitItem()), 1, 1));
                entry.setSoil(Blocks.AIR.getDefaultState());
                registers.add(entry);
            }
            
            for (BlockPamFruitLog log : FruitRegistry.logs.values())
            {
                CustomPlantEntry entry = new CustomPlantEntry(
                        new ItemStack(log.getSapling()),
                        log.getDefaultState(),
                        new PlantDrop(new ItemStack(log.getFruitItem()), 1, 1));
                entry.setSoil(log.getDefaultState());
                registers.add(entry);
            }
        } catch (NoSuchFieldException | IllegalAccessException e)
        {
            Mia.LOGGER.error("Could not access IPlantRegistry.registers, plant registration for Harvestcraft will use fallback code.");
            
            for (BlockPamFruit fruit : FruitRegistry.fruits)
            {
                Item sapling = Item.getItemFromBlock(fruit.getSapling());
                PlantDrop entry = new PlantDrop(new ItemStack(fruit.getFruitItem()), 1, 1);
                
                if (sapling instanceof IPlantable)
                    plantRegistry.registerWithSoil((Item & IPlantable) sapling, Blocks.DIRT.getDefaultState(), entry);
                else
                    plantRegistry.registerWithSoil(new ItemStack(fruit.getSapling()), Blocks.DIRT.getDefaultState(), entry);
            }
            
            for (BlockPamFruitLog log : FruitRegistry.logs.values())
            {
                Item sapling = Item.getItemFromBlock(log.getSapling());
                PlantDrop entry = new PlantDrop(new ItemStack(log.getFruitItem()), 1, 1);
                
                if (sapling instanceof IPlantable)
                    plantRegistry.registerWithSoil((Item & IPlantable) sapling, Blocks.DIRT.getDefaultState(), entry);
                else
                    plantRegistry.registerWithSoil(new ItemStack(sapling), Blocks.DIRT.getDefaultState(), entry);
            }
        }
    }
    
    @Nonnull
    @Override
    public ModIds getModId()
    {
        return ModIds.HARVESTCRAFT;
    }
}
