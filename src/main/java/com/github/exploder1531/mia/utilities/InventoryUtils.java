package com.github.exploder1531.mia.utilities;

import baubles.api.BaublesApi;
import baubles.api.cap.IBaublesItemHandler;
import com.github.exploder1531.mia.integrations.ModLoadStatus;
import com.google.common.collect.ImmutableSet;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.apache.commons.lang3.tuple.ImmutableTriple;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class InventoryUtils
{
    public static void dropInventoryItems(World worldIn, BlockPos pos, IInventory inventory)
    {
        dropInventoryItems(worldIn, pos.getX(), pos.getY(), pos.getZ(), inventory);
    }
    
    public static void dropInventoryItems(World worldIn, Entity entityAt, IInventory inventory)
    {
        dropInventoryItems(worldIn, entityAt.posX, entityAt.posY, entityAt.posZ, inventory);
    }
    
    // Special implementation of InventoryHelper.dropInventoryItems that takes max stack size into consideration
    private static void dropInventoryItems(World worldIn, double x, double y, double z, IInventory inventory)
    {
        for (int i = 0; i < inventory.getSizeInventory(); ++i)
        {
            ItemStack itemstack = inventory.getStackInSlot(i);
            
            while (!itemstack.isEmpty())
            {
                ItemStack dropStack = itemstack.splitStack(itemstack.getMaxStackSize());
                
                if (!dropStack.isEmpty())
                {
                    InventoryHelper.spawnItemStack(worldIn, x, y, z, itemstack);
                }
            }
        }
    }
    
    public static ImmutableTriple<ItemStack, Integer, Integer> findItemInInventory(EntityPlayer player, Item targetItem)
    {
        ItemStack item = ItemStack.EMPTY;
        int type = 0;
        int slot = -1;
        
        if (ModLoadStatus.baublesLoaded)
        {
            slot = BaublesApi.isBaubleEquipped(player, targetItem);
            
            if (slot >= 0)
            {
                item = BaublesApi.getBaublesHandler(player).getStackInSlot(slot);
                type = 3;
            }
        }
        
        if (item.isEmpty())
        {
            if (player.getHeldItemMainhand().getItem() == targetItem)
            {
                item = player.getHeldItemMainhand();
                type = 0;
            }
            else if (player.getHeldItemOffhand().getItem() == targetItem)
            {
                item = player.getHeldItemOffhand();
                type = 1;
            }
            else
            {
                NonNullList<ItemStack> inventory = player.inventory.mainInventory;
                
                for (int i = 0, inventorySize = inventory.size(); i < inventorySize; i++)
                {
                    ItemStack inventoryItem = inventory.get(i);
                    if (inventoryItem.getItem() == targetItem)
                    {
                        item = inventoryItem;
                        type = 2;
                        slot = i;
                        break;
                    }
                }
            }
        }
        
        return new ImmutableTriple<>(item, type, slot);
    }
    
    public static ImmutableSet<ItemStack> getAllItemsOfType(EntityPlayer player, Item targetItem)
    {
        ImmutableSet.Builder<ItemStack> set = ImmutableSet.builder();
        
        if (ModLoadStatus.baublesLoaded)
        {
            IBaublesItemHandler baubles = BaublesApi.getBaublesHandler(player);
            
            for (int i = 0; i < baubles.getSlots(); i++)
            {
                ItemStack stack = baubles.getStackInSlot(i);
                if (stack.getItem() == targetItem)
                    set.add(stack);
            }
        }
        
        for (ItemStack stack : player.inventory.mainInventory)
        {
            if (stack.getItem() == targetItem)
                set.add(stack);
        }
        
        if (player.getHeldItemOffhand().getItem() == targetItem)
            set.add(player.getHeldItemOffhand());
        
        return set.build();
    }
    
    public static ItemStack getItemFromPlayer(EntityPlayer player, int type, int slot)
    {
        ItemStack itemStack = ItemStack.EMPTY;
        
        switch (type)
        {
            // Main hand (for convenience)
            case 0:
                itemStack = player.getHeldItemMainhand();
                break;
            // Offhand, can't be accessed from normal inventory (I think)
            case 1:
                itemStack = player.getHeldItemOffhand();
                break;
            // Normal inventory
            case 2:
                itemStack = player.inventory.getStackInSlot(slot);
                break;
            // Baubles inventory
            case 3:
                if (ModLoadStatus.baublesLoaded)
                    itemStack = BaublesApi.getBaublesHandler(player).getStackInSlot(slot);
                break;
        }
        
        return itemStack;
    }
}
