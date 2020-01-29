package com.github.sokyranthedragon.mia.integrations.biomesoplenty;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.api.item.BOPItems;
import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.integrations.xu2.IExtraUtilsIntegration;
import com.rwtema.extrautils2.api.machine.MachineSlotItem;
import com.rwtema.extrautils2.machine.EnergyBaseRecipe.EnergyBaseItem;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.item.ItemStack;

import javax.annotation.Nullable;

import static com.rwtema.extrautils2.api.machine.XUMachineGenerators.DEATH_GENERATOR;
import static com.rwtema.extrautils2.api.machine.XUMachineGenerators.PINK_GENERATOR;
import static com.rwtema.extrautils2.utils.datastructures.ItemRef.wrap;

@MethodsReturnNonnullByDefault
class ExtraUtilsBopIntegration implements IExtraUtilsIntegration
{
    @Override
    public void addRecipes(@Nullable MachineSlotItem slimeGeneratorSecondary)
    {
        DEATH_GENERATOR.recipes_registry.addRecipe(new EnergyBaseItem(wrap(BOPItems.fleshchunk), 8000, 20));
        DEATH_GENERATOR.recipes_registry.addRecipe(new EnergyBaseItem(wrap(BOPBlocks.flesh), 8000 * 4, 20));
    
        PINK_GENERATOR.recipes_registry.addRecipe(new EnergyBaseItem(wrap(new ItemStack(BOPItems.gem, 1, 0)), 400, 40));
        PINK_GENERATOR.recipes_registry.addRecipe(new EnergyBaseItem(wrap(new ItemStack(BOPBlocks.gem_block, 1, 0)), 400, 40));
        PINK_GENERATOR.recipes_registry.addRecipe(new EnergyBaseItem(wrap(new ItemStack(BOPBlocks.leaves_2, 1, 9)), 400, 40));
        PINK_GENERATOR.recipes_registry.addRecipe(new EnergyBaseItem(wrap(new ItemStack(BOPBlocks.coral, 1, 0)), 400, 40));
        PINK_GENERATOR.recipes_registry.addRecipe(new EnergyBaseItem(wrap(new ItemStack(BOPBlocks.flower_1, 1, 3)), 400, 40));
    }
    
    @Override
    public ModIds getModId()
    {
        return ModIds.BIOMES_O_PLENTY;
    }
}
