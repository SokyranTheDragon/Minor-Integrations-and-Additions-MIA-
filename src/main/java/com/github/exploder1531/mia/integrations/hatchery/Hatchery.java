package com.github.exploder1531.mia.integrations.hatchery;

import com.gendeathrow.hatchery.block.nestpen.NestPenTileEntity;
import com.gendeathrow.hatchery.core.config.ConfigLootHandler;
import com.gendeathrow.hatchery.core.init.ModItems;
import com.github.exploder1531.mia.Mia;
import com.github.exploder1531.mia.block.BlockEggSorter;
import com.github.exploder1531.mia.core.MiaBlocks;
import com.github.exploder1531.mia.integrations.ModIds;
import com.github.exploder1531.mia.integrations.base.IBaseMod;
import com.github.exploder1531.mia.integrations.base.IModIntegration;
import com.github.exploder1531.mia.tile.TileEggSorter;
import com.github.exploder1531.mia.utilities.RegisterUtils;
import com.google.common.collect.Lists;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.fml.common.ProgressManager;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.aspects.AspectRegistryEvent;

import java.util.List;

import static com.github.exploder1531.mia.config.HatcheryConfiguration.*;

public class Hatchery implements IBaseMod
{
    private final List<IHatcheryIntegration> modIntegrations = Lists.newLinkedList();
    private final LuckyEggLoader loader = new LuckyEggLoader();
    
    @Override
    public void addIntegration(IModIntegration integration)
    {
        // We don't disable external integrations as usual, as we need to get ModId data from other mods.
        if (integration instanceof IHatcheryIntegration)
        {
            modIntegrations.add((IHatcheryIntegration) integration);
            return;
        }
        
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
        
        final IForgeRegistry<Block> registry = event.getRegistry();
        
        MiaBlocks.egg_sorter = new BlockEggSorter();
        
        registry.register(MiaBlocks.egg_sorter);
        
        GameRegistry.registerTileEntity(TileEggSorter.class, new ResourceLocation("mia", "egg_sorter"));
    }
    
    @SuppressWarnings("ConstantConditions")
    @Override
    public void registerItems(RegistryEvent.Register<Item> event)
    {
        if (!hatcheryAdditionsEnabled)
            return;
        
        final IForgeRegistry<Item> registry = event.getRegistry();
        
        registry.register(new ItemBlock(MiaBlocks.egg_sorter).setRegistryName(MiaBlocks.egg_sorter.getRegistryName()));
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void registerRenders(ModelRegistryEvent event)
    {
        if (!hatcheryAdditionsEnabled)
            return;
        
        RegisterUtils.registerItemblockRenderer(MiaBlocks.egg_sorter);
//        Item eggSorterItem = Item.getItemFromBlock(MiaBlocks.egg_sorter);
//        ModelLoader.setCustomModelResourceLocation(eggSorterItem, 0, new ModelResourceLocation("mia:" + eggSorterItem.getRegistryName().getPath(), "inventory"));
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
}
