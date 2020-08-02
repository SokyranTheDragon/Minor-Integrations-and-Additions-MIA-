package com.github.sokyranthedragon.mia.integrations.aether;

import cofh.thermalexpansion.util.managers.device.TapperManager;
import cofh.thermalexpansion.util.managers.machine.InsolatorManager;
import cofh.thermalexpansion.util.managers.machine.PulverizerManager;
import cofh.thermalexpansion.util.managers.machine.SawmillManager;
import cofh.thermalexpansion.util.managers.machine.SmelterManager;
import cofh.thermalfoundation.init.TFFluids;
import cofh.thermalfoundation.item.ItemMaterial;
import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.integrations.thermalexpansion.IThermalExpansionIntegration;
import com.legacy.aether.blocks.BlocksAether;
import com.legacy.aether.items.ItemsAether;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.OreDictionary;

@MethodsReturnNonnullByDefault
class ThermalExpansionAetherIntegration implements IThermalExpansionIntegration
{
    @Override
    public void addRecipes()
    {
        // Some of the recipes end up with air as the input in JEI, but the recipes work
        
        int energy = 6_000;
        
        // Vanilla
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(ItemsAether.leather_gloves), new ItemStack(Items.LEATHER), 1);
        SawmillManager.addRecycleRecipe(energy, new ItemStack(ItemsAether.diamond_gloves), new ItemStack(Items.DIAMOND), 1);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(ItemsAether.iron_gloves), new ItemStack(Items.IRON_INGOT), 1);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(ItemsAether.chain_gloves), new ItemStack(Items.IRON_INGOT), 1);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(ItemsAether.golden_gloves), new ItemStack(Items.GOLD_INGOT), 1);
        
        // Zanite
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(ItemsAether.zanite_helmet), new ItemStack(ItemsAether.zanite_gemstone), 2);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(ItemsAether.zanite_chestplate), new ItemStack(ItemsAether.zanite_gemstone), 4);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(ItemsAether.zanite_leggings), new ItemStack(ItemsAether.zanite_gemstone), 2);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(ItemsAether.zanite_boots), new ItemStack(ItemsAether.zanite_gemstone), 3);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(ItemsAether.zanite_gloves), new ItemStack(ItemsAether.zanite_gemstone), 1);
//
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(ItemsAether.zanite_pickaxe), new ItemStack(ItemsAether.zanite_gemstone), 1);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(ItemsAether.zanite_axe), new ItemStack(ItemsAether.zanite_gemstone), 1);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(ItemsAether.zanite_shovel), new ItemStack(ItemsAether.zanite_gemstone), 1);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(ItemsAether.zanite_sword), new ItemStack(ItemsAether.zanite_gemstone), 1);
        
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(ItemsAether.nature_staff), new ItemStack(ItemsAether.zanite_gemstone), 1);
        
        // Gravitite
        SmelterManager.addRecycleRecipe(energy, new ItemStack(ItemsAether.gravitite_helmet), new ItemStack(BlocksAether.enchanted_gravitite), 2);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(ItemsAether.gravitite_chestplate), new ItemStack(BlocksAether.enchanted_gravitite), 4);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(ItemsAether.gravitite_leggings), new ItemStack(BlocksAether.enchanted_gravitite), 2);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(ItemsAether.gravitite_boots), new ItemStack(BlocksAether.enchanted_gravitite), 3);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(ItemsAether.gravitite_gloves), new ItemStack(BlocksAether.enchanted_gravitite), 1);
//
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(ItemsAether.gravitite_pickaxe), new ItemStack(BlocksAether.enchanted_gravitite), 1);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(ItemsAether.gravitite_axe), new ItemStack(BlocksAether.enchanted_gravitite), 1);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(ItemsAether.gravitite_shovel), new ItemStack(BlocksAether.enchanted_gravitite), 1);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(ItemsAether.gravitite_sword), new ItemStack(BlocksAether.enchanted_gravitite), 1);
        
        // Skyroot
        energy = 3_000;
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(ItemsAether.skyroot_axe), ItemMaterial.dustWood, 2);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(ItemsAether.skyroot_pickaxe), ItemMaterial.dustWood, 2);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(ItemsAether.skyroot_shovel), ItemMaterial.dustWood, 2);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(ItemsAether.skyroot_sword), ItemMaterial.dustWood, 2);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(ItemsAether.skyroot_bucket), ItemMaterial.dustWood, 2);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(BlocksAether.skyroot_plank), ItemMaterial.dustWood, 2);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(BlocksAether.aether_log, 1, OreDictionary.WILDCARD_VALUE), ItemMaterial.dustWood, 8);
        
        // Rings
        energy = 3_000;
        SmelterManager.addRecycleRecipe(energy, new ItemStack(ItemsAether.iron_ring), new ItemStack(Items.IRON_INGOT), 4);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(ItemsAether.golden_ring), new ItemStack(Items.GOLD_INGOT), 4);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(ItemsAether.zanite_ring), new ItemStack(ItemsAether.zanite_gemstone), 2);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(ItemsAether.iron_pendant), new ItemStack(Items.IRON_INGOT), 1);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(ItemsAether.golden_pendant), new ItemStack(Items.GOLD_INGOT), 1);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(ItemsAether.zanite_pendant), new ItemStack(ItemsAether.zanite_gemstone), 1);
        
        // Trees
        TapperManager.addStandardMapping(new ItemStack(BlocksAether.aether_log), new FluidStack(TFFluids.fluidResin, 50));
        
        InsolatorManager.addDefaultTreeRecipe(new ItemStack(BlocksAether.skyroot_sapling), new ItemStack(BlocksAether.aether_log, 6, 1), new ItemStack(BlocksAether.skyroot_sapling));
        InsolatorManager.addDefaultTreeRecipe(new ItemStack(BlocksAether.golden_oak_sapling), new ItemStack(BlocksAether.aether_log, 1, 1), new ItemStack(BlocksAether.skyroot_sapling));
        
        // Flowers, etc
        InsolatorManager.addDefaultRecipe(new ItemStack(BlocksAether.purple_flower), new ItemStack(BlocksAether.purple_flower, 3), ItemStack.EMPTY, 0);
        InsolatorManager.addDefaultRecipe(new ItemStack(BlocksAether.white_flower), new ItemStack(BlocksAether.white_flower, 3), ItemStack.EMPTY, 0);
        
        InsolatorManager.addDefaultRecipe(new ItemStack(BlocksAether.berry_bush_stem), new ItemStack(ItemsAether.blue_berry, 3), new ItemStack(BlocksAether.berry_bush_stem), 100);
    }
    
    @Override
    public ModIds getModId()
    {
        return ModIds.AETHER;
    }
}
