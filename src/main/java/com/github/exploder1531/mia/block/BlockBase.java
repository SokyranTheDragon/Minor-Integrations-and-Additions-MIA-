package com.github.exploder1531.mia.block;

import com.github.exploder1531.mia.Mia;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

import javax.annotation.Nonnull;

public class BlockBase extends Block
{
    public BlockBase(Material material, @Nonnull String name)
    {
        super(material);
        
        setTranslationKey(name);
        setRegistryName(Mia.MODID, name);
    }
}
