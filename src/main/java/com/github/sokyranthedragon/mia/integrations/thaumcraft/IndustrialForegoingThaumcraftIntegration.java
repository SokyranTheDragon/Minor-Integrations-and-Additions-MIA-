package com.github.sokyranthedragon.mia.integrations.thaumcraft;

import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.integrations.industrialforegoing.IIndustrialForegoingIntegration;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.item.ItemStack;
import thaumcraft.api.items.ItemsTC;

@MethodsReturnNonnullByDefault
class IndustrialForegoingThaumcraftIntegration implements IIndustrialForegoingIntegration
{
    @Override
    public ItemStack[] getBasicProteinGeneratorEntries()
    {
        return new ItemStack[]
            {
                new ItemStack(ItemsTC.brain),
                new ItemStack(ItemsTC.tripleMeatTreat)
            };
    }
    
    @Override
    public ModIds getModId()
    {
        return ModIds.THAUMCRAFT;
    }
}
