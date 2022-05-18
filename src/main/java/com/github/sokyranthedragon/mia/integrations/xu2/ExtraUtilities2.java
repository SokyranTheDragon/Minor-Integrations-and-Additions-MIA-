package com.github.sokyranthedragon.mia.integrations.xu2;

import cofh.thermalfoundation.item.ItemMaterial;
import com.github.sokyranthedragon.mia.Mia;
import com.github.sokyranthedragon.mia.config.MiaConfig;
import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.integrations.base.IBaseMod;
import com.github.sokyranthedragon.mia.integrations.base.IModIntegration;
import com.github.sokyranthedragon.mia.integrations.thaumcraft.ThaumcraftHelpers;
import com.rwtema.extrautils2.api.machine.MachineSlotItem;
import com.rwtema.extrautils2.api.machine.RecipeBuilder;
import com.rwtema.extrautils2.api.machine.XUMachineCrusher;
import com.rwtema.extrautils2.api.machine.XUMachineGenerators;
import com.rwtema.extrautils2.backend.entries.Entry;
import com.rwtema.extrautils2.backend.entries.XU2Entries;
import com.rwtema.extrautils2.machine.EnergyBaseRecipe;
import com.rwtema.extrautils2.machine.MachineInit;
import com.rwtema.extrautils2.utils.datastructures.ItemRef;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.fml.common.ProgressManager;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.oredict.OreDictionary;
import thaumcraft.api.aspects.*;

import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

import static com.github.sokyranthedragon.mia.config.Xu2Configuration.*;
import static com.github.sokyranthedragon.mia.integrations.ModIds.*;

public class ExtraUtilities2 implements IBaseMod
{
    private final List<IExtraUtilsIntegration> modIntegrations = new LinkedList<>();

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
        if (enableChiselIntegration && CHISEL.isLoaded)
            modIntegration.accept(CHISEL, new ChiselExtraUtilsIntegration());
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
        if (xu2AdditionsEnabled && !MiaConfig.disableAllRecipes)
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

        if (xu2AdditionsEnabled && !MiaConfig.disableOreDict)
        {
            for (int meta = 0; meta <= 5; meta++)
            {
                if (meta <= 2)
                    OreDictionary.registerOre("blockGlassColorless", XU2Entries.decorativeGlass.newStackMeta(meta));
                if (meta != 0 && meta != 3)
                    OreDictionary.registerOre("blockGlass", XU2Entries.decorativeGlass.newStackMeta(meta));
            }
            OreDictionary.registerOre("blockGlassBlack", XU2Entries.decorativeGlass.newStackMeta(3));
            OreDictionary.registerOre("blockGlassYellow", XU2Entries.decorativeGlass.newStackMeta(4));
            OreDictionary.registerOre("blockGlassRed", XU2Entries.decorativeGlass.newStackMeta(5));
        }

        if (!modIntegrations.isEmpty() && !MiaConfig.disableAllRecipes)
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
        AspectEventProxy register = event.register;

        registerAspects(register, XU2Entries.miniChest, new AspectList().add(Aspect.PLANT, 2));
        registerAspects(register, XU2Entries.blockEnderLilly, new AspectList().add(Aspect.MOTION, 5).add(Aspect.ELDRITCH, 3).add(Aspect.PLANT, 5).add(Aspect.SENSES, 5).add(Aspect.LIFE, 1));
        registerAspects(register, XU2Entries.blockRedOrchid, new AspectList().add(Aspect.PLANT, 5).add(Aspect.SENSES, 5).add(Aspect.ENERGY, 5).add(Aspect.LIFE, 1));
        registerAspects(register, XU2Entries.wateringCan, OreDictionary.WILDCARD_VALUE, new AspectList().add(Aspect.TOOL, 16).add(Aspect.VOID, 3).add(Aspect.EARTH, 15));
        registerAspects(register, XU2Entries.goldenLasso, new AspectList().add(Aspect.BEAST, 15).add(Aspect.CRAFT, 3).add(Aspect.METAL, 3).add(Aspect.DESIRE, 3));
        registerAspects(register, XU2Entries.cursedEarth, new AspectList().add(Aspect.EARTH, 4).add(Aspect.PLANT, 1).add(Aspect.METAL, 2).add(Aspect.FIRE, 2));
        registerAspects(register, XU2Entries.pipe, new AspectList().add(Aspect.EARTH, 4).add(Aspect.CRYSTAL, 7).add(Aspect.ENERGY, 7));
        registerAspects(register, XU2Entries.sunCrystal, new AspectList().add(Aspect.LIGHT, 100).add(Aspect.SENSES, 25).add(Aspect.CRYSTAL, 25).add(Aspect.DESIRE, 25));

