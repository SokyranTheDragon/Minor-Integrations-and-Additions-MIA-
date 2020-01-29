package com.github.sokyranthedragon.mia.integrations.biomesoplenty;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.api.item.BOPItems;
import com.gendeathrow.hatchery.core.config.ConfigLootHandler.ItemDrop;
import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.integrations.hatchery.Hatchery;
import com.github.sokyranthedragon.mia.integrations.hatchery.IHatcheryIntegration;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;
import java.util.LinkedList;
import java.util.List;

import static com.github.sokyranthedragon.mia.integrations.hatchery.Hatchery.getDrop;

@MethodsReturnNonnullByDefault
class HatcheryBopIntegration implements IHatcheryIntegration
{
    private boolean isModEnabled;
    
    public HatcheryBopIntegration(boolean isModEnabled)
    {
        this.isModEnabled = isModEnabled;
    }
    
    @Override
    public boolean isModEnabled()
    {
        return isModEnabled;
    }
    
    @Override
    public void registerShredder()
    {
        Hatchery.registerShredder(new ItemStack(BOPBlocks.plant_1, 1, 4), new ItemStack(BOPItems.brown_dye, 3));
        Hatchery.registerShredder(new ItemStack(BOPBlocks.plant_1, 1, 10), new ItemStack(Items.DYE, 3, 1));
        Hatchery.registerShredder(new ItemStack(BOPBlocks.double_plant, 1, 0), new ItemStack(Items.DYE, 3, 12));
        Hatchery.registerShredder(new ItemStack(BOPBlocks.double_plant, 1, 1), new ItemStack(BOPItems.brown_dye, 3));
        Hatchery.registerShredder(new ItemStack(BOPBlocks.mushroom, 1, 2), new ItemStack(BOPItems.blue_dye, 3));
        Hatchery.registerShredder(new ItemStack(BOPBlocks.mushroom, 1, 3), new ItemStack(Items.DYE, 3, 10));
        Hatchery.registerShredder(new ItemStack(BOPBlocks.mushroom, 1, 4), new ItemStack(BOPItems.brown_dye, 3));
        Hatchery.registerShredder(new ItemStack(BOPBlocks.flower_0, 1, 0), new ItemStack(Items.DYE, 3, 7));
        Hatchery.registerShredder(new ItemStack(BOPBlocks.flower_0, 1, 1), new ItemStack(Items.DYE, 3, 6));
        Hatchery.registerShredder(new ItemStack(BOPBlocks.flower_0, 1, 2), new ItemStack(BOPItems.black_dye, 3));
        Hatchery.registerShredder(new ItemStack(BOPBlocks.flower_0, 1, 3), new ItemStack(Items.DYE, 3, 6));
        Hatchery.registerShredder(new ItemStack(BOPBlocks.flower_0, 1, 4), new ItemStack(Items.DYE, 3, 12));
        Hatchery.registerShredder(new ItemStack(BOPBlocks.flower_0, 1, 5), new ItemStack(Items.DYE, 3, 14));
        Hatchery.registerShredder(new ItemStack(BOPBlocks.flower_0, 1, 6), new ItemStack(Items.DYE, 3, 9));
        Hatchery.registerShredder(new ItemStack(BOPBlocks.flower_0, 1, 7), new ItemStack(Items.DYE, 3, 13));
        Hatchery.registerShredder(new ItemStack(BOPBlocks.flower_0, 1, 8), new ItemStack(Items.DYE, 3, 5));
        Hatchery.registerShredder(new ItemStack(BOPBlocks.flower_0, 1, 9), new ItemStack(BOPItems.white_dye, 3));
        Hatchery.registerShredder(new ItemStack(BOPBlocks.flower_0, 1, 10), new ItemStack(BOPItems.black_dye, 3));
        Hatchery.registerShredder(new ItemStack(BOPBlocks.flower_0, 1, 11), new ItemStack(Items.DYE, 3, 1));
        Hatchery.registerShredder(new ItemStack(BOPBlocks.flower_0, 1, 12), new ItemStack(Items.DYE, 3, 8));
        Hatchery.registerShredder(new ItemStack(BOPBlocks.flower_0, 1, 13), new ItemStack(Items.DYE, 3, 9));
        Hatchery.registerShredder(new ItemStack(BOPBlocks.flower_0, 1, 14), new ItemStack(BOPItems.white_dye, 3));
        Hatchery.registerShredder(new ItemStack(BOPBlocks.flower_0, 1, 15), new ItemStack(Items.DYE, 3, 14));
        Hatchery.registerShredder(new ItemStack(BOPBlocks.flower_1, 1, 0), new ItemStack(Items.DYE, 3, 5));
        Hatchery.registerShredder(new ItemStack(BOPBlocks.flower_1, 1, 1), new ItemStack(Items.DYE, 3, 11));
        Hatchery.registerShredder(new ItemStack(BOPBlocks.flower_1, 1, 2), new ItemStack(BOPItems.blue_dye, 3));
        Hatchery.registerShredder(new ItemStack(BOPBlocks.flower_1, 1, 3), new ItemStack(Items.DYE, 3, 9));
        Hatchery.registerShredder(new ItemStack(BOPBlocks.flower_1, 1, 4), new ItemStack(Items.DYE, 3, 12));
        Hatchery.registerShredder(new ItemStack(BOPBlocks.flower_1, 1, 5), new ItemStack(Items.DYE, 3, 1));
    }
    
    @Override
    public int getCurrentLootVersion()
    {
        return 0;
    }
    
    @Nonnull
    @Override
    public List<ItemDrop> getDefaultEggDrops()
    {
        LinkedList<ItemDrop> entries = new LinkedList<>();
        
        for (int meta = 0; meta <= 7; meta++)
            entries.add(getDrop(BOPItems.gem, meta, 1));
        
        return entries;
    }
    
    @Override
    public ModIds getModId()
    {
        return ModIds.BIOMES_O_PLENTY;
    }
}
