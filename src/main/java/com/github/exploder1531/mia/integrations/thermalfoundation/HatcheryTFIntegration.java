package com.github.exploder1531.mia.integrations.thermalfoundation;

import cofh.thermalfoundation.item.ItemFertilizer;
import cofh.thermalfoundation.item.ItemMaterial;
import com.gendeathrow.hatchery.core.config.ConfigLootHandler;
import com.github.exploder1531.mia.integrations.ModIds;
import com.github.exploder1531.mia.integrations.hatchery.IHatcheryIntegration;
import com.google.common.collect.Lists;

import javax.annotation.Nonnull;
import java.util.List;

public class HatcheryTFIntegration implements IHatcheryIntegration
{
    private final boolean modEnabled;
    
    HatcheryTFIntegration(boolean modEnabled)
    {
        this.modEnabled = modEnabled;
    }
    
    @Override
    public boolean isModEnabled()
    {
        return modEnabled;
    }
    
    @Override
    public int getCurrentLootVersion()
    {
        return 0;
    }
    
    @Nonnull
    @Override
    public List<ConfigLootHandler.ItemDrop> getDefaultEggDrops()
    {
        List<ConfigLootHandler.ItemDrop> drops = Lists.newLinkedList();
        
        // Ingots
        drops.add(new ConfigLootHandler.ItemDrop(ItemMaterial.ingotCopper, 4, 1, 1));
        drops.add(new ConfigLootHandler.ItemDrop(ItemMaterial.ingotTin, 4, 1, 1));
        drops.add(new ConfigLootHandler.ItemDrop(ItemMaterial.ingotSilver, 4, 1, 1));
        drops.add(new ConfigLootHandler.ItemDrop(ItemMaterial.ingotLead, 4, 1, 1));
        drops.add(new ConfigLootHandler.ItemDrop(ItemMaterial.ingotAluminum, 4, 1, 1));
        drops.add(new ConfigLootHandler.ItemDrop(ItemMaterial.ingotNickel, 4, 1, 1));
        drops.add(new ConfigLootHandler.ItemDrop(ItemMaterial.ingotPlatinum, 2, 1, 1));
        // Useless/unused? We might add it in Thermal Expansion as that's where it does have some use
        // (turning into coins and using in numismatic dynamo), but will be useful with other mods thanks to oredict
//        drops.add(new ConfigLootHandler.ItemDrop(ItemMaterial.ingotIridium, 2, 1, 1));
//        drops.add(new ConfigLootHandler.ItemDrop(ItemMaterial.ingotMithril, 2, 1, 1));
        drops.add(new ConfigLootHandler.ItemDrop(ItemMaterial.ingotSteel, 2, 1, 1));
        drops.add(new ConfigLootHandler.ItemDrop(ItemMaterial.ingotElectrum, 2, 1, 1));
        drops.add(new ConfigLootHandler.ItemDrop(ItemMaterial.ingotInvar, 2, 1, 1));
        drops.add(new ConfigLootHandler.ItemDrop(ItemMaterial.ingotBronze, 2, 1, 1));
        drops.add(new ConfigLootHandler.ItemDrop(ItemMaterial.ingotConstantan, 2, 1, 1));
        drops.add(new ConfigLootHandler.ItemDrop(ItemMaterial.ingotSignalum, 1, 1, 1));
        drops.add(new ConfigLootHandler.ItemDrop(ItemMaterial.ingotLumium, 1, 1, 1));
        drops.add(new ConfigLootHandler.ItemDrop(ItemMaterial.ingotEnderium, 1, 1, 1));
    
        // Phyto-gro
        drops.add(new ConfigLootHandler.ItemDrop(ItemFertilizer.fertilizerBasic, 10, 2, 4));
        drops.add(new ConfigLootHandler.ItemDrop(ItemFertilizer.fertilizerRich, 5, 2, 4));
        drops.add(new ConfigLootHandler.ItemDrop(ItemFertilizer.fertilizerFlux, 1, 2, 4));
        
        // Biomass/Bioblend
        drops.add(new ConfigLootHandler.ItemDrop(ItemMaterial.dustBiomass, 7, 1, 3));
        drops.add(new ConfigLootHandler.ItemDrop(ItemMaterial.dustBioblend, 7, 1, 3));
        drops.add(new ConfigLootHandler.ItemDrop(ItemMaterial.dustBiomassRich, 2, 1, 3));
        drops.add(new ConfigLootHandler.ItemDrop(ItemMaterial.dustBioblendRich, 2, 1, 3));
    
        // Slag
        drops.add(new ConfigLootHandler.ItemDrop(ItemMaterial.crystalSlag, 15, 1, 3));
        drops.add(new ConfigLootHandler.ItemDrop(ItemMaterial.crystalSlagRich, 10, 1, 3));
        drops.add(new ConfigLootHandler.ItemDrop(ItemMaterial.crystalCinnabar, 5, 1, 3));
        
        // Ore drops with liquid attached
        drops.add(new ConfigLootHandler.ItemDrop(ItemMaterial.crystalCrudeOil, 10, 1, 3));
        drops.add(new ConfigLootHandler.ItemDrop(ItemMaterial.crystalRedstone, 10, 1, 3));
        drops.add(new ConfigLootHandler.ItemDrop(ItemMaterial.crystalGlowstone, 10, 1, 3));
        drops.add(new ConfigLootHandler.ItemDrop(ItemMaterial.crystalEnder, 10, 1, 3));
        
        // Mob rods
        drops.add(new ConfigLootHandler.ItemDrop(ItemMaterial.rodBlizz, 5, 1, 2));
        drops.add(new ConfigLootHandler.ItemDrop(ItemMaterial.rodBlitz, 5, 1, 2));
        drops.add(new ConfigLootHandler.ItemDrop(ItemMaterial.rodBasalz, 5, 1, 2));
    
        // Other
        drops.add(new ConfigLootHandler.ItemDrop(ItemMaterial.fuelCoke, 5, 1, 3));
        drops.add(new ConfigLootHandler.ItemDrop(ItemMaterial.globRosin, 5, 1, 3));
        drops.add(new ConfigLootHandler.ItemDrop(ItemMaterial.globTar, 5, 1, 3));
    
        return drops;
    }
    
    @Nonnull
    @Override
    public String getModId()
    {
        return ModIds.THERMAL_FOUNDATION;
    }
}
