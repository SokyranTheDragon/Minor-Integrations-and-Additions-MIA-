package com.github.exploder1531.mia.integrations.xu2;

import cofh.thermalexpansion.util.managers.device.FactorizerManager;
import cofh.thermalexpansion.util.managers.machine.EnchanterManager;
import cofh.thermalexpansion.util.managers.machine.PulverizerManager;
import cofh.thermalexpansion.util.managers.machine.SmelterManager;
import cofh.thermalfoundation.item.ItemMaterial;
import com.github.exploder1531.mia.integrations.ModIds;
import com.github.exploder1531.mia.integrations.thermalexpansion.IThermalExpansionIntegration;
import com.rwtema.extrautils2.backend.entries.XU2Entries;
import com.rwtema.extrautils2.items.ItemIngredients;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

class ThermalExpansionExtraUtilsIntegration implements IThermalExpansionIntegration
{
    @Override
    public void addRecipes()
    {
        int energy = 6_000;
        // Induction smelter
        SmelterManager.addRecycleRecipe(energy, new ItemStack(XU2Entries.sickles[2].value), new ItemStack(Items.IRON_INGOT), 2);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(XU2Entries.sickles[3].value), new ItemStack(Items.GOLD_INGOT), 2);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(XU2Entries.itemGlassCutter.value), new ItemStack(Items.IRON_INGOT), 1);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(XU2Entries.trowel.value), new ItemStack(Items.IRON_INGOT), 1);
        
        
        // Pulverizer
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(XU2Entries.sickles[0].value), ItemMaterial.dustWood, 2);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(XU2Entries.sickles[4].value), new ItemStack(Items.DIAMOND), 2);
        
        
        // Arcane Enscroller
        EnchanterManager.addDefaultEnchantmentRecipe(new ItemStack(XU2Entries.itemIngredients.value, 1, ItemIngredients.Type.DEMON_INGOT.meta), "extrautils2:xu.kaboomerang", 2);
        EnchanterManager.addDefaultEnchantmentRecipe(new ItemStack(XU2Entries.itemEnderShard.value, 12), "extrautils2:xu.zoomerang", 2);
        EnchanterManager.addDefaultEnchantmentRecipe(new ItemStack(XU2Entries.itemIngredients.value, 4, ItemIngredients.Type.RED_COAL.meta), "extrautils2:xu.burnerang", 3);
        EnchanterManager.addDefaultEnchantmentRecipe(new ItemStack(XU2Entries.openium.value, 1, 0), "extrautils2:xu.bladerang", 2);
        EnchanterManager.addDefaultEnchantmentRecipe(new ItemStack(XU2Entries.sickles[2].value), "extrautils2:xu.boomereaperang", 1);
    
        FactorizerManager.removeRecipe(XU2Entries.unstableIngots.newStackMeta(0), true);
        FactorizerManager.removeRecipe(XU2Entries.unstableIngots.newStackMeta(1), false);
        
        FactorizerManager.addDefaultRecipe(XU2Entries.unstableIngots.newStackMeta(1), XU2Entries.unstableIngots.newStackMeta(2));
    }
    
    @Nonnull
    @Override
    public ModIds getModId()
    {
        return ModIds.EXTRA_UTILITIES;
    }
}
