package com.github.exploder1531.mia.gui.slots;

import com.github.exploder1531.mia.inventory.MusicPlayerInventory;

public class MusicPlayerSlot extends InventoryValidityCheckSlot
{
    protected final MusicPlayerInventory inventory;
    
    public MusicPlayerSlot(MusicPlayerInventory inventory, int index, int xPosition, int yPosition)
    {
        super(inventory, index, xPosition, yPosition);
        
        this.inventory = inventory;
    }
    
    @Override
    public boolean isEnabled()
    {
        return getSlotIndex() >= inventory.openPage * 7 && getSlotIndex() < (inventory.openPage + 1) * 7 && getSlotIndex() <= inventory.getSizeInventory();
    }
}
