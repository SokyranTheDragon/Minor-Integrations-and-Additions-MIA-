package com.github.sokyranthedragon.mia.gui.slots;

import com.github.sokyranthedragon.mia.inventory.MusicPlayerInventory;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

public class FakeMusicPlayerSlot extends Slot
{
    protected final MusicPlayerInventory inventory;
    
    public FakeMusicPlayerSlot(MusicPlayerInventory inventory, int index, int xPosition, int yPosition)
    {
        super(inventory, index, xPosition, yPosition);
        
        this.inventory = inventory;
    }
    
    @Override
    public boolean canTakeStack(EntityPlayer playerIn)
    {
        return false;
    }
    
    @Nonnull
    @Override
    public ItemStack getStack()
    {
        return inventory.inventory.getCurrentSong();
    }
}
