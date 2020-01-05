package com.github.exploder1531.mia.proxy;

import com.github.exploder1531.mia.Mia;
import com.github.exploder1531.mia.capabilities.MusicPlayerCapabilityProvider;
import com.github.exploder1531.mia.core.MiaItems;
import com.github.exploder1531.mia.integrations.ModIds;
import com.github.exploder1531.mia.integrations.base.LootTableIntegrator;
import com.github.exploder1531.mia.integrations.base.ModIntegrator;
import com.github.exploder1531.mia.integrations.harvestcraft.CraftTweakerHarvestcraftIntegration;
import com.github.exploder1531.mia.network.MessageSyncMusicPlayer;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLLoadCompleteEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.oredict.OreDictionary;
import thaumcraft.api.aspects.AspectRegistryEvent;

@SuppressWarnings("WeakerAccess")
@Mod.EventBusSubscriber(modid = Mia.MODID)
public class CommonProxy
{
    protected static ModIntegrator modIntegrator;
    protected static LootTableIntegrator lootTableIntegrator;
    
    public void preInit(FMLPreInitializationEvent event)
    {
        MusicPlayerCapabilityProvider.register();
        
        modIntegrator = new ModIntegrator();
        modIntegrator.registerMods();
        
        lootTableIntegrator = new LootTableIntegrator();
        lootTableIntegrator.registerLootTableIntegration(modIntegrator);
        
        modIntegrator.preInit(event);
    }
    
    public void init(FMLInitializationEvent event)
    {
        Mia.network = NetworkRegistry.INSTANCE.newSimpleChannel(Mia.MODID + "_NETWORK");
        Mia.network.registerMessage(MessageSyncMusicPlayer.Handler.class, MessageSyncMusicPlayer.class, 0, Side.SERVER);
        
        OreDictionary.registerOre("buttonWood", Blocks.WOODEN_BUTTON);
        OreDictionary.registerOre("trapdoorWood", Blocks.TRAPDOOR);
        
        modIntegrator.init(event);
        
        if (ModIds.HARVESTCRAFT.isLoaded && ModIds.CRAFT_TWEAKER.isLoaded)
            CraftTweakerHarvestcraftIntegration.applyRemovals();
    }
    
    public void postInit(FMLPostInitializationEvent event)
    {
        modIntegrator.postInit(event);
    }
    
    public void loadCompleted(FMLLoadCompleteEvent event)
    {
        modIntegrator.loadCompleted(event);
        // It will no longer used after this point, as loadCompleted is the last time it's called.
        // It should clear the only reference to modIntegrator, which I hope would let GC clear all the stuff in there.
        modIntegrator = null;
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
        lootTableIntegrator.lootTableLoad(event);
    }
    
    @SubscribeEvent
    @Optional.Method(modid = ModIds.ConstantIds.THAUMCRAFT)
    public static void aspectRegistrationEvent(AspectRegistryEvent event)
    {
        modIntegrator.registerAspects(event);
    }
}
