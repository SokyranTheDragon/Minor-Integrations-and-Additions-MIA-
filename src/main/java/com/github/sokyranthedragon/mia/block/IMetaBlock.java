package com.github.sokyranthedragon.mia.block;

import com.github.sokyranthedragon.mia.block.base.BlockBaseMeta;
import mcp.MethodsReturnNonnullByDefault;

@MethodsReturnNonnullByDefault
public interface IMetaBlock
{
    int getMaxMeta();
    
    String getNameFromMeta(int i);
    
    default String getVariantName()
    {
        return BlockBaseMeta.DEFAULT_VARIANT_NAME;
    }
}
