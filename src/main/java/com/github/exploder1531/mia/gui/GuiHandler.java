package com.github.exploder1531.mia.gui;

import com.github.exploder1531.mia.capabilities.MusicPlayerCapabilityProvider;
import com.github.exploder1531.mia.gui.client.GuiEggSorter;
import com.github.exploder1531.mia.gui.client.GuiMusicPlayer;
import com.github.exploder1531.mia.gui.client.GuiPixieDustExtractor;
import com.github.exploder1531.mia.gui.client.GuiVoidCreator;
import com.github.exploder1531.mia.gui.container.ContainerEggSorter;
import com.github.exploder1531.mia.gui.container.ContainerMusicPlayer;
import com.github.exploder1531.mia.gui.container.ContainerPixieDustExtractor;
import com.github.exploder1531.mia.gui.container.ContainerVoidCreator;
import com.github.exploder1531.mia.handlers.MusicPlayerStackHandler;
import com.github.exploder1531.mia.inventory.MusicPlayerInventory;
import com.github.exploder1531.mia.tile.TileEggSorter;
import com.github.exploder1531.mia.tile.TilePixieDustExtractor;
import com.github.exploder1531.mia.tile.TileVoidCreator;
import com.github.exploder1531.mia.utilities.InventoryUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

import javax.annotation.Nullable;

@SuppressWarnings("ConstantConditions")
public class GuiHandler implements IGuiHandler
{
    public static final int EGG_SORTER = 0;
    public static final int VOID_CREATOR = 1;
    public static final int PIXIE_DUST_EXTRACTOR = 2;
    public static final int MUSIC_PLAYER = 3;
    
    @Nullable
    @Override
    public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z)
    {
        TileEntity tile = null;
        
        // In case of item inventory, x/y/z parameters are used for other data
        if (id != MUSIC_PLAYER)
            tile = world.getTileEntity(new BlockPos(x, y, z));
        
        switch (id)
        {
            case EGG_SORTER:
                if (tile instanceof TileEggSorter)
                    return new ContainerEggSorter(player.inventory, (TileEggSorter) tile);
                break;
            case VOID_CREATOR:
                if (tile instanceof TileVoidCreator)
                    return new ContainerVoidCreator(player.inventory, (TileVoidCreator) tile);
                break;
            case PIXIE_DUST_EXTRACTOR:
                if (tile instanceof TilePixieDustExtractor)
                    return new ContainerPixieDustExtractor(player.inventory, (TilePixieDustExtractor) tile);
                break;
            case MUSIC_PLAYER:
                ItemStack musicPlayer = InventoryUtils.getItemFromPlayer(player, x, y);
                // No check for music player, I guess if some other mod creator wants to extend my music player and use the same gui/container then they can
                MusicPlayerStackHandler capability = musicPlayer.getCapability(MusicPlayerCapabilityProvider.ITEM_HANDLER_CAPABILITY, null);
                if (capability instanceof MusicPlayerStackHandler)
                    return new ContainerMusicPlayer(player.inventory, new MusicPlayerInventory(musicPlayer, capability));
                break;
        }
        
        return null;
    }
    
    @Nullable
    @Override
    public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z)
    {
        TileEntity tile = null;
        
        // In case of item inventory, x/y/z parameters are used for other data
        if (id != MUSIC_PLAYER)
            tile = world.getTileEntity(new BlockPos(x, y, z));
        
        switch (id)
        {
            case EGG_SORTER:
                if (tile instanceof TileEggSorter)
                    return new GuiEggSorter(player.inventory, (TileEggSorter) tile);
                break;
            case VOID_CREATOR:
                if (tile instanceof TileVoidCreator)
                    return new GuiVoidCreator(player.inventory, (TileVoidCreator) tile);
                break;
            case PIXIE_DUST_EXTRACTOR:
                if (tile instanceof TilePixieDustExtractor)
                    return new GuiPixieDustExtractor(player.inventory, (TilePixieDustExtractor) tile);
                break;
            case MUSIC_PLAYER:
                ItemStack musicPlayer = InventoryUtils.getItemFromPlayer(player, x, y);
                // No check for music player, I guess if some other mod creator wants to extend my music player and use the same gui/container then they can
                MusicPlayerStackHandler capability = musicPlayer.getCapability(MusicPlayerCapabilityProvider.ITEM_HANDLER_CAPABILITY, null);
                if (capability instanceof MusicPlayerStackHandler)
                    return new GuiMusicPlayer(player.inventory, new MusicPlayerInventory(musicPlayer, capability));
                break;
        }
        
        return null;
    }
}
