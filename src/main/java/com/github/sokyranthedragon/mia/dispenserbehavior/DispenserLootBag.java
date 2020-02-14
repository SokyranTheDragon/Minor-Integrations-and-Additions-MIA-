package com.github.sokyranthedragon.mia.dispenserbehavior;

import com.github.sokyranthedragon.mia.Mia;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.BlockDispenser;
import net.minecraft.dispenser.IBehaviorDispenseItem;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.ArrayList;
import java.util.Collection;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class DispenserLootBag implements IBehaviorDispenseItem
{
    @Nullable
    protected static DispenserLootBag INSTANCE = null;
    protected Collection<IDispenserListener> listeners = new ArrayList<>();
    
    public static DispenserLootBag getInstance()
    {
        if (INSTANCE == null)
            INSTANCE = new DispenserLootBag();
        return INSTANCE;
    }
    
    public void addListener(IDispenserListener listener, Item... items)
    {
        assert INSTANCE != null;
        if (listeners.contains(listener))
            Mia.LOGGER.warn("Tried to register the same listener dispenser loot bag listener twice, this shouldn't have happened!");
        else
        {
            listeners.add(listener);
            for (Item item : items)
            {
                assert item != null;
                BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(item, INSTANCE);
            }
        }
    }
    
    public void addListener(IDispenserListener listener, ItemStack... items)
    {
        assert INSTANCE != null;
        if (listeners.contains(listener))
            Mia.LOGGER.warn("Tried to register the same listener dispenser loot bag listener twice, this shouldn't have happened!");
        else
        {
            listeners.add(listener);
            for (ItemStack item : items)
            {
                assert item != null;
                if (!item.isEmpty())
                    BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(item.getItem(), INSTANCE);
            }
        }
    }
    
    @Override
    public ItemStack dispense(IBlockSource source, ItemStack stack)
    {
        for (IDispenserListener listener : listeners)
            if (listener.dispense(source, stack))
                break;
        
        return stack;
    }
    
    @FunctionalInterface
    public interface IDispenserListener
    {
        boolean dispense(IBlockSource source, ItemStack stack);
    }
}
