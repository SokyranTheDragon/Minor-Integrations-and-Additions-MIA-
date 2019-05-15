package com.github.exploder1531.mia.gui.container;

import com.github.exploder1531.mia.gui.slots.IInventoryValidityCheckSlot;
import com.github.exploder1531.mia.tile.TileEggSorter;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ClickType;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

@SuppressWarnings("WeakerAccess")
public class ContainerEggSorter extends Container
{
    private final IInventory eggSorter;
    
    public ContainerEggSorter(InventoryPlayer playerInventory, IInventory eggSorter)
    {
        this.eggSorter = eggSorter;
        
        for (int i = 0; i < 3; i++)
            for (int k = 0; k < 3; k++)
                this.addSlotToContainer(new IInventoryValidityCheckSlot(eggSorter, i * 3 + k, 8 + i * 4 * 18, 18 + k * 18));
        
        for (int i = 0; i < 3; i++)
            for (int k = 0; k < 9; k++)
                this.addSlotToContainer(new Slot(playerInventory, k + i * 9 + 9, 8 + k * 18, 84 + i * 18));
        
        for (int i = 0; i < 9; i++)
            this.addSlotToContainer(new Slot(playerInventory, i, 8 + i * 18, 142));
    }
    
    @Override
    public boolean canInteractWith(@Nonnull EntityPlayer player)
    {
        return eggSorter.isUsableByPlayer(player);
    }
    
