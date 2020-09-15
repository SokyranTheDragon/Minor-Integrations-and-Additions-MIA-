package com.github.sokyranthedragon.mia.integrations.aether;

import com.buuz135.industrial.api.extractor.ExtractorEntry;
import com.buuz135.industrial.api.recipe.LaserDrillEntry;
import com.buuz135.industrial.proxy.FluidsRegistry;
import com.gildedgames.the_aether.blocks.BlocksAether;
import com.gildedgames.the_aether.world.AetherWorld;
import com.github.sokyranthedragon.mia.Mia;
import com.github.sokyranthedragon.mia.config.AetherConfig;
import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.integrations.industrialforegoing.IIndustrialForegoingIntegration;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.item.ItemStack;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fluids.FluidStack;

import javax.annotation.ParametersAreNonnullByDefault;
import java.lang.reflect.Field;
import java.util.List;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
class IndustrialForegoingAetherIntegration implements IIndustrialForegoingIntegration
{
    @SuppressWarnings("unchecked")
    @Override
    public void addPostInitRecipes()
    {
        if (AetherConfig.blacklistAetherFromNonAetherOres)
        {
            try
            {
                Field whitelistField = LaserDrillEntry.class.getDeclaredField("whitelist");
                Field blacklistField = LaserDrillEntry.class.getDeclaredField("blacklist");
                
                whitelistField.setAccessible(true);
                blacklistField.setAccessible(true);
                
                for (List<LaserDrillEntry> laserDrillEntry : LaserDrillEntry.LASER_DRILL_ENTRIES)
                {
                    for (LaserDrillEntry drillEntry : laserDrillEntry)
                    {
                        Object objW = whitelistField.get(drillEntry);
                        Object objB = blacklistField.get(drillEntry);
                        
                        if (objW instanceof List && objB instanceof List)
                        {
                            List<Biome> whitelist = (List<Biome>) objW;
                            List<Biome> blacklist = (List<Biome>) objB;
                            
                            if (whitelist.isEmpty() && !blacklist.contains(AetherWorld.aether_biome))
                                blacklist.add(AetherWorld.aether_biome);
                        }
                    }
                }
            } catch (NoSuchFieldException | IllegalAccessException e)
            {
                Mia.LOGGER.error("Could not access Laser Drill whitelist/blacklist, no changes to be made.");
            }
        }
    }
    
    @Override
    public ExtractorEntry[] getLatexEntries()
    {
        return new ExtractorEntry[]
            {
                new ExtractorEntry(new ItemStack(BlocksAether.aether_log, 1, 0), new FluidStack(FluidsRegistry.LATEX, 1)),
                new ExtractorEntry(new ItemStack(BlocksAether.aether_log, 1, 0), new FluidStack(FluidsRegistry.LATEX, 1))
            };
    }
    
    @Override
    public boolean loadLaserDrillEntries()
    {
        return true;
    }
    
    @Override
    public ModIds getModId()
    {
        return ModIds.AETHER;
    }
}
