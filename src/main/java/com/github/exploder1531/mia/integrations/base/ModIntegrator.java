package com.github.exploder1531.mia.integrations.base;

import com.github.exploder1531.mia.integrations.ModIds;
import com.github.exploder1531.mia.integrations.hatchery.Hatchery;
import com.github.exploder1531.mia.integrations.iceandfire.IceAndFire;
import com.github.exploder1531.mia.integrations.jer.JustEnoughResources;
import com.github.exploder1531.mia.integrations.tconstruct.TinkersConstruct;
import com.github.exploder1531.mia.integrations.thaumcraft.Thaumcraft;
import com.github.exploder1531.mia.integrations.theoneprobe.TheOneProbe;
import com.github.exploder1531.mia.integrations.thermalexpansion.ThermalExpansion;
import com.github.exploder1531.mia.integrations.thermalfoundation.ThermalFoundation;
import com.github.exploder1531.mia.integrations.xu2.ExtraUtilities2;
import com.google.common.collect.Maps;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLLoadCompleteEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import thaumcraft.api.aspects.AspectRegistryEvent;

import java.util.Map;

public class ModIntegrator
{
    private Map<String, IBaseMod> modIntegrations = Maps.newHashMap();
    
    private boolean modsRegistered = false;
    private boolean modsPreInitialized = false;
    private boolean modsInitialized = false;
    private boolean modsPostInitialized = false;
    private boolean modsLoadCompleted = false;
    
    private boolean registeredBlocks = false;
    private boolean registeredItems = false;


    
    // TODO: add logs for incorrect status
    
    public void registerMods()
    {
        if (modsRegistered)
            return;
        modsRegistered = true;
        
        if (Loader.isModLoaded(ModIds.EXTRA_UTILITIES))
            modIntegrations.put(ModIds.EXTRA_UTILITIES, new ExtraUtilities2());
        if (Loader.isModLoaded(ModIds.THERMAL_FOUNDATION))
            modIntegrations.put(ModIds.THERMAL_FOUNDATION, new ThermalFoundation());
        if (Loader.isModLoaded(ModIds.THERMAL_EXPANSION))
            modIntegrations.put(ModIds.THERMAL_EXPANSION, new ThermalExpansion());
        if (Loader.isModLoaded(ModIds.TINKERS_CONSTRUCT))
            modIntegrations.put(ModIds.TINKERS_CONSTRUCT, new TinkersConstruct());
        if (Loader.isModLoaded(ModIds.JER))
            modIntegrations.put(ModIds.JER, new JustEnoughResources());
        if (Loader.isModLoaded(ModIds.ICE_AND_FIRE))
            modIntegrations.put(ModIds.ICE_AND_FIRE, new IceAndFire());
        if (Loader.isModLoaded(ModIds.HATCHERY))
            modIntegrations.put(ModIds.HATCHERY, new Hatchery());
        if (Loader.isModLoaded(ModIds.THAUMCRAFT))
            modIntegrations.put(ModIds.THAUMCRAFT, new Thaumcraft());
        if (Loader.isModLoaded(ModIds.THE_ONE_PROBE))
            modIntegrations.put(ModIds.THE_ONE_PROBE, new TheOneProbe());
        
        for (IBaseMod mod : modIntegrations.values())
            mod.register(this::registerIntegration);
    }
    
    private void registerIntegration(String mod, IModIntegration integration)
    {
        IBaseMod baseMod = modIntegrations.get(mod);
        
        if (baseMod != null)
            baseMod.addIntegration(integration);
    }
    
    public void preInit(FMLPreInitializationEvent event)
    {
        if (modsPreInitialized)
            return;
        modsPreInitialized = true;
        
        for (IBaseMod mod : modIntegrations.values())
            mod.preInit(event);
    }
    
    public void init(FMLInitializationEvent event)
    {
        if (modsInitialized)
            return;
        modsInitialized = true;
        
        for (IBaseMod mod : modIntegrations.values())
            mod.init(event);
    }
    
    public void postInit(FMLPostInitializationEvent event)
    {
        if (modsPostInitialized)
            return;
        modsPostInitialized = true;
        
        for (IBaseMod mod : modIntegrations.values())
            mod.postInit(event);
    }
    
    public void loadCompleted(FMLLoadCompleteEvent event)
    {
        if (modsLoadCompleted)
            return;
        modsLoadCompleted = true;
        
        for (IBaseMod mod : modIntegrations.values())
            mod.loadCompleted(event);
    }
    
    public void registerBlocks(RegistryEvent.Register<Block> event)
    {
        if (registeredBlocks)
            return;
        registeredBlocks = true;
        
        for (IBaseMod mod : modIntegrations.values())
            mod.registerBlocks(event);
    }
    
    public void registerItems(RegistryEvent.Register<Item> event)
    {
        if (registeredItems)
            return;
        registeredItems = true;
        
        for (IBaseMod mod : modIntegrations.values())
            mod.registerItems(event);
    }
    
    @SideOnly(Side.CLIENT)
    public void registerRenders(ModelRegistryEvent event)
    {
        for (IBaseMod mod : modIntegrations.values())
            mod.registerRenders(event);
    }
}
