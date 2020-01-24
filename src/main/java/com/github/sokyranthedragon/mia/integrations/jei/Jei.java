package com.github.sokyranthedragon.mia.integrations.jei;

import com.github.sokyranthedragon.mia.Mia;
import com.github.sokyranthedragon.mia.integrations.base.IBaseMod;
import com.github.sokyranthedragon.mia.integrations.base.IModIntegration;
import net.minecraftforge.fml.common.ProgressManager;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;

import java.util.LinkedList;
import java.util.List;

public class Jei implements IBaseMod
{
    final List<IJeiIntegration> modIntegrations = new LinkedList<>();
    
    public Jei()
    {
        if (MiaJeiPlugin.jeiIntegration != null)
            throw new IllegalStateException("Cannot create Jei IBaseMod more than twice!");
        MiaJeiPlugin.jeiIntegration = this;
    }
    
    @Override
    public void addIntegration(IModIntegration integration)
    {
        if (integration instanceof IJeiIntegration)
            modIntegrations.add((IJeiIntegration) integration);
        else
            Mia.LOGGER.warn("Incorrect JEI integration with id of " + integration.getModId() + ": " + integration.toString());
    }
    
    @Override
    public void postInit(FMLPostInitializationEvent event)
    {
        if (!modIntegrations.isEmpty())
        {
            ProgressManager.ProgressBar progressBar = ProgressManager.push("JustEnoughItems registerRecipes", modIntegrations.size());
            for (IJeiIntegration integration : modIntegrations)
            {
                progressBar.step(integration.getModId().modId);
                integration.registerRecipes();
            }
            ProgressManager.pop(progressBar);
        }
    }
}
