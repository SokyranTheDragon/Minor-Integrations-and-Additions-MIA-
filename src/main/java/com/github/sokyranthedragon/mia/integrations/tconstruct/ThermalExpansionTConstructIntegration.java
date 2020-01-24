package com.github.sokyranthedragon.mia.integrations.tconstruct;

import cofh.thermalexpansion.util.managers.dynamo.NumismaticManager;
import cofh.thermalexpansion.util.managers.machine.PulverizerManager;
import cofh.thermalexpansion.util.managers.machine.SawmillManager;
import cofh.thermalfoundation.item.ItemMaterial;
import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.integrations.thermalexpansion.IThermalExpansionIntegration;
import net.minecraft.item.ItemStack;
import slimeknights.tconstruct.TConstruct;
import slimeknights.tconstruct.gadgets.TinkerGadgets;
import slimeknights.tconstruct.shared.TinkerCommons;
import slimeknights.tconstruct.smeltery.TinkerSmeltery;

import javax.annotation.Nonnull;

class ThermalExpansionTConstructIntegration implements IThermalExpansionIntegration
{
    
    @Override
    public void addRecipes()
    {
        boolean tinkerCommons = TConstruct.pulseManager.isPulseLoaded(TinkerCommons.PulseId);
        boolean tinkerSmeltry = TConstruct.pulseManager.isPulseLoaded(TinkerSmeltery.PulseId);
        boolean tinkerGadgets = TConstruct.pulseManager.isPulseLoaded(TinkerGadgets.PulseId);
        
        // Sawmill
        int energy = 2_000;
        if (tinkerCommons)
        {
            SawmillManager.addRecipe(energy, new ItemStack(TinkerCommons.stairsFirewood, 2), new ItemStack(TinkerCommons.blockFirewood, 1, 1), ItemMaterial.dustWood.copy(), 25);
            SawmillManager.addRecipe(energy, new ItemStack(TinkerCommons.stairsLavawood, 2), new ItemStack(TinkerCommons.blockFirewood, 1, 0), ItemMaterial.dustWood.copy(), 25);
        }
        
        
        // Pulverizer
        energy = 3000;
        
        if (tinkerCommons)
        {
            PulverizerManager.addRecycleRecipe(energy, new ItemStack(TinkerCommons.blockDecoGround, 1, 0), TinkerCommons.mudBrick, 4, false);
            PulverizerManager.addRecycleRecipe(energy, new ItemStack(TinkerCommons.stairsMudBrick, 2), TinkerCommons.mudBrick, 3);
            PulverizerManager.addRecycleRecipe(energy, new ItemStack(TinkerCommons.slabDecoGround, 1, 0), TinkerCommons.mudBrick, 2, false);
        }
        if (tinkerSmeltry)
        {
            PulverizerManager.addRecycleRecipe(energy, new ItemStack(TinkerSmeltery.searedBlock, 1, 3), TinkerCommons.searedBrick, 4, false);
            PulverizerManager.addRecycleRecipe(energy, new ItemStack(TinkerSmeltery.searedStairsBrick, 2), TinkerCommons.searedBrick, 3);
            PulverizerManager.addRecycleRecipe(energy, new ItemStack(TinkerSmeltery.searedSlab, 1, 3), TinkerCommons.searedBrick, 2, false);
        }
        if (tinkerGadgets)
        {
            PulverizerManager.addRecycleRecipe(energy, new ItemStack(TinkerGadgets.driedClay, 1, 1), TinkerCommons.driedBrick, 4, false);
            PulverizerManager.addRecycleRecipe(energy, new ItemStack(TinkerGadgets.driedClayStairs, 2), TinkerCommons.driedBrick, 3);
            PulverizerManager.addRecycleRecipe(energy, new ItemStack(TinkerGadgets.driedClaySlab, 1, 1), TinkerCommons.driedBrick, 2, false);
        }
        
        
        // Numismatic dynamo
        if (tinkerCommons)
        {
            NumismaticManager.addGemFuel(TinkerCommons.matSilkyJewel, 360_000);
            NumismaticManager.addGemFuel(TinkerCommons.blockSilkyJewel, 360_000 * 9);
        }
    }
    
    @Nonnull
    @Override
    public ModIds getModId()
    {
        return ModIds.TINKERS_CONSTRUCT;
    }
}
