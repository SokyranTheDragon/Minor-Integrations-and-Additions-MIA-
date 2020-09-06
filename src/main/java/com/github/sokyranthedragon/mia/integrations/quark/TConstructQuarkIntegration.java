package com.github.sokyranthedragon.mia.integrations.quark;

import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.integrations.tconstruct.ITConstructIntegration;
import com.github.sokyranthedragon.mia.utilities.QuarkUtils;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.oredict.OreDictionary;
import slimeknights.mantle.util.RecipeMatch;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.materials.Material;
import slimeknights.tconstruct.library.smeltery.MeltingRecipe;
import slimeknights.tconstruct.shared.TinkerFluids;
import vazkii.quark.automation.feature.MetalButtons;
import vazkii.quark.automation.feature.ObsidianPressurePlate;
import vazkii.quark.automation.feature.PistonSpikes;
import vazkii.quark.building.feature.IronPlates;
import vazkii.quark.decoration.feature.GlassItemFrame;
import vazkii.quark.decoration.feature.Grate;
import vazkii.quark.tweaks.feature.GlassShards;
import vazkii.quark.world.entity.EntityArchaeologist;
import vazkii.quark.world.feature.Archaeologist;

@MethodsReturnNonnullByDefault
class TConstructQuarkIntegration implements ITConstructIntegration
{
    @Override
    public void init(FMLInitializationEvent event)
    {
        if (QuarkUtils.isFeatureEnabled(GlassShards.class))
            TinkerRegistry.registerMelting(new MeltingRecipe(RecipeMatch.of(new ItemStack(GlassShards.glass_shard, 1, OreDictionary.WILDCARD_VALUE), Material.VALUE_Glass / 4), TinkerFluids.glass));
        if (QuarkUtils.isFeatureEnabled(GlassItemFrame.class))
            TinkerRegistry.registerMelting(new MeltingRecipe(RecipeMatch.of(GlassItemFrame.glass_item_frame, (Material.VALUE_Glass * 6 / 16) * (8 / 2)), TinkerFluids.glass));
        if (QuarkUtils.isFeatureEnabled(IronPlates.class))
            TinkerRegistry.registerMelting(new MeltingRecipe(RecipeMatch.of(new ItemStack(IronPlates.iron_plate, 1, 1), Material.VALUE_Nugget * 3), TinkerFluids.iron));
        if (QuarkUtils.isFeatureEnabled(PistonSpikes.class))
            TinkerRegistry.registerMelting(new MeltingRecipe(RecipeMatch.of(PistonSpikes.iron_rod, Material.VALUE_Ingot * 2), TinkerFluids.iron));
        if (QuarkUtils.isFeatureEnabled(Grate.class))
            TinkerRegistry.registerMelting(new MeltingRecipe(RecipeMatch.of(Grate.grate, Material.VALUE_Ingot * 6 / 4), TinkerFluids.iron));
        if (QuarkUtils.isFeatureEnabled(MetalButtons.class))
        {
            if (MetalButtons.enableIron)
                TinkerRegistry.registerMelting(new MeltingRecipe(RecipeMatch.of(MetalButtons.iron_button, Material.VALUE_Ingot), TinkerFluids.iron));
            if (MetalButtons.enableGold)
                TinkerRegistry.registerMelting(new MeltingRecipe(RecipeMatch.of(MetalButtons.gold_button, Material.VALUE_Ingot), TinkerFluids.gold));
        }
        if (QuarkUtils.isFeatureEnabled(ObsidianPressurePlate.class))
            TinkerRegistry.registerMelting(new MeltingRecipe(RecipeMatch.of(ObsidianPressurePlate.obsidian_pressure_plate, Material.VALUE_SearedBlock * 2), TinkerFluids.obsidian));
        
        if (QuarkUtils.isFeatureEnabled(Archaeologist.class))
            TinkerRegistry.registerEntityMelting(EntityArchaeologist.class, new FluidStack(TinkerFluids.emerald, 6));
    }
    
    @Override
    public ModIds getModId()
    {
        return ModIds.QUARK;
    }
}
