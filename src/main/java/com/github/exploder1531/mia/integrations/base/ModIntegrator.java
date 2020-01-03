package com.github.exploder1531.mia.integrations.base;

import com.gendeathrow.morechickens.core.ChickensMore;
import com.gendeathrow.morechickens.core.ModItems;
import com.github.exploder1531.mia.Mia;
import com.github.exploder1531.mia.integrations.ModIds;
import com.github.exploder1531.mia.integrations.base.LootTableIntegrator.LootTableListener;
import com.github.exploder1531.mia.integrations.botania.Botania;
import com.github.exploder1531.mia.integrations.cofhcore.CofhCore;
import com.github.exploder1531.mia.integrations.dungeontactics.DungeonTactics;
import com.github.exploder1531.mia.integrations.extrabotany.ExtraBotany;
import com.github.exploder1531.mia.integrations.futuremc.FutureMc;
import com.github.exploder1531.mia.integrations.harvestcraft.Harvestcraft;
import com.github.exploder1531.mia.integrations.hatchery.Hatchery;
import com.github.exploder1531.mia.integrations.iceandfire.IceAndFire;
import com.github.exploder1531.mia.integrations.jei.Jei;
import com.github.exploder1531.mia.integrations.jer.JustEnoughResources;
import com.github.exploder1531.mia.integrations.mocreatures.MoCreatures;
import com.github.exploder1531.mia.integrations.quark.Quark;
import com.github.exploder1531.mia.integrations.tconstruct.TinkersConstruct;
import com.github.exploder1531.mia.integrations.thaumcraft.Thaumcraft;
import com.github.exploder1531.mia.integrations.theoneprobe.TheOneProbe;
import com.github.exploder1531.mia.integrations.thermalexpansion.ThermalExpansion;
import com.github.exploder1531.mia.integrations.thermalfoundation.ThermalFoundation;
import com.github.exploder1531.mia.integrations.xu2.ExtraUtilities2;
import com.setycz.chickens.ChickensMod;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Optional;
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

import static com.github.exploder1531.mia.integrations.ModIds.*;

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
    @SideOnly(Side.CLIENT)
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
        
        if (EXTRA_UTILITIES.isLoaded)
            modIntegrations.put(EXTRA_UTILITIES, new ExtraUtilities2());
        if (COFH_CORE.isLoaded)
            modIntegrations.put(COFH_CORE, new CofhCore());
        if (THERMAL_FOUNDATION.isLoaded)
            modIntegrations.put(THERMAL_FOUNDATION, new ThermalFoundation());
        if (THERMAL_EXPANSION.isLoaded)
            modIntegrations.put(THERMAL_EXPANSION, new ThermalExpansion());
        if (TINKERS_CONSTRUCT.isLoaded)
            modIntegrations.put(TINKERS_CONSTRUCT, new TinkersConstruct());
        if (JEI.isLoaded)
            modIntegrations.put(JEI, new Jei());
        if (JER.isLoaded)
            modIntegrations.put(JER, new JustEnoughResources());
        if (ICE_AND_FIRE.isLoaded)
            modIntegrations.put(ICE_AND_FIRE, new IceAndFire());
        if (HATCHERY.isLoaded)
            modIntegrations.put(HATCHERY, new Hatchery());
        if (THAUMCRAFT.isLoaded)
            modIntegrations.put(THAUMCRAFT, new Thaumcraft());
        if (THE_ONE_PROBE.isLoaded)
            modIntegrations.put(THE_ONE_PROBE, new TheOneProbe());
        if (MO_CREATURES.isLoaded)
            modIntegrations.put(MO_CREATURES, new MoCreatures());
        if (DUNGEON_TACTICS.isLoaded)
            modIntegrations.put(DUNGEON_TACTICS, new DungeonTactics());
        if (HARVESTCRAFT.isLoaded)
            modIntegrations.put(HARVESTCRAFT, new Harvestcraft());
        if (BOTANIA.isLoaded)
            modIntegrations.put(BOTANIA, new Botania());
        if (EXTRABOTANY.isLoaded)
            modIntegrations.put(EXTRABOTANY, new ExtraBotany());
        if (QUARK.isLoaded)
            modIntegrations.put(QUARK, new Quark());
        if (FUTURE_MC.isLoaded)
            modIntegrations.put(FUTURE_MC, new FutureMc());
        
        for (IBaseMod mod : modIntegrations.values())
            mod.register(this::registerIntegration);
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
    
    public void registerLootTableListeners(HashSet<LootTableListener> integrations)
    {
        if (registeredLootTableListeners)
        {
            Mia.LOGGER.warn("ModIntegrator.registerLootTableListeners() was called more than once, this is not something that should happen.");
            return;
        }
        registeredLootTableListeners = true;
        
        for (IBaseMod mod : modIntegrations.values())
        {
            LootTableListener listener = mod.registerLootListener();
            if (listener != null)
                integrations.add(listener);
        }
    }
    
    @Optional.Method(modid = ConstantIds.THAUMCRAFT)
    public void registerAspects(AspectRegistryEvent event)
    {
        if (registeredAspects)
        {
            Mia.LOGGER.warn("ModIntegrator.registerAspects() was called more than once, this is not something that should happen.");
            return;
        }
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
