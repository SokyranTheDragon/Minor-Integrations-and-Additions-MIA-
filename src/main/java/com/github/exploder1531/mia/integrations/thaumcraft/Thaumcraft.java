package com.github.exploder1531.mia.integrations.thaumcraft;

import com.github.exploder1531.mia.Mia;
import com.github.exploder1531.mia.block.BlockVoidCreator;
import com.github.exploder1531.mia.core.MiaBlocks;
import com.github.exploder1531.mia.integrations.ModIds;
import com.github.exploder1531.mia.integrations.base.IBaseMod;
import com.github.exploder1531.mia.integrations.base.IModIntegration;
import com.github.exploder1531.mia.tile.TileVoidCreator;
import com.github.exploder1531.mia.utilities.RegisterUtils;
import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.blocks.BlocksTC;
import thaumcraft.api.crafting.InfusionRecipe;
import thaumcraft.api.items.ItemsTC;

import java.util.function.BiConsumer;

import static com.github.exploder1531.mia.config.ThaumcraftConfiguration.*;
import static com.github.exploder1531.mia.integrations.ModLoadStatus.*;

public class Thaumcraft implements IBaseMod
{
    @Override
    public void register(BiConsumer<String, IModIntegration> modIntegration)
    {
        if (enableJerIntegration && jerLoaded)
            modIntegration.accept(ModIds.JER, new JerThaumcraftIntegration());
        if (enableTeIntegration && thermalExpansionLoaded)
            modIntegration.accept(ModIds.THERMAL_EXPANSION, new ThermalExpansionThaumcraftIntegration());
        if (enableXu2Integration && extraUtilitiesLoaded)
            modIntegration.accept(ModIds.EXTRA_UTILITIES, new ExtraUtilsThaumcraftIntegration());
        if (hatcheryLoaded)
            modIntegration.accept(ModIds.HATCHERY, new HatcheryThaumcraftIntegration(enableHatcheryIntegration));
        if (dungeonTacticsLoaded)
            modIntegration.accept(ModIds.DUNGEON_TACTICS, new DungeonTacticsThaumcraftIntegration());
    }
    
    @Override
    public void preInit(FMLPreInitializationEvent event)
    {
        if (!thaumcraftAdditionsEnabled)
            return;
        
        ThaumcraftApi.registerResearchLocation(new ResourceLocation(Mia.MODID, "research/basics.json"));
    }
    
    @Override
    public void registerBlocks(RegistryEvent.Register<Block> event)
    {
        if (!thaumcraftAdditionsEnabled)
            return;
        
        final IForgeRegistry<Block> registry = event.getRegistry();
        
        MiaBlocks.void_creator = new BlockVoidCreator();
        
        registry.register(MiaBlocks.void_creator);
        
        GameRegistry.registerTileEntity(TileVoidCreator.class, new ResourceLocation("mia", "void_creator"));
    }
    
    @SuppressWarnings("ConstantConditions")
    @Override
    public void registerItems(RegistryEvent.Register<Item> event)
    {
        if (!thaumcraftAdditionsEnabled)
            return;
        
        final IForgeRegistry<Item> registry = event.getRegistry();
        
        registry.register(new ItemBlock(MiaBlocks.void_creator).setRegistryName(MiaBlocks.void_creator.getRegistryName()));
        
        ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation(Mia.MODID, "void_creator"),
                new InfusionRecipe(
                        "MIA.VOID_CREATOR",
                        new ItemStack(MiaBlocks.void_creator),
                        9,
                        new AspectList().add(Aspect.ELDRITCH, 50).add(Aspect.CRAFT, 50).add(Aspect.ENTROPY, 50).add(Aspect.VOID, 100),
                        new ItemStack(Items.GHAST_TEAR),
                        new ItemStack(BlocksTC.stoneArcane),
                        new ItemStack(BlocksTC.stoneArcane),
                        new ItemStack(ItemsTC.mechanismComplex),
                        "plateBrass",
                        "plateBrass",
                        new ItemStack(Items.DIAMOND),
                        new ItemStack(Items.DIAMOND),
                        new ItemStack(Items.NETHER_STAR)
                ));
    }
    
    @Override
    public void registerRenders(ModelRegistryEvent event)
    {
        if (!thaumcraftAdditionsEnabled)
            return;
        
        RegisterUtils.registerItemblockRenderer(MiaBlocks.void_creator);
    }
}
