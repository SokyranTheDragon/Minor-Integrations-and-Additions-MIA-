package com.github.exploder1531.mia.integrations.botania;

import cofh.thermalexpansion.util.managers.device.FactorizerManager;
import cofh.thermalexpansion.util.managers.device.TapperManager;
import cofh.thermalexpansion.util.managers.machine.PulverizerManager;
import cofh.thermalexpansion.util.managers.machine.SawmillManager;
import cofh.thermalfoundation.init.TFFluids;
import cofh.thermalfoundation.item.ItemMaterial;
import com.github.exploder1531.mia.Mia;
import com.github.exploder1531.mia.integrations.ModIds;
import com.github.exploder1531.mia.integrations.thermalexpansion.IThermalExpansionIntegration;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import vazkii.botania.common.block.ModBlocks;
import vazkii.botania.common.block.ModFluffBlocks;
import vazkii.botania.common.item.ModItems;

import javax.annotation.Nonnull;
import java.lang.reflect.Field;

class ThermalExpansionBotaniaIntegration implements IThermalExpansionIntegration
{
    @Override
    public void addRecipes()
    {
        // Factorizer
        
        // Quartz
        FactorizerManager.addDefaultRecipe(new ItemStack(ModItems.quartz, 1, 0), new ItemStack(ModFluffBlocks.darkQuartz), 4);
        FactorizerManager.addDefaultRecipe(new ItemStack(ModItems.quartz, 1, 1), new ItemStack(ModFluffBlocks.manaQuartz), 4);
        FactorizerManager.addDefaultRecipe(new ItemStack(ModItems.quartz, 1, 2), new ItemStack(ModFluffBlocks.blazeQuartz), 4);
        FactorizerManager.addDefaultRecipe(new ItemStack(ModItems.quartz, 1, 3), new ItemStack(ModFluffBlocks.lavenderQuartz), 4);
        FactorizerManager.addDefaultRecipe(new ItemStack(ModItems.quartz, 1, 4), new ItemStack(ModFluffBlocks.redQuartz), 4);
        FactorizerManager.addDefaultRecipe(new ItemStack(ModItems.quartz, 1, 5), new ItemStack(ModFluffBlocks.elfQuartz), 4);
        FactorizerManager.addDefaultRecipe(new ItemStack(ModItems.quartz, 1, 6), new ItemStack(ModFluffBlocks.sunnyQuartz), 4);
        // Compressed ingot-like materials
        FactorizerManager.addDefaultRecipe(new ItemStack(ModItems.manaResource, 1, 0), new ItemStack(ModBlocks.storage, 1, 0));
        FactorizerManager.addDefaultRecipe(new ItemStack(ModItems.manaResource, 1, 4), new ItemStack(ModBlocks.storage, 1, 1));
        FactorizerManager.addDefaultRecipe(new ItemStack(ModItems.manaResource, 1, 7), new ItemStack(ModBlocks.storage, 1, 2));
        FactorizerManager.addDefaultRecipe(new ItemStack(ModItems.manaResource, 1, 2), new ItemStack(ModBlocks.storage, 1, 3));
        FactorizerManager.addDefaultRecipe(new ItemStack(ModItems.manaResource, 1, 9), new ItemStack(ModBlocks.storage, 1, 4));
        // Compressed petals
        for (int i = 0; i < ItemDye.DYE_COLORS.length; i++)
            FactorizerManager.addDefaultRecipe(new ItemStack(ModItems.petal, 1, i), new ItemStack(ModBlocks.petalBlock, 1, i));
        
        
        // Pulverizer
        PulverizerManager.addRecipe(3_000, new ItemStack(ModFluffBlocks.darkQuartz), new ItemStack(ModItems.quartz, 4, 0));
        PulverizerManager.addRecipe(3_000, new ItemStack(ModFluffBlocks.darkQuartz, 1, 1), new ItemStack(ModItems.quartz, 4, 0));
        PulverizerManager.addRecipe(3_000, new ItemStack(ModFluffBlocks.darkQuartz, 1, 2), new ItemStack(ModItems.quartz, 4, 0));
        PulverizerManager.addRecipe(3_000, new ItemStack(ModFluffBlocks.darkQuartzSlab), new ItemStack(ModItems.quartz, 2, 0));
        PulverizerManager.addRecipe(3_000, new ItemStack(ModFluffBlocks.darkQuartzStairs), new ItemStack(ModItems.quartz, 3, 0));
        
        PulverizerManager.addRecipe(3_000, new ItemStack(ModFluffBlocks.manaQuartz), new ItemStack(ModItems.quartz, 4, 1));
        PulverizerManager.addRecipe(3_000, new ItemStack(ModFluffBlocks.manaQuartz, 1, 1), new ItemStack(ModItems.quartz, 4, 1));
        PulverizerManager.addRecipe(3_000, new ItemStack(ModFluffBlocks.manaQuartz, 1, 2), new ItemStack(ModItems.quartz, 4, 1));
        PulverizerManager.addRecipe(3_000, new ItemStack(ModFluffBlocks.manaQuartzSlab), new ItemStack(ModItems.quartz, 2, 1));
        PulverizerManager.addRecipe(3_000, new ItemStack(ModFluffBlocks.manaQuartzStairs), new ItemStack(ModItems.quartz, 3, 1));
        
        PulverizerManager.addRecipe(3_000, new ItemStack(ModFluffBlocks.blazeQuartz), new ItemStack(ModItems.quartz, 4, 2));
        PulverizerManager.addRecipe(3_000, new ItemStack(ModFluffBlocks.blazeQuartz, 1, 1), new ItemStack(ModItems.quartz, 4, 2));
        PulverizerManager.addRecipe(3_000, new ItemStack(ModFluffBlocks.blazeQuartz, 1, 2), new ItemStack(ModItems.quartz, 4, 2));
        PulverizerManager.addRecipe(3_000, new ItemStack(ModFluffBlocks.blazeQuartzSlab), new ItemStack(ModItems.quartz, 2, 2));
        PulverizerManager.addRecipe(3_000, new ItemStack(ModFluffBlocks.blazeQuartzStairs), new ItemStack(ModItems.quartz, 3, 2));
        
        PulverizerManager.addRecipe(3_000, new ItemStack(ModFluffBlocks.lavenderQuartz), new ItemStack(ModItems.quartz, 4, 3));
        PulverizerManager.addRecipe(3_000, new ItemStack(ModFluffBlocks.lavenderQuartz, 1, 1), new ItemStack(ModItems.quartz, 4, 3));
        PulverizerManager.addRecipe(3_000, new ItemStack(ModFluffBlocks.lavenderQuartz, 1, 2), new ItemStack(ModItems.quartz, 4, 3));
        PulverizerManager.addRecipe(3_000, new ItemStack(ModFluffBlocks.lavenderQuartzSlab), new ItemStack(ModItems.quartz, 2, 3));
        PulverizerManager.addRecipe(3_000, new ItemStack(ModFluffBlocks.lavenderQuartzStairs), new ItemStack(ModItems.quartz, 3, 3));
        
        PulverizerManager.addRecipe(3_000, new ItemStack(ModFluffBlocks.redQuartz), new ItemStack(ModItems.quartz, 4, 4));
        PulverizerManager.addRecipe(3_000, new ItemStack(ModFluffBlocks.redQuartz, 1, 1), new ItemStack(ModItems.quartz, 4, 4));
        PulverizerManager.addRecipe(3_000, new ItemStack(ModFluffBlocks.redQuartz, 1, 2), new ItemStack(ModItems.quartz, 4, 4));
        PulverizerManager.addRecipe(3_000, new ItemStack(ModFluffBlocks.redQuartzSlab), new ItemStack(ModItems.quartz, 2, 4));
        PulverizerManager.addRecipe(3_000, new ItemStack(ModFluffBlocks.redQuartzStairs), new ItemStack(ModItems.quartz, 3, 4));
        
        PulverizerManager.addRecipe(3_000, new ItemStack(ModFluffBlocks.elfQuartz), new ItemStack(ModItems.quartz, 4, 5));
        PulverizerManager.addRecipe(3_000, new ItemStack(ModFluffBlocks.elfQuartz, 1, 1), new ItemStack(ModItems.quartz, 4, 5));
        PulverizerManager.addRecipe(3_000, new ItemStack(ModFluffBlocks.elfQuartz, 1, 2), new ItemStack(ModItems.quartz, 4, 5));
        PulverizerManager.addRecipe(3_000, new ItemStack(ModFluffBlocks.elfQuartzSlab), new ItemStack(ModItems.quartz, 2, 5));
        PulverizerManager.addRecipe(3_000, new ItemStack(ModFluffBlocks.elfQuartzStairs), new ItemStack(ModItems.quartz, 3, 5));
        
        PulverizerManager.addRecipe(3_000, new ItemStack(ModFluffBlocks.sunnyQuartz), new ItemStack(ModItems.quartz, 4, 6));
        PulverizerManager.addRecipe(3_000, new ItemStack(ModFluffBlocks.sunnyQuartz, 1, 1), new ItemStack(ModItems.quartz, 4, 6));
        PulverizerManager.addRecipe(3_000, new ItemStack(ModFluffBlocks.sunnyQuartz, 1, 2), new ItemStack(ModItems.quartz, 4, 6));
        PulverizerManager.addRecipe(3_000, new ItemStack(ModFluffBlocks.sunnyQuartzSlab), new ItemStack(ModItems.quartz, 2, 6));
        PulverizerManager.addRecipe(3_000, new ItemStack(ModFluffBlocks.sunnyQuartzStairs), new ItemStack(ModItems.quartz, 3, 6));
        
        
        // Sawmill log multiplier
        float logMultiplier = 1.5f;
        
        try
        {
            Field multiplier = SawmillManager.class.getDeclaredField("logMultiplier");
            multiplier.setAccessible(true);
            logMultiplier = multiplier.getFloat(null);
        } catch (NoSuchFieldException | IllegalAccessException e)
        {
            Mia.LOGGER.error("Could not check the logMultiplier value from ThermalExpansion");
        }
        
        // Sawmill and upgrade
        TapperManager.addStandardMapping(new ItemStack(ModBlocks.livingwood), new FluidStack(TFFluids.fluidExperience, 25));
        TapperManager.addStandardMapping(new ItemStack(ModBlocks.dreamwood), new FluidStack(TFFluids.fluidExperience, 75));
        
        SawmillManager.addRecipe(10_000, new ItemStack(ModBlocks.livingwood, 1, 0), new ItemStack(ModBlocks.livingwood, (int) (4f * logMultiplier), 1), ItemMaterial.dustWood, 100);
        SawmillManager.addRecipe(25_000, new ItemStack(ModBlocks.dreamwood, 1, 0), new ItemStack(ModBlocks.dreamwood, (int) (4f * logMultiplier), 1), ItemMaterial.dustWood, 100);
        
        SawmillManager.addRecipe(10_000, new ItemStack(ModFluffBlocks.livingwoodPlankStairs, 2, 0), new ItemStack(ModBlocks.livingwood, 1, 1), ItemMaterial.dustWood, 50);
        SawmillManager.addRecipe(10_000, new ItemStack(ModFluffBlocks.livingwoodStairs, 2, 0), new ItemStack(ModBlocks.livingwood, 1), ItemMaterial.dustWood, 50);
        SawmillManager.addRecipe(25_000, new ItemStack(ModFluffBlocks.dreamwoodPlankStairs, 2, 0), new ItemStack(ModBlocks.dreamwood, 1, 1), ItemMaterial.dustWood, 50);
        SawmillManager.addRecipe(25_000, new ItemStack(ModFluffBlocks.dreamwoodStairs, 2, 0), new ItemStack(ModBlocks.dreamwood, 1), ItemMaterial.dustWood, 50);
        SawmillManager.addRecipe(25_000, new ItemStack(ModFluffBlocks.shimmerwoodPlankStairs, 2, 0), new ItemStack(ModBlocks.shimmerwoodPlanks, 1, 0), ItemMaterial.dustWood, 50);
        
        SawmillManager.addRecipe(10_000, new ItemStack(ModFluffBlocks.livingwoodWall, 2), new ItemStack(ModBlocks.livingwood, 1), ItemMaterial.dustWood, 25);
        SawmillManager.addRecipe(25_000, new ItemStack(ModFluffBlocks.dreamwoodWall, 2), new ItemStack(ModBlocks.dreamwood, 1), ItemMaterial.dustWood, 25);
    }
    
    @Nonnull
    @Override
    public String getModId()
    {
        return ModIds.BOTANIA;
    }
}
