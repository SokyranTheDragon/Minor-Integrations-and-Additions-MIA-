package com.github.sokyranthedragon.mia.integrations.botania;

import com.github.sokyranthedragon.mia.Mia;
import com.github.sokyranthedragon.mia.block.BlockBotaniaSpecialFlower;
import com.github.sokyranthedragon.mia.config.MiaConfig;
import com.github.sokyranthedragon.mia.core.MiaBlocks;
import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.integrations.base.IBaseMod;
import com.github.sokyranthedragon.mia.integrations.base.IModIntegration;
import com.github.sokyranthedragon.mia.integrations.botania.crafting.MiaPetalRecipes;
import com.github.sokyranthedragon.mia.integrations.botania.lexicon.MiaLexiconData;
import com.github.sokyranthedragon.mia.integrations.botania.subtile.SubTileOrechidVacuam;
import com.github.sokyranthedragon.mia.integrations.botania.wiki.PartialSimpleWikiProvider;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.ProgressManager;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.BotaniaAPIClient;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

import static com.github.sokyranthedragon.mia.config.BotaniaConfiguration.*;
import static com.github.sokyranthedragon.mia.integrations.ModIds.*;

public class Botania implements IBaseMod
{
    private final Map<ModIds, IBotaniaIntegration> modIntegrations = new HashMap<>();
    
    @Override
    public void register(BiConsumer<ModIds, IModIntegration> modIntegration)
    {
        if (enableDungeonTacticsIntegration && DUNGEON_TACTICS.isLoaded)
            modIntegration.accept(DUNGEON_TACTICS, new DungeonTacticsBotaniaIntegration());
        if (enableTeIntegration && THERMAL_EXPANSION.isLoaded)
            modIntegration.accept(THERMAL_EXPANSION, new ThermalExpansionBotaniaIntegration());
        if (JEI.isLoaded)
            modIntegration.accept(JEI, new JeiBotaniaIntegration());
        if (JER.isLoaded)
            modIntegration.accept(JER, new JerBotaniaIntegration());
        if (enableFutureMcIntegration && FUTURE_MC.isLoaded)
            modIntegration.accept(FUTURE_MC, new FutureMcBotaniaIntegration());
        if (HATCHERY.isLoaded)
            modIntegration.accept(HATCHERY, new HatcheryBotaniaIntegration(enableHatcheryIntegration));
        if (enableChiselIntegration && CHISEL.isLoaded)
            modIntegration.accept(CHISEL, new ChiselBotaniaIntegration());
    }
    
    @Override
    public void addIntegration(IModIntegration integration)
    {
        if (!externalIntegrationsEnabled)
            return;
        
        if (integration instanceof IBotaniaIntegration)
            modIntegrations.put(integration.getModId(), (IBotaniaIntegration) integration);
        else
            Mia.LOGGER.warn("Incorrect Botania integration with id of " + integration.getModId() + ": " + integration.toString());
    }
    
    @Override
    public void preInit(FMLPreInitializationEvent event)
    {
        BotaniaAPI.registerModWiki(MIA.modId, new PartialSimpleWikiProvider());
    }
    
    @Override
    public void init(FMLInitializationEvent event)
    {
        if (!modIntegrations.isEmpty() && !MiaConfig.disableAllRecipes)
        {
            ProgressManager.ProgressBar progressBar = ProgressManager.push("Botania addRecipes", modIntegrations.size());
            for (IBotaniaIntegration integration : modIntegrations.values())
            {
                progressBar.step(integration.getModId().modId);
                integration.addRecipes();
            }
            ProgressManager.pop(progressBar);
        }
        
        if (botaniaAdditionsEnabled)
        {
            MiaPetalRecipes.init();
            MiaLexiconData.init();
        }
    }
    
    @Override
    public void registerItems(RegistryEvent.Register<Item> event)
    {
        if (botaniaAdditionsEnabled)
        {
            BotaniaAPI.registerSubTile("orechidVacuam", SubTileOrechidVacuam.class);
            
            MiaBlocks.blockBotaniaSpecialFlower = new BlockBotaniaSpecialFlower();
            MiaBlocks.blocks.add(MiaBlocks.blockBotaniaSpecialFlower);
        }
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void registerRenders(ModelRegistryEvent event)
    {
        if (botaniaAdditionsEnabled)
            BotaniaAPIClient.registerSubtileModel(SubTileOrechidVacuam.class, new ModelResourceLocation(BOTANIA.modId + ":orechidVacuam"));
    }
}
