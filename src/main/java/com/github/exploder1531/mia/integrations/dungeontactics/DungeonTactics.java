package com.github.exploder1531.mia.integrations.dungeontactics;

import com.github.exploder1531.mia.Mia;
import com.github.exploder1531.mia.integrations.ModIds;
import com.github.exploder1531.mia.integrations.base.IBaseMod;
import com.github.exploder1531.mia.integrations.base.IModIntegration;
import com.github.exploder1531.mia.integrations.dungeontactics.jei.CauldronEntry;
import com.github.exploder1531.mia.integrations.dungeontactics.jei.CauldronRegistry;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.oredict.OreDictionary;
import pegbeard.dungeontactics.handlers.DTBlocks;
import pegbeard.dungeontactics.handlers.DTEffects;
import pegbeard.dungeontactics.handlers.DTItems;

import java.util.function.BiConsumer;

import static com.github.exploder1531.mia.integrations.ModLoadStatus.thermalExpansionLoaded;
import static com.github.exploder1531.mia.integrations.ModLoadStatus.tinkersConstructLoaded;

public class DungeonTactics implements IBaseMod
{
    @Override
    public void register(BiConsumer<String, IModIntegration> modIntegration)
    {
        if (tinkersConstructLoaded)
            modIntegration.accept(ModIds.TINKERS_CONSTRUCT, new TConstructDungeonTacticsIntegration());
        if (thermalExpansionLoaded)
            modIntegration.accept(ModIds.THERMAL_EXPANSION, new ThermalExpansionDungeonTacticsIntegration());
//        if (jerLoaded)
//            modIntegration.accept(ModIds.JER, new JerDungeonTacticsIntegration());
    }
    
    @Override
    public void init(FMLInitializationEvent event)
    {
        OreDictionary.registerOre("blockGlass", DTBlocks.DUNGEON_GLASS);
        
        OreDictionary.registerOre("listAllberry", DTItems.CHERRYBOMB);
        OreDictionary.registerOre("listAllfruit", DTItems.CHERRYBOMB);
        OreDictionary.registerOre("listAllberry", DTItems.INCINDIBERRY);
        OreDictionary.registerOre("listAllfruit", DTItems.INCINDIBERRY);
        OreDictionary.registerOre("listAllberry", DTItems.GLOWCURRENT);
        OreDictionary.registerOre("listAllfruit", DTItems.GLOWCURRENT);
        
        OreDictionary.registerOre("foodToast", DTItems.TOAST);
        OreDictionary.registerOre("foodToastslice", DTItems.TOASTSLICE);
        OreDictionary.registerOre("foodJamtoast", DTItems.JAMSLICE);
        OreDictionary.registerOre("foodBreadslice", DTItems.BREADSLICE);
    }
    
