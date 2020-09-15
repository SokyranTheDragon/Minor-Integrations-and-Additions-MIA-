package com.github.sokyranthedragon.mia.integrations.aether_lost_content;

import cofh.thermalexpansion.util.managers.machine.InsolatorManager;
import cofh.thermalexpansion.util.managers.machine.PulverizerManager;
import cofh.thermalexpansion.util.managers.machine.SmelterManager;
import com.gildedgames.the_aether.blocks.BlocksAether;
import com.gildedgames.the_aether.items.ItemsAether;
import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.integrations.thermalexpansion.IThermalExpansionIntegration;
import com.legacy.lostaether.blocks.BlocksLostAether;
import com.legacy.lostaether.items.ItemsLostAether;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.item.ItemStack;

@MethodsReturnNonnullByDefault
class ThermalExpansionAetherLostContentIntegration implements IThermalExpansionIntegration
{
    @Override
    public void addRecipes()
    {
        int energy = 3_000;
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(ItemsLostAether.zanite_shield), new ItemStack(ItemsAether.zanite_gemstone), 3);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(ItemsLostAether.gravitite_shield), new ItemStack(BlocksAether.enchanted_gravitite), 3);
        
        InsolatorManager.addDefaultTreeRecipe(new ItemStack(BlocksLostAether.crystal_sapling), new ItemStack(ItemsAether.white_apple), new ItemStack(BlocksLostAether.crystal_sapling));
    }
    
    @Override
    public ModIds getModId()
    {
        return ModIds.AETHER_LOST_CONTENT;
    }
}
