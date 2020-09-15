package com.github.sokyranthedragon.mia.proxy;

import com.gildedgames.the_aether.api.freezables.AetherFreezableFuel;
import com.github.sokyranthedragon.mia.Mia;
import com.github.sokyranthedragon.mia.block.decorative.BlockPackedPaper;
import com.github.sokyranthedragon.mia.capabilities.MusicPlayerCapabilityProvider;
import com.github.sokyranthedragon.mia.config.GenericAdditionsConfig;
import com.github.sokyranthedragon.mia.config.MiaConfig;
import com.github.sokyranthedragon.mia.core.MiaBlocks;
import com.github.sokyranthedragon.mia.core.MiaItems;
import com.github.sokyranthedragon.mia.events.Size_BaseEvents;
import com.github.sokyranthedragon.mia.events.Size_ExtendedEvents;
import com.github.sokyranthedragon.mia.events.Size_ItemEvents;
import com.github.sokyranthedragon.mia.events.WorldEvents;
import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.integrations.base.LootTableIntegrator;
import com.github.sokyranthedragon.mia.integrations.base.ModIntegrator;
import com.github.sokyranthedragon.mia.integrations.chisel.Chisel;
import com.github.sokyranthedragon.mia.integrations.harvestcraft.CraftTweakerHarvestcraftIntegration;
import com.github.sokyranthedragon.mia.network.MessageExtendedReachAttack;
import com.github.sokyranthedragon.mia.network.MessageSyncMusicPlayer;
import com.github.sokyranthedragon.mia.utilities.size.SizeOreDictionaryUtils;
import com.github.sokyranthedragon.mia.utilities.size.SizeUtils;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.event.RegistryEvent;
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
public class CommonProxy
{
    protected static ModIntegrator modIntegrator;
    protected static LootTableIntegrator lootTableIntegrator;
    
    public void preInit(FMLPreInitializationEvent event)
    {
        MinecraftForge.EVENT_BUS.register(this);
        
        SizeUtils.isSizeComponentEnabled = GenericAdditionsConfig.enableSizeComponent && ModIds.ARTEMISLIB.isLoaded;
        if (SizeUtils.isSizeComponentEnabled)
        {
            SizeOreDictionaryUtils.setupOreDictUtils();
            MinecraftForge.EVENT_BUS.register(Size_BaseEvents.class);
            MinecraftForge.EVENT_BUS.register(Size_ExtendedEvents.class);
            MinecraftForge.EVENT_BUS.register(Size_ItemEvents.class);
        }
        if (GenericAdditionsConfig.enableSizeComponent && !ModIds.ARTEMISLIB.isLoaded)
            Mia.LOGGER.warn("Size component is enabled, but ArtemisLib is not installed! It won't work!");
        
        if (GenericAdditionsConfig.enableEvtp && GenericAdditionsConfig.evtp.deadFlowerEnabled)
            MinecraftForge.TERRAIN_GEN_BUS.register(WorldEvents.class);
        
        MusicPlayerCapabilityProvider.register();
        
        modIntegrator = new ModIntegrator();
        modIntegrator.registerMods();
        
        lootTableIntegrator = new LootTableIntegrator();
        lootTableIntegrator.registerLootTableIntegration(modIntegrator, event.getSide());
        
        modIntegrator.preInit(event);
    }
    
    public void init(FMLInitializationEvent event)
    {
        Mia.network = NetworkRegistry.INSTANCE.newSimpleChannel(Mia.MODID + "_NETWORK");
        Mia.network.registerMessage(MessageSyncMusicPlayer.Handler.class, MessageSyncMusicPlayer.class, 0, Side.SERVER);
        Mia.network.registerMessage(MessageExtendedReachAttack.Handler.class, MessageExtendedReachAttack.class, 1, Side.SERVER);
        
        if (!MiaConfig.disableOreDict)
        {
            OreDictionary.registerOre("buttonWood", Blocks.WOODEN_BUTTON);
            OreDictionary.registerOre("trapdoorWood", Blocks.TRAPDOOR);
            OreDictionary.registerOre("listAllsugar", Items.SUGAR);
            OreDictionary.registerOre("listAllmilk", Items.MILK_BUCKET);
            // OreDictionary.registerOre("listAllmushroom", Blocks.BROWN_MUSHROOM);
            // OreDictionary.registerOre("listAllmushroom", Blocks.RED_MUSHROOM);
        }
        
        if (GenericAdditionsConfig.enableEvtp)
        {
            if (GenericAdditionsConfig.evtp.deadFlowerEnabled)
            {
                assert MiaBlocks.flowerDead != null;
                OreDictionary.registerOre("dyeBlack", MiaBlocks.flowerDead);
            }
            
            if (GenericAdditionsConfig.evtp.packedPaperEnabled)
            {
                assert MiaBlocks.packedPaper != null;
                OreDictionary.registerOre("blockPaper", new ItemStack(MiaBlocks.packedPaper, 1, OreDictionary.WILDCARD_VALUE));
                if (ModIds.CHISEL.isLoaded)
                {
                    for (int meta = 0; meta <= ((BlockPackedPaper) MiaBlocks.packedPaper).getMaxMeta(); meta++)
                        Chisel.sendChiselMessage("block_paper", MiaBlocks.packedPaper, meta);
                }
            }
        }
        
        modIntegrator.registerDispenserBehaviors(event.getSide());
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
    public void registerBlocks(RegistryEvent.Register<Block> event)
    {
        MiaBlocks.initMiaBlocks();
        modIntegrator.registerBlocks(event, getSide());
        MiaBlocks.registerMiaBlocks(event);
    }
    
    @SubscribeEvent
    public void registerItems(RegistryEvent.Register<Item> event)
    {
        MiaItems.registerMiaItems(event);
        MiaBlocks.registerMiaItemblocks(event);
        modIntegrator.registerItems(event, getSide());
    }
    
    @SubscribeEvent
    public void lootTableLoad(LootTableLoadEvent event)
    {
        lootTableIntegrator.lootTableLoad(event);
    }
    
    @SubscribeEvent
    @Optional.Method(modid = ModIds.ConstantIds.THAUMCRAFT)
    public void aspectRegistrationEvent(AspectRegistryEvent event)
    {
        modIntegrator.registerAspects(event, getSide());
    }
    
    @SubscribeEvent
    @Optional.Method(modid = ModIds.ConstantIds.AETHER)
    public void registerFreezableFuel(RegistryEvent.Register<AetherFreezableFuel> event)
    {
        modIntegrator.registerFreezableFuel(event, getSide());
    }
    
    protected Side getSide()
    {
        return Side.SERVER;
    }
}
