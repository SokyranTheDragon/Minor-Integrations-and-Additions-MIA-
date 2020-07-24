package com.github.sokyranthedragon.mia.integrations.aether_lost_content;

import cofh.thermalexpansion.util.managers.machine.InsolatorManager;
import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.integrations.thermalexpansion.IThermalExpansionIntegration;
import com.legacy.aether.items.ItemsAether;
import com.legacy.lostaether.blocks.BlocksLostAether;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.item.ItemStack;

@MethodsReturnNonnullByDefault
class ThermalExpansionAetherLostContentIntegration implements IThermalExpansionIntegration
{
    @Override
    public void addRecipes()
    {
        InsolatorManager.addDefaultTreeRecipe(new ItemStack(BlocksLostAether.crystal_sapling), new ItemStack(ItemsAether.white_apple), new ItemStack(BlocksLostAether.crystal_sapling));
    }
    
    @Override
    public ModIds getModId()
    {
        return ModIds.AETHER_LOST_CONTENT;
    }
}
