package com.github.exploder1531.mia.integrations.base;

import com.gendeathrow.morechickens.core.ChickensMore;
import com.gendeathrow.morechickens.core.ModItems;
import com.github.exploder1531.mia.integrations.ModIds;
import com.github.exploder1531.mia.integrations.botania.Botania;
import com.github.exploder1531.mia.integrations.cofhcore.CofhCore;
import com.github.exploder1531.mia.integrations.dungeontactics.DungeonTactics;
import com.github.exploder1531.mia.integrations.extrabotany.ExtraBotany;
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
import com.google.common.collect.Maps;
import com.setycz.chickens.ChickensMod;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.LootTableLoadEvent;
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
import java.util.Map;

import static com.github.exploder1531.mia.integrations.ModLoadStatus.*;

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
        
        if (extraUtilitiesLoaded)
            modIntegrations.put(ModIds.EXTRA_UTILITIES, new ExtraUtilities2());
        if (cofhCoreLoaded)
            modIntegrations.put(ModIds.COFH_CORE, new CofhCore());
        if (thermalFoundationLoaded)
            modIntegrations.put(ModIds.THERMAL_FOUNDATION, new ThermalFoundation());
        if (thermalExpansionLoaded)
            modIntegrations.put(ModIds.THERMAL_EXPANSION, new ThermalExpansion());
        if (tinkersConstructLoaded)
            modIntegrations.put(ModIds.TINKERS_CONSTRUCT, new TinkersConstruct());
        if (jeiLoaded)
            modIntegrations.put(ModIds.JEI, new Jei());
        if (jerLoaded)
            modIntegrations.put(ModIds.JER, new JustEnoughResources());
        if (iceAndFireLoaded)
            modIntegrations.put(ModIds.ICE_AND_FIRE, new IceAndFire());
        if (hatcheryLoaded)
            modIntegrations.put(ModIds.HATCHERY, new Hatchery());
        if (thaumcraftLoaded)
            modIntegrations.put(ModIds.THAUMCRAFT, new Thaumcraft());
        if (theOneProbeLoaded)
            modIntegrations.put(ModIds.THE_ONE_PROBE, new TheOneProbe());
        if (moCreaturesLoaded)
            modIntegrations.put(ModIds.MO_CREATURES, new MoCreatures());
        if (dungeonTacticsLoaded)
            modIntegrations.put(ModIds.DUNGEON_TACTICS, new DungeonTactics());
        if (harvestcraftLoaded)
            modIntegrations.put(ModIds.HARVESTCRAFT, new Harvestcraft());
        if (botaniaLoaded)
            modIntegrations.put(ModIds.BOTANIA, new Botania());
        if (extraBotanyLoaded)
            modIntegrations.put(ModIds.EXTRABOTANY, new ExtraBotany());
        if (quarkLoaded)
            modIntegrations.put(ModIds.QUARK, new Quark());
        
        for (IBaseMod mod : modIntegrations.values())
            mod.register(this::registerIntegration);
    }
    
    @ParametersAreNonnullByDefault
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
    
    public void lootTableLoad(LootTableLoadEvent event)
    {
        for (IBaseMod mod : modIntegrations.values())
            mod.lootLoad(event);
    }
    
    @Optional.Method(modid = ModIds.THAUMCRAFT)
    public void registerAspects(AspectRegistryEvent event)
    {
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
