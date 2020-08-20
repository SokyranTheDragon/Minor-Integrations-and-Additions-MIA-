package com.github.sokyranthedragon.mia;

import cofh.thermalexpansion.util.managers.machine.EnchanterManager;
import com.github.sokyranthedragon.mia.commands.CommandSize;
import com.github.sokyranthedragon.mia.config.QuarkConfiguration;
import com.github.sokyranthedragon.mia.gui.GuiHandler;
import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.proxy.CommonProxy;
import com.github.sokyranthedragon.mia.utilities.size.SizeUtils;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.init.Items;
import net.minecraft.item.ItemEnchantedBook;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import vazkii.quark.misc.feature.AncientTomes;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("WeakerAccess")
@Mod(
    modid = Mia.MODID,
    name = Mia.NAME,
    version = Mia.VERSION,
    updateJSON = "https://raw.githubusercontent.com/SokyranTheDragon/Minor-Integrations-and-Additions-MIA-/master/update.json",
    dependencies =
        "after:" + ModIds.ConstantIds.ARTEMISLIB +
            ";after:" + ModIds.ConstantIds.EXTRA_UTILITIES +
            ";after:" + ModIds.ConstantIds.COFH_CORE +
            ";after:" + ModIds.ConstantIds.THERMAL_FOUNDATION +
            ";after:" + ModIds.ConstantIds.THERMAL_EXPANSION +
            ";after:" + ModIds.ConstantIds.TINKERS_CONSTRUCT +
            ";after:" + ModIds.ConstantIds.JEI +
            ";after:" + ModIds.ConstantIds.JER +
            ";after:" + ModIds.ConstantIds.ICE_AND_FIRE +
            ";after:" + ModIds.ConstantIds.HATCHERY +
            ";after:" + ModIds.ConstantIds.THAUMCRAFT +
            ";after:" + ModIds.ConstantIds.MO_CREATURES +
            ";after:" + ModIds.ConstantIds.HARVESTCRAFT +
            ";after:" + ModIds.ConstantIds.DUNGEON_TACTICS +
            ";after:" + ModIds.ConstantIds.BOTANIA +
            ";after:" + ModIds.ConstantIds.EXTRABOTANY +
            ";after:" + ModIds.ConstantIds.QUARK +
            ";after:" + ModIds.ConstantIds.CRAFT_TWEAKER +
            ";after:" + ModIds.ConstantIds.FUTURE_MC +
            ";after:" + ModIds.ConstantIds.THE_ONE_PROBE +
            ";after:" + ModIds.ConstantIds.NATURA +
            ";after:" + ModIds.ConstantIds.BIOMES_O_PLENTY +
            // ";after:" + ModIds.ConstantIds.CHISEL + // Not really needed for anything, since we're using IMC for adding blocks
            ";after:" + ModIds.ConstantIds.INDUSTRIAL_FOREGOING +
            ";after:" + ModIds.ConstantIds.ABYSSALCRAFT +
            ";after:" + ModIds.ConstantIds.AETHER +
            ";after:" + ModIds.ConstantIds.AETHER_CONTINUATION +
            ";after:" + ModIds.ConstantIds.AETHER_LOST_CONTENT +
            // Unrelated to the project, but seemed to be causing some issues
            ";after:mystcraft" +
            ";")
public class Mia
{
    public static final String MODID = "mia";
    public static final String NAME = "Minor Integrations & Additions";
    public static final String VERSION = "1.12.2-0.2.0b";
    
    @Mod.Instance
    public static Mia instance;
    
    @SidedProxy(clientSide = "com.github.sokyranthedragon.mia.proxy.ClientProxy", serverSide = "com.github.sokyranthedragon.mia.proxy.CommonProxy")
    public static CommonProxy proxy;
    
    public static final Logger LOGGER = LogManager.getLogger(MODID);
    public static SimpleNetworkWrapper network;
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        proxy.preInit(event);
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());
        proxy.init(event);
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        proxy.postInit(event);
    }
    
    @EventHandler
    public void loadCompleted(FMLLoadCompleteEvent event)
    {
        proxy.loadCompleted(event);
    }
    
    @EventHandler
    public void serverRegistries(FMLServerStartingEvent event)
    {
        if (SizeUtils.isSizeComponentEnabled)
            event.registerServerCommand(new CommandSize());
    }
    
    @EventHandler
    public void handleIdMappingEvent(FMLModIdMappingEvent event)
    {
        if (ModIds.THERMAL_EXPANSION.isLoaded && ModIds.QUARK.isLoaded && AncientTomes.ancient_tome != null && QuarkConfiguration.ancientTomesCrafting)
        {
            List<EnchanterManager.EnchanterRecipe> recipesToReplace = new ArrayList<>();
            
            for (EnchanterManager.EnchanterRecipe enchanterRecipe : EnchanterManager.getRecipeList())
            {
                if (enchanterRecipe.getPrimaryInput().getItem() == Items.ENCHANTED_BOOK && enchanterRecipe.getOutput().getItem() == AncientTomes.ancient_tome)
                    recipesToReplace.add(enchanterRecipe);
            }
            
            for (EnchanterManager.EnchanterRecipe enchanterRecipe : recipesToReplace)
            {
                EnchanterManager.removeRecipe(enchanterRecipe.getPrimaryInput(), enchanterRecipe.getSecondaryInput());
                Enchantment enchantment = Enchantment.getEnchantmentByLocation(enchanterRecipe.getEnchantName());
                
                if (enchantment == null)
                    continue;
                
                ItemStack ancientTome = new ItemStack(AncientTomes.ancient_tome);
                ItemEnchantedBook.addEnchantment(ancientTome, new EnchantmentData(enchantment, enchantment.getMaxLevel()));
                
                EnchanterManager.addRecipe(enchanterRecipe.getEnergy(),
                    ItemEnchantedBook.getEnchantedItemStack(new EnchantmentData(enchantment, enchantment.getMaxLevel())),
                    enchanterRecipe.getSecondaryInput(),
                    ancientTome,
                    enchanterRecipe.getExperience(),
                    enchanterRecipe.getType());
            }
        }
    }
}
