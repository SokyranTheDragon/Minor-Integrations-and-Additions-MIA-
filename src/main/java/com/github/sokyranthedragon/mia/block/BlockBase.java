package com.github.sokyranthedragon.mia.block;

import com.github.sokyranthedragon.mia.Mia;
import com.github.sokyranthedragon.mia.MiaCreativeTab;
import com.github.sokyranthedragon.mia.config.MiaConfig;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class BlockBase extends Block
{
    public BlockBase(Material material, @Nonnull String name, @Nullable CreativeTabs creativeTab)
    {
        super(material);
        
        setTranslationKey(name);
        setRegistryName(Mia.MODID, name);
        
        if (MiaConfig.miaCreativeTab)
            setCreativeTab(MiaCreativeTab.INSTANCE);
        else if (creativeTab != null)
            setCreativeTab(creativeTab);
    }
}
