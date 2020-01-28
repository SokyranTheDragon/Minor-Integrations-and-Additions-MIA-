package com.github.sokyranthedragon.mia.integrations.natura;

import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.integrations.xu2.IExtraUtilsIntegration;
import com.progwml6.natura.decorative.NaturaDecorative;
import com.progwml6.natura.library.enums.WoodTypes;
import com.progwml6.natura.nether.NaturaNether;
import com.progwml6.natura.overworld.NaturaOverworld;
import com.progwml6.natura.shared.NaturaCommons;
import com.rwtema.extrautils2.api.machine.MachineRecipeRegistry;
import com.rwtema.extrautils2.api.machine.MachineSlotItem;
import com.rwtema.extrautils2.api.machine.XUMachineGenerators;
import com.rwtema.extrautils2.machine.EnergyBaseRecipe;
import com.rwtema.extrautils2.utils.datastructures.ItemRef;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.item.ItemStack;

import javax.annotation.Nullable;

import static com.progwml6.natura.Natura.pulseManager;

@MethodsReturnNonnullByDefault
class ExtraUtilsNaturaIntegration implements IExtraUtilsIntegration
{
    @Override
    public void addRecipes(@Nullable MachineSlotItem slimeGeneratorSecondary)
    {
        MachineRecipeRegistry pinkRecipes = XUMachineGenerators.PINK_GENERATOR.recipes_registry;
        
        if (pulseManager.isPulseLoaded(NaturaOverworld.PulseId))
        {
            pinkRecipes.addRecipe(new EnergyBaseRecipe.EnergyBaseItem(ItemRef.wrap(NaturaOverworld.cotton_seeds), 400, 40));
            
            pinkRecipes.addRecipe(new EnergyBaseRecipe.EnergyBaseItem(ItemRef.wrap(new ItemStack(NaturaOverworld.overworldLeaves2, 1, 3)), 400, 40));
            pinkRecipes.addRecipe(new EnergyBaseRecipe.EnergyBaseItem(ItemRef.wrap(new ItemStack(NaturaOverworld.overworldSapling2, 1, 3)), 400, 40));
            
            pinkRecipes.addRecipe(new EnergyBaseRecipe.EnergyBaseItem(ItemRef.wrap(new ItemStack(NaturaOverworld.overworldPlanks, 1, WoodTypes.EUCALYPTUS.getPlankMeta())), 400, 40));
            if (pulseManager.isPulseLoaded(NaturaCommons.PulseId))
                pinkRecipes.addRecipe(new EnergyBaseRecipe.EnergyBaseItem(ItemRef.wrap(new ItemStack(NaturaCommons.sticks, 1, WoodTypes.EUCALYPTUS.getStickMeta())), 400, 40));
            pinkRecipes.addRecipe(new EnergyBaseRecipe.EnergyBaseItem(ItemRef.wrap(NaturaOverworld.overworldSlab2), 400, 40));
            pinkRecipes.addRecipe(new EnergyBaseRecipe.EnergyBaseItem(ItemRef.wrap(NaturaOverworld.overworldStairsEucalyptus), 400, 40));
            pinkRecipes.addRecipe(new EnergyBaseRecipe.EnergyBaseItem(ItemRef.wrap(NaturaOverworld.eucalyptusDoor), 400, 40));
            
            if (pulseManager.isPulseLoaded(NaturaDecorative.PulseId))
            {
                pinkRecipes.addRecipe(new EnergyBaseRecipe.EnergyBaseItem(ItemRef.wrap(NaturaDecorative.buttons[WoodTypes.EUCALYPTUS.ordinal()]), 400, 40));
                pinkRecipes.addRecipe(new EnergyBaseRecipe.EnergyBaseItem(ItemRef.wrap(NaturaDecorative.pressurePlates[WoodTypes.EUCALYPTUS.ordinal()]), 400, 40));
                pinkRecipes.addRecipe(new EnergyBaseRecipe.EnergyBaseItem(ItemRef.wrap(NaturaDecorative.trapDoors[WoodTypes.EUCALYPTUS.ordinal()]), 400, 40));
                pinkRecipes.addRecipe(new EnergyBaseRecipe.EnergyBaseItem(ItemRef.wrap(NaturaDecorative.fences[WoodTypes.EUCALYPTUS.ordinal()]), 400, 40));
                pinkRecipes.addRecipe(new EnergyBaseRecipe.EnergyBaseItem(ItemRef.wrap(NaturaDecorative.fenceGates[WoodTypes.EUCALYPTUS.ordinal()]), 400, 40));
                pinkRecipes.addRecipe(new EnergyBaseRecipe.EnergyBaseItem(ItemRef.wrap(NaturaDecorative.fenceGates[WoodTypes.EUCALYPTUS.ordinal()]), 400, 40));
                pinkRecipes.addRecipe(new EnergyBaseRecipe.EnergyBaseItem(ItemRef.wrap(new ItemStack(NaturaDecorative.overworldBookshelves, 1, WoodTypes.EUCALYPTUS.getPlankMeta())), 400, 40));
                pinkRecipes.addRecipe(new EnergyBaseRecipe.EnergyBaseItem(ItemRef.wrap(new ItemStack(NaturaDecorative.overworldWorkbenches, 1, WoodTypes.EUCALYPTUS.getPlankMeta())), 400, 40));
            }
        }
        if (pulseManager.isPulseLoaded(NaturaNether.PulseId))
            pinkRecipes.addRecipe(new EnergyBaseRecipe.EnergyBaseItem(ItemRef.wrap(NaturaNether.netherBerryBushBlightberry), 400, 40));
    }
    
    @Override
    public ModIds getModId()
    {
        return ModIds.NATURA;
    }
}
