package com.github.exploder1531.mia.gui;

import com.github.exploder1531.mia.gui.client.GuiEggSorter;
import com.github.exploder1531.mia.gui.client.GuiPixieDustExtractor;
import com.github.exploder1531.mia.gui.client.GuiVoidCreator;
import com.github.exploder1531.mia.gui.container.ContainerEggSorter;
import com.github.exploder1531.mia.gui.container.ContainerPixieDustExtractor;
import com.github.exploder1531.mia.gui.container.ContainerVoidCreator;
import com.github.exploder1531.mia.tile.TileEggSorter;
import com.github.exploder1531.mia.tile.TilePixieDustExtractor;
import com.github.exploder1531.mia.tile.TileVoidCreator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

import javax.annotation.Nullable;

public class GuiHandler implements IGuiHandler
{
    public static final int EGG_SORTER = 0;
    public static final int VOID_CREATOR = 1;
    public static final int PIXIE_DUST_EXTRACTOR = 2;
    
    @Nullable
    @Override
    public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z)
    {
        TileEntity tile = world.getTileEntity(new BlockPos(x, y, z));
        
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
        }
        
        return null;
    }
    
    @Nullable
    @Override
    public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z)
    {
        TileEntity tile = world.getTileEntity(new BlockPos(x, y, z));
        
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
        }
        
        return null;
    }
}
