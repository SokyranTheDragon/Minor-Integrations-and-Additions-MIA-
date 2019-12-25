package com.github.exploder1531.mia.integrations.quark;

import cofh.thermalfoundation.item.ItemMaterial;
import com.github.exploder1531.mia.integrations.ModIds;
import com.github.exploder1531.mia.integrations.ModLoadStatus;
import com.github.exploder1531.mia.integrations.xu2.IExtraUtilsIntegration;
import com.github.exploder1531.mia.utilities.ItemStackUtils;
import com.rwtema.extrautils2.api.machine.MachineSlotItem;
import com.rwtema.extrautils2.api.machine.RecipeBuilder;
import com.rwtema.extrautils2.api.machine.XUMachineCrusher;
import com.rwtema.extrautils2.api.machine.XUMachineGenerators;
import com.rwtema.extrautils2.blocks.BlockTerraformer;
import com.rwtema.extrautils2.tile.TileTerraformerClimograph;
import com.rwtema.extrautils2.utils.datastructures.ItemRef;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import vazkii.quark.automation.feature.ColorSlime;
import vazkii.quark.automation.feature.SugarBlock;
import vazkii.quark.decoration.feature.BlazeLantern;
import vazkii.quark.misc.feature.BlackAsh;
import vazkii.quark.world.feature.Biotite;
import vazkii.quark.world.feature.UndergroundBiomes;
import vazkii.quark.world.feature.Wraiths;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

class ExtraUtilsQuarkIntegration implements IExtraUtilsIntegration
{
    @Override
    public void addRecipes(@Nullable MachineSlotItem slimeSecondary)
    {
        if (Biotite.biotite_block != null)
        {
            XUMachineCrusher.addRecipe(new ItemStack(Biotite.biotite_ore), new ItemStack(Biotite.biotite), new ItemStack(Biotite.biotite, 3), 0.2f);
            for (int meta = 0; meta <= 2; meta++)
                XUMachineCrusher.addRecipe(new ItemStack(Biotite.biotite_block, 1, meta), new ItemStack(Biotite.biotite, 4));
            ItemStack slab = ItemStackUtils.getStack(ModIds.QUARK, "biotite_slab");
            if (!slab.isEmpty())
                XUMachineCrusher.addRecipe(slab, new ItemStack(Biotite.biotite, 1));
            ItemStack stairs = ItemStackUtils.getStack(ModIds.QUARK, "biotite_stairs");
            if (!stairs.isEmpty())
                XUMachineCrusher.addRecipe(stairs, new ItemStack(Biotite.biotite, 2));
        }
        
        if (UndergroundBiomes.biome_cobblestone != null)
        {
            if (UndergroundBiomes.firestoneEnabled)
            {
                XUMachineCrusher.addRecipe(new ItemStack(UndergroundBiomes.biome_cobblestone, 1, 0), new ItemStack(Blocks.GRAVEL), new ItemStack(Items.BLAZE_POWDER), 0.05f);
                TileTerraformerClimograph.register(BlockTerraformer.Type.HEATER, ItemRef.wrap(new ItemStack(UndergroundBiomes.biome_cobblestone, 1, 0)), 8);
            }
            if (UndergroundBiomes.icystoneEnabled)
            {
                XUMachineCrusher.addRecipe(new ItemStack(UndergroundBiomes.biome_cobblestone, 1, 1), new ItemStack(Blocks.GRAVEL), ModLoadStatus.thermalFoundationLoaded ? ItemMaterial.dustBlizz : new ItemStack(Items.SNOWBALL), 0.05f);
                TileTerraformerClimograph.register(BlockTerraformer.Type.COOLER, ItemRef.wrap(new ItemStack(UndergroundBiomes.biome_cobblestone, 1, 1)), 8);
            }
        }
        
        if (BlazeLantern.blaze_lantern != null)
        {
            XUMachineCrusher.addRecipe(new ItemStack(BlazeLantern.blaze_lantern), new ItemStack(Items.BLAZE_POWDER, 16));
            TileTerraformerClimograph.register(BlockTerraformer.Type.HEATER, ItemRef.wrap(BlazeLantern.blaze_lantern), 37);
        }
        
        if (SugarBlock.sugar_block != null)
            TileTerraformerClimograph.register(BlockTerraformer.Type.HUMIDIFIER, ItemRef.wrap(SugarBlock.sugar_block), 36);
        
        if (BlackAsh.black_ash != null)
            XUMachineGenerators.DEATH_GENERATOR.recipes_registry.addRecipe(
                    RecipeBuilder.newbuilder(XUMachineGenerators.DEATH_GENERATOR)
                                 .setRFRate(10_333, 80.0f)
                                 .setItemInput(XUMachineGenerators.INPUT_ITEM, new ItemStack(BlackAsh.black_ash), 1)
                                 .build());
        
        if (Wraiths.soul_bead != null)
            XUMachineGenerators.DEATH_GENERATOR.recipes_registry.addRecipe(
                    RecipeBuilder.newbuilder(XUMachineGenerators.DEATH_GENERATOR)
                            .setRFRate(8_000, 20.0f)
                            .setItemInput(XUMachineGenerators.INPUT_ITEM, new ItemStack(Wraiths.soul_bead), 1)
                            .build());
        
        // Slime generator
        if (ColorSlime.color_slime != null && slimeSecondary != null)
        {
            List<ItemStack> slimeList = new ArrayList<>();
            
            for (int meta = 0; meta <= 4; meta++)
                slimeList.add(new ItemStack(ColorSlime.color_slime, 1, meta));
            
            XUMachineGenerators.SLIME_GENERATOR.recipes_registry.addRecipe(
                    RecipeBuilder.newbuilder(XUMachineGenerators.SLIME_GENERATOR)
                                 .setRFRate(432_000, 400.0f)
                                 .setItemInput(XUMachineGenerators.INPUT_ITEM, slimeList, 1)
                                 .setItemInput(slimeSecondary, new ItemStack(Items.MILK_BUCKET, 1))
                                 .build());
        }
    }
    
    @Nonnull
    @Override
    public String getModId()
    {
        return ModIds.QUARK;
    }
}
