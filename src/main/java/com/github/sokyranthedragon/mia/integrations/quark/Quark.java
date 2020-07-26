package com.github.sokyranthedragon.mia.integrations.quark;

import com.github.sokyranthedragon.mia.Mia;
import com.github.sokyranthedragon.mia.config.MiaConfig;
import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.integrations.base.IBaseMod;
import com.github.sokyranthedragon.mia.integrations.base.IModIntegration;
import com.github.sokyranthedragon.mia.utilities.QuarkUtils;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.ProgressManager;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.oredict.OreDictionary;
import org.apache.commons.lang3.tuple.Pair;
import vazkii.quark.base.module.Feature;
import vazkii.quark.base.module.ModuleLoader;
import vazkii.quark.building.feature.Trowel;
import vazkii.quark.client.feature.EnchantedBooksShowItems;
import vazkii.quark.misc.feature.AncientTomes;
import vazkii.quark.world.feature.Biotite;
import vazkii.quark.world.feature.Crabs;
import vazkii.quark.world.feature.Frogs;
import vazkii.quark.world.feature.UndergroundBiomes;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.BiConsumer;

import static com.github.sokyranthedragon.mia.config.QuarkConfiguration.*;
import static com.github.sokyranthedragon.mia.integrations.ModIds.*;

public class Quark implements IBaseMod
{
    private List<IQuarkIntegration> modIntegrations = new ArrayList<>();
    
    @Override
    public void register(BiConsumer<ModIds, IModIntegration> modIntegration)
    {
        if (enableXu2Integration && EXTRA_UTILITIES.isLoaded)
            modIntegration.accept(EXTRA_UTILITIES, new ExtraUtilsQuarkIntegration());
        if (enableTeIntegration && THERMAL_EXPANSION.isLoaded)
            modIntegration.accept(THERMAL_EXPANSION, new ThermalExpansionQuarkIntegration());
        if (enableJerIntegration && JER.isLoaded)
            modIntegration.accept(JER, new JerQuarkIntegration());
        if (enableDungeonTacticsIntegration && DUNGEON_TACTICS.isLoaded)
            modIntegration.accept(DUNGEON_TACTICS, new DungeonTacticsQuarkIntegration());
        if (enableFutureMcIntegration && FUTURE_MC.isLoaded)
            modIntegration.accept(FUTURE_MC, new FutureMcQuarkIntegration());
        if (enableChiselIntegration && CHISEL.isLoaded)
            modIntegration.accept(CHISEL, new ChiselQuarkIntegration());
        if (enableIFIntegration && INDUSTRIAL_FOREGOING.isLoaded)
            modIntegration.accept(INDUSTRIAL_FOREGOING, new IndustrialForegoingQuarkIntegration());
    }
    
    @Override
    public void addIntegration(IModIntegration integration)
    {
        if (!externalIntegrationsEnabled)
            return;
        
        if (integration instanceof IQuarkIntegration)
            modIntegrations.add((IQuarkIntegration) integration);
        else
            Mia.LOGGER.warn("Incorrect XU2 integration with id of " + integration.getModId() + ": " + integration.toString());
    }
    
    @Override
    public void preInit(FMLPreInitializationEvent event)
    {
        Feature ancientTomesFeature = ModuleLoader.featureInstances.get(AncientTomes.class);
        if (addAncientTomes && !modIntegrations.isEmpty() && ancientTomesFeature.isEnabled())
        {
            ProgressManager.ProgressBar progressBar = ProgressManager.push("Quark ancient tomes", modIntegrations.size() + 1);
            
            List<String> tempEnchants = new ArrayList<>();
            for (IQuarkIntegration integration : modIntegrations)
            {
                progressBar.step(integration.getModId().modId);
                tempEnchants.addAll(integration.getAllowedAncientTomeEnchants());
            }
            
            progressBar.step("adding tomes");
            
            if (!tempEnchants.isEmpty())
            {
                try
                {
                    Field enchantNames = AncientTomes.class.getDeclaredField("enchantNames");
                    enchantNames.setAccessible(true);
                    
                    Object obj = enchantNames.get(ancientTomesFeature);
                    if (obj instanceof String[])
                    {
                        List<String> enchants = new ArrayList<>();
                        Collections.addAll(enchants, (String[]) obj);
                        enchants.addAll(tempEnchants);
                        enchantNames.set(ancientTomesFeature, enchants.toArray(new String[0]));
                    }
                } catch (NoSuchFieldException | IllegalAccessException e)
                {
                    Mia.LOGGER.error("Could not access Quark AncientTomes.enchantNames, no default ancient tome insertions will be added.");
                }
            }
            
            ProgressManager.pop(progressBar);
        }
    }
    
    @Override
    public void init(FMLInitializationEvent event)
    {
        if (addItemTooltips && event.getSide() == Side.CLIENT && !modIntegrations.isEmpty() && QuarkUtils.isFeatureEnabled(EnchantedBooksShowItems.class))
        {
            ProgressManager.ProgressBar progressBar = ProgressManager.push("Quark enchantment tooltips", modIntegrations.size() + 1);
            
            List<ItemStack> tempItems = new ArrayList<>();
            for (IQuarkIntegration integration : modIntegrations)
            {
                progressBar.step(integration.getModId().modId);
                tempItems.addAll(integration.getItemsToShowEnchantmentsFor());
            }
            
            progressBar.step("adding tooltips");
            
            if (!tempItems.isEmpty())
            {
                try
                {
                    Field testItems = EnchantedBooksShowItems.class.getDeclaredField("testItemLocations");
                    testItems.setAccessible(true);
                    
                    Object obj = testItems.get(testItems);
                    if (obj instanceof List)
                    {
                        //noinspection unchecked
                        List<Pair<ResourceLocation, Integer>> items = (List<Pair<ResourceLocation, Integer>>) obj;
                        
                        for (ItemStack item : tempItems)
                            items.add(Pair.of(item.getItem().getRegistryName(), item.getMetadata()));
                    }
                } catch (NoSuchFieldException | IllegalAccessException e)
                {
                    Mia.LOGGER.error("Could not access Quark EnchantedBooksShowItems.testItemLocations, no default items to be displayed on enchanted books will be added.");
                }
            }
            
            ProgressManager.pop(progressBar);
        }
        
        if (quarkAdditionsEnabled)
        {
            if (QuarkUtils.isFeatureEnabled(Trowel.class) && !MiaConfig.disableAllRecipes)
                FurnaceRecipes.instance().addSmeltingRecipe(new ItemStack(Trowel.trowel), new ItemStack(Items.IRON_NUGGET), 0.1f);
            
            if (!MiaConfig.disableOreDict)
            {
                if (QuarkUtils.isFeatureEnabled(Frogs.class))
                {
                    OreDictionary.registerOre("foodFrograw", Frogs.frogLeg);
                    OreDictionary.registerOre("foodFrogcooked", Frogs.cookedFrogLeg);
                }
                
                if (QuarkUtils.isFeatureEnabled(Crabs.class))
                {
                    OreDictionary.registerOre("foodCrabraw", Crabs.crabLeg);
                    OreDictionary.registerOre("foodCrabcooked", Crabs.cookedCrabLeg);
                }
                
                if (QuarkUtils.isFeatureEnabled(Biotite.class))
                    OreDictionary.registerOre("oreEnderBiotite", Biotite.biotite_ore);
                if (QuarkUtils.isFeatureEnabled(UndergroundBiomes.class))
                    OreDictionary.registerOre("listAllmushroom", UndergroundBiomes.glowshroom);
            }
        }
    }
}
