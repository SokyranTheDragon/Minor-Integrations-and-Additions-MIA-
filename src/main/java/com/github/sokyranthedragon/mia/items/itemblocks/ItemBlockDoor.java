package com.github.sokyranthedragon.mia.items.itemblocks;

import com.github.sokyranthedragon.mia.block.IFurnaceFuel;
import net.minecraft.block.Block;
import net.minecraft.item.ItemDoor;
import net.minecraft.item.ItemStack;

public class ItemBlockDoor extends ItemDoor
{
    protected final IFurnaceFuel furnaceFuel;
    
    public ItemBlockDoor(Block block)
    {
        super(block);
        
        if (block instanceof IFurnaceFuel)
            furnaceFuel = (IFurnaceFuel)block;
        else
            furnaceFuel = null;
    }
    
    @Override
    public int getItemBurnTime(ItemStack stack)
    {
        if (furnaceFuel == null)
            return 0;
        else
            return furnaceFuel.getBurnTime(stack);
    }
}
