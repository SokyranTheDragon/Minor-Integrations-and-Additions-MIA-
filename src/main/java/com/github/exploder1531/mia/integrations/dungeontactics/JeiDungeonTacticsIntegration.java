package com.github.exploder1531.mia.integrations.dungeontactics;

import com.github.exploder1531.mia.Mia;
import com.github.exploder1531.mia.integrations.ModIds;
import com.github.exploder1531.mia.integrations.jei.IJeiIntegration;
import com.github.exploder1531.mia.integrations.jei.MiaJeiPlugin;
import com.github.exploder1531.mia.integrations.jei.categories.cauldron.CauldronCategory;
import com.github.exploder1531.mia.integrations.jei.categories.cauldron.CauldronEntry;
import com.github.exploder1531.mia.integrations.jei.categories.cauldron.CauldronRegistry;
import com.github.exploder1531.mia.integrations.jei.categories.cauldron.CauldronWrapper;
import com.github.exploder1531.mia.integrations.jei.categories.lootbag.LootBagCategory;
import com.github.exploder1531.mia.integrations.jei.categories.lootbag.LootBagEntry;
import com.github.exploder1531.mia.integrations.jei.categories.lootbag.LootBagRegistry;
import com.github.exploder1531.mia.integrations.jei.categories.lootbag.LootBagWrapper;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.oredict.OreDictionary;
import pegbeard.dungeontactics.handlers.DTBlocks;
import pegbeard.dungeontactics.handlers.DTEffects;
import pegbeard.dungeontactics.handlers.DTItems;
import pegbeard.dungeontactics.handlers.DTLoots;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static com.github.exploder1531.mia.integrations.ModLoadStatus.jerLoaded;

