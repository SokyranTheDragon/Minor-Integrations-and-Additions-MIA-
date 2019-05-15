package com.github.exploder1531.mia.tile;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

import javax.annotation.Nonnull;

@SuppressWarnings("WeakerAccess")
public abstract class TileBaseInventory extends TileBase implements ISidedInventory
{
    protected NonNullList<ItemStack> stacks;
    
    protected TileBaseInventory(int slots)
    {
        stacks = NonNullList.withSize(slots, ItemStack.EMPTY);
    }
    
    @Override
    public boolean isEmpty()
    {
        for (ItemStack itemStack : stacks)
        {
            if (!itemStack.isEmpty())
                return false;
        }
        
        return true;
    }
    
    @Nonnull
    @Override
    public ItemStack decrStackSize(int index, int count)
    {
        ItemStack stack = stacks.get(index);
        
        if (stack.isEmpty())
            return ItemStack.EMPTY;
        
        if (stack.getCount() <= count)
        {
            stacks.set(index, ItemStack.EMPTY);
            return stack;
        }
        else
        {
            ItemStack output = stack.splitStack(count);
            if (stack.isEmpty())
                stacks.set(index, ItemStack.EMPTY);
            return output;
        }
    }
    
    @Nonnull
    @Override
    public ItemStack removeStackFromSlot(int index)
    {
        ItemStack inventoryStack = stacks.get(index);
        stacks.set(index, ItemStack.EMPTY);
        
        return inventoryStack;
    }
    
    @Nonnull
    @Override
    public ItemStack getStackInSlot(int index)
    {
        return stacks.get(index);
    }
    
    @Override
    public int getField(int id)
    {
        return 0;
    }
    
    @Override
    public void setField(int id, int value)
    {
    }
    
    @Override
    public void openInventory(@Nonnull EntityPlayer player)
    {
    }
    
    @Override
    public void closeInventory(@Nonnull EntityPlayer player)
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
        stacks.clear();
    }
}
