package com.github.sokyranthedragon.mia.integrations.industrialforegoing;

import com.buuz135.industrial.proxy.BlockRegistry;
import com.buuz135.industrial.tile.misc.FrosterTile;
import com.github.sokyranthedragon.mia.Mia;
import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.integrations.base.IBaseMod;
import com.github.sokyranthedragon.mia.integrations.base.IModIntegration;
import jackyy.integrationforegoing.util.Reference;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.ProgressManager;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

import java.util.LinkedList;
import java.util.List;
import java.util.function.BiConsumer;

public class IndustrialForegoing implements IBaseMod
{
    private final List<IIndustrialForegoingIntegration> modIntegrations = new LinkedList<>();
    final List<ItemStack> registeredFrosterItems = new LinkedList<>();
    
    @Override
    public void register(BiConsumer<ModIds, IModIntegration> modIntegration)
    {
        if (!Loader.isModLoaded(Reference.MODID))
            Mia.LOGGER.warn("Integration Foregoing is not detected - integrations in MIA were created with Integration Foregoing in mind, and therefore they might feel incomplete otherwise. It's highly recommended to install it as well!");
        
        if (ModIds.JEI.isLoaded)
            modIntegration.accept(ModIds.JEI, new JeiIndustrialForegoingIntegration(this));
    }
    
    @Override
    public void addIntegration(IModIntegration integration)
    {
        // TODO: Check if enabled
        
        if (integration instanceof IIndustrialForegoingIntegration)
            modIntegrations.add((IIndustrialForegoingIntegration) integration);
        else
            Mia.LOGGER.warn("Incorrect Industrial Foregoing integration with id of " + integration.getModId() + ": " + integration.toString());
    }
    
    @Override
    public void init(FMLInitializationEvent event)
    {
        if (!modIntegrations.isEmpty())
        {
            ProgressManager.ProgressBar progressBar = ProgressManager.push("Industrial Foregoing init", modIntegrations.size());
            
            for (IIndustrialForegoingIntegration integration : modIntegrations)
            {
                progressBar.step(integration.getModId().modId);
                if (BlockRegistry.frosterBlock.isEnabled())
                {
                    integration.addFrosterRecipe((name, item, value) ->
                    {
                        if (!item.isEmpty() && value >= 0 && value <= 8000)
                        {
                            EnumHelper.addEnum(FrosterTile.FrosterResult.class, name, new Class<?>[]{ ItemStack.class, int.class }, item, value);
                            registeredFrosterItems.add(item);
                        }
                        else
                            Mia.LOGGER.warn("Incorrect Froster entry, liquid= " + value + ", item=" + item);
                    });
                }
                integration.addGenericIntegrations();
            }
            
            ProgressManager.pop(progressBar);
        }
    }
}