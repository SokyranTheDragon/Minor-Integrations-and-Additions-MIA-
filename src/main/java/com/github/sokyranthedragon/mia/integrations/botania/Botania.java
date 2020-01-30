package com.github.sokyranthedragon.mia.integrations.botania;

import com.github.sokyranthedragon.mia.Mia;
import com.github.sokyranthedragon.mia.block.BlockBotaniaSpecialFlower;
import com.github.sokyranthedragon.mia.core.MiaBlocks;
import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.integrations.base.IBaseMod;
import com.github.sokyranthedragon.mia.integrations.base.IModIntegration;
import com.github.sokyranthedragon.mia.integrations.botania.crafting.MiaPetalRecipes;
import com.github.sokyranthedragon.mia.integrations.botania.lexicon.MiaLexiconData;
import com.github.sokyranthedragon.mia.integrations.botania.subtile.SubTileOrechidVacuam;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.BotaniaAPIClient;
import vazkii.botania.api.wiki.SimpleWikiProvider;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

import static com.github.sokyranthedragon.mia.config.BotaniaConfiguration.*;

public class Botania implements IBaseMod
{
    private final Map<ModIds, IBotaniaIntegration> modIntegrations = new HashMap<>();
    
    @Override
    public void register(BiConsumer<ModIds, IModIntegration> modIntegration)
    {
        if (enableDungeonTacticsIntegration && ModIds.DUNGEON_TACTICS.isLoaded)
            modIntegration.accept(ModIds.DUNGEON_TACTICS, new DungeonTacticsBotaniaIntegration());
        if (enableTeIntegration && ModIds.THERMAL_EXPANSION.isLoaded)
            modIntegration.accept(ModIds.THERMAL_EXPANSION, new ThermalExpansionBotaniaIntegration());
        if (ModIds.JEI.isLoaded)
            modIntegration.accept(ModIds.JEI, new JeiBotaniaIntegration());
        if (ModIds.JER.isLoaded)
            modIntegration.accept(ModIds.JER, new JerBotaniaIntegration());
        if (enableFutureMcIntegration && ModIds.FUTURE_MC.isLoaded)
            modIntegration.accept(ModIds.FUTURE_MC, new FutureMcBotaniaIntegration());
        if (ModIds.HATCHERY.isLoaded)
            modIntegration.accept(ModIds.HATCHERY, new HatcheryBotaniaIntegration(enableHatcheryIntegration));
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
        BotaniaAPI.registerModWiki(ModIds.MIA.modId, new SimpleWikiProvider("MIA Wiki", "https://github.com/SokyranTheDragon/Minor-Integrations-and-Additions-MIA-/wiki/%s", "-"));
    }
    
    @Override
    public void init(FMLInitializationEvent event)
    {
        if (!modIntegrations.isEmpty())
        {
            for (IBotaniaIntegration integration : modIntegrations.values())
                integration.addRecipes();
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
            BotaniaAPIClient.registerSubtileModel(SubTileOrechidVacuam.class, new ModelResourceLocation(ModIds.BOTANIA.modId + ":orechidVacuam"));
    }
}
