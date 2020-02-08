package com.github.sokyranthedragon.mia.block.base;

import com.github.sokyranthedragon.mia.Mia;
import com.github.sokyranthedragon.mia.MiaCreativeTab;
import com.github.sokyranthedragon.mia.block.IAutoRegisterBlock;
import com.github.sokyranthedragon.mia.config.MiaConfig;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.BlockBush;
import net.minecraft.creativetab.CreativeTabs;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class BlockBaseBush extends BlockBush implements IAutoRegisterBlock
{
    public BlockBaseBush(String name, @Nullable CreativeTabs creativeTab)
    {
        setTranslationKey(name);
        setRegistryName(Mia.MODID, name);
    
        if (MiaConfig.miaCreativeTab)
            setCreativeTab(MiaCreativeTab.INSTANCE);
        else if (creativeTab != null)
            setCreativeTab(creativeTab);
    }
}
