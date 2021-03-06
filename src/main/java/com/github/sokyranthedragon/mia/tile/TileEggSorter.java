package com.github.sokyranthedragon.mia.tile;

import com.gendeathrow.hatchery.core.init.ModItems;
import com.github.sokyranthedragon.mia.Mia;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;

import javax.annotation.Nonnull;
import java.util.stream.IntStream;

public class TileEggSorter extends TileBaseInventory implements ITickable
{
    private static final int[] slots = IntStream.rangeClosed(0, 8).toArray();
    
    private String eggEntityId = null;
    
    public TileEggSorter()
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
        return isItemValidForSlot(index, itemStackIn);
    }
    
    @Override
    public boolean canExtractItem(int index, @Nonnull ItemStack stack, @Nonnull EnumFacing direction)
    {
        return index >= 6;
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
        
        if (isEmpty())
            eggEntityId = null;
        
        return stack;
    }
    
    @Nonnull
    @Override
    public ItemStack removeStackFromSlot(int index)
    {
        ItemStack stack = super.removeStackFromSlot(index);
        
        if (isEmpty())
            eggEntityId = null;
        
        return stack;
    }
    
    @Override
    public void setInventorySlotContents(int index, @Nonnull ItemStack stack)
    {
        ItemStack inventoryStack = stacks.get(index);
        
        boolean areItemStacksEqual = !stack.isEmpty() && stack.isItemEqual(inventoryStack) && ItemStack.areItemStackTagsEqual(stack, inventoryStack);
        stacks.set(index, stack);
        if (!stack.isEmpty() && stack.getCount() > getInventoryStackLimit())
            stack.setCount(getInventoryStackLimit());
        
        if (eggEntityId == null && !stack.isEmpty())
            //noinspection ConstantConditions
            eggEntityId = stack.getTagCompound().getCompoundTag("storedEntity").getString("Type");
        else if (eggEntityId != null && stack.isEmpty() && isEmpty())
            eggEntityId = null;
        
        if (index <= 2 && !areItemStacksEqual)
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
    
    @SuppressWarnings("ConstantConditions")
    @Override
    public boolean isItemValidForSlot(int index, @Nonnull ItemStack stack)
    {
        if (index > 2)
            return false;
        if (!isItemValid(stack))
            return false;
        if (eggEntityId != null && !stack.getTagCompound().getCompoundTag("storedEntity").getString("Type").equals(eggEntityId))
            return false;
        ItemStack egg = stacks.get(index);
        return egg.isEmpty() || egg.isItemEqual(stack) && ItemStack.areItemStackTagsEqual(egg, stack);
    }
    
    @SuppressWarnings({ "RedundantIfStatement", "BooleanMethodIsAlwaysInverted", "ConstantConditions" })
    public static boolean isItemValid(ItemStack stack)
    {
        if (stack.getItem() != ModItems.hatcheryEgg)
            return false;
        if (!stack.hasTagCompound())
            return false;
        NBTTagCompound nbt = stack.getTagCompound();
        if (!nbt.hasKey("storedEntity"))
            return false;
        nbt = nbt.getCompoundTag("storedEntity");
        if (!nbt.hasKey("Growth"))
            return false;
        if (!nbt.hasKey("Gain"))
            return false;
        if (!nbt.hasKey("Strength"))
            return false;
        if (!nbt.hasKey("Type"))
            return false;
        return true;
    }
    
    @Override
    public void update()
    {
        boolean[] isDirty = { false };
        
        for (int i = 0; i < 3; i++)
        {
            if (processEgg(i, isDirty))
                break;
        }
        
        if (isDirty[0])
            markDirty();
    }
    
    @Nonnull
    @Override
    public String getName()
    {
        return "tile.egg_sorter.name";
    }
    
    @Override
    public boolean hasCustomName()
    {
        return false;
    }
    
    @SuppressWarnings("ConstantConditions")
    @Override
    public void readFromNBT(NBTTagCompound nbt)
    {
        super.readFromNBT(nbt);
        ItemStackHelper.loadAllItems(nbt, stacks);
        
        for (ItemStack egg : stacks)
        {
            if (egg != ItemStack.EMPTY)
                eggEntityId = egg.getTagCompound().getCompoundTag("storedEntity").getString("Type");
        }
    }
    
    @Nonnull
    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt)
    {
        super.writeToNBT(nbt);
        ItemStackHelper.saveAllItems(nbt, stacks);
        return nbt;
    }
    
    @Nonnull
    @Override
    public ITextComponent getDisplayName()
    {
        return new TextComponentTranslation("tile.egg_sorter.name");
    }
    
    private boolean processEgg(int eggIndex, boolean[] isDirty)
    {
        return processEgg(eggIndex, isDirty, 0);
    }
    
    @SuppressWarnings("ConstantConditions")
    private boolean processEgg(int eggIndex, boolean[] isDirty, int depth)
    {
        ItemStack stack = stacks.get(eggIndex);
        
        // Safety for recursive call, just in case for some reason we end up repeating it multiple times.
        if (depth > 10)
        {
            Mia.LOGGER.warn("Recursive call in egg sorter reached maximum depth, sorting stopped. This shouldn't happen. Please report this to mod developer.");
            
            isDirty[0] = true;
            stacks.set(eggIndex, ItemStack.EMPTY);
            
            if (stacks.get(6).isEmpty())
                stacks.set(6, stack);
            else if (stacks.get(7).isEmpty())
                stacks.set(7, stack);
            else if (stacks.get(8).isEmpty())
                stacks.set(8, stack);
            
            return true;
        }
        
        if (stack.isEmpty())
            return false;
        // Make sure we are 100% sure we can move the item into output if we need to get rid of one of the stacks
        if (!stacks.get(6).isEmpty() && !stacks.get(7).isEmpty() && !stacks.get(8).isEmpty())
            return true;
        
        // The 3 ints are what is important for comparison (we trust that the egg that was put in matches the type)
        final NBTTagCompound nbt = stack.getTagCompound().getCompoundTag("storedEntity");
        final int growth = nbt.getInteger("Growth");
        final int gain = nbt.getInteger("Gain");
        final int strength = nbt.getInteger("Strength");
        
        int slotMatch = 0;
        int improvement = 0;
        int improvementTotal = 0;
        
        // Iterate over the slots containing the 3 stats in order of: growth, gain, strength
        for (int k = 3; k < 6; k++)
        {
            ItemStack inventoryStack = stacks.get(k);
            
            // If we have an empty slot then it's safe to assume we can put our item there
            // We still continue to look through the other slots, in case we have a better match
            if (inventoryStack.isEmpty())
            {
                if (slotMatch == 0)
                    slotMatch = k;
            }
            else
            {
                // We get the same stats for the egg in the slot
                NBTTagCompound inventoryNbt = inventoryStack.getTagCompound().getCompoundTag("storedEntity");
                final int inventoryGrowth = inventoryNbt.getInteger("Growth");
                final int inventoryGain = inventoryNbt.getInteger("Gain");
                final int inventoryStrength = inventoryNbt.getInteger("Strength");
                
                // We've found a perfect match (identical stats), join the 2 stack if possible
                if (stack.isItemEqual(inventoryStack) && growth == inventoryGrowth && gain == inventoryGain && strength == inventoryStrength && inventoryStack.getCount() < getInventoryStackLimit())
                {
                    int total = stack.getCount() + inventoryStack.getCount();
                    
                    if (total <= getInventoryStackLimit())
                    {
                        inventoryStack.setCount(total);
                        stack = ItemStack.EMPTY;
                        stacks.set(eggIndex, ItemStack.EMPTY);
                        isDirty[0] = true;
                        break;
                    }
                    else
                    {
                        inventoryStack.setCount(getInventoryStackLimit());
                        stack.setCount(total - getInventoryStackLimit());
                    }
                }
                // If it's not a perfect match, then we check which item is better to keep in here
                else
                {
                    final int total = growth + gain + strength;
                    final int inventoryTotal = inventoryGrowth + inventoryGain + inventoryStrength;
                    int tempImprovement;
                    // We check by how much the total stats have improved (sum of all 3)
                    int tempImprovementTotal = total - inventoryTotal;
                    
                    // We check how the main stat (based on slot) increased
                    switch (k)
                    {
                        default:
                        case 3:
                            tempImprovement = growth - inventoryGrowth;
                            break;
                        case 4:
                            tempImprovement = gain - inventoryGain;
                            break;
                        case 5:
                            tempImprovement = strength - inventoryStrength;
                            break;
                    }
                    
                    // The egg does have better main stat
                    if (tempImprovement > improvement)
                    {
                        improvement = tempImprovement;
                        improvementTotal = tempImprovementTotal;
                        slotMatch = k;
                    }
                    // The egg does have identical main stat, but better total stats
                    else if (tempImprovement == improvement && tempImprovementTotal > improvementTotal)
                    {
                        improvementTotal = tempImprovementTotal;
                        slotMatch = k;
                    }
                }
            }
        }
        
        // If we still have the egg left and we have a matching slot, and we still have the egg,
        // we swap the 2 spots and repeat the process for the egg we replaced to check if it does have a better match
        if (slotMatch != 0 && !stack.isEmpty())
        {
            stacks.set(eggIndex, stacks.get(slotMatch));
            stacks.set(slotMatch, stack);
            processEgg(eggIndex, isDirty, ++depth);
            isDirty[0] = true;
        }
        // If we no longer have the egg we ensure that the slot it was in is empty
        else if (stack.isEmpty())
        {
            stacks.set(eggIndex, ItemStack.EMPTY);
            isDirty[0] = true;
        }
        // In any other case, we just move the egg into output
        else
        {
            for (int k = 6; k < 9; k++)
            {
                ItemStack outputStack = stacks.get(k);
                if (outputStack.isEmpty())
                {
                    stacks.set(k, stack);
                    stacks.set(eggIndex, ItemStack.EMPTY);
                    isDirty[0] = true;
                    break;
                }
                
                NBTTagCompound inventoryNbt = outputStack.getTagCompound().getCompoundTag("storedEntity");
                final int outputGrowth = inventoryNbt.getInteger("Growth");
                final int outputGain = inventoryNbt.getInteger("Gain");
                final int outputStrength = inventoryNbt.getInteger("Strength");
                
                if (outputStack.isItemEqual(stack) && growth == outputGrowth && gain == outputGain && strength == outputStrength)
                {
                    int maxSize = 64 - outputStack.getCount();
                    
                    if (maxSize <= 0)
                        continue;
                    
                    ItemStack split = stack.splitStack(maxSize);
                    outputStack.setCount(outputStack.getCount() + split.getCount());
                    
                    if (stack.isEmpty())
                    {
                        stacks.set(eggIndex, ItemStack.EMPTY);
                        isDirty[0] = true;
                        break;
                    }
                }
            }
        }
        
        return false;
    }
}
