package com.github.sokyranthedragon.mia.proxy;

import com.github.sokyranthedragon.mia.Mia;
import com.github.sokyranthedragon.mia.client.input.MiaKeyBindings;
import com.github.sokyranthedragon.mia.core.MiaItems;
import com.github.sokyranthedragon.mia.utilities.RegisterUtils;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod.EventBusSubscriber(modid = Mia.MODID)
public class ClientProxy extends CommonProxy
{
    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public static void registerRenders(ModelRegistryEvent event)
    {
        RegisterUtils.registerItemRenderer(MiaItems.musicPlayer);
        modIntegrator.registerRenders(event);
    }
    
    @Override
    public void init(FMLInitializationEvent event)
    {
        super.init(event);
        
        MiaKeyBindings.register();
    }
}
