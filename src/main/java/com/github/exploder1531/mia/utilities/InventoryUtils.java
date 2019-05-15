package com.github.exploder1531.mia.utilities;

import net.minecraft.entity.Entity;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class InventoryUtils
{
    public static void dropInventoryItems(World worldIn, BlockPos pos, IInventory inventory)
    {
        dropInventoryItems(worldIn, (double)pos.getX(), (double)pos.getY(), (double)pos.getZ(), inventory);
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
}
