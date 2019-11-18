package com.github.exploder1531.mia.integrations.theoneprobe;

import com.github.alexthe666.iceandfire.entity.tile.TileEntityJar;
import com.github.exploder1531.mia.Mia;
import com.github.exploder1531.mia.integrations.ModIds;
import drzhark.mocreatures.entity.item.MoCEntityEgg;
import mcjty.theoneprobe.api.*;
import mcjty.theoneprobe.apiimpl.ProbeConfig;
import mcjty.theoneprobe.apiimpl.elements.ElementProgress;
import mcjty.theoneprobe.config.Config;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Loader;
import thaumcraft.common.blocks.devices.BlockVisBattery;
import thaumcraft.common.tiles.crafting.TileVoidSiphon;
import thaumcraft.common.tiles.devices.TileJarBrain;
import thaumcraft.common.tiles.devices.TileVisGenerator;

import javax.annotation.Nullable;
import java.lang.reflect.Field;

public class ProgressProvider implements IProbeInfoProvider, IProbeInfoEntityProvider
{
    private static final Field moEggProgress;
    
    static
    {
        Field moEggField = null;
        
        if (Loader.isModLoaded(ModIds.MO_CREATURES))
        {
            try
            {
                moEggField = MoCEntityEgg.class.getDeclaredField("tCounter");
                moEggField.setAccessible(true);
            } catch (NoSuchFieldException e)
            {
                Mia.LOGGER.error("Cannot access MoCEntityEgg tCounter field, no hatching progress will be displayed");
            }
        }
        
        moEggProgress = moEggField;
    }
    
    @Override
    public String getID()
    {
        return "mia:progress";
    }
    
    @SuppressWarnings("UnnecessaryReturnStatement")
    @Override
    public void addProbeInfo(ProbeMode mode, IProbeInfo probeInfo, EntityPlayer player, World world, IBlockState blockState, IProbeHitData data)
    {
        TileEntity tile = world.getTileEntity(data.getPos());
        
        if (tile instanceof ITileProbeProgress)
        {
            ITileProbeProgress progressTile = ((ITileProbeProgress) tile);
            addProgressData(probeInfo, progressTile.getProgress(), progressTile.getProgressMax());
            return;
        }
        
        if (Loader.isModLoaded(ModIds.THAUMCRAFT))
        {
            if (blockState.getBlock() instanceof BlockVisBattery)
            {
                addProgressData(probeInfo, blockState.getValue(BlockVisBattery.CHARGE), 10);
                return;
            }
            if (tile instanceof TileVoidSiphon)
            {
                TileVoidSiphon siphon = ((TileVoidSiphon) tile);
                addProgressData(probeInfo, siphon.progress, siphon.PROGREQ);
                return;
            }
            if (tile instanceof TileVisGenerator)
            {
                TileVisGenerator generator = ((TileVisGenerator) tile);
                addRfData(probeInfo, generator.getEnergyStored(), generator.getMaxEnergyStored());
                return;
            }
            if (tile instanceof TileJarBrain)
            {
                TileJarBrain brain = ((TileJarBrain) tile);
                probeInfo.horizontal().progress(brain.xp, brain.xpMax, probeInfo.defaultProgressStyle().suffix("xp"));
                return;
            }
        }
        
        if (Loader.isModLoaded(ModIds.ICE_AND_FIRE))
        {
            if (tile instanceof TileEntityJar)
            {
                TileEntityJar jar = ((TileEntityJar) tile);
                
                if (jar.hasPixie)
                {
                    if (jar.hasProduced)
                        addProgressData(probeInfo, 1, 1);
                    else
                        addProgressData(probeInfo, jar.ticksExisted % 24_000, 24_000);
                }
                return;
            }
        }
    }
    
    @SuppressWarnings("UnnecessaryReturnStatement")
    @Override
    public void addProbeEntityInfo(ProbeMode probeMode, IProbeInfo iProbeInfo, EntityPlayer entityPlayer, World world, Entity entity, IProbeHitEntityData iProbeHitEntityData)
    {
        if (Loader.isModLoaded(ModIds.MO_CREATURES))
        {
            if (entity instanceof MoCEntityEgg)
            {
                if (moEggProgress != null)
                {
                    try
                    {
                        addProgressData(iProbeInfo, moEggProgress.getInt(entity), 30);
                    } catch (IllegalAccessException e)
                    {
                        Mia.LOGGER.error("Cannot access MoCEntityEgg progress counter, even though it was found");
                        e.printStackTrace();
                    }
                }
                
                return;
            }
        }
    }
    
    private void addProgressData(IProbeInfo probeInfo, int currentProgress, int maxProgress)
    {
        addProgressData(probeInfo, currentProgress, maxProgress, null);
    }
    
    private void addProgressData(IProbeInfo probeInfo, int currentProgress, int maxProgress, @SuppressWarnings("SameParameterValue") @Nullable String hexColor)
    {
        float progress = (float) currentProgress / maxProgress * 100;
        final IProgressStyle progressStyle = probeInfo.defaultProgressStyle().suffix("%");
        
        if (hexColor != null)
            progressStyle.alternateFilledColor(0);
        
        probeInfo.progress((int) progress, 100, progressStyle);
    }
    
    private void addRfData(IProbeInfo probeInfo, int energy, int maxEnergy)
    {
        ProbeConfig config = Config.getDefaultConfig();
        
        if (config.getRFMode() == 1)
            probeInfo.progress(energy, maxEnergy, probeInfo.defaultProgressStyle().suffix("RF").filledColor(Config.rfbarFilledColor).alternateFilledColor(Config.rfbarAlternateFilledColor).borderColor(Config.rfbarBorderColor).numberFormat(Config.rfFormat));
        else
            probeInfo.text(TextStyleClass.PROGRESS + "RF: " + ElementProgress.format(energy, Config.rfFormat, "RF"));
    }
}
