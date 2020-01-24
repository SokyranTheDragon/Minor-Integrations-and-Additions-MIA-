package com.github.sokyranthedragon.mia.integrations.base;

import com.github.sokyranthedragon.mia.integrations.ModIds;
import mcp.MethodsReturnNonnullByDefault;

@MethodsReturnNonnullByDefault
public interface IModIntegration
{
    ModIds getModId();
}
