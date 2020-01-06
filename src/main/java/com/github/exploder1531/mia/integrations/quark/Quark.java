package com.github.exploder1531.mia.integrations.quark;

import com.github.exploder1531.mia.Mia;
import com.github.exploder1531.mia.integrations.ModIds;
import com.github.exploder1531.mia.integrations.base.IBaseMod;
import com.github.exploder1531.mia.integrations.base.IModIntegration;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.oredict.OreDictionary;
import vazkii.quark.base.module.Feature;
import vazkii.quark.base.module.ModuleLoader;
import vazkii.quark.building.feature.Trowel;
import vazkii.quark.misc.feature.AncientTomes;
import vazkii.quark.world.feature.Biotite;
import vazkii.quark.world.feature.Crabs;
import vazkii.quark.world.feature.Frogs;
import vazkii.quark.world.feature.UndergroundBiomes;

import java.lang.reflect.Field;
import java.util.*;
import java.util.function.BiConsumer;

import static com.github.exploder1531.mia.config.QuarkConfiguration.*;
import static com.github.exploder1531.mia.integrations.ModIds.*;

public class Quark implements IBaseMod
{
    private List<IQuarkIntegration> modIntegrations = new ArrayList<>();
    
    @Override
    public void register(BiConsumer<ModIds, IModIntegration> modIntegration)
    {
        if (enableXu2Integration && EXTRA_UTILITIES.isLoaded)
            modIntegration.accept(EXTRA_UTILITIES, new ExtraUtilsQuarkIntegration());
        if (enableTeIntegration && THERMAL_EXPANSION.isLoaded)
            modIntegration.accept(THERMAL_EXPANSION, new ThermalExpansionQuarkIntegration());
        if (enableJerIntegration && JER.isLoaded)
            modIntegration.accept(JER, new JerQuarkIntegration());
        if (enableDungeonTacticsIntegration && DUNGEON_TACTICS.isLoaded)
            modIntegration.accept(DUNGEON_TACTICS, new DungeonTacticsQuarkIntegration());
        if (enableFutureMcIntegration && FUTURE_MC.isLoaded)
            modIntegration.accept(FUTURE_MC, new FutureMcQuarkIntegration());
    }
    
    @Override
    public void addIntegration(IModIntegration integration)
    {
        if (!externalIntegrationsEnabled)
            return;
        
        if (integration instanceof IQuarkIntegration)
            modIntegrations.add((IQuarkIntegration) integration);
        else
            Mia.LOGGER.warn("Incorrect XU2 integration with id of " + integration.getModId() + ": " + integration.toString());
    }
    
    @Override
    public void preInit(FMLPreInitializationEvent event)
    {
        if (addAncientTomes && AncientTomes.ancient_tome != null && modIntegrations.size() > 0)
        {
            Feature feature = ModuleLoader.featureInstances.get(AncientTomes.class);
            if (feature.isEnabled())
            {
                try
                {
                    Field enchantNames = AncientTomes.class.getDeclaredField("enchantNames");
                    enchantNames.setAccessible(true);
                    
                    Object obj = enchantNames.get(feature);
                    if (obj instanceof String[])
                    {
                        Set<String> enchants = new HashSet<>();
                        Collections.addAll(enchants, ((String[]) obj));
                        
                        int size = enchants.size();
                        
                        for (IQuarkIntegration integration : modIntegrations)
                            enchants.addAll(integration.getAllowedAncientTomeEnchants());
                        
                        if (enchants.size() > size)
                            enchantNames.set(feature, enchants.toArray(new String[0]));
                    }
                } catch (NoSuchFieldException | IllegalAccessException e)
                {
                    Mia.LOGGER.error("Could not access Quark AncientTomes.enchantNames, no default ancient tome insertions will be added.");
                }
            }
        }
    }
    
    @Override
    public void init(FMLInitializationEvent event)
    {
        if (quarkAdditionsEnabled)
        {
            if (Trowel.trowel != null)
                FurnaceRecipes.instance().addSmeltingRecipe(new ItemStack(Trowel.trowel), new ItemStack(Items.IRON_NUGGET), 0.1f);
            
            if (Frogs.frogLeg != null)
            {
                OreDictionary.registerOre("foodFrograw", Frogs.frogLeg);
                OreDictionary.registerOre("foodFrogcooked", Frogs.cookedFrogLeg);
            }
            
            if (Crabs.crabLeg != null)
            {
                OreDictionary.registerOre("foodCrabraw", Crabs.crabLeg);
                OreDictionary.registerOre("foodCrabcooked", Crabs.cookedCrabLeg);
            }
            
            if (Biotite.biotite_ore != null)
                OreDictionary.registerOre("oreEnderBiotite", Biotite.biotite_ore);
            if (UndergroundBiomes.glowshroom != null)
                OreDictionary.registerOre("listAllmushroom", UndergroundBiomes.glowshroom);
        }
    }
}
