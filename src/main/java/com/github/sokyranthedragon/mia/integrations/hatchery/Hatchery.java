package com.github.sokyranthedragon.mia.integrations.hatchery;

import com.gendeathrow.hatchery.api.crafting.ShredderRecipe;
import com.gendeathrow.hatchery.block.nestpen.NestPenTileEntity;
import com.gendeathrow.hatchery.block.shredder.ShredderTileEntity;
import com.gendeathrow.hatchery.core.config.ConfigLootHandler;
import com.gendeathrow.hatchery.core.init.ModItems;
import com.github.sokyranthedragon.mia.Mia;
import com.github.sokyranthedragon.mia.block.BlockEggSorter;
import com.github.sokyranthedragon.mia.config.MiaConfig;
import com.github.sokyranthedragon.mia.core.MiaBlocks;
import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.integrations.base.IBaseMod;
import com.github.sokyranthedragon.mia.integrations.base.IModIntegration;
import com.github.sokyranthedragon.mia.tile.TileEggSorter;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.fml.common.ProgressManager;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.aspects.AspectRegistryEvent;

import java.util.LinkedList;
import java.util.List;

import static com.github.sokyranthedragon.mia.config.HatcheryConfiguration.*;

public class Hatchery implements IBaseMod
{
    private final List<IHatcheryIntegration> modIntegrations = new LinkedList<>();
    private final LuckyEggLoader loader = new LuckyEggLoader();
    
    @Override
    public void addIntegration(IModIntegration integration)
    {
        // We don't disable external integrations as usual, as we need to get ModId data from other mods.
        if (integration instanceof IHatcheryIntegration)
            modIntegrations.add((IHatcheryIntegration) integration);
        else
            Mia.LOGGER.warn("Incorrect Hatchery integration with id of " + integration.getModId() + ": " + integration.toString());
    }
    
    @Override
    public void init(FMLInitializationEvent event)
    {
        ProgressManager.ProgressBar progressBar = ProgressManager.push("Hatchery", modIntegrations.size() + 2);
        progressBar.step("setting up");
        
        if (externalIntegrationsEnabled)
        {
            for (IHatcheryIntegration integration : modIntegrations)
            {
                progressBar.step(integration.getModId().modId);
                
                if (integration.isModEnabled())
                {
                    loader.tryCreateNewLootFile(integration.getModId(), integration.getCurrentLootVersion(), integration.getDefaultEggDrops());
                    if (!MiaConfig.disableAllRecipes)
                        integration.registerShredder();
                }
                else
                    loader.loadedFiles.add(integration.getModId().modId);
            }
        }
        
        progressBar.step("finishing up");
        
        if (registerCustomLuckyEggLoot)
            loader.loadRemainingFiles();
        ConfigLootHandler.drops.addAll(loader.drops);
        
        if (disableNestingPenChickenDisplay)
            TileEntityRendererDispatcher.instance.renderers.remove(NestPenTileEntity.class);
        
        ProgressManager.pop(progressBar);
    }
    
    @Override
    public void registerBlocks(RegistryEvent.Register<Block> event)
    {
        if (!hatcheryAdditionsEnabled)
            return;
        
        MiaBlocks.eggSorter = MiaBlocks.registerBlock(new BlockEggSorter());
        
        GameRegistry.registerTileEntity(TileEggSorter.class, new ResourceLocation("mia", "egg_sorter"));
    }
    
    @Optional.Method(modid = ModIds.ConstantIds.THAUMCRAFT)
    @Override
    public void registerAspects(AspectRegistryEvent event)
    {
        if (!hatcheryAdditionsEnabled)
            return;
        
        event.register.registerObjectTag(new ItemStack(ModItems.hatcheryEgg), new AspectList().add(Aspect.LIFE, 5).add(Aspect.BEAST, 5));
    }
    
    public static ConfigLootHandler.ItemDrop getDrop(ItemStack item, int weight, int minQuantity, int maxQuantity)
    {
        return new ConfigLootHandler.ItemDrop(item, weight, minQuantity, maxQuantity);
    }
    
    public static ConfigLootHandler.ItemDrop getDrop(Item item, int meta, int weight, int minQuantity, int maxQuantity)
    {
        return getDrop(new ItemStack(item, 1, meta), weight, minQuantity, maxQuantity);
    }
    
    public static ConfigLootHandler.ItemDrop getDrop(Item item, int weight, int minQuantity, int maxQuantity)
    {
        return getDrop(new ItemStack(item), weight, minQuantity, maxQuantity);
    }
    
    public static ConfigLootHandler.ItemDrop getDrop(Item item, int meta, int weight)
    {
        return getDrop(new ItemStack(item, 1, meta), weight, 1, 1);
    }
    
    public static ConfigLootHandler.ItemDrop getDrop(Item item, int weight)
    {
        return getDrop(new ItemStack(item), weight, 1, 1);
    }
    
    public static ConfigLootHandler.ItemDrop getDrop(Block item, int meta, int weight, int minQuantity, int maxQuantity)
    {
        return getDrop(new ItemStack(item, 1, meta), weight, minQuantity, maxQuantity);
    }
    
    public static ConfigLootHandler.ItemDrop getDrop(Block item, int weight, int minQuantity, int maxQuantity)
    {
        return getDrop(new ItemStack(item), weight, minQuantity, maxQuantity);
    }
    
    public static ConfigLootHandler.ItemDrop getDrop(Block item, int meta, int weight)
    {
        return getDrop(new ItemStack(item, 1, meta), weight, 1, 1);
    }
    
    public static ConfigLootHandler.ItemDrop getDrop(Block item, int weight)
    {
        return getDrop(new ItemStack(item), weight, 1, 1);
    }
    
    public static void registerShredder(ItemStack input, ItemStack output)
    {
        registerShredder(input, output, ItemStack.EMPTY);
    }
    
    public static void registerShredder(ItemStack input, ItemStack output, ItemStack extra)
    {
        registerShredder(input, output, extra, 3);
    }
    
    public static void registerShredder(ItemStack input, ItemStack output, ItemStack extra, int chance)
    {
        registerShredder(input, output, extra, chance, 100);
    }
    
    public static void registerShredder(ItemStack input, ItemStack output, ItemStack extra, int chance, int time)
    {
        ShredderTileEntity.shredderRecipes.add(new ShredderRecipe(input, output, extra, chance, time));
    }
}
