package com.github.sokyranthedragon.mia.integrations.thermalfoundation;

import cofh.thermalfoundation.init.TFEquipment;
import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.integrations.tconstruct.ITConstructIntegration;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import slimeknights.mantle.util.RecipeMatch;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.materials.Material;
import slimeknights.tconstruct.library.smeltery.MeltingRecipe;

@MethodsReturnNonnullByDefault
class TConstructTFIntegration implements ITConstructIntegration
{
    @Override
    public void init(FMLInitializationEvent event)
    {
        // Horse armors to melting
        for (TFEquipment.HorseArmor armor : TFEquipment.HorseArmor.values())
        {
            if (!armor.ingot.startsWith("ingot"))
                continue;
            
            String suffix = armor.ingot.substring(5);
            Fluid fluid = FluidRegistry.getFluid(suffix.toLowerCase());
            
            if (fluid != null)
                TinkerRegistry.registerMelting(new MeltingRecipe(RecipeMatch.of(armor.armor, Material.VALUE_Ingot * 4), fluid));
        }
    }
    
    @Override
    public ModIds getModId()
    {
        return ModIds.THERMAL_FOUNDATION;
    }
}
