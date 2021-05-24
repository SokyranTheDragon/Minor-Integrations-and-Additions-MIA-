package com.github.sokyranthedragon.mia.integrations.quark;

import cofh.thermalexpansion.util.managers.device.FactorizerManager;
import cofh.thermalexpansion.util.managers.machine.*;
import cofh.thermalexpansion.util.managers.machine.EnchanterManager.EnchanterRecipe;
import cofh.thermalfoundation.item.ItemMaterial;
import com.github.sokyranthedragon.mia.config.QuarkConfiguration;
import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.integrations.thermalexpansion.IThermalExpansionIntegration;
import com.github.sokyranthedragon.mia.utilities.QuarkUtils;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemEnchantedBook;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import vazkii.quark.automation.feature.ChainLinkage;
import vazkii.quark.automation.feature.MetalButtons;
import vazkii.quark.automation.feature.PistonSpikes;
import vazkii.quark.building.feature.SoulSandstone;
import vazkii.quark.building.feature.Trowel;
import vazkii.quark.decoration.feature.Grate;
import vazkii.quark.misc.feature.AncientTomes;
import vazkii.quark.world.feature.Basalt;
import vazkii.quark.world.feature.Biotite;
import vazkii.quark.world.feature.RevampStoneGen;
import vazkii.quark.world.feature.UndergroundBiomes;

import javax.annotation.Nonnull;
import java.util.Optional;

class ThermalExpansionQuarkIntegration implements IThermalExpansionIntegration
{
    @Override
    public void addRecipes()
    {
        if (QuarkUtils.isFeatureEnabled(PistonSpikes.class))
            SmelterManager.addRecycleRecipe(8_000, new ItemStack(PistonSpikes.iron_rod), new ItemStack(Items.IRON_INGOT), 1);
        if (QuarkUtils.isFeatureEnabled(Trowel.class))
            SmelterManager.addRecycleRecipe(8_000, new ItemStack(Trowel.trowel), new ItemStack(Items.IRON_INGOT), 1);
        if (QuarkUtils.isFeatureEnabled(Grate.class))
            SmelterManager.addRecycleRecipe(8_000, new ItemStack(Grate.grate), new ItemStack(Items.IRON_NUGGET), (9 * 6 / 16) * 4); // 12
        if (QuarkUtils.isFeatureEnabled(ChainLinkage.class))
            SmelterManager.addRecycleRecipe(8_000, new ItemStack(ChainLinkage.chain), new ItemStack(Items.IRON_NUGGET), (9 * 2 + 3) / 3); // 7
        if (QuarkUtils.isFeatureEnabled(MetalButtons.class))
        {
            if (MetalButtons.enableGold)
                SmelterManager.addRecycleRecipe(8_000, new ItemStack(MetalButtons.gold_button), new ItemStack(Items.GOLD_INGOT), 1);
            if (MetalButtons.enableIron)
                SmelterManager.addRecycleRecipe(8_000, new ItemStack(MetalButtons.iron_button), new ItemStack(Items.IRON_INGOT), 1);
        }
        
        if (QuarkUtils.isFeatureEnabled(Biotite.class))
        {
            FactorizerManager.addDefaultRecipe(new ItemStack(Biotite.biotite), new ItemStack(Biotite.biotite_block), 4);
            PulverizerManager.addRecipe(3_000, new ItemStack(Biotite.biotite_block, 1, 2), new ItemStack(Biotite.biotite));
        }
        
        if (QuarkUtils.isFeatureEnabled(SoulSandstone.class))
        {
            for (int meta = 1; meta <= 2; meta++)
                PulverizerManager.addRecipe(3_000, new ItemStack(SoulSandstone.soul_sandstone, 1, meta), new ItemStack(Blocks.SOUL_SAND), ItemMaterial.dustSulfur, 40);
        }
        
        
        if (QuarkUtils.isFeatureEnabled(UndergroundBiomes.class))
        {
            if (UndergroundBiomes.firestoneEnabled)
                CrucibleManager.addRecipe(40_000, new ItemStack(UndergroundBiomes.biome_cobblestone, 1, 0), new FluidStack(FluidRegistry.LAVA, 1000));
            if (UndergroundBiomes.icystoneEnabled)
                CrucibleManager.addRecipe(1_600, new ItemStack(UndergroundBiomes.biome_cobblestone, 1, 1), new FluidStack(FluidRegistry.WATER, 1000));
        }
        
        // Igneous Extruder
        if (QuarkUtils.isFeatureEnabled(Basalt.class))
            ExtruderManager.addRecipeIgneous(800, new ItemStack(Basalt.basalt), new FluidStack(FluidRegistry.LAVA, 0), new FluidStack(FluidRegistry.WATER, 1000));
        if (QuarkUtils.isFeatureEnabled(RevampStoneGen.class))
        {
            // Technically metamorphic rocks, but we don't have an upgrade for that (yet? maybe I'll look into it)
            if (RevampStoneGen.enableMarble)
                ExtruderManager.addRecipeIgneous(800, new ItemStack(RevampStoneGen.marble), new FluidStack(FluidRegistry.LAVA, 0), new FluidStack(FluidRegistry.WATER, 1000));
            if (RevampStoneGen.enableSlate)
                ExtruderManager.addRecipeIgneous(800, new ItemStack(RevampStoneGen.slate), new FluidStack(FluidRegistry.LAVA, 0), new FluidStack(FluidRegistry.WATER, 1000));
            // Sedimentary Deposition
            if (RevampStoneGen.enableLimestone)
                ExtruderManager.addRecipeSedimentary(6_400, new ItemStack(RevampStoneGen.limestone), new FluidStack(FluidRegistry.LAVA, 0), new FluidStack(FluidRegistry.WATER, 2000));
            if (RevampStoneGen.enableJasper)
                ExtruderManager.addRecipeSedimentary(6_400, new ItemStack(RevampStoneGen.jasper), new FluidStack(FluidRegistry.LAVA, 0), new FluidStack(FluidRegistry.WATER, 2000));
        }
    }
    
