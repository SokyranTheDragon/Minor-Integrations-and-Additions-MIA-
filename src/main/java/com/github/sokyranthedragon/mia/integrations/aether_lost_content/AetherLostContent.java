package com.github.sokyranthedragon.mia.integrations.aether_lost_content;

import com.github.sokyranthedragon.mia.config.MiaConfig;
import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.integrations.base.IBaseMod;
import com.github.sokyranthedragon.mia.integrations.base.IModIntegration;
import com.legacy.lostaether.blocks.BlocksLostAether;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

import java.util.function.BiConsumer;

import static com.github.sokyranthedragon.mia.config.AetherConfig.*;
import static com.github.sokyranthedragon.mia.integrations.ModIds.*;
import static net.minecraftforge.oredict.OreDictionary.registerOre;

public class AetherLostContent implements IBaseMod
{
    @Override
    public void register(BiConsumer<ModIds, IModIntegration> modIntegration)
    {
        if (FUTURE_MC.isLoaded && enableFutureMcIntegration)
            modIntegration.accept(FUTURE_MC, new FutureMcAetherLostContentIntegration());
        if (THERMAL_EXPANSION.isLoaded && enableTeIntegration)
            modIntegration.accept(THERMAL_EXPANSION, new ThermalExpansionAetherLostContentIntegration());
        if (enableJerIntegration && JER.isLoaded)
            modIntegration.accept(JER, new JerAetherLostContentIntegration());
    }
    
    @Override
    public void init(FMLInitializationEvent event)
    {
        if (!aetherLostContentAdditionsEnabled)
            return;
        
        if (!MiaConfig.disableOreDict)
            registerOre("treeSapling", new ItemStack(BlocksLostAether.crystal_sapling));
    }
}