        registerAspects(register, XU2Entries.itemIngredients, 3, new AspectList().add(Aspect.SENSES, 5).add(Aspect.EARTH, 2).add(Aspect.DESIRE, 2).add(Aspect.DARKNESS, 2));
        registerAspects(register, XU2Entries.itemIngredients, 4, new AspectList().add(Aspect.ENERGY, 13).add(Aspect.FIRE, 13));
        registerAspects(register, XU2Entries.itemIngredients, 9, new AspectList().add(AspectHelper.getObjectAspects(new ItemStack(Blocks.LIGHT_WEIGHTED_PRESSURE_PLATE))).add(Aspect.FIRE, 3));
        registerAspects(register, XU2Entries.itemIngredients, 10, new AspectList().add(Aspect.METAL, 3).add(Aspect.FIRE, 3));
        registerAspects(register, XU2Entries.itemIngredients, 11, new AspectList().add(Aspect.METAL, 10).add(Aspect.DESIRE, 10).add(Aspect.FIRE, 10));
        registerAspects(register, XU2Entries.itemIngredients, 12, new AspectList().add(Aspect.METAL, 10).add(Aspect.DESIRE, 10).add(Aspect.MAGIC, 5));
        registerAspects(register, XU2Entries.itemIngredients, 13, new AspectList().add(Aspect.METAL, 3).add(Aspect.ENERGY, 5).add(Aspect.FIRE, 1));
        registerAspects(register, XU2Entries.itemIngredients, 17, new AspectList().add(Aspect.MAGIC, 2).add(Aspect.ORDER, 2).add(Aspect.ELDRITCH, 1).add(Aspect.AURA, 1).add(Aspect.METAL, 10).add(Aspect.DARKNESS, 2));

        registerAspects(register, XU2Entries.unstableIngots, 0, new AspectList().add(Aspect.ENTROPY, 250).add(Aspect.CRYSTAL, 150).add(Aspect.DESIRE, 150).add(Aspect.METAL, 100));
        registerAspects(register, XU2Entries.unstableIngots, 1, new AspectList().add(Aspect.ENTROPY, 10).add(Aspect.CRYSTAL, 5).add(Aspect.DESIRE, 5).add(Aspect.METAL, 3));
        registerAspects(register, XU2Entries.unstableIngots, 2, new AspectList().add(Aspect.ENTROPY, 90).add(Aspect.CRYSTAL, 45).add(Aspect.DESIRE, 45).add(Aspect.METAL, 27));

        registerAspects(register, XU2Entries.decorativeSolidWood, 0, 1, new AspectList().add(Aspect.MAGIC, 20).add(Aspect.PLANT, 20).add(Aspect.MIND, 8));

        registerAspects(register, XU2Entries.decorativeGlass, 0, 2, new AspectList().add(Aspect.CRYSTAL, 6));
        registerAspects(register, XU2Entries.decorativeGlass, 3, new AspectList().add(Aspect.CRYSTAL, 6).add(Aspect.SENSES, 1));
        registerAspects(register, XU2Entries.decorativeGlass, 4, new AspectList().add(Aspect.CRYSTAL, 6).add(Aspect.LIGHT, 3).add(Aspect.SENSES, 1));
        registerAspects(register, XU2Entries.decorativeGlass, 5, new AspectList().add(Aspect.CRYSTAL, 6).add(Aspect.ENERGY, 3));

        registerAspects(register, XU2Entries.decorativeSolid, 0, 2, new AspectList().add(Aspect.EARTH, 3));
        registerAspects(register, XU2Entries.decorativeSolid, 3, new AspectList().add(Aspect.EARTH, 3).add(Aspect.FIRE, 2));
        registerAspects(register, XU2Entries.decorativeSolid, 5, new AspectList().add(Aspect.EARTH, 3));
        registerAspects(register, XU2Entries.decorativeSolid, 7, new AspectList().add(Aspect.CRYSTAL, 15).add(Aspect.FIRE, 2));
        registerAspects(register, XU2Entries.decorativeSolid, 8, new AspectList().add(Aspect.CRYSTAL, 15).add(Aspect.MAGIC, 15).add(Aspect.ENERGY, 1).add(Aspect.ELDRITCH, 15));

