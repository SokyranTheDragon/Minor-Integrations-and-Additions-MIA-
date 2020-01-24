package com.github.sokyranthedragon.mia.tile;

import com.github.alexthe666.iceandfire.core.ModBlocks;
import com.github.alexthe666.iceandfire.core.ModItems;
import com.github.sokyranthedragon.mia.integrations.theoneprobe.ITileProbeProgress;
import com.github.sokyranthedragon.mia.utilities.ExtraUtilitiesUtils;
import com.github.sokyranthedragon.mia.utilities.HatcheryUtils;
import com.github.sokyranthedragon.mia.utilities.ThermalExpansionUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;

import javax.annotation.Nonnull;
import java.util.stream.IntStream;

@SuppressWarnings("WeakerAccess")
public class TilePixieDustExtractor extends TileBaseInventory implements ITickable, ITileProbeProgress
{
    private static final int[] slots = IntStream.rangeClosed(0, 10).toArray();
    public int dustProgress = 0;
    public int pixiesTotal = 0;
    
    public TilePixieDustExtractor()
    {
        super(slots.length);
    }
    
    @Nonnull
    @Override
    public int[] getSlotsForFace(@Nonnull EnumFacing side)
    {
        return slots;
    }
    
    @Override
    public boolean canInsertItem(int index, @Nonnull ItemStack itemStackIn, @Nonnull EnumFacing direction)
    {
        return index < 10 && isItemValidForSlot(index, itemStackIn);
    }
    
    @Override
    public boolean canExtractItem(int index, @Nonnull ItemStack stack, @Nonnull EnumFacing direction)
    {
        return index == 10;
    }
    
    @Override
    public int getSizeInventory()
    {
        return slots.length;
    }
    
    @Nonnull
    @Override
    public ItemStack decrStackSize(int index, int count)
    {
        ItemStack stack = super.decrStackSize(index, count);
        updatePixieTotal();
        return stack;
    }
    
    @Nonnull
    @Override
    public ItemStack removeStackFromSlot(int index)
    {
        ItemStack stack = super.removeStackFromSlot(index);
        updatePixieTotal();
        return stack;
    }
    
    @Override
    public int getField(int id)
    {
        return id == 0 ? dustProgress : 0;
    }
    
    @Override
    public void setField(int id, int value)
    {
        if (id == 0)
            dustProgress = value;
    }
    
    @Override
    public int getFieldCount()
    {
        return 1;
    }
    
    protected void updatePixieTotal()
    {
        pixiesTotal = 0;
        
        for (int i = 0; i < getSizeInventory() - 1; i++)
        {
            if (!stacks.get(i).isEmpty())
                pixiesTotal++;
        }
    }
    
    @Override
    public void setInventorySlotContents(int index, @Nonnull ItemStack stack)
    {
        stacks.set(index, stack);
        if (!stack.isEmpty() && stack.getCount() > getInventoryStackLimit())
            stack.setCount(getInventoryStackLimit());
        
        updatePixieTotal();
        
        if (index <= 9)
            markDirty();
    }
    
    @Override
    public int getInventoryStackLimit()
    {
        return 1;
    }
    
    @Override
    public boolean isUsableByPlayer(@Nonnull EntityPlayer player)
    {
        return true;
    }
    
    @Override
    public boolean isItemValidForSlot(int index, @Nonnull ItemStack stack)
    {
        if (index >= 10)
            return false;
        if (!stacks.get(index).isEmpty())
            return false;
        return isItemValid(stack);
    }
    
    public static boolean isItemValid(@Nonnull ItemStack stack)
    {
        if (stack.getItem() == Item.getItemFromBlock(ModBlocks.jar_pixie))
            return true;
        else return ThermalExpansionUtils.isItemStackMorbWithMob(stack, "iceandfire:if_pixie") ||
                HatcheryUtils.isItemAnimalNetWithMob(stack, "iceandfire:if_pixie") ||
                ExtraUtilitiesUtils.isItemLassoWithMob(stack, "iceandfire:if_pixie");
    }
    
    @Override
    public void readFromNBT(NBTTagCompound compound)
    {
        dustProgress = compound.getInteger("DustProgress");
        ItemStackHelper.loadAllItems(compound, stacks);
        super.readFromNBT(compound);
        updatePixieTotal();
    }
    
    @Nonnull
    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound);
        compound.setInteger("DustProgress", dustProgress);
        ItemStackHelper.saveAllItems(compound, stacks);
        return compound;
    }
    
    @Override
    public void update()
    {
        if (pixiesTotal == 0)
        {
            dustProgress = 0;
            return;
        }
        
        ItemStack dust = stacks.get(10);
        
        if (dust.isEmpty() || dust.getCount() < dust.getMaxStackSize())
        {
            dustProgress += pixiesTotal;
            
            while (dustProgress >= 24_000)
            {
                dustProgress -= 24_000;
                
                if (dust.isEmpty())
                    stacks.set(10, new ItemStack(ModItems.pixie_dust));
                else
                    dust.setCount(dust.getCount() + 1);
                
                markDirty();
            }
        }
    }
    
    @Nonnull
    @Override
    public String getName()
    {
        return "tile.pixie_dust_extractor.name";
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
        return new TextComponentTranslation("tile.pixie_dust_extractor.name");
    }
    
    @Override
    public int getProgress()
    {
        return dustProgress;
    }
    
    @Override
    public int getProgressMax()
    {
        return 24_000;
    }
}
