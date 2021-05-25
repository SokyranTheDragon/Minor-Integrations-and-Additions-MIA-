package com.github.sokyranthedragon.mia.integrations.base;

import com.gendeathrow.morechickens.core.ChickensMore;
import com.gendeathrow.morechickens.core.ModItems;
import com.gildedgames.the_aether.api.freezables.AetherFreezableFuel;
import com.github.sokyranthedragon.mia.Mia;
import com.github.sokyranthedragon.mia.config.AetherConfig;
import com.github.sokyranthedragon.mia.config.MiaConfig;
import com.github.sokyranthedragon.mia.config.ThaumcraftConfiguration;
import com.github.sokyranthedragon.mia.gui.client.ErrorToast;
import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.integrations.aether.Aether;
import com.github.sokyranthedragon.mia.integrations.aether_continuation.AetherContinuation;
import com.github.sokyranthedragon.mia.integrations.aether_lost_content.AetherLostContent;
import com.github.sokyranthedragon.mia.integrations.biomesoplenty.BiomesOPlenty;
import com.github.sokyranthedragon.mia.integrations.botania.Botania;
import com.github.sokyranthedragon.mia.integrations.charm.Charm;
import com.github.sokyranthedragon.mia.integrations.chisel.Chisel;
import com.github.sokyranthedragon.mia.integrations.cofhcore.CofhCore;
import com.github.sokyranthedragon.mia.integrations.dungeontactics.DungeonTactics;
import com.github.sokyranthedragon.mia.integrations.ender_io.EnderIo;
import com.github.sokyranthedragon.mia.integrations.ender_io_zoo.EnderIoZoo;
import com.github.sokyranthedragon.mia.integrations.extrabotany.ExtraBotany;
import com.github.sokyranthedragon.mia.integrations.futuremc.FutureMc;
import com.github.sokyranthedragon.mia.integrations.harvestcraft.Harvestcraft;
import com.github.sokyranthedragon.mia.integrations.hatchery.Hatchery;
import com.github.sokyranthedragon.mia.integrations.iceandfire.IceAndFire;
import com.github.sokyranthedragon.mia.integrations.industrialforegoing.IndustrialForegoing;
import com.github.sokyranthedragon.mia.integrations.jei.Jei;
import com.github.sokyranthedragon.mia.integrations.jer.JustEnoughResources;
import com.github.sokyranthedragon.mia.integrations.mia.MiaSelfIntegrations;
import com.github.sokyranthedragon.mia.integrations.mocreatures.MoCreatures;
import com.github.sokyranthedragon.mia.integrations.natura.Natura;
import com.github.sokyranthedragon.mia.integrations.quark.Quark;
import com.github.sokyranthedragon.mia.integrations.tconstruct.TinkersConstruct;
import com.github.sokyranthedragon.mia.integrations.thaumcraft.Thaumcraft;
import com.github.sokyranthedragon.mia.integrations.thaumcraft.ThaumcraftHelpers;
import com.github.sokyranthedragon.mia.integrations.theoneprobe.TheOneProbe;
import com.github.sokyranthedragon.mia.integrations.thermalexpansion.ThermalExpansion;
import com.github.sokyranthedragon.mia.integrations.thermalfoundation.ThermalFoundation;
import com.github.sokyranthedragon.mia.integrations.xu2.ExtraUtilities2;
import com.setycz.chickens.ChickensMod;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
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
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.registries.IForgeRegistry;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectEventProxy;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.aspects.AspectRegistryEvent;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;

import static com.github.sokyranthedragon.mia.integrations.ModIds.*;

public class ModIntegrator
{
    private Map<ModIds, IBaseMod> modIntegrations = new LinkedHashMap<>();
    
    private boolean modsRegistered = false;
    private boolean modsPreInitialized = false;
    private boolean modsInitialized = false;
    private boolean modsPostInitialized = false;
    private boolean modsLoadCompleted = false;
    
    private boolean registeredBlocks = false;
    private boolean registeredItems = false;
    private boolean registeredRenders = false;
    private boolean registeredLootTableListeners = false;
    private boolean registeredDispenserBehaviors = false;
    
