package com.github.exploder1531.mia.network;

import com.github.exploder1531.mia.capabilities.MusicPlayerCapabilityProvider;
import com.github.exploder1531.mia.handlers.MusicPlayerStackHandler;
import com.github.exploder1531.mia.utilities.InventoryUtils;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import javax.annotation.Nullable;
import java.util.UUID;

public class MessageSyncMusicPlayer implements IMessage
{
    private int slotType;
    private int slotInventory;
    private int slotMusicPlayer;

    public boolean repeat;
    public boolean autoplay;
    public boolean shuffle;
    @Nullable
    private UUID uuid = null;
    
    public MessageSyncMusicPlayer()
    {
    }
    
    public MessageSyncMusicPlayer(int slotType, int slotInventory, int slotMusicPlayer, boolean repeat, boolean autoplay, boolean shuffle)
    {
        this.slotType = slotType;
        this.slotInventory = slotInventory;
        this.slotMusicPlayer = slotMusicPlayer;
        this.repeat = repeat;
        this.autoplay = autoplay;
        this.shuffle = shuffle;
    }
    
    public MessageSyncMusicPlayer(int slotType, int slotInventory, int slotMusicPlayer, boolean repeat, boolean autoplay, boolean shuffle, @Nullable UUID uuid)
    {
        this(slotType, slotInventory, slotMusicPlayer, repeat, autoplay, shuffle);
        this.uuid = uuid;
    }
    
    public MessageSyncMusicPlayer(int slotType, int slotInventory, MusicPlayerStackHandler musicPlayer, boolean updateUuid)
    {
        this(slotType, slotInventory, musicPlayer.getCurrentSongSlot(), musicPlayer.repeat, musicPlayer.autoplay, musicPlayer.shuffle);
        
        if (updateUuid)
            this.uuid = musicPlayer.itemUuid;
    }
    
    @Override
    public void fromBytes(ByteBuf byteBuf)
    {
        slotType = ByteBufUtils.readVarInt(byteBuf, 1);
        slotInventory = ByteBufUtils.readVarInt(byteBuf, 1);
        slotMusicPlayer = ByteBufUtils.readVarInt(byteBuf, 5);
        
        byte data = byteBuf.readByte();
        
        repeat = (data & 0b0001) != 0;
        autoplay = (data & 0b0010) != 0;
        shuffle = (data & 0b0100) != 0;
        
        if ((data & 0b1000) != 0)
        {
            uuid = new UUID(byteBuf.readLong(), byteBuf.readLong());
        }
    }
    
    @Override
    public void toBytes(ByteBuf byteBuf)
    {
        ByteBufUtils.writeVarInt(byteBuf, slotType, 1);
        ByteBufUtils.writeVarInt(byteBuf, slotInventory, 1);
        ByteBufUtils.writeVarInt(byteBuf, slotMusicPlayer, 5);
    
        byte data = 0b0000;
        if (repeat)
            data |= 0b0001;
        if (autoplay)
            data |= 0b0010;
        if (shuffle)
            data |= 0b0100;
        if (uuid != null)
        {
            data |= 0x1000;
            byteBuf.writeByte(data);
            byteBuf.writeLong(uuid.getMostSignificantBits());
            byteBuf.writeLong(uuid.getLeastSignificantBits());
        }
        else
            byteBuf.writeByte(data);
    }
    
    public static class Handler implements IMessageHandler<MessageSyncMusicPlayer, IMessage>
    {
        @Override
        public IMessage onMessage(MessageSyncMusicPlayer message, MessageContext context)
        {
            final EntityPlayerMP player = context.getServerHandler().player;
            
            player.getServerWorld().addScheduledTask(() ->
            {
                ItemStack item = InventoryUtils.getItemFromPlayer(player, message.slotType, message.slotInventory);
                MusicPlayerStackHandler capability = item.getCapability(MusicPlayerCapabilityProvider.ITEM_HANDLER_CAPABILITY, null);
                
                if (capability != null)
                {
                    capability.setCurrentSongSlot(message.slotMusicPlayer);
                    capability.repeat = message.repeat;
                    capability.autoplay = message.autoplay;
                    capability.shuffle = message.shuffle;
                    if (message.uuid != null)
                        capability.itemUuid = message.uuid;
                }
            });
            
            return null;
        }
    }
}
