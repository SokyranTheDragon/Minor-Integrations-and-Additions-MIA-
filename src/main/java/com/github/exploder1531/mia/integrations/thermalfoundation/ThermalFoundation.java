package com.github.exploder1531.mia.integrations.thermalfoundation;

import cofh.thermalfoundation.init.TFEquipment;
import com.github.exploder1531.mia.config.TfConfiguration;
import com.github.exploder1531.mia.integrations.ModIds;
import com.github.exploder1531.mia.integrations.base.IBaseMod;
import com.github.exploder1531.mia.integrations.base.IModIntegration;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.oredict.OreDictionary;
import slimeknights.mantle.util.RecipeMatch;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.materials.Material;
import slimeknights.tconstruct.library.smeltery.MeltingRecipe;

import java.util.function.BiConsumer;

import static com.github.exploder1531.mia.config.TfConfiguration.*;
import static com.github.exploder1531.mia.integrations.ModIds.*;

public class ThermalFoundation implements IBaseMod
{
    @Override
    public void register(BiConsumer<ModIds, IModIntegration> modIntegration)
    {
        if (enableXu2Integration && EXTRA_UTILITIES.isLoaded)
            modIntegration.accept(EXTRA_UTILITIES, new ExtraUtilsTFIntegration());
        if (enableJerIntegration && JER.isLoaded)
            modIntegration.accept(JER, new JerTFIntegration());
        if (enableDungeonTacticsIntegration && DUNGEON_TACTICS.isLoaded)
            modIntegration.accept(DUNGEON_TACTICS, new DungeonTacticsTFIntegration());
        if (HATCHERY.isLoaded)
            modIntegration.accept(HATCHERY, new HatcheryTFIntegration(enableHatcheryIntegration));
        if (enableFutureMcIntegration && FUTURE_MC.isLoaded)
            modIntegration.accept(FUTURE_MC, new FutureMcTFIntegration());
    }
    
    @Override
    public void init(FMLInitializationEvent event)
    {
        if (TfConfiguration.tfAdditionsEnabled)
        {
            FurnaceRecipes furnaceRecipes = FurnaceRecipes.instance();
            
            // Horse armors to nuggets
            for (TFEquipment.HorseArmor armor : TFEquipment.HorseArmor.values())
            {
                if (!armor.ingot.startsWith("ingot"))
                    continue;
                
                String suffix = armor.ingot.substring(5);
                Fluid fluid = FluidRegistry.getFluid(suffix.toLowerCase());
                NonNullList<ItemStack> items = OreDictionary.getOres("nugget" + suffix);
                
                if (fluid != null)
                    TinkerRegistry.registerMelting(new MeltingRecipe(RecipeMatch.of(armor.armor, Material.VALUE_Ingot * 4), fluid));
                
                if (items.size() > 0)
                    furnaceRecipes.addSmeltingRecipe(armor.armor, items.get(0), 0.1f);
            }
            
            // Armors to nuggets
            for (TFEquipment.ArmorSet armor : TFEquipment.ArmorSet.values())
            {
                if (!armor.ingot.startsWith("ingot"))
                    continue;
                
                String suffix = armor.ingot.substring(5);
                NonNullList<ItemStack> items = OreDictionary.getOres("nugget" + suffix);
                
                if (items.size() > 0)
                {
                    ItemStack nugget = items.get(0);
                    
                    furnaceRecipes.addSmeltingRecipe(armor.armorHelmet, nugget, 0.1f);
                    furnaceRecipes.addSmeltingRecipe(armor.armorChestplate, nugget, 0.1f);
                    furnaceRecipes.addSmeltingRecipe(armor.armorLegs, nugget, 0.1f);
                    furnaceRecipes.addSmeltingRecipe(armor.armorBoots, nugget, 0.1f);
                }
            }
            
            // Vanilla shear smelting
//            furnaceRecipes.addSmeltingRecipe(new ItemStack(Items.SHEARS), new ItemStack(Items.IRON_NUGGET), 0.1f);
            
            // Tools to nuggets
            // New tools for vanilla materials to nugget
            for (TFEquipment.ToolSetVanilla tool : TFEquipment.ToolSetVanilla.values())
            {
                if (!tool.ingot.startsWith("ingot"))
                    continue;
                
                String suffix = tool.ingot.substring(5);
                NonNullList<ItemStack> items = OreDictionary.getOres("nugget" + suffix);
                
                if (items.size() > 0)
                {
                    ItemStack nugget = items.get(0);
                    
                    furnaceRecipes.addSmeltingRecipe(tool.toolBow, nugget, 0.1f);
                    furnaceRecipes.addSmeltingRecipe(tool.toolExcavator, nugget, 0.1f);
                    furnaceRecipes.addSmeltingRecipe(tool.toolFishingRod, nugget, 0.1f);
                    furnaceRecipes.addSmeltingRecipe(tool.toolHammer, nugget, 0.1f);
                    furnaceRecipes.addSmeltingRecipe(tool.toolShears, nugget, 0.1f);
                    furnaceRecipes.addSmeltingRecipe(tool.toolShield, nugget, 0.1f);
                    furnaceRecipes.addSmeltingRecipe(tool.toolSickle, nugget, 0.1f);
                }
            }
            
            // Thermal tools tools to nugget
            for (TFEquipment.ToolSet tool : TFEquipment.ToolSet.values())
            {
                if (!tool.ingot.startsWith("ingot"))
                    continue;
                
                String suffix = tool.ingot.substring(5);
                NonNullList<ItemStack> items = OreDictionary.getOres("nugget" + suffix);
                
                if (items.size() > 0)
                {
                    ItemStack nugget = items.get(0);
                    
                    furnaceRecipes.addSmeltingRecipe(tool.toolShovel, nugget, 0.1f);
                    furnaceRecipes.addSmeltingRecipe(tool.toolPickaxe, nugget, 0.1f);
                    furnaceRecipes.addSmeltingRecipe(tool.toolAxe, nugget, 0.1f);
                    furnaceRecipes.addSmeltingRecipe(tool.toolHoe, nugget, 0.1f);
                    furnaceRecipes.addSmeltingRecipe(tool.toolBow, nugget, 0.1f);
                    furnaceRecipes.addSmeltingRecipe(tool.toolFishingRod, nugget, 0.1f);
                    furnaceRecipes.addSmeltingRecipe(tool.toolShears, nugget, 0.1f);
                    furnaceRecipes.addSmeltingRecipe(tool.toolSickle, nugget, 0.1f);
                    furnaceRecipes.addSmeltingRecipe(tool.toolHammer, nugget, 0.1f);
                    furnaceRecipes.addSmeltingRecipe(tool.toolExcavator, nugget, 0.1f);
                    furnaceRecipes.addSmeltingRecipe(tool.toolShield, nugget, 0.1f);
                }
            }
        }
    }
}
