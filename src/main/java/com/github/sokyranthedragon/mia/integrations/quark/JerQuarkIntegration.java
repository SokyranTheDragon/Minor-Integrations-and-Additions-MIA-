package com.github.sokyranthedragon.mia.integrations.quark;

import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.integrations.jer.ExtraConditional;
import com.github.sokyranthedragon.mia.integrations.jer.IJerIntegration;
import com.github.sokyranthedragon.mia.integrations.jer.JustEnoughResources;
import com.github.sokyranthedragon.mia.integrations.jer.ResourceLocationWrapper;
import com.github.sokyranthedragon.mia.integrations.jer.custom.CustomVillagerEntry;
import com.github.sokyranthedragon.mia.utilities.QuarkUtils;
import jeresources.api.IDungeonRegistry;
import jeresources.api.IMobRegistry;
import jeresources.api.conditionals.Conditional;
import jeresources.api.conditionals.LightLevel;
import jeresources.api.drop.LootDrop;
import jeresources.entry.MobEntry;
import jeresources.registry.VillagerRegistry;
import jeresources.util.LootTableHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IMerchant;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.init.Biomes;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraft.world.storage.loot.LootTableManager;
import net.minecraftforge.common.BiomeDictionary;
import vazkii.quark.vanity.feature.WitchHat;
import vazkii.quark.world.entity.*;
import vazkii.quark.world.feature.*;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.*;

@ParametersAreNonnullByDefault
class JerQuarkIntegration implements IJerIntegration
{
    @Override
    public void addMobs(JustEnoughResources.CustomMobTableBuilder builder)
    {
        if (QuarkUtils.isFeatureEnabled(DepthMobs.class))
        {
            if (DepthMobs.enableAshen)
                builder.add(new ResourceLocationWrapper(LootTableList.ENTITIES_SKELETON, 0), EntityAshen.class);
            if (DepthMobs.enableDweller)
                builder.add(new ResourceLocationWrapper(LootTableList.ENTITIES_ZOMBIE), EntityDweller.class);
        }
        if (QuarkUtils.isFeatureEnabled(PirateShips.class) && !PirateShips.onlyHat)
            builder.add(new ResourceLocationWrapper(LootTableList.ENTITIES_SKELETON, 1), EntityPirate.class);
        if (QuarkUtils.isFeatureEnabled(Crabs.class))
            builder.add(EntityCrab.CRAB_LOOT_TABLE, EntityCrab.class);
        if (QuarkUtils.isFeatureEnabled(Foxhounds.class))
            builder.add(EntityFoxhound.FOXHOUND_LOOT_TABLE, EntityFoxhound.class);
        if (QuarkUtils.isFeatureEnabled(Frogs.class))
            builder.add(EntityFrog.FROG_LOOT_TABLE, EntityFrog.class);
        if (QuarkUtils.isFeatureEnabled(Stonelings.class))
            builder.add(EntityStoneling.LOOT_TABLE, EntityStoneling.class);
        if (QuarkUtils.isFeatureEnabled(Wraiths.class))
            builder.add(EntityWraith.LOOT_TABLE, EntityWraith.class);
    }
    
    @Override
    public void configureMob(ResourceLocation resource, EntityLivingBase entity, @Nullable LootTableManager manager, IMobRegistry mobRegistry)
    {
        LightLevel lightLevel = LightLevel.any;
        Set<Biome> validBiomes = new HashSet<>();
        List<LootDrop> loot = null;
        if (manager != null)
            loot = LootTableHelper.toDrops(manager.getLootTableFromLocation(resource));
        int experienceMin = 1;
        int experienceMax = 3;
        
        if (entity instanceof EntityAshen)
            Collections.addAll(validBiomes, DepthMobs.getBiomesWithMob(EntitySkeleton.class));
        else if (entity instanceof EntityDweller)
            Collections.addAll(validBiomes, DepthMobs.getBiomesWithMob(EntityZombie.class));
        else if (entity instanceof EntityPirate)
        {
            validBiomes.add(Biomes.OCEAN);
            validBiomes.add(Biomes.DEEP_OCEAN);
            
            if (loot != null)
                loot.add(new LootDrop(PirateShips.pirate_hat, 0.085f));
        }
        else if (entity instanceof EntityCrab)
            validBiomes.add(Biomes.BEACH);
        else if (entity instanceof EntityFoxhound || entity instanceof EntityWraith)
            validBiomes.addAll(BiomeDictionary.getBiomes(BiomeDictionary.Type.NETHER));
        else if (entity instanceof EntityFrog)
            validBiomes.add(Biomes.SWAMPLAND);
        else if (entity instanceof EntityStoneling)
        {
            Collections.addAll(validBiomes, DepthMobs.getBiomesWithMob(EntityZombie.class));
            
            if (loot != null)
            {
                List<LootDrop> carryDrops = LootTableHelper.toDrops(manager.getLootTableFromLocation(EntityStoneling.CARRY_LOOT_TABLE));
                for (LootDrop drop : carryDrops)
                    drop.addConditional(ExtraConditional.carryingItem);
                
                loot.addAll(carryDrops);
            }
        }
        
        if (entity instanceof EntityMob)
            experienceMin = experienceMax = 5;
        
        if (loot == null)
        {
            if (validBiomes.isEmpty())
                mobRegistry.register(entity, lightLevel, experienceMin, experienceMax, resource);
            else
                mobRegistry.register(entity, lightLevel, experienceMin, experienceMax, validBiomes.stream().map(Biome::getBiomeName).toArray(String[]::new), resource);
        }
        else
        {
            LootDrop[] drops = loot.toArray(new LootDrop[0]);
            if (validBiomes.isEmpty())
                mobRegistry.register(entity, lightLevel, experienceMin, experienceMax, drops);
            else
                mobRegistry.register(entity, lightLevel, experienceMin, experienceMax, validBiomes.stream().map(Biome::getBiomeName).toArray(String[]::new), drops);
        }
    }
    
