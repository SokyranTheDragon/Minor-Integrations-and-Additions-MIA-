package com.github.sokyranthedragon.mia.block;

import mcp.MethodsReturnNonnullByDefault;

@MethodsReturnNonnullByDefault
public interface IMetaBlock
{
    int getMaxMeta();
    
    String getNameFromMeta(int i);
    
    String getVariantName();
}
