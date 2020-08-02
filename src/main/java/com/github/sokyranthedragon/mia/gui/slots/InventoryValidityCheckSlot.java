package com.github.sokyranthedragon.mia.gui.slots;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class InventoryValidityCheckSlot extends Slot
{
    private int maxStackSize = -1;
    
    public InventoryValidityCheckSlot(IInventory inventoryIn, int index, int xPosition, int yPosition)
    {
        super(inventoryIn, index, xPosition, yPosition);
    }
    
    @Override
    public boolean isItemValid(ItemStack stack)
    {
        return inventory.isItemValidForSlot(getSlotIndex(), stack);
    }
    
    public void setMaxStackSize(int maxStackSize)
    {
        this.maxStackSize = maxStackSize;
    }
    
    @Override
    public int getSlotStackLimit()
    {
        if (maxStackSize <= 0)
            return super.getSlotStackLimit();
        else
            return maxStackSize;
    }
}
