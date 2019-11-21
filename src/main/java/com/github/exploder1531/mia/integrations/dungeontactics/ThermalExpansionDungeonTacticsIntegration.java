package com.github.exploder1531.mia.integrations.dungeontactics;

import cofh.thermalexpansion.util.managers.machine.EnchanterManager;
import cofh.thermalexpansion.util.managers.machine.InsolatorManager;
import cofh.thermalexpansion.util.managers.machine.PulverizerManager;
import cofh.thermalexpansion.util.managers.machine.SmelterManager;
import cofh.thermalfoundation.item.ItemFertilizer;
import cofh.thermalfoundation.item.ItemMaterial;
import com.github.exploder1531.mia.integrations.ModIds;
import com.github.exploder1531.mia.integrations.thermalexpansion.IThermalExpansionIntegration;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import pegbeard.dungeontactics.handlers.DTBlocks;
import pegbeard.dungeontactics.handlers.DTItems;

import javax.annotation.Nonnull;

public class ThermalExpansionDungeonTacticsIntegration implements IThermalExpansionIntegration
{
    @Override
    public void addRecipes()
    {
        // Induction smelter
        int energy = 6_000;
        // Iron
        SmelterManager.addRecycleRecipe(energy, new ItemStack(DTItems.IRON_HAMMER), new ItemStack(Items.IRON_INGOT), 2);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(DTItems.IRON_BATTLEAXE), new ItemStack(Items.IRON_INGOT), 1);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(DTItems.IRON_GLAIVE), new ItemStack(Items.IRON_INGOT), 1);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(DTItems.IRON_CUTLASS), new ItemStack(Items.IRON_INGOT), 1);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(DTItems.IRON_KNIFE), new ItemStack(Items.IRON_INGOT), 1);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(DTItems.IRON_CESTUS), new ItemStack(Items.IRON_INGOT), 1);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(DTItems.IRON_SHIELD), new ItemStack(Items.IRON_INGOT), 1);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(DTItems.IRONPLATE_HEAD), new ItemStack(Items.IRON_INGOT), 1);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(DTItems.IRONPLATE_CHEST), new ItemStack(Items.IRON_INGOT), 2);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(DTItems.IRONPLATE_LEGS), new ItemStack(Items.IRON_INGOT), 1);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(DTItems.IRONPLATE_FEET), new ItemStack(Items.IRON_INGOT), 1);
        // Gold
        SmelterManager.addRecycleRecipe(energy, new ItemStack(DTItems.GOLDEN_HAMMER), new ItemStack(Items.GOLD_INGOT), 2);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(DTItems.GOLDEN_BATTLEAXE), new ItemStack(Items.GOLD_INGOT), 1);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(DTItems.GOLDEN_GLAIVE), new ItemStack(Items.GOLD_INGOT), 1);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(DTItems.GOLDEN_CUTLASS), new ItemStack(Items.GOLD_INGOT), 1);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(DTItems.GOLDEN_KNIFE), new ItemStack(Items.GOLD_INGOT), 1);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(DTItems.GOLDEN_CESTUS), new ItemStack(Items.GOLD_INGOT), 1);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(DTItems.GOLDEN_SHIELD), new ItemStack(Items.GOLD_INGOT), 1);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(DTItems.GOLDPLATE_HEAD), new ItemStack(Items.GOLD_INGOT), 1);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(DTItems.GOLDPLATE_CHEST), new ItemStack(Items.GOLD_INGOT), 2);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(DTItems.GOLDPLATE_LEGS), new ItemStack(Items.GOLD_INGOT), 1);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(DTItems.GOLDPLATE_FEET), new ItemStack(Items.GOLD_INGOT), 1);
        // Steel
        SmelterManager.addRecycleRecipe(energy, new ItemStack(DTItems.STEEL_HAMMER), new ItemStack(DTItems.INGOT_STEEL), 2);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(DTItems.STEEL_BATTLEAXE), new ItemStack(DTItems.INGOT_STEEL), 1);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(DTItems.STEEL_GLAIVE), new ItemStack(DTItems.INGOT_STEEL), 1);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(DTItems.STEEL_CUTLASS), new ItemStack(DTItems.INGOT_STEEL), 1);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(DTItems.STEEL_KNIFE), new ItemStack(DTItems.INGOT_STEEL), 1);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(DTItems.STEEL_CESTUS), new ItemStack(DTItems.INGOT_STEEL), 1);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(DTItems.STEEL_SHIELD), new ItemStack(DTItems.INGOT_STEEL), 1);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(DTItems.STEELPLATE_HEAD), new ItemStack(DTItems.INGOT_STEEL), 1);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(DTItems.STEELPLATE_CHEST), new ItemStack(DTItems.INGOT_STEEL), 2);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(DTItems.STEELPLATE_LEGS), new ItemStack(DTItems.INGOT_STEEL), 1);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(DTItems.STEELPLATE_FEET), new ItemStack(DTItems.INGOT_STEEL), 1);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(DTItems.STEEL_HELMET), new ItemStack(DTItems.INGOT_STEEL), 2);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(DTItems.STEEL_CHESTPLATE), new ItemStack(DTItems.INGOT_STEEL), 4);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(DTItems.STEEL_LEGGINGS), new ItemStack(DTItems.INGOT_STEEL), 3);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(DTItems.STEEL_BOOTS), new ItemStack(DTItems.INGOT_STEEL), 2);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(DTItems.STEEL_SWORD), new ItemStack(DTItems.INGOT_STEEL), 1);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(DTItems.STEEL_PICK), new ItemStack(DTItems.INGOT_STEEL), 1);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(DTItems.STEEL_SHOVEL), new ItemStack(DTItems.INGOT_STEEL), 1);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(DTItems.STEEL_AXE), new ItemStack(DTItems.INGOT_STEEL), 1);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(DTItems.STEEL_HOE), new ItemStack(DTItems.INGOT_STEEL), 1);
        // Silver
        SmelterManager.addRecycleRecipe(energy, new ItemStack(DTItems.SILVER_HAMMER), new ItemStack(DTItems.INGOT_SILVER), 2);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(DTItems.SILVER_BATTLEAXE), new ItemStack(DTItems.INGOT_SILVER), 1);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(DTItems.SILVER_GLAIVE), new ItemStack(DTItems.INGOT_SILVER), 1);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(DTItems.SILVER_CUTLASS), new ItemStack(DTItems.INGOT_SILVER), 1);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(DTItems.SILVER_KNIFE), new ItemStack(DTItems.INGOT_SILVER), 1);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(DTItems.SILVER_CESTUS), new ItemStack(DTItems.INGOT_SILVER), 1);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(DTItems.SILVER_SHIELD), new ItemStack(DTItems.INGOT_SILVER), 1);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(DTItems.SILVERPLATE_HEAD), new ItemStack(DTItems.INGOT_SILVER), 1);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(DTItems.SILVERPLATE_CHEST), new ItemStack(DTItems.INGOT_SILVER), 2);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(DTItems.SILVERPLATE_LEGS), new ItemStack(DTItems.INGOT_SILVER), 1);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(DTItems.SILVERPLATE_FEET), new ItemStack(DTItems.INGOT_SILVER), 1);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(DTItems.SILVER_HELMET), new ItemStack(DTItems.INGOT_SILVER), 2);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(DTItems.SILVER_CHESTPLATE), new ItemStack(DTItems.INGOT_SILVER), 4);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(DTItems.SILVER_LEGGINGS), new ItemStack(DTItems.INGOT_SILVER), 3);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(DTItems.SILVER_BOOTS), new ItemStack(DTItems.INGOT_SILVER), 2);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(DTItems.SILVER_SWORD), new ItemStack(DTItems.INGOT_SILVER), 1);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(DTItems.SILVER_PICK), new ItemStack(DTItems.INGOT_SILVER), 1);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(DTItems.SILVER_SHOVEL), new ItemStack(DTItems.INGOT_SILVER), 1);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(DTItems.SILVER_AXE), new ItemStack(DTItems.INGOT_SILVER), 1);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(DTItems.SILVER_HOE), new ItemStack(DTItems.INGOT_SILVER), 1);
        // Mithril
        SmelterManager.addRecycleRecipe(energy, new ItemStack(DTItems.MITHRIL_HAMMER), new ItemStack(DTItems.INGOT_MITHRIL), 2);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(DTItems.MITHRIL_BATTLEAXE), new ItemStack(DTItems.INGOT_MITHRIL), 1);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(DTItems.MITHRIL_GLAIVE), new ItemStack(DTItems.INGOT_MITHRIL), 1);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(DTItems.MITHRIL_CUTLASS), new ItemStack(DTItems.INGOT_MITHRIL), 1);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(DTItems.MITHRIL_KNIFE), new ItemStack(DTItems.INGOT_MITHRIL), 1);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(DTItems.MITHRIL_CESTUS), new ItemStack(DTItems.INGOT_MITHRIL), 1);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(DTItems.MITHRIL_SHIELD), new ItemStack(DTItems.INGOT_MITHRIL), 1);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(DTItems.MITHRILPLATE_HEAD), new ItemStack(DTItems.INGOT_MITHRIL), 1);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(DTItems.MITHRILPLATE_CHEST), new ItemStack(DTItems.INGOT_MITHRIL), 2);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(DTItems.MITHRILPLATE_LEGS), new ItemStack(DTItems.INGOT_MITHRIL), 1);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(DTItems.MITHRILPLATE_FEET), new ItemStack(DTItems.INGOT_MITHRIL), 1);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(DTItems.MITHRIL_HELMET), new ItemStack(DTItems.INGOT_MITHRIL), 2);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(DTItems.MITHRIL_CHESTPLATE), new ItemStack(DTItems.INGOT_MITHRIL), 4);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(DTItems.MITHRIL_LEGGINGS), new ItemStack(DTItems.INGOT_MITHRIL), 3);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(DTItems.MITHRIL_BOOTS), new ItemStack(DTItems.INGOT_MITHRIL), 2);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(DTItems.MITHRIL_SWORD), new ItemStack(DTItems.INGOT_MITHRIL), 1);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(DTItems.MITHRIL_PICK), new ItemStack(DTItems.INGOT_MITHRIL), 1);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(DTItems.MITHRIL_SHOVEL), new ItemStack(DTItems.INGOT_MITHRIL), 1);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(DTItems.MITHRIL_AXE), new ItemStack(DTItems.INGOT_MITHRIL), 1);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(DTItems.MITHRIL_HOE), new ItemStack(DTItems.INGOT_MITHRIL), 1);
        // Wrenches, other stuff
        SmelterManager.addRecycleRecipe(energy, new ItemStack(DTItems.WRENCH_IRON), new ItemStack(Items.IRON_INGOT), 2);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(DTItems.WRENCH_STEEL), new ItemStack(DTItems.INGOT_STEEL), 2);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(DTBlocks.POWERED_FENCE), new ItemStack(Items.IRON_NUGGET), 2);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(DTBlocks.TRAP_CLAMP), new ItemStack(Items.IRON_INGOT), 2);
        
        
        
        
        // Pulverizer
        energy = 3_000;
        // Diamond
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(DTItems.DIAMOND_HAMMER), new ItemStack(Items.DIAMOND), 2);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(DTItems.DIAMOND_BATTLEAXE), new ItemStack(Items.DIAMOND), 1);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(DTItems.DIAMOND_GLAIVE), new ItemStack(Items.DIAMOND), 1);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(DTItems.DIAMOND_CUTLASS), new ItemStack(Items.DIAMOND), 1);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(DTItems.DIAMOND_KNIFE), new ItemStack(Items.DIAMOND), 1);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(DTItems.DIAMOND_CESTUS), new ItemStack(Items.DIAMOND), 1);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(DTItems.DIAMOND_SHIELD), new ItemStack(Items.DIAMOND), 1);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(DTItems.DIAMONDPLATE_HEAD), new ItemStack(Items.DIAMOND), 1);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(DTItems.DIAMONDPLATE_CHEST), new ItemStack(Items.DIAMOND), 2);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(DTItems.DIAMONDPLATE_LEGS), new ItemStack(Items.DIAMOND), 1);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(DTItems.DIAMONDPLATE_FEET), new ItemStack(Items.DIAMOND), 1);
        // Wood
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(DTItems.WOODEN_HAMMER), ItemMaterial.dustWood, 2);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(DTItems.WOODEN_BATTLEAXE), ItemMaterial.dustWood, 2);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(DTItems.WOODEN_GLAIVE), ItemMaterial.dustWood, 2);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(DTItems.WOODEN_CUTLASS), ItemMaterial.dustWood, 2);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(DTItems.WOODEN_KNIFE), ItemMaterial.dustWood, 2);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(DTItems.WOODEN_CESTUS), ItemMaterial.dustWood, 2);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(DTItems.WOODEN_SHIELD), ItemMaterial.dustWood, 2);
        // Bone
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(DTItems.BONE_HAMMER), new ItemStack(Items.DYE, 1, 15), 13);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(DTItems.BONE_BATTLEAXE), new ItemStack(Items.DYE, 1, 15), 13);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(DTItems.BONE_GLAIVE), new ItemStack(Items.DYE, 1, 15), 9);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(DTItems.BONE_CUTLASS), new ItemStack(Items.DYE, 1, 15), 9);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(DTItems.BONE_KNIFE), new ItemStack(Items.DYE, 1, 15), 4);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(DTItems.BONE_CESTUS), new ItemStack(Items.DYE, 1, 15), 4);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(DTItems.BONE_SHIELD), new ItemStack(Items.DYE, 1, 15), 13);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(DTItems.BONE_HELMET), new ItemStack(Items.DYE, 1, 15), 22);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(DTItems.BONE_CHESTPLATE), new ItemStack(Items.DYE, 1, 15), 40);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(DTItems.BONE_LEGGINGS), new ItemStack(Items.DYE, 1, 15), 31);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(DTItems.BONE_BOOTS), new ItemStack(Items.DYE, 1, 15), 18);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(DTItems.BONE_SWORD), new ItemStack(Items.DYE, 1, 15), 9);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(DTItems.BONE_PICK), new ItemStack(Items.DYE, 1, 15), 13);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(DTItems.BONE_SHOVEL), new ItemStack(Items.DYE, 1, 15), 4);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(DTItems.BONE_AXE), new ItemStack(Items.DYE, 1, 15), 13);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(DTItems.BONE_HOE), new ItemStack(Items.DYE, 1, 15), 9);
        // Studded armor
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(DTItems.STUDDED_HELMET), new ItemStack(Items.IRON_INGOT), 2);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(DTItems.STUDDED_CHESTPLATE), new ItemStack(Items.IRON_INGOT), 2);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(DTItems.STUDDED_LEGGINGS), new ItemStack(Items.IRON_INGOT), 2);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(DTItems.STUDDED_BOOTS), new ItemStack(Items.IRON_INGOT), 2);
        // Other stuff
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(DTBlocks.CLIMBINGROPE), new ItemStack(Items.STRING), 1);
        
        
        // Arcane Enscroller
