package com.github.sokyranthedragon.mia.integrations.charm;

import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.integrations.jer.IJerIntegration;
import com.github.sokyranthedragon.mia.integrations.jer.JerLightHelper;
import com.github.sokyranthedragon.mia.integrations.jer.JustEnoughResources;
import jeresources.api.IDungeonRegistry;
import jeresources.api.IMobRegistry;
import jeresources.api.conditionals.Conditional;
import jeresources.api.drop.LootDrop;
import jeresources.entry.MobEntry;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionType;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraft.world.storage.loot.LootTableManager;
import org.jetbrains.annotations.NotNull;
import svenhjol.charm.Charm;
import svenhjol.charm.base.CharmLootTables;
import svenhjol.charm.brewing.potion.DecayPotion;
import svenhjol.charm.crafting.feature.Barrel;
import svenhjol.charm.crafting.feature.BookshelfChest;
import svenhjol.charm.crafting.feature.Composter;
import svenhjol.charm.tweaks.feature.WitchesDropDecay;
import svenhjol.charm.tweaks.feature.WitchesDropLuck;
import svenhjol.charm.world.compat.FutureMcBlocks;
import svenhjol.charm.world.entity.EntitySpectre;
import svenhjol.charm.world.feature.AbandonedCrates;
import svenhjol.charm.world.feature.Spectre;
import svenhjol.charm.world.feature.SwampHutDecorations;
import svenhjol.charm.world.feature.VillageDecorations;
import svenhjol.meson.helper.LootHelper;

import javax.annotation.Nullable;
import java.util.List;

class JerCharmIntegration implements IJerIntegration
{
    @Override
    public void addMobs(@NotNull JustEnoughResources.CustomMobTableBuilder builder)
    {
        if (Charm.hasFeature(Spectre.class))
            builder.add(EntitySpectre.LOOT_TABLE, EntitySpectre.class);
    }
    
    @Override
    public void configureMob(@NotNull ResourceLocation resource, @NotNull EntityLivingBase entity, @Nullable LootTableManager manager, @NotNull IMobRegistry mobRegistry)
    {
        if (entity instanceof EntitySpectre)
            mobRegistry.register(entity, JerLightHelper.getLightLevelBelow(Spectre.despawnLight), 5, resource);
    }
    
    @Override
    public void overrideExistingMobDrops(@NotNull MobEntry mobEntry)
    {
        if (mobEntry.getEntity() instanceof EntityWitch)
        {
            if (Charm.hasFeature(WitchesDropDecay.class))
            {
                ItemStack item = new ItemStack(Items.POTIONITEM);
                PotionType decay = DecayPotion.type;
                PotionUtils.addPotionToItemStack(item, decay);
                
                mobEntry.addDrop(new LootDrop(item, 0, 1, (float) WitchesDropDecay.dropChance, 0, Conditional.affectedByLooting));
            }
            
            if (Charm.hasFeature(WitchesDropLuck.class))
            {
                ItemStack item = new ItemStack(Items.POTIONITEM);
                PotionType luck = PotionType.REGISTRY.getObject(new ResourceLocation("luck"));
                PotionUtils.addPotionToItemStack(item, luck);
                
                mobEntry.addDrop(new LootDrop(item, 0, 1, (float) WitchesDropLuck.dropChance, 0, Conditional.affectedByLooting));
            }
        }
    }
    
    @Override
    public void addDungeonLoot(@NotNull IDungeonRegistry dungeonRegistry, @NotNull World world)
    {
        if (Charm.hasFeature(VillageDecorations.class))
        {
            if (VillageDecorations.storage)
            {
                final String butcher = "chests/charm_butcher";
                final String carpenter = "chests/charm_carpenter";
                final String fisherman = "chests/charm_fisherman";
                final String librarian = "chests/charm_librarian";
                final String priest = "chests/charm_priest";
                final String shepherd = "chests/charm_shepherd";
                final String smith = "chests/charm_smith";
                
                dungeonRegistry.registerCategory(butcher, "mia.jer.dungeon.charm_butcher");
                dungeonRegistry.registerCategory(carpenter, "mia.jer.dungeon.charm_carpenter");
                dungeonRegistry.registerCategory(fisherman, "mia.jer.dungeon.charm_fisherman");
                dungeonRegistry.registerCategory(librarian, "mia.jer.dungeon.charm_librarian");
                dungeonRegistry.registerCategory(priest, "mia.jer.dungeon.charm_priest");
                dungeonRegistry.registerCategory(shepherd, "mia.jer.dungeon.charm_shepherd");
                dungeonRegistry.registerCategory(smith, "mia.jer.dungeon.charm_smith");
                
                // Butcher
                dungeonRegistry.registerChest(butcher, CharmLootTables.VILLAGE_BUTCHER);
                // Carpenter
                dungeonRegistry.registerChest(carpenter, CharmLootTables.VILLAGE_CARPENTER);
                // Fisherman
                dungeonRegistry.registerChest(fisherman, CharmLootTables.VILLAGE_FISHERMAN);
                // Librarian
                if (Charm.hasFeature(BookshelfChest.class)) // Bookshelf loot
                {
                    for (ResourceLocation loot : LootHelper.getLootTables(LootHelper.RARITY.COMMON, LootHelper.TYPE.BOOK))
                        dungeonRegistry.registerChest(librarian, loot);
                }
                dungeonRegistry.registerChest(librarian, CharmLootTables.VILLAGE_LIBRARIAN);
                dungeonRegistry.registerChest(librarian, LootTableList.CHESTS_STRONGHOLD_LIBRARY); // Rare, 5% chance
                dungeonRegistry.registerChest(librarian, LootTableList.CHESTS_WOODLAND_MANSION); // Rare, 5% chance
                // Priest
                dungeonRegistry.registerChest(priest, CharmLootTables.VILLAGE_PRIEST);
                dungeonRegistry.registerChest(priest, LootTableList.CHESTS_STRONGHOLD_CROSSING); // Rare, 5% chance
                // Shepherd
                dungeonRegistry.registerChest(shepherd, CharmLootTables.VILLAGE_SHEPHERD);
                // Smith
                dungeonRegistry.registerChest(smith, CharmLootTables.VILLAGE_SMITH);
                dungeonRegistry.registerChest(smith, LootTableList.CHESTS_ABANDONED_MINESHAFT);
            }
            
            // Currently requires either barrels or composter, but accept FutureMC barrels
            if (VillageDecorations.barrelsChance > 0 && (Charm.hasFeature(Barrel.class) || (FutureMcBlocks.barrel != null && Charm.hasFeature(Composter.class))))
            {
                final String farmer = "chests/charm_farmer";
                
                dungeonRegistry.registerCategory(farmer, "mia.jer.dungeon.charm_farmer");
                dungeonRegistry.registerChest(farmer, CharmLootTables.VILLAGE_FARMER);
            }
        }
        
        if (Charm.hasFeature(SwampHutDecorations.class))
        {
            List<ResourceLocation> tables = LootHelper.getLootTables(LootHelper.RARITY.COMMON, LootHelper.TYPE.POTION);
            
            if (!tables.isEmpty())
            {
                final String swampHut = "chests/charm_swamp_hut";
                
                dungeonRegistry.registerCategory(swampHut, "mia.jer.dungeon.charm_swamp_hut");
                
                for (ResourceLocation table : tables)
                    dungeonRegistry.registerChest(swampHut, table);
            }
        }
        
        if (Charm.hasFeature(AbandonedCrates.class))
        {
            final String crates = "chests/charm_abandoned_crates";
            
            dungeonRegistry.registerCategory(crates, "mia.jer.dungeon.charm_abandoned_crates");
            
            dungeonRegistry.registerChest(crates, CharmLootTables.TREASURE_DANGEROUS);
            dungeonRegistry.registerChest(crates, CharmLootTables.TREASURE_EXPLOSIVE);
            
            for (ResourceLocation loot : LootHelper.getLootTables(LootHelper.RARITY.COMMON, LootHelper.TYPE.MISC))
                dungeonRegistry.registerChest(crates, loot);
            for (ResourceLocation loot : LootHelper.getLootTables(LootHelper.RARITY.UNCOMMON, LootHelper.TYPE.MISC))
                dungeonRegistry.registerChest(crates, loot);
            for (ResourceLocation loot : LootHelper.getLootTables(LootHelper.RARITY.VALUABLE, LootHelper.TYPE.MISC))
                dungeonRegistry.registerChest(crates, loot);
            for (ResourceLocation loot : LootHelper.getLootTables(LootHelper.RARITY.RARE, LootHelper.TYPE.MISC))
                dungeonRegistry.registerChest(crates, loot);
        }
    }
    
    @NotNull
    @Override
    public ModIds getModId()
    {
        return ModIds.CHARM;
    }
}
