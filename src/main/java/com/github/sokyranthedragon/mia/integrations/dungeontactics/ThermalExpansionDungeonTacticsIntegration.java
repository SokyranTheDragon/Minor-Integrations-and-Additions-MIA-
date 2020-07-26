package com.github.sokyranthedragon.mia.integrations.dungeontactics;

import cofh.thermalexpansion.util.managers.machine.*;
import cofh.thermalfoundation.init.TFFluids;
import cofh.thermalfoundation.item.ItemFertilizer;
import cofh.thermalfoundation.item.ItemMaterial;
import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.integrations.thermalexpansion.IThermalExpansionIntegration;
import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import pegbeard.dungeontactics.handlers.DTBlocks;
import pegbeard.dungeontactics.handlers.DTItems;

import javax.annotation.Nonnull;

class ThermalExpansionDungeonTacticsIntegration implements IThermalExpansionIntegration
{
    @Override
    public void addRecipes()
    {
        // Induction smelter
        int energy = 6_000;
        // Iron
        SmelterManager.addRecycleRecipe(energy, new ItemStack(DTItems.IRON_HAMMER), new ItemStack(Items.IRON_INGOT), 1);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(DTItems.IRON_BATTLEAXE), new ItemStack(Items.IRON_INGOT), 1);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(DTItems.IRON_GLAIVE), new ItemStack(Items.IRON_INGOT), 1);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(DTItems.IRON_CLUB), new ItemStack(Items.IRON_INGOT), 1);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(DTItems.IRON_CUTLASS), new ItemStack(Items.IRON_INGOT), 1);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(DTItems.IRON_KNIFE), new ItemStack(Items.IRON_INGOT), 1);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(DTItems.IRON_CESTUS), new ItemStack(Items.IRON_INGOT), 1);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(DTItems.IRON_SHIELD), new ItemStack(Items.IRON_INGOT), 1);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(DTItems.IRONPLATE_HEAD), new ItemStack(Items.IRON_INGOT), 1);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(DTItems.IRONPLATE_CHEST), new ItemStack(Items.IRON_INGOT), 2);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(DTItems.IRONPLATE_LEGS), new ItemStack(Items.IRON_INGOT), 1);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(DTItems.IRONPLATE_FEET), new ItemStack(Items.IRON_INGOT), 1);
        // Gold
        SmelterManager.addRecycleRecipe(energy, new ItemStack(DTItems.GOLDEN_HAMMER), new ItemStack(Items.GOLD_INGOT), 1);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(DTItems.GOLDEN_BATTLEAXE), new ItemStack(Items.GOLD_INGOT), 1);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(DTItems.GOLDEN_GLAIVE), new ItemStack(Items.GOLD_INGOT), 1);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(DTItems.GOLDEN_CLUB), new ItemStack(Items.GOLD_INGOT), 1);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(DTItems.GOLDEN_CUTLASS), new ItemStack(Items.GOLD_INGOT), 1);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(DTItems.GOLDEN_KNIFE), new ItemStack(Items.GOLD_INGOT), 1);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(DTItems.GOLDEN_CESTUS), new ItemStack(Items.GOLD_INGOT), 1);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(DTItems.GOLDEN_SHIELD), new ItemStack(Items.GOLD_INGOT), 1);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(DTItems.GOLDPLATE_HEAD), new ItemStack(Items.GOLD_INGOT), 1);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(DTItems.GOLDPLATE_CHEST), new ItemStack(Items.GOLD_INGOT), 2);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(DTItems.GOLDPLATE_LEGS), new ItemStack(Items.GOLD_INGOT), 1);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(DTItems.GOLDPLATE_FEET), new ItemStack(Items.GOLD_INGOT), 1);
        // Steel
        SmelterManager.addRecycleRecipe(energy, new ItemStack(DTItems.STEEL_HAMMER), new ItemStack(DTItems.INGOT_STEEL), 1);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(DTItems.STEEL_BATTLEAXE), new ItemStack(DTItems.INGOT_STEEL), 1);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(DTItems.STEEL_GLAIVE), new ItemStack(DTItems.INGOT_STEEL), 1);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(DTItems.STEEL_CLUB), new ItemStack(DTItems.INGOT_STEEL), 1);
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
        SmelterManager.addRecycleRecipe(energy, new ItemStack(DTItems.SILVER_HAMMER), new ItemStack(DTItems.INGOT_SILVER), 1);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(DTItems.SILVER_BATTLEAXE), new ItemStack(DTItems.INGOT_SILVER), 1);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(DTItems.SILVER_GLAIVE), new ItemStack(DTItems.INGOT_SILVER), 1);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(DTItems.SILVER_CLUB), new ItemStack(DTItems.INGOT_SILVER), 1);
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
        SmelterManager.addRecycleRecipe(energy, new ItemStack(DTItems.MITHRIL_HAMMER), new ItemStack(DTItems.INGOT_MITHRIL), 1);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(DTItems.MITHRIL_BATTLEAXE), new ItemStack(DTItems.INGOT_MITHRIL), 1);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(DTItems.MITHRIL_GLAIVE), new ItemStack(DTItems.INGOT_MITHRIL), 1);
        SmelterManager.addRecycleRecipe(energy, new ItemStack(DTItems.MITHRIL_CLUB), new ItemStack(DTItems.INGOT_MITHRIL), 1);
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
        SmelterManager.addRecycleRecipe(energy, new ItemStack(DTBlocks.ALCHEMYCAULDRON), new ItemStack(Items.IRON_INGOT), 7);
        
        
        // Pulverizer
        energy = 3_000;
        // Diamond
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(DTItems.DIAMOND_HAMMER), new ItemStack(Items.DIAMOND), 2);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(DTItems.DIAMOND_BATTLEAXE), new ItemStack(Items.DIAMOND), 1);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(DTItems.DIAMOND_GLAIVE), new ItemStack(Items.DIAMOND), 1);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(DTItems.DIAMOND_CLUB), new ItemStack(Items.DIAMOND), 1);
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
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(DTItems.WOODEN_CLUB), ItemMaterial.dustWood, 2);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(DTItems.WOODEN_CUTLASS), ItemMaterial.dustWood, 2);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(DTItems.WOODEN_KNIFE), ItemMaterial.dustWood, 2);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(DTItems.WOODEN_CESTUS), ItemMaterial.dustWood, 2);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(DTItems.WOODEN_SHIELD), ItemMaterial.dustWood, 2);
        // Bone
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(DTItems.BONE_HAMMER), new ItemStack(Items.DYE, 1, 15), 13);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(DTItems.BONE_BATTLEAXE), new ItemStack(Items.DYE, 1, 15), 13);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(DTItems.BONE_GLAIVE), new ItemStack(Items.DYE, 1, 15), 9);
        PulverizerManager.addRecycleRecipe(energy, new ItemStack(DTItems.BONE_CLUB), new ItemStack(Items.DYE, 1, 15), 9);
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
        
        
        // Sawmill
        energy = 1_500;
        SawmillManager.addRecycleRecipe(energy, new ItemStack(DTBlocks.WOODCHAIR), ItemMaterial.dustWood, 1);
        SawmillManager.addRecycleRecipe(energy, new ItemStack(DTBlocks.WOODCHAIR), ItemMaterial.dustWood, 1);
        SawmillManager.addRecycleRecipe(energy, new ItemStack(DTBlocks.BARREL), ItemMaterial.dustWood, 6);
        
        
        // Arcane Enscroller
        // Normal enchants
        EnchanterManager.addDefaultEnchantmentRecipe(new ItemStack(DTItems.DUCT_TAPE), "dungeontactics:ducttaped", 0);
        EnchanterManager.addDefaultEnchantmentRecipe(new ItemStack(DTItems.BOMB_PORTING_CLUSTER), "dungeontactics:sonicboom", 4);
        EnchanterManager.addDefaultEnchantmentRecipe(new ItemStack(DTItems.BOMB_FRAG_CLUSTER), "dungeontactics:clustered", 4);
        EnchanterManager.addDefaultEnchantmentRecipe(new ItemStack(DTBlocks.POWDERKEG), "dungeontactics:explosive", 4);
        EnchanterManager.addDefaultEnchantmentRecipe(new ItemStack(DTBlocks.FLOWER_CINDER), "dungeontactics:debilitating", 4);
        EnchanterManager.addDefaultEnchantmentRecipe(new ItemStack(DTItems.BOMB_PYRO_CLUSTER), "dungeontactics:smelting", 2);
        EnchanterManager.addDefaultEnchantmentRecipe(new ItemStack(DTBlocks.FLOWER_BARK), "dungeontactics:berserking", 3);
        EnchanterManager.addDefaultEnchantmentRecipe(new ItemStack(DTBlocks.FLOWER_SANGUINE), "dungeontactics:lifesteal", 3);
        EnchanterManager.addDefaultEnchantmentRecipe(new ItemStack(DTItems.MAGIC_POWDER), "dungeontactics:runed", 2);
        EnchanterManager.addDefaultEnchantmentRecipe(new ItemStack(DTItems.MAGIC_SCROLL), "dungeontactics:mage_affinity", 2);
        // Scrolls
        EnchanterManager.addDefaultEnchantmentRecipe(new ItemStack(DTItems.HEART_GOLDEN), "dungeontactics:restoration", 2);
        EnchanterManager.addDefaultEnchantmentRecipe(new ItemStack(DTItems.JAMSLICE), "dungeontactics:satiate", 2);
        EnchanterManager.addDefaultEnchantmentRecipe(new ItemStack(DTItems.IRON_HAMMER), "dungeontactics:forging", 2);
        EnchanterManager.addDefaultEnchantmentRecipe(new ItemStack(DTBlocks.TRAP_FIRE), "dungeontactics:cooking", 2);
        EnchanterManager.addDefaultEnchantmentRecipe(new ItemStack(DTItems.BOMB_CRYO_CLUSTER), "dungeontactics:freezing", 2);
        EnchanterManager.addDefaultEnchantmentRecipe(new ItemStack(DTItems.GLOWCURRENT, 9), "dungeontactics:uncover", 2);
        EnchanterManager.addDefaultEnchantmentRecipe(new ItemStack(DTItems.CHERRYBOMB, 9), "dungeontactics:disarm", 2);
        EnchanterManager.addDefaultEnchantmentRecipe(new ItemStack(DTBlocks.FLOWER_FADE), "dungeontactics:disorient", 2);
        EnchanterManager.addDefaultEnchantmentRecipe(new ItemStack(DTItems.INCINDIBERRY, 9), "dungeontactics:punish", 2);
        EnchanterManager.addDefaultEnchantmentRecipe(new ItemStack(DTBlocks.WITHER_WEB), "dungeontactics:wither", 2);
        EnchanterManager.addDefaultEnchantmentRecipe(new ItemStack(DTBlocks.BLOCK_SILVER), "dungeontactics:smite", 2);
        EnchanterManager.addDefaultEnchantmentRecipe(new ItemStack(DTItems.POTSHOTAMMO), "dungeontactics:magicmissile", 2);
        EnchanterManager.addDefaultEnchantmentRecipe(new ItemStack(DTItems.THROWINGKNIFE, 10), "dungeontactics:pinmissile", 2);
        EnchanterManager.addDefaultEnchantmentRecipe(new ItemStack(DTItems.BONE_CHESTPLATE), "dungeontactics:transport", 2);
        EnchanterManager.addDefaultEnchantmentRecipe(new ItemStack(DTItems.BONE_HAMMER), "dungeontactics:companion", 2);
        EnchanterManager.addDefaultEnchantmentRecipe(new ItemStack(DTBlocks.FLOWER_BRAMBLE), "dungeontactics:sunder", 2);
        EnchanterManager.addDefaultEnchantmentRecipe(new ItemStack(DTBlocks.FLOWER_TANGLE), "dungeontactics:rage", 2);
        // Trinkets
        EnchanterManager.addDefaultEnchantmentRecipe(new ItemStack(DTItems.FISH_SWIFT), "dungeontactics:trinket_speed", 2);
        EnchanterManager.addDefaultEnchantmentRecipe(new ItemStack(DTItems.FISH_FLYING), "dungeontactics:trinket_jump", 2);
        EnchanterManager.addDefaultEnchantmentRecipe(new ItemStack(DTItems.JAM), "dungeontactics:trinket_haste", 2);
        EnchanterManager.addDefaultEnchantmentRecipe(new ItemStack(DTItems.FISH_LUNG), "dungeontactics:trinket_gills", 2);
        EnchanterManager.addDefaultEnchantmentRecipe(new ItemStack(DTItems.FISH_TUNNEL), "dungeontactics:trinket_nightvision", 2);
        EnchanterManager.addDefaultEnchantmentRecipe(new ItemStack(DTItems.FISH_LAVA), "dungeontactics:trinket_fireshield", 3);
        EnchanterManager.addDefaultEnchantmentRecipe(new ItemStack(DTItems.FISH_OBSIDIAN), "dungeontactics:trinket_resistance", 3);
        EnchanterManager.addDefaultEnchantmentRecipe(new ItemStack(DTItems.FISH_MUSCLE), "dungeontactics:trinket_strength", 4);
        EnchanterManager.addDefaultEnchantmentRecipe(new ItemStack(DTItems.MAGIC_POUCH, 1, 640), "dungeontactics:trinket_invisibility", 4);
        
        
        // Phytogenic Insolator
        ItemStack input;
        Block[] blocks = new Block[]
            {
                DTBlocks.FLOWER_AILMENT,
                DTBlocks.FLOWER_BARK,
                DTBlocks.FLOWER_BRAMBLE,
                DTBlocks.FLOWER_CINDER,
                DTBlocks.FLOWER_FADE,
                DTBlocks.FLOWER_FEATHER,
                DTBlocks.FLOWER_SANGUINE,
                DTBlocks.FLOWER_TANGLE,
                DTBlocks.MUSHROOM_BLAST,
                DTBlocks.MUSHROOM_COAL,
                DTBlocks.MUSHROOM_GOLD,
                DTBlocks.MUSHROOM_IRON,
                DTBlocks.MUSHROOM_MITHRIL,
                DTBlocks.MUSHROOM_SILVER,
                DTBlocks.MUSHROOM_STEEL
            };
        
        for (Block block : blocks)
        {
            input = new ItemStack(block);
            InsolatorManager.addDefaultRecipe(input, input, input, 5);
        }
        
        input = new ItemStack(DTBlocks.CHERRYBOMB_BUSH);
        InsolatorManager.addDefaultRecipe(input, new ItemStack(DTItems.CHERRYBOMB, 3), input, 100);
        input = new ItemStack(DTBlocks.INCINDIBERRY_BUSH);
        InsolatorManager.addDefaultRecipe(input, new ItemStack(DTItems.INCINDIBERRY, 3), input, 100);
        input = new ItemStack(DTBlocks.GLOWCURRENT_BUSH);
        InsolatorManager.addDefaultRecipe(input, new ItemStack(DTItems.GLOWCURRENT, 3), input, 100);
        // Since this flower lets you get XP bottles, I matched the recipe to Ender Lily/Red Orchid from XU2
        InsolatorManager.addRecipe(120_000, 4_000, new ItemStack(DTBlocks.FLOWER_XP), ItemFertilizer.fertilizerFlux, new ItemStack(Items.EXPERIENCE_BOTTLE), new ItemStack(DTBlocks.FLOWER_XP));
        
        
        // Magmatic Crucible
        CrucibleManager.addRecipe(5000, new ItemStack(DTItems.INCINDIBERRY, 16), new FluidStack(FluidRegistry.LAVA, 250));
        CrucibleManager.addRecipe(250, new ItemStack(DTItems.GLOWCURRENT, 16), new FluidStack(TFFluids.fluidGlowstone, 250));
    }
    
    @Nonnull
    @Override
    public ModIds getModId()
    {
        return ModIds.DUNGEON_TACTICS;
    }
}