    @Override
    public void postInit(FMLPostInitializationEvent event)
    {
        if (Loader.isModLoaded("jei"))
        {
            CauldronRegistry registry = CauldronRegistry.getInstance();
        
            if (registry != null)
            {
                // Weapon imbuing
                registerImbuing(registry, Items.IRON_SWORD, DTEffects.POISONAILMENT, new ItemStack(DTBlocks.FLOWER_AILMENT));
                registerImbuing(registry, Items.IRON_SWORD, DTEffects.POISONBARK, new ItemStack(DTBlocks.FLOWER_BARK));
                registerImbuing(registry, Items.IRON_SWORD, DTEffects.POISONBRAMBLE, new ItemStack(DTBlocks.FLOWER_BRAMBLE));
                registerImbuing(registry, Items.IRON_SWORD, DTEffects.POISONCINDER, new ItemStack(DTBlocks.FLOWER_CINDER));
                registerImbuing(registry, Items.IRON_SWORD, DTEffects.POISONFADE, new ItemStack(DTBlocks.FLOWER_FADE));
                registerImbuing(registry, Items.IRON_SWORD, DTEffects.POISONFEATHER, new ItemStack(DTBlocks.FLOWER_FEATHER));
                registerImbuing(registry, Items.IRON_SWORD, DTEffects.POISONSANGUINE, new ItemStack(DTBlocks.FLOWER_SANGUINE));
                registerImbuing(registry, Items.IRON_SWORD, DTEffects.POISONTANGLE, new ItemStack(DTBlocks.FLOWER_TANGLE));
                
                // Cooking
                registerCooking(registry, Items.IRON_SHOVEL, Items.GUNPOWDER, new ItemStack(DTItems.CHERRYBOMB, 4), new ItemStack(Items.REDSTONE, 4), new ItemStack(Items.FLINT));
                registerCooking(registry, Items.IRON_SHOVEL, Items.GLOWSTONE_DUST, new ItemStack(DTItems.GLOWCURRENT, 4), new ItemStack(Items.REDSTONE, 4), new ItemStack(Items.BLAZE_POWDER));
                registerCooking(registry, Items.IRON_SHOVEL, Items.BLAZE_POWDER, new ItemStack(DTItems.INCINDIBERRY, 4), new ItemStack(Items.REDSTONE, 4), new ItemStack(Items.COAL));
                // 1 to 3 + fortune level
                registerCooking(registry, Items.IRON_SHOVEL, DTItems.MAGIC_POWDER, new ItemStack(Items.GLOWSTONE_DUST), new ItemStack(Blocks.RED_MUSHROOM), new ItemStack(Blocks.BROWN_MUSHROOM));
                registerCooking(registry, Items.IRON_SHOVEL, new ItemStack(Items.CLAY_BALL, 4), new ItemStack(Items.SLIME_BALL, 3), new ItemStack(Blocks.SAND));
                registerCooking(registry, Items.IRON_SHOVEL, Items.LEATHER, Items.SLIME_BALL, new ItemStack(Items.ROTTEN_FLESH, 3), new ItemStack(Items.SUGAR, 2));
                // Any (vanilla) leaves
                registerCooking(registry, Items.IRON_SHOVEL, new ItemStack(Blocks.DIRT), new ItemStack(Blocks.LEAVES, 2, OreDictionary.WILDCARD_VALUE), new ItemStack(Blocks.SAND));
                registerCooking(registry, Items.IRON_SHOVEL, new ItemStack(Blocks.MYCELIUM), new ItemStack(Blocks.DIRT), new ItemStack(Blocks.RED_MUSHROOM, 2), new ItemStack(Blocks.BROWN_MUSHROOM, 2));
            }
            else
                Mia.LOGGER.error("Could not access Alchemical Cauldron recipe registry, this shouldn't have happened as Dungeon Tactics is loaded. Something is very wrong.");
        }
    }
    
    @SuppressWarnings("SameParameterValue")
    private void registerImbuing(CauldronRegistry registry, Item weapon, Enchantment enchantment, ItemStack... input)
    {
        ItemStack output = new ItemStack(weapon);
        output.addEnchantment(enchantment, 1);
        registry.registerCauldronRecipe(new CauldronEntry(new ItemStack(weapon), output, input));
    }
    
    private void registerCooking(CauldronRegistry registry, Item spoon, Item output, ItemStack... input)
    {
        registerCooking(registry, spoon, output, null, input);
    }
    
    private void registerCooking(CauldronRegistry registry, Item spoon, ItemStack output, ItemStack... input)
    {
        registerCooking(registry, spoon, output, null, input);
    }
    
    private void registerCooking(CauldronRegistry registry, Item spoon, Item output, Item byproduct, ItemStack... input)
    {
        registerCooking(registry, spoon, new ItemStack(output), byproduct, input);
    }
    
    private void registerCooking(CauldronRegistry registry, Item spoon, ItemStack output, Item byproduct, ItemStack... input)
    {
        if (byproduct != null)
            registry.registerCauldronRecipe(new CauldronEntry(new ItemStack(spoon), output, new ItemStack(byproduct), input));
        else
            registry.registerCauldronRecipe(new CauldronEntry(new ItemStack(spoon), output, input));
    }
}
