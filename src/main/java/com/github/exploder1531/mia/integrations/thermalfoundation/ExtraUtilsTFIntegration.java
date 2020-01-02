package com.github.exploder1531.mia.integrations.thermalfoundation;

import cofh.core.util.helpers.ItemHelper;
import cofh.thermalfoundation.block.BlockRockwool;
import cofh.thermalfoundation.block.BlockStorageAlloy;
import cofh.thermalfoundation.init.TFBlocks;
import cofh.thermalfoundation.item.ItemMaterial;
import com.github.exploder1531.mia.integrations.ModIds;
import com.github.exploder1531.mia.integrations.xu2.IExtraUtilsIntegration;
import com.rwtema.extrautils2.api.machine.MachineSlotItem;
import com.rwtema.extrautils2.api.machine.RecipeBuilder;
import com.rwtema.extrautils2.api.machine.XUMachineCrusher;
import com.rwtema.extrautils2.api.machine.XUMachineGenerators;
import com.rwtema.extrautils2.blocks.BlockTerraformer;
import com.rwtema.extrautils2.machine.EnergyBaseRecipe;
import com.rwtema.extrautils2.tile.TileTerraformerClimograph;
import com.rwtema.extrautils2.utils.datastructures.ItemRef;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

class ExtraUtilsTFIntegration implements IExtraUtilsIntegration
{
    @Override
    public void addRecipes(@Nullable MachineSlotItem slimeGeneratorSecondary)
    {
        // Generator recipes
        
        // Lava generator, heated redstone
        FluidStack fluid = FluidRegistry.getFluidStack("pyrotheum", 50);
        ItemStack pyrotheum = ItemStack.EMPTY;
        if (fluid != null)
        {
            pyrotheum = FluidUtil.getFilledBucket(fluid);
            
            XUMachineGenerators.LAVA_GENERATOR.recipes_registry.addRecipe(
                    RecipeBuilder.newbuilder(XUMachineGenerators.LAVA_GENERATOR)
                            .setRFRate(100_000, 40.0F)
                            .setFluidInputFluidStack(XUMachineGenerators.INPUT_FLUID, fluid)
                            .build());

            XUMachineGenerators.REDSTONE_GENERATOR.recipes_registry.addRecipe(
                    RecipeBuilder.newbuilder(XUMachineGenerators.REDSTONE_GENERATOR)
                            .setRFRate(160_000, 160.0F)
                            .setItemInput(XUMachineGenerators.INPUT_ITEM, new ItemStack(Items.REDSTONE))
                            .setFluidInputFluidStack(XUMachineGenerators.INPUT_FLUID, fluid)
                            .build());
        }


        // Ice generator
        XUMachineGenerators.ICE_GENERATOR.recipes_registry.addRecipe(new EnergyBaseRecipe.EnergyBaseItem(ItemRef.wrap(ItemMaterial.rodBlizz), 700_000, 300));
        XUMachineGenerators.ICE_GENERATOR.recipes_registry.addRecipe(new EnergyBaseRecipe.EnergyBaseItem(ItemRef.wrap(ItemMaterial.dustBlizz), 200_000, 300));
        XUMachineGenerators.ICE_GENERATOR.recipes_registry.addRecipe(new EnergyBaseRecipe.EnergyBaseItem(ItemRef.wrap(ItemMaterial.dustCryotheum), 240_000, 300));
        fluid = FluidRegistry.getFluidStack("cryotheum", 1_000);
        ItemStack cryotheum = ItemStack.EMPTY;
        if (fluid != null)
        {
            cryotheum = FluidUtil.getFilledBucket(fluid);
        
            if (!cryotheum.isEmpty())
                XUMachineGenerators.ICE_GENERATOR.recipes_registry.addRecipe(new EnergyBaseRecipe.EnergyBaseItem(ItemRef.wrap(cryotheum), 960_000, 300));
        }
    
        // Ender generator
        XUMachineGenerators.ENDER_GENERATOR.recipes_registry.addRecipe(new EnergyBaseRecipe.EnergyBaseItem(ItemRef.wrap(ItemMaterial.crystalEnder), 64_000, 80));
        XUMachineGenerators.ENDER_GENERATOR.recipes_registry.addRecipe(new EnergyBaseRecipe.EnergyBaseItem(ItemRef.wrap(BlockStorageAlloy.blockEnderium), 2_916_000, 120));
        XUMachineGenerators.ENDER_GENERATOR.recipes_registry.addRecipe(new EnergyBaseRecipe.EnergyBaseItem(ItemRef.wrap(ItemMaterial.dustEnderium), 324_000, 120));
        XUMachineGenerators.ENDER_GENERATOR.recipes_registry.addRecipe(new EnergyBaseRecipe.EnergyBaseItem(ItemRef.wrap(ItemMaterial.ingotEnderium), 324_000, 120));
        XUMachineGenerators.ENDER_GENERATOR.recipes_registry.addRecipe(new EnergyBaseRecipe.EnergyBaseItem(ItemRef.wrap(ItemMaterial.nuggetEnderium), 36_000, 120));
        fluid = FluidRegistry.getFluidStack("ender", 1_000);
        if (fluid != null)
        {
            ItemStack bucket = FluidUtil.getFilledBucket(fluid);
        
            if (!bucket.isEmpty())
                XUMachineGenerators.ENDER_GENERATOR.recipes_registry.addRecipe(new EnergyBaseRecipe.EnergyBaseItem(ItemRef.wrap(bucket), 256_000, 80));
        }
        fluid = FluidRegistry.getFluidStack("enderium", 1_000);
        if (fluid != null)
        {
            ItemStack bucket = FluidUtil.getFilledBucket(fluid);
        
            if (!bucket.isEmpty())
                XUMachineGenerators.ENDER_GENERATOR.recipes_registry.addRecipe(new EnergyBaseRecipe.EnergyBaseItem(ItemRef.wrap(bucket), 2_250_000, 120));
        }
        
        // Pink generator
        XUMachineGenerators.PINK_GENERATOR.recipes_registry.addRecipe(new EnergyBaseRecipe.EnergyBaseItem(ItemRef.wrap(BlockRockwool.rockwoolPink), 400, 40));
        
        
        // Terraformer recipes
        
        // Cooler
        TileTerraformerClimograph.register(BlockTerraformer.Type.COOLER, ItemRef.wrap(ItemMaterial.rodBlizz), 32);
        TileTerraformerClimograph.register(BlockTerraformer.Type.COOLER, ItemRef.wrap(ItemMaterial.dustBlizz), 2);
        TileTerraformerClimograph.register(BlockTerraformer.Type.COOLER, ItemRef.wrap(ItemMaterial.dustCryotheum), 10);
        TileTerraformerClimograph.register(BlockTerraformer.Type.COOLER, ItemRef.wrap(cryotheum), 16);
        
        // Heater
        TileTerraformerClimograph.register(BlockTerraformer.Type.HEATER, ItemRef.wrap(ItemMaterial.dustPyrotheum), 2);
        if (!pyrotheum.isEmpty())
            TileTerraformerClimograph.register(BlockTerraformer.Type.HEATER, ItemRef.wrap(pyrotheum), 8);
        
        
        // Crusher
        XUMachineCrusher.addRecipe(ItemMaterial.rodBlizz, ItemHelper.cloneStack(ItemMaterial.dustBlizz, 2), ItemHelper.cloneStack(ItemMaterial.dustBlizz, 3), 0.4f);
        XUMachineCrusher.addRecipe(ItemMaterial.rodBasalz, ItemHelper.cloneStack(ItemMaterial.dustBasalz, 2), ItemHelper.cloneStack(ItemMaterial.dustBasalz, 3), 0.4f);
        XUMachineCrusher.addRecipe(ItemMaterial.rodBlitz, ItemHelper.cloneStack(ItemMaterial.dustBlitz, 2), ItemHelper.cloneStack(ItemMaterial.dustBlitz, 3), 0.4f);
        
        for (BlockRockwool.Type type : BlockRockwool.VARIANT.getAllowedValues())
            XUMachineCrusher.addRecipe(new ItemStack(TFBlocks.blockRockwool, 1, type.getMetadata()), ItemMaterial.crystalSlag, new ItemStack(Items.DYE, 1, type.getMetadata()), 0.05f);
    }
    
    @Nonnull
    @Override
    public ModIds getModId()
    {
        return ModIds.THERMAL_FOUNDATION;
    }
}
