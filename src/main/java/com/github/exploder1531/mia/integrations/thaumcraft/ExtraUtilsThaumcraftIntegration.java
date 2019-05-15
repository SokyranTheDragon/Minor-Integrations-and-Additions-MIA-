package com.github.exploder1531.mia.integrations.thaumcraft;

import com.github.exploder1531.mia.integrations.ModIds;
import com.github.exploder1531.mia.integrations.xu2.IExtraUtilsIntegration;
import com.rwtema.extrautils2.api.machine.MachineSlotItem;
import com.rwtema.extrautils2.api.machine.XUMachineGenerators;
import com.rwtema.extrautils2.machine.EnergyBaseRecipe;
import com.rwtema.extrautils2.utils.datastructures.ItemRef;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import thaumcraft.api.blocks.BlocksTC;
import thaumcraft.api.items.ItemsTC;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

class ExtraUtilsThaumcraftIntegration implements IExtraUtilsIntegration
{
    @Override
    public void addRecipes(@Nullable MachineSlotItem slimeGeneratorSecondary)
    {
        // Death generator
        XUMachineGenerators.DEATH_GENERATOR.recipes_registry.addRecipe(new EnergyBaseRecipe.EnergyBaseItem(ItemRef.wrap(ItemsTC.brain), 32_000, 60));
        XUMachineGenerators.DEATH_GENERATOR.recipes_registry.addRecipe(new EnergyBaseRecipe.EnergyBaseItem(ItemRef.wrap(BlocksTC.fleshBlock), 72_000, 20));
        
        FluidStack fluid = FluidRegistry.getFluidStack("pyrotheum", 50);
        if (fluid != null)
        {
            ItemStack liquidDeathBucket = FluidUtil.getFilledBucket(fluid);
            if (!liquidDeathBucket.isEmpty())
                XUMachineGenerators.DEATH_GENERATOR.recipes_registry.addRecipe(new EnergyBaseRecipe.EnergyBaseItem(ItemRef.wrap(liquidDeathBucket), 256_000, 160));
        }
        
        // Pink generator
        XUMachineGenerators.PINK_GENERATOR.recipes_registry.addRecipe(new EnergyBaseRecipe.EnergyBaseItem(ItemRef.wrap(BlocksTC.nitor.get(EnumDyeColor.PINK)), 500, 50));
        XUMachineGenerators.PINK_GENERATOR.recipes_registry.addRecipe(new EnergyBaseRecipe.EnergyBaseItem(ItemRef.wrap(BlocksTC.banners.get(EnumDyeColor.PINK)), 500, 50));
        XUMachineGenerators.PINK_GENERATOR.recipes_registry.addRecipe(new EnergyBaseRecipe.EnergyBaseItem(ItemRef.wrap(BlocksTC.candles.get(EnumDyeColor.PINK)), 500, 50));
        
        // Explosion generator
        XUMachineGenerators.TNT_GENERATOR.recipes_registry.addRecipe(new EnergyBaseRecipe.EnergyBaseItem(ItemRef.wrap(ItemsTC.alumentum), 512_000, 160));
    }
    
    @Nonnull
    @Override
    public String getModId()
    {
        return ModIds.THAUMCRAFT;
    }
}
