package com.github.exploder1531.mia.integrations.theoneprobe;

import com.google.common.base.Function;
import mcjty.theoneprobe.api.ITheOneProbe;

import javax.annotation.Nullable;

public class GetTheOneProbe implements Function<ITheOneProbe, Void>
{
    @Nullable
    @Override
    public Void apply(@Nullable ITheOneProbe probe)
    {
        if (probe != null)
            probe.registerProvider(new ProgressProvider());
        
        return null;
    }
}
