package com.github.sokyranthedragon.mia.capabilities;

import com.github.sokyranthedragon.mia.handlers.MusicPlayerStackHandler;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

@SuppressWarnings("ConstantConditions")
public class MusicPlayerCapabilityProvider implements ICapabilitySerializable<NBTTagCompound>
{
    @CapabilityInject(MusicPlayerStackHandler.class)
    public static final Capability<MusicPlayerStackHandler> ITEM_HANDLER_CAPABILITY = null;
    private MusicPlayerStackHandler instance = ITEM_HANDLER_CAPABILITY.getDefaultInstance();
    
    public static void register()
    {
        CapabilityManager.INSTANCE.register(MusicPlayerStackHandler.class, new Capability.IStorage<MusicPlayerStackHandler>()
        {
            @Nullable
            @Override
            public NBTBase writeNBT(Capability<MusicPlayerStackHandler> capability, MusicPlayerStackHandler musicPlayerStackHandler, EnumFacing enumFacing)
            {
                return musicPlayerStackHandler.serializeNBT();
            }
            
            @Override
            public void readNBT(Capability<MusicPlayerStackHandler> capability, MusicPlayerStackHandler musicPlayerStackHandler, EnumFacing enumFacing, NBTBase nbtBase)
            {
                if (nbtBase instanceof NBTTagCompound)
                    musicPlayerStackHandler.deserializeNBT((NBTTagCompound) nbtBase);
            }
        }, MusicPlayerStackHandler::new);
    }
    
    @Override
    public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing enumFacing)
    {
        return capability == ITEM_HANDLER_CAPABILITY;
    }
    
    @Nullable
    @Override
    public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing enumFacing)
    {
        if (capability == ITEM_HANDLER_CAPABILITY)
            return ITEM_HANDLER_CAPABILITY.cast(instance);
        
        return null;
    }
    
    @Override
    public NBTTagCompound serializeNBT()
    {
        return (NBTTagCompound) ITEM_HANDLER_CAPABILITY.writeNBT(instance, null);
    }
    
    @Override
    public void deserializeNBT(NBTTagCompound nbt)
    {
        ITEM_HANDLER_CAPABILITY.readNBT(instance, null, nbt);
    }
}
