package com.github.sokyranthedragon.mia.integrations.futuremc;

import cofh.thermalexpansion.util.managers.machine.*;
import cofh.thermalfoundation.item.ItemMaterial;
import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.integrations.thermalexpansion.IThermalExpansionIntegration;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import thedarkcolour.futuremc.registry.FBlocks;
import thedarkcolour.futuremc.registry.FItems;

import javax.annotation.Nonnull;

class ThermalExpansionFutureMcIntegration implements IThermalExpansionIntegration
{
    @Override
    public void addRecipes()
    {
        // Glacial Precipitator
        PrecipitatorManager.addRecipe(1_600 * 9, new ItemStack(FBlocks.INSTANCE.getBLUE_ICE()), new FluidStack(FluidRegistry.WATER, 10_000));
        
        // Phytogenic Insolator
        InsolatorManager.addDefaultRecipe(new ItemStack(FItems.INSTANCE.getBAMBOO()), new ItemStack(FItems.INSTANCE.getBAMBOO(), 3), ItemStack.EMPTY, 0);
        InsolatorManager.addDefaultRecipe(new ItemStack(FBlocks.INSTANCE.getLILY_OF_THE_VALLEY()), new ItemStack(FBlocks.INSTANCE.getLILY_OF_THE_VALLEY(), 2), ItemStack.EMPTY, 0);
        InsolatorManager.addDefaultRecipe(new ItemStack(FBlocks.INSTANCE.getCORNFLOWER()), new ItemStack(FBlocks.INSTANCE.getCORNFLOWER(), 2), ItemStack.EMPTY, 0);
        InsolatorManager.addDefaultRecipe(new ItemStack(FBlocks.INSTANCE.getWITHER_ROSE()), new ItemStack(FBlocks.INSTANCE.getWITHER_ROSE(), 2), ItemStack.EMPTY, 0);
        
        // Arcane Enscroller
        EnchanterManager.addDefaultEnchantmentRecipe(new ItemStack(FItems.INSTANCE.getBAMBOO(), 64), "futuremc:impaling", 0);
        EnchanterManager.addDefaultEnchantmentRecipe(new ItemStack(FBlocks.INSTANCE.getWITHER_ROSE(), 32), "futuremc:channeling", 3);
        EnchanterManager.addDefaultEnchantmentRecipe(new ItemStack(FBlocks.INSTANCE.getHONEY_BLOCK(), 8), "futuremc:loyalty", 2);
        EnchanterManager.addDefaultEnchantmentRecipe(new ItemStack(FBlocks.INSTANCE.getBLUE_ICE(), 1), "futuremc:riptide", 3);
    
        // Pulverizer
        PulverizerManager.addRecipe(2_000, new ItemStack(FBlocks.INSTANCE.getLILY_OF_THE_VALLEY()), new ItemStack(FItems.INSTANCE.getDYES(), 4, 0));
        PulverizerManager.addRecipe(2_000, new ItemStack(FBlocks.INSTANCE.getCORNFLOWER()), new ItemStack(FItems.INSTANCE.getDYES(), 4, 1));
        PulverizerManager.addRecipe(2_000, new ItemStack(FBlocks.INSTANCE.getWITHER_ROSE()), new ItemStack(FItems.INSTANCE.getDYES(), 4, 2));
    
        PulverizerManager.addRecipe(3_000, new ItemStack(FBlocks.INSTANCE.getBRICK_WALL()), new ItemStack(Items.BRICK, 4));
        PulverizerManager.addRecipe(3_000, new ItemStack(FBlocks.INSTANCE.getNETHER_BRICK_WALL()), new ItemStack(Items.NETHERBRICK, 4));
        PulverizerManager.addRecipe(3_000, new ItemStack(FBlocks.INSTANCE.getNETHER_BRICK_WALL()), new ItemStack(Items.BRICK, 4));
        PulverizerManager.addRecipe(3_000, new ItemStack(FBlocks.INSTANCE.getSANDSTONE_WALL()), new ItemStack(Blocks.SAND, 2, 0), ItemMaterial.dustNiter, 40);
        PulverizerManager.addRecipe(3_000, new ItemStack(FBlocks.INSTANCE.getSANDSTONE_WALL()), new ItemStack(Blocks.SAND, 2, 1), ItemMaterial.dustNiter, 40);
    
        PulverizerManager.addRecipe(9_000, new ItemStack(FItems.INSTANCE.getNETHERITE_SWORD()), new ItemStack(FItems.INSTANCE.getNETHERITE_SCRAP(), 2));
        PulverizerManager.addRecipe(9_000, new ItemStack(FItems.INSTANCE.getNETHERITE_AXE()), new ItemStack(FItems.INSTANCE.getNETHERITE_SCRAP(), 2));
        PulverizerManager.addRecipe(9_000, new ItemStack(FItems.INSTANCE.getNETHERITE_PICKAXE()), new ItemStack(FItems.INSTANCE.getNETHERITE_SCRAP(), 2));
        PulverizerManager.addRecipe(9_000, new ItemStack(FItems.INSTANCE.getNETHERITE_SHOVEL()), new ItemStack(FItems.INSTANCE.getNETHERITE_SCRAP(), 2));
        PulverizerManager.addRecipe(9_000, new ItemStack(FItems.INSTANCE.getNETHERITE_HOE()), new ItemStack(FItems.INSTANCE.getNETHERITE_SCRAP(), 2));
        PulverizerManager.addRecipe(9_000, new ItemStack(FItems.INSTANCE.getNETHERITE_HELMET()), new ItemStack(FItems.INSTANCE.getNETHERITE_SCRAP(), 2));
        PulverizerManager.addRecipe(9_000, new ItemStack(FItems.INSTANCE.getNETHERITE_CHESTPLATE()), new ItemStack(FItems.INSTANCE.getNETHERITE_SCRAP(), 2));
        PulverizerManager.addRecipe(9_000, new ItemStack(FItems.INSTANCE.getNETHERITE_LEGGINGS()), new ItemStack(FItems.INSTANCE.getNETHERITE_SCRAP(), 2));
        PulverizerManager.addRecipe(9_000, new ItemStack(FItems.INSTANCE.getNETHERITE_BOOTS()), new ItemStack(FItems.INSTANCE.getNETHERITE_SCRAP(), 2));
        
        // Sawmill
        SawmillManager.addRecipe(2_000, new ItemStack(FBlocks.INSTANCE.getSPRUCE_TRAPDOOR()), new ItemStack(Blocks.PLANKS, 1, 1), ItemMaterial.dustWood, 75);
        SawmillManager.addRecipe(2_000, new ItemStack(FBlocks.INSTANCE.getBIRCH_TRAPDOOR()), new ItemStack(Blocks.PLANKS, 1, 2), ItemMaterial.dustWood, 75);
        SawmillManager.addRecipe(2_000, new ItemStack(FBlocks.INSTANCE.getJUNGLE_TRAPDOOR()), new ItemStack(Blocks.PLANKS, 1, 3), ItemMaterial.dustWood, 75);
        SawmillManager.addRecipe(2_000, new ItemStack(FBlocks.INSTANCE.getACACIA_TRAPDOOR()), new ItemStack(Blocks.PLANKS, 1, 4), ItemMaterial.dustWood, 75);
        SawmillManager.addRecipe(2_000, new ItemStack(FBlocks.INSTANCE.getDARK_OAK_TRAPDOOR()), new ItemStack(Blocks.PLANKS, 1, 5), ItemMaterial.dustWood, 75);
        
        // Induction Smelter
        SmelterManager.addRecycleRecipe(2_000, new ItemStack(FBlocks.INSTANCE.getCHAIN()), new ItemStack(Items.IRON_NUGGET), 11);
        SmelterManager.addRecycleRecipe(2_000, new ItemStack(FBlocks.INSTANCE.getLANTERN()), new ItemStack(Items.IRON_NUGGET), 8);
        SmelterManager.addRecycleRecipe(2_000, new ItemStack(FBlocks.INSTANCE.getSOUL_FIRE_LANTERN()), new ItemStack(Items.IRON_NUGGET), 8);
        SmelterManager.addRecycleRecipe(2_000, new ItemStack(FBlocks.INSTANCE.getSMITHING_TABLE()), new ItemStack(Items.IRON_INGOT), 2);
        SmelterManager.addRecycleRecipe(2_000, new ItemStack(FBlocks.INSTANCE.getSTONECUTTER()), new ItemStack(Items.IRON_INGOT), 1);
        SmelterManager.addRecycleRecipe(2_000, new ItemStack(FBlocks.BLAST_FURNACE), new ItemStack(Items.IRON_INGOT), 5);
    }
    
    @Nonnull
    @Override
    public ModIds getModId()
    {
        return ModIds.FUTURE_MC;
    }
}
