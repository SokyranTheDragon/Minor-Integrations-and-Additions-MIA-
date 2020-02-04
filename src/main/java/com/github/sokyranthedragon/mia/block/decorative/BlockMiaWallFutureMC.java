package com.github.sokyranthedragon.mia.block.decorative;

import com.github.sokyranthedragon.mia.MiaCreativeTab;
import com.github.sokyranthedragon.mia.config.MiaConfig;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.Block;
import thedarkcolour.futuremc.block.BlockWall;

import javax.annotation.ParametersAreNonnullByDefault;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class BlockMiaWallFutureMC extends BlockWall
{
    public BlockMiaWallFutureMC(String variant)
    {
        super(variant);
        
        if (MiaConfig.miaCreativeTab)
            setCreativeTab(MiaCreativeTab.INSTANCE);
    }
    
    @Override
    public Block setTranslationKey(String key)
    {
        return super.setTranslationKey(key.substring("minecraftfuture.".length()));
    }
    
    
}
