package com.github.exploder1531.mia.integrations.dungeontactics;

import com.github.exploder1531.mia.integrations.ModIds;
import com.github.exploder1531.mia.integrations.tconstruct.ITConstructIntegration;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import pegbeard.dungeontactics.handlers.DTBlocks;
import pegbeard.dungeontactics.handlers.DTItems;
import slimeknights.mantle.util.RecipeMatch;
import slimeknights.tconstruct.library.MaterialIntegration;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.materials.Material;
import slimeknights.tconstruct.library.smeltery.MeltingRecipe;
import slimeknights.tconstruct.shared.TinkerFluids;

import javax.annotation.Nonnull;
import java.util.List;

class TConstructDungeonTacticsIntegration implements ITConstructIntegration
{
    @Override
    public void init(FMLInitializationEvent event)
    {
        final List<MaterialIntegration> materialIntegrations = TinkerRegistry.getMaterialIntegrations();
        
        for (MaterialIntegration m : materialIntegrations)
        {
            Fluid fluid = m.fluid;
            
            if (fluid != null)
            {
                String name = fluid.getName();
                
                switch (name)
                {
                    case "mithril":
                        TinkerRegistry.registerMelting(new MeltingRecipe(RecipeMatch.of(DTItems.CLUSTER_MITHRIL, Material.VALUE_Ingot), fluid));
                        TinkerRegistry.registerMelting(new MeltingRecipe(RecipeMatch.of(DTItems.MITHRIL_CUTLASS, Material.VALUE_Ingot), fluid));
                        TinkerRegistry.registerMelting(new MeltingRecipe(RecipeMatch.of(DTItems.MITHRIL_GLAIVE, Material.VALUE_Ingot * 2), fluid));
                        TinkerRegistry.registerMelting(new MeltingRecipe(RecipeMatch.of(DTItems.MITHRIL_SHIELD, Material.VALUE_Ingot * 3), TinkerFluids.silver));
                        
                        // Plate armor
                        TinkerRegistry.registerMelting(new MeltingRecipe(RecipeMatch.of(DTItems.MITHRILPLATE_HEAD, Material.VALUE_Ingot * 3), fluid));
                        TinkerRegistry.registerMelting(new MeltingRecipe(RecipeMatch.of(DTItems.MITHRILPLATE_CHEST, Material.VALUE_Ingot * 4), fluid));
                        TinkerRegistry.registerMelting(new MeltingRecipe(RecipeMatch.of(DTItems.MITHRILPLATE_LEGS, Material.VALUE_Ingot * 2), fluid));
                        TinkerRegistry.registerMelting(new MeltingRecipe(RecipeMatch.of(DTItems.MITHRILPLATE_FEET, Material.VALUE_Ingot * 2), fluid));
                        break;
                    case "osmium":
                        TinkerRegistry.registerMelting(new MeltingRecipe(RecipeMatch.of(DTItems.CLUSTER_OSMIUM, Material.VALUE_Ingot), fluid));
                        break;
                    case "platinum":
                        TinkerRegistry.registerMelting(new MeltingRecipe(RecipeMatch.of(DTItems.CLUSTER_PLATINUM, Material.VALUE_Ingot), fluid));
                        break;
                    case "titanium":
                        TinkerRegistry.registerMelting(new MeltingRecipe(RecipeMatch.of(DTItems.CLUSTER_TITANIUM, Material.VALUE_Ingot), fluid));
                        break;
                    case "tungsten":
                        TinkerRegistry.registerMelting(new MeltingRecipe(RecipeMatch.of(DTItems.CLUSTER_TUNGSTEN, Material.VALUE_Ingot), fluid));
                        break;
                    case "diamond":
                        TinkerRegistry.registerMelting(new MeltingRecipe(RecipeMatch.of(DTItems.MITHRIL_CUTLASS, Material.VALUE_Gem * 2), fluid));
//                        TinkerRegistry.registerMelting(new MeltingRecipe(RecipeMatch.of(DTItems.CUTLASS_PIERCE, Material.VALUE_Gem * 2), fluid));
//                        TinkerRegistry.registerMelting(new MeltingRecipe(RecipeMatch.of(DTItems.CUTLASS_RIPOSTE, Material.VALUE_Gem * 2), fluid));
                        TinkerRegistry.registerMelting(new MeltingRecipe(RecipeMatch.of(DTItems.DIAMOND_GLAIVE, Material.VALUE_Gem * 2), fluid));
                        TinkerRegistry.registerMelting(new MeltingRecipe(RecipeMatch.of(DTItems.DIAMOND_SHIELD, Material.VALUE_Gem * 3), TinkerFluids.silver));
    
                        // Plate armor
                        TinkerRegistry.registerMelting(new MeltingRecipe(RecipeMatch.of(DTItems.DIAMONDPLATE_HEAD, Material.VALUE_Ingot * 3), fluid));
                        TinkerRegistry.registerMelting(new MeltingRecipe(RecipeMatch.of(DTItems.DIAMONDPLATE_CHEST, Material.VALUE_Ingot * 4), fluid));
                        TinkerRegistry.registerMelting(new MeltingRecipe(RecipeMatch.of(DTItems.DIAMONDPLATE_LEGS, Material.VALUE_Ingot * 2), fluid));
                        TinkerRegistry.registerMelting(new MeltingRecipe(RecipeMatch.of(DTItems.DIAMONDPLATE_FEET, Material.VALUE_Ingot * 2), fluid));
                        break;
                }
            }
        }
        
        TinkerRegistry.registerMelting(new MeltingRecipe(RecipeMatch.of(DTBlocks.OBSIDIAN_BRICK, Material.VALUE_SearedBlock), TinkerFluids.obsidian));
        TinkerRegistry.registerMelting(new MeltingRecipe(RecipeMatch.of(DTBlocks.ALCHEMYCAULDRON, Material.VALUE_Ingot * 7), TinkerFluids.iron));
        TinkerRegistry.registerMelting(new MeltingRecipe(RecipeMatch.of(DTBlocks.POWERED_FENCE, Material.VALUE_Ingot * 6 / 16), TinkerFluids.iron));
        TinkerRegistry.registerMelting(new MeltingRecipe(RecipeMatch.of(DTBlocks.TRAP_CLAMP, Material.VALUE_Ingot * 4), TinkerFluids.iron));
        TinkerRegistry.registerMelting(new MeltingRecipe(RecipeMatch.of(DTBlocks.LANTERN_IRON, Material.VALUE_Ingot * 2), TinkerFluids.iron));
        
        // Ore clusters
        TinkerRegistry.registerMelting(new MeltingRecipe(RecipeMatch.of(DTItems.CLUSTER_IRON, Material.VALUE_Ingot), TinkerFluids.iron));
        TinkerRegistry.registerMelting(new MeltingRecipe(RecipeMatch.of(DTItems.CLUSTER_ALUMINIUM, Material.VALUE_Ingot), TinkerFluids.aluminum));
        TinkerRegistry.registerMelting(new MeltingRecipe(RecipeMatch.of(DTItems.CLUSTER_COPPER, Material.VALUE_Ingot), TinkerFluids.copper));
        TinkerRegistry.registerMelting(new MeltingRecipe(RecipeMatch.of(DTItems.CLUSTER_GOLD, Material.VALUE_Ingot), TinkerFluids.gold));
        TinkerRegistry.registerMelting(new MeltingRecipe(RecipeMatch.of(DTItems.CLUSTER_LEAD, Material.VALUE_Ingot), TinkerFluids.lead));
        TinkerRegistry.registerMelting(new MeltingRecipe(RecipeMatch.of(DTItems.CLUSTER_NICKEL, Material.VALUE_Ingot), TinkerFluids.nickel));
        TinkerRegistry.registerMelting(new MeltingRecipe(RecipeMatch.of(DTItems.CLUSTER_SILVER, Material.VALUE_Ingot), TinkerFluids.silver));
        TinkerRegistry.registerMelting(new MeltingRecipe(RecipeMatch.of(DTItems.CLUSTER_TIN, Material.VALUE_Ingot), TinkerFluids.tin));
        
        TinkerRegistry.registerMelting(new MeltingRecipe(RecipeMatch.of(DTItems.STEEL_CUTLASS, Material.VALUE_Ingot * 2), TinkerFluids.steel));
        TinkerRegistry.registerMelting(new MeltingRecipe(RecipeMatch.of(DTItems.GOLDEN_CUTLASS, Material.VALUE_Ingot * 2), TinkerFluids.gold));
        TinkerRegistry.registerMelting(new MeltingRecipe(RecipeMatch.of(DTItems.IRON_CUTLASS, Material.VALUE_Ingot * 2), TinkerFluids.iron));
        TinkerRegistry.registerMelting(new MeltingRecipe(RecipeMatch.of(DTItems.SILVER_CUTLASS, Material.VALUE_Ingot * 2), TinkerFluids.silver));
        
        TinkerRegistry.registerMelting(new MeltingRecipe(RecipeMatch.of(DTItems.STEEL_GLAIVE, Material.VALUE_Ingot * 2), TinkerFluids.steel));
        TinkerRegistry.registerMelting(new MeltingRecipe(RecipeMatch.of(DTItems.GOLDEN_GLAIVE, Material.VALUE_Ingot * 2), TinkerFluids.gold));
        TinkerRegistry.registerMelting(new MeltingRecipe(RecipeMatch.of(DTItems.IRON_GLAIVE, Material.VALUE_Ingot * 2), TinkerFluids.iron));
        TinkerRegistry.registerMelting(new MeltingRecipe(RecipeMatch.of(DTItems.SILVER_GLAIVE, Material.VALUE_Ingot * 2), TinkerFluids.silver));
        
        TinkerRegistry.registerMelting(new MeltingRecipe(RecipeMatch.of(DTItems.STEEL_SHIELD, Material.VALUE_Ingot * 3), TinkerFluids.steel));
        TinkerRegistry.registerMelting(new MeltingRecipe(RecipeMatch.of(DTItems.GOLDEN_SHIELD, Material.VALUE_Ingot * 3), TinkerFluids.gold));
        TinkerRegistry.registerMelting(new MeltingRecipe(RecipeMatch.of(DTItems.IRON_SHIELD, Material.VALUE_Ingot * 3), TinkerFluids.iron));
        TinkerRegistry.registerMelting(new MeltingRecipe(RecipeMatch.of(DTItems.SILVER_SHIELD, Material.VALUE_Ingot * 3), TinkerFluids.silver));
        
        TinkerRegistry.registerMelting(new MeltingRecipe(RecipeMatch.of(DTItems.SLOWNESSKNIFE, Material.VALUE_Nugget * 2), TinkerFluids.iron));
        TinkerRegistry.registerMelting(new MeltingRecipe(RecipeMatch.of(DTItems.WEAKNESSKNIFE, Material.VALUE_Nugget * 2), TinkerFluids.iron));
        TinkerRegistry.registerMelting(new MeltingRecipe(RecipeMatch.of(DTItems.POISONKNIFE, Material.VALUE_Nugget * 2), TinkerFluids.iron));
        TinkerRegistry.registerMelting(new MeltingRecipe(RecipeMatch.of(DTItems.CAUSTICKNIFE, Material.VALUE_Nugget * 2), TinkerFluids.iron));
        
        
        // Plate armor
        TinkerRegistry.registerMelting(new MeltingRecipe(RecipeMatch.of(DTItems.IRONPLATE_HEAD, Material.VALUE_Ingot * 3), TinkerFluids.iron));
        TinkerRegistry.registerMelting(new MeltingRecipe(RecipeMatch.of(DTItems.IRONPLATE_CHEST, Material.VALUE_Ingot * 4), TinkerFluids.iron));
        TinkerRegistry.registerMelting(new MeltingRecipe(RecipeMatch.of(DTItems.IRONPLATE_LEGS, Material.VALUE_Ingot * 2), TinkerFluids.iron));
        TinkerRegistry.registerMelting(new MeltingRecipe(RecipeMatch.of(DTItems.IRONPLATE_FEET, Material.VALUE_Ingot * 2), TinkerFluids.iron));
        
        TinkerRegistry.registerMelting(new MeltingRecipe(RecipeMatch.of(DTItems.SILVERPLATE_HEAD, Material.VALUE_Ingot * 3), TinkerFluids.silver));
        TinkerRegistry.registerMelting(new MeltingRecipe(RecipeMatch.of(DTItems.SILVERPLATE_CHEST, Material.VALUE_Ingot * 4), TinkerFluids.silver));
        TinkerRegistry.registerMelting(new MeltingRecipe(RecipeMatch.of(DTItems.SILVERPLATE_LEGS, Material.VALUE_Ingot * 2), TinkerFluids.silver));
        TinkerRegistry.registerMelting(new MeltingRecipe(RecipeMatch.of(DTItems.SILVERPLATE_FEET, Material.VALUE_Ingot * 2), TinkerFluids.silver));
        
        TinkerRegistry.registerMelting(new MeltingRecipe(RecipeMatch.of(DTItems.GOLDPLATE_HEAD, Material.VALUE_Ingot * 3), TinkerFluids.gold));
        TinkerRegistry.registerMelting(new MeltingRecipe(RecipeMatch.of(DTItems.GOLDPLATE_CHEST, Material.VALUE_Ingot * 4), TinkerFluids.gold));
        TinkerRegistry.registerMelting(new MeltingRecipe(RecipeMatch.of(DTItems.GOLDPLATE_LEGS, Material.VALUE_Ingot * 2), TinkerFluids.gold));
        TinkerRegistry.registerMelting(new MeltingRecipe(RecipeMatch.of(DTItems.GOLDPLATE_FEET, Material.VALUE_Ingot * 2), TinkerFluids.gold));
        
        TinkerRegistry.registerMelting(new MeltingRecipe(RecipeMatch.of(DTItems.STEELPLATE_HEAD, Material.VALUE_Ingot * 3), TinkerFluids.steel));
        TinkerRegistry.registerMelting(new MeltingRecipe(RecipeMatch.of(DTItems.STEELPLATE_CHEST, Material.VALUE_Ingot * 4), TinkerFluids.steel));
        TinkerRegistry.registerMelting(new MeltingRecipe(RecipeMatch.of(DTItems.STEELPLATE_LEGS, Material.VALUE_Ingot * 2), TinkerFluids.steel));
        TinkerRegistry.registerMelting(new MeltingRecipe(RecipeMatch.of(DTItems.STEELPLATE_FEET, Material.VALUE_Ingot * 2), TinkerFluids.steel));
    }
    
    @Nonnull
    @Override
    public String getModId()
    {
        return ModIds.TINKERS_CONSTRUCT;
    }
}
