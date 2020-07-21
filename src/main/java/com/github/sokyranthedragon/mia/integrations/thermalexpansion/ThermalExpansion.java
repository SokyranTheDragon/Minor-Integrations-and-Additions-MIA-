package com.github.sokyranthedragon.mia.integrations.thermalexpansion;

import cofh.thermalexpansion.util.managers.machine.BrewerManager;
import cofh.thermalexpansion.util.managers.machine.EnchanterManager;
import cofh.thermalexpansion.util.managers.machine.SmelterManager;
import com.github.sokyranthedragon.mia.Mia;
import com.github.sokyranthedragon.mia.config.MiaConfig;
import com.github.sokyranthedragon.mia.config.TeConfiguration;
import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.integrations.base.IBaseMod;
import com.github.sokyranthedragon.mia.integrations.base.IModIntegration;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionType;
import net.minecraftforge.fml.common.ProgressManager;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.LinkedList;
import java.util.List;
import java.util.function.BiConsumer;

import static com.github.sokyranthedragon.mia.config.TeConfiguration.enableQuarkIntegration;
import static com.github.sokyranthedragon.mia.config.TeConfiguration.teAdditionsEnabled;

public class ThermalExpansion implements IBaseMod
{
    private List<IThermalExpansionIntegration> modIntegrations = new LinkedList<>();
    
    @Override
    public void register(BiConsumer<ModIds, IModIntegration> modIntegration)
    {
        if (enableQuarkIntegration && ModIds.QUARK.isLoaded)
            modIntegration.accept(ModIds.QUARK, new QuarkTEIntegration());
    }
    
    @Override
    public void addIntegration(IModIntegration integration)
    {
        if (!TeConfiguration.externalIntegrationsEnabled)
            return;
        
        if (integration instanceof IThermalExpansionIntegration)
        {
            modIntegrations.add((IThermalExpansionIntegration) integration);
            return;
        }
        
        Mia.LOGGER.warn("Incorrect Thermal Foundation integration with id of " + integration.getModId() + ": " + integration.toString());
    }
    
    @Override
    public void init(FMLInitializationEvent event)
    {
        if (teAdditionsEnabled && !MiaConfig.disableAllRecipes)
        {
            int energy = 6_000;
            ItemStack ingot = new ItemStack(Items.IRON_INGOT);
            SmelterManager.addRecycleRecipe(energy, new ItemStack(Items.CHAINMAIL_HELMET), ingot, 2);
            SmelterManager.addRecycleRecipe(energy, new ItemStack(Items.CHAINMAIL_CHESTPLATE), ingot, 4);
            SmelterManager.addRecycleRecipe(energy, new ItemStack(Items.CHAINMAIL_LEGGINGS), ingot, 3);
            SmelterManager.addRecycleRecipe(energy, new ItemStack(Items.CHAINMAIL_BOOTS), ingot, 2);
        }
        
        if (!modIntegrations.isEmpty() && !MiaConfig.disableAllRecipes)
        {
            ProgressManager.ProgressBar progressBar = ProgressManager.push("ThermalExpansion addRecipes", modIntegrations.size());
            for (IThermalExpansionIntegration integration : modIntegrations)
            {
                progressBar.step(integration.getModId().modId);
                integration.addRecipes();
            }
            ProgressManager.pop(progressBar);
        }
    }
    
    @Override
    public void postInit(FMLPostInitializationEvent event)
    {
        if (!modIntegrations.isEmpty() && !MiaConfig.disableAllRecipes)
        {
            ProgressManager.ProgressBar progressBar = ProgressManager.push("ThermalExpansion addPostInitRecipes", modIntegrations.size());
            for (IThermalExpansionIntegration integration : modIntegrations)
            {
                progressBar.step(integration.getModId().modId);
                integration.addPostInitRecipes();
            }
            ProgressManager.pop(progressBar);
        }
    }
    
    public static void addBrewingRecipe(@Nonnull PotionType result, @Nullable PotionType previousLevel, @Nonnull ItemStack ingredient)
    {
        if (!ModIds.THERMAL_EXPANSION.isLoaded)
            return;
        
        if (previousLevel == null)
            BrewerManager.addSwapPotionRecipes(result);
        else
            BrewerManager.addDefaultPotionRecipes(previousLevel, ingredient, result);
    }
    
    public static void addEnchantingRecipe(ItemStack input, @Nonnull String enchantment, int tier)
    {
        if (!ModIds.THERMAL_EXPANSION.isLoaded)
            return;
        
        EnchanterManager.addDefaultEnchantmentRecipe(input, enchantment, tier);
    }
}
