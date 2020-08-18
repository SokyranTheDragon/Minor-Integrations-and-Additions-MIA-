package com.github.sokyranthedragon.mia.integrations.xu2;

import com.github.sokyranthedragon.mia.Mia;
import com.github.sokyranthedragon.mia.config.Xu2Configuration;
import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.integrations.jer.IJerIntegration;
import com.github.sokyranthedragon.mia.integrations.jer.custom.CustomPlantEntry;
import com.rwtema.extrautils2.backend.entries.XU2Entries;
import com.rwtema.extrautils2.blocks.BlockEnderLilly;
import com.rwtema.extrautils2.blocks.BlockRedOrchid;
import com.rwtema.extrautils2.villagers.XUVillagerCareer;
import gnu.trove.map.hash.TIntObjectHashMap;
import jeresources.api.IPlantRegistry;
import jeresources.api.drop.PlantDrop;
import jeresources.entry.PlantEntry;
import jeresources.entry.VillagerEntry;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.RegistryNamespaced;
import net.minecraftforge.fml.common.registry.VillagerRegistry.VillagerCareer;
import net.minecraftforge.fml.common.registry.VillagerRegistry.VillagerProfession;
import net.minecraftforge.registries.GameData;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@ParametersAreNonnullByDefault
class JerExtraUtilsIntegration implements IJerIntegration
{
    @Override
    public void addPlantDrops(IPlantRegistry plantRegistry, @Nullable Collection<PlantEntry> registers)
    {
        if (registers != null)
        {
            CustomPlantEntry redOrchid = new CustomPlantEntry(
                XU2Entries.blockRedOrchid.newStack(),
                XU2Entries.blockRedOrchid.value,
                BlockRedOrchid.GROWTH_STATE,
                new PlantDrop(XU2Entries.blockRedOrchid.newStack(), 1, 1),
                new PlantDrop(new ItemStack(Items.REDSTONE), 1, 3));
            
            redOrchid.setSoil(Blocks.REDSTONE_ORE.getDefaultState());
            registers.add(redOrchid);
            
            CustomPlantEntry enderLilly = new CustomPlantEntry(
                XU2Entries.blockEnderLilly.newStack(),
                XU2Entries.blockEnderLilly.value,
                BlockEnderLilly.GROWTH_STATE,
                new PlantDrop(XU2Entries.blockEnderLilly.newStack(), 1, 1),
                new PlantDrop(new ItemStack(Items.ENDER_PEARL), 1, 2));
            
            enderLilly.setSoil(Blocks.END_STONE.getDefaultState());
            registers.add(enderLilly);
        }
        else
        {
            plantRegistry.registerWithSoil(
                XU2Entries.blockEnderLilly.newStack(),
                Blocks.END_STONE.getDefaultState(),
                new PlantDrop(XU2Entries.blockEnderLilly.newStack(), 1, 1),
                new PlantDrop(new ItemStack(Items.ENDER_PEARL), 1, 2));
            
            plantRegistry.registerWithSoil(
                XU2Entries.blockRedOrchid.newStack(),
                Blocks.REDSTONE_ORE.getDefaultState(),
                new PlantDrop(XU2Entries.blockRedOrchid.newStack(), 1, 1),
                new PlantDrop(new ItemStack(Items.REDSTONE), 1, 3));
        }
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public void overrideExistingVillagerTrades(VillagerEntry villagerEntry)
    {
        try
        {
            RegistryNamespaced<ResourceLocation, VillagerProfession> wrapper = GameData.getWrapper(VillagerProfession.class);
            
            Field randomLists = XUVillagerCareer.class.getDeclaredField("randomLists");
            Field detLists = XUVillagerCareer.class.getDeclaredField("detLists");
            
            randomLists.setAccessible(true);
            detLists.setAccessible(true);
            
            VillagerProfession profession = wrapper.getObjectById(villagerEntry.getProfession());
            if (profession == null || profession.getRegistryName() == null || !profession.getRegistryName().getNamespace().equals(ModIds.EXTRA_UTILITIES.modId))
                return;
            
            VillagerCareer career = profession.getCareer(villagerEntry.getCareer());
            
            TIntObjectHashMap<List<EntityVillager.ITradeList>> tradeList = null;
            
            switch (villagerEntry.getName())
            {
                case "alchemist":
                    tradeList = (TIntObjectHashMap<List<EntityVillager.ITradeList>>) detLists.get(career);
                    break;
                case "red_mechanic":
                    tradeList = (TIntObjectHashMap<List<EntityVillager.ITradeList>>) randomLists.get(career);
                    break;
                case "shady_merchant":
                    if (Xu2Configuration.enableShadyMerchantJer)
                        tradeList = (TIntObjectHashMap<List<EntityVillager.ITradeList>>) randomLists.get(career);
                    break;
            }
            
            if (tradeList != null) villagerEntry.addITradeLists(new ArrayList<>(tradeList.valueCollection()));
        } catch (NoSuchFieldException | IllegalAccessException e)
        {
            Mia.LOGGER.error("Could not add villager trades for ExtraUtils", e);
        }
    }
    
    @Nonnull
    @Override
    public ModIds getModId()
    {
        return ModIds.EXTRA_UTILITIES;
    }
}