    @Override
    public void overrideExistingMobDrops(MobEntry mobEntry)
    {
        if (mobEntry.getEntity() instanceof EntityWitch && QuarkUtils.isFeatureEnabled(WitchHat.class) && WitchHat.dropRate > 0)
        {
            Conditional[] conditionals;
            
            if (WitchHat.verifyTruePlayer && WitchHat.lootingBoost > 0)
                conditionals = new Conditional[]{ Conditional.affectedByLooting, Conditional.playerKill };
            else if (WitchHat.verifyTruePlayer)
                conditionals = new Conditional[]{ Conditional.playerKill };
            else if (WitchHat.lootingBoost > 0)
                conditionals = new Conditional[]{ Conditional.affectedByLooting };
            else
                conditionals = new Conditional[0];
            
            mobEntry.addDrop(new LootDrop(new ItemStack(WitchHat.witch_hat), 0, 1, (float) WitchHat.dropRate, 0, conditionals));
        }
    }
    
    @Override
    public void addDungeonLoot(IDungeonRegistry dungeonRegistry)
    {
        if (QuarkUtils.isFeatureEnabled(PirateShips.class) && !PirateShips.onlyHat)
        {
            final String pirateShip = "chests/quark_pirate_ship";
            
            dungeonRegistry.registerCategory(pirateShip, "mia.jer.dungeon.quark_pirate_ship");
            dungeonRegistry.registerChest(pirateShip, PirateShips.PIRATE_CHEST_LOOT_TABLE);
        }
        if (QuarkUtils.isFeatureEnabled(BuriedTreasure.class))
        {
            final String buriedTreasure = "chests/quark_buried_treasure";
            
            dungeonRegistry.registerCategory(buriedTreasure, "mia.jer.dungeon.quark_buried_treasure");
            dungeonRegistry.registerChest(buriedTreasure, new ResourceLocationWrapper(LootTableList.CHESTS_SIMPLE_DUNGEON));
        }
        if (QuarkUtils.isFeatureEnabled(NetherObsidianSpikes.class) && NetherObsidianSpikes.bigSpikeSpawners && NetherObsidianSpikes.bigSpikeChance > 0)
        {
            final String obsidianSpikes = "chests/quark_nether_obsidian_spikes";
            
            dungeonRegistry.registerCategory(obsidianSpikes, "mia.jer.dungeon.quark_nether_spikes");
            dungeonRegistry.registerChest(obsidianSpikes, new ResourceLocationWrapper(LootTableList.CHESTS_NETHER_BRIDGE));
        }
        if (QuarkUtils.isFeatureEnabled(VariedDungeons.class) && VariedDungeons.lootTable != null)
            dungeonRegistry.registerChest("chests/quark_simple_dungeon", new ResourceLocationWrapper(VariedDungeons.lootTable, -1));
    }
    
    @Override
    public void addVillagerTrades(VillagerRegistry villagerRegistry, boolean acceptsCustomEntries)
    {
        if (acceptsCustomEntries && QuarkUtils.isFeatureEnabled(Archaeologist.class))
        {
            ArrayList<List<EntityVillager.ITradeList>> allTrades = new ArrayList<>();
            ArrayList<EntityVillager.ITradeList> trades = new ArrayList<>();
            allTrades.add(trades);
            
            trades.add(new BasicTrade(new ItemStack(Items.EMERALD, 2), new ItemStack(Items.BONE, 3)));
            trades.add(new BasicTrade(new ItemStack(Items.BONE, 10), new ItemStack(Items.EMERALD, 1)));
            trades.add(new BasicTrade(new ItemStack(Items.GUNPOWDER, 7), new ItemStack(Items.EMERALD, 1)));
            trades.add(new BasicTrade(new ItemStack(Items.COAL, 16), new ItemStack(Items.EMERALD, 1)));
            trades.add(new BasicTrade(new ItemStack(Items.EMERALD, 12), new ItemStack(Items.DIAMOND, 1)));
            trades.add(new BasicTrade(new ItemStack(Items.EMERALD, 8), new ItemStack(Items.IRON_PICKAXE, 1)));
            trades.add(new BasicTrade(new ItemStack(Items.EMERALD, 6), new ItemStack(Items.IRON_SHOVEL, 1)));
            if (Archaeologist.enableHat && Archaeologist.sellHat)
                trades.add(new BasicTrade(new ItemStack(Items.EMERALD, 9), new ItemStack(Archaeologist.archaeologist_hat, 1)));
            
            villagerRegistry.addVillagerEntry(new CustomVillagerEntry("quark:archaeologist", allTrades)
            {
                @Override
                public EntityLivingBase getEntity(@Nonnull Minecraft minecraft)
                {
                    return new EntityArchaeologist(minecraft.world);
                }
                
                @Override
                public String getDisplayName()
                {
                    return "entity." + getName() + ".name";
                }
            });
        }
    }
    
    @Nonnull
    @Override
    public ModIds getModId()
    {
        return ModIds.QUARK;
    }
    
    private static class BasicTrade implements EntityVillager.ITradeList
    {
        private ItemStack input;
        private ItemStack output;
        
        public BasicTrade(ItemStack input, ItemStack output)
        {
            this.input = input;
            this.output = output;
        }
        
        @Override
        public void addMerchantRecipe(IMerchant iMerchant, MerchantRecipeList merchantRecipeList, Random random)
        {
            merchantRecipeList.add(new MerchantRecipe(input, output));
        }
    }
}