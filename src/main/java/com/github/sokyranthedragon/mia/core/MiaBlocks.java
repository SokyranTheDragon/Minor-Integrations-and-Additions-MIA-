package com.github.sokyranthedragon.mia.core;

import com.github.sokyranthedragon.mia.block.IAutoRegisterBlock;
import com.github.sokyranthedragon.mia.block.base.BlockBaseDoor;
import com.github.sokyranthedragon.mia.block.base.BlockBaseGlass;
import com.github.sokyranthedragon.mia.block.decorative.*;
import com.github.sokyranthedragon.mia.utilities.RegisterUtils;
import com.github.sokyranthedragon.mia.utilities.annotations.FieldsAreNullableByDefault;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.LinkedList;
import java.util.List;

@FieldsAreNullableByDefault
public class MiaBlocks
{
    private MiaBlocks()
    {
    }
    
    @Nonnull
    public static final List<Block> blocks = new LinkedList<>();
    
    // Mia
    public static Block armoredGlass = null;
    public static Block packedPaper = null;
    public static Block torchGold = null;
    public static BlockBush flowerDead = null;
    public static Block doorStone = null;
    public static Block redstoneLantern = null;
    
    // Hatchery
    public static Block eggSorter = null;
    
    // Ice and Fire
    public static Block pixieDustExtractor = null;
    
    // Thaumcraft
    public static Block voidCreator = null;
    
    // Botania
    public static Block blockBotaniaSpecialFlower = null;
    
    // Biomes O' Plenty
    public static SandstoneEntry whiteSandstone = null;
    
    public static <T extends Block> T registerBlock(T block)
    {
        return registerBlock(block, null);
    }
    
    public static <T extends Block> T registerBlock(T block, @Nullable IForgeRegistry<Block> registry)
    {
        if (registry != null)
            registry.register(block);
        blocks.add(block);
        return block;
    }
    
    public static void initMiaBlocks()
    {
        armoredGlass = registerBlock(
                new BlockBaseGlass("armored_glass", CreativeTabs.BUILDING_BLOCKS, false)
                        .setHardness(40)
                        .setResistance(1750));
        packedPaper = registerBlock(new BlockPackedPaper());
        torchGold = registerBlock(new BlockGoldenTorch());
        flowerDead = registerBlock(new BlockDeadFlower());
        doorStone = registerBlock(new BlockBaseDoor(Material.ROCK, "door_stone", CreativeTabs.REDSTONE, SoundType.STONE).setHardness(4.0f));
        doorStone.setHarvestLevel("pickaxe", -1);
        redstoneLantern = registerBlock(new BlockRedstoneLantern());
        redstoneLantern.setHarvestLevel("pickaxe", 0);
    }
    
    public static void registerMiaBlocks(RegistryEvent.Register<Block> event)
    {
        IForgeRegistry<Block> registry = event.getRegistry();
        
        for (Block block : blocks)
        {
            if (block instanceof IAutoRegisterBlock && ((IAutoRegisterBlock) block).registerBlock())
                registry.register(block);
        }
    }
    
    public static void registerMiaItemblocks(RegistryEvent.Register<Item> event)
    {
        IForgeRegistry<Item> registry = event.getRegistry();
        
        for (Block block : blocks)
        {
            if (block instanceof IAutoRegisterBlock && ((IAutoRegisterBlock) block).registerItemblock())
                RegisterUtils.registerItemblock(block, registry);
        }
    }
    
    @SideOnly(Side.CLIENT)
    public static void registerMiaItemblockRenderers()
    {
        for (Block block : blocks)
        {
            if (block instanceof IAutoRegisterBlock && ((IAutoRegisterBlock) block).registerItemblockRenderer())
                RegisterUtils.registerItemblockRenderer(block);
        }
    }
}
