package com.github.exploder1531.mia.gui.container;

import com.github.exploder1531.mia.tile.TileVoidCreator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import thaumcraft.common.container.slot.SlotOutput;

import javax.annotation.Nonnull;

public class ContainerVoidCreator extends Container
{
    private TileVoidCreator creator;
    private int lastProgress;
    
    public ContainerVoidCreator(InventoryPlayer playerInventory, TileVoidCreator creator)
    {
        this.creator = creator;
        
        addSlotToContainer(new SlotOutput(creator, 0, 80, 32));
        
        for (int i = 0; i < 3; i++)
            for (int k = 0; k < 9; k++)
                addSlotToContainer(new Slot(playerInventory, k + i * 9 + 9, 8 + k * 18, 84 + i + 18));
        
        for (int i = 0; i < 9; i++)
            addSlotToContainer(new Slot(playerInventory, i, 8 + i * 18, 142));
    }
    
    @Override
    public void addListener(IContainerListener crafting)
    {
        super.addListener(crafting);
        crafting.sendWindowProperty(this, 0, creator.progress);
    }
    
    @Override
    public void detectAndSendChanges()
    {
        super.detectAndSendChanges();
        
        if (lastProgress != creator.progress)
        {
            for (IContainerListener listener : listeners)
                listener.sendWindowProperty(this, 0, creator.progress);
        }
        
        lastProgress = creator.progress;
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public void updateProgressBar(int id, int data)
    {
        if (id == 0)
            creator.progress = data;
    }
    
    @Override
    public boolean canInteractWith(@Nonnull EntityPlayer playerIn)
    {
        return creator.isUsableByPlayer(playerIn);
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
            if (index == 0)
            {
                if (!creator.isItemValidForSlot(index, stackInSlot) || !mergeItemStack(stackInSlot, 1, inventorySlots.size(), true))
                    return ItemStack.EMPTY;
            }
            else if (!creator.isItemValidForSlot(index, stackInSlot) || !mergeItemStack(stackInSlot, 0, 1, false))
                return ItemStack.EMPTY;
            
            if (stackInSlot.getCount() == 0)
                slotObject.putStack(ItemStack.EMPTY);
            else
                slotObject.onSlotChanged();
        }
        
        return stack;
    }
}