    private boolean registeredAspects = false;
    private boolean registeredAetherFreezable = false;
    
    
    public void registerMods()
    {
        if (modsRegistered)
        {
            Mia.LOGGER.warn("ModIntegrator.registerMods() was called more than once, this is not something that should happen.");
            return;
        }
        modsRegistered = true;
        
        modIntegrations.put(MIA, new MiaSelfIntegrations());
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
        if (NATURA.isLoaded)
            modIntegrations.put(NATURA, new Natura());
        if (BIOMES_O_PLENTY.isLoaded)
            modIntegrations.put(BIOMES_O_PLENTY, new BiomesOPlenty());
        if (CHISEL.isLoaded)
            modIntegrations.put(CHISEL, new Chisel());
        if (INDUSTRIAL_FOREGOING.isLoaded)
            modIntegrations.put(INDUSTRIAL_FOREGOING, new IndustrialForegoing());
        if (AETHER.isLoaded)
        {
            modIntegrations.put(AETHER, new Aether());
            if (AETHER_CONTINUATION.isLoaded)
                modIntegrations.put(AETHER_CONTINUATION, new AetherContinuation());
            if (AETHER_LOST_CONTENT.isLoaded)
                modIntegrations.put(AETHER_LOST_CONTENT, new AetherLostContent());
        }
        if (ENDER_IO.isLoaded)
            modIntegrations.put(ENDER_IO, new EnderIo());
        if (ENDER_IO_ZOO.isLoaded)
            modIntegrations.put(ENDER_IO_ZOO, new EnderIoZoo());
        if (CHARM.isLoaded)
            modIntegrations.put(CHARM, new Charm());
        
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
        
        for (Map.Entry<ModIds, IBaseMod> mod : modIntegrations.entrySet())
        {
            try
            {
                mod.getValue().preInit(event);
            } catch (Exception e)
            {
                Mia.LOGGER.error("An exception occurred in preInit phase of " + mod.getKey().modId + ", this is not really good... Please let the MIA developer know about this.", e);
                if (event.getSide() == Side.CLIENT) ErrorToast.tryAddToast(Minecraft.getMinecraft().getToastGui());
            }
        }
    }
    
    public void init(FMLInitializationEvent event)
    {
        if (modsInitialized)
        {
            Mia.LOGGER.warn("ModIntegrator.init() was called more than once, this is not something that should happen.");
            return;
        }
        modsInitialized = true;
        
        for (Map.Entry<ModIds, IBaseMod> mod : modIntegrations.entrySet())
        {
            try
            {
                mod.getValue().init(event);
            } catch (Exception e)
            {
                Mia.LOGGER.error("An exception occurred in init phase of " + mod.getKey().modId + ", this is not really good... Please let the MIA developer know about this.", e);
                if (event.getSide() == Side.CLIENT) ErrorToast.tryAddToast(Minecraft.getMinecraft().getToastGui());
            }
        }
    }
    
    public void postInit(FMLPostInitializationEvent event)
    {
        if (modsPostInitialized)
        {
            Mia.LOGGER.warn("ModIntegrator.postInit() was called more than once, this is not something that should happen.");
            return;
        }
        modsPostInitialized = true;
        
        for (Map.Entry<ModIds, IBaseMod> mod : modIntegrations.entrySet())
        {
            try
            {
                mod.getValue().postInit(event);
            } catch (Exception e)
            {
                Mia.LOGGER.error("An exception occurred in postInit phase of " + mod.getKey().modId + ", this is not really good... Please let the MIA developer know about this.", e);
                if (event.getSide() == Side.CLIENT) ErrorToast.tryAddToast(Minecraft.getMinecraft().getToastGui());
            }
        }
    }
    
