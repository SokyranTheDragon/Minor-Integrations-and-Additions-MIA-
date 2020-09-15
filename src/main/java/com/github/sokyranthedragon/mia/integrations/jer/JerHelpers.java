package com.github.sokyranthedragon.mia.integrations.jer;

import com.github.sokyranthedragon.mia.Mia;
import jeresources.api.IDungeonRegistry;
import jeresources.util.LootTableHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTable;
import net.minecraft.world.storage.loot.LootTableManager;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

@ParametersAreNonnullByDefault
public class JerHelpers
{
    private JerHelpers()
    {
    }
    
    public static void addDungeonLootCategory(@Nullable World world, IDungeonRegistry registry, String name, ResourceLocation... loot)
    {
        addDungeonLootCategory(world, registry, name, "chests", loot);
    }
    
    public static void addDungeonLootCategory(@Nullable World world, IDungeonRegistry registry, String name, String prefix, ResourceLocation... loot)
    {
        String category = prefix + "/" + name;
        
        registry.registerCategory(category, "mia.jer.dungeon." + name);
    
        LootTableManager manager = null;
        if (world != null)
        {
            try
            {
                manager = LootTableHelper.getManager(world);
            }
            catch (Exception e)
            {
                Mia.LOGGER.error("Encountered an issue registering JER loot table helper! A lot of dungeon loot entries might be broken!");
                e.printStackTrace();
            }
        }
        
        if (manager == null)
        {
            for (ResourceLocation resourceLocation : loot)
                registry.registerChest(category, resourceLocation);
        }
        else
        {
            for (ResourceLocation resourceLocation : loot)
            {
                try
                {
                    LootTable lootTableFromLocation = manager.getLootTableFromLocation(resourceLocation);
                    registry.registerChest(category, lootTableFromLocation);
                }
                catch (Exception e)
                {
                    Mia.LOGGER.error("Encountered an issue registering JER dungeon loot entries in category: " + category);
                    registry.registerChest(category, resourceLocation);
                }
            }
        }
    }
    
    public static void removeDuplicateEntries(List<ItemStack> injectedDrops, List<ItemStack> baseDrops)
    {
        for (ItemStack item : baseDrops)
        {
            if (injectedDrops.remove(item) && injectedDrops.size() == 0)
                break;
        }
    }
}
