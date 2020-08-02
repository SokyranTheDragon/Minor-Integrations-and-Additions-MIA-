package com.github.sokyranthedragon.mia.integrations.aether;

import com.github.sokyranthedragon.mia.config.MiaConfig;
import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.integrations.base.IBaseMod;
import com.github.sokyranthedragon.mia.integrations.base.IModIntegration;
import com.legacy.aether.blocks.BlocksAether;
import com.legacy.aether.items.ItemsAether;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

import java.util.function.BiConsumer;

import static com.github.sokyranthedragon.mia.config.AetherConfig.*;
import static com.github.sokyranthedragon.mia.integrations.ModIds.*;
import static net.minecraftforge.oredict.OreDictionary.registerOre;

public class Aether implements IBaseMod
{
    @Override
    public void register(BiConsumer<ModIds, IModIntegration> modIntegration)
    {
        if (FUTURE_MC.isLoaded && enableFutureMcIntegration)
            modIntegration.accept(FUTURE_MC, new FutureMcAetherIntegration());
        if (DUNGEON_TACTICS.isLoaded && enableDungeonTacticsIntegration)
            modIntegration.accept(DUNGEON_TACTICS, new DungeonTacticsAetherIntegration());
        if (THERMAL_EXPANSION.isLoaded && enableTeIntegration)
            modIntegration.accept(THERMAL_EXPANSION, new ThermalExpansionAetherIntegration());
        if (JER.isLoaded)
            modIntegration.accept(JER, new JerAetherIntegration());
        if (INDUSTRIAL_FOREGOING.isLoaded)
            modIntegration.accept(INDUSTRIAL_FOREGOING, new IndustrialForegoingAetherIntegration());
        if (CHISEL.isLoaded && enableChiselIntegration)
            modIntegration.accept(CHISEL, new ChiselAetherIntegration());
        if (EXTRA_UTILITIES.isLoaded && enableXu2Integration)
            modIntegration.accept(EXTRA_UTILITIES, new ExtraUtilsAetherIntegration());
    }
    
    @Override
    public void init(FMLInitializationEvent event)
    {
        if (!aetherAdditionsEnabled)
            return;
        
        BlocksAether.aether_dirt.setHarvestLevel("shovel", -1);
        BlocksAether.aether_grass.setHarvestLevel("shovel", -1);
        BlocksAether.quicksoil.setHarvestLevel("shovel", -1);
        
        if (!MiaConfig.disableOreDict)
        {
            registerOre("listAllMilk", new ItemStack(ItemsAether.skyroot_bucket, 1, 4));
            registerOre("slimeball", new ItemStack(ItemsAether.swetty_ball));
            registerOre("bookshelf", new ItemStack(BlocksAether.skyroot_bookshelf));
            registerOre("plankWood", new ItemStack(BlocksAether.skyroot_plank));
            registerOre("grass", new ItemStack(BlocksAether.aether_grass));
            registerOre("dirt", new ItemStack(BlocksAether.aether_dirt));
            
            registerOre("listAllBerry", new ItemStack(ItemsAether.blue_berry));
            registerOre("listAllFruit", new ItemStack(ItemsAether.blue_berry));
        }
    }
}
