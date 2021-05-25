package com.github.sokyranthedragon.mia.integrations.charm;

import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.integrations.xu2.IExtraUtilsIntegration;
import com.rwtema.extrautils2.api.machine.MachineSlotItem;
import com.rwtema.extrautils2.api.machine.XUMachineCrusher;
import com.rwtema.extrautils2.api.machine.XUMachineGenerators;
import com.rwtema.extrautils2.machine.EnergyBaseRecipe;
import com.rwtema.extrautils2.utils.datastructures.ItemRef;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import svenhjol.charm.Charm;
import svenhjol.charm.automation.feature.GunpowderBlock;
import svenhjol.charm.crafting.feature.EnderPearlBlock;
import svenhjol.charm.crafting.feature.RottenFleshBlock;
import svenhjol.charm.crafting.feature.SmoothGlowstone;

import javax.annotation.Nullable;

class ExtraUtilsCharmIntegration implements IExtraUtilsIntegration
{
    @Override
    public void addRecipes(@Nullable MachineSlotItem slimeGeneratorSecondary)
    {
        if (Charm.hasFeature(GunpowderBlock.class))
            XUMachineGenerators.TNT_GENERATOR.recipes_registry.addRecipe(new EnergyBaseRecipe.EnergyBaseItem(ItemRef.wrap(GunpowderBlock.block), 64_000 * 9, 160));
        
        if (Charm.hasFeature(RottenFleshBlock.class))
            XUMachineGenerators.DEATH_GENERATOR.recipes_registry.addRecipe(new EnergyBaseRecipe.EnergyBaseItem(ItemRef.wrap(RottenFleshBlock.block), 8_000 * 9, 20));
        
        if (Charm.hasFeature(EnderPearlBlock.class))
            XUMachineGenerators.ENDER_GENERATOR.recipes_registry.addRecipe(new EnergyBaseRecipe.EnergyBaseItem(ItemRef.wrap(EnderPearlBlock.block), 64_000 * 9, 40));
        
        if (Charm.hasFeature(SmoothGlowstone.class))
            XUMachineCrusher.addRecipe(new ItemStack(SmoothGlowstone.block), new ItemStack(Items.GLOWSTONE_DUST, 4));
    }
    
    @NotNull
    @Override
    public ModIds getModId()
    {
        return ModIds.CHARM;
    }
}
