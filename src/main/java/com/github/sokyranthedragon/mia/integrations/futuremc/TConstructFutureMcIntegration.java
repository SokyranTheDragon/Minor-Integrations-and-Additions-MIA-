package com.github.sokyranthedragon.mia.integrations.futuremc;

import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.integrations.tconstruct.ITConstructIntegration;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import org.jetbrains.annotations.NotNull;
import slimeknights.mantle.util.RecipeMatch;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.materials.Material;
import slimeknights.tconstruct.library.smeltery.MeltingRecipe;
import slimeknights.tconstruct.shared.TinkerFluids;
import thedarkcolour.futuremc.registry.FBlocks;

class TConstructFutureMcIntegration implements ITConstructIntegration
{
    @Override
    public void init(FMLInitializationEvent event)
    {
        TinkerRegistry.registerMelting(new MeltingRecipe(RecipeMatch.of(new ItemStack(FBlocks.INSTANCE.getSMITHING_TABLE()), Material.VALUE_Ingot * 2), TinkerFluids.iron));
        TinkerRegistry.registerMelting(new MeltingRecipe(RecipeMatch.of(new ItemStack(FBlocks.INSTANCE.getSTONECUTTER()), Material.VALUE_Ingot), TinkerFluids.iron));
        TinkerRegistry.registerMelting(new MeltingRecipe(RecipeMatch.of(new ItemStack(FBlocks.BLAST_FURNACE), Material.VALUE_Ingot * 5), TinkerFluids.iron));
        
        TinkerRegistry.registerMelting(new MeltingRecipe(RecipeMatch.of(new ItemStack(FBlocks.INSTANCE.getLANTERN()),  Material.VALUE_Nugget * 8), TinkerFluids.iron));
        TinkerRegistry.registerMelting(new MeltingRecipe(RecipeMatch.of(new ItemStack(FBlocks.INSTANCE.getSOUL_FIRE_LANTERN()),  Material.VALUE_Nugget * 8), TinkerFluids.iron));
        
        TinkerRegistry.registerMelting(new MeltingRecipe(RecipeMatch.of(new ItemStack(FBlocks.INSTANCE.getBELL()),  Material.VALUE_Ingot * 4), TinkerFluids.gold));
    }
    
    @NotNull
    @Override
    public ModIds getModId()
    {
        return ModIds.FUTURE_MC;
    }
}
