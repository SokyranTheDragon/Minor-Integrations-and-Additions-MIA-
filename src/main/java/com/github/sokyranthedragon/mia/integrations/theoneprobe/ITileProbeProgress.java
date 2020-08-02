package com.github.sokyranthedragon.mia.integrations.theoneprobe;

public interface ITileProbeProgress
{
    int getProgress();
    int getProgressMax();
    default Integer getProgressHexColor()
    {
        return null;
    }
    default Integer getProgressTintHexColor()
    {
        return null;
    }
    default String getProgressMessage()
    {
        return null;
    }
}