    @Nonnull
    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int index)
    {
        ItemStack stack = ItemStack.EMPTY;
        Slot slotObject = inventorySlots.get(index);
        
        if (slotObject != null && slotObject.getHasStack())
        {
            ItemStack stackInSlot = slotObject.getStack();
            stack = stackInSlot.copy();
            
            if (index <= 8)
            {
                ItemStack split = stackInSlot.splitStack(16);
                if (!mergeItemStack(split, 9, inventorySlots.size(), true))
                    return ItemStack.EMPTY;
            }
            else
            {
                if (!TileEggSorter.isItemValid(stackInSlot) || !mergeItemStack(stackInSlot, 0, 3, false, false))
                    return ItemStack.EMPTY;
            }
            
            if (stackInSlot.getCount() == 0)
                slotObject.putStack(ItemStack.EMPTY);
            else
                slotObject.onSlotChanged();
        }
        
        return stack;
    }
    
    @Nonnull
    @Override
    public ItemStack slotClick(int slotId, int dragType, ClickType clickTypeIn, EntityPlayer player)
    {
        if (clickTypeIn == ClickType.PICKUP && slotId != -999)
        {
            if (slotId < 0)
            {
                return ItemStack.EMPTY;
            }
            
            Slot inventory = this.inventorySlots.get(slotId);
            ItemStack returnStack = ItemStack.EMPTY;
            
            if (inventory != null)
            {
                InventoryPlayer inventoryplayer = player.inventory;
                ItemStack containerSlot = inventory.getStack();
                ItemStack playerSlot = inventoryplayer.getItemStack();
                
                if (!containerSlot.isEmpty())
                {
                    returnStack = containerSlot.copy();
                }
                
                if (containerSlot.isEmpty())
                {
                    if (!playerSlot.isEmpty() && inventory.isItemValid(playerSlot))
                    {
                        int count = dragType == 0 ? playerSlot.getCount() : 1;
                        
                        if (count > inventory.getItemStackLimit(playerSlot))
                        {
                            count = inventory.getItemStackLimit(playerSlot);
                        }
                        
                        inventory.putStack(playerSlot.splitStack(count));
                    }
                }
                else if (inventory.canTakeStack(player))
                {
                    if (playerSlot.isEmpty())
                    {
                        if (containerSlot.isEmpty())
                        {
                            inventory.putStack(ItemStack.EMPTY);
                            inventoryplayer.setItemStack(ItemStack.EMPTY);
                        }
                        else
                        {
                            int count = dragType == 0 ? containerSlot.getCount() : (containerSlot.getCount() + 1) / 2;
                            if (count > 16)
                                count = 16;
                            
                            inventoryplayer.setItemStack(inventory.decrStackSize(count));
                            
                            if (containerSlot.isEmpty())
                            {
                                inventory.putStack(ItemStack.EMPTY);
                            }
                            
                            inventory.onTake(player, inventoryplayer.getItemStack());
                        }
                    }
                    else if (inventory.isItemValid(playerSlot))
                    {
                        if (containerSlot.getItem() == playerSlot.getItem() && containerSlot.getMetadata() == playerSlot.getMetadata() && ItemStack.areItemStackTagsEqual(containerSlot, playerSlot))
                        {
                            int count = dragType == 0 ? playerSlot.getCount() : 1;
                            
                            if (count > inventory.getItemStackLimit(playerSlot) - containerSlot.getCount())
                            {
                                count = inventory.getItemStackLimit(playerSlot) - containerSlot.getCount();
                            }
                            
                            if (count > playerSlot.getMaxStackSize() - containerSlot.getCount())
                            {
                                count = playerSlot.getMaxStackSize() - containerSlot.getCount();
                            }
                            
                            playerSlot.shrink(count);
                            containerSlot.grow(count);
                        }
                        else if (playerSlot.getCount() <= inventory.getItemStackLimit(playerSlot))
                        {
                            inventory.putStack(playerSlot);
                            inventoryplayer.setItemStack(containerSlot);
                        }
                    }
                    else if (containerSlot.getItem() == playerSlot.getItem() && playerSlot.getMaxStackSize() > 1 && (!containerSlot.getHasSubtypes() || containerSlot.getMetadata() == playerSlot.getMetadata()) && ItemStack.areItemStackTagsEqual(containerSlot, playerSlot) && !containerSlot.isEmpty())
                    {
                        int count = containerSlot.getCount();
                        
                        if (count + playerSlot.getCount() <= 16)
                        {
                            playerSlot.grow(count);
                            containerSlot = inventory.decrStackSize(count);
                            
                            if (containerSlot.isEmpty())
                            {
                                inventory.putStack(ItemStack.EMPTY);
                            }
                            
                            inventory.onTake(player, inventoryplayer.getItemStack());
                        }
                    }
                }
                
                inventory.onSlotChanged();
            }
            
            return returnStack;
        }
        
        return super.slotClick(slotId, dragType, clickTypeIn, player);
    }
    
    @Override
    protected boolean mergeItemStack(ItemStack stack, int startIndex, int endIndex, boolean reverseDirection)
    {
        return mergeItemStack(stack, startIndex, endIndex, reverseDirection, true);
    }

    protected boolean mergeItemStack(ItemStack stack, int startIndex, int endIndex, boolean reverseDirection, boolean insertToInventory)
    {
        boolean flag = false;
        int i = startIndex;

        if (reverseDirection)
        {
            i = endIndex - 1;
        }

        if (stack.isStackable())
        {
            while (!stack.isEmpty())
            {
                if (reverseDirection)
                {
                    if (i < startIndex)
                    {
                        break;
                    }
                }
                else if (i >= endIndex)
                {
                    break;
                }

                Slot slot = this.inventorySlots.get(i);
                ItemStack itemstack = slot.getStack();
                if (insertToInventory || eggSorter.isItemValidForSlot(i, itemstack))
                {
                    if (!itemstack.isEmpty() && itemstack.getItem() == stack.getItem() && (!stack.getHasSubtypes() || stack.getMetadata() == itemstack.getMetadata()) && ItemStack.areItemStackTagsEqual(stack, itemstack))
                    {
                        int j = itemstack.getCount() + stack.getCount();
                        int maxSize = insertToInventory ? stack.getMaxStackSize() : slot.getSlotStackLimit();

                        if (j <= maxSize)
                        {
                            stack.setCount(0);
                            itemstack.setCount(j);
                            slot.onSlotChanged();
                            flag = true;
                        }
                        else if (itemstack.getCount() < maxSize)
                        {
                            stack.shrink(maxSize - itemstack.getCount());
                            itemstack.setCount(maxSize);
                            slot.onSlotChanged();
                            flag = true;
                        }
                    }
                }

                if (reverseDirection)
                {
                    --i;
                }
                else
                {
                    ++i;
                }
            }
        }

        if (!stack.isEmpty())
        {
            if (reverseDirection)
            {
                i = endIndex - 1;
            }
            else
            {
                i = startIndex;
            }

            while (true)
            {
                if (reverseDirection)
                {
                    if (i < startIndex)
                    {
                        break;
                    }
                }
                else if (i >= endIndex)
                {
                    break;
                }

                Slot slot1 = this.inventorySlots.get(i);
                ItemStack itemstack1 = slot1.getStack();

                if (itemstack1.isEmpty() && slot1.isItemValid(stack))
                {
                    if (stack.getCount() > slot1.getSlotStackLimit())
                    {
                        slot1.putStack(stack.splitStack(slot1.getSlotStackLimit()));
                    }
                    else
                    {
                        slot1.putStack(stack.splitStack(stack.getCount()));
                    }

                    slot1.onSlotChanged();
                    flag = true;
                    break;
                }

                if (reverseDirection)
                {
                    --i;
                }
                else
                {
                    ++i;
                }
            }
        }

        return flag;
    }
}
