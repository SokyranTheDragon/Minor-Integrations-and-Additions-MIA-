package com.github.sokyranthedragon.mia.integrations.harvestcraft;

import cofh.thermalexpansion.util.managers.device.FactorizerManager;
import cofh.thermalexpansion.util.managers.machine.InsolatorManager;
import cofh.thermalexpansion.util.managers.machine.SawmillManager;
import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.integrations.thermalexpansion.IThermalExpansionIntegration;
import com.pam.harvestcraft.blocks.BlockRegistry;
import com.pam.harvestcraft.blocks.CropRegistry;
import com.pam.harvestcraft.blocks.FruitRegistry;
import com.pam.harvestcraft.item.ItemRegistry;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

class ThermalExpansionHarvestcraftIntegration implements IThermalExpansionIntegration
{
    @Override
    public void addRecipes()
    {
        int energy = 6_000;
        ItemStack output = new ItemStack(ItemRegistry.hardenedleatherItem);
        
        // Sawmill
        SawmillManager.addRecycleRecipe(energy, new ItemStack(ItemRegistry.hardenedleatherhelmItem), output, 2);
        SawmillManager.addRecycleRecipe(energy, new ItemStack(ItemRegistry.hardenedleatherchestItem), output, 4);
        SawmillManager.addRecycleRecipe(energy, new ItemStack(ItemRegistry.hardenedleatherleggingsItem), output, 3);
        SawmillManager.addRecycleRecipe(energy, new ItemStack(ItemRegistry.hardenedleatherbootsItem), output, 2);
    
    
        // Phytogenic Insolator
        // Normal recipes
        ItemStack input = new ItemStack(CropRegistry.getSeed(CropRegistry.GIGAPICKLE));
        InsolatorManager.addDefaultRecipe(input, new ItemStack(CropRegistry.getFood(CropRegistry.GIGAPICKLE)), input, 110);
        input = new ItemStack(CropRegistry.getSeed(CropRegistry.SESAME));
        InsolatorManager.addDefaultRecipe(input, new ItemStack(CropRegistry.getFood(CropRegistry.SESAME)), input, 110);
        // Tree recipes
        input = new ItemStack(FruitRegistry.getSapling(FruitRegistry.RAMBUTAN));
        InsolatorManager.addDefaultTreeRecipe(input, new ItemStack(FruitRegistry.getFood(FruitRegistry.RAMBUTAN)), input);
        input = new ItemStack(FruitRegistry.getSapling(FruitRegistry.SOURSOP));
        InsolatorManager.addDefaultTreeRecipe(input, new ItemStack(FruitRegistry.getFood(FruitRegistry.SOURSOP)), input);
        input = new ItemStack(FruitRegistry.getSapling(FruitRegistry.JACKFRUIT));
        InsolatorManager.addDefaultTreeRecipe(input, new ItemStack(FruitRegistry.getFood(FruitRegistry.JACKFRUIT)), input);
        input = new ItemStack(FruitRegistry.getSapling(FruitRegistry.PASSIONFRUIT));
        InsolatorManager.addDefaultTreeRecipe(input, new ItemStack(FruitRegistry.getFood(FruitRegistry.PASSIONFRUIT)), input);
        input = new ItemStack(FruitRegistry.getSapling(FruitRegistry.VANILLABEAN));
        InsolatorManager.addDefaultTreeRecipe(input, new ItemStack(FruitRegistry.getFood(FruitRegistry.VANILLABEAN)), input);
        input = new ItemStack(FruitRegistry.getSapling(FruitRegistry.HAZELNUT));
        InsolatorManager.addDefaultTreeRecipe(input, new ItemStack(FruitRegistry.getFood(FruitRegistry.HAZELNUT)), input);
        input = new ItemStack(FruitRegistry.getSapling(FruitRegistry.PAWPAW));
        InsolatorManager.addDefaultTreeRecipe(input, new ItemStack(FruitRegistry.getFood(FruitRegistry.PAWPAW)), input);
        input = new ItemStack(FruitRegistry.getSapling(FruitRegistry.LYCHEE));
        InsolatorManager.addDefaultTreeRecipe(input, new ItemStack(FruitRegistry.getFood(FruitRegistry.LYCHEE)), input);
        input = new ItemStack(FruitRegistry.getSapling(FruitRegistry.GUAVA));
        InsolatorManager.addDefaultTreeRecipe(input, new ItemStack(FruitRegistry.getFood(FruitRegistry.GUAVA)), input);
        input = new ItemStack(FruitRegistry.getSapling(FruitRegistry.BREADFRUIT));
        InsolatorManager.addDefaultTreeRecipe(input, new ItemStack(FruitRegistry.getFood(FruitRegistry.BREADFRUIT)), input);
        input = new ItemStack(FruitRegistry.getSapling(FruitRegistry.TAMARIND));
        InsolatorManager.addDefaultTreeRecipe(input, new ItemStack(FruitRegistry.getFood(FruitRegistry.TAMARIND)), input);
        input = new ItemStack(FruitRegistry.getSapling(FruitRegistry.MAPLE));
        InsolatorManager.addDefaultTreeRecipe(input, new ItemStack(FruitRegistry.getFood(FruitRegistry.MAPLE)), input);
        input = new ItemStack(FruitRegistry.getSapling(FruitRegistry.SPIDERWEB));
        InsolatorManager.addDefaultTreeRecipe(input, new ItemStack(FruitRegistry.getFood(FruitRegistry.SPIDERWEB), 2), input);
        input = new ItemStack(FruitRegistry.getSapling(FruitRegistry.GOOSEBERRY));
        InsolatorManager.addDefaultTreeRecipe(input, new ItemStack(FruitRegistry.getFood(FruitRegistry.GOOSEBERRY)), input);
        input = new ItemStack(FruitRegistry.getSapling(FruitRegistry.CINNAMON));
        InsolatorManager.addDefaultTreeRecipe(input, new ItemStack(FruitRegistry.getFood(FruitRegistry.CINNAMON)), input);
        input = new ItemStack(FruitRegistry.getSapling(FruitRegistry.PAPERBARK));
        InsolatorManager.addDefaultTreeRecipe(input, new ItemStack(FruitRegistry.getFood(FruitRegistry.PAPERBARK)), input);
        
        
        // Factorizer
        FactorizerManager.addDefaultRecipe(new ItemStack(ItemRegistry.beeswaxItem), new ItemStack(BlockRegistry.pressedwax));
    }
    
    @Nonnull
    @Override
    public ModIds getModId()
    {
        return ModIds.HARVESTCRAFT;
    }
}
