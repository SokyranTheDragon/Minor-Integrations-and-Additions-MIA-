package com.github.exploder1531.mia.integrations.tconstruct;

import com.github.exploder1531.mia.Mia;
import com.github.exploder1531.mia.integrations.ModIds;
import com.github.exploder1531.mia.integrations.base.IBaseMod;
import com.github.exploder1531.mia.integrations.base.IModIntegration;
import com.google.common.collect.Lists;
import net.minecraft.init.Items;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import slimeknights.mantle.util.RecipeMatch;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.materials.Material;
import slimeknights.tconstruct.library.smeltery.MeltingRecipe;

import java.util.List;
import java.util.function.BiConsumer;

import static com.github.exploder1531.mia.config.TConstructConfiguration.*;

public class TinkersConstruct implements IBaseMod
{
    private List<ITConstructIntegration> modIntegrations = Lists.newArrayList();
    
    @Override
    public void addIntegration(IModIntegration integration)
    {
        if (!externalIntegrationsEnabled)
            return;
        
        if (integration instanceof ITConstructIntegration)
            modIntegrations.add((ITConstructIntegration) integration);
        
        Mia.LOGGER.warn("Incorrect TConstruct integration with id of " + integration.getModId() + ": " + integration.toString());
    }
    
    @Override
    public void register(BiConsumer<String, IModIntegration> modIntegration)
    {
        if (enableXu2Integration)
            modIntegration.accept(ModIds.EXTRA_UTILITIES, new ExtraUtilsTConstructIntegration());
        if (enableJerIntegration)
            modIntegration.accept(ModIds.JER, new JerTConstructIntegration());
        if (enableTeIntegration)
            modIntegration.accept(ModIds.THERMAL_EXPANSION, new ThermalExpansionTConstructIntegration());
        modIntegration.accept(ModIds.HATCHERY, new HatcheryTConstructIntegration(enableHatcheryIntegration));
    }
    
    @Override
    public void init(FMLInitializationEvent event)
    {
        if (tconstructAdditionsEnabled)
        {
            Fluid fluid = FluidRegistry.getFluid("iron");
            
            if (fluid != null)
            {
                TinkerRegistry.registerMelting(new MeltingRecipe(RecipeMatch.of(Items.CHAINMAIL_HELMET, Material.VALUE_Nugget * 5 * 4), fluid));
                TinkerRegistry.registerMelting(new MeltingRecipe(RecipeMatch.of(Items.CHAINMAIL_CHESTPLATE, Material.VALUE_Nugget * 8 * 4), fluid));
                TinkerRegistry.registerMelting(new MeltingRecipe(RecipeMatch.of(Items.CHAINMAIL_LEGGINGS, Material.VALUE_Nugget * 7 * 4), fluid));
                TinkerRegistry.registerMelting(new MeltingRecipe(RecipeMatch.of(Items.CHAINMAIL_BOOTS, Material.VALUE_Nugget * 4 * 4), fluid));
            }
        }
        
        for (ITConstructIntegration integration : modIntegrations)
            integration.init(event);
    }
}
