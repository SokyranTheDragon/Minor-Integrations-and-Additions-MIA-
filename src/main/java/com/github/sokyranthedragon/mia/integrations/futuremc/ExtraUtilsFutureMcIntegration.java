package com.github.sokyranthedragon.mia.integrations.futuremc;

import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.integrations.xu2.IExtraUtilsIntegration;
import com.rwtema.extrautils2.api.machine.MachineSlotItem;
import com.rwtema.extrautils2.api.machine.XUMachineCrusher;
import com.rwtema.extrautils2.blocks.BlockTerraformer;
import com.rwtema.extrautils2.tile.TileTerraformerClimograph;
import com.rwtema.extrautils2.utils.datastructures.ItemRef;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import thedarkcolour.futuremc.registry.FBlocks;
import thedarkcolour.futuremc.registry.FItems;

import javax.annotation.Nullable;

class ExtraUtilsFutureMcIntegration implements IExtraUtilsIntegration
{
    @Override
    public void addRecipes(@Nullable MachineSlotItem slimeGeneratorSecondary)
    {
        // Crusher
        XUMachineCrusher.addRecipe(new ItemStack(FBlocks.INSTANCE.getANCIENT_DEBRIS()), new ItemStack(FItems.INSTANCE.getNETHERITE_SCRAP(), 2));
        
        XUMachineCrusher.addRecipe(new ItemStack(FBlocks.LILY_OF_THE_VALLEY), new ItemStack(FItems.INSTANCE.getDYES(), 2, 0));
        XUMachineCrusher.addRecipe(new ItemStack(FBlocks.CORNFLOWER), new ItemStack(FItems.INSTANCE.getDYES(), 2, 1));
        XUMachineCrusher.addRecipe(new ItemStack(FBlocks.WITHER_ROSE), new ItemStack(FItems.INSTANCE.getDYES(), 2, 3));
        
        // Cooler
        TileTerraformerClimograph.register(BlockTerraformer.Type.COOLER, ItemRef.wrap(new ItemStack(FBlocks.INSTANCE.getBLUE_ICE())), 16);
    }
    
    @NotNull
    @Override
    public ModIds getModId()
    {
        return ModIds.FUTURE_MC;
    }
}