//        EnchanterManager.addDefaultEnchantmentRecipe();
        
        
        // Phytogenic Insolator
        ItemStack input = new ItemStack(DTBlocks.FLOWER_AILMENT);
        InsolatorManager.addDefaultRecipe(input, input, input, 5);
        input = new ItemStack(DTBlocks.FLOWER_BARK);
        InsolatorManager.addDefaultRecipe(input, input, input, 5);
        input = new ItemStack(DTBlocks.FLOWER_BRAMBLE);
        InsolatorManager.addDefaultRecipe(input, input, input, 5);
        input = new ItemStack(DTBlocks.FLOWER_CINDER);
        InsolatorManager.addDefaultRecipe(input, input, input, 5);
        input = new ItemStack(DTBlocks.FLOWER_FADE);
        InsolatorManager.addDefaultRecipe(input, input, input, 5);
        input = new ItemStack(DTBlocks.FLOWER_FEATHER);
        InsolatorManager.addDefaultRecipe(input, input, input, 5);
        input = new ItemStack(DTBlocks.FLOWER_SANGUINE);
        InsolatorManager.addDefaultRecipe(input, input, input, 5);
        input = new ItemStack(DTBlocks.FLOWER_TANGLE);
        InsolatorManager.addDefaultRecipe(input, input, input, 5);
        input = new ItemStack(DTBlocks.CHERRYBOMB_BUSH);
        InsolatorManager.addDefaultRecipe(input, new ItemStack(DTItems.CHERRYBOMB), input, 100);
        input = new ItemStack(DTBlocks.INCINDIBERRY_BUSH);
        InsolatorManager.addDefaultRecipe(input, new ItemStack(DTItems.INCINDIBERRY), input, 100);
        input = new ItemStack(DTBlocks.GLOWCURRENT_BUSH);
        InsolatorManager.addDefaultRecipe(input, new ItemStack(DTItems.GLOWCURRENT), input, 100);
        // Since this flower lets you get XP bottles, I matched the recipe to Ender Lily from XU2
        InsolatorManager.addRecipe(120_000, 4_000, new ItemStack(DTBlocks.FLOWER_XP), ItemFertilizer.fertilizerFlux, new ItemStack(Items.EXPERIENCE_BOTTLE), new ItemStack(DTBlocks.FLOWER_XP));
    }
    
    @Nonnull
    @Override
    public String getModId()
    {
        return ModIds.DUNGEON_TACTICS;
    }
}
