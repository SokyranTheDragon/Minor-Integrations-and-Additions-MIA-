package com.github.sokyranthedragon.mia.integrations.jei.categories.orechidvacuam;

import com.github.sokyranthedragon.api.botania.MiaBotaniaAPI;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import vazkii.botania.client.integration.jei.orechid.OrechidRecipeWrapper;

import java.util.Map;

public class OrechidVacuamRecipeWrapper extends OrechidRecipeWrapper
{
    public OrechidVacuamRecipeWrapper(Map.Entry<String, Integer> entry)
    {
        super(entry);
    }
    
    @Override
    protected ItemStack getInputStack()
    {
        return new ItemStack(Blocks.END_STONE, 64);
    }
    
    @Override
    public Map<String, Integer> getOreWeights()
    {
        return MiaBotaniaAPI.oreWeightsEnd;
    }
}
