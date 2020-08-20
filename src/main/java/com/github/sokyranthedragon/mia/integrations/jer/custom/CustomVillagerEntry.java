package com.github.sokyranthedragon.mia.integrations.jer.custom;

import jeresources.entry.VillagerEntry;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityVillager;

import javax.annotation.Nonnull;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public abstract class CustomVillagerEntry extends VillagerEntry
{
    public CustomVillagerEntry(String name, List<List<EntityVillager.ITradeList>> tradesLists)
    {
        super(name, 0, 0, tradesLists);
    }
    
    public CustomVillagerEntry(String name, int profession, int career, List<List<EntityVillager.ITradeList>> tradesLists)
    {
        super(name, profession, career, tradesLists);
    }
    
    public abstract EntityLivingBase getEntity(@Nonnull Minecraft minecraft) throws IllegalAccessException, InvocationTargetException, InstantiationException;
    
    public float getRenderScale()
    {
        return 36f;
    }
}
