package com.github.exploder1531.mia.integrations.theoneprobe;

import com.github.alexthe666.iceandfire.IceAndFire;
import com.github.alexthe666.iceandfire.entity.EntityDragonEgg;
import com.github.alexthe666.iceandfire.entity.EntityMyrmexEgg;
import com.github.alexthe666.iceandfire.entity.tile.*;
import com.github.exploder1531.mia.Mia;
import com.github.exploder1531.mia.integrations.ModIds;
import com.pam.harvestcraft.HarvestCraft;
import com.pam.harvestcraft.tileentities.*;
import drzhark.mocreatures.entity.item.MoCEntityEgg;
import mcjty.theoneprobe.api.*;
import mcjty.theoneprobe.apiimpl.ProbeConfig;
import mcjty.theoneprobe.apiimpl.elements.ElementProgress;
import mcjty.theoneprobe.config.Config;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.fml.common.Loader;
import thaumcraft.common.blocks.devices.BlockVisBattery;
import thaumcraft.common.tiles.crafting.TileVoidSiphon;
import thaumcraft.common.tiles.devices.TileJarBrain;
import thaumcraft.common.tiles.devices.TileVisGenerator;

import javax.annotation.Nullable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static com.github.exploder1531.mia.integrations.ModLoadStatus.*;

@SuppressWarnings("UnnecessaryReturnStatement")
public class ProgressProvider implements IProbeInfoProvider, IProbeInfoEntityProvider
{
    private static final Field moEggProgress;
    private static final Field iceFireDragonforgeInputCore;
    
    private static final Method harvestApiaryRunTime;
    private static final Method harvestGroundTrapRunTime;
    private static final Method harvestWaterTrapRunTime;
    private static final Method iceFireDragonforgeBrickCore;
    
    static
    {
        Field moEggField = null;
        Field iceFireDragonforgeInput = null;
        
        Method harvestApiaryMethod = null;
        Method harvestGroundTrapMethod = null;
        Method harvestWaterTrapMethod = null;
        Method iceFireDragonforgeBrick = null;
        
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
        if (Loader.isModLoaded(ModIds.ICE_AND_FIRE))
        {
            try
            {
                iceFireDragonforgeInput = TileEntityDragonforgeInput.class.getDeclaredField("core");
                iceFireDragonforgeInput.setAccessible(true);
            } catch (NoSuchFieldException e)
            {
                Mia.LOGGER.error("Cannot access TileEntityDragonforgeInput core field, no smelting progress will be displayed");
            }
            try
            {
                iceFireDragonforgeBrick = TileEntityDragonforgeBrick.class.getDeclaredMethod("getConnectedTileEntity");
                iceFireDragonforgeBrick.setAccessible(true);
            } catch (NoSuchMethodException e)
            {
                Mia.LOGGER.error("Cannot access TileEntityDragonforgeBrick getConnectedTileEntity() method, no smelting progress will be displayed");
            }
        }
        if (Loader.isModLoaded(ModIds.HARVESTCRAFT))
        {
            try
            {
                harvestApiaryMethod = TileEntityApiary.class.getDeclaredMethod("getRunTime");
                harvestApiaryMethod.setAccessible(true);
            } catch (NoSuchMethodException e)
            {
                Mia.LOGGER.error("Cannot access TileEntityApiary getRunTime() method, no progress will be displayed");
            }
            try
            {
                harvestGroundTrapMethod = TileEntityGroundTrap.class.getDeclaredMethod("getRunTime");
                harvestGroundTrapMethod.setAccessible(true);
            } catch (NoSuchMethodException e)
            {
                Mia.LOGGER.error("Cannot access TileEntityGroundTrap getRunTime() method, no progress will be displayed");
            }
            try
            {
                harvestWaterTrapMethod = TileEntityWaterTrap.class.getDeclaredMethod("getRunTime");
                harvestWaterTrapMethod.setAccessible(true);
            } catch (NoSuchMethodException e)
            {
                Mia.LOGGER.error("Cannot access TileEntityWaterTrap getRunTime() method, no progress will be displayed");
            }
        }
        
        moEggProgress = moEggField;
        iceFireDragonforgeInputCore = iceFireDragonforgeInput;
        
        harvestApiaryRunTime = harvestApiaryMethod;
        harvestGroundTrapRunTime = harvestGroundTrapMethod;
        harvestWaterTrapRunTime = harvestWaterTrapMethod;
        iceFireDragonforgeBrickCore = iceFireDragonforgeBrick;
    }
    
