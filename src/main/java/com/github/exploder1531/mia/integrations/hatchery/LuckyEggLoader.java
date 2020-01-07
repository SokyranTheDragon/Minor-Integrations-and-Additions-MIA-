package com.github.exploder1531.mia.integrations.hatchery;

import com.gendeathrow.hatchery.core.config.ConfigLootHandler;
import com.github.exploder1531.mia.Mia;
import com.github.exploder1531.mia.integrations.ModIds;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.annotation.Nonnull;
import java.io.*;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

class LuckyEggLoader
{
    public final List<ConfigLootHandler.ItemDrop> drops = Lists.newLinkedList();
    final Set<String> loadedFiles = Sets.newHashSet();
    
    void tryCreateNewLootFile(ModIds modId, int configVersion, @Nonnull List<ConfigLootHandler.ItemDrop> loot)
    {
        loadedFiles.add(modId.modId);
        File lootFile = new File("config/mia/lucky_eggs/" + modId + ".json");
        
        if (!lootFile.isFile())
        {
            if (lootFile.getParentFile().isDirectory() || lootFile.getParentFile().mkdirs())
                writeConfig(lootFile, configVersion, loot);
            
            return;
        }
        
        LuckyEggLootConfig config = loadConfig(lootFile);
        if (config == null)
        {
            drops.addAll(loot);
            return;
        }
        
        if (config.modCanAutomaticallyReplaceConfig && config.configVersion < configVersion)
            writeConfig(lootFile, configVersion, loot);
        else
        {
            config.cleanLoot();
            drops.addAll(config.loot);
        }
    }
    
    void loadRemainingFiles()
    {
        File configDir = new File("config/mia/lucky_eggs");
        
        if (configDir.isDirectory())
        {
            File[] configFiles = configDir.listFiles((dir, name) -> name.endsWith(".json") && !loadedFiles.contains(name.substring(0, name.length() - 5)));
            
            if (configFiles == null)
                return;
            
            for (File file : configFiles)
            {
                LuckyEggLootConfig config = loadConfig(file);
                
                if (config != null)
                {
                    config.cleanLoot();
                    drops.addAll(config.loot);
                }
            }
        }
    }
    
    private void writeConfig(File lootFile, int configVersion, List<ConfigLootHandler.ItemDrop> loot)
    {
        drops.addAll(loot);
        
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        LuckyEggLootConfig config = new LuckyEggLootConfig(configVersion, loot);
        
        try (FileWriter writer = new FileWriter(lootFile))
        {
            gson.toJson(config, writer);
        } catch (IOException ignored)
        {
            Mia.LOGGER.warn("Could not save lucky egg config: " + lootFile + ", config version: " + configVersion + ", drop entries: " + loot.size());
        }
    }
    
    private LuckyEggLootConfig loadConfig(File lootFile)
    {
        Gson gson = new Gson();
        LuckyEggLootConfig config = null;
        
        try (Reader reader = new FileReader(lootFile))
        {
            config = gson.fromJson(reader, LuckyEggLootConfig.class);
        } catch (Exception ignored)
        {
            Mia.LOGGER.warn("Could not load lucky egg config: " + lootFile);
        }
        
        return config;
    }
    
    private static class LuckyEggLootConfig
    {
        boolean modCanAutomaticallyReplaceConfig = true;
        int configVersion;
        List<ConfigLootHandler.ItemDrop> loot;
        
        LuckyEggLootConfig(int configVersion, @Nonnull List<ConfigLootHandler.ItemDrop> loot)
        {
            this.configVersion = configVersion;
            this.loot = loot;
        }
        
        void cleanLoot()
        {
            loot = loot.stream().filter(entry -> entry.getItem() != null).collect(Collectors.toList());
        }
    }
}
