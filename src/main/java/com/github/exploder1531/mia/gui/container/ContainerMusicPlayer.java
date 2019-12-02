package com.github.exploder1531.mia.gui.container;

import com.github.exploder1531.mia.gui.slots.MusicPlayerSlot;
import com.github.exploder1531.mia.inventory.MusicPlayerInventory;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemRecord;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

public class ContainerMusicPlayer extends Container
{
    private final MusicPlayerInventory inventory;
    private int slots = 0;
    
    public ContainerMusicPlayer(InventoryPlayer playerInventory, MusicPlayerInventory inventory)
    {
        this.inventory = inventory;
        inventory.inventory.container = this;
//        this.addSlotToContainer(new InventoryValidityCheckSlot(inventory, -1, 80, 6));
        
        for (int i = 0; i < 3; i++)
            for (int k = 0; k < 9; k++)
                this.addSlotToContainer(new Slot(playerInventory, k + i * 9 + 9, 8 + k * 18, 84 + i * 18));
        
        for (int i = 0; i < 9; i++)
            this.addSlotToContainer(new Slot(playerInventory, i, 8 + i * 18, 142));
        
        while (slots < inventory.getSizeInventory() + 1)
            insertNextSlot();
    }
    
    @Override
    public boolean canInteractWith(@Nonnull EntityPlayer player)
    {
        return player.getHeldItemMainhand() == inventory.item || player.getHeldItemOffhand() == inventory.item;
    }
    
    public void insertNextSlot()
    {
        addSlotToContainer(new MusicPlayerSlot(inventory, slots, 26 + (slots % 7) * 18, 54));
        slots++;
    }
    
    public int getSlots()
    {
        return slots;
    }
    
    @Nonnull
    @Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index)
    {
        ItemStack stack = ItemStack.EMPTY;
        Slot slotObject = inventorySlots.get(index);
        
        if (slotObject != null && slotObject.getHasStack())
        {
            ItemStack stackInSlot = slotObject.getStack();
            stack = stackInSlot.copy();
            
            if (slotObject.inventory == this.inventory)
            {
                if (!mergeItemStack(stackInSlot, 0, 36, true))
                    return ItemStack.EMPTY;
            }
            else
            {
                if (!(stackInSlot.getItem() instanceof ItemRecord) || !mergeItemStack(stackInSlot, 36, 37 + inventory.getSizeInventory(), false))
                    return ItemStack.EMPTY;
            }
            
            if (stackInSlot.getCount() == 0)
                slotObject.putStack(ItemStack.EMPTY);
            else
                slotObject.onSlotChanged();
        }
        
        return stack;
    }
}
