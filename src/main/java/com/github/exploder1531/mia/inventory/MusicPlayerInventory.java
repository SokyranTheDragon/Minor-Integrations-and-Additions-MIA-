package com.github.exploder1531.mia.inventory;

import com.github.exploder1531.mia.handlers.MusicPlayerStackHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;

import javax.annotation.Nonnull;

public class MusicPlayerInventory implements IInventory
{
    public final ItemStack item;
    public final MusicPlayerStackHandler inventory;
    public int openPage = 0;
    
    public MusicPlayerInventory(ItemStack item, MusicPlayerStackHandler inventory)
    {
        this.item = item;
        this.inventory = inventory;
    }
    
    @Override
    public int getSizeInventory()
    {
        return inventory.getSlots();
    }
    
    @Override
    public boolean isEmpty()
    {
        return inventory.getSlots() <= 0;
    }
    
    @Nonnull
    @Override
    public ItemStack getStackInSlot(int i)
    {
        if (i < 0)
            return inventory.getCurrentSong();
        else if (i < getSizeInventory())
            return inventory.getStackInSlot(i);
        else
            return ItemStack.EMPTY;
    }
    
    @Nonnull
    @Override
    public ItemStack decrStackSize(int i, int count)
    {
        ItemStack stack = inventory.getStackInSlot(i);
        if (count < 0 || stack.isEmpty())
            return ItemStack.EMPTY;
        inventory.extractItem(i, count, false);
        return stack;
    }
    
    @Nonnull
    @Override
    public ItemStack removeStackFromSlot(int i)
    {
        return ItemStack.EMPTY;
    }
    
    @Override
    public void setInventorySlotContents(int i, @Nonnull ItemStack itemStack)
    {
        inventory.setStackInSlot(i, itemStack);
    }
    
    @Override
    public int getInventoryStackLimit()
    {
        return 1;
    }
    
    @Override
    public void markDirty()
    {
        inventory.markDirty();
    }
    
    @Override
    public boolean isUsableByPlayer(@Nonnull EntityPlayer entityPlayer)
    {
        return true;
    }
    
    @Override
    public void openInventory(@Nonnull EntityPlayer entityPlayer)
    {
    
    }
    
    @Override
    public void closeInventory(@Nonnull EntityPlayer entityPlayer)
    {
        inventory.container = null;
    }
    
    @Override
    public boolean isItemValidForSlot(int i, @Nonnull ItemStack itemStack)
    {
        return inventory.isItemValid(i, itemStack);
    }
    
    @Override
    public int getField(int i)
    {
        return 0;
    }
    
    @Override
    public void setField(int i, int i1)
    {
    }
    
    @Override
    public int getFieldCount()
    {
        return 0;
    }
    
    @Override
    public void clear()
    {
    }
    
    @Nonnull
    @Override
    public String getName()
    {
        return "item.music_player.name";
    }
    
    @Override
    public boolean hasCustomName()
    {
        return false;
    }
    
    @Nonnull
    @Override
    public ITextComponent getDisplayName()
    {
        return new TextComponentString("item.music_player.name");
    }
}