    @Override
    public void addPostInitRecipes()
    {
        if (QuarkConfiguration.ancientTomesCrafting && AncientTomes.ancient_tome != null)
        {
            EnchanterRecipe[] recipes = EnchanterManager.getRecipeList();
            
            for (EnchanterRecipe recipe : recipes)
            {
                Optional<Enchantment> result =
                    AncientTomes.validEnchants.stream()
                                              .filter(enchant ->
                                              {
                                                  ResourceLocation name = enchant.getRegistryName();
                                                  return name != null && name.toString().equals(recipe.getEnchantName());
                                              })
                                              .findAny();
                
                if (result.isPresent())
                {
                    Enchantment enchantment = result.get();
//                    ItemStack baseBook = new ItemStack(Items.ENCHANTED_BOOK);
                    ItemStack baseBook = ItemEnchantedBook.getEnchantedItemStack(new EnchantmentData(enchantment, 1));
                    ItemStack ancientTome = new ItemStack(AncientTomes.ancient_tome);
//                    ItemEnchantedBook.addEnchantment(baseBook, new EnchantmentData(enchantment, enchantment.getMaxLevel()));
                    ItemEnchantedBook.addEnchantment(ancientTome, new EnchantmentData(enchantment, enchantment.getMaxLevel()));
                    
                    if (baseBook.hasTagCompound() && ancientTome.hasTagCompound())
                    {
                        EnchanterManager.addRecipe(
                            recipe.getEnergy() * 10,
                            baseBook,
                            new ItemStack(recipe.getSecondaryInput().getItem(), recipe.getSecondaryInput().getMaxStackSize()),
                            ancientTome,
                            10_000,
                            EnchanterManager.Type.STANDARD);
                    }
                }
            }
        }
    }
    
    @Nonnull
    @Override
    public ModIds getModId()
    {
        return ModIds.QUARK;
    }
}
