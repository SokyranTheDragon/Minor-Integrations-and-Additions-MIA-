package com.github.sokyranthedragon.mia.gui.slots;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class InventoryValidityCheckSlot extends Slot
{
    public InventoryValidityCheckSlot(IInventory inventoryIn, int index, int xPosition, int yPosition)
    {
        super(inventoryIn, index, xPosition, yPosition);
    }
    
    @Override
    public boolean isItemValid(ItemStack stack)
    {
        return inventory.isItemValidForSlot(getSlotIndex(), stack);
    }
}
