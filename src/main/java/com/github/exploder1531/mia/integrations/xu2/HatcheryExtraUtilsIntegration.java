package com.github.exploder1531.mia.integrations.xu2;

import com.gendeathrow.hatchery.core.config.ConfigLootHandler;
import com.github.exploder1531.mia.integrations.ModIds;
import com.github.exploder1531.mia.integrations.hatchery.IHatcheryIntegration;
import com.rwtema.extrautils2.backend.entries.XU2Entries;

import javax.annotation.Nonnull;
import java.util.LinkedList;
import java.util.List;

class HatcheryExtraUtilsIntegration implements IHatcheryIntegration
{
    private final boolean modEnabled;
    
    @Override
    public boolean isModEnabled()
    {
        return modEnabled;
    }
    
    HatcheryExtraUtilsIntegration(boolean modEnabled)
    {
        this.modEnabled = modEnabled;
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
        
        drops.add(new ConfigLootHandler.ItemDrop(XU2Entries.blockEnderLilly.newStack(1), 1, 1, 1));
        drops.add(new ConfigLootHandler.ItemDrop(XU2Entries.blockRedOrchid.newStack(1), 3, 1, 1));
        drops.add(new ConfigLootHandler.ItemDrop(XU2Entries.sunCrystal.newStack(1), 1, 1, 1));
        drops.add(new ConfigLootHandler.ItemDrop(XU2Entries.unstableIngots.newStack(1, 1), 1, 1, 1));
        drops.add(new ConfigLootHandler.ItemDrop(XU2Entries.itemIngredients.newStack(1), 5, 1, 2));
        drops.add(new ConfigLootHandler.ItemDrop(XU2Entries.itemIngredients.newStack(1, 3), 10, 1, 3));
        drops.add(new ConfigLootHandler.ItemDrop(XU2Entries.itemIngredients.newStack(1, 4), 5, 1, 2));
        drops.add(new ConfigLootHandler.ItemDrop(XU2Entries.itemIngredients.newStack(1, 5), 1, 1, 1));
        drops.add(new ConfigLootHandler.ItemDrop(XU2Entries.itemIngredients.newStack(1, 9), 2, 1, 2));
        drops.add(new ConfigLootHandler.ItemDrop(XU2Entries.itemIngredients.newStack(1, 10), 5, 1, 3));
        drops.add(new ConfigLootHandler.ItemDrop(XU2Entries.itemIngredients.newStack(1, 11), 2, 1, 1));
        drops.add(new ConfigLootHandler.ItemDrop(XU2Entries.itemIngredients.newStack(1, 12), 1, 1, 1));
        drops.add(new ConfigLootHandler.ItemDrop(XU2Entries.itemIngredients.newStack(1, 17), 1, 1, 1));
        drops.add(new ConfigLootHandler.ItemDrop(XU2Entries.goldenLasso.newStack(1), 4, 1, 1));
        drops.add(new ConfigLootHandler.ItemDrop(XU2Entries.boomerang.newStack(1), 1, 1, 1));
        drops.add(new ConfigLootHandler.ItemDrop(XU2Entries.magicApple.newStack(1), 1, 1, 1));
        drops.add(new ConfigLootHandler.ItemDrop(XU2Entries.magical_wood.newStack(1), 1, 1, 1));
        
        return drops;
    }
    
    @Nonnull
    @Override
    public ModIds getModId()
    {
        return ModIds.EXTRA_UTILITIES;
    }
}
