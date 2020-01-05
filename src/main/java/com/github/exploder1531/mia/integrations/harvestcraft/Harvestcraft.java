package com.github.exploder1531.mia.integrations.harvestcraft;

import com.github.exploder1531.mia.integrations.ModIds;
import com.github.exploder1531.mia.integrations.base.IBaseMod;
import com.github.exploder1531.mia.integrations.base.IModIntegration;
import com.pam.harvestcraft.HarvestCraft;
import com.pam.harvestcraft.blocks.FruitRegistry;
import com.pam.harvestcraft.item.ItemRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.oredict.OreDictionary;

import java.util.function.BiConsumer;

import static com.github.exploder1531.mia.config.HarvestcraftConfiguration.enableTeIntegration;
import static com.github.exploder1531.mia.config.HarvestcraftConfiguration.harvestcraftAdditionsEnabled;
import static com.github.exploder1531.mia.integrations.ModIds.*;

public class Harvestcraft implements IBaseMod
{
    @Override
    public void register(BiConsumer<ModIds, IModIntegration> modIntegration)
    {
        if (enableTeIntegration && THERMAL_EXPANSION.isLoaded)
            modIntegration.accept(THERMAL_EXPANSION, new ThermalExpansionHarvestcraftIntegration());
        if (JER.isLoaded)
            modIntegration.accept(JER, new JerHarvestcraftIntegration());
        if (JEI.isLoaded)
            modIntegration.accept(JEI, new JeiHarvestcraftIntegration());
        if (FUTURE_MC.isLoaded)
            modIntegration.accept(FUTURE_MC, new FutureMcHarvestcraftIntegration());
    }
    
    @Override
    public void init(FMLInitializationEvent event)
    {
        if (!harvestcraftAdditionsEnabled)
            return;
        
        if (HarvestCraft.config.enableTofuAsMeatInRecipes)
            OreDictionary.registerOre("egg", ItemRegistry.rawtofeegItem);
        
        OreDictionary.registerOre("honeycomb", ItemRegistry.honeycombItem);
        
        OreDictionary.registerOre("treeSapling", FruitRegistry.getSapling(FruitRegistry.SPIDERWEB));
        OreDictionary.registerOre("treeSapling", FruitRegistry.getSapling(FruitRegistry.AVOCADO));
        OreDictionary.registerOre("treeSapling", FruitRegistry.getSapling(FruitRegistry.WALNUT));
    }
}
