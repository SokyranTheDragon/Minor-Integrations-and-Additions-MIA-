package com.github.sokyranthedragon.mia.integrations.xu2;

import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.integrations.jer.IJerIntegration;
import com.github.sokyranthedragon.mia.integrations.jer.custom.CustomPlantEntry;
import com.rwtema.extrautils2.backend.entries.XU2Entries;
import com.rwtema.extrautils2.blocks.BlockEnderLilly;
import com.rwtema.extrautils2.blocks.BlockRedOrchid;
import jeresources.api.IPlantRegistry;
import jeresources.api.drop.PlantDrop;
import jeresources.entry.PlantEntry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Collection;

@ParametersAreNonnullByDefault
class JerExtraUtilsIntegration implements IJerIntegration
{
    @Override
    public void addPlantDrops(IPlantRegistry plantRegistry, @Nullable Collection<PlantEntry> registers)
    {
        if (registers != null)
        {
            CustomPlantEntry redOrchid = new CustomPlantEntry(
                    XU2Entries.blockRedOrchid.newStack(),
                    XU2Entries.blockRedOrchid.value,
                    BlockRedOrchid.GROWTH_STATE,
                    new PlantDrop(XU2Entries.blockRedOrchid.newStack(), 1, 1),
                    new PlantDrop(new ItemStack(Items.REDSTONE), 1, 3));
    
            redOrchid.setSoil(Blocks.REDSTONE_ORE.getDefaultState());
            registers.add(redOrchid);
    
            CustomPlantEntry enderLilly = new CustomPlantEntry(
                    XU2Entries.blockEnderLilly.newStack(),
                    XU2Entries.blockEnderLilly.value,
                    BlockEnderLilly.GROWTH_STATE,
                    new PlantDrop(XU2Entries.blockEnderLilly.newStack(), 1, 1),
                    new PlantDrop(new ItemStack(Items.ENDER_PEARL), 1, 2));
    
            enderLilly.setSoil(Blocks.END_STONE.getDefaultState());
            registers.add(enderLilly);
        }
    }
    
    @Nonnull
    @Override
    public ModIds getModId()
    {
        return ModIds.EXTRA_UTILITIES;
    }
}
