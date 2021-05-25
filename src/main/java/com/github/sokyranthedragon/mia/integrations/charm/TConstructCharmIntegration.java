package com.github.sokyranthedragon.mia.integrations.charm;

import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.integrations.tconstruct.ITConstructIntegration;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import org.jetbrains.annotations.NotNull;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.materials.Material;
import slimeknights.tconstruct.shared.TinkerFluids;
import svenhjol.charm.Charm;
import svenhjol.charm.crafting.feature.Lantern;
import svenhjol.charm.crafting.feature.RottenFleshBlock;

class TConstructCharmIntegration implements ITConstructIntegration
{
    @Override
    public void init(FMLInitializationEvent event)
    {
        if (Charm.hasFeature(Lantern.class))
        {
            if (Lantern.ironLantern != null)
                TinkerRegistry.registerMelting(Lantern.ironLantern, TinkerFluids.iron, Material.VALUE_Nugget * 8);
            if (Lantern.goldLantern != null)
                TinkerRegistry.registerMelting(Lantern.goldLantern, TinkerFluids.gold, Material.VALUE_Nugget * 8);
        }
        
        if (Charm.hasFeature(RottenFleshBlock.class))
            TinkerRegistry.registerMelting(new ItemStack(RottenFleshBlock.block), TinkerFluids.blood, 40 * 9);
    }
    
    @NotNull
    @Override
    public ModIds getModId()
    {
        return ModIds.CHARM;
    }
}
