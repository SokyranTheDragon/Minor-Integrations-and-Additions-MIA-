package com.github.exploder1531.mia.integrations.xu2;

import com.github.exploder1531.mia.integrations.ModIds;
import com.github.exploder1531.mia.integrations.jer.IJerIntegration;
import com.rwtema.extrautils2.backend.entries.XU2Entries;
import jeresources.api.IPlantRegistry;
import jeresources.api.drop.PlantDrop;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
class JerExtraUtilsIntegration implements IJerIntegration
{
    @Override
    public void addPlantDrops(IPlantRegistry plantRegistry)
    {
        plantRegistry.registerWithSoil(
                XU2Entries.blockRedOrchid.newStack(),
                Blocks.REDSTONE_ORE.getDefaultState(),
                new PlantDrop(XU2Entries.blockRedOrchid.newStack(), 1, 1),
                new PlantDrop(new ItemStack(Items.REDSTONE), 1, 3));
//                new PlantDrop(new ItemStack(Items.REDSTONE), 0.0333333f),
//                new PlantDrop(new ItemStack(Items.REDSTONE, 2), 0.0666666f)

        plantRegistry.registerWithSoil(
                XU2Entries.blockEnderLilly.newStack(),
                Blocks.END_STONE.getDefaultState(),
                new PlantDrop(XU2Entries.blockEnderLilly.newStack(), 1, 1),
                new PlantDrop(new ItemStack(Items.ENDER_PEARL), 1, 2));
//                new PlantDrop(new ItemStack(Items.ENDER_PEARL), 0.1f)
    }
    
    @Nonnull
    @Override
    public String getModId()
    {
        return ModIds.EXTRA_UTILITIES;
    }
}
