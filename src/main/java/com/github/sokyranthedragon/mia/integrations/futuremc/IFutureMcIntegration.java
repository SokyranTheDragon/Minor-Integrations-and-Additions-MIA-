package com.github.sokyranthedragon.mia.integrations.futuremc;

import com.github.sokyranthedragon.mia.integrations.base.IModIntegration;
import net.minecraft.block.state.IBlockState;

public interface IFutureMcIntegration extends IModIntegration
{
    default void addRecipes()
    { }
    
    default IBlockState[] registerPollinationFlowers()
    {
        return new IBlockState[0];
    }
}