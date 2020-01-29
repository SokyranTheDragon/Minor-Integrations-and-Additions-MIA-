package com.github.sokyranthedragon.mia.integrations.botania;

import com.github.sokyranthedragon.mia.Mia;
import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.integrations.base.IBaseMod;
import com.github.sokyranthedragon.mia.integrations.base.IModIntegration;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

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
    public void init(FMLInitializationEvent event)
    {
        if (!modIntegrations.isEmpty())
        {
            for (IBotaniaIntegration integration : modIntegrations.values())
                integration.addRecipes();
        }
    }
}
