package com.github.exploder1531.mia.integrations.futuremc;

import com.github.exploder1531.mia.Mia;
import com.github.exploder1531.mia.integrations.ModIds;
import com.github.exploder1531.mia.integrations.base.IBaseMod;
import com.github.exploder1531.mia.integrations.base.IModIntegration;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.common.ProgressManager;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.oredict.OreDictionary;
import thedarkcolour.futuremc.block.BlockFurnaceAdvanced;
import thedarkcolour.futuremc.init.Init;
import thedarkcolour.futuremc.tile.TileCampfire;

import java.util.LinkedList;
import java.util.List;
import java.util.function.BiConsumer;

import static com.github.exploder1531.mia.config.FutureMcConfiguration.*;
import static com.github.exploder1531.mia.integrations.ModIds.*;
import static thedarkcolour.futuremc.block.BlockFurnaceAdvanced.Recipes.blastFurnaceRecipe;

public class FutureMc implements IBaseMod
{
    private final List<IFutureMcIntegration> modIntegrations = new LinkedList<>();
    
    @Override
    public void register(BiConsumer<ModIds, IModIntegration> modIntegration)
    {
        if (JER.isLoaded)
            modIntegration.accept(JER, new JerFutureMcIntegration());
        if (enableQuarkIntegration && QUARK.isLoaded)
            modIntegration.accept(QUARK, new QuarkFutureMcIntegration());
        if (enableTeIntegration && THERMAL_EXPANSION.isLoaded)
            modIntegration.accept(THERMAL_EXPANSION, new ThermalExpansionFutureMcIntegration());
    }
    
    @Override
    public void addIntegration(IModIntegration integration)
    {
        if (!externalIntegrationsEnabled)
            return;
        
        if (integration instanceof IFutureMcIntegration)
            modIntegrations.add((IFutureMcIntegration) integration);
        else
            Mia.LOGGER.warn("Incorrect FutureMC integration with id of " + integration.getModId() + ": " + integration.toString());
    }
    
    @Override
    public void init(FMLInitializationEvent event)
    {
        if (futureMcAdditionsEnabled)
        {
            OreDictionary.registerOre("blockHoney", Init.HONEY_BLOCK);
            OreDictionary.registerOre("honeycomb", Init.HONEY_COMB);
            OreDictionary.registerOre("listAllsugar", Init.HONEY_BOTTLE);
            OreDictionary.registerOre("dropHoney", Init.HONEY_BOTTLE);
            OreDictionary.registerOre("foodHoneydrop", Init.HONEY_BOTTLE);
        }
        
        if (!modIntegrations.isEmpty())
        {
            ProgressManager.ProgressBar progressBar = ProgressManager.push("FutureMc addRecipes - setting up", modIntegrations.size());
            for (IFutureMcIntegration integration : modIntegrations)
            {
                progressBar.step("FutureMc addRecipes - " + integration.getModId().modId);
                integration.addRecipes();
            }
            ProgressManager.pop(progressBar);
        }
    }
    
    public static void addFoodRecipe(ItemStack input, ItemStack output, int duration)
    {
        TileCampfire.Recipes.recipe(input, output, duration);
        BlockFurnaceAdvanced.Recipes.smokerRecipe(input, output);
    }
    
    public static void addFoodRecipe(ItemStack input, ItemStack output)
    {
        addFoodRecipe(input, output, 600);
    }
    
    public static void oreDictBlastFurnaceRecipe(ItemStack input, String output, int count)
    {
        NonNullList<ItemStack> ores = OreDictionary.getOres(output);
        if (!ores.isEmpty())
        {
            ItemStack ore = ores.get(0);
            blastFurnaceRecipe(input, new ItemStack(ore.getItem(), count, ore.getMetadata()));
        }
    }
    
    public static void oreDictBlastFurnaceRecipe(ItemStack input, String output)
    {
        oreDictBlastFurnaceRecipe(input, output, 1);
    }
    
    public static void oreDictBlastFurnaceRecipe(Item input, int meta, String output, int count)
    {
        oreDictBlastFurnaceRecipe(new ItemStack(input, 1, meta), output, count);
    }
    
    public static void oreDictBlastFurnaceRecipe(Item input, String output)
    {
        oreDictBlastFurnaceRecipe(new ItemStack(input), output);
    }
}
