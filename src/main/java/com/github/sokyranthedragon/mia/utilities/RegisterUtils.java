package com.github.sokyranthedragon.mia.utilities;

import com.github.sokyranthedragon.mia.block.IMetaBlock;
import com.github.sokyranthedragon.mia.items.itemblocks.ItemBlockMeta;
import com.github.sokyranthedragon.mia.items.itemblocks.ItemSlabMeta;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemSlab;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNullableByDefault;

import static net.minecraftforge.fml.relauncher.Side.CLIENT;

@ParametersAreNullableByDefault
@SuppressWarnings("WeakerAccess")
public class RegisterUtils
{
    private RegisterUtils()
    {
    }
    
    public static void registerItemblockSlab(BlockSlab singleSlab, BlockSlab doubleSlab, @Nonnull IForgeRegistry<Item> registry)
    {
        if (singleSlab != null && doubleSlab != null && singleSlab.getRegistryName() != null)
        {
            if (singleSlab instanceof IMetaBlock)
                registry.register(new ItemSlabMeta((Block & IMetaBlock) singleSlab, singleSlab, doubleSlab).setRegistryName(singleSlab.getRegistryName()));
            else
                registry.register(new ItemSlab(singleSlab, singleSlab, doubleSlab).setRegistryName(singleSlab.getRegistryName()));
        }
    }
    
    public static void registerItemblock(Block block, @Nonnull IForgeRegistry<Item> registry)
    {
        if (block != null && block.getRegistryName() != null)
        {
            if (block instanceof IMetaBlock)
                registry.register(new ItemBlockMeta((Block & IMetaBlock) block).setRegistryName(block.getRegistryName()));
            else
                registry.register(new ItemBlock(block).setRegistryName(block.getRegistryName()));
        }
    }
    
    @SideOnly(CLIENT)
    public static void registerItemblockRenderer(Block block)
    {
        if (block instanceof IMetaBlock)
        {
            for (int i = 0; i <= ((IMetaBlock)block).getMaxMeta(); i++)
                registerItemblockRenderer(block, i);
        }
        else
            registerItemblockRenderer(block, 0);
    }
    
    @SideOnly(CLIENT)
    public static void registerItemblockRenderer(Block block, int metadata)
    {
        if (block != null)
        {
            Item item = Item.getItemFromBlock(block);
            registerItemRenderer(item, metadata);
        }
    }
    
    @SideOnly(CLIENT)
    public static void registerItemRenderer(Item item)
    {
        registerItemRenderer(item, 0);
    }
    
    @SideOnly(CLIENT)
    public static void registerItemRenderer(Item item, int metadata)
    {
        if (item != null && item != Items.AIR)
        {
            ResourceLocation registryName = item.getRegistryName();
            if (registryName != null)
            {
                if (item instanceof IMetaBlock)
                    ModelLoader.setCustomModelResourceLocation(item, metadata, new ModelResourceLocation(registryName, ((IMetaBlock) item).getVariantName() + "=" + ((IMetaBlock) item).getNameFromMeta(metadata)));
                else
                    ModelLoader.setCustomModelResourceLocation(item, metadata, new ModelResourceLocation(registryName, "inventory"));
            }
        }
    }
}
