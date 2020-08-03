package com.github.sokyranthedragon.mia.integrations.thaumcraft;

import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectEventProxy;
import thaumcraft.api.aspects.AspectHelper;
import thaumcraft.api.aspects.AspectList;

import java.util.Map;

public class ThaumcraftHelpers
{
    public static void transferAspects(ItemStack target, ItemStack origin, AspectEventProxy register)
    {
        transferAspects(target, origin, register, 1, true);
    }
    
    public static void transferAspects(ItemStack target, ItemStack origin, AspectEventProxy register, boolean loseFromCrafting)
    {
        transferAspects(target, origin, register, 1, loseFromCrafting);
    }
    
    public static void transferAspects(ItemStack target, ItemStack origin, AspectEventProxy register, float multiplier)
    {
        transferAspects(target, origin, register, multiplier, true);
    }
    
    public static void transferAspects(ItemStack target, ItemStack origin, AspectEventProxy register, float multiplier, boolean loseFromCrafting)
    {
        transferAspects(target, origin, register, multiplier, loseFromCrafting, null);
    }
    
    public static void transferAspects(ItemStack target, ItemStack origin, AspectEventProxy register, float multiplier, boolean loseFromCrafting, AspectList additionalAspects)
    {
        AspectList originAspects = AspectHelper.getObjectAspects(origin);
        if (originAspects == null || originAspects.aspects.isEmpty())
            return;
        
        AspectList targetAspects = AspectHelper.getObjectAspects(target);
        if (targetAspects == null)
            targetAspects = new AspectList();
        if (additionalAspects != null)
            targetAspects.merge(additionalAspects);
        
        if (loseFromCrafting)
            multiplier *= 0.75f;
        
        for (Map.Entry<Aspect, Integer> aspects : originAspects.aspects.entrySet())
        {
            int value = (int)(aspects.getValue() * multiplier);
            
            if (value > 0)
                targetAspects.merge(aspects.getKey(), Math.max(value, 500));
        }
        
        register.registerObjectTag(target, targetAspects);
    }
    
    public static void addAspect(NonNullList<ItemStack> stacks, AspectEventProxy register, Aspect aspect)
    {
        addAspect(stacks, register, aspect, 1);
    }
    
    public static void addAspect(NonNullList<ItemStack> stacks, AspectEventProxy register, Aspect aspect, int amount)
    {
        for (ItemStack stack : stacks)
        {
            AspectList aspects = AspectHelper.getObjectAspects(stack);
            
            if (aspects == null)
                register.registerObjectTag(stack, new AspectList().add(aspect, amount));
            else
                register.registerObjectTag(stack, aspects.merge(aspect, amount));
        }
    }
}
