package com.github.sokyranthedragon.mia.integrations.industrialforegoing;

import com.buuz135.industrial.api.IndustrialForegoingHelper;
import com.buuz135.industrial.api.extractor.ExtractorEntry;
import com.buuz135.industrial.api.recipe.LaserDrillEntry;
import com.buuz135.industrial.api.recipe.ProteinReactorEntry;
import com.buuz135.industrial.proxy.BlockRegistry;
import com.buuz135.industrial.tile.misc.FrosterTile;
import com.github.sokyranthedragon.mia.Mia;
import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.integrations.base.IBaseMod;
import com.github.sokyranthedragon.mia.integrations.base.IModIntegration;
import jackyy.integrationforegoing.util.Reference;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.ProgressManager;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;
import java.util.function.BiConsumer;

import static com.github.sokyranthedragon.mia.config.IndustrialForegoingConfiguration.*;

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
        if (ModIds.JER.isLoaded)
            modIntegration.accept(ModIds.JER, new JerIndustrialForegoingIntegration());
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
    public void preInit(FMLPreInitializationEvent event)
    {
        if (!modIntegrations.isEmpty())
        {
            ProgressManager.ProgressBar progressBar = ProgressManager.push("Industrial Foregoing preInit", modIntegrations.size());
            
            for (IIndustrialForegoingIntegration integration : modIntegrations)
            {
                progressBar.step(integration.getModId().modId);
                if (enableLaserDrillEntries && integration.loadLaserDrillEntries())
                    LaserDrillEntry.addOreFile(new ResourceLocation(ModIds.MIA.modId, integration.getModId().modId + "_ores.json"));
                else
                    deleteExistingLaserDrillFile(event.getModConfigurationDirectory(), integration.getModId().modId + "_ores.json");
            }
            
            ProgressManager.pop(progressBar);
        }
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
                if (enableFrosterRecipes && BlockRegistry.frosterBlock.isEnabled())
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
                
                if (enableProteinGeneratorEntries)
                {
                    for (ItemStack proteinGeneratorEntry : integration.getBasicProteinGeneratorEntries())
                        IndustrialForegoingHelper.addProteinReactorEntry(new ProteinReactorEntry(proteinGeneratorEntry));
                }
                
                if (enableLogLatexEntries)
                {
                    for (ExtractorEntry entry : integration.getLatexEntries())
                        IndustrialForegoingHelper.addWoodToLatex(entry);
                }
                
                integration.addGenericIntegrations();
            }
            
            ProgressManager.pop(progressBar);
        }
    }
    
    private static void deleteExistingLaserDrillFile(File dir, String fileName)
    {
        Path path = dir.toPath().resolve("laser_drill_ores");
        if (Files.exists(path))
        {
            File file = new File(path.toFile(), fileName + "_ores.json");
            if (!file.exists())
                return;
            
            if (file.delete())
                Reference.LOGGER.info("Deleted existing Laser Drill config file " + file.getName());
        }
    }
}