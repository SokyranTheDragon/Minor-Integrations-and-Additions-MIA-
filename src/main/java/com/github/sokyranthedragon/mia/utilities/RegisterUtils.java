package com.github.sokyranthedragon.mia.utilities;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;

import javax.annotation.Nonnull;

@SuppressWarnings("WeakerAccess")
public class RegisterUtils
{
    private RegisterUtils()
    {
    }
    
    public static void registerItemblockRenderer(@Nonnull Block block)
    {
        registerItemblockRenderer(block, 0);
    }
    
    public static void registerItemblockRenderer(@Nonnull Block block, int metadata)
    {
        Item item = Item.getItemFromBlock(block);
        registerItemRenderer(item, metadata);
    }
    
    public static void registerItemRenderer(@Nonnull Item item)
    {
        registerItemRenderer(item, 0);
    }
    
    public static void registerItemRenderer(@Nonnull Item item, int metadata)
    {
        ResourceLocation registryName = item.getRegistryName();
        if (registryName != null)
            ModelLoader.setCustomModelResourceLocation(item, metadata, new ModelResourceLocation(registryName, "inventory"));
    }
}
