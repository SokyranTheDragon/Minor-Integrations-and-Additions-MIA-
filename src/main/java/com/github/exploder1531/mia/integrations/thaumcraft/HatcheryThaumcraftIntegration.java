package com.github.exploder1531.mia.integrations.thaumcraft;

import com.gendeathrow.hatchery.core.config.ConfigLootHandler;
import com.github.exploder1531.mia.integrations.ModIds;
import com.github.exploder1531.mia.integrations.hatchery.IHatcheryIntegration;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.blocks.BlocksTC;
import thaumcraft.api.items.ItemsTC;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

class HatcheryThaumcraftIntegration implements IHatcheryIntegration
{
    private final boolean modEnabled;
    
    HatcheryThaumcraftIntegration(boolean modEnabled)
    {
        this.modEnabled = modEnabled;
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
        
        
        final ArrayList<Aspect> primalAspects = Aspect.getPrimalAspects();
        
        for (Aspect aspect : primalAspects)
        {
            ItemStack aspectItem = ThaumcraftApiHelper.makeCrystal(aspect);
            drops.add(new ConfigLootHandler.ItemDrop(aspectItem, 5, 5, 10));
        }
        
        drops.add(new ConfigLootHandler.ItemDrop(new ItemStack(ItemsTC.focus1), 5, 1, 1));
        drops.add(new ConfigLootHandler.ItemDrop(new ItemStack(ItemsTC.focus2), 1, 1, 1));
        drops.add(new ConfigLootHandler.ItemDrop(new ItemStack(ItemsTC.seals, 1, 0), 3, 3, 6));
        drops.add(new ConfigLootHandler.ItemDrop(new ItemStack(BlocksTC.nitor.get(EnumDyeColor.YELLOW)), 5, 1, 2));
        drops.add(new ConfigLootHandler.ItemDrop(new ItemStack(ItemsTC.primordialPearl, 1, 7), 1, 1, 1));
        drops.add(new ConfigLootHandler.ItemDrop(new ItemStack(ItemsTC.voidSeed), 1, 1, 1));
        drops.add(new ConfigLootHandler.ItemDrop(new ItemStack(ItemsTC.nuggets, 1, 10), 4, 1, 2));
        drops.add(new ConfigLootHandler.ItemDrop(new ItemStack(ItemsTC.ingots, 1, 2), 4, 1, 1)); // Brass
        drops.add(new ConfigLootHandler.ItemDrop(new ItemStack(ItemsTC.ingots, 1, 0), 2, 1, 1)); // Thaumium
        drops.add(new ConfigLootHandler.ItemDrop(new ItemStack(ItemsTC.ingots, 1, 1), 1, 1, 1)); // Void metal
        drops.add(new ConfigLootHandler.ItemDrop(new ItemStack(ItemsTC.amber), 4, 1, 2));
        drops.add(new ConfigLootHandler.ItemDrop(new ItemStack(ItemsTC.quicksilver), 4, 1, 2));
        
        return drops;
    }
    
    @Nonnull
    @Override
    public ModIds getModId()
    {
        return ModIds.THAUMCRAFT;
    }
}
