package com.github.exploder1531.mia.handlers;

import com.github.exploder1531.mia.gui.container.ContainerMusicPlayer;
import net.minecraft.item.ItemRecord;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;

import javax.annotation.Nonnull;

@SuppressWarnings("WeakerAccess")
public class MusicPlayerStackHandler implements IItemHandler, IItemHandlerModifiable, INBTSerializable<NBTTagCompound>
{
    protected NonNullList<ItemStack> stacks;
    public SoundEvent currentSong = null;
    public ItemStack currentSongItem = ItemStack.EMPTY;
    public ContainerMusicPlayer container = null;
    
    public MusicPlayerStackHandler()
    {
        stacks = NonNullList.create();
        stacks.add(ItemStack.EMPTY);
    }
    
    @Override
    public boolean isItemValid(int slot, @Nonnull ItemStack stack)
    {
        return stack.getItem() instanceof ItemRecord && stacks.stream().noneMatch(i -> i.isItemEqual(stack));
    }
    
    @Override
    public void setStackInSlot(int slot, @Nonnull ItemStack stack)
    {
        validateSlotIndex(slot);
        if (stacks.get(slot).isEmpty() && !stack.isEmpty())
        {
            stacks.add(slot, stack);
            if (container != null && container.getSlots() < stacks.size())
                container.insertNextSlot();
        }
        else if (!stacks.get(slot).isEmpty() && stack.isEmpty())
            stacks.remove(slot);
        else
            stacks.set(slot, stack);
//        onContentsChanged(slot);
    }
    
    @Nonnull
    @Override
    public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate)
    {
        if (stack.isEmpty())
            return ItemStack.EMPTY;
        if (!isItemValid(slot, stack))
            return stack;
        
        if (!simulate)
        {
            if (slot < 0)
                slot = 0;
            else if (slot >= stacks.size())
                slot = stacks.size() - 1;
            
            stacks.add(slot, stack);
//            onContentsChanged(slot);
            
            if (container != null && container.getSlots() < stacks.size())
                container.insertNextSlot();
        }
        
        return ItemStack.EMPTY;
    }
    
    @Nonnull
    @Override
    public ItemStack extractItem(int slot, int amount, boolean simulate)
    {
        if (amount == 0)
            return ItemStack.EMPTY;
        
        validateSlotIndex(slot);
        ItemStack existing = this.stacks.get(slot);
        if (existing.isEmpty())
            return ItemStack.EMPTY;
        else if (!simulate)
        {
            stacks.remove(slot);
//            onContentsChanged(slot);
        }
        
        return existing;
    }
    
    @Override
    public int getSlots()
    {
        for (int i = 0; i < stacks.size(); i++)
        {
            if (stacks.get(i).isEmpty())
                return i;
        }
        
        return 0;
    }
    
    @Nonnull
    @Override
    public ItemStack getStackInSlot(int slot)
    {
        this.validateSlotIndex(slot);
        return this.stacks.get(slot);
    }
    
    public void markDirty()
    {
        if (stacks.size() == 0 || !stacks.get(stacks.size() - 1).isEmpty())
            stacks.add(ItemStack.EMPTY);
        
        if (stacks.size() > 1)
        {
            for (int i = stacks.size() - 2; i >= 0; i--)
            {
                if (stacks.get(i).isEmpty())
                    stacks.remove(i);
            }
        }
    }
    
    @Override
    public int getSlotLimit(int slot)
    {
        return 1;
    }
    
    
    public NBTTagCompound serializeNBT()
    {
        NBTTagList nbtTagList = new NBTTagList();
        
        for (ItemStack stack : this.stacks)
        {
            if (!stack.isEmpty())
            {
                NBTTagCompound itemTag = new NBTTagCompound();
                stack.writeToNBT(itemTag);
                nbtTagList.appendTag(itemTag);
            }
        }
        
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setTag("Items", nbtTagList);
        return nbt;
    }
    
    public void deserializeNBT(NBTTagCompound nbt)
    {
        stacks = NonNullList.create();
        NBTTagList tagList = nbt.getTagList("Items", Constants.NBT.TAG_COMPOUND);
        
        for (int i = 0; i < tagList.tagCount(); ++i)
            this.stacks.add(new ItemStack(tagList.getCompoundTagAt(i)));
        
        markDirty();
    }
    
    protected void validateSlotIndex(int slot)
    {
        if (slot < 0 || slot >= this.stacks.size())
            throw new RuntimeException("Slot " + slot + " not in valid range - [0," + this.stacks.size() + ")");
    }
}
