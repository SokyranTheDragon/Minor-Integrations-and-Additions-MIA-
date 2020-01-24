package com.github.sokyranthedragon.mia.integrations.tconstruct;

import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.integrations.xu2.IExtraUtilsIntegration;
import com.rwtema.extrautils2.api.machine.MachineSlotItem;
import com.rwtema.extrautils2.api.machine.RecipeBuilder;
import com.rwtema.extrautils2.api.machine.XUMachineGenerators;
import com.rwtema.extrautils2.machine.EnergyBaseRecipe;
import com.rwtema.extrautils2.utils.datastructures.ItemRef;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import slimeknights.tconstruct.TConstruct;
import slimeknights.tconstruct.gadgets.TinkerGadgets;
import slimeknights.tconstruct.shared.TinkerCommons;
import slimeknights.tconstruct.shared.block.BlockSlime;
import slimeknights.tconstruct.world.TinkerWorld;
import slimeknights.tconstruct.world.block.BlockSlimeDirt;
import slimeknights.tconstruct.world.block.BlockSlimeGrass;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

class ExtraUtilsTConstructIntegration implements IExtraUtilsIntegration
{
    @Override
    public void addRecipes(@Nullable MachineSlotItem slimeSecondary)
    {
        boolean tinkerGadgets = TConstruct.pulseManager.isPulseLoaded(TinkerGadgets.PulseId);
        
        // Explosion generator
        if (tinkerGadgets)
            XUMachineGenerators.TNT_GENERATOR.recipes_registry.addRecipe(new EnergyBaseRecipe.EnergyBaseItem(ItemRef.wrap(new ItemStack(TinkerGadgets.throwball, 1, 1)), 80_000, 160));
        
        // Slime generator
        if (slimeSecondary != null)
        {
            boolean tinkerCommons = TConstruct.pulseManager.isPulseLoaded(TinkerCommons.PulseId);
            boolean tinkerWorld = TConstruct.pulseManager.isPulseLoaded(TinkerWorld.PulseId);
            
            // Slime blocks
            List<ItemStack> slimeList = new ArrayList<>();
            if (tinkerCommons)
            {
                for (BlockSlime.SlimeType slimeType : BlockSlime.SlimeType.values())
                    slimeList.add(new ItemStack(TinkerCommons.blockSlime, 1, slimeType.meta));
                
                XUMachineGenerators.SLIME_GENERATOR.recipes_registry.addRecipe(
                        RecipeBuilder.newbuilder(XUMachineGenerators.SLIME_GENERATOR)
                                     .setRFRate(432_000, 400.0F)
                                     .setItemInput(XUMachineGenerators.INPUT_ITEM, slimeList, 1)
                                     .setItemInput(slimeSecondary, new ItemStack(Items.MILK_BUCKET, 1))
                                     .build());
                
                slimeList = new ArrayList<>();
            }
            
            // Congealed slime, dirt, grass
            if (tinkerCommons)
            {
                for (BlockSlime.SlimeType slimeType : BlockSlime.SlimeType.values())
                    slimeList.add(new ItemStack(TinkerCommons.blockSlimeCongealed, 1, slimeType.meta));
            }
            if (tinkerWorld)
            {
                for (BlockSlimeDirt.DirtType dirtType : BlockSlimeDirt.DirtType.values())
                    slimeList.add(new ItemStack(TinkerWorld.slimeDirt, 1, dirtType.meta));
                for (BlockSlimeGrass.DirtType dirtType : BlockSlimeGrass.DirtType.values())
                    for (BlockSlimeGrass.FoliageType foliageType : BlockSlimeGrass.FoliageType.values())
                        slimeList.add(new ItemStack(TinkerWorld.slimeGrass, 1, dirtType.ordinal() + foliageType.ordinal() * 5));
            }
            
            if (!slimeList.isEmpty())
            {
                XUMachineGenerators.SLIME_GENERATOR.recipes_registry.addRecipe(
                        RecipeBuilder.newbuilder(XUMachineGenerators.SLIME_GENERATOR)
                                     .setRFRate(192_000, 400.0F)
                                     .setItemInput(XUMachineGenerators.INPUT_ITEM, slimeList, 1)
                                     .setItemInput(slimeSecondary, new ItemStack(Items.MILK_BUCKET, 1))
                                     .build());
                
                slimeList = new ArrayList<>();
            }
            
            // Slimedrops
            if (tinkerCommons)
            {
                slimeList.add(TinkerCommons.slimedropGreen);
                slimeList.add(TinkerCommons.slimedropBlue);
                slimeList.add(TinkerCommons.slimedropPurple);
                slimeList.add(TinkerCommons.slimedropMagma);
                slimeList.add(TinkerCommons.slimedropBlood);
                
                XUMachineGenerators.SLIME_GENERATOR.recipes_registry.addRecipe(
                        RecipeBuilder.newbuilder(XUMachineGenerators.SLIME_GENERATOR)
                                     .setRFRate(192_000, 400.0F)
                                     .setItemInput(XUMachineGenerators.INPUT_ITEM, slimeList, 3)
                                     .setItemInput(slimeSecondary, new ItemStack(Items.MILK_BUCKET, 1))
                                     .build());
                
                slimeList = new ArrayList<>();
            }
            
            // Sapplings
            if (tinkerWorld)
            {
                for (BlockSlimeGrass.FoliageType foliage : BlockSlimeGrass.FoliageType.values())
                    slimeList.add(new ItemStack(TinkerWorld.slimeSapling, 1, foliage.getMeta()));
                
                XUMachineGenerators.SLIME_GENERATOR.recipes_registry.addRecipe(
                        RecipeBuilder.newbuilder(XUMachineGenerators.SLIME_GENERATOR)
                                     .setRFRate(192_000, 400.0F)
                                     .setItemInput(XUMachineGenerators.INPUT_ITEM, slimeList, 4)
                                     .setItemInput(slimeSecondary, new ItemStack(Items.MILK_BUCKET, 1))
                                     .build());
                
                slimeList = new ArrayList<>();
            }
            
            // Leaves
            if (tinkerWorld)
            {
                for (BlockSlimeGrass.FoliageType foliage : BlockSlimeGrass.FoliageType.values())
                    slimeList.add(new ItemStack(TinkerWorld.slimeLeaves, 1, foliage.getMeta()));
                
                XUMachineGenerators.SLIME_GENERATOR.recipes_registry.addRecipe(
                        RecipeBuilder.newbuilder(XUMachineGenerators.SLIME_GENERATOR)
                                     .setRFRate(192_000, 400.0F)
                                     .setItemInput(XUMachineGenerators.INPUT_ITEM, slimeList, 8)
                                     .setItemInput(slimeSecondary, new ItemStack(Items.MILK_BUCKET, 1))
                                     .build());
                
                slimeList = new ArrayList<>();
            }
            
            // Buckets of slime
            FluidStack fluid = FluidRegistry.getFluidStack("blueslime", 1_000);
            if (fluid != null)
            {
                ItemStack bucket = FluidUtil.getFilledBucket(fluid);
                
                if (!bucket.isEmpty())
                    slimeList.add(bucket);
            }
            fluid = FluidRegistry.getFluidStack("purpleslime", 1_000);
            if (fluid != null)
            {
                ItemStack bucket = FluidUtil.getFilledBucket(fluid);
                
                if (!bucket.isEmpty())
                    slimeList.add(bucket);
            }
            
            if (slimeList.size() > 0)
            {
                XUMachineGenerators.SLIME_GENERATOR.recipes_registry.addRecipe(
                        RecipeBuilder.newbuilder(XUMachineGenerators.SLIME_GENERATOR)
                                     .setRFRate(192_000, 400.0F)
                                     .setItemInput(XUMachineGenerators.INPUT_ITEM, slimeList, 1)
                                     .setItemInput(slimeSecondary, new ItemStack(Items.MILK_BUCKET, 1))
                                     .build());
                
                slimeList = new ArrayList<>();
            }
            
            
            // Slime channels
            if (tinkerGadgets)
            {
                for (BlockSlime.SlimeType slimeType : BlockSlime.SlimeType.values())
                    slimeList.add(new ItemStack(TinkerGadgets.slimeChannel, 1, slimeType.getMeta()));
                
                XUMachineGenerators.SLIME_GENERATOR.recipes_registry.addRecipe(
                        RecipeBuilder.newbuilder(XUMachineGenerators.SLIME_GENERATOR)
                                     .setRFRate(192_000, 400.0F)
                                     .setItemInput(XUMachineGenerators.INPUT_ITEM, slimeList, 3)
                                     .setItemInput(slimeSecondary, new ItemStack(Items.MILK_BUCKET, 1))
                                     .build());
                
                slimeList = new ArrayList<>();
            }
            
            // Slimy mud
            // Slightly stronger than normal
            if (tinkerCommons)
            {
                slimeList.add(TinkerCommons.slimyMudBlue);
                slimeList.add(TinkerCommons.slimyMudGreen);
                slimeList.add(TinkerCommons.slimyMudMagma);
                
                XUMachineGenerators.SLIME_GENERATOR.recipes_registry.addRecipe(
                        RecipeBuilder.newbuilder(XUMachineGenerators.SLIME_GENERATOR)
                                     .setRFRate(240_000, 400.0f)
                                     .setItemInput(XUMachineGenerators.INPUT_ITEM, slimeList, 1)
                                     .setItemInput(slimeSecondary, new ItemStack(Items.MILK_BUCKET, 1))
                                     .build());
                
                slimeList = new ArrayList<>();
            }
            
            // Slime crystals
            // Same total energy as mud, but require lava instead of milk and produce more energy per tick.
            if (tinkerCommons)
            {
                slimeList.add(TinkerCommons.matSlimeCrystalBlue);
                slimeList.add(TinkerCommons.matSlimeCrystalGreen);
                slimeList.add(TinkerCommons.matSlimeCrystalMagma);
                
                XUMachineGenerators.SLIME_GENERATOR.recipes_registry.addRecipe(
                        RecipeBuilder.newbuilder(XUMachineGenerators.SLIME_GENERATOR)
                                     .setRFRate(240_000, 800.0f)
                                     .setItemInput(XUMachineGenerators.INPUT_ITEM, slimeList, 1)
                                     .setItemInput(slimeSecondary, new ItemStack(Items.LAVA_BUCKET, 1))
                                     .build());
                
                // Knighslime
                XUMachineGenerators.SLIME_GENERATOR.recipes_registry.addRecipe(
                        RecipeBuilder.newbuilder(XUMachineGenerators.SLIME_GENERATOR)
                                     .setRFRate(648_000, 800.0f)
                                     .setItemInput(XUMachineGenerators.INPUT_ITEM, TinkerCommons.blockKnightSlime, 1)
                                     .setItemInput(slimeSecondary, new ItemStack(Items.LAVA_BUCKET, 1))
                                     .build());
                
                XUMachineGenerators.SLIME_GENERATOR.recipes_registry.addRecipe(
                        RecipeBuilder.newbuilder(XUMachineGenerators.SLIME_GENERATOR)
                                     .setRFRate(648_000, 800.0f)
                                     .setItemInput(XUMachineGenerators.INPUT_ITEM, TinkerCommons.ingotKnightSlime, 9)
                                     .setItemInput(slimeSecondary, new ItemStack(Items.LAVA_BUCKET, 1))
                                     .build());
                
                XUMachineGenerators.SLIME_GENERATOR.recipes_registry.addRecipe(
                        RecipeBuilder.newbuilder(XUMachineGenerators.SLIME_GENERATOR)
                                     .setRFRate(512_000, 800.0f)
                                     .setItemInput(XUMachineGenerators.INPUT_ITEM, TinkerCommons.nuggetKnightSlime, 64)
                                     .setItemInput(slimeSecondary, new ItemStack(Items.LAVA_BUCKET, 1))
                                     .build());
            }
            
            fluid = FluidRegistry.getFluidStack("knightslime", 1_000);
            if (fluid != null)
            {
                ItemStack bucket = FluidUtil.getFilledBucket(fluid);
                
                if (!bucket.isEmpty())
                    XUMachineGenerators.SLIME_GENERATOR.recipes_registry.addRecipe(
                            RecipeBuilder.newbuilder(XUMachineGenerators.SLIME_GENERATOR)
                                         .setRFRate(500_000, 800.0f)
                                         .setItemInput(XUMachineGenerators.INPUT_ITEM, bucket, 1)
                                         .setItemInput(slimeSecondary, new ItemStack(Items.LAVA_BUCKET, 1))
                                         .build());
            }
        }
    }
    
    @Nonnull
    @Override
    public ModIds getModId()
    {
        return ModIds.TINKERS_CONSTRUCT;
    }
}
