package com.github.exploder1531.mia.integrations.xu2;

import cofh.thermalfoundation.item.ItemMaterial;
import com.github.exploder1531.mia.Mia;
import com.github.exploder1531.mia.integrations.ModIds;
import com.github.exploder1531.mia.integrations.base.IBaseMod;
import com.github.exploder1531.mia.integrations.base.IModIntegration;
import com.google.common.collect.Lists;
import com.rwtema.extrautils2.api.machine.MachineSlotItem;
import com.rwtema.extrautils2.api.machine.RecipeBuilder;
import com.rwtema.extrautils2.api.machine.XUMachineCrusher;
import com.rwtema.extrautils2.api.machine.XUMachineGenerators;
import com.rwtema.extrautils2.backend.entries.XU2Entries;
import com.rwtema.extrautils2.machine.EnergyBaseRecipe;
import com.rwtema.extrautils2.machine.MachineInit;
import com.rwtema.extrautils2.utils.datastructures.ItemRef;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.fml.common.ProgressManager;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.oredict.OreDictionary;
import thaumcraft.api.aspects.*;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

import static com.github.exploder1531.mia.config.Xu2Configuration.*;
import static com.github.exploder1531.mia.integrations.ModIds.*;

public class ExtraUtilities2 implements IBaseMod
{
    private final List<IExtraUtilsIntegration> modIntegrations = Lists.newLinkedList();
    
    @Override
    public void register(BiConsumer<ModIds, IModIntegration> modIntegration)
    {
        if (enableTeIntegration && THERMAL_EXPANSION.isLoaded)
            modIntegration.accept(THERMAL_EXPANSION, new ThermalExpansionExtraUtilsIntegration());
        if (HATCHERY.isLoaded)
            modIntegration.accept(HATCHERY, new HatcheryExtraUtilsIntegration(enableHatcheryIntegration));
        if (enableQuarkIntegration && QUARK.isLoaded)
            modIntegration.accept(QUARK, new QuarkExtraUtilsIntegration());
        if (enableJerIntegration && JER.isLoaded)
            modIntegration.accept(JER, new JerExtraUtilsIntegration());
        if (enableFutureMcIntegration && FUTURE_MC.isLoaded)
            modIntegration.accept(FUTURE_MC, new FutureMcExtraUtilsIntegration());
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
            if (THERMAL_FOUNDATION.isLoaded)
                XUMachineCrusher.addRecipe(new ItemStack(Blocks.NETHERRACK), new ItemStack(Blocks.GRAVEL), ItemMaterial.dustSulfur, 0.1f);
            else
                XUMachineCrusher.addRecipe(new ItemStack(Blocks.NETHERRACK), new ItemStack(Blocks.GRAVEL));
        }
        
