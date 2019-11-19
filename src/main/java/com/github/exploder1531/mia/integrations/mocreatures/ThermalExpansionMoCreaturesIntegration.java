package com.github.exploder1531.mia.integrations.mocreatures;

import cofh.core.util.helpers.ItemHelper;
import cofh.thermalexpansion.util.managers.device.TapperManager;
import cofh.thermalexpansion.util.managers.machine.PulverizerManager;
import cofh.thermalexpansion.util.managers.machine.SawmillManager;
import cofh.thermalexpansion.util.managers.machine.SmelterManager;
import cofh.thermalfoundation.init.TFFluids;
import cofh.thermalfoundation.item.ItemMaterial;
import com.github.exploder1531.mia.integrations.ModIds;
import com.github.exploder1531.mia.integrations.thermalexpansion.IThermalExpansionIntegration;
import drzhark.mocreatures.block.MoCBlock;
import drzhark.mocreatures.init.MoCBlocks;
import drzhark.mocreatures.init.MoCItems;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import javax.annotation.Nonnull;

public class ThermalExpansionMoCreaturesIntegration implements IThermalExpansionIntegration
{
    @Override
    public void addRecipes()
    {
        int energy = 6_000;
        // Induction smelter
        SmelterManager.addRecycleRecipe(energy, new ItemStack(MoCItems.tusksIron), new ItemStack(Items.IRON_INGOT), 2);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(MoCItems.silversword), ItemMaterial.ingotSilver, 2);
        
