package com.github.exploder1531.mia.integrations.mocreatures;

import com.gendeathrow.hatchery.api.crafting.ShredderRecipe;
import com.gendeathrow.hatchery.block.shredder.ShredderTileEntity;
import com.gendeathrow.hatchery.core.config.ConfigLootHandler;
import com.github.exploder1531.mia.integrations.ModIds;
import com.github.exploder1531.mia.integrations.hatchery.IHatcheryIntegration;
import drzhark.mocreatures.init.MoCItems;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;
import java.util.LinkedList;
import java.util.List;

import static com.github.exploder1531.mia.integrations.hatchery.Hatchery.getDrop;

@MethodsReturnNonnullByDefault
class HatcheryMoCreaturesIntegration implements IHatcheryIntegration
{
    private final boolean modEnabled;
    
    HatcheryMoCreaturesIntegration(boolean modEnabled)
    {
        this.modEnabled = modEnabled;
    }
    
    @Override
    public void registerShredder()
    {
        ShredderTileEntity.shredderRecipes.add(new ShredderRecipe(new ItemStack(MoCItems.sharkteeth), new ItemStack(Items.DYE, 2, 15)));
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
        
        drops.add(getDrop(MoCItems.whip, 5));
        drops.add(getDrop(MoCItems.silversword, 2));
        drops.add(getDrop(MoCItems.essencedarkness, 1));
        drops.add(getDrop(MoCItems.essencefire, 1));
        drops.add(getDrop(MoCItems.essencelight, 1));
        drops.add(getDrop(MoCItems.essencedarkness, 1));
        drops.add(getDrop(MoCItems.essenceundead, 1));
        drops.add(getDrop(MoCItems.recordshuffle, 1));
        
        return drops;
    }
    
    @Override
    public ModIds getModId()
    {
        return ModIds.MO_CREATURES;
    }
}