        if (XU2Entries.compressedCobblestone.enabled && XU2Entries.compressedNetherack.enabled && XU2Entries.compressedDirt.enabled && XU2Entries.compressedGravel.enabled && XU2Entries.compressedSand.enabled)
        {
            AspectList cobblestone = AspectHelper.getObjectAspects(new ItemStack(Blocks.COBBLESTONE));
            AspectList netherrack = AspectHelper.getObjectAspects(new ItemStack(Blocks.NETHERRACK));
            AspectList dirt = AspectHelper.getObjectAspects(new ItemStack(Blocks.DIRT));
            AspectList sand = AspectHelper.getObjectAspects(new ItemStack(Blocks.SAND));
            AspectList gravel = AspectHelper.getObjectAspects(new ItemStack(Blocks.GRAVEL));

            for (int meta = 0, power = MathHelper.ceil(5 * 9 * 0.75f); meta <= 7; meta++, power = MathHelper.ceil(power * 9 * 0.75f))
            {
                if (XU2Entries.compressedCobblestone.enabled)
                    ThaumcraftHelpers.transferAspects(XU2Entries.compressedCobblestone.newStack(meta), cobblestone, register, power, false);
                if (XU2Entries.compressedNetherack.enabled && meta <= 5)
                    ThaumcraftHelpers.transferAspects(XU2Entries.compressedNetherack.newStackMeta(meta), netherrack, register, power, false);
                if (XU2Entries.compressedDirt.enabled && meta <= 3)
                    ThaumcraftHelpers.transferAspects(XU2Entries.compressedDirt.newStackMeta(meta), dirt, register, power, false);
                if (meta <= 2)
                {
                    if (XU2Entries.compressedGravel.enabled)
                        ThaumcraftHelpers.transferAspects(XU2Entries.compressedGravel.newStackMeta(meta), gravel, register, power, false);
                    if (XU2Entries.compressedSand.enabled)
                        ThaumcraftHelpers.transferAspects(XU2Entries.compressedSand.newStackMeta(meta), sand, register, power, false);
                }
            }
        }

        registerAspects(register, XU2Entries.decorativeBedrock, 0, 2, AspectHelper.getObjectAspects(new ItemStack(Blocks.BEDROCK)));

        if (XU2Entries.itemGlassCutter.enabled)
            appendToolAspect(register, XU2Entries.itemGlassCutter.newWildcardStack());
        if (XU2Entries.trowel.enabled)
            appendToolAspect(register, XU2Entries.trowel.newWildcardStack());
        if (XU2Entries.wrench.enabled)
            appendToolAspect(register, XU2Entries.wrench.newWildcardStack());

        if (XU2Entries.angelRing.enabled && XU2Entries.chickenRing.enabled)
        {
            List<ItemStack> angelRingStacks = XU2Entries.angelRing.getCreativeStacks();
            AspectList squidRingAspects = AspectHelper.getObjectAspects(XU2Entries.chickenRing.newStackMeta(1));

            for (ItemStack angelRing : angelRingStacks)
            {
                AspectList angelAspects = AspectHelper.getObjectAspects(angelRing);
                for (Map.Entry<Aspect, Integer> aspects : squidRingAspects.aspects.entrySet())
                    angelAspects.merge(aspects.getKey(), (int) (aspects.getValue() * 0.75f));
                register.registerObjectTag(angelRing, angelAspects);
            }
        }

        if (XU2Entries.snowGlobe.enabled)
            ThaumcraftHelpers.transferAspects(XU2Entries.snowGlobe.newStackMeta(1), XU2Entries.snowGlobe.newStackMeta(0), register, new AspectList().add(Aspect.MAGIC, 25).add(Aspect.PLANT, 50));
    }

    private <T> void registerAspects(AspectEventProxy register, Entry<T> entry, AspectList aspects)
    {
        if (entry.enabled)
            register.registerObjectTag(entry.newStack(), aspects);
    }

    private <T> void registerAspects(AspectEventProxy register, Entry<T> entry, int meta, AspectList aspects)
    {
        if (entry.enabled)
            register.registerObjectTag(entry.newStackMeta(meta), aspects);
    }

    private <T> void registerAspects(AspectEventProxy register, Entry<T> entry, int metaStart, int metaEnd, AspectList aspects)
    {
        if (entry.enabled)
        {
            for (int meta = metaStart; meta <= metaEnd; meta++)
                register.registerObjectTag(entry.newStackMeta(meta), aspects);
        }
    }

    @Optional.Method(modid = ConstantIds.THAUMCRAFT)
    private void appendToolAspect(AspectEventProxy register, ItemStack item)
    {
        register.registerObjectTag(item, AspectHelper.getObjectAspects(item).merge(Aspect.TOOL, 12));
    }
}
