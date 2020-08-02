package com.github.sokyranthedragon.mia.tile;

import com.github.sokyranthedragon.mia.integrations.theoneprobe.ITileProbeProgress;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import thaumcraft.api.aura.AuraHelper;
import thaumcraft.api.items.ItemsTC;
import thaumcraft.common.config.ModConfig;

import javax.annotation.Nonnull;

@SuppressWarnings("WeakerAccess")
public class TileVoidCreator extends TileBaseInventory implements ITickable, ITileProbeProgress
{
    private static final int[] slots = new int[]{0};
    int counter = 0;
    public int progress = 0;
    public final static int REQUIRED_PROGRESS = 2_000;
    
    public TileVoidCreator()
    {
        super(slots.length);
    }
    
    @Override
    public void update()
    {
        counter++;
        
        ItemStack item = getStackInSlot(0);
        
        if (!getWorld().isRemote && counter % 20 == 0 &&
                (item.isEmpty() ||
                        (item.getItem() == ItemsTC.voidSeed && item.getCount() < item.getMaxStackSize()) ||
                        (item.getItem() == ItemsTC.primordialPearl && item.getItemDamage() > 0)))
        {
            if (!ModConfig.CONFIG_MISC.wussMode)
            {
                progress -= 10;
                
                if (progress < 0)
                    progress = 0;
                
                return;
            }
            
            final float flux = AuraHelper.getFlux(world, pos);
            final float vis = AuraHelper.getVis(world, pos);
            
            if (flux <= 25 || flux <= vis)
                return;
            
            int drain = 5;
            progress++;
            
            if (world.rand.nextInt(33) == 0)
            {
                progress += 10;
                drain += 10;
            }
            if (counter % 40 == 0 && world.rand.nextInt(3) == 0)
            {
                progress += 2;
                drain += 5;
            }
            
            AuraHelper.drainFlux(world, pos, drain, false);
            
            boolean created = false;
            
            while (progress >= REQUIRED_PROGRESS)
            {
                progress -= REQUIRED_PROGRESS;
                
                created = true;
                
                if (item.getItem() == ItemsTC.primordialPearl)
                {
                    item.setItemDamage(item.getItemDamage() - 1);
                    
                    if (item.getItemDamage() <= 0)
                        break;
                }
                else
                {
                    if (item.isEmpty())
                    {
                        item = new ItemStack(ItemsTC.voidSeed);
                        setInventorySlotContents(0, item);
                    }
                    else
                        item.setCount(item.getCount() + 1);
                    
                    if (world.rand.nextInt(100) <= item.getCount())
                    {
                        item = new ItemStack(ItemsTC.primordialPearl, 1, 7);
                        setInventorySlotContents(0, item);
                    }
                    
                    if (item.getItem() == ItemsTC.primordialPearl)
                        break;
                }
            }
            
            if (created)
                markDirty();
        }
    }
    
    @Override
    public void readFromNBT(NBTTagCompound nbtCompound)
    {
        super.readFromNBT(nbtCompound);
        progress = nbtCompound.getShort("progress");
    }
    
    @Nonnull
    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbtCompound)
    {
        super.writeToNBT(nbtCompound);
        nbtCompound.setShort("progress", (short) this.progress);
        return nbtCompound;
    }
    
    @Override
    public boolean isItemValidForSlot(int par1, ItemStack stack)
    {
        return stack.getItem() == ItemsTC.voidSeed;
    }
    
    @Nonnull
    @Override
    public int[] getSlotsForFace(@Nonnull EnumFacing side)
    {
        return slots;
    }
    
    @Override
    public boolean canInsertItem(int par1, @Nonnull ItemStack stack2, @Nonnull EnumFacing par3)
    {
        return false;
    }
    
    @Override
    public boolean canExtractItem(int par1, @Nonnull ItemStack stack2, @Nonnull EnumFacing par3)
    {
        return true;
    }
    
    @Override
    public int getSizeInventory()
    {
        return slots.length;
    }
    
    @Override
    public void setInventorySlotContents(int index, @Nonnull ItemStack stack)
    {
        ItemStack inventoryStack = stacks.get(index);
        
        boolean areItemStacksEqual = !stack.isEmpty() && stack.isItemEqual(inventoryStack) && ItemStack.areItemStackTagsEqual(stack, inventoryStack);
        stacks.set(index, stack);
        if (!stack.isEmpty() && stack.getCount() > getInventoryStackLimit())
            stack.setCount(getInventoryStackLimit());
        
        if (index == 0 && !areItemStacksEqual)
            markDirty();
    }
    
    @Override
    public int getInventoryStackLimit()
    {
        return 64;
    }
    
    @Override
    public boolean isUsableByPlayer(@Nonnull EntityPlayer player)
    {
        return true;
    }
    
    @Override
    public int getProgress()
    {
        return progress;
    }
    
    @Override
    public int getProgressMax()
    {
        return REQUIRED_PROGRESS;
    }
    
    @Override
    public Integer getProgressHexColor()
    {
        return 0xFF312537;
    }
    
    @Override
    public Integer getProgressTintHexColor()
    {
        return 0xFF2C2131;
    }
    
    @Nonnull
    @Override
    public String getName()
    {
        return "tile.void_creator.name";
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
        return new TextComponentTranslation("tile.void_creator.name");
    }
}
