package com.github.sokyranthedragon.mia.utilities;

import baubles.api.BaublesApi;
import baubles.api.cap.IBaublesItemHandler;
import com.gildedgames.the_aether.api.AetherAPI;
import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.meteor.extrabotany.common.item.ModItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import vazkii.botania.common.item.ItemBaubleBox;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class BaublesUtils
{
    private BaublesUtils()
    {
    }
    
    public static boolean isBaubleActiveAFO(EntityPlayer player, @Nullable Item item)
    {
        if (item == null)
            return false;
        if (ModIds.BAUBLES.isLoaded)
        {
            if (ModIds.EXTRABOTANY.isLoaded && ModIds.BOTANIA.isLoaded)
            {
                if (baublesCheck(player, item))
                    return true;
            }
            else if (BaublesApi.isBaubleEquipped(player, item) >= 0)
                return true;
        }
        return ModIds.AETHER.isLoaded && !getAetherStack(player, item).isEmpty();
    }
    
    public static boolean baublesCheck(EntityPlayer player, Item item)
    {
        IBaublesItemHandler handler = BaublesApi.getBaublesHandler(player);
        int maxSize = 0;
        
        for (int slot = 0; slot < handler.getSlots(); slot++)
        {
            if (!handler.getStackInSlot(slot).isEmpty())
            {
                Item temp = handler.getStackInSlot(slot).getItem();
                if (temp == item)
                    return true;
                else if (temp == ModItems.allforone)
                    maxSize = Integer.MAX_VALUE;
                else if (temp == ModItems.elvenking)
                    maxSize = Math.max(3, maxSize);
            }
        }
        
        if (maxSize == 0)
            return false;
        
        for (int i = 0; i < player.inventory.getSizeInventory(); i++)
        {
            ItemStack box = player.inventory.getStackInSlot(i);
            if (box.getItem() instanceof ItemBaubleBox && player.inventory.hasItemStack(box))
            {
                IItemHandler newInv = box.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
                if (newInv == null)
                    return false;
                
                maxSize = Math.min(maxSize, newInv.getSlots());
                
                for (int slot = 0; slot < maxSize; slot++)
                {
                    if (newInv.getStackInSlot(slot).getItem() == item)
                        return true;
                }
                break;
            }
        }
        
        return false;
    }
    
    public static ItemStack getAetherStack(EntityPlayer player, Item item)
    {
        for (ItemStack accessory : AetherAPI.getInstance().get(player).getAccessoryInventory().getAccessories())
        {
            if (accessory.getItem() == item)
                return accessory;
        }
        
        return ItemStack.EMPTY;
    }
}