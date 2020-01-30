package com.github.sokyranthedragon.mia.integrations.thaumcraft;

import com.github.sokyranthedragon.mia.Mia;
import com.github.sokyranthedragon.mia.block.BlockVoidCreator;
import com.github.sokyranthedragon.mia.core.MiaBlocks;
import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.integrations.base.IBaseMod;
import com.github.sokyranthedragon.mia.integrations.base.IModIntegration;
import com.github.sokyranthedragon.mia.tile.TileVoidCreator;
import com.github.sokyranthedragon.mia.utilities.RegisterUtils;
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
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.blocks.BlocksTC;
import thaumcraft.api.crafting.InfusionRecipe;
import thaumcraft.api.items.ItemsTC;

import java.util.function.BiConsumer;

import static com.github.sokyranthedragon.mia.config.ThaumcraftConfiguration.*;

public class Thaumcraft implements IBaseMod
{
    @Override
    public void register(BiConsumer<ModIds, IModIntegration> modIntegration)
    {
        if (ModIds.JEI.isLoaded)
            modIntegration.accept(ModIds.JEI, new JeiThaumcraftIntegration());
        if (enableJerIntegration && ModIds.JER.isLoaded)
            modIntegration.accept(ModIds.JER, new JerThaumcraftIntegration());
        if (enableTeIntegration && ModIds.THERMAL_EXPANSION.isLoaded)
            modIntegration.accept(ModIds.THERMAL_EXPANSION, new ThermalExpansionThaumcraftIntegration());
        if (enableXu2Integration && ModIds.EXTRA_UTILITIES.isLoaded)
            modIntegration.accept(ModIds.EXTRA_UTILITIES, new ExtraUtilsThaumcraftIntegration());
        if (ModIds.HATCHERY.isLoaded)
            modIntegration.accept(ModIds.HATCHERY, new HatcheryThaumcraftIntegration(enableHatcheryIntegration));
        if (enableDungeonTacticsIntegration && ModIds.DUNGEON_TACTICS.isLoaded)
            modIntegration.accept(ModIds.DUNGEON_TACTICS, new DungeonTacticsThaumcraftIntegration());
        if (enableFutureMcIntegration && ModIds.FUTURE_MC.isLoaded)
            modIntegration.accept(ModIds.FUTURE_MC, new FutureMcThaumcraftIntegration());
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
        
        MiaBlocks.voidCreator = MiaBlocks.registerBlock(new BlockVoidCreator(), registry);
        
        GameRegistry.registerTileEntity(TileVoidCreator.class, new ResourceLocation("mia", "void_creator"));
    }
    
    @SuppressWarnings("ConstantConditions")
    @Override
    public void registerItems(RegistryEvent.Register<Item> event)
    {
        if (!thaumcraftAdditionsEnabled)
            return;
        
        final IForgeRegistry<Item> registry = event.getRegistry();
        
        registry.register(new ItemBlock(MiaBlocks.voidCreator).setRegistryName(MiaBlocks.voidCreator.getRegistryName()));
        
        ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation(Mia.MODID, "void_creator"),
                new InfusionRecipe(
                        "MIA.VOID_CREATOR",
                        new ItemStack(MiaBlocks.voidCreator),
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
    @SideOnly(Side.CLIENT)
    public void registerRenders(ModelRegistryEvent event)
    {
        if (!thaumcraftAdditionsEnabled)
            return;
        
        RegisterUtils.registerItemblockRenderer(MiaBlocks.voidCreator);
    }
}
