package com.github.sokyranthedragon.mia.integrations.base;

import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.fml.relauncher.Side;

import java.util.HashSet;

public class LootTableIntegrator
{
    private HashSet<LootTableListener> integrations = new HashSet<>();
    
    public void registerLootTableIntegration(ModIntegrator integrator, Side side)
    {
        integrator.registerLootTableListeners(integrations, side);
    }
    
    public void lootTableLoad(LootTableLoadEvent event)
    {
        for (LootTableListener integration : integrations)
            integration.lootTableLoad(event);
    }
    
    @FunctionalInterface
    public interface LootTableListener
    {
        void lootTableLoad(LootTableLoadEvent event);
    }
}
