package com.github.exploder1531.mia.integrations.jei;

import com.github.exploder1531.mia.Mia;
import com.github.exploder1531.mia.integrations.base.IBaseMod;
import com.github.exploder1531.mia.integrations.base.IModIntegration;
import com.google.common.collect.Lists;
import net.minecraftforge.fml.common.ProgressManager;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;

import java.util.List;

public class Jei implements IBaseMod
{
    final List<IJeiIntegration> modIntegrations = Lists.newLinkedList();
    
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
            ProgressManager.ProgressBar progressBar = ProgressManager.push("JustEnoughItems registerRecipes - setting up", modIntegrations.size());
            for (IJeiIntegration integration : modIntegrations)
            {
                progressBar.step("JustEnoughItems registerRecipes - " + integration.getModId().modId);
                integration.registerRecipes();
            }
            ProgressManager.pop(progressBar);
        }
    }
}
