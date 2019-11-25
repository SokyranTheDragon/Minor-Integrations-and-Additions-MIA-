package com.github.exploder1531.mia.integrations.jer;

import jeresources.entry.MobEntry;

import java.util.LinkedHashSet;

class CustomLinkedHashSet<T> extends LinkedHashSet<T>
{
    JustEnoughResources jer;
    
    @Override
    public boolean add(T entry)
    {
        boolean add = super.add(entry);
        
        if (add && entry instanceof MobEntry)
            jer.overrideMobDrop((MobEntry)entry);
        
        return add;
    }
}