    public void loadCompleted(FMLLoadCompleteEvent event)
    {
        if (modsLoadCompleted)
        {
            Mia.LOGGER.warn("ModIntegrator.loadCompleted() was called more than once, this is not something that should happen.");
            return;
        }
        modsLoadCompleted = true;
        
        for (Map.Entry<ModIds, IBaseMod> mod : modIntegrations.entrySet())
        {
            try
            {
                mod.getValue().loadCompleted(event);
            } catch (Exception e)
            {
                Mia.LOGGER.error("An exception occurred in loadCompleted phase of " + mod.getKey().modId + ", this is not really good... Please let the MIA developer know about this.", e);
                if (event.getSide() == Side.CLIENT) ErrorToast.tryAddToast(Minecraft.getMinecraft().getToastGui());
            }
        }
    }
    
    public void registerBlocks(RegistryEvent.Register<Block> event, Side side)
    {
        if (registeredBlocks)
        {
            Mia.LOGGER.warn("ModIntegrator.registerBlocks() was called more than once, this is not something that should happen.");
            return;
        }
        registeredBlocks = true;
        
        for (Map.Entry<ModIds, IBaseMod> mod : modIntegrations.entrySet())
        {
            try
            {
                mod.getValue().registerBlocks(event);
            } catch (Exception e)
            {
                Mia.LOGGER.error("An exception occurred in registerBlocks phase of " + mod.getKey().modId + ", this is not really good... Please let the MIA developer know about this.", e);
                if (side == Side.CLIENT) ErrorToast.tryAddToast(Minecraft.getMinecraft().getToastGui());
            }
        }
    }
    
    public void registerItems(RegistryEvent.Register<Item> event, Side side)
    {
        if (registeredItems)
        {
            Mia.LOGGER.warn("ModIntegrator.registerItems() was called more than once, this is not something that should happen.");
            return;
        }
        registeredItems = true;
        
        for (Map.Entry<ModIds, IBaseMod> mod : modIntegrations.entrySet())
        {
            try
            {
                mod.getValue().registerItems(event);
            } catch (Exception e)
            {
                Mia.LOGGER.error("An exception occurred in registerItems phase of " + mod.getKey().modId + ", this is not really good... Please let the MIA developer know about this.", e);
                if (side == Side.CLIENT) ErrorToast.tryAddToast(Minecraft.getMinecraft().getToastGui());
            }
        }
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
        
        for (Map.Entry<ModIds, IBaseMod> mod : modIntegrations.entrySet())
        {
            try
            {
                mod.getValue().registerRenders(event);
            } catch (Exception e)
            {
                Mia.LOGGER.error("An exception occurred in registerRenders phase of " + mod.getKey().modId + ", this is not really good... Please let the MIA developer know about this.", e);
                ErrorToast.tryAddToast(Minecraft.getMinecraft().getToastGui());
            }
        }
    }
    
    public void registerLootTableListeners(HashSet<LootTableIntegrator.LootTableListener> integrations, Side side)
    {
        if (registeredLootTableListeners)
        {
            Mia.LOGGER.warn("ModIntegrator.registerLootTableListeners() was called more than once, this is not something that should happen.");
            return;
        }
        registeredLootTableListeners = true;
        
        for (Map.Entry<ModIds, IBaseMod> mod : modIntegrations.entrySet())
        {
            try
            {
                LootTableIntegrator.LootTableListener listener = mod.getValue().registerLootListener();
                if (listener != null)
                    integrations.add(listener);
            } catch (Exception e)
            {
                Mia.LOGGER.error("An exception occurred in registerLootTableListeners phase of " + mod.getKey().modId + ", this is not really good... Please let the MIA developer know about this.", e);
                if (side == Side.CLIENT) ErrorToast.tryAddToast(Minecraft.getMinecraft().getToastGui());
            }
        }
    }
    
    public void registerDispenserBehaviors(Side side)
    {
        if (registeredDispenserBehaviors)
        {
            Mia.LOGGER.warn("ModIntegrator.registerDispenserBehaviors() was called more than once, this is not something that should happen.");
            return;
        }
        registeredDispenserBehaviors = true;
        
        if (!modIntegrations.isEmpty())
        {
            ProgressManager.ProgressBar progressBar = ProgressManager.push("Registering dispenser behaviors", modIntegrations.size());
            for (Map.Entry<ModIds, IBaseMod> mod : modIntegrations.entrySet())
            {
                try
                {
                    progressBar.step(mod.getKey().modId);
                    mod.getValue().registerDispenserBehaviors();
                } catch (Exception e)
                {
                    Mia.LOGGER.error("An exception occurred in registerDispenserBehaviors phase of " + mod.getKey().modId + ", this is not really good... Please let the MIA developer know about this.", e);
                    if (side == Side.CLIENT) ErrorToast.tryAddToast(Minecraft.getMinecraft().getToastGui());
                }
            }
            ProgressManager.pop(progressBar);
        }
        
    }
    
