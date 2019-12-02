package com.github.exploder1531.mia.proxy;

import com.github.exploder1531.mia.Mia;
import com.github.exploder1531.mia.capabilities.MusicPlayerCapabilityProvider;
import com.github.exploder1531.mia.core.MiaItems;
import com.github.exploder1531.mia.integrations.ModLoadStatus;
import com.github.exploder1531.mia.integrations.base.ModIntegrator;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLLoadCompleteEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@SuppressWarnings("WeakerAccess")
@Mod.EventBusSubscriber(modid = Mia.MODID)
public class CommonProxy
{
    protected static ModIntegrator modIntegrator;
    
    public void preInit(FMLPreInitializationEvent event)
    {
        ModLoadStatus.preInit();
        MusicPlayerCapabilityProvider.register();
        modIntegrator = new ModIntegrator();
        modIntegrator.registerMods();
        modIntegrator.preInit(event);
    }
    
    public void init(FMLInitializationEvent event)
    {
        modIntegrator.init(event);
    }
    
    public void postInit(FMLPostInitializationEvent event)
    {
        modIntegrator.postInit(event);
    }
    
    public void loadCompleted(FMLLoadCompleteEvent event)
    {
        modIntegrator.loadCompleted(event);
    }
    
    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event)
    {
        modIntegrator.registerBlocks(event);
    }
    
    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event)
    {
        MiaItems.registerItems(event);
        modIntegrator.registerItems(event);
    }
    
    @SubscribeEvent
    public static void lootTableLoad(LootTableLoadEvent event)
    {
        modIntegrator.lootTableLoad(event);
    }

//    @SubscribeEvent
//    public static void aspectRegistrationEvent(AspectRegistryEvent event)
//    {
//    }
}
