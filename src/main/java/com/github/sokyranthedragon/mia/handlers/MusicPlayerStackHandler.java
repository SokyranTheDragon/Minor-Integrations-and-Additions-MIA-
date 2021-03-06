package com.github.sokyranthedragon.mia.handlers;

import com.github.sokyranthedragon.mia.gui.container.ContainerMusicPlayer;
import net.minecraft.item.ItemRecord;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.NonNullList;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.items.IItemHandlerModifiable;

import javax.annotation.Nonnull;
import java.util.UUID;

@SuppressWarnings("WeakerAccess")
public class MusicPlayerStackHandler implements IItemHandlerModifiable, INBTSerializable<NBTTagCompound>
{
    protected NonNullList<ItemStack> stacks;
    private int currentSongSlot = 0;
    public ContainerMusicPlayer container = null;
    public UUID itemUuid;
    
    public boolean repeat = false;
    public boolean autoplay = true;
    public boolean shuffle = false;
    
    public MusicPlayerStackHandler()
    {
        stacks = NonNullList.create();
        stacks.add(ItemStack.EMPTY);
        
        itemUuid = UUID.randomUUID();
    }
    
    @Override
    public boolean isItemValid(int slot, @Nonnull ItemStack stack)
    {
        return stack.getItem() instanceof ItemRecord && stacks.stream().noneMatch(i -> i.isItemEqual(stack));
    }
    
    @Override
    public void setStackInSlot(int slot, @Nonnull ItemStack stack)
    {
        if (slot < 0 || slot >= stacks.size())
            return;
        
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
        if (amount == 0 || slot >= stacks.size())
            return ItemStack.EMPTY;
        
        ItemStack existing = stacks.get(slot);
        if (existing.isEmpty())
            return ItemStack.EMPTY;
        else if (!simulate && slot >= 0)
        {
            stacks.remove(slot);
//            onContentsChanged(slot);
        }
        
        return existing;
    }
    
    @Override
    public int getSlots()
    {
        for (int i = stacks.size() - 1; i >= 0; i--)
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
        if (slot < 0)
            return stacks.get(currentSongSlot);
        validateSlotIndex(slot);
        return stacks.get(slot);
    }
    
    public void markDirty()
    {
        if (stacks.size() == 0 || !stacks.get(stacks.size() - 1).isEmpty())
            stacks.add(ItemStack.EMPTY);
        
        if (currentSongSlot >= stacks.size())
            currentSongSlot = stacks.size() - 2;
        
        if (stacks.size() > 1)
        {
            for (int i = stacks.size() - 2; i >= 0; i--)
            {
                if (stacks.get(i).isEmpty())
                {
                    stacks.remove(i);
                    if (currentSongSlot >= 1)
                        currentSongSlot--;
                }
            }
        }
        
        if (currentSongSlot < 0)
            currentSongSlot = 0;
    }
    
    @Override
    public int getSlotLimit(int slot)
    {
        return 1;
    }
    
    public NBTTagCompound serializeNBT()
    {
        NBTTagList nbtTagList = new NBTTagList();
        
        for (ItemStack stack : stacks)
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
        nbt.setInteger("CurrentSong", currentSongSlot);
        nbt.setBoolean("Autoplay", autoplay);
        nbt.setBoolean("Repeat", repeat);
        nbt.setBoolean("Shuffle", shuffle);
        nbt.setUniqueId("Uuid", itemUuid);
        return nbt;
    }
    
    public void deserializeNBT(NBTTagCompound nbt)
    {
        stacks = NonNullList.create();
        NBTTagList tagList = nbt.getTagList("Items", Constants.NBT.TAG_COMPOUND);
        currentSongSlot = nbt.getInteger("CurrentSong");
        autoplay = nbt.getBoolean("Autoplay");
        repeat = nbt.getBoolean("Repeat");
        shuffle = nbt.getBoolean("Shuffle");
        itemUuid = nbt.getUniqueId("Uuid");
        
        for (int i = 0; i < tagList.tagCount(); ++i)
            stacks.add(new ItemStack(tagList.getCompoundTagAt(i)));
        
        markDirty();
    }
    
    protected void validateSlotIndex(int slot)
    {
        if (slot < 0 || slot >= stacks.size())
            throw new RuntimeException("Slot " + slot + " not in valid range - [0," + stacks.size() + ")");
    }
    
    public ItemStack getCurrentSong()
    {
        return stacks.get(currentSongSlot);
    }
    
    public int getCurrentSongSlot()
    {
        return currentSongSlot;
    }
    
    public void setCurrentSongSlot(int slot)
    {
        int totalSlots = getSlots();
        
        if (slot > totalSlots - 1)
            currentSongSlot = 0;
        else if (slot < 0)
            currentSongSlot = totalSlots - 1;
        else
            currentSongSlot = slot;
    }
    
    public void nextSong()
    {
        setCurrentSongSlot(currentSongSlot + 1);
    }
    
    public void previousSong()
    {
        setCurrentSongSlot(currentSongSlot - 1);
    }
}
