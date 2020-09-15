package com.github.sokyranthedragon.mia.integrations.thermalfoundation;

import cofh.thermalfoundation.init.TFEquipment;
import cofh.thermalfoundation.item.ItemMaterial;
import com.gildedgames.the_aether.api.freezables.AetherFreezableFuel;
import com.github.sokyranthedragon.mia.config.MiaConfig;
import com.github.sokyranthedragon.mia.config.TfConfiguration;
import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.integrations.base.IBaseMod;
import com.github.sokyranthedragon.mia.integrations.base.IModIntegration;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.function.BiConsumer;

import static com.github.sokyranthedragon.mia.config.TfConfiguration.*;

public class ThermalFoundation implements IBaseMod
{
    @Override
    public void register(BiConsumer<ModIds, IModIntegration> modIntegration)
    {
        if (enableXu2Integration && ModIds.EXTRA_UTILITIES.isLoaded)
            modIntegration.accept(ModIds.EXTRA_UTILITIES, new ExtraUtilsTFIntegration());
        if (enableJerIntegration && ModIds.JER.isLoaded)
            modIntegration.accept(ModIds.JER, new JerTFIntegration());
        if (enableDungeonTacticsIntegration && ModIds.DUNGEON_TACTICS.isLoaded)
            modIntegration.accept(ModIds.DUNGEON_TACTICS, new DungeonTacticsTFIntegration());
        if (ModIds.HATCHERY.isLoaded)
            modIntegration.accept(ModIds.HATCHERY, new HatcheryTFIntegration(enableHatcheryIntegration));
        if (enableFutureMcIntegration && ModIds.FUTURE_MC.isLoaded)
            modIntegration.accept(ModIds.FUTURE_MC, new FutureMcTFIntegration());
        if (enableQuarkIntegration && ModIds.QUARK.isLoaded)
            modIntegration.accept(ModIds.QUARK, new QuarkTFIntegration());
        if (false && ModIds.TINKERS_CONSTRUCT.isLoaded)
            modIntegration.accept(ModIds.TINKERS_CONSTRUCT, new TConstructTFIntegration());
    }
    
    @Override
    public void init(FMLInitializationEvent event)
    {
        if (TfConfiguration.tfAdditionsEnabled && !MiaConfig.disableAllRecipes)
        {
            FurnaceRecipes furnaceRecipes = FurnaceRecipes.instance();
            
            // Horse armors to nuggets
            for (TFEquipment.HorseArmor armor : TFEquipment.HorseArmor.values())
            {
                if (!armor.ingot.startsWith("ingot"))
                    continue;
                
                String suffix = armor.ingot.substring(5);
                NonNullList<ItemStack> items = OreDictionary.getOres("nugget" + suffix);
                
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
    
    @Override
    public void registerFreezableFuel(IForgeRegistry<AetherFreezableFuel> event)
    {
        event.register(new AetherFreezableFuel(ItemMaterial.dustCryotheum, 500));
    }
}
