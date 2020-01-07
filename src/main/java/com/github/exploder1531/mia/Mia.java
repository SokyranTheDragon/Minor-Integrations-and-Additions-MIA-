package com.github.exploder1531.mia;

import com.github.exploder1531.mia.gui.GuiHandler;
import com.github.exploder1531.mia.integrations.ModIds;
import com.github.exploder1531.mia.proxy.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLLoadCompleteEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings("WeakerAccess")
@Mod(
        modid = Mia.MODID,
        name = Mia.NAME,
        version = Mia.VERSION,
        dependencies = "after:" + ModIds.ConstantIds.EXTRA_UTILITIES +
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
                ";")
public class Mia
{
    public static final String MODID = "mia";
    public static final String NAME = "Minor Integrations & Additions";
    public static final String VERSION = "1.12.2-0.1.5";
    
    @Mod.Instance
    public static Mia instance;
    
    @SidedProxy(clientSide = "com.github.exploder1531.mia.proxy.ClientProxy", serverSide = "com.github.exploder1531.mia.proxy.CommonProxy")
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
}
