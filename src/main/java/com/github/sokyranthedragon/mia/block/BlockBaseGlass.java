package com.github.sokyranthedragon.mia.block;

import com.github.sokyranthedragon.mia.Mia;
import com.github.sokyranthedragon.mia.MiaCreativeTab;
import com.github.sokyranthedragon.mia.config.MiaConfig;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.BlockGlass;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class BlockBaseGlass extends BlockGlass implements IAutoRegisterBlock
{
    public BlockBaseGlass(@Nonnull String name, @Nullable CreativeTabs creativeTab, boolean ignoreSimilarity)
    {
        super(Material.GLASS, ignoreSimilarity);

        setTranslationKey(name);
        setRegistryName(Mia.MODID, name);
        setSoundType(SoundType.GLASS);

        if (MiaConfig.miaCreativeTab)
            setCreativeTab(MiaCreativeTab.INSTANCE);
        else if (creativeTab != null)
            setCreativeTab(creativeTab);
    }
}
