package com.github.sokyranthedragon.mia.integrations.aether_continuation;

import com.github.sokyranthedragon.mia.config.MiaConfig;
import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.integrations.base.IBaseMod;
import com.github.sokyranthedragon.mia.integrations.base.IModIntegration;
import com.gildedgames.the_aether.addon.blocks.BlocksAetherAddon;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectEventProxy;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.aspects.AspectRegistryEvent;

import java.util.function.BiConsumer;

import static com.github.sokyranthedragon.mia.config.AetherConfig.*;
import static com.github.sokyranthedragon.mia.integrations.ModIds.*;
import static com.github.sokyranthedragon.mia.integrations.thaumcraft.ThaumcraftHelpers.transferAspects;
import static net.minecraftforge.oredict.OreDictionary.registerOre;

public class AetherContinuation implements IBaseMod
{
    @Override
    public void register(BiConsumer<ModIds, IModIntegration> modIntegration)
    {
        if (DUNGEON_TACTICS.isLoaded && enableDungeonTacticsIntegration)
            modIntegration.accept(DUNGEON_TACTICS, new DungeonTacticsAetherContinuationIntegration());
        if (THERMAL_EXPANSION.isLoaded && enableTeIntegration)
            modIntegration.accept(THERMAL_EXPANSION, new ThermalExpansionAetherContinuationIntegration());
        if (FUTURE_MC.isLoaded && enableFutureMcIntegration)
            modIntegration.accept(FUTURE_MC, new FutureMcAetherContinuationIntegration());
    }

    @Override
    public void init(FMLInitializationEvent event)
    {
        if (!aetherContinuationAdditionsEnabled)
            return;

        if (!MiaConfig.disableOreDict)
        {
            registerOre("trapdoorWood", new ItemStack(BlocksAetherAddon.skyroot_trapdoor));
            registerOre("buttonWood", new ItemStack(BlocksAetherAddon.skyroot_button));
            registerOre("pressurePlateWood", new ItemStack(BlocksAetherAddon.skyroot_pressure_plate));
        }
    }

    @Override
    public void registerAspects(AspectRegistryEvent event)
    {
        AspectEventProxy register = event.register;

        AspectList target = new AspectList().add(Aspect.MECHANISM, 5).add(Aspect.TRAP, 5);
        transferAspects(new ItemStack(BlocksAetherAddon.skyroot_door), target, register);
        transferAspects(new ItemStack(BlocksAetherAddon.zanite_door), target, register);
        target = new AspectList().add(Aspect.MOTION, 5);
        transferAspects(new ItemStack(BlocksAetherAddon.skyroot_trapdoor), target, register);
        transferAspects(new ItemStack(BlocksAetherAddon.zanite_trapdoor), target, register);
    }
}