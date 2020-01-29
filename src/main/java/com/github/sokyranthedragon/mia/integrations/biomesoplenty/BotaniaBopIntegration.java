package com.github.sokyranthedragon.mia.integrations.biomesoplenty;

import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.integrations.botania.IBotaniaIntegration;
import mcp.MethodsReturnNonnullByDefault;
import vazkii.botania.api.BotaniaAPI;

@MethodsReturnNonnullByDefault
class BotaniaBopIntegration implements IBotaniaIntegration
{
    @Override
    public void addRecipes()
    {
        BotaniaAPI.addOreWeight("orePeridot", 1250);
        BotaniaAPI.addOreWeight("oreTopaz", 1250);
        BotaniaAPI.addOreWeight("oreTanzanite", 1250);
        BotaniaAPI.addOreWeight("oreMalachite", 1250);
    }
    
    @Override
    public ModIds getModId()
    {
        return ModIds.BIOMES_O_PLENTY;
    }
}
