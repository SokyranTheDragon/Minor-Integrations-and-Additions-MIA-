package com.github.exploder1531.mia.integrations.theoneprobe;

import com.google.common.base.Function;
import mcjty.theoneprobe.api.ITheOneProbe;

import javax.annotation.Nullable;

@SuppressWarnings("unused")
public class GetTheOneProbe implements Function<ITheOneProbe, Void>
{
    @Nullable
    @Override
    public Void apply(@Nullable ITheOneProbe probe)
    {
        if (probe != null)
        {
            ProgressProvider progressProvider = new ProgressProvider();
    
            probe.registerProvider(progressProvider);
            probe.registerEntityProvider(progressProvider);
        }
        
        return null;
    }
}
