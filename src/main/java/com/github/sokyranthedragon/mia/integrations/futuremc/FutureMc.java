package com.github.sokyranthedragon.mia.integrations.futuremc;

import com.github.sokyranthedragon.mia.Mia;
import com.github.sokyranthedragon.mia.config.FutureMcConfiguration;
import com.github.sokyranthedragon.mia.config.MiaConfig;
import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.integrations.base.IBaseMod;
import com.github.sokyranthedragon.mia.integrations.base.IModIntegration;
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

import static thedarkcolour.futuremc.block.BlockFurnaceAdvanced.Recipes.blastFurnaceRecipe;

public class FutureMc implements IBaseMod
{
    private final List<IFutureMcIntegration> modIntegrations = new LinkedList<>();
    
    @Override
    public void register(BiConsumer<ModIds, IModIntegration> modIntegration)
    {
        if (ModIds.JER.isLoaded)
            modIntegration.accept(ModIds.JER, new JerFutureMcIntegration());
        if (FutureMcConfiguration.enableQuarkIntegration && ModIds.QUARK.isLoaded)
            modIntegration.accept(ModIds.QUARK, new QuarkFutureMcIntegration());
        if (FutureMcConfiguration.enableTeIntegration && ModIds.THERMAL_EXPANSION.isLoaded)
            modIntegration.accept(ModIds.THERMAL_EXPANSION, new ThermalExpansionFutureMcIntegration());
        if (ModIds.HATCHERY.isLoaded)
            modIntegration.accept(ModIds.HATCHERY, new HatcheryFutureMcIntegration(FutureMcConfiguration.enableHatcheryIntegration));
        if (FutureMcConfiguration.enableHarvestcraftIntegration && ModIds.HARVESTCRAFT.isLoaded)
            modIntegration.accept(ModIds.HARVESTCRAFT, new HarvestcraftFutureMcIntegration());
    }
    
    @Override
    public void addIntegration(IModIntegration integration)
    {
        if (!FutureMcConfiguration.externalIntegrationsEnabled)
            return;
        
        if (integration instanceof IFutureMcIntegration)
            modIntegrations.add((IFutureMcIntegration) integration);
        else
            Mia.LOGGER.warn("Incorrect FutureMC integration with id of " + integration.getModId() + ": " + integration.toString());
    }
    
    @Override
    public void init(FMLInitializationEvent event)
    {
        if (FutureMcConfiguration.futureMcAdditionsEnabled && !MiaConfig.disableOreDict)
        {
            OreDictionary.registerOre("blockHoney", Init.HONEY_BLOCK);
            OreDictionary.registerOre("honeycomb", Init.HONEY_COMB);
            OreDictionary.registerOre("listAllsugar", Init.HONEY_BOTTLE);
            OreDictionary.registerOre("dropHoney", Init.HONEY_BOTTLE);
            OreDictionary.registerOre("foodHoneydrop", Init.HONEY_BOTTLE);
        }
        
        if (!modIntegrations.isEmpty() && !MiaConfig.disableAllRecipes)
        {
            ProgressManager.ProgressBar progressBar = ProgressManager.push("FutureMc addRecipes", modIntegrations.size());
            for (IFutureMcIntegration integration : modIntegrations)
            {
                progressBar.step(integration.getModId().modId);
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
    
    public static void oreDictBlastFurnaceRecipe(ItemStack input, int count, String... outputs)
    {
        for (String output : outputs)
        {
            NonNullList<ItemStack> ores = OreDictionary.getOres(output);
            if (!ores.isEmpty())
            {
                ItemStack ore = ores.get(0);
                blastFurnaceRecipe(input, new ItemStack(ore.getItem(), count, ore.getMetadata()));
                return;
            }
        }
    }
    
    public static void oreDictBlastFurnaceRecipe(ItemStack input, String... outputs)
    {
        oreDictBlastFurnaceRecipe(input, 1, outputs);
    }
    
    public static void oreDictBlastFurnaceRecipe(Item input, int meta, int count, String... outputs)
    {
        oreDictBlastFurnaceRecipe(new ItemStack(input, 1, meta), count, outputs);
    }
    
    public static void oreDictBlastFurnaceRecipe(Item input, String... outputs)
    {
        oreDictBlastFurnaceRecipe(new ItemStack(input), outputs);
    }
}