    @Optional.Method(modid = ConstantIds.THAUMCRAFT)
    public void registerAspects(AspectRegistryEvent event, Side side)
    {
        if (registeredAspects)
        {
            Mia.LOGGER.warn("ModIntegrator.registerAspects() was called more than once, this is not something that should happen.");
            return;
        }
        
        registeredAspects = true;
        
        if (!ThaumcraftConfiguration.registerAspects)
            return;
        
        if (modIntegrations.size() > 0)
        {
            ProgressManager.ProgressBar progressBar = ProgressManager.push("Thaumcraft registerAspects", modIntegrations.size());
            for (Map.Entry<ModIds, IBaseMod> mod : modIntegrations.entrySet())
            {
                try
                {
                    progressBar.step(mod.getKey().modId);
                    mod.getValue().registerAspects(event);
                } catch (Exception e)
                {
                    Mia.LOGGER.error("An exception occurred in registerAspects phase of " + mod.getKey().modId + ", this is not really good... Please let the MIA developer know about this.", e);
                    if (side == Side.CLIENT) ErrorToast.tryAddToast(Minecraft.getMinecraft().getToastGui());
                }
            }
            ProgressManager.pop(progressBar);
        }
        
        AspectEventProxy register = event.register;
        
        for (ItemStack bookshelf : OreDictionary.getOres("bookshelf", false))
            ThaumcraftHelpers.transferAspects(bookshelf, new AspectList().add(Aspect.MIND, 8), register);
        
        // If those mods are added as full integration then these should be moved there
        if (Loader.isModLoaded(ChickensMod.MODID))
        {
            register.registerObjectTag(new ItemStack(ChickensMod.liquidEgg, 1, 0), new AspectList().add(Aspect.WATER, 20));
            register.registerObjectTag(new ItemStack(ChickensMod.liquidEgg, 1, 1), new AspectList().add(Aspect.FIRE, 20));
        }
        if (Loader.isModLoaded(ChickensMore.MODID))
            register.registerObjectTag(new ItemStack(ModItems.solidXp), new AspectList().add(Aspect.MIND, 20));
    }
    
    @Optional.Method(modid = ConstantIds.AETHER)
    public void registerFreezableFuel(RegistryEvent.Register<AetherFreezableFuel> event, Side side)
    {
        if (registeredAetherFreezable)
        {
            Mia.LOGGER.warn("ModIntegrator.registerFreezableFuel() was called more than once, this is not something that should happen.");
            return;
        }
        
        registeredAetherFreezable = true;
        
        if (MiaConfig.disableAllRecipes || !AetherConfig.allowNewFreezerFuel)
            return;
        
        if (modIntegrations.size() > 0)
        {
            ProgressManager.ProgressBar progressBar = ProgressManager.push("Aether registerFreezableFuel", modIntegrations.size());
            IForgeRegistry<AetherFreezableFuel> registry = event.getRegistry();
            
            for (Map.Entry<ModIds, IBaseMod> mod : modIntegrations.entrySet())
            {
                try
                {
                    progressBar.step(mod.getKey().modId);
                    mod.getValue().registerFreezableFuel(registry);
                } catch (Exception e)
                {
                    Mia.LOGGER.error("An exception occurred in registerFreezableFuel phase of " + mod.getKey().modId + ", this is not really good... Please let the MIA developer know about this.", e);
                    if (side == Side.CLIENT) ErrorToast.tryAddToast(Minecraft.getMinecraft().getToastGui());
                }
            }
            ProgressManager.pop(progressBar);
        }
    }
}
