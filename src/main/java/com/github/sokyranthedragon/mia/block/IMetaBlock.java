package com.github.sokyranthedragon.mia.block;

import com.github.sokyranthedragon.mia.block.base.BlockBaseMeta;
import mcp.MethodsReturnNonnullByDefault;

import javax.annotation.Nullable;

@MethodsReturnNonnullByDefault
public interface IMetaBlock
{
    int getMaxMeta();
    
    String getNameFromMeta(int i);
    
    @Nullable
    default String getVariantName()
    {
        return BlockBaseMeta.DEFAULT_VARIANT_NAME;
    }
}
