package com.github.sokyranthedragon.mia.integrations.aether;

import com.github.sokyranthedragon.mia.config.MiaConfig;
import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.integrations.base.IBaseMod;
import com.github.sokyranthedragon.mia.integrations.base.IModIntegration;
import com.legacy.aether.blocks.BlocksAether;
import com.legacy.aether.items.ItemsAether;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import thaumcraft.api.aspects.*;

import java.util.function.BiConsumer;

import static com.github.sokyranthedragon.mia.config.AetherConfig.*;
import static com.github.sokyranthedragon.mia.integrations.ModIds.*;
import static com.github.sokyranthedragon.mia.integrations.thaumcraft.ThaumcraftHelpers.*;
import static net.minecraftforge.oredict.OreDictionary.registerOre;

public class Aether implements IBaseMod
{
    @Override
    public void register(BiConsumer<ModIds, IModIntegration> modIntegration)
    {
        if (FUTURE_MC.isLoaded && enableFutureMcIntegration)
            modIntegration.accept(FUTURE_MC, new FutureMcAetherIntegration());
        if (DUNGEON_TACTICS.isLoaded && enableDungeonTacticsIntegration)
            modIntegration.accept(DUNGEON_TACTICS, new DungeonTacticsAetherIntegration());
        if (THERMAL_EXPANSION.isLoaded && enableTeIntegration)
            modIntegration.accept(THERMAL_EXPANSION, new ThermalExpansionAetherIntegration());
        if (JER.isLoaded)
            modIntegration.accept(JER, new JerAetherIntegration());
        if (INDUSTRIAL_FOREGOING.isLoaded)
            modIntegration.accept(INDUSTRIAL_FOREGOING, new IndustrialForegoingAetherIntegration());
        if (CHISEL.isLoaded && enableChiselIntegration)
            modIntegration.accept(CHISEL, new ChiselAetherIntegration());
        if (EXTRA_UTILITIES.isLoaded && enableXu2Integration)
            modIntegration.accept(EXTRA_UTILITIES, new ExtraUtilsAetherIntegration());
    }
    
    @Override
    public void init(FMLInitializationEvent event)
    {
        if (!aetherAdditionsEnabled)
            return;
        
        BlocksAether.aether_dirt.setHarvestLevel("shovel", -1);
        BlocksAether.aether_grass.setHarvestLevel("shovel", -1);
        BlocksAether.quicksoil.setHarvestLevel("shovel", -1);
        
        if (!MiaConfig.disableOreDict)
        {
            registerOre("listAllMilk", new ItemStack(ItemsAether.skyroot_bucket, 1, 4));
            registerOre("slimeball", new ItemStack(ItemsAether.swetty_ball));
            registerOre("bookshelf", new ItemStack(BlocksAether.skyroot_bookshelf));
            registerOre("plankWood", new ItemStack(BlocksAether.skyroot_plank));
            registerOre("grass", new ItemStack(BlocksAether.aether_grass));
            registerOre("dirt", new ItemStack(BlocksAether.aether_dirt));
            registerOre("blockMossy", new ItemStack(BlocksAether.mossy_holystone));
            
            registerOre("listAllBerry", new ItemStack(ItemsAether.blue_berry));
            registerOre("listAllFruit", new ItemStack(ItemsAether.blue_berry));
        }
    }
    
