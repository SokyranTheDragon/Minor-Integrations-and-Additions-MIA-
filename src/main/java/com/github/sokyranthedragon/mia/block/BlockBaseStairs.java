package com.github.sokyranthedragon.mia.block;

import com.github.sokyranthedragon.mia.Mia;
import com.github.sokyranthedragon.mia.MiaCreativeTab;
import com.github.sokyranthedragon.mia.config.MiaConfig;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class BlockBaseStairs extends BlockStairs implements IAutoRegisterBlock
{
    public BlockBaseStairs(IBlockState modelState, @Nonnull String name, @Nullable CreativeTabs creativeTab)
    {
        super(modelState);
    
        setTranslationKey(name);
        setRegistryName(Mia.MODID, name);
        useNeighborBrightness = true;
    
        if (MiaConfig.miaCreativeTab)
            setCreativeTab(MiaCreativeTab.INSTANCE);
        else if (creativeTab != null)
            setCreativeTab(creativeTab);
    }
}
