package com.github.sokyranthedragon.mia.integrations.futuremc;

import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.integrations.jer.IJerIntegration;
import com.github.sokyranthedragon.mia.integrations.jer.custom.CustomPlantEntry;
import jeresources.api.IPlantRegistry;
import jeresources.api.drop.PlantDrop;
import jeresources.entry.PlantEntry;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import thedarkcolour.futuremc.registry.FBlocks;
import thedarkcolour.futuremc.registry.FItems;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Collection;

@ParametersAreNonnullByDefault
class JerFutureMcIntegration implements IJerIntegration
{
    @Override
    public void addPlantDrops(IPlantRegistry plantRegistry, @Nullable Collection<PlantEntry> registers)
    {
        if (registers != null)
        {
            CustomPlantEntry sweetBerry = new CustomPlantEntry(
                new ItemStack(FItems.INSTANCE.getSWEET_BERRIES()),
                FBlocks.INSTANCE.getSWEET_BERRY_BUSH().getDefaultState(),
                new PlantDrop(new ItemStack(FItems.INSTANCE.getSWEET_BERRIES()), 1, 3));
            sweetBerry.setSoil(Blocks.GRASS.getDefaultState());
            registers.add(sweetBerry);
        }
        else
        {
            plantRegistry.registerWithSoil(
                new ItemStack(FItems.INSTANCE.getSWEET_BERRIES()),
                Blocks.GRASS.getDefaultState(),
                new PlantDrop(new ItemStack(FItems.INSTANCE.getSWEET_BERRIES()), 3, 3));
        }
    }
    
    @Nonnull
    @Override
    public ModIds getModId()
    {
        return ModIds.FUTURE_MC;
    }
}
