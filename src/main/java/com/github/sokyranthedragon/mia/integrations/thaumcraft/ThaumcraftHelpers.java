package com.github.sokyranthedragon.mia.integrations.thaumcraft;

import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectEventProxy;
import thaumcraft.api.aspects.AspectHelper;
import thaumcraft.api.aspects.AspectList;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Map;

@ParametersAreNonnullByDefault
public class ThaumcraftHelpers
{
    public static void addAspects(ItemStack target, @Nullable AspectList origin, AspectEventProxy register)
    {
        transferAspects(target, origin, register, 1, false);
    }
    
    public static void transferAspects(ItemStack target, @Nullable AspectList origin, AspectEventProxy register)
    {
        transferAspects(target, origin, register, 1, true);
    }
    
    public static void transferAspects(ItemStack target, @Nullable AspectList origin, AspectEventProxy register, boolean lossFromCrafting)
    {
        transferAspects(target, origin, register, 1, lossFromCrafting);
    }
    
    public static void transferAspects(ItemStack target, @Nullable AspectList origin, AspectEventProxy register, boolean lossFromCrafting, @Nullable AspectList additionalAspects)
    {
        transferAspects(target, origin, register, 1, lossFromCrafting, additionalAspects);
    }
    
    public static void transferAspects(ItemStack target, @Nullable AspectList origin, AspectEventProxy register, float multiplier)
    {
        transferAspects(target, origin, register, multiplier, true);
    }
    
    public static void transferAspects(ItemStack target, @Nullable AspectList origin, AspectEventProxy register, float multiplier, @Nullable AspectList additionalAspects)
    {
        transferAspects(target, origin, register, multiplier, true, additionalAspects);
    }
    
    public static void transferAspects(ItemStack target, @Nullable AspectList origin, AspectEventProxy register, @Nullable AspectList additionalAspects)
    {
        transferAspects(target, origin, register, 1, true, additionalAspects);
    }
    
    public static void transferAspects(ItemStack target, @Nullable AspectList origin, AspectEventProxy register, float multiplier, boolean lossFromCrafting)
    {
        transferAspects(target, origin, register, multiplier, lossFromCrafting, null);
    }
    
    public static void transferAspects(ItemStack target, ItemStack origin, AspectEventProxy register)
    {
        transferAspects(target, origin, register, 1, true);
    }
    
    public static void transferAspects(ItemStack target, ItemStack origin, AspectEventProxy register, boolean lossFromCrafting)
    {
        transferAspects(target, origin, register, 1, lossFromCrafting);
    }
    
    public static void transferAspects(ItemStack target, ItemStack origin, AspectEventProxy register, boolean lossFromCrafting, @Nullable AspectList additionalAspects)
    {
        transferAspects(target, origin, register, 1, lossFromCrafting, additionalAspects);
    }
    
    public static void transferAspects(ItemStack target, ItemStack origin, AspectEventProxy register, float multiplier)
    {
        transferAspects(target, origin, register, multiplier, true);
    }
    
    public static void transferAspects(ItemStack target, ItemStack origin, AspectEventProxy register, float multiplier, @Nullable AspectList additionalAspects)
    {
        transferAspects(target, origin, register, multiplier, true, additionalAspects);
    }
    
    public static void transferAspects(ItemStack target, ItemStack origin, AspectEventProxy register, @Nullable AspectList additionalAspects)
    {
        transferAspects(target, origin, register, 1, true, additionalAspects);
    }
    
    public static void transferAspects(ItemStack target, ItemStack origin, AspectEventProxy register, float multiplier, boolean lossFromCrafting)
    {
        transferAspects(target, origin, register, multiplier, lossFromCrafting, null);
    }
    
    public static void transferAspects(ItemStack target, ItemStack origin, AspectEventProxy register, float multiplier, boolean lossFromCrafting, @Nullable AspectList additionalAspects)
    {
        transferAspects(target, AspectHelper.getObjectAspects(origin), register, multiplier, lossFromCrafting, additionalAspects);
    }
    
    public static void transferAspects(ItemStack target, @Nullable AspectList origin, AspectEventProxy register, float multiplier, boolean lossFromCrafting, @Nullable AspectList additionalAspects)
    {
        AspectList targetAspects = AspectHelper.getObjectAspects(target);
        if (targetAspects == null)
            targetAspects = new AspectList();
        if (additionalAspects != null && additionalAspects.size() > 0)
            targetAspects.merge(additionalAspects);
        
        if (origin == null || origin.size() <= 0 || multiplier == 0)
        {
            if (additionalAspects != null && additionalAspects.size() > 0)
                register.registerObjectTag(target, targetAspects);
            return;
        }
    
        if (lossFromCrafting)
            multiplier *= 0.75f;
        
        if (multiplier == 1)
            targetAspects.merge(origin);
        else
        {
            for (Map.Entry<Aspect, Integer> aspects : origin.aspects.entrySet())
            {
                int value = (int)(aspects.getValue() * multiplier);
        
                if (value > 0)
                    targetAspects.merge(aspects.getKey(), Math.min(value, 500));
            }
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