        if (!modIntegrations.isEmpty())
        {
            ProgressManager.ProgressBar progressBar = ProgressManager.push("ExtraUtilities2 addRecipes", modIntegrations.size() + 1);
            progressBar.step("setting up");
            
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
            {
                progressBar.step(integration.getModId().modId);
                integration.addRecipes(slimeSecondary);
            }
            ProgressManager.pop(progressBar);
        }
    }
    
    @Override
    @Optional.Method(modid = ConstantIds.THAUMCRAFT)
    public void registerAspects(AspectRegistryEvent event)
    {
        event.register.registerObjectTag(XU2Entries.miniChest.newStack(), new AspectList().add(Aspect.PLANT, 2));
        event.register.registerObjectTag(XU2Entries.blockEnderLilly.newStack(), new AspectList().add(Aspect.MOTION, 5).add(Aspect.ELDRITCH, 3).add(Aspect.PLANT, 5).add(Aspect.SENSES, 5).add(Aspect.LIFE, 1));
        event.register.registerObjectTag(XU2Entries.blockRedOrchid.newStack(), new AspectList().add(Aspect.PLANT, 5).add(Aspect.SENSES, 5).add(Aspect.ENERGY, 5).add(Aspect.LIFE, 1));
        event.register.registerObjectTag(XU2Entries.wateringCan.newStackMeta(OreDictionary.WILDCARD_VALUE), new AspectList().add(Aspect.TOOL, 16).add(Aspect.VOID, 3).add(Aspect.EARTH, 15));
        event.register.registerObjectTag(XU2Entries.goldenLasso.newStack(), new AspectList().add(Aspect.BEAST, 15).add(Aspect.CRAFT, 3).add(Aspect.METAL, 3).add(Aspect.DESIRE, 3));
        event.register.registerObjectTag(XU2Entries.cursedEarth.newStack(), new AspectList().add(Aspect.EARTH, 4).add(Aspect.PLANT, 1).add(Aspect.METAL, 2).add(Aspect.FIRE, 2));
        event.register.registerObjectTag(XU2Entries.pipe.newStack(), new AspectList().add(Aspect.EARTH, 4).add(Aspect.CRYSTAL, 7).add(Aspect.ENERGY, 7));
        event.register.registerObjectTag(XU2Entries.sunCrystal.newStack(), new AspectList().add(Aspect.LIGHT, 100).add(Aspect.SENSES, 25).add(Aspect.CRYSTAL, 25).add(Aspect.DESIRE, 25));
        
        event.register.registerObjectTag(XU2Entries.itemIngredients.newStackMeta(3), new AspectList().add(Aspect.SENSES, 5).add(Aspect.EARTH, 2).add(Aspect.DESIRE, 2).add(Aspect.DARKNESS, 2));
        event.register.registerObjectTag(XU2Entries.itemIngredients.newStackMeta(4), new AspectList().add(Aspect.ENERGY, 13).add(Aspect.FIRE, 13));
        event.register.registerObjectTag(XU2Entries.itemIngredients.newStackMeta(9), new AspectList().add(AspectHelper.getObjectAspects(new ItemStack(Blocks.LIGHT_WEIGHTED_PRESSURE_PLATE))).add(Aspect.FIRE, 3));
        event.register.registerObjectTag(XU2Entries.itemIngredients.newStackMeta(10), new AspectList().add(Aspect.METAL, 3).add(Aspect.FIRE, 3));
        event.register.registerObjectTag(XU2Entries.itemIngredients.newStackMeta(11), new AspectList().add(Aspect.METAL, 10).add(Aspect.DESIRE, 10).add(Aspect.FIRE, 10));
        event.register.registerObjectTag(XU2Entries.itemIngredients.newStackMeta(12), new AspectList().add(Aspect.METAL, 10).add(Aspect.DESIRE, 10).add(Aspect.MAGIC, 5));
        event.register.registerObjectTag(XU2Entries.itemIngredients.newStackMeta(13), new AspectList().add(Aspect.METAL, 3).add(Aspect.ENERGY, 5).add(Aspect.FIRE, 1));
        event.register.registerObjectTag(XU2Entries.itemIngredients.newStackMeta(17), new AspectList().add(Aspect.MAGIC, 2).add(Aspect.ORDER, 2).add(Aspect.ELDRITCH, 1).add(Aspect.AURA, 1).add(Aspect.METAL, 10).add(Aspect.DARKNESS, 2));
        
        event.register.registerObjectTag(XU2Entries.unstableIngots.newStackMeta(0), new AspectList().add(Aspect.ENTROPY, 250).add(Aspect.CRYSTAL, 150).add(Aspect.DESIRE, 150).add(Aspect.METAL, 100));
        event.register.registerObjectTag(XU2Entries.unstableIngots.newStackMeta(1), new AspectList().add(Aspect.ENTROPY, 10).add(Aspect.CRYSTAL, 5).add(Aspect.DESIRE, 5).add(Aspect.METAL, 3));
        event.register.registerObjectTag(XU2Entries.unstableIngots.newStackMeta(2), new AspectList().add(Aspect.ENTROPY, 90).add(Aspect.CRYSTAL, 45).add(Aspect.DESIRE, 45).add(Aspect.METAL, 27));
        
        for (int meta = 0; meta <= 1; meta++)
            event.register.registerObjectTag(XU2Entries.decorativeSolidWood.newStackMeta(meta), new AspectList().add(Aspect.MAGIC, 20).add(Aspect.PLANT, 20).add(Aspect.MIND, 8));
        
        for (int meta = 0; meta <= 2; meta++)
            event.register.registerObjectTag(XU2Entries.decorativeGlass.newStackMeta(meta), new AspectList().add(Aspect.CRYSTAL, 6));
        event.register.registerObjectTag(XU2Entries.decorativeGlass.newStackMeta(3), new AspectList().add(Aspect.CRYSTAL, 6).add(Aspect.SENSES, 1));
        event.register.registerObjectTag(XU2Entries.decorativeGlass.newStackMeta(4), new AspectList().add(Aspect.CRYSTAL, 6).add(Aspect.LIGHT, 3).add(Aspect.SENSES, 1));
        event.register.registerObjectTag(XU2Entries.decorativeGlass.newStackMeta(5), new AspectList().add(Aspect.CRYSTAL, 6).add(Aspect.ENERGY, 3));
        
        for (int meta = 0; meta <= 2; meta++)
            event.register.registerObjectTag(XU2Entries.decorativeSolid.newStackMeta(meta), new AspectList().add(Aspect.EARTH, 3));
        event.register.registerObjectTag(XU2Entries.decorativeSolid.newStackMeta(3), new AspectList().add(Aspect.EARTH, 3).add(Aspect.FIRE, 2));
        event.register.registerObjectTag(XU2Entries.decorativeSolid.newStackMeta(5), new AspectList().add(Aspect.EARTH, 3));
        event.register.registerObjectTag(XU2Entries.decorativeSolid.newStackMeta(7), new AspectList().add(Aspect.CRYSTAL, 15).add(Aspect.FIRE, 2));
        event.register.registerObjectTag(XU2Entries.decorativeSolid.newStackMeta(8), new AspectList().add(Aspect.CRYSTAL, 15).add(Aspect.MAGIC, 15).add(Aspect.ENERGY, 1).add(Aspect.ELDRITCH, 15));
        
        for (int meta = 0; meta <= 7; meta++)
        {
            int aspectPower = 500;
            if (meta == 2) aspectPower = 270;
            else if (meta == 1) aspectPower = 40;
            else if (meta == 0) aspectPower = 6;
            
            int aspectPower2 = Math.min(500, aspectPower * 3);
            int aspectPower5 = Math.min(500, aspectPower * 5);
            
            // This could be optimized more, but I decided to go for readability since it's during initialization phase and the loop is called only once
            event.register.registerObjectTag(XU2Entries.compressedCobblestone.newStackMeta(meta), new AspectList().add(Aspect.EARTH, aspectPower5).add(Aspect.ENTROPY, aspectPower));
            if (meta <= 5)
                event.register.registerObjectTag(XU2Entries.compressedNetherack.newStackMeta(meta), new AspectList().add(Aspect.EARTH, aspectPower5).add(Aspect.FIRE, aspectPower2));
            if (meta <= 3)
                event.register.registerObjectTag(XU2Entries.compressedDirt.newStackMeta(meta), new AspectList().add(Aspect.EARTH, aspectPower5));
            if (meta <= 2)
            {
                event.register.registerObjectTag(XU2Entries.compressedGravel.newStackMeta(meta), new AspectList().add(Aspect.EARTH, aspectPower5).add(Aspect.ENTROPY, aspectPower2));
                event.register.registerObjectTag(XU2Entries.compressedSand.newStackMeta(meta), new AspectList().add(Aspect.EARTH, aspectPower5).add(Aspect.ENTROPY, aspectPower5));
            }
        }
        
        AspectList bedrockAspects = AspectHelper.getObjectAspects(new ItemStack(Blocks.BEDROCK));
        for (int meta = 0; meta <= 2; meta++)
            event.register.registerObjectTag(XU2Entries.decorativeBedrock.newStackMeta(meta), bedrockAspects);
        
        appendToolAspect(event.register, XU2Entries.itemGlassCutter.newWildcardStack());
        appendToolAspect(event.register, XU2Entries.trowel.newWildcardStack());
        appendToolAspect(event.register, XU2Entries.wrench.newWildcardStack());
        
        List<ItemStack> angelRingStacks = XU2Entries.angelRing.getCreativeStacks();
        AspectList squidRingAspects = AspectHelper.getObjectAspects(XU2Entries.chickenRing.newStackMeta(1));
        
        for (ItemStack angelRing : angelRingStacks)
        {
            AspectList angelAspects = AspectHelper.getObjectAspects(angelRing);
            for (Map.Entry<Aspect, Integer> aspects : squidRingAspects.aspects.entrySet())
                angelAspects.merge(aspects.getKey(), (int) (aspects.getValue() * 0.75f));
            event.register.registerObjectTag(angelRing, angelAspects);
        }
    }
    
    @Optional.Method(modid = ConstantIds.THAUMCRAFT)
    private void appendToolAspect(AspectEventProxy register, ItemStack item)
    {
        register.registerObjectTag(item, AspectHelper.getObjectAspects(item).merge(Aspect.TOOL, 12));
    }
}
