package com.github.exploder1531.mia.integrations.base;

import com.github.exploder1531.mia.integrations.ModIds;
import com.github.exploder1531.mia.integrations.base.LootTableIntegrator.LootTableListener;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLLoadCompleteEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import thaumcraft.api.aspects.AspectRegistryEvent;

import javax.annotation.Nullable;
import java.util.function.BiConsumer;

public interface IBaseMod
{
    default void register(BiConsumer<ModIds, IModIntegration> modIntegration)
    {
    }
    
    default void addIntegration(IModIntegration integration)
    {
    }
    
    default void preInit(FMLPreInitializationEvent event)
    {
    }
    
    default void init(FMLInitializationEvent event)
    {
    }
    
    default void postInit(FMLPostInitializationEvent event)
    {
    }
    
    default void loadCompleted(FMLLoadCompleteEvent event)
    {
    }
    
    default void registerBlocks(RegistryEvent.Register<Block> event)
    {
    }
    
    default void registerItems(RegistryEvent.Register<Item> event)
    {
    }
    
    @SideOnly(Side.CLIENT)
    default void registerRenders(ModelRegistryEvent event)
    {
    }
    
    @Nullable
    default LootTableListener registerLootListener()
    {
        return null;
    }
    
    @Optional.Method(modid = ModIds.ConstantIds.THAUMCRAFT)
    default void registerAspects(AspectRegistryEvent event)
    {
    }
}
