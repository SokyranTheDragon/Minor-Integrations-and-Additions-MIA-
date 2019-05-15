package com.github.exploder1531.mia.gui.slots;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class IInventoryValidityCheckSlot extends Slot
{
    public IInventoryValidityCheckSlot(IInventory inventoryIn, int index, int xPosition, int yPosition)
    {
        super(inventoryIn, index, xPosition, yPosition);
    }
    
    @Override
    public boolean isItemValid(ItemStack stack)
    {
        return inventory.isItemValidForSlot(getSlotIndex(), stack);
    }
}
