package com.github.sokyranthedragon.mia.integrations.iceandfire;

import com.github.alexthe666.iceandfire.block.IafBlockRegistry;
import com.github.alexthe666.iceandfire.enums.EnumSkullType;
import com.github.alexthe666.iceandfire.item.IafItemRegistry;
import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.integrations.xu2.IExtraUtilsIntegration;
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
        XUMachineGenerators.DEATH_GENERATOR.recipes_registry.addRecipe(new EnergyBaseRecipe.EnergyBaseItem(ItemRef.wrap(IafItemRegistry.dragonbone), 64_000, 60));
        XUMachineGenerators.DEATH_GENERATOR.recipes_registry.addRecipe(new EnergyBaseRecipe.EnergyBaseItem(ItemRef.wrap(IafBlockRegistry.dragon_bone_block_wall), 64_000 * 6, 120));
        XUMachineGenerators.DEATH_GENERATOR.recipes_registry.addRecipe(new EnergyBaseRecipe.EnergyBaseItem(ItemRef.wrap(IafBlockRegistry.dragon_bone_block), 64_000 * 9, 180));
        XUMachineGenerators.DEATH_GENERATOR.recipes_registry.addRecipe(new EnergyBaseRecipe.EnergyBaseItem(ItemRef.wrap(IafItemRegistry.troll_tusk), 27_000, 40));
        XUMachineGenerators.DEATH_GENERATOR.recipes_registry.addRecipe(new EnergyBaseRecipe.EnergyBaseItem(ItemRef.wrap(IafItemRegistry.wither_shard), 10_333, 80));
        // Skulls
        for (EnumSkullType skull : EnumSkullType.values())
        {
            XUMachineGenerators.DEATH_GENERATOR.recipes_registry.addRecipe(new EnergyBaseRecipe.EnergyBaseItem(ItemRef.wrap(skull.skull_item), 120_000, 200));
            
            XUMachineCrusher.addRecipe(new ItemStack(skull.skull_item), new ItemStack(Items.DYE, 5, 15), new ItemStack(Items.DYE, 3, 15), 50);
        }
        
        // Ice generator
        XUMachineGenerators.ICE_GENERATOR.recipes_registry.addRecipe(new EnergyBaseRecipe.EnergyBaseItem(ItemRef.wrap(IafBlockRegistry.frozenDirt), 1_600, 40));
        XUMachineGenerators.ICE_GENERATOR.recipes_registry.addRecipe(new EnergyBaseRecipe.EnergyBaseItem(ItemRef.wrap(IafBlockRegistry.frozenGrass), 1_600, 40));
        XUMachineGenerators.ICE_GENERATOR.recipes_registry.addRecipe(new EnergyBaseRecipe.EnergyBaseItem(ItemRef.wrap(IafBlockRegistry.frozenStone), 1_600, 40));
        XUMachineGenerators.ICE_GENERATOR.recipes_registry.addRecipe(new EnergyBaseRecipe.EnergyBaseItem(ItemRef.wrap(IafBlockRegistry.frozenCobblestone), 1_600, 40));
        XUMachineGenerators.ICE_GENERATOR.recipes_registry.addRecipe(new EnergyBaseRecipe.EnergyBaseItem(ItemRef.wrap(IafBlockRegistry.frozenGravel), 1_600, 40));
        XUMachineGenerators.ICE_GENERATOR.recipes_registry.addRecipe(new EnergyBaseRecipe.EnergyBaseItem(ItemRef.wrap(IafBlockRegistry.frozenGrassPath), 1_600, 40));
        XUMachineGenerators.ICE_GENERATOR.recipes_registry.addRecipe(new EnergyBaseRecipe.EnergyBaseItem(ItemRef.wrap(IafBlockRegistry.frozenSplinters), 1_600, 40));
        XUMachineGenerators.ICE_GENERATOR.recipes_registry.addRecipe(new EnergyBaseRecipe.EnergyBaseItem(ItemRef.wrap(IafBlockRegistry.dragon_ice), 1_600, 40));
        XUMachineGenerators.ICE_GENERATOR.recipes_registry.addRecipe(new EnergyBaseRecipe.EnergyBaseItem(ItemRef.wrap(IafBlockRegistry.dragon_ice_spikes), 1_600, 40));
        
        // Pink generator
        XUMachineGenerators.PINK_GENERATOR.recipes_registry.addRecipe(new EnergyBaseRecipe.EnergyBaseItem(ItemRef.wrap(IafItemRegistry.pixie_dust), 400, 40));
        XUMachineGenerators.PINK_GENERATOR.recipes_registry.addRecipe(new EnergyBaseRecipe.EnergyBaseItem(ItemRef.wrap(IafItemRegistry.ambrosia), 400, 40));
//        XUMachineGenerators.PINK_GENERATOR.recipes_registry.addRecipe(new EnergyBaseRecipe.EnergyBaseItem(ItemRef.wrap(new ItemStack(IafBlockRegistry.jar_pixie, 1, 0)), 400, 40));
        
        
        // Terraformer recipes
        
        // Cooler
        TileTerraformerClimograph.register(BlockTerraformer.Type.COOLER, ItemRef.wrap(IafBlockRegistry.dragon_ice), 8);
        TileTerraformerClimograph.register(BlockTerraformer.Type.COOLER, ItemRef.wrap(IafBlockRegistry.dragon_ice_spikes), 8);
        
        // Magic Infuser
        TileTerraformerClimograph.register(BlockTerraformer.Type.MAGIC_INFUSER, ItemRef.wrap(IafItemRegistry.pixie_dust), 16);
        
        // Crusher
        XUMachineCrusher.addRecipe(new ItemStack(IafItemRegistry.troll_tusk), new ItemStack(Items.DYE, 5, 15), new ItemStack(Items.DYE, 3, 15), 50);
    }
    
    @Nonnull
    @Override
    public ModIds getModId()
    {
        return ModIds.ICE_AND_FIRE;
    }
}