public class JeiDungeonTacticsIntegration implements IJeiIntegration
{
    @Override
    public void register(IModRegistry registry, Collection<String> registeredCategories)
    {
        if (registeredCategories.add(MiaJeiPlugin.Categories.DUNGEON_TACTICS_CAULDRON))
        {
            registry.handleRecipes(CauldronEntry.class, CauldronWrapper::new, MiaJeiPlugin.Categories.DUNGEON_TACTICS_CAULDRON);
            registry.addRecipes(CauldronRegistry.getRecipesOrEmpty(), MiaJeiPlugin.Categories.DUNGEON_TACTICS_CAULDRON);
        }
        if (registeredCategories.add(MiaJeiPlugin.Categories.LOOT_BAG))
        {
            registry.handleRecipes(LootBagEntry.class, LootBagWrapper::new, MiaJeiPlugin.Categories.LOOT_BAG);
            registry.addRecipes(LootBagRegistry.getRecipesOrEmpty(), MiaJeiPlugin.Categories.LOOT_BAG);
        }
        
        registry.addRecipeCatalyst(new ItemStack(DTBlocks.ALCHEMYCAULDRON), MiaJeiPlugin.Categories.DUNGEON_TACTICS_CAULDRON);
        registry.addRecipeCatalyst(new ItemStack(DTItems.BAG_ARBOUR), MiaJeiPlugin.Categories.LOOT_BAG);
        registry.addRecipeCatalyst(new ItemStack(DTItems.BAG_BOOK), MiaJeiPlugin.Categories.LOOT_BAG);
        registry.addRecipeCatalyst(new ItemStack(DTItems.BAG_FOOD), MiaJeiPlugin.Categories.LOOT_BAG);
        registry.addRecipeCatalyst(new ItemStack(DTItems.BAG_MAGIC), MiaJeiPlugin.Categories.LOOT_BAG);
        registry.addRecipeCatalyst(new ItemStack(DTItems.BAG_ORE), MiaJeiPlugin.Categories.LOOT_BAG);
        registry.addRecipeCatalyst(new ItemStack(DTItems.BAG_POTION), MiaJeiPlugin.Categories.LOOT_BAG);
        registry.addRecipeCatalyst(new ItemStack(DTItems.BAG_QUIVER), MiaJeiPlugin.Categories.LOOT_BAG);
        registry.addRecipeCatalyst(new ItemStack(DTItems.BAG_RECORD), MiaJeiPlugin.Categories.LOOT_BAG);
        registry.addRecipeCatalyst(new ItemStack(DTItems.BAG_SAMHAIN), MiaJeiPlugin.Categories.LOOT_BAG);
        registry.addRecipeCatalyst(new ItemStack(DTItems.BAG_SOLSTICE), MiaJeiPlugin.Categories.LOOT_BAG);
        registry.addRecipeCatalyst(new ItemStack(DTItems.BAG_TOOL), MiaJeiPlugin.Categories.LOOT_BAG);
        
        registry.addIngredientInfo(Arrays.asList(
                new ItemStack(DTItems.FISH_MUSCLE),
                new ItemStack(DTItems.FISH_OBSIDIAN),
                new ItemStack(DTItems.FISH_LAVA),
                new ItemStack(DTItems.FISH_TUNNEL),
                new ItemStack(DTItems.FISH_FLYING),
                new ItemStack(DTItems.FISH_SWIFT),
                new ItemStack(DTItems.FISH_LUNG)),
                VanillaTypes.ITEM, "mia.jei.info.dt.fishing");
        
        registry.addIngredientInfo(Arrays.asList(
                new ItemStack(DTItems.SLINGSHOT),
                new ItemStack(DTItems.IRONRING)),
                VanillaTypes.ITEM, "mia.jei.info.dt.fishing_treasure");
        
        registry.addIngredientInfo(new ItemStack(DTItems.REXO_LEGGINGS), VanillaTypes.ITEM, "mia.jei.dt.info.leggings");
        registry.addIngredientInfo(new ItemStack(DTItems.REXO_GOGGLES), VanillaTypes.ITEM, "mia.jei.dt.info.goggles");
        registry.addIngredientInfo(new ItemStack(DTItems.REXO_BOOTS), VanillaTypes.ITEM, "mia.jei.dt.info.boots");
        
        registry.addIngredientInfo(new ItemStack(DTItems.ESCAPEROPE), VanillaTypes.ITEM, "mia.jei.dt.info.rope");
        registry.addIngredientInfo(new ItemStack(DTItems.PHYLACTERY), VanillaTypes.ITEM, "mia.jei.dt.info.phylactery");
        registry.addIngredientInfo(new ItemStack(DTItems.ENDERBAG), VanillaTypes.ITEM, "mia.jei.dt.info.bag_of_hoarding");
        registry.addIngredientInfo(new ItemStack(DTItems.DUCT_TAPE), VanillaTypes.ITEM, "mia.jei.dt.info.duct_tape");
        registry.addIngredientInfo(new ItemStack(DTItems.HEART_DROP), VanillaTypes.ITEM, "mia.jei.dt.info.heart_drop");
        registry.addIngredientInfo(new ItemStack(DTBlocks.LANTERN_IRON), VanillaTypes.ITEM, "mia.jei.dt.info.lantern_iron");
        registry.addIngredientInfo(new ItemStack(DTBlocks.LANTERN_MAGIC), VanillaTypes.ITEM, "mia.jei.dt.info.lantern_magic");
        registry.addIngredientInfo(new ItemStack(DTItems.ENGINEERS_DUNGAREES), VanillaTypes.ITEM, "mia.jei.dt.info.engineer_pants");
        registry.addIngredientInfo(new ItemStack(DTItems.PEG_HAMMER), VanillaTypes.ITEM, "mia.jei.dt.info.peg_hammer");
    }
    
    @Override
    public void registerCategories(IRecipeCategoryRegistration registry, Collection<String> registeredCategories)
    {
        if (registeredCategories.add(MiaJeiPlugin.Categories.DUNGEON_TACTICS_CAULDRON))
            registry.addRecipeCategories(new CauldronCategory(registry.getJeiHelpers().getGuiHelper()));
        if (registeredCategories.add(MiaJeiPlugin.Categories.LOOT_BAG))
            registry.addRecipeCategories(new LootBagCategory(registry.getJeiHelpers().getGuiHelper(), DTItems.BAG_ARBOUR));
    }
    
