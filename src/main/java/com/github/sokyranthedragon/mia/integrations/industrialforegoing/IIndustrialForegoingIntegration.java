package com.github.sokyranthedragon.mia.integrations.industrialforegoing;

import com.buuz135.industrial.api.extractor.ExtractorEntry;
import com.github.sokyranthedragon.mia.integrations.base.IModIntegration;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.item.ItemStack;
import org.apache.logging.log4j.util.TriConsumer;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public interface IIndustrialForegoingIntegration extends IModIntegration
{
    default void addGenericIntegrations()
    {
    }
    
    default void addPostInitRecipes()
    {
    }
    
    default void addFrosterRecipe(TriConsumer<String, ItemStack, Integer> frosterEntry)
    {
    }
    
    default ItemStack[] getBasicProteinGeneratorEntries()
    {
        return new ItemStack[0];
    }
    
    default ExtractorEntry[] getLatexEntries()
    {
        return new ExtractorEntry[0];
    }
    
    default boolean loadLaserDrillEntries()
    {
        return false;
    }
}
