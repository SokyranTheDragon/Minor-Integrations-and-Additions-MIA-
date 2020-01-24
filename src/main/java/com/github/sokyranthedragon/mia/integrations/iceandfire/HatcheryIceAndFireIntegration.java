package com.github.sokyranthedragon.mia.integrations.iceandfire;

import com.gendeathrow.hatchery.api.crafting.ShredderRecipe;
import com.gendeathrow.hatchery.block.shredder.ShredderTileEntity;
import com.gendeathrow.hatchery.core.config.ConfigLootHandler;
import com.github.alexthe666.iceandfire.core.ModBlocks;
import com.github.alexthe666.iceandfire.core.ModItems;
import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.integrations.hatchery.IHatcheryIntegration;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;
import java.util.LinkedList;
import java.util.List;

class HatcheryIceAndFireIntegration implements IHatcheryIntegration
{
    private final boolean modEnabled;
    
    HatcheryIceAndFireIntegration(boolean modEnabled)
    {
        this.modEnabled = modEnabled;
    }
    
    @Override
    public void registerShredder()
    {
        ShredderTileEntity.shredderRecipes.add(new ShredderRecipe(new ItemStack(ModItems.troll_tusk), new ItemStack(Items.DYE, 6, 15)));
        ShredderTileEntity.shredderRecipes.add(new ShredderRecipe(new ItemStack(ModBlocks.fire_lily), new ItemStack(Items.DYE, 2, 1)));
        ShredderTileEntity.shredderRecipes.add(new ShredderRecipe(new ItemStack(ModBlocks.frost_lily), new ItemStack(Items.DYE, 2, 12)));
    }
    
    @Override
    public boolean isModEnabled()
    {
        return modEnabled;
    }
    
    @Override
    public int getCurrentLootVersion()
    {
        return 0;
    }
    
    @Nonnull
    @Override
    public List<ConfigLootHandler.ItemDrop> getDefaultEggDrops()
    {
        List<ConfigLootHandler.ItemDrop> drops = new LinkedList<>();
    
        drops.add(new ConfigLootHandler.ItemDrop(new ItemStack(ModItems.silverIngot), 4, 1, 1));
        drops.add(new ConfigLootHandler.ItemDrop(new ItemStack(ModItems.dragonsteel_fire_ingot), 1, 1, 1));
        drops.add(new ConfigLootHandler.ItemDrop(new ItemStack(ModItems.dragonsteel_ice_ingot), 1, 1, 1));
        drops.add(new ConfigLootHandler.ItemDrop(new ItemStack(ModItems.sapphireGem), 1, 1, 1));
        drops.add(new ConfigLootHandler.ItemDrop(new ItemStack(ModItems.pixie_dust), 2, 1, 2));
        drops.add(new ConfigLootHandler.ItemDrop(new ItemStack(ModItems.rotten_egg), 1, 1, 1));
        drops.add(new ConfigLootHandler.ItemDrop(new ItemStack(ModItems.stymphalian_arrow), 5, 1, 3));
        drops.add(new ConfigLootHandler.ItemDrop(new ItemStack(ModItems.amphithere_arrow), 5, 1, 3));
        drops.add(new ConfigLootHandler.ItemDrop(new ItemStack(ModItems.dragonbone_arrow), 5, 1, 3));
        drops.add(new ConfigLootHandler.ItemDrop(new ItemStack(ModItems.sea_serpent_arrow), 5, 1, 3));
        drops.add(new ConfigLootHandler.ItemDrop(new ItemStack(ModItems.manuscript), 7, 2, 4));
        drops.add(new ConfigLootHandler.ItemDrop(new ItemStack(ModItems.myrmex_desert_resin), 5, 1, 4));
        drops.add(new ConfigLootHandler.ItemDrop(new ItemStack(ModItems.myrmex_jungle_resin), 5, 1, 4));
        drops.add(new ConfigLootHandler.ItemDrop(new ItemStack(ModItems.myrmex_desert_chitin), 3, 1, 3));
        drops.add(new ConfigLootHandler.ItemDrop(new ItemStack(ModItems.myrmex_jungle_chitin), 3, 1, 3));
        drops.add(new ConfigLootHandler.ItemDrop(new ItemStack(ModItems.myrmex_stinger), 6, 1, 1));
        drops.add(new ConfigLootHandler.ItemDrop(new ItemStack(ModItems.fishing_spear), 5, 1, 1));
        drops.add(new ConfigLootHandler.ItemDrop(new ItemStack(ModItems.witherbone), 10, 1, 3));
        drops.add(new ConfigLootHandler.ItemDrop(new ItemStack(ModItems.dragonbone), 3, 1, 2));
        drops.add(new ConfigLootHandler.ItemDrop(new ItemStack(ModItems.shiny_scales), 3, 1, 2));
        
        return drops;
    }
    
    @Nonnull
    @Override
    public ModIds getModId()
    {
        return ModIds.ICE_AND_FIRE;
    }
}
