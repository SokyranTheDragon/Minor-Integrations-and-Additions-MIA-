package com.github.sokyranthedragon.mia.proxy;

import com.artemis.artemislib.compatibilities.sizeCap.ISizeCap;
import com.artemis.artemislib.compatibilities.sizeCap.SizeCapPro;
import com.github.sokyranthedragon.mia.Mia;
import com.github.sokyranthedragon.mia.client.input.MiaKeyBindings;
import com.github.sokyranthedragon.mia.config.GenericAdditionsConfig;
import com.github.sokyranthedragon.mia.core.MiaBlocks;
import com.github.sokyranthedragon.mia.core.MiaItems;
import com.github.sokyranthedragon.mia.utilities.size.SizeUtils;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod.EventBusSubscriber(modid = Mia.MODID)
public class ClientProxy extends CommonProxy
{
    private static boolean renderingStarted = false;
    private static boolean renderingSpecialStarted = false;
    
    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public static void registerRenders(ModelRegistryEvent event)
    {
        MiaItems.registerMiaItemRenderers();
        MiaBlocks.registerMiaItemblockRenderers();
        modIntegrator.registerRenders(event);
    }
    
    @Override
    public void init(FMLInitializationEvent event)
    {
        super.init(event);
        
        MiaKeyBindings.register();
    }
    
    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public static void onEntityRenderPre(RenderLivingEvent.Pre<?> event)
    {
        if (!SizeUtils.isSizeComponentEnabled)
            return;
        
        final EntityLivingBase entity = event.getEntity();
        
        if ((GenericAdditionsConfig.sizeModule.fixRendering || GenericAdditionsConfig.sizeModule.fixInventoryRendering) && entity.hasCapability(SizeCapPro.sizeCapability, null))
        {
            final ISizeCap cap = entity.getCapability(SizeCapPro.sizeCapability, null);
            
            //noinspection ConstantConditions
            if (cap.getTrans())
            {
                float scale = entity.height / cap.getDefaultHeight();
                boolean resizeInventoryPlayer = false;
                
                if (entity instanceof EntityPlayerSP && scale != 1)
                {
                    for (StackTraceElement element : Thread.currentThread().getStackTrace())
                    {
                        if (element.getClassName().equals(GuiInventory.class.getName()))
                        {
                            resizeInventoryPlayer = true;
                            break;
                        }
                    }
                }
                
                if ((GenericAdditionsConfig.sizeModule.fixRendering && scale < 0.4F) || (GenericAdditionsConfig.sizeModule.fixInventoryRendering && resizeInventoryPlayer))
                {
                    renderingStarted = true;
                    
                    GlStateManager.pushMatrix();
                    GlStateManager.translate(event.getX(), event.getY(), event.getZ());
                    if (resizeInventoryPlayer)
                        GlStateManager.scale(1 / scale, 1 / scale, 1 / scale);
                    if (GenericAdditionsConfig.sizeModule.fixRendering && scale < 0.4f)
                        GlStateManager.scale(scale * 2.5F, 1.0F, scale * 2.5F);
                    GlStateManager.translate(-event.getX(), -event.getY(), -event.getZ());
                }
            }
        }
    }
    
    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public static void onLivingRenderPost(RenderLivingEvent.Post<?> event)
    {
        if (renderingStarted)
        {
            renderingStarted = false;
            GlStateManager.popMatrix();
        }
    }
    
    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public static void onLivingRenderSpecialPre(RenderLivingEvent.Specials.Pre<?> event)
    {
        if (!SizeUtils.isSizeComponentEnabled)
            return;
    
        final EntityLivingBase entity = event.getEntity();
        
        if (entity.hasCapability(SizeCapPro.sizeCapability, null))
        {
            final ISizeCap cap = entity.getCapability(SizeCapPro.sizeCapability, null);
            
            //noinspection ConstantConditions
            if (cap.getTrans())
            {
                renderingSpecialStarted = true;
                GlStateManager.pushMatrix();
                GlStateManager.translate(0, cap.getDefaultHeight() - entity.height, 0);
            }
        }
    }
    
    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public static void onLivingRenderSpecialPre(RenderLivingEvent.Specials.Post<?> event)
    {
        if (renderingSpecialStarted)
        {
            renderingSpecialStarted = false;
            GlStateManager.popMatrix();
        }
    }
    
    @Override
    protected Side getSide()
    {
        return Side.CLIENT;
    }
}
