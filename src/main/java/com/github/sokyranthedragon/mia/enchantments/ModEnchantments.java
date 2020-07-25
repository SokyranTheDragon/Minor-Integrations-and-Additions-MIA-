package com.github.sokyranthedragon.mia.enchantments;

import com.github.sokyranthedragon.mia.Mia;
import com.github.sokyranthedragon.mia.integrations.thermalexpansion.ThermalExpansion;
import com.github.sokyranthedragon.mia.utilities.size.SizeUtils;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber(modid = Mia.MODID)
public class ModEnchantments
{
    public static final Enchantment SHRINKING;
    public static final Enchantment SIZE_STEAL;
    public static final Enchantment KOBOLD;
    
    static
    {
        if (SizeUtils.isSizeComponentEnabled)
        {
            SHRINKING = addEnchantment(new EnchantmentShrinking(), "shrinking");
            SIZE_STEAL = addEnchantment(new EnchantmentSizeSteal(), "size_steal");
            KOBOLD = addEnchantment(new EnchantmentKobold(), "kobold");
        }
        else
        {
            SHRINKING = null;
            SIZE_STEAL = null;
            KOBOLD = null;
        }
    }
    
    private static Enchantment addEnchantment(Enchantment enchantment, String name)
    {
        return enchantment.setRegistryName(name).setName(name);
    }
    
    @SubscribeEvent
    public static void registerEnchantments(RegistryEvent.Register<Enchantment> event)
    {
        if (SizeUtils.isSizeComponentEnabled)
        {
            IForgeRegistry<Enchantment> registry = event.getRegistry();
            
            registry.register(SHRINKING);
            registry.register(SIZE_STEAL);
            registry.register(KOBOLD);
            
            ThermalExpansion.addEnchantingRecipe(new ItemStack(Items.CHORUS_FRUIT), "mia:shrinking", 0);
            ThermalExpansion.addEnchantingRecipe(new ItemStack(Items.CHORUS_FRUIT_POPPED), "mia:size_steal", 3);
            ThermalExpansion.addEnchantingRecipe(new ItemStack(Blocks.CHORUS_FLOWER), "mia:kobold", 4);
        }
    }
}