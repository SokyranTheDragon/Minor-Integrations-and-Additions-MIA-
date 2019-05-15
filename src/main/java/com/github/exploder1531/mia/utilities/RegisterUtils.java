package com.github.exploder1531.mia.utilities;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;

@SuppressWarnings("WeakerAccess")
public class RegisterUtils
{
    private RegisterUtils()
    {
    }
    
    public static void registerItemblockRenderer(Block block)
    {
        registerItemblockRenderer(block, 0);
    }
    
    @SuppressWarnings("ConstantConditions")
    public static void registerItemblockRenderer(Block block, int metadata)
    {
        Item item = Item.getItemFromBlock(block);
        ModelLoader.setCustomModelResourceLocation(item, metadata, new ModelResourceLocation(item.getRegistryName(), "inventory"));
    }
}
