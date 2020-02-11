package com.github.sokyranthedragon.mia.integrations.chisel;

import com.github.sokyranthedragon.mia.Mia;
import com.github.sokyranthedragon.mia.config.MiaConfig;
import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.integrations.base.IBaseMod;
import com.github.sokyranthedragon.mia.integrations.base.IModIntegration;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.ProgressManager;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLInterModComms;

import java.util.HashMap;
import java.util.Map;

import static com.github.sokyranthedragon.mia.config.ChiselConfiguration.externalIntegrationsEnabled;

public class Chisel implements IBaseMod
{
    private final Map<ModIds, IChiselIntegration> modIntegrations = new HashMap<>();
    
    @Override
    public void addIntegration(IModIntegration integration)
    {
        if (!externalIntegrationsEnabled)
            return;
        
        if (integration instanceof IChiselIntegration)
            modIntegrations.put(integration.getModId(), (IChiselIntegration) integration);
        else
            Mia.LOGGER.warn("Incorrect Chisel integration with id of " + integration.getModId() + ": " + integration.toString());
    }
    
    @Override
    public void init(FMLInitializationEvent event)
    {
        if (!modIntegrations.isEmpty() && !MiaConfig.disableAllRecipes)
        {
            ProgressManager.ProgressBar progressBar = ProgressManager.push("Chisel addRecipes", modIntegrations.size());
            for (IChiselIntegration integration : modIntegrations.values())
            {
                progressBar.step(integration.getModId().modId);
                integration.sendChiselMessages(Chisel::sendChiselMessage, Chisel::sendChiselMessage);
            }
            ProgressManager.pop(progressBar);
        }
    }
    
    public static void sendChiselMessage(String blockType, Block block, int meta)
    {
        sendChiselMessage(blockType, new ItemStack(block, 1, meta));
    }
    
    public static void sendChiselMessage(String blockType, ItemStack[] items)
    {
        for (ItemStack item : items)
            sendChiselMessage(blockType, item);
    }
    
    public static void sendChiselMessage(String blockType, ItemStack item)
    {
        Block block = Block.getBlockFromItem(item.getItem());
        if (block != Blocks.AIR && block.getRegistryName() != null)
        {
            NBTTagCompound nbt = new NBTTagCompound();
            nbt.setString("group", blockType);
            nbt.setTag("stack", item.writeToNBT(new NBTTagCompound()));
            nbt.setString("block", block.getRegistryName().toString());
            nbt.setInteger("meta", item.getMetadata());
            FMLInterModComms.sendMessage(ModIds.CHISEL.modId, "add_variation", nbt);
        }
    }
}
