package com.github.exploder1531.mia.integrations.futuremc;

import com.github.exploder1531.mia.Mia;
import com.github.exploder1531.mia.integrations.ModIds;
import com.github.exploder1531.mia.integrations.jer.IJerIntegration;
import com.github.exploder1531.mia.integrations.jer.custom.CustomPlantEntry;
import jeresources.api.IPlantRegistry;
import jeresources.api.drop.PlantDrop;
import jeresources.entry.PlantEntry;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import thedarkcolour.futuremc.init.Init;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.lang.reflect.Field;
import java.util.Collection;

@ParametersAreNonnullByDefault
class JerFutureMcIntegration implements IJerIntegration
{
    @Override
    public void addPlantDrops(IPlantRegistry plantRegistry)
    {
        try
        {
            Field registersField = plantRegistry.getClass().getDeclaredField("registers");
            registersField.setAccessible(true);
            //noinspection unchecked
            Collection<PlantEntry> registers = (Collection<PlantEntry>) registersField.get(plantRegistry);
            
            CustomPlantEntry sweetBerry = new CustomPlantEntry(
                    new ItemStack(Init.SWEET_BERRY),
                    Init.SWEET_BERRY_BUSH.getDefaultState(),
                    new PlantDrop(new ItemStack(Init.SWEET_BERRY), 1, 3));
            sweetBerry.setSoil(Blocks.GRASS.getDefaultState());
            registers.add(sweetBerry);
        } catch (NoSuchFieldException | IllegalAccessException e)
        {
            Mia.LOGGER.error("Could not access IPlantRegistry.registers, plant registration for FutureMC will use fallback code.");
            
            plantRegistry.registerWithSoil(
                    new ItemStack(Init.SWEET_BERRY),
                    Blocks.GRASS.getDefaultState(),
                    new PlantDrop(new ItemStack(Init.SWEET_BERRY), 3, 3));
        }
    }
    
    @Nonnull
    @Override
    public ModIds getModId()
    {
        return ModIds.FUTURE_MC;
    }
}
