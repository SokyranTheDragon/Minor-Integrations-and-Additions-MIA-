package com.github.sokyranthedragon.mia.integrations.futuremc;

import com.github.sokyranthedragon.mia.Mia;
import com.github.sokyranthedragon.mia.config.FutureMcConfiguration;
import com.github.sokyranthedragon.mia.config.MiaConfig;
import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.integrations.base.IBaseMod;
import com.github.sokyranthedragon.mia.integrations.base.IModIntegration;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.ProgressManager;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.oredict.OreDictionary;
import thedarkcolour.futuremc.api.BeePollinationHandler;
import thedarkcolour.futuremc.api.BeePollinationTargetsJVM;
import thedarkcolour.futuremc.entity.bee.EntityBee;
import thedarkcolour.futuremc.recipe.campfire.CampfireRecipes;
import thedarkcolour.futuremc.recipe.furnace.BlastFurnaceRecipes;
import thedarkcolour.futuremc.recipe.furnace.SmokerRecipes;
import thedarkcolour.futuremc.recipe.stonecutter.StonecutterRecipes;
import thedarkcolour.futuremc.registry.FBlocks;
import thedarkcolour.futuremc.registry.FItems;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.LinkedList;
import java.util.List;
import java.util.function.BiConsumer;

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
        if (ModIds.INDUSTRIAL_FOREGOING.isLoaded)
            modIntegration.accept(ModIds.INDUSTRIAL_FOREGOING, new IndustrialForegoingFutureMcIntegration());
        if (ModIds.DUNGEON_TACTICS.isLoaded)
            modIntegration.accept(ModIds.DUNGEON_TACTICS, new DungeonTacticsFutureMcIntegration());
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
            OreDictionary.registerOre("blockHoney", FBlocks.INSTANCE.getHONEY_BLOCK());
            OreDictionary.registerOre("honeycomb", FItems.INSTANCE.getHONEYCOMB());
            OreDictionary.registerOre("listAllsugar", FItems.INSTANCE.getHONEY_BOTTLE());
            OreDictionary.registerOre("dropHoney", FItems.INSTANCE.getHONEY_BOTTLE());
            OreDictionary.registerOre("foodHoneydrop", FItems.INSTANCE.getHONEY_BOTTLE());
        }
        
        if (!modIntegrations.isEmpty() && !MiaConfig.disableAllRecipes)
        {
            ProgressManager.ProgressBar progressBar = ProgressManager.push("FutureMc addRecipes", modIntegrations.size());
            for (IFutureMcIntegration integration : modIntegrations)
            {
                progressBar.step(integration.getModId().modId);
                integration.addRecipes();
                
                IBlockState[] flowersToRegister = integration.registerPollinationFlowers();
                if (flowersToRegister.length > 0)
                    BeePollinationTargetsJVM.addPollinationTargets();
            }
            ProgressManager.pop(progressBar);
        }
    }
    
    public static void addFoodRecipe(ItemStack input, ItemStack output, int duration)
    {
        CampfireRecipes.INSTANCE.addRecipe(input, output, duration);
        SmokerRecipes.INSTANCE.addRecipe(input, output);
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
                BlastFurnaceRecipes.INSTANCE.addRecipe(input, new ItemStack(ore.getItem(), count, ore.getMetadata()));
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
    
    public static void addBlastFurnaceRecipe(ItemStack input, ItemStack output)
    {
        BlastFurnaceRecipes.INSTANCE.addRecipe(input, output);
    }
    
    public static void addSmokerRecipe(ItemStack input, ItemStack output)
    {
        SmokerRecipes.INSTANCE.addRecipe(input, output);
    }
    
    public static void addStonecutterRecipes(ItemStack input, ItemStack... output)
    {
        for (ItemStack item : output)
            StonecutterRecipes.INSTANCE.addRecipe(input, item);
    }
    
    public static void addCampfireRecipe(ItemStack input, ItemStack output, int duration)
    {
        CampfireRecipes.INSTANCE.addRecipe(input, output, duration);
    }
    
    public static void addCampfireRecipe(ItemStack input, ItemStack output)
    {
        addCampfireRecipe(input, output, 600);
    }
    
    @ParametersAreNonnullByDefault
    public static class GenericBeePollinationHandler implements BeePollinationHandler
    {
        private final PropertyInteger ageProperty;
        private final int maxValue;
        
        public GenericBeePollinationHandler(PropertyInteger ageProperty, int maxValue)
        {
            this.ageProperty = ageProperty;
            this.maxValue = maxValue;
        }
        
        @Override
        public boolean pollinateCrop(World world, BlockPos blockPos, IBlockState blockState, EntityBee entityBee)
        {
            if (world.getBlockState(blockPos).getValue(ageProperty) < maxValue)
            {
                entityBee.world.playEvent(2005, blockPos, 0);
                entityBee.world.setBlockState(blockPos, blockState.withProperty(ageProperty, blockState.getValue(ageProperty) + 1));
                return true;
            }
    
            return false;
        }
    }
}
