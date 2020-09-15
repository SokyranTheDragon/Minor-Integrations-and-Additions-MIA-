//package com.github.sokyranthedragon.mia.integrations.aether_continuation;
//
//import cofh.thermalexpansion.util.managers.machine.PulverizerManager;
//import cofh.thermalexpansion.util.managers.machine.SawmillManager;
//import com.github.sokyranthedragon.mia.integrations.ModIds;
//import com.github.sokyranthedragon.mia.integrations.thermalexpansion.IThermalExpansionIntegration;
//import com.legacy.aether.addon.blocks.BlocksAetherAddon;
//import com.legacy.aether.addon.items.ItemsAetherAddon;
//import com.legacy.aether.blocks.BlocksAether;
//import com.legacy.aether.items.ItemsAether;
//import mcp.MethodsReturnNonnullByDefault;
//import net.minecraft.item.ItemStack;
//
//@MethodsReturnNonnullByDefault
//class ThermalExpansionAetherContinuationIntegration implements IThermalExpansionIntegration
//{
//    @Override
//    public void addPostInitRecipes()
//    {
//        SawmillManager.addRecycleRecipe(2_000, new ItemStack(ItemsAetherAddon.skyroot_door), new ItemStack(BlocksAether.skyroot_plank), 1);
//        PulverizerManager.addRecycleRecipe(4_000, new ItemStack(ItemsAetherAddon.zanite_door), new ItemStack(ItemsAether.zanite_gemstone), 2);
//        PulverizerManager.addRecycleRecipe(4_000, new ItemStack(BlocksAetherAddon.zanite_trapdoor), new ItemStack(ItemsAether.zanite_gemstone), 1);
//        PulverizerManager.addRecycleRecipe(4_000, new ItemStack(BlocksAetherAddon.zanite_pressure_plate), new ItemStack(ItemsAether.zanite_gemstone), 2);
//    }
//
//    @Override
//    public ModIds getModId()
//    {
//        return ModIds.AETHER_CONTINUATION;
//    }
//}
