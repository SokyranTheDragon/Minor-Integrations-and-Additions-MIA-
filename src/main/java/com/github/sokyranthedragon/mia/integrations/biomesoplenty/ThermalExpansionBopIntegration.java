package com.github.sokyranthedragon.mia.integrations.biomesoplenty;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.api.item.BOPItems;
import cofh.thermalexpansion.util.managers.device.FactorizerManager;
import cofh.thermalexpansion.util.managers.device.TapperManager;
import cofh.thermalexpansion.util.managers.machine.PrecipitatorManager;
import cofh.thermalfoundation.init.TFFluids;
import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.integrations.thermalexpansion.IThermalExpansionIntegration;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

@MethodsReturnNonnullByDefault
class ThermalExpansionBopIntegration implements IThermalExpansionIntegration
{
    @Override
    public void addRecipes()
    {
        // Factorizer adds ash <-> coal recipe by default, so we'll remove it
//        FactorizerManager.removeRecipe(new ItemStack(BOPItems.ash), false);
//        if (FactorizerManager.getRecipe(new ItemStack(Items.COAL), true).getOutput().getItem() == BOPItems.ash)
//            FactorizerManager.removeRecipe(new ItemStack(Items.COAL), true);
        FactorizerManager.addDefaultRecipe(new ItemStack(BOPItems.ash), new ItemStack(BOPBlocks.ash_block), 4);
        FactorizerManager.addDefaultRecipe(new ItemStack(BOPItems.mudball), new ItemStack(BOPBlocks.mud), 4);
        FactorizerManager.addDefaultRecipe(new ItemStack(BOPItems.fleshchunk), new ItemStack(BOPBlocks.flesh), 4);
        FactorizerManager.addDefaultRecipe(new ItemStack(BOPItems.crystal_shard), new ItemStack(BOPBlocks.crystal), 4);
        FactorizerManager.addDefaultRecipe(new ItemStack(BOPItems.honeycomb), new ItemStack(BOPBlocks.hive, 1, 1), 4);
        FactorizerManager.addDefaultRecipe(new ItemStack(BOPItems.filled_honeycomb), new ItemStack(BOPBlocks.hive, 1, 3), 4);
        FactorizerManager.addDefaultRecipe(new ItemStack(BOPBlocks.bamboo), new ItemStack(BOPBlocks.bamboo_thatching), 4);
        
        PrecipitatorManager.addRecipe(1600, new ItemStack(BOPBlocks.hard_ice), new FluidStack(FluidRegistry.WATER, 2000));
        
        TapperManager.addStandardMapping(new ItemStack(BOPBlocks.log_0, 1, 4), new FluidStack(TFFluids.fluidExperience, 25));
        TapperManager.addStandardMapping(new ItemStack(BOPBlocks.log_1, 1, 4), new FluidStack(TFFluids.fluidExperience, 25));
        TapperManager.addStandardMapping(new ItemStack(BOPBlocks.log_1, 1, 7), new FluidStack(TFFluids.fluidResin, 50));
        TapperManager.addStandardMapping(new ItemStack(BOPBlocks.log_2, 1, 4), new FluidStack(TFFluids.fluidResin, 50));
        TapperManager.addStandardMapping(new ItemStack(BOPBlocks.log_2, 1, 7), new FluidStack(FluidRegistry.LAVA, 25));
        TapperManager.addStandardMapping(new ItemStack(BOPBlocks.log_1, 1, 7), new FluidStack(TFFluids.fluidResin, 50));
        TapperManager.addStandardMapping(new ItemStack(BOPBlocks.log_1, 1, 7), new FluidStack(TFFluids.fluidResin, 50));
    }
    
    @Override
    public void addPostInitRecipes()
    {
        // It gets replaced by resin
        TapperManager.addStandardMapping(new ItemStack(BOPBlocks.log_1, 1, 5), new FluidStack(TFFluids.fluidExperience, 25));
    }
    
    @Override
    public ModIds getModId()
    {
        return ModIds.BIOMES_O_PLENTY;
    }
}
