package com.github.exploder1531.mia.proxy;

import com.github.exploder1531.mia.Mia;
import com.github.exploder1531.mia.client.input.MiaKeyBindings;
import com.github.exploder1531.mia.core.MiaItems;
import com.github.exploder1531.mia.utilities.RegisterUtils;
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
        RegisterUtils.registerItemRenderer(MiaItems.music_player);
        modIntegrator.registerRenders(event);
    }
    
    @Override
    public void init(FMLInitializationEvent event)
    {
        super.init(event);
        
        MiaKeyBindings.register();
    }
}
