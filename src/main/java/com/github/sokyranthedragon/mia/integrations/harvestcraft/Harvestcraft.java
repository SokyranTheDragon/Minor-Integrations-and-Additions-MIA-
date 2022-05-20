package com.github.sokyranthedragon.mia.integrations.harvestcraft;

import com.github.sokyranthedragon.mia.Mia;
import com.github.sokyranthedragon.mia.config.MiaConfig;
import com.github.sokyranthedragon.mia.dispenserbehavior.DispenserLootBag;
import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.integrations.base.IBaseMod;
import com.github.sokyranthedragon.mia.integrations.base.IModIntegration;
import com.pam.harvestcraft.HarvestCraft;
import com.pam.harvestcraft.blocks.BlockRegistry;
import com.pam.harvestcraft.blocks.FruitRegistry;
import com.pam.harvestcraft.blocks.blocks.BlockBaseGarden;
import com.pam.harvestcraft.item.ItemRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDispenser;
import net.minecraft.dispenser.IBehaviorDispenseItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.common.ProgressManager;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.oredict.OreDictionary;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

import static com.github.sokyranthedragon.mia.config.HarvestcraftConfiguration.*;

public class Harvestcraft implements IBaseMod
{
    private List<IHarvestcraftIntegration> modIntegrations = new ArrayList<>();
    
    @Override
    public void register(BiConsumer<ModIds, IModIntegration> modIntegration)
    {
        if (enableTeIntegration && ModIds.THERMAL_EXPANSION.isLoaded)
            modIntegration.accept(ModIds.THERMAL_EXPANSION, new ThermalExpansionHarvestcraftIntegration());
        if (ModIds.JER.isLoaded)
            modIntegration.accept(ModIds.JER, new JerHarvestcraftIntegration());
        if (ModIds.JEI.isLoaded)
            modIntegration.accept(ModIds.JEI, new JeiHarvestcraftIntegration());
    }
    
    @Override
    public void addIntegration(IModIntegration integration)
    {
        if (!externalIntegrationsEnabled)
            return;
        
        if (integration instanceof IHarvestcraftIntegration)
            modIntegrations.add((IHarvestcraftIntegration)integration);
        else
            Mia.LOGGER.warn("Incorrect Harvestcraft integration with id of " + integration.getModId() + ": " + integration.toString());
    }
    
    @Override
    public void init(FMLInitializationEvent event)
    {
        if (harvestcraftAdditionsEnabled && !MiaConfig.disableOreDict)
        {
            if (HarvestCraft.config.enableTofuAsMeatInRecipes)
                OreDictionary.registerOre("egg", ItemRegistry.rawtofeegItem);
    
            OreDictionary.registerOre("honeycomb", ItemRegistry.honeycombItem);
    
            OreDictionary.registerOre("treeSapling", FruitRegistry.getSapling(FruitRegistry.SPIDERWEB));
            OreDictionary.registerOre("treeSapling", FruitRegistry.getSapling(FruitRegistry.AVOCADO));
            OreDictionary.registerOre("treeSapling", FruitRegistry.getSapling(FruitRegistry.WALNUT));
    
        }
    
        if (!modIntegrations.isEmpty() && !MiaConfig.disableAllRecipes)
        {
            ProgressManager.ProgressBar progressBar = ProgressManager.push("Harvestcraft addRecipes", modIntegrations.size());

            for (IHarvestcraftIntegration integration : modIntegrations)
            {
                progressBar.step(integration.getModId().modId);
                integration.addRecipes();
            }

            ProgressManager.pop(progressBar);
        }
    }
    
    @Override
    public void registerDispenserBehaviors()
    {
        DispenserLootBag.getInstance().addListener(((source, stack) ->
        {
            Block block = Block.getBlockFromItem(stack.getItem());
            if (block instanceof BlockBaseGarden)
            {
                stack.shrink(1);
                IBehaviorDispenseItem defaultDispenserBehavior = BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.getObject(null);
                
                NonNullList<ItemStack> drops = NonNullList.create();
                block.getDrops(drops, source.getWorld(), source.getBlockPos(), source.getBlockState(), 0);
                for (ItemStack lootItem : drops)
                {
                    if (!lootItem.isEmpty())
                        defaultDispenserBehavior.dispense(source, lootItem);
                }
                
                return true;
            }
            
            return false;
        }), BlockRegistry.gardens.values().toArray(new BlockBaseGarden[0]));
    }
}
