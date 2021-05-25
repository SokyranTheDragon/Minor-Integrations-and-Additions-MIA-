package com.github.sokyranthedragon.mia.integrations.charm;

import cofh.core.util.helpers.ItemHelper;
import cofh.thermalexpansion.util.managers.machine.*;
import cofh.thermalfoundation.init.TFFluids;
import cofh.thermalfoundation.item.ItemMaterial;
import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.integrations.thermalexpansion.IThermalExpansionIntegration;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.OreDictionary;
import org.jetbrains.annotations.NotNull;
import slimeknights.tconstruct.library.TinkerRegistry;
import svenhjol.charm.Charm;
import svenhjol.charm.automation.feature.GunpowderBlock;
import svenhjol.charm.automation.feature.RedstoneSand;
import svenhjol.charm.crafting.feature.*;
import svenhjol.charm.enchanting.feature.CurseBreak;
import svenhjol.charm.enchanting.feature.Homing;
import svenhjol.charm.enchanting.feature.Magnetic;
import svenhjol.charm.enchanting.feature.Salvage;
import svenhjol.charm.world.feature.ChargedEmeralds;
import svenhjol.charm.world.feature.Moonstone;
import svenhjol.meson.MesonBlock;

class ThermalExpansionCharmIntegration implements IThermalExpansionIntegration
{
    @Override
    public void addRecipes()
    {
        if (Charm.hasFeature(Lantern.class))
        {
            if (Lantern.ironLantern != null)
                SmelterManager.addRecycleRecipe(2_000, new ItemStack(Lantern.ironLantern), new ItemStack(Items.IRON_NUGGET), 8);
            if (Lantern.goldLantern != null)
                SmelterManager.addRecycleRecipe(2_000, new ItemStack(Lantern.goldLantern), new ItemStack(Items.GOLD_NUGGET), 8);
        }
        
        if (Charm.hasFeature(SmoothGlowstone.class))
        {
            PulverizerManager.addRecipe(4_000, new ItemStack(SmoothGlowstone.block), new ItemStack(Items.GLOWSTONE_DUST, 4));
            CrucibleManager.addRecipe(80_000, new ItemStack(SmoothGlowstone.block), new FluidStack(TFFluids.fluidGlowstone, 1000));
            
            if (ModIds.TINKERS_CONSTRUCT.isLoaded)
                addTinkerSmelting(new ItemStack(SmoothGlowstone.block), TFFluids.fluidGlowstone, 1000);
        }
        
        if (Charm.hasFeature(EnderPearlBlock.class))
        {
            CrucibleManager.addRecipe(20_000 * 9, new ItemStack(EnderPearlBlock.block), new FluidStack(TFFluids.fluidEnder, 250 * 9));
            addTinkerSmelting(new ItemStack(EnderPearlBlock.block), TFFluids.fluidEnder, 250 * 9);
        }
        
        if (Charm.hasFeature(Barrel.class))
        {
            for (MesonBlock.WoodVariant variant : MesonBlock.WoodVariant.values())
                SawmillManager.addRecycleRecipe(2_000, new ItemStack(Barrel.block, 1, variant.ordinal()), new ItemStack(Blocks.PLANKS, 1, variant.ordinal()), 3);
        }
        
        if (Charm.hasFeature(Crate.class))
            SawmillManager.addRecipe(2_000, new ItemStack(Crate.crate), new ItemStack(Items.IRON_INGOT, 4, OreDictionary.WILDCARD_VALUE), ItemHelper.cloneStack(ItemMaterial.dustWood, 8));
        
        if (Charm.hasFeature(BookshelfChest.class))
            PulverizerManager.addRecipe(4_000, new ItemStack(BookshelfChest.bookshelfChest), ItemHelper.cloneStack(ItemMaterial.dustWood, 32), new ItemStack(Items.BOOK), 150);
        
        if (Charm.hasFeature(CurseBreak.class))
        {
            ItemStack target = ItemStack.EMPTY;
            
            if (Charm.hasFeature(ChargedEmeralds.class))
                target = new ItemStack(ChargedEmeralds.emerald);
            else if (Charm.hasFeature(GunpowderBlock.class))
                target = new ItemStack(GunpowderBlock.block, 4);
            else if (Charm.hasFeature(Moonstone.class))
                target = new ItemStack(Moonstone.moonstone, 8, 2);
            
            if (!target.isEmpty())
                EnchanterManager.addDefaultEnchantmentRecipe(target, "charm:curse_break", 2);
        }
    
        if (Charm.hasFeature(Homing.class))
        {
            ItemStack target = ItemStack.EMPTY;
            
            if (Charm.hasFeature(EnderPearlBlock.class))
                target = new ItemStack(EnderPearlBlock.block, 1);
            else if (Charm.hasFeature(RedstoneSand.class))
                target = new ItemStack(RedstoneSand.block, 4);
            else if (Charm.hasFeature(Moonstone.class))
                target = new ItemStack(Moonstone.moonstone, 8, 11);
        
            if (!target.isEmpty())
                EnchanterManager.addDefaultEnchantmentRecipe(target, "charm:homing", 2);
        }
    
        if (Charm.hasFeature(Magnetic.class))
        {
            ItemStack target = ItemStack.EMPTY;
            
            if (Charm.hasFeature(Lantern.class))
            {
                if (Lantern.ironLantern != null)
                    target = new ItemStack(Lantern.ironLantern, 8);
                else if (Lantern.goldLantern != null)
                    target = new ItemStack(Lantern.goldLantern, 8);
            }
            if (target.isEmpty() && Charm.hasFeature(Moonstone.class))
                target = new ItemStack(Moonstone.moonstone, 8, 8);
            
            if (!target.isEmpty())
                EnchanterManager.addDefaultEnchantmentRecipe(target, "charm:magnetic", 2);
        }
        
        if (Charm.hasFeature(Salvage.class))
        {
            ItemStack target = ItemStack.EMPTY;
            
            if (Charm.hasFeature(SmoothGlowstone.class))
                target = new ItemStack(SmoothGlowstone.block, 4);
            else if (Charm.hasFeature(RottenFleshBlock.class))
                target = new ItemStack(RottenFleshBlock.block, 8);
            else if (Charm.hasFeature(Moonstone.class))
                target = new ItemStack(Moonstone.moonstone, 8, 4);
    
            if (!target.isEmpty())
                EnchanterManager.addDefaultEnchantmentRecipe(target, "charm:salvage", 2);
        }
    }
    
    private static void addTinkerSmelting(ItemStack input, Fluid output, int outputCount)
    {
        TinkerRegistry.registerMelting(input, output, outputCount);
    }
    
    @NotNull
    @Override
    public ModIds getModId()
    {
        return ModIds.CHARM;
    }
}