    @Override
    public void registerRecipes()
    {
        CauldronRegistry cauldronRegistry = CauldronRegistry.getInstance();
    
        if (cauldronRegistry != null)
        {
            // Weapon imbuing
            registerImbuing(cauldronRegistry, Items.IRON_SWORD, DTEffects.POISONAILMENT, new ItemStack(DTBlocks.FLOWER_AILMENT));
            registerImbuing(cauldronRegistry, Items.IRON_SWORD, DTEffects.POISONBARK, new ItemStack(DTBlocks.FLOWER_BARK));
            registerImbuing(cauldronRegistry, Items.IRON_SWORD, DTEffects.POISONBRAMBLE, new ItemStack(DTBlocks.FLOWER_BRAMBLE));
            registerImbuing(cauldronRegistry, Items.IRON_SWORD, DTEffects.POISONCINDER, new ItemStack(DTBlocks.FLOWER_CINDER));
            registerImbuing(cauldronRegistry, Items.IRON_SWORD, DTEffects.POISONFADE, new ItemStack(DTBlocks.FLOWER_FADE));
            registerImbuing(cauldronRegistry, Items.IRON_SWORD, DTEffects.POISONFEATHER, new ItemStack(DTBlocks.FLOWER_FEATHER));
            registerImbuing(cauldronRegistry, Items.IRON_SWORD, DTEffects.POISONSANGUINE, new ItemStack(DTBlocks.FLOWER_SANGUINE));
            registerImbuing(cauldronRegistry, Items.IRON_SWORD, DTEffects.POISONTANGLE, new ItemStack(DTBlocks.FLOWER_TANGLE));
        
            // Cooking
            registerCooking(cauldronRegistry, Items.IRON_SHOVEL, Items.GUNPOWDER, new ItemStack(DTItems.CHERRYBOMB, 4), new ItemStack(Items.REDSTONE, 4), new ItemStack(Items.FLINT));
            registerCooking(cauldronRegistry, Items.IRON_SHOVEL, Items.GLOWSTONE_DUST, new ItemStack(DTItems.GLOWCURRENT, 4), new ItemStack(Items.REDSTONE, 4), new ItemStack(Items.BLAZE_POWDER));
            registerCooking(cauldronRegistry, Items.IRON_SHOVEL, Items.BLAZE_POWDER, new ItemStack(DTItems.INCINDIBERRY, 4), new ItemStack(Items.REDSTONE, 4), new ItemStack(Items.COAL));
            // 1 to 3 + fortune level
            registerCooking(cauldronRegistry, Items.IRON_SHOVEL, DTItems.MAGIC_POWDER, new ItemStack(Items.GLOWSTONE_DUST), new ItemStack(Blocks.RED_MUSHROOM), new ItemStack(Blocks.BROWN_MUSHROOM));
            registerCooking(cauldronRegistry, Items.IRON_SHOVEL, new ItemStack(Items.CLAY_BALL, 4), new ItemStack(Items.SLIME_BALL, 3), new ItemStack(Blocks.SAND));
            registerCooking(cauldronRegistry, Items.IRON_SHOVEL, Items.LEATHER, Items.SLIME_BALL, new ItemStack(Items.ROTTEN_FLESH, 3), new ItemStack(Items.SUGAR, 2));
            // Any (vanilla) leaves
            registerCooking(cauldronRegistry, Items.IRON_SHOVEL, new ItemStack(Blocks.DIRT), new ItemStack(Blocks.LEAVES, 2, OreDictionary.WILDCARD_VALUE), new ItemStack(Blocks.SAND));
            registerCooking(cauldronRegistry, Items.IRON_SHOVEL, new ItemStack(Blocks.MYCELIUM), new ItemStack(Blocks.DIRT), new ItemStack(Blocks.RED_MUSHROOM, 2), new ItemStack(Blocks.BROWN_MUSHROOM, 2));
        }
        else
            Mia.LOGGER.error("Could not access Alchemical Cauldron recipe registry, this shouldn't have happened as Dungeon Tactics is loaded. Something is very wrong.");
    
        LootBagRegistry lootBagRegistry = LootBagRegistry.getInstance();
    
        if (lootBagRegistry != null)
        {
            registerLootBag(lootBagRegistry, DTItems.BAG_ARBOUR, DTLoots.ARBOUR_LOOT);
            registerLootBag(lootBagRegistry, DTItems.BAG_BOOK, DTLoots.BOOK_LOOT);
            registerLootBag(lootBagRegistry, DTItems.BAG_FOOD, DTLoots.FOOD_LOOT);
            registerLootBag(lootBagRegistry, DTItems.BAG_MAGIC, DTLoots.MAGIC_LOOT);
            registerLootBag(lootBagRegistry, DTItems.BAG_ORE, DTLoots.ORE_LOOT);
            registerLootBag(lootBagRegistry, DTItems.BAG_POTION, DTLoots.POTION_LOOT);
            registerLootBag(lootBagRegistry, DTItems.BAG_QUIVER, DTLoots.QUIVER_LOOT);
            registerLootBag(lootBagRegistry, DTItems.BAG_RECORD, DTLoots.RECORD_LOOT);
            registerLootBag(lootBagRegistry, DTItems.BAG_SAMHAIN, DTLoots.SAMHAIN_LOOT);
            registerLootBag(lootBagRegistry, DTItems.BAG_SOLSTICE, DTLoots.SOLSTICE_LOOT);
            registerLootBag(lootBagRegistry, DTItems.BAG_TOOL, DTLoots.TOOL_LOOT);
        }
        else if (jerLoaded)
            Mia.LOGGER.error("Could not access Loot Bag recipe registry, this shouldn't have happened as Dungeon Tactics and JER are loaded. Something is very wrong.");
    }
    
