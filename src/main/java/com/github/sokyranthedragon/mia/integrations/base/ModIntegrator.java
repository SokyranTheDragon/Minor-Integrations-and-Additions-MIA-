package com.github.sokyranthedragon.mia.integrations.base;

import com.gendeathrow.morechickens.core.ChickensMore;
import com.gendeathrow.morechickens.core.ModItems;
import com.github.sokyranthedragon.mia.Mia;
import com.github.sokyranthedragon.mia.config.ThaumcraftConfiguration;
import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.integrations.botania.Botania;
import com.github.sokyranthedragon.mia.integrations.cofhcore.CofhCore;
import com.github.sokyranthedragon.mia.integrations.dungeontactics.DungeonTactics;
import com.github.sokyranthedragon.mia.integrations.extrabotany.ExtraBotany;
import com.github.sokyranthedragon.mia.integrations.futuremc.FutureMc;
import com.github.sokyranthedragon.mia.integrations.harvestcraft.Harvestcraft;
import com.github.sokyranthedragon.mia.integrations.hatchery.Hatchery;
import com.github.sokyranthedragon.mia.integrations.iceandfire.IceAndFire;
import com.github.sokyranthedragon.mia.integrations.jei.Jei;
import com.github.sokyranthedragon.mia.integrations.jer.JustEnoughResources;
import com.github.sokyranthedragon.mia.integrations.mocreatures.MoCreatures;
import com.github.sokyranthedragon.mia.integrations.quark.Quark;
import com.github.sokyranthedragon.mia.integrations.tconstruct.TinkersConstruct;
import com.github.sokyranthedragon.mia.integrations.thaumcraft.Thaumcraft;
import com.github.sokyranthedragon.mia.integrations.theoneprobe.TheOneProbe;
import com.github.sokyranthedragon.mia.integrations.thermalexpansion.ThermalExpansion;
import com.github.sokyranthedragon.mia.integrations.thermalfoundation.ThermalFoundation;
import com.github.sokyranthedragon.mia.integrations.xu2.ExtraUtilities2;
import com.setycz.chickens.ChickensMod;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.fml.common.ProgressManager;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLLoadCompleteEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.aspects.AspectRegistryEvent;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class ModIntegrator
{
    private Map<ModIds, IBaseMod> modIntegrations = new HashMap<>();
    
    private boolean modsRegistered = false;
    private boolean modsPreInitialized = false;
    private boolean modsInitialized = false;
    private boolean modsPostInitialized = false;
    private boolean modsLoadCompleted = false;
    
    private boolean registeredBlocks = false;
    private boolean registeredItems = false;
    private boolean registeredRenders = false;
    private boolean registeredLootTableListeners = false;
    
    private boolean registeredAspects = false;
    
    
    public void registerMods()
    {
        if (modsRegistered)
        {
            Mia.LOGGER.warn("ModIntegrator.registerMods() was called more than once, this is not something that should happen.");
            return;
        }
        modsRegistered = true;
        
        if (ModIds.EXTRA_UTILITIES.isLoaded)
            modIntegrations.put(ModIds.EXTRA_UTILITIES, new ExtraUtilities2());
        if (ModIds.COFH_CORE.isLoaded)
            modIntegrations.put(ModIds.COFH_CORE, new CofhCore());
        if (ModIds.THERMAL_FOUNDATION.isLoaded)
            modIntegrations.put(ModIds.THERMAL_FOUNDATION, new ThermalFoundation());
        if (ModIds.THERMAL_EXPANSION.isLoaded)
            modIntegrations.put(ModIds.THERMAL_EXPANSION, new ThermalExpansion());
        if (ModIds.TINKERS_CONSTRUCT.isLoaded)
            modIntegrations.put(ModIds.TINKERS_CONSTRUCT, new TinkersConstruct());
        if (ModIds.JEI.isLoaded)
            modIntegrations.put(ModIds.JEI, new Jei());
        if (ModIds.JER.isLoaded)
            modIntegrations.put(ModIds.JER, new JustEnoughResources());
        if (ModIds.ICE_AND_FIRE.isLoaded)
            modIntegrations.put(ModIds.ICE_AND_FIRE, new IceAndFire());
        if (ModIds.HATCHERY.isLoaded)
            modIntegrations.put(ModIds.HATCHERY, new Hatchery());
        if (ModIds.THAUMCRAFT.isLoaded)
            modIntegrations.put(ModIds.THAUMCRAFT, new Thaumcraft());
        if (ModIds.THE_ONE_PROBE.isLoaded)
            modIntegrations.put(ModIds.THE_ONE_PROBE, new TheOneProbe());
        if (ModIds.MO_CREATURES.isLoaded)
            modIntegrations.put(ModIds.MO_CREATURES, new MoCreatures());
        if (ModIds.DUNGEON_TACTICS.isLoaded)
            modIntegrations.put(ModIds.DUNGEON_TACTICS, new DungeonTactics());
        if (ModIds.HARVESTCRAFT.isLoaded)
            modIntegrations.put(ModIds.HARVESTCRAFT, new Harvestcraft());
        if (ModIds.BOTANIA.isLoaded)
            modIntegrations.put(ModIds.BOTANIA, new Botania());
        if (ModIds.EXTRABOTANY.isLoaded)
            modIntegrations.put(ModIds.EXTRABOTANY, new ExtraBotany());
        if (ModIds.QUARK.isLoaded)
            modIntegrations.put(ModIds.QUARK, new Quark());
        if (ModIds.FUTURE_MC.isLoaded)
            modIntegrations.put(ModIds.FUTURE_MC, new FutureMc());
        
        if (!modIntegrations.isEmpty())
        {
            ProgressManager.ProgressBar progressBar = ProgressManager.push("Registering integrations", modIntegrations.size());
            for (Map.Entry<ModIds, IBaseMod> mod : modIntegrations.entrySet())
            {
                progressBar.step(mod.getKey().modId);
                mod.getValue().register(this::registerIntegration);
            }
            ProgressManager.pop(progressBar);
        }
    }
    
    @ParametersAreNonnullByDefault
    private void registerIntegration(ModIds mod, IModIntegration integration)
    {
        IBaseMod baseMod = modIntegrations.get(mod);
        
        if (baseMod != null)
            baseMod.addIntegration(integration);
    }
    
    public void preInit(FMLPreInitializationEvent event)
    {
        if (modsPreInitialized)
        {
            Mia.LOGGER.warn("ModIntegrator.preInit() was called more than once, this is not something that should happen.");
            return;
        }
        modsPreInitialized = true;
        
        for (IBaseMod mod : modIntegrations.values())
            mod.preInit(event);
    }
    
    public void init(FMLInitializationEvent event)
    {
        if (modsInitialized)
        {
            Mia.LOGGER.warn("ModIntegrator.init() was called more than once, this is not something that should happen.");
            return;
        }
        modsInitialized = true;
        
        for (IBaseMod mod : modIntegrations.values())
            mod.init(event);
    }
    
    public void postInit(FMLPostInitializationEvent event)
    {
        if (modsPostInitialized)
        {
            Mia.LOGGER.warn("ModIntegrator.postInit() was called more than once, this is not something that should happen.");
            return;
        }
        modsPostInitialized = true;
        
        for (IBaseMod mod : modIntegrations.values())
            mod.postInit(event);
    }
    
    public void loadCompleted(FMLLoadCompleteEvent event)
    {
        if (modsLoadCompleted)
        {
            Mia.LOGGER.warn("ModIntegrator.loadCompleted() was called more than once, this is not something that should happen.");
            return;
        }
        modsLoadCompleted = true;
        
        for (IBaseMod mod : modIntegrations.values())
            mod.loadCompleted(event);
    }
    
    public void registerBlocks(RegistryEvent.Register<Block> event)
    {
        if (registeredBlocks)
        {
            Mia.LOGGER.warn("ModIntegrator.registerBlocks() was called more than once, this is not something that should happen.");
            return;
        }
        registeredBlocks = true;
        
        for (IBaseMod mod : modIntegrations.values())
            mod.registerBlocks(event);
    }
    
    public void registerItems(RegistryEvent.Register<Item> event)
    {
        if (registeredItems)
        {
            Mia.LOGGER.warn("ModIntegrator.registerItems() was called more than once, this is not something that should happen.");
            return;
        }
        registeredItems = true;
        
        for (IBaseMod mod : modIntegrations.values())
            mod.registerItems(event);
    }
    
    @SideOnly(Side.CLIENT)
    public void registerRenders(ModelRegistryEvent event)
    {
        if (registeredRenders)
        {
            Mia.LOGGER.warn("ModIntegrator.registerRenders() was called more than once, this is not something that should happen.");
            return;
        }
        registeredRenders = true;
        
        for (IBaseMod mod : modIntegrations.values())
            mod.registerRenders(event);
    }
    
    public void registerLootTableListeners(HashSet<LootTableIntegrator.LootTableListener> integrations)
    {
        if (registeredLootTableListeners)
        {
            Mia.LOGGER.warn("ModIntegrator.registerLootTableListeners() was called more than once, this is not something that should happen.");
            return;
        }
        registeredLootTableListeners = true;
        
        for (IBaseMod mod : modIntegrations.values())
        {
            LootTableIntegrator.LootTableListener listener = mod.registerLootListener();
            if (listener != null)
                integrations.add(listener);
        }
    }
    
    @Optional.Method(modid = ModIds.ConstantIds.THAUMCRAFT)
    public void registerAspects(AspectRegistryEvent event)
    {
        if (registeredAspects)
        {
            Mia.LOGGER.warn("ModIntegrator.registerAspects() was called more than once, this is not something that should happen.");
            return;
        }
        if (!ThaumcraftConfiguration.registerAspects)
            return;
        registeredAspects = true;
        
        for (IBaseMod mod : modIntegrations.values())
            mod.registerAspects(event);
        
        event.register.registerObjectTag("bookshelf", new AspectList().add(Aspect.PLANT, 20).add(Aspect.MIND, 8));
        
        // If those mods are added as full integration then these should be moved there
        if (Loader.isModLoaded(ChickensMod.MODID))
        {
            event.register.registerObjectTag(new ItemStack(ChickensMod.liquidEgg, 1, 0), new AspectList().add(Aspect.WATER, 20));
            event.register.registerObjectTag(new ItemStack(ChickensMod.liquidEgg, 1, 1), new AspectList().add(Aspect.FIRE, 20));
        }
        if (Loader.isModLoaded(ChickensMore.MODID))
            event.register.registerObjectTag(new ItemStack(ModItems.solidXp), new AspectList().add(Aspect.MIND, 20));
    }
}
