package com.github.sokyranthedragon.mia.events;

import com.gildedgames.the_aether.api.accessories.AccessoryType;
import com.gildedgames.the_aether.api.accessories.AetherAccessory;
import com.github.sokyranthedragon.mia.Mia;
import com.github.sokyranthedragon.mia.core.MiaBlocks;
import com.github.sokyranthedragon.mia.core.MiaItems;
import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.utilities.size.SizeUtils;
import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber(modid = Mia.MODID)
public class RegistryEvents
{
    @SubscribeEvent
    public static void missingBlockMappingsEvent(RegistryEvent.MissingMappings<Block> event)
    {
        for (RegistryEvent.MissingMappings.Mapping<Block> entry : event.getAllMappings())
        {
            if (entry.key.getNamespace().equals(Mia.MODID))
            {
                Block block = getBlockFromPath(entry.key.getPath());
                if (block != null)
                    entry.remap(block);
            }
        }
    }
    
    @SubscribeEvent
    public static void missingItemMappingsEvent(RegistryEvent.MissingMappings<Item> event)
    {
        for (RegistryEvent.MissingMappings.Mapping<Item> entry : event.getAllMappings())
        {
            if (entry.key.getNamespace().equals(Mia.MODID))
            {
                Block block = getBlockFromPath(entry.key.getPath());
                if (block != null)
                {
                    Item itemBlock = Item.getItemFromBlock(block);
                    if (itemBlock != Items.AIR)
                        entry.remap(itemBlock);
                }
            }
        }
    }
    
    @Nullable
    private static Block getBlockFromPath(String path)
    {
        switch (path)
        {
            case "white_sandstone_wall_quark":
                if (MiaBlocks.whiteSandstone != null)
                    return MiaBlocks.whiteSandstone.wallFutureMc;
                break;
            case "white_sandstone_wall_futuremc":
                if (MiaBlocks.whiteSandstone != null)
                    return MiaBlocks.whiteSandstone.wallQuark;
                break;
        }
        
        return null;
    }
    
    @SubscribeEvent
    @Optional.Method(modid = ModIds.ConstantIds.AETHER)
    public static void onRegisterAetherAccessory(RegistryEvent.Register<AetherAccessory> event)
    {
        if (MiaItems.musicPlayer != null)
            event.getRegistry().register(new AetherAccessory(MiaItems.musicPlayer, AccessoryType.MISC));
        if (SizeUtils.isSizeComponentEnabled && MiaItems.koboldRing != null)
            event.getRegistry().register(new AetherAccessory(MiaItems.koboldRing, AccessoryType.RING));
    }
}