    @Override
    public void registerAspects(AspectRegistryEvent event)
    {
        AspectEventProxy register = event.register;
        
        // Ores, trees, and similar
        transferAspects(new ItemStack(BlocksAether.holystone), new ItemStack(Blocks.COBBLESTONE), register, false);
        transferAspects(new ItemStack(BlocksAether.mossy_holystone), new ItemStack(Blocks.MOSSY_COBBLESTONE), register, false);
        transferAspects(new ItemStack(BlocksAether.enchanted_aether_grass), new AspectList().add(Aspect.EARTH, 5).add(Aspect.PLANT, 2).add(Aspect.MAGIC, 2), register);
        transferAspects(new ItemStack(BlocksAether.icestone), new AspectList().add(Aspect.COLD, 5), register);
        transferAspects(new ItemStack(BlocksAether.quicksoil), new AspectList().add(Aspect.EARTH, 3).add(Aspect.MOTION, 5), register);
        
        transferAspects(new ItemStack(ItemsAether.zanite_gemstone), new AspectList().add(Aspect.DESIRE, 15).add(Aspect.CRYSTAL, 15).add(Aspect.MAGIC, 1), register);
        transferAspects(new ItemStack(BlocksAether.zanite_block), new ItemStack(ItemsAether.zanite_gemstone), register, 9);
        transferAspects(new ItemStack(ItemsAether.ambrosium_shard), new AspectList().add(Aspect.AIR, 10).add(Aspect.MAGIC, 5), register);
        transferAspects(new ItemStack(BlocksAether.enchanted_gravitite), new AspectList().add(Aspect.FLIGHT, 5).add(Aspect.AIR, 5).add(Aspect.MAGIC, 5).add(Aspect.METAL, 15), register);
        
        transferAspects(new ItemStack(BlocksAether.ambrosium_torch), new AspectList().add(Aspect.LIGHT, 5).add(Aspect.ENERGY, 1), register);
        
        // Armor, weapons
        AspectList target = AspectHelper.getObjectAspects(new ItemStack(Blocks.OBSIDIAN));
        transferAspects(new ItemStack(ItemsAether.obsidian_helmet), target, register, 2.5f);
        transferAspects(new ItemStack(ItemsAether.obsidian_chestplate), target, register, 4f);
        transferAspects(new ItemStack(ItemsAether.obsidian_leggings), target, register, 3.5f);
        transferAspects(new ItemStack(ItemsAether.obsidian_boots), target, register, 2f);
        transferAspects(new ItemStack(ItemsAether.obsidian_gloves), target, register, 1f);
        
        target = new AspectList().add(Aspect.FIRE, 15);
        transferAspects(new ItemStack(ItemsAether.phoenix_helmet), target, register, 2.5f, false);
        transferAspects(new ItemStack(ItemsAether.phoenix_chestplate), target, register, 4f, false);
        transferAspects(new ItemStack(ItemsAether.phoenix_leggings), target, register, 3.5f, false);
        transferAspects(new ItemStack(ItemsAether.phoenix_boots), target, register, 2f, false);
        transferAspects(new ItemStack(ItemsAether.phoenix_gloves), target, register, 1f, false);
        transferAspects(new ItemStack(ItemsAether.phoenix_bow), target, register, 1f, false);
        transferAspects(new ItemStack(ItemsAether.flaming_sword), target, register, 2f, false);
        
        target = new AspectList().add(Aspect.WATER, 15);
        transferAspects(new ItemStack(ItemsAether.neptune_helmet), target, register, 2.5f, false);
        transferAspects(new ItemStack(ItemsAether.neptune_chestplate), target, register, 4f, false);
        transferAspects(new ItemStack(ItemsAether.neptune_leggings), target, register, 3.5f, false);
        transferAspects(new ItemStack(ItemsAether.neptune_boots), target, register, 2f, false);
        transferAspects(new ItemStack(ItemsAether.neptune_gloves), target, register, 1f, false);
        
        transferAspects(new ItemStack(ItemsAether.chain_gloves), new ItemStack(Items.CHAINMAIL_CHESTPLATE), register, 0.25f, false);
        addAspects(new ItemStack(ItemsAether.lightning_sword), new AspectList().add(Aspect.ENERGY, 16), register);
        addAspects(new ItemStack(ItemsAether.lightning_knife), new AspectList().add(Aspect.ENERGY, 4).add(Aspect.AVERSION, 4), register);
        addAspects(new ItemStack(ItemsAether.vampire_blade), new AspectList().add(Aspect.LIFE, 15).add(Aspect.UNDEAD, 5), register);
        
        // Dart shooter
        transferAspects(new ItemStack(ItemsAether.dart_shooter, 1, 0), new ItemStack(ItemsAether.dart_shooter, 1, 1), register, false);
        transferAspects(new ItemStack(ItemsAether.dart_shooter, 1, 0), new ItemStack(ItemsAether.dart_shooter, 1, 2), register, false, new AspectList().add(Aspect.MAGIC, 10));
        
        transferAspects(new ItemStack(ItemsAether.dart, 1, 0), new ItemStack(ItemsAether.dart, 1, 1), register, false);
        transferAspects(new ItemStack(ItemsAether.dart, 1, 0), new ItemStack(ItemsAether.dart, 1, 2), register, false, new AspectList().add(Aspect.MAGIC, 1));
        
        // Plants
        target = new AspectList().add(Aspect.PLANT, 5).add(Aspect.SENSES, 5).add(Aspect.LIFE, 1);
        addAspects(new ItemStack(BlocksAether.purple_flower), target, register);
        addAspects(new ItemStack(BlocksAether.white_flower), target, register);
        addAspects(new ItemStack(BlocksAether.berry_bush), target, register);
        addAspects(new ItemStack(BlocksAether.berry_bush_stem), target, register);
        
        target = AspectHelper.getObjectAspects(new ItemStack(Items.APPLE));
        transferAspects(new ItemStack(ItemsAether.blue_berry), target, register, false);
        transferAspects(new ItemStack(ItemsAether.enchanted_blueberry), target, register, 3, false, new AspectList().add(Aspect.MAGIC, 5));
        transferAspects(new ItemStack(ItemsAether.white_apple), target, register, false);
        
        // Buckets
        addAspects(new ItemStack(ItemsAether.skyroot_bucket, 1, 0), new AspectList().add(Aspect.VOID, 5), register);
        addAspects(new ItemStack(ItemsAether.skyroot_bucket, 1, 1), new AspectList().add(Aspect.VOID, 5).add(Aspect.WATER, 20), register);
        addAspects(new ItemStack(ItemsAether.skyroot_bucket, 1, 2), new AspectList().add(Aspect.VOID, 5).add(Aspect.DEATH, 10).add(Aspect.MAGIC, 5), register);
        addAspects(new ItemStack(ItemsAether.skyroot_bucket, 1, 3), new AspectList().add(Aspect.VOID, 5).add(Aspect.LIFE, 10).add(Aspect.MAGIC, 15), register);
        addAspects(new ItemStack(ItemsAether.skyroot_bucket, 1, 4), new AspectList().add(Aspect.VOID, 5).add(Aspect.LIFE, 10).add(Aspect.BEAST, 5).add(Aspect.WATER, 5), register);
        
        // Random stuff
        addAspects(new ItemStack(ItemsAether.healing_stone), new AspectList().add(Aspect.LIFE, 10).add(Aspect.MAGIC, 10), register);
        addAspects(new ItemStack(ItemsAether.golden_amber), new AspectList().add(Aspect.PLANT, 1).add(Aspect.DESIRE, 1), register);
        addAspects(new ItemStack(ItemsAether.aechor_petal), new AspectList().add(Aspect.PLANT, 5).add(Aspect.SENSES, 5), register);
        addAspects(new ItemStack(BlocksAether.aerogel), new AspectList().add(Aspect.COLD, 5).add(Aspect.FIRE, 5), register);
        addAspects(new ItemStack(BlocksAether.sun_altar), new AspectList().add(Aspect.MAGIC, 50).add(Aspect.LIGHT, 15).add(Aspect.FIRE, 30), register);
        
        // Add 1 aer to every item/block
        for (Item item : ItemsAether.itemRegistry)
        {
            if (item.getRegistryName() != null && (item.getRegistryName().getNamespace().equals("aether_legacy") || item.getRegistryName().getNamespace().equals("lost_aether")))
            {
                NonNullList<ItemStack> stacks = NonNullList.create();
                item.getSubItems(CreativeTabs.SEARCH, stacks);
                addAspect(stacks, register, Aspect.AIR);
            }
        }
        
        for (Block block : BlocksAether.blockList)
        {
            if (block.getRegistryName() != null && (block.getRegistryName().getNamespace().equals("aether_legacy") || block.getRegistryName().getNamespace().equals("lost_aether")))
            {
                NonNullList<ItemStack> stacks = NonNullList.create();
                block.getSubBlocks(null, stacks);
                addAspect(stacks, register, Aspect.AIR);
            }
        }
    }
}