package com.github.sokyranthedragon.mia.integrations.jer;

import jeresources.entry.VillagerEntry;

import java.util.LinkedList;

public class CustomLinkedList<T> extends LinkedList<T>
{
    JustEnoughResources jer;
    private boolean skippedFirstClear = false;
    
    @Override
    public boolean add(T entry)
    {
        boolean add = super.add(entry);
        
        if (add && entry instanceof VillagerEntry)
            jer.overrideVillagerTrades((VillagerEntry) entry);
        
        return add;
    }
    
    @Override
    public void clear()
    {
        if (skippedFirstClear)
            super.clear();
        else
            skippedFirstClear = true;
    }
}
