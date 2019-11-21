package com.github.exploder1531.mia.integrations.mocreatures;

import com.github.exploder1531.mia.integrations.ModIds;
import com.github.exploder1531.mia.integrations.base.IBaseMod;
import com.github.exploder1531.mia.integrations.base.IModIntegration;
import com.pam.harvestcraft.item.ItemRegistry;
import drzhark.mocreatures.init.MoCBlocks;
import drzhark.mocreatures.init.MoCItems;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.oredict.OreDictionary;

import java.util.List;
import java.util.function.BiConsumer;

import static com.github.exploder1531.mia.config.MoCreaturesConfiguration.*;
import static com.github.exploder1531.mia.integrations.ModLoadStatus.*;

public class MoCreatures implements IBaseMod
{
    @Override
    public void register(BiConsumer<String, IModIntegration> modIntegration)
    {
        if (enableJerIntegration && jerLoaded)
            modIntegration.accept(ModIds.JER, new JerMoCreaturesIntegration());
        if (enableTeIntegration && thermalExpansionLoaded)
            modIntegration.accept(ModIds.THERMAL_EXPANSION, new ThermalExpansionMoCreaturesIntegration());
    }
    
    @Override
    public void init(FMLInitializationEvent event)
    {
        if (!moCreaturesAdditionsEnabled)
            return;
        
        FurnaceRecipes smelting = FurnaceRecipes.instance();
        
        if (harvestcraftLoaded)
            smelting.addSmelting(MoCItems.turtleraw, new ItemStack(ItemRegistry.turtlecookedItem), 0.1f);
        List<ItemStack> nuggets = OreDictionary.getOres("nuggetSilver");
        if (nuggets.size() > 0)
            smelting.addSmelting(MoCItems.silversword, nuggets.get(0), 0.1f);
        
        OreDictionary.registerOre("foodTurtleraw", MoCItems.turtleraw);
        OreDictionary.registerOre("foodTurtlesoup", MoCItems.turtlesoup);
        
        OreDictionary.registerOre("foodTurkeyraw", MoCItems.rawTurkey);
        OreDictionary.registerOre("listAllturkeyraw", MoCItems.rawTurkey);
        OreDictionary.registerOre("listAllmeatraw", MoCItems.rawTurkey);
        OreDictionary.registerOre("foodTurkeycooked", MoCItems.cookedTurkey);
        OreDictionary.registerOre("listAllturkeycooked", MoCItems.cookedTurkey);
        OreDictionary.registerOre("listAllmeatcooked", MoCItems.cookedTurkey);
        
        OreDictionary.registerOre("foodCrabraw", MoCItems.crabraw);
        OreDictionary.registerOre("foodCrabcooked", MoCItems.crabcooked);
        
        OreDictionary.registerOre("foodRatraw", MoCItems.ratRaw);
        OreDictionary.registerOre("listAllmeatraw", MoCItems.ratRaw);
        OreDictionary.registerOre("foodRatcooked", MoCItems.ratCooked);
        OreDictionary.registerOre("listAllmeatraw", MoCItems.ratCooked);
        
        OreDictionary.registerOre("foodOstrichraw", MoCItems.ostrichraw);
        OreDictionary.registerOre("listAllmeatraw", MoCItems.ostrichraw);
        OreDictionary.registerOre("foodOstrichcooked", MoCItems.ostrichcooked);
        OreDictionary.registerOre("listAllmeatraw", MoCItems.ostrichcooked);
        
        OreDictionary.registerOre("foodRatburger", MoCItems.ratBurger);
        
        OreDictionary.registerOre("egg", new ItemStack(MoCItems.mocegg, 1, OreDictionary.WILDCARD_VALUE));
        OreDictionary.registerOre("listAllegg", MoCItems.mocegg);
        
        OreDictionary.registerOre("foodOmelet", MoCItems.omelet);
        
        OreDictionary.registerOre("logWood", new ItemStack(MoCBlocks.mocLog, 1, OreDictionary.WILDCARD_VALUE));
        OreDictionary.registerOre("plankWood", new ItemStack(MoCBlocks.mocPlank, 1, OreDictionary.WILDCARD_VALUE));
        OreDictionary.registerOre("treeLeaves", new ItemStack(MoCBlocks.mocLeaf, 1, OreDictionary.WILDCARD_VALUE));
        OreDictionary.registerOre("dirt", new ItemStack(MoCBlocks.mocDirt, 1, OreDictionary.WILDCARD_VALUE));
        
        OreDictionary.registerOre("record", MoCItems.recordshuffle);
    }
}
