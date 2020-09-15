package com.github.sokyranthedragon.mia.integrations.aether;

import codechicken.lib.colour.EnumColour;
import com.gildedgames.the_aether.blocks.BlocksAether;
import com.gildedgames.the_aether.items.ItemsAether;
import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.integrations.xu2.IExtraUtilsIntegration;
import com.rwtema.extrautils2.api.machine.MachineSlotItem;
import com.rwtema.extrautils2.api.machine.XUMachineCrusher;
import com.rwtema.extrautils2.tile.TileTerraformerClimograph;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import javax.annotation.Nullable;

import static com.rwtema.extrautils2.api.machine.XUMachineGenerators.*;
import static com.rwtema.extrautils2.blocks.BlockTerraformer.Type.COOLER;
import static com.rwtema.extrautils2.machine.EnergyBaseRecipe.EnergyBaseItem;
import static com.rwtema.extrautils2.utils.datastructures.ItemRef.wrap;

@MethodsReturnNonnullByDefault
class ExtraUtilsAetherIntegration implements IExtraUtilsIntegration
{
    @Override
    public void addRecipes(@Nullable MachineSlotItem slimeGeneratorSecondary)
    {
        POTION_GENERATOR.recipes_registry.addRecipe(
            new EnergyBaseItem(wrap(new ItemStack(ItemsAether.skyroot_bucket, 1, 2)), 3_200, 40));
        POTION_GENERATOR.recipes_registry.addRecipe(
            new EnergyBaseItem(wrap(new ItemStack(ItemsAether.skyroot_bucket, 1, 3)), 6_400, 80));
        
        ENCHANT_GENERATOR.recipes_registry.addRecipe(
            new EnergyBaseItem(wrap(new ItemStack(ItemsAether.ambrosium_shard)), 6_400));
        
        ICE_GENERATOR.recipes_registry.addRecipe(
            new EnergyBaseItem(wrap(BlocksAether.icestone), 16_000, 80));
        
        TileTerraformerClimograph.register(COOLER, wrap(BlocksAether.icestone), 14);
        
        XUMachineCrusher.addRecipe(new ItemStack(ItemsAether.blue_cape), new ItemStack(Items.STRING, 18), new ItemStack(Items.DYE, 1, EnumColour.BLUE.getDyeMeta()), 30);
        XUMachineCrusher.addRecipe(new ItemStack(ItemsAether.red_cape), new ItemStack(Items.STRING, 18), new ItemStack(Items.DYE, 1, EnumColour.RED.getDyeMeta()), 30);
        XUMachineCrusher.addRecipe(new ItemStack(ItemsAether.white_cape), new ItemStack(Items.STRING, 18), new ItemStack(Items.DYE, 1, EnumColour.WHITE.getDyeMeta()), 30);
        XUMachineCrusher.addRecipe(new ItemStack(ItemsAether.yellow_cape), new ItemStack(Items.STRING, 18), new ItemStack(Items.DYE, 1, EnumColour.YELLOW.getDyeMeta()), 30);
    }
    
    @Override
    public ModIds getModId()
    {
        return ModIds.AETHER;
    }
}
