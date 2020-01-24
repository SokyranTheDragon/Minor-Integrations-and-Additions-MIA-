package com.github.sokyranthedragon.mia.integrations.extrabotany;

import com.gendeathrow.hatchery.core.config.ConfigLootHandler;
import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.integrations.hatchery.IHatcheryIntegration;
import com.meteor.extrabotany.common.item.ModItems;
import mcp.MethodsReturnNonnullByDefault;

import javax.annotation.Nonnull;
import java.util.LinkedList;
import java.util.List;

import static com.github.sokyranthedragon.mia.integrations.hatchery.Hatchery.getDrop;

@MethodsReturnNonnullByDefault
class HatcheryExtraBotanyIntegration implements IHatcheryIntegration
{
    private boolean modEnabled;
    
    HatcheryExtraBotanyIntegration(boolean modEnabled)
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
    
        drops.add(getDrop(ModItems.gaiarecord, 1));
        drops.add(getDrop(ModItems.herrscherrecord, 1));
        
        return drops;
    }
    
    @Override
    public ModIds getModId()
    {
        return ModIds.EXTRABOTANY;
    }
}
