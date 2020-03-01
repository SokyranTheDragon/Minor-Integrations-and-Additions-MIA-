package com.github.sokyranthedragon.mia.integrations.industrialforegoing;

import com.buuz135.industrial.jei.machineproduce.MachineProduceWrapper;
import com.buuz135.industrial.proxy.BlockRegistry;
import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.integrations.jei.IJeiIntegration;
import mcp.MethodsReturnNonnullByDefault;
import mezz.jei.api.IModRegistry;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Collection;
import java.util.stream.Collectors;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
class JeiIndustrialForegoingIntegration implements IJeiIntegration
{
    private IndustrialForegoing industrialForegoing;
    
    public JeiIndustrialForegoingIntegration(IndustrialForegoing industrialForegoing)
    {
        this.industrialForegoing = industrialForegoing;
    }
    
    @Override
    public void register(IModRegistry registry, Collection<String> registeredCategories)
    {
        if (industrialForegoing.registeredFrosterItems.size() > 0)
            registry.addRecipes(
                    industrialForegoing.registeredFrosterItems.stream().map(stack -> new MachineProduceWrapper(BlockRegistry.frosterBlock, stack)).collect(Collectors.toList()),
                    "machine_produce_category");
    }
    
    @Override
    public ModIds getModId()
    {
        return ModIds.INDUSTRIAL_FOREGOING;
    }
}
