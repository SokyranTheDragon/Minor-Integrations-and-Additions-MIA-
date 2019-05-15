package com.github.exploder1531.mia.integrations.thermalfoundation;

import com.github.exploder1531.mia.integrations.ModIds;
import com.github.exploder1531.mia.integrations.tconstruct.ITConstructIntegration;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

import javax.annotation.Nonnull;

class TConstructTFIntegration implements ITConstructIntegration
{
    @Override
    public void init(FMLInitializationEvent event)
    {
//        for (TFEquipment.HorseArmor armor : TFEquipment.HorseArmor.values())
//        {
//            String suffix = armor.ingot.substring(5);
//            Fluid fluid = FluidRegistry.getFluid(suffix.toLowerCase());
//
//            if (fluid != null)
//                TinkerRegistry.registerMelting(new MeltingRecipe(RecipeMatch.of(armor.armor, Material.VALUE_Ingot * 4), fluid));
//        }
    }
    
    @Nonnull
    @Override
    public String getModId()
    {
        return ModIds.TINKERS_CONSTRUCT;
    }
}