    @Override
    public String getID()
    {
        return "mia:progress";
    }
    
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
        
        if (thaumcraftLoaded)
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
        
        if (iceAndFireLoaded)
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
            else if (tile instanceof TileEntityEggInIce)
            {
                TileEntityEggInIce egg = (TileEntityEggInIce) tile;
                addProgressData(probeInfo, egg.age, IceAndFire.CONFIG.dragonEggTime);
                return;
            }
            else if (tile instanceof TileEntityDragonforgeBrick)
            {
                if (iceFireDragonforgeBrickCore != null)
                {
                    try
                    {
                        TileEntityDragonforgeBrick input = (TileEntityDragonforgeBrick) tile;
                        ICapabilityProvider capacity = (ICapabilityProvider)iceFireDragonforgeBrickCore.invoke(input);
                        
                        if (capacity instanceof TileEntityDragonforge)
                        {
                            TileEntityDragonforge forge = (TileEntityDragonforge) capacity;
                            addProgressData(probeInfo, forge.getField(0), forge.getMaxCookTime());
                        }
                    } catch (IllegalAccessException | InvocationTargetException e)
                    {
                        e.printStackTrace();
                    }
                }
                
                return;
            }
            else if (tile instanceof TileEntityDragonforgeInput)
            {
                if (iceFireDragonforgeInputCore != null)
                {
                    try
                    {
                        TileEntityDragonforgeInput input = (TileEntityDragonforgeInput) tile;
                        TileEntityDragonforge forge = (TileEntityDragonforge) iceFireDragonforgeInputCore.get(input);
                        addProgressData(probeInfo, forge.getField(0), forge.getMaxCookTime());
                    } catch (IllegalAccessException e)
                    {
                        Mia.LOGGER.error("Cannot access TileEntityDragonforgeInput core, even though it was found");
                    }
                }
                
                return;
            }
            else if (tile instanceof TileEntityDragonforge)
            {
                TileEntityDragonforge forge = (TileEntityDragonforge) tile;
                addProgressData(probeInfo, forge.getField(0), forge.getMaxCookTime());
                return;
            }
        }
        
        if (harvestcraftLoaded)
        {
            if (tile instanceof TileEntityApiary)
            {
                if (harvestApiaryRunTime != null)
                {
                    TileEntityApiary apiary = (TileEntityApiary) tile;
                    
                    try
                    {
                        int maxProgress = (int) harvestApiaryRunTime.invoke(apiary);
                        addProgressData(probeInfo, apiary.produceTime, maxProgress);
                        probeInfo.text(I18n.format("mia.top.progress_speed", (int) ((3500f / maxProgress) * 100f)) + "%");
                    } catch (IllegalAccessException | InvocationTargetException e)
                    {
                        Mia.LOGGER.error("Cannot access TileEntityApiary getRunTime(), even though it was found");
                        e.printStackTrace();
                    }
                }
                
                return;
            }
            if (tile instanceof TileEntityGroundTrap)
            {
                if (harvestGroundTrapRunTime != null)
                {
                    TileEntityGroundTrap trap = (TileEntityGroundTrap) tile;
                    
                    // Despite the name, it actually counts dirt and grass blocks around it
                    int dirt = trap.countFlowers();
                    
                    if (dirt < 5)
                    {
                        probeInfo.text(I18n.format("mia.top.not_enough_dirt"));
                        probeInfo.progress(dirt, 5);
                    }
                    else
                    {
                        try
                        {
                            int maxProgress = (int) harvestGroundTrapRunTime.invoke(trap);
                            addProgressData(probeInfo, trap.produceTime, maxProgress);
                            probeInfo.text(I18n.format("mia.top.progress_speed", (int) ((3500f / maxProgress) * 100f)) + "%");
                        } catch (IllegalAccessException | InvocationTargetException e)
                        {
                            Mia.LOGGER.error("Cannot access TileEntityGroundTrap getRunTime(), even though it was found");
                            e.printStackTrace();
                        }
                    }
                }
                
                return;
            }
            if (tile instanceof TileEntityWaterTrap)
            {
                TileEntityWaterTrap trap = (TileEntityWaterTrap) tile;
                
                // A nice name for a method counting water
                int water = trap.countFlowers();
                
                if (water < 5)
                {
                    probeInfo.text(I18n.format("mia.top.not_enough_water"));
                    probeInfo.progress(water, 5);
                }
                else
                {
                    try
                    {
                        int maxProgress = (int) harvestWaterTrapRunTime.invoke(trap);
                        addProgressData(probeInfo, trap.produceTime, maxProgress);
                        probeInfo.text(I18n.format("mia.top.progress_speed", (int) ((3500f / maxProgress) * 100f)) + "%");
                    } catch (IllegalAccessException | InvocationTargetException e)
                    {
                        Mia.LOGGER.error("Cannot access TileEntityWaterTrap getRunTime(), even though it was found");
                        e.printStackTrace();
                    }
                }
                
                return;
            }
            if (tile instanceof TileEntityWaterFilter)
            {
                TileEntityWaterFilter filter = (TileEntityWaterFilter) tile;
                
                int water = filter.countFlowers();
                
                if (water < 5)
                {
                    probeInfo.text(I18n.format("mia.top.not_enough_water"));
                    probeInfo.progress(water, 5);
                }
                else
                    addProgressData(probeInfo, filter.cookTime, HarvestCraft.config.waterfilterTime);
                
                return;
            }
            if (tile instanceof TileEntityPresser)
            {
                addProgressData(probeInfo, ((TileEntityPresser) tile).cookTime, 125);
                return;
            }
            if (tile instanceof TileEntityGrinder)
            {
                addProgressData(probeInfo, ((TileEntityGrinder) tile).cookTime, 125);
                return;
            }
        }
    }
    
    @Override
    public void addProbeEntityInfo(ProbeMode probeMode, IProbeInfo probeInfo, EntityPlayer entityPlayer, World world, Entity entity, IProbeHitEntityData iProbeHitEntityData)
    {
        if (moCreaturesLoaded)
        {
            if (entity instanceof MoCEntityEgg)
            {
                if (moEggProgress != null)
                {
                    try
                    {
                        addProgressData(probeInfo, moEggProgress.getInt(entity), 30);
                    } catch (IllegalAccessException e)
                    {
                        Mia.LOGGER.error("Cannot access MoCEntityEgg progress counter, even though it was found");
                        e.printStackTrace();
                    }
                }
                
                return;
            }
        }
        if (iceAndFireLoaded)
        {
            if (entity instanceof EntityDragonEgg)
            {
                EntityDragonEgg egg = (EntityDragonEgg) entity;
                addProgressData(probeInfo, egg.getDragonAge(), IceAndFire.CONFIG.dragonEggTime);
                return;
            }
            else if (entity instanceof EntityMyrmexEgg)
            {
                EntityMyrmexEgg egg = (EntityMyrmexEgg) entity;
                
                StringBuilder name = new StringBuilder(19);
                if (egg.isJungle())
                    name.append("Jungle ");
                else
                    name.append("Desert ");

                switch (egg.getMyrmexCaste())
                {
                    case 1:
                        name.append(I18n.format("myrmex.caste_soldier.name"));
                        break;
                    case 2:
                        name.append(I18n.format("myrmex.caste_royal.name"));
                        break;
                    case 3:
                        name.append(I18n.format("myrmex.caste_sentinel.name"));
                        break;
                    case 4:
                        name.append(I18n.format("myrmex.caste_queen.name"));
                        break;
                    default:
                        name.append(I18n.format("myrmex.caste_worker.name"));
                        break;
                }
                
                name.append(" Egg");
                probeInfo.text(name.toString());
                addProgressData(probeInfo, egg.getMyrmexAge(), IceAndFire.CONFIG.myrmexEggTicks);
                return;
            }
        }
    }
    
    private void addProgressData(IProbeInfo probeInfo, int currentProgress, int maxProgress)
    {
        addProgressData(probeInfo, currentProgress, maxProgress, null, I18n.format("mia.top.progress"));
    }
    
    
    private void addProgressData(IProbeInfo probeInfo, int currentProgress, int maxProgress, @SuppressWarnings("SameParameterValue") @Nullable String message)
    {
        addProgressData(probeInfo, currentProgress, maxProgress, null, message);
    }
    
    private void addProgressData(IProbeInfo probeInfo, int currentProgress, int maxProgress, @SuppressWarnings("SameParameterValue") @Nullable String hexColor, @Nullable String message)
    {
        float progress = (float) currentProgress / maxProgress * 100;
        final IProgressStyle progressStyle = probeInfo.defaultProgressStyle().suffix("%");
        
        if (hexColor != null)
            progressStyle.alternateFilledColor(0);
        
        if (message != null)
            probeInfo.text(message);
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
