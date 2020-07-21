package com.github.sokyranthedragon.mia.network;

import com.github.sokyranthedragon.mia.utilities.size.SizeUtils;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageExtendedReachAttack implements IMessage
{
    private int entityId;
    
    public MessageExtendedReachAttack()
    {
    }
    
    public MessageExtendedReachAttack(int entityId)
    {
        this.entityId = entityId;
    }
    
    @Override
    public void fromBytes(ByteBuf buf)
    {
        entityId = ByteBufUtils.readVarInt(buf, 4);
    }
    
    @Override
    public void toBytes(ByteBuf buf)
    {
        ByteBufUtils.writeVarInt(buf, entityId, 4);
    }
    
    public static class Handler implements IMessageHandler<MessageExtendedReachAttack, IMessage>
    {
        
        @Override
        public IMessage onMessage(MessageExtendedReachAttack message, MessageContext context)
        {
            final EntityPlayerMP player = context.getServerHandler().player;
            
            player.getServerWorld().addScheduledTask(
                    () ->
                    {
                        Entity entity = player.world.getEntityByID(message.entityId);
                        
                        if (entity == null)
                            return;
                        
                        float size = SizeUtils.getEntitySize(player);
                        
                        // 6 is vanilla creative reach, and it seems to be checked as max no matter whether it's creative or not, so we use that as our base
                        double distanceSquared = 36.0D;
                        
                        if (size > 1)
                        {
                            double tempDistance = 3f * size;
                            
                            if (tempDistance > 6)
                                distanceSquared = tempDistance * tempDistance;
                        }
                        
                        // In this case vanilla distance is set to 9, so we divide by 4 to keep the scale at bigger sizes
                        if (!player.canEntityBeSeen(entity))
                            distanceSquared /= 4.0D;
                        
                        if (player.getDistanceSq(entity) < distanceSquared)
                        {
                            if (entity instanceof EntityItem || entity instanceof EntityXPOrb || entity instanceof EntityArrow || entity == player)
                                return;
                            
                            player.attackTargetEntityWithCurrentItem(entity);
                        }
                    }
            );
            
            return null;
        }
    }
}