        // Pulverizer
        energy = 3_000;
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(MoCItems.sharkteeth), new ItemStack(Items.DYE, 2, 15), 2);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(MoCItems.sharksword), new ItemStack(MoCItems.sharkteeth), 2);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(MoCItems.woolball), new ItemStack(Items.STRING), 4);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(MoCItems.sugarlump), new ItemStack(Items.SUGAR), 4);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(MoCItems.tusksWood), ItemMaterial.dustWood, 10);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(MoCItems.tusksDiamond), new ItemStack(Items.DIAMOND), 2);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(MoCItems.horsearmorcrystal), new ItemStack(Items.DIAMOND), 2);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(MoCItems.sugarlump), new ItemStack(Items.SUGAR), 4);
        PulverizerManager.addRecipe(energy, new ItemStack(MoCItems.medallion), new ItemStack(Items.LEATHER), new ItemStack(Items.GOLD_INGOT), 25);
        // Chitin armor
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(MoCItems.scorpHelmetDirt), new ItemStack(MoCItems.chitin), 2);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(MoCItems.scorpPlateDirt), new ItemStack(MoCItems.chitin), 4);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(MoCItems.scorpLegsDirt), new ItemStack(MoCItems.chitin), 3);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(MoCItems.scorpBootsDirt), new ItemStack(MoCItems.chitin), 2);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(MoCItems.scorpHelmetCave), new ItemStack(MoCItems.chitinCave), 2);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(MoCItems.scorpPlateCave), new ItemStack(MoCItems.chitinCave), 4);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(MoCItems.scorpLegsCave), new ItemStack(MoCItems.chitinCave), 3);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(MoCItems.scorpBootsCave), new ItemStack(MoCItems.chitinCave), 2);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(MoCItems.scorpHelmetFrost), new ItemStack(MoCItems.chitinFrost), 2);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(MoCItems.scorpPlateFrost), new ItemStack(MoCItems.chitinFrost), 4);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(MoCItems.scorpLegsFrost), new ItemStack(MoCItems.chitinFrost), 3);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(MoCItems.scorpBootsFrost), new ItemStack(MoCItems.chitinFrost), 2);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(MoCItems.scorpHelmetNether), new ItemStack(MoCItems.chitinNether), 2);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(MoCItems.scorpPlateNether), new ItemStack(MoCItems.chitinNether), 4);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(MoCItems.scorpLegsNether), new ItemStack(MoCItems.chitinNether), 3);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(MoCItems.scorpBootsNether), new ItemStack(MoCItems.chitinNether), 2);
        
        // Sawmill
        energy = 1_500;
        SawmillManager.addRecipe(energy, new ItemStack(MoCBlocks.mocLog, 1, 0), new ItemStack(MoCBlocks.mocPlank, 6, 0), ItemMaterial.dustWood, 100);
        SawmillManager.addRecipe(energy, new ItemStack(MoCBlocks.mocLog, 1, 1), new ItemStack(MoCBlocks.mocPlank, 6, 1), ItemMaterial.dustWood, 100);
        TapperManager.addStandardMapping(new ItemStack(MoCBlocks.mocLog, 1, 0), new FluidStack(TFFluids.fluidResin, 100));
        TapperManager.addStandardMapping(new ItemStack(MoCBlocks.mocLog, 1, 1), new FluidStack(TFFluids.fluidResin, 100));
        SawmillManager.addRecycleRecipe(energy, new ItemStack(MoCItems.horsesaddle), new ItemStack(Items.LEATHER), 3);
        SawmillManager.addRecycleRecipe(energy, new ItemStack(MoCItems.whip), new ItemStack(Items.LEATHER), 3);
        SawmillManager.addRecipe(energy, new ItemStack(MoCItems.elephantChest), new ItemStack(Blocks.CHEST), new ItemStack(MoCItems.animalHide), 50);
        SawmillManager.addRecipe(energy, new ItemStack(MoCItems.elephantGarment), new ItemStack(MoCItems.medallion), new ItemStack(Items.STRING, 12), 100);
        SawmillManager.addRecipe(energy, new ItemStack(MoCItems.elephantHarness), new ItemStack(MoCItems.animalHide, 2), new ItemStack(Items.STRING, 8), 100);
        SawmillManager.addRecipe(energy, new ItemStack(MoCItems.elephantHowdah), new ItemStack(Items.STRING, 12));
        SawmillManager.addRecipe(energy, new ItemStack(MoCItems.mammothPlatform), ItemHelper.cloneStack(ItemMaterial.dustWood, 16), new ItemStack(Items.LEAD, 1), 150);
        SawmillManager.addRecipe(energy, new ItemStack(MoCItems.litterbox), new ItemStack(Blocks.PLANKS, 2), new ItemStack(Blocks.SAND), 25);
        for (int i = 0; i < MoCItems.kittybed.length; i++)
            SawmillManager.addRecipe(energy, new ItemStack(MoCItems.kittybed[i]), new ItemStack(Blocks.PLANKS, 1), new ItemStack(Blocks.WOOL, 1, i));
        // Scalie
        SawmillManager.addRecycleRecipe(energy, new ItemStack(MoCItems.helmetCroc), new ItemStack(MoCItems.hideCroc), 2);
        SawmillManager.addRecycleRecipe(energy, new ItemStack(MoCItems.plateCroc), new ItemStack(MoCItems.hideCroc), 4);
        SawmillManager.addRecycleRecipe(energy, new ItemStack(MoCItems.legsCroc), new ItemStack(MoCItems.hideCroc), 3);
        SawmillManager.addRecycleRecipe(energy, new ItemStack(MoCItems.bootsCroc), new ItemStack(MoCItems.hideCroc), 2);
        // Furry
        SawmillManager.addRecycleRecipe(energy, new ItemStack(MoCItems.helmetFur), new ItemStack(MoCItems.fur), 2);
        SawmillManager.addRecycleRecipe(energy, new ItemStack(MoCItems.chestFur), new ItemStack(MoCItems.fur), 4);
        SawmillManager.addRecycleRecipe(energy, new ItemStack(MoCItems.legsFur), new ItemStack(MoCItems.fur), 3);
        SawmillManager.addRecycleRecipe(energy, new ItemStack(MoCItems.bootsFur), new ItemStack(MoCItems.fur), 2);
        // Hidey
        SawmillManager.addRecycleRecipe(energy, new ItemStack(MoCItems.helmetHide), new ItemStack(MoCItems.animalHide), 2);
        SawmillManager.addRecycleRecipe(energy, new ItemStack(MoCItems.chestHide), new ItemStack(MoCItems.animalHide), 4);
        SawmillManager.addRecycleRecipe(energy, new ItemStack(MoCItems.legsHide), new ItemStack(MoCItems.animalHide), 3);
        SawmillManager.addRecycleRecipe(energy, new ItemStack(MoCItems.bootsHide), new ItemStack(MoCItems.animalHide), 2);
    }
    
    @Nonnull
    @Override
    public String getModId()
    {
        return ModIds.MO_CREATURES;
    }
}
