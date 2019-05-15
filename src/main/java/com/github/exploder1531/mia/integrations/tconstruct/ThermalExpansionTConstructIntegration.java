package com.github.exploder1531.mia.integrations.tconstruct;

import cofh.thermalexpansion.util.managers.dynamo.NumismaticManager;
import cofh.thermalexpansion.util.managers.machine.PulverizerManager;
import cofh.thermalexpansion.util.managers.machine.SawmillManager;
import cofh.thermalfoundation.item.ItemMaterial;
import com.github.exploder1531.mia.integrations.ModIds;
import com.github.exploder1531.mia.integrations.thermalexpansion.IThermalExpansionIntegration;
import net.minecraft.item.ItemStack;
import slimeknights.tconstruct.gadgets.TinkerGadgets;
import slimeknights.tconstruct.shared.TinkerCommons;
import slimeknights.tconstruct.smeltery.TinkerSmeltery;

import javax.annotation.Nonnull;

class ThermalExpansionTConstructIntegration implements IThermalExpansionIntegration
{
    
    @Override
    public void addRecipes()
    {
        // Sawmill
        int energy = 2_000;
        SawmillManager.addRecipe(energy, new ItemStack(TinkerCommons.stairsFirewood, 2), new ItemStack(TinkerCommons.blockFirewood, 1, 1), ItemMaterial.dustWood.copy(), 25);
        SawmillManager.addRecipe(energy, new ItemStack(TinkerCommons.stairsLavawood, 2), new ItemStack(TinkerCommons.blockFirewood, 1, 0), ItemMaterial.dustWood.copy(), 25);
        
        
        // Pulverizer
        energy = 3000;
    
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(TinkerCommons.blockDecoGround, 1, 0), TinkerCommons.mudBrick, 4);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(TinkerCommons.stairsMudBrick, 2), TinkerCommons.mudBrick, 3);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(TinkerCommons.slabDecoGround, 1, 0), TinkerCommons.mudBrick, 2);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(TinkerSmeltery.searedBlock, 1, 3), TinkerCommons.searedBrick, 4);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(TinkerSmeltery.searedStairsBrick, 2), TinkerCommons.searedBrick, 3);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(TinkerSmeltery.searedSlab, 1, 3), TinkerCommons.searedBrick, 2);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(TinkerGadgets.driedClay, 1, 1), TinkerCommons.driedBrick, 4);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(TinkerGadgets.driedClayStairs, 2), TinkerCommons.driedBrick, 3);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(TinkerGadgets.driedClaySlab, 1, 1), TinkerCommons.driedBrick, 2);
        
        
        // Numismatic dynamo
        NumismaticManager.addGemFuel(TinkerCommons.matSilkyJewel, 360_000);
        NumismaticManager.addGemFuel(TinkerCommons.blockSilkyJewel, 360_000 * 9);
    }
    
    @Nonnull
    @Override
    public String getModId()
    {
        return ModIds.TINKERS_CONSTRUCT;
    }
}
