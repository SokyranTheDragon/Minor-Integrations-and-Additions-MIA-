package com.github.exploder1531.mia.gui.container;

import com.github.exploder1531.mia.gui.slots.IInventoryValidityCheckSlot;
import com.github.exploder1531.mia.tile.TilePixieDustExtractor;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.*;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;

public class ContainerPixieDustExtractor extends Container
{
    private final IInventory pixieDustExtractor;
    private int dustProgress;
    
    public ContainerPixieDustExtractor(InventoryPlayer playerInventory, IInventory pixieDustExtractor)
    {
        this.pixieDustExtractor = pixieDustExtractor;
    
        for (int i = 0; i < 3; i++)
            for (int k = 0; k < 3; k++)
                this.addSlotToContainer(new IInventoryValidityCheckSlot(pixieDustExtractor, i * 3 + k, 30 + i * 18, 17 + k * 18));
        
        this.addSlotToContainer(new IInventoryValidityCheckSlot(pixieDustExtractor, 10, 126, 35));
        
        for (int i = 0; i < 3; i++)
            for (int k = 0; k < 9; k++)
                this.addSlotToContainer(new Slot(playerInventory, k + i * 9 + 9, 8 + k * 18, 84 + i * 18));
    
        for (int i = 0; i < 9; i++)
            this.addSlotToContainer(new Slot(playerInventory, i, 8 + i * 18, 142));
    }
    
    @Override
    public void addListener(IContainerListener listener)
    {
        super.addListener(listener);
        listener.sendAllWindowProperties(this, this.pixieDustExtractor);
    }
    
    @Override
    public void detectAndSendChanges()
    {
        super.detectAndSendChanges();
    
        if (dustProgress != pixieDustExtractor.getField(0))
        {
            for (IContainerListener listener : listeners)
                listener.sendWindowProperty(this, 0, pixieDustExtractor.getField(0));
        }
        
        dustProgress = pixieDustExtractor.getField(0);
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public void updateProgressBar(int id, int data)
    {
        pixieDustExtractor.setField(id, data);
    }
    
    @Override
    public boolean canInteractWith(@Nonnull EntityPlayer player)
    {
        return pixieDustExtractor.isUsableByPlayer(player);
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
            
            if (index <= 10)
            {
                if (!mergeItemStack(stackInSlot, 11, inventorySlots.size(), true))
                    return ItemStack.EMPTY;
            }
            else
            {
                if (!TilePixieDustExtractor.isItemValid(stackInSlot) || !mergeItemStack(stackInSlot, 0, 10, false))
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
