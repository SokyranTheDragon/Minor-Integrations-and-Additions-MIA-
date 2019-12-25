package com.github.exploder1531.mia.integrations.xu2;

import cofh.thermalfoundation.item.ItemMaterial;
import com.github.exploder1531.mia.Mia;
import com.github.exploder1531.mia.integrations.ModIds;
import com.github.exploder1531.mia.integrations.ModLoadStatus;
import com.github.exploder1531.mia.integrations.base.IBaseMod;
import com.github.exploder1531.mia.integrations.base.IModIntegration;
import com.google.common.collect.Lists;
import com.rwtema.extrautils2.api.machine.MachineSlotItem;
import com.rwtema.extrautils2.api.machine.RecipeBuilder;
import com.rwtema.extrautils2.api.machine.XUMachineCrusher;
import com.rwtema.extrautils2.api.machine.XUMachineGenerators;
import com.rwtema.extrautils2.machine.EnergyBaseRecipe;
import com.rwtema.extrautils2.machine.MachineInit;
import com.rwtema.extrautils2.utils.datastructures.ItemRef;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

import java.lang.reflect.Field;
import java.util.List;
import java.util.function.BiConsumer;

import static com.github.exploder1531.mia.config.Xu2Configuration.*;

public class ExtraUtilities2 implements IBaseMod
{
    private final List<IExtraUtilsIntegration> modIntegrations = Lists.newLinkedList();
    
    @Override
    public void register(BiConsumer<String, IModIntegration> modIntegration)
    {
        if (ModLoadStatus.thermalExpansionLoaded)
            modIntegration.accept(ModIds.THERMAL_EXPANSION, new ThermalExpansionExtraUtilsIntegration());
        if (ModLoadStatus.hatcheryLoaded)
            modIntegration.accept(ModIds.HATCHERY, new HatcheryExtraUtilsIntegration(enableHatcheryIntegration));
        if (ModLoadStatus.quarkLoaded)
            modIntegration.accept(ModIds.QUARK, new QuarkExtraUtilsIntegration());
    }
    
    @Override
    public void addIntegration(IModIntegration integration)
    {
        if (!externalIntegrationsEnabled)
            return;
        
        if (integration instanceof IExtraUtilsIntegration)
        {
            modIntegrations.add((IExtraUtilsIntegration) integration);
            return;
        }
        
        Mia.LOGGER.warn("Incorrect XU2 integration with id of " + integration.getModId() + ": " + integration.toString());
    }
    
    @Override
    public void init(FMLInitializationEvent event)
    {
        if (xu2AdditionsEnabled)
        {
            // Death generator
            XUMachineGenerators.DEATH_GENERATOR.recipes_registry.addRecipe(new EnergyBaseRecipe.EnergyBaseItem(ItemRef.wrap(new ItemStack(Items.SKULL, 1, 0)), 30000, 40));
            XUMachineGenerators.DEATH_GENERATOR.recipes_registry.addRecipe(new EnergyBaseRecipe.EnergyBaseItem(ItemRef.wrap(new ItemStack(Items.SKULL, 1, 2)), 30000, 40));
            XUMachineGenerators.DEATH_GENERATOR.recipes_registry.addRecipe(new EnergyBaseRecipe.EnergyBaseItem(new ItemRef.MatcherMakerOreDic("boneWithered"), 32000));
            
            XUMachineGenerators.PINK_GENERATOR.recipes_registry.addRecipe(new EnergyBaseRecipe.EnergyBaseItem(ItemRef.wrap(Blocks.PINK_GLAZED_TERRACOTTA), 400, 40));
            XUMachineGenerators.PINK_GENERATOR.recipes_registry.addRecipe(new EnergyBaseRecipe.EnergyBaseItem(ItemRef.wrap(Blocks.PINK_SHULKER_BOX), 400, 40));
            
            // Crusher
            if (ModLoadStatus.thermalExpansionLoaded)
                XUMachineCrusher.addRecipe(new ItemStack(Blocks.NETHERRACK), new ItemStack(Blocks.GRAVEL), ItemMaterial.dustSulfur, 0.1f);
            else
                XUMachineCrusher.addRecipe(new ItemStack(Blocks.NETHERRACK), new ItemStack(Blocks.GRAVEL));
        }
        
        if (modIntegrations.size() > 0)
        {
            MachineSlotItem slimeSecondary;
            try
            {
                // The slime secondary input is not available as public, and creating a new MachineSlotItem didn't work.
                Field secondInputField = MachineInit.class.getDeclaredField("SLOT_SLIME_SECONDARY");
                secondInputField.setAccessible(true);
                slimeSecondary = (MachineSlotItem) secondInputField.get(null);
                
                // Slime blocks
                // Ideally, this would just use ore dictionary, but XU2 machines don't seem to like wildcard metadata
                XUMachineGenerators.SLIME_GENERATOR.recipes_registry.addRecipe(
                        RecipeBuilder.newbuilder(XUMachineGenerators.SLIME_GENERATOR)
                                     .setRFRate(432_000, 400.0f)
                                     .setItemInput(XUMachineGenerators.INPUT_ITEM, new ItemStack(Blocks.SLIME_BLOCK), 1)
                                     .setItemInput(slimeSecondary, new ItemStack(Items.MILK_BUCKET, 1))
                                     .build());
            } catch (Exception e)
            {
                slimeSecondary = null;
            }
            
            for (IExtraUtilsIntegration integration : modIntegrations)
                integration.addRecipes(slimeSecondary);
        }
    }
}