    @Nonnull
    @Override
    public String getModId()
    {
        return ModIds.DUNGEON_TACTICS;
    }
    
    @SuppressWarnings("SameParameterValue")
    private void registerImbuing(CauldronRegistry registry, Item weapon, Enchantment enchantment, ItemStack... input)
    {
        ItemStack output = new ItemStack(weapon);
        output.addEnchantment(enchantment, 1);
        registry.registerCauldronRecipe(new CauldronEntry(new ItemStack(weapon), output, input));
    }
    
    @SuppressWarnings("SameParameterValue")
    private void registerCooking(CauldronRegistry registry, Item spoon, Item output, ItemStack... input)
    {
        registerCooking(registry, spoon, output, null, input);
    }
    
    @SuppressWarnings("SameParameterValue")
    private void registerCooking(CauldronRegistry registry, Item spoon, ItemStack output, ItemStack... input)
    {
        registerCooking(registry, spoon, output, null, input);
    }
    
    private void registerCooking(CauldronRegistry registry, Item spoon, Item output, Item byproduct, ItemStack... input)
    {
        registerCooking(registry, spoon, new ItemStack(output), byproduct, input);
    }
    
    private void registerCooking(CauldronRegistry registry, Item spoon, ItemStack output, Item byproduct, ItemStack... input)
    {
        if (byproduct != null)
            registry.registerCauldronRecipe(new CauldronEntry(new ItemStack(spoon), output, new ItemStack(byproduct), input));
        else
            registry.registerCauldronRecipe(new CauldronEntry(new ItemStack(spoon), output, input));
    }
    
    private void registerLootBag(LootBagRegistry registry, Item lootBag, ResourceLocation possibleLoot)
    {
        List<LootBagEntry> entries = LootBagEntry.getEntries(new ItemStack(lootBag), possibleLoot);
        
        for (LootBagEntry entry : entries)
            registry.registerLootBagRecipe(entry);
    }
}
