package com.github.sokyranthedragon.mia.integrations.futuremc;

import com.gendeathrow.hatchery.api.crafting.ShredderRecipe;
import com.gendeathrow.hatchery.block.shredder.ShredderTileEntity;
import com.gendeathrow.hatchery.core.config.ConfigLootHandler;
import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.integrations.hatchery.IHatcheryIntegration;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.item.ItemStack;
import thedarkcolour.futuremc.registry.FBlocks;
import thedarkcolour.futuremc.registry.FItems;

import javax.annotation.Nonnull;
import java.util.LinkedList;
import java.util.List;

import static com.github.sokyranthedragon.mia.integrations.hatchery.Hatchery.getDrop;

@MethodsReturnNonnullByDefault
class HatcheryFutureMcIntegration implements IHatcheryIntegration
{
    private final boolean modEnabled;
    
    HatcheryFutureMcIntegration(boolean modEnabled)
    {
        this.modEnabled = modEnabled;
    }
    
    @Override
    public void registerShredder()
    {
        ShredderTileEntity.shredderRecipes.add(new ShredderRecipe(new ItemStack(FBlocks.INSTANCE.getLILY_OF_THE_VALLEY()), new ItemStack(FItems.INSTANCE.getDYES(), 2, 0)));
        ShredderTileEntity.shredderRecipes.add(new ShredderRecipe(new ItemStack(FBlocks.INSTANCE.getCORNFLOWER()), new ItemStack(FItems.INSTANCE.getDYES(), 2, 1)));
        ShredderTileEntity.shredderRecipes.add(new ShredderRecipe(new ItemStack(FBlocks.INSTANCE.getWITHER_ROSE()), new ItemStack(FItems.INSTANCE.getDYES(), 2, 3)));
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
        
        drops.add(getDrop(FItems.INSTANCE.getTRIDENT(), 1));
        drops.add(getDrop(FBlocks.INSTANCE.getBLUE_ICE(), 1));
        
        return drops;
    }
    
    @Override
    public ModIds getModId()
    {
        return ModIds.FUTURE_MC;
    }
}
