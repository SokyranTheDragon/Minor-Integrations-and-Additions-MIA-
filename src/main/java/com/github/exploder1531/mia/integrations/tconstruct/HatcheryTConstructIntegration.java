package com.github.exploder1531.mia.integrations.tconstruct;

import com.gendeathrow.hatchery.core.config.ConfigLootHandler;
import com.github.exploder1531.mia.integrations.ModIds;
import com.github.exploder1531.mia.integrations.hatchery.IHatcheryIntegration;
import net.minecraft.item.ItemStack;
import slimeknights.tconstruct.gadgets.TinkerGadgets;
import slimeknights.tconstruct.shared.TinkerCommons;

import javax.annotation.Nonnull;
import java.util.LinkedList;
import java.util.List;

class HatcheryTConstructIntegration implements IHatcheryIntegration
{
    private final boolean modEnabled;
    
    HatcheryTConstructIntegration(boolean modEnabled)
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
    
        drops.add(new ConfigLootHandler.ItemDrop(new ItemStack(TinkerGadgets.throwball, 1, 0), 5, 2, 5));
        drops.add(new ConfigLootHandler.ItemDrop(new ItemStack(TinkerGadgets.throwball, 1, 1), 5, 1, 2));
        drops.add(new ConfigLootHandler.ItemDrop(TinkerCommons.ingotKnightSlime, 2, 1, 1));
        drops.add(new ConfigLootHandler.ItemDrop(TinkerCommons.ingotPigIron, 3, 1, 1));
        drops.add(new ConfigLootHandler.ItemDrop(TinkerCommons.ingotArdite, 1, 1, 1));
        drops.add(new ConfigLootHandler.ItemDrop(TinkerCommons.ingotCobalt, 1, 1, 1));
        drops.add(new ConfigLootHandler.ItemDrop(TinkerCommons.ingotManyullyn, 1, 1, 1));
        drops.add(new ConfigLootHandler.ItemDrop(TinkerCommons.matSlimeCrystalGreen, 3, 1, 1));
        drops.add(new ConfigLootHandler.ItemDrop(TinkerCommons.matSlimeCrystalBlue, 3, 1, 1));
        drops.add(new ConfigLootHandler.ItemDrop(TinkerCommons.matSlimeCrystalMagma, 3, 1, 1));
        drops.add(new ConfigLootHandler.ItemDrop(TinkerCommons.matNecroticBone, 2, 1, 2));
        drops.add(new ConfigLootHandler.ItemDrop(TinkerCommons.matMendingMoss, 1, 1, 1));
        drops.add(new ConfigLootHandler.ItemDrop(new ItemStack(TinkerGadgets.spaghetti, 1, 0), 1, 1, 1));
        
        return drops;
    }
    
    @Nonnull
    @Override
    public ModIds getModId()
    {
        return ModIds.TINKERS_CONSTRUCT;
    }
}
