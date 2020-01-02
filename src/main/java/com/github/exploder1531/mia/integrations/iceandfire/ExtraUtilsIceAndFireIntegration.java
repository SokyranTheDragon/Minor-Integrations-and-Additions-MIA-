package com.github.exploder1531.mia.integrations.iceandfire;

import com.github.alexthe666.iceandfire.core.ModBlocks;
import com.github.alexthe666.iceandfire.core.ModItems;
import com.github.alexthe666.iceandfire.enums.EnumSkullType;
import com.github.exploder1531.mia.integrations.ModIds;
import com.github.exploder1531.mia.integrations.xu2.IExtraUtilsIntegration;
import com.rwtema.extrautils2.api.machine.MachineSlotItem;
import com.rwtema.extrautils2.api.machine.XUMachineCrusher;
import com.rwtema.extrautils2.api.machine.XUMachineGenerators;
import com.rwtema.extrautils2.blocks.BlockTerraformer;
import com.rwtema.extrautils2.machine.EnergyBaseRecipe;
import com.rwtema.extrautils2.tile.TileTerraformerClimograph;
import com.rwtema.extrautils2.utils.datastructures.ItemRef;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

class ExtraUtilsIceAndFireIntegration implements IExtraUtilsIntegration
{
    
    @Override
    public void addRecipes(@Nullable MachineSlotItem slimeGeneratorSecondary)
    {
        // Generator recipes
        
        // Death generator
        XUMachineGenerators.DEATH_GENERATOR.recipes_registry.addRecipe(new EnergyBaseRecipe.EnergyBaseItem(ItemRef.wrap(ModItems.dragonbone), 64_000, 60));
        XUMachineGenerators.DEATH_GENERATOR.recipes_registry.addRecipe(new EnergyBaseRecipe.EnergyBaseItem(ItemRef.wrap(ModBlocks.dragon_bone_block_wall), 64_000 * 6, 120));
        XUMachineGenerators.DEATH_GENERATOR.recipes_registry.addRecipe(new EnergyBaseRecipe.EnergyBaseItem(ItemRef.wrap(ModBlocks.dragon_bone_block), 64_000 * 9, 180));
        XUMachineGenerators.DEATH_GENERATOR.recipes_registry.addRecipe(new EnergyBaseRecipe.EnergyBaseItem(ItemRef.wrap(ModItems.troll_tusk), 27_000, 40));
        XUMachineGenerators.DEATH_GENERATOR.recipes_registry.addRecipe(new EnergyBaseRecipe.EnergyBaseItem(ItemRef.wrap(ModItems.wither_shard), 10_333, 80));
        // Skulls
        for (EnumSkullType skull : EnumSkullType.values())
        {
            XUMachineGenerators.DEATH_GENERATOR.recipes_registry.addRecipe(new EnergyBaseRecipe.EnergyBaseItem(ItemRef.wrap(skull.skull_item), 120_000, 200));
        }
        
        // Ice generator
        XUMachineGenerators.ICE_GENERATOR.recipes_registry.addRecipe(new EnergyBaseRecipe.EnergyBaseItem(ItemRef.wrap(ModBlocks.frozenDirt), 1_600, 40));
        XUMachineGenerators.ICE_GENERATOR.recipes_registry.addRecipe(new EnergyBaseRecipe.EnergyBaseItem(ItemRef.wrap(ModBlocks.frozenGrass), 1_600, 40));
        XUMachineGenerators.ICE_GENERATOR.recipes_registry.addRecipe(new EnergyBaseRecipe.EnergyBaseItem(ItemRef.wrap(ModBlocks.frozenStone), 1_600, 40));
        XUMachineGenerators.ICE_GENERATOR.recipes_registry.addRecipe(new EnergyBaseRecipe.EnergyBaseItem(ItemRef.wrap(ModBlocks.frozenCobblestone), 1_600, 40));
        XUMachineGenerators.ICE_GENERATOR.recipes_registry.addRecipe(new EnergyBaseRecipe.EnergyBaseItem(ItemRef.wrap(ModBlocks.frozenGravel), 1_600, 40));
        XUMachineGenerators.ICE_GENERATOR.recipes_registry.addRecipe(new EnergyBaseRecipe.EnergyBaseItem(ItemRef.wrap(ModBlocks.frozenGrassPath), 1_600, 40));
        XUMachineGenerators.ICE_GENERATOR.recipes_registry.addRecipe(new EnergyBaseRecipe.EnergyBaseItem(ItemRef.wrap(ModBlocks.frozenSplinters), 1_600, 40));
        XUMachineGenerators.ICE_GENERATOR.recipes_registry.addRecipe(new EnergyBaseRecipe.EnergyBaseItem(ItemRef.wrap(ModBlocks.dragon_ice), 1_600, 40));
        XUMachineGenerators.ICE_GENERATOR.recipes_registry.addRecipe(new EnergyBaseRecipe.EnergyBaseItem(ItemRef.wrap(ModBlocks.dragon_ice_spikes), 1_600, 40));
        
        // Pink generator
        XUMachineGenerators.PINK_GENERATOR.recipes_registry.addRecipe(new EnergyBaseRecipe.EnergyBaseItem(ItemRef.wrap(ModItems.pixie_dust), 400, 40));
        XUMachineGenerators.PINK_GENERATOR.recipes_registry.addRecipe(new EnergyBaseRecipe.EnergyBaseItem(ItemRef.wrap(ModItems.ambrosia), 400, 40));
//        XUMachineGenerators.PINK_GENERATOR.recipes_registry.addRecipe(new EnergyBaseRecipe.EnergyBaseItem(ItemRef.wrap(new ItemStack(ModBlocks.jar_pixie, 1, 0)), 400, 40));
        
        
        // Terraformer recipes
        
        // Cooler
        TileTerraformerClimograph.register(BlockTerraformer.Type.COOLER, ItemRef.wrap(ModBlocks.dragon_ice), 8);
        TileTerraformerClimograph.register(BlockTerraformer.Type.COOLER, ItemRef.wrap(ModBlocks.dragon_ice_spikes), 8);
        
        // Magic Infuser
        TileTerraformerClimograph.register(BlockTerraformer.Type.MAGIC_INFUSER, ItemRef.wrap(ModItems.pixie_dust), 16);
        
        
        // Crusher
        XUMachineCrusher.addRecipe(new ItemStack(ModItems.troll_tusk), new ItemStack(Items.DYE, 5, 15), new ItemStack(Items.DYE, 3, 15), 50);
    }
    
    @Nonnull
    @Override
    public ModIds getModId()
    {
        return ModIds.ICE_AND_FIRE;
    }
}
