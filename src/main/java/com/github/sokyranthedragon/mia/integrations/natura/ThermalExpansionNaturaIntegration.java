package com.github.sokyranthedragon.mia.integrations.natura;

import cofh.thermalexpansion.util.managers.device.FactorizerManager;
import cofh.thermalexpansion.util.managers.device.TapperManager;
import cofh.thermalexpansion.util.managers.machine.InsolatorManager;
import cofh.thermalexpansion.util.managers.machine.PulverizerManager;
import cofh.thermalexpansion.util.managers.machine.SawmillManager;
import cofh.thermalexpansion.util.managers.machine.TransposerManager;
import cofh.thermalfoundation.init.TFFluids;
import cofh.thermalfoundation.item.ItemFertilizer;
import cofh.thermalfoundation.item.ItemMaterial;
import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.integrations.thermalexpansion.IThermalExpansionIntegration;
import com.progwml6.natura.entities.NaturaEntities;
import com.progwml6.natura.nether.NaturaNether;
import com.progwml6.natura.overworld.NaturaOverworld;
import com.progwml6.natura.shared.NaturaCommons;
import com.progwml6.natura.tools.NaturaTools;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

import javax.annotation.Nonnull;

import static com.progwml6.natura.Natura.pulseManager;

class ThermalExpansionNaturaIntegration implements IThermalExpansionIntegration
{
    @Override
    public void addRecipes()
    {
        if (pulseManager.isPulseLoaded(NaturaCommons.PulseId))
            FactorizerManager.addDefaultRecipe(NaturaCommons.sulfurPowder, new ItemStack(NaturaCommons.clouds, 1, 3), 4);
        if (pulseManager.isPulseLoaded(NaturaOverworld.PulseId))
        {
            TapperManager.addStandardMapping(new ItemStack(NaturaOverworld.redwoodLog, 1, 1), new FluidStack(TFFluids.fluidResin, 50));
            
            SawmillManager.addDoorRecipe(NaturaOverworld.redwood_bark_door, new ItemStack(NaturaOverworld.redwoodLog, 1, 0));
            SawmillManager.addDoorRecipe(NaturaOverworld.tiger_door, new ItemStack(NaturaOverworld.overworldPlanks, 1, 3));
            SawmillManager.addDoorRecipe(NaturaOverworld.maple_door, new ItemStack(NaturaOverworld.overworldPlanks, 1, 4));
            SawmillManager.addDoorRecipe(NaturaOverworld.silverbell_door, new ItemStack(NaturaOverworld.overworldPlanks, 1, 5));
    
            InsolatorManager.addDefaultRecipe(new ItemStack(NaturaOverworld.bluebellsFlower), new ItemStack(NaturaOverworld.bluebellsFlower, 3), ItemStack.EMPTY, 0);
            InsolatorManager.addDefaultTreeRecipe(new ItemStack(NaturaOverworld.redwoodSapling), new ItemStack(NaturaOverworld.redwoodLog, 6, 0), new ItemStack(NaturaOverworld.redwoodSapling));
            InsolatorManager.addRecipe(400, new ItemStack(NaturaOverworld.redwoodLog, 1, 0), ItemFertilizer.fertilizerBasic, new ItemStack(NaturaOverworld.redwoodLog, 1, 2), ItemStack.EMPTY, 0, InsolatorManager.Type.TREE);
            InsolatorManager.addRecipe(400, new ItemStack(NaturaOverworld.redwoodLog, 1, 2), ItemFertilizer.fertilizerBasic, new ItemStack(NaturaOverworld.redwoodLog, 1, 1), ItemStack.EMPTY, 0, InsolatorManager.Type.TREE);
            InsolatorManager.addRecipe(400, new ItemStack(NaturaOverworld.redwoodLog, 1, 1), ItemFertilizer.fertilizerBasic, new ItemStack(NaturaOverworld.redwoodLog, 1, 0), ItemStack.EMPTY, 0, InsolatorManager.Type.TREE);
        }
        if (pulseManager.isPulseLoaded(NaturaNether.PulseId))
        {
            FluidStack fluidBloodwood = FluidRegistry.getFluidStack("blood", 125);
            if (fluidBloodwood == null)
                fluidBloodwood = new FluidStack(FluidRegistry.LAVA, 25);
            TapperManager.addStandardMapping(new ItemStack(NaturaNether.netherLog2, 1, 0), fluidBloodwood);
            TapperManager.addStandardMapping(new ItemStack(NaturaNether.netherLog2, 1, 15), fluidBloodwood);
            
            SawmillManager.addDoorRecipe(NaturaNether.darkwood_door, new ItemStack(NaturaNether.netherPlanks, 1, 2));
            SawmillManager.addDoorRecipe(NaturaNether.fusewood_door, new ItemStack(NaturaNether.netherPlanks, 1, 3));
        }
        if (pulseManager.isPulseLoaded(NaturaTools.PulseId))
        {
            // Ghostwood
            PulverizerManager.addRecycleRecipe(3000, new ItemStack(NaturaTools.ghostwoodBow), ItemMaterial.dustWood, 2);
            PulverizerManager.addRecycleRecipe(3000, new ItemStack(NaturaTools.ghostwoodAxe), ItemMaterial.dustWood, 2);
            PulverizerManager.addRecycleRecipe(3000, new ItemStack(NaturaTools.ghostwoodKama), ItemMaterial.dustWood, 2);
            PulverizerManager.addRecycleRecipe(3000, new ItemStack(NaturaTools.ghostwoodPickaxe), ItemMaterial.dustWood, 2);
            PulverizerManager.addRecycleRecipe(3000, new ItemStack(NaturaTools.ghostwoodShovel), ItemMaterial.dustWood, 2);
            PulverizerManager.addRecycleRecipe(3000, new ItemStack(NaturaTools.ghostwoodSword), ItemMaterial.dustWood, 2);
            // Bloodwood
            PulverizerManager.addRecycleRecipe(3000, new ItemStack(NaturaTools.bloodwoodBow), ItemMaterial.dustWood, 2);
            PulverizerManager.addRecycleRecipe(3000, new ItemStack(NaturaTools.bloodwoodAxe), ItemMaterial.dustWood, 2);
            PulverizerManager.addRecycleRecipe(3000, new ItemStack(NaturaTools.bloodwoodKama), ItemMaterial.dustWood, 2);
            PulverizerManager.addRecycleRecipe(3000, new ItemStack(NaturaTools.bloodwoodPickaxe), ItemMaterial.dustWood, 2);
            PulverizerManager.addRecycleRecipe(3000, new ItemStack(NaturaTools.bloodwoodShovel), ItemMaterial.dustWood, 2);
            PulverizerManager.addRecycleRecipe(3000, new ItemStack(NaturaTools.bloodwoodSword), ItemMaterial.dustWood, 2);
            // Darkwood
            PulverizerManager.addRecycleRecipe(3000, new ItemStack(NaturaTools.darkwoodBow), ItemMaterial.dustWood, 2);
            PulverizerManager.addRecycleRecipe(3000, new ItemStack(NaturaTools.darkwoodAxe), ItemMaterial.dustWood, 2);
            PulverizerManager.addRecycleRecipe(3000, new ItemStack(NaturaTools.darkwoodKama), ItemMaterial.dustWood, 2);
            PulverizerManager.addRecycleRecipe(3000, new ItemStack(NaturaTools.darkwoodPickaxe), ItemMaterial.dustWood, 2);
            PulverizerManager.addRecycleRecipe(3000, new ItemStack(NaturaTools.darkwoodShovel), ItemMaterial.dustWood, 2);
            PulverizerManager.addRecycleRecipe(3000, new ItemStack(NaturaTools.darkwoodSword), ItemMaterial.dustWood, 2);
            // Fusewood
            PulverizerManager.addRecycleRecipe(3000, new ItemStack(NaturaTools.fusewoodBow), ItemMaterial.dustWood, 2);
            PulverizerManager.addRecycleRecipe(3000, new ItemStack(NaturaTools.fusewoodAxe), ItemMaterial.dustWood, 2);
            PulverizerManager.addRecycleRecipe(3000, new ItemStack(NaturaTools.fusewoodKama), ItemMaterial.dustWood, 2);
            PulverizerManager.addRecycleRecipe(3000, new ItemStack(NaturaTools.fusewoodPickaxe), ItemMaterial.dustWood, 2);
            PulverizerManager.addRecycleRecipe(3000, new ItemStack(NaturaTools.fusewoodShovel), ItemMaterial.dustWood, 2);
            PulverizerManager.addRecycleRecipe(3000, new ItemStack(NaturaTools.fusewoodSword), ItemMaterial.dustWood, 2);
            // Quartz
            PulverizerManager.addRecycleRecipe(3000, new ItemStack(NaturaTools.netherquartzAxe), new ItemStack(Items.QUARTZ), 1);
            PulverizerManager.addRecycleRecipe(3000, new ItemStack(NaturaTools.netherquartzKama), new ItemStack(Items.QUARTZ), 1);
            PulverizerManager.addRecycleRecipe(3000, new ItemStack(NaturaTools.netherquartzPickaxe), new ItemStack(Items.QUARTZ), 1);
            PulverizerManager.addRecycleRecipe(3000, new ItemStack(NaturaTools.netherquartzShovel), new ItemStack(Items.QUARTZ), 1);
            PulverizerManager.addRecycleRecipe(3000, new ItemStack(NaturaTools.netherquartzSword), new ItemStack(Items.QUARTZ), 1);
            
            if (pulseManager.isPulseLoaded(NaturaCommons.PulseId))
            {
                for (int meta = 0; meta <= 3; meta++)
                    TransposerManager.addFillRecipe(400, new ItemStack(NaturaCommons.empty_bowls, 1, meta), new ItemStack(NaturaCommons.soups, 1, meta), new FluidStack(TFFluids.fluidMushroomStew, 250), true);
            }
            if (pulseManager.isPulseLoaded(NaturaEntities.PulseId) && pulseManager.isPulseLoaded(NaturaCommons.PulseId))
            {
                SawmillManager.addRecycleRecipe(4500, NaturaTools.impHelmetStack, NaturaCommons.impLeather, 2);
                SawmillManager.addRecycleRecipe(4500, NaturaTools.impChestplateStack, NaturaCommons.impLeather, 4);
                SawmillManager.addRecycleRecipe(4500, NaturaTools.impLeggingsStack, NaturaCommons.impLeather, 3);
                SawmillManager.addRecycleRecipe(4500, NaturaTools.impBootsStack, NaturaCommons.impLeather, 2);
            }
        }
    }
    
    @Override
    @Nonnull
    public ModIds getModId()
    {
        return ModIds.NATURA;
    }
}
