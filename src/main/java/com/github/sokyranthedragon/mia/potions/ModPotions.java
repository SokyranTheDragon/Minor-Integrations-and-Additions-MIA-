package com.github.sokyranthedragon.mia.potions;

import com.github.sokyranthedragon.mia.Mia;
import com.github.sokyranthedragon.mia.integrations.thermalexpansion.ThermalExpansion;
import com.github.sokyranthedragon.mia.utilities.size.SizeUtils;
import net.minecraft.init.Items;
import net.minecraft.init.PotionTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionType;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.brewing.BrewingRecipe;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

@Mod.EventBusSubscriber(modid = Mia.MODID)
public class ModPotions
{
    public static final Potion shrinkingPotion = new ShrinkingPotion();
    public static final Potion growthPotion = new GrowthPotion();
    public static final Potion shrinkingVenom = new ShrinkingVenom();
    public static final Potion growthVenom = new GrowthVenom();
    public static final Potion sizeStabilizationPotion = new SizeStabilizationPotion();
    
    @SubscribeEvent
    public static void registerPotions(RegistryEvent.Register<Potion> event)
    {
        if (!SizeUtils.isSizeComponentEnabled)
            return;
        
        event.getRegistry().register(shrinkingPotion);
        event.getRegistry().register(growthPotion);
        event.getRegistry().register(shrinkingVenom);
        event.getRegistry().register(growthVenom);
        event.getRegistry().register(sizeStabilizationPotion);
    }
    
    @SubscribeEvent
    public static void registerPotionEffects(RegistryEvent.Register<PotionType> event)
    {
        if (!SizeUtils.isSizeComponentEnabled)
            return;
    
        PotionType shrinking_potion1 = registerPotionType(event, shrinkingPotion, "shrinking_potion1", 20 * 5, 0);
        PotionType shrinking_potion1_long = registerPotionType(event, shrinkingPotion, "shrinking_potion1_long", 20 * 10, 0);
        PotionType shrinking_potion1_longer = registerPotionType(event, shrinkingPotion, "shrinking_potion1_longer", 20 * 15, 0);
        PotionType shrinking_potion2 = registerPotionType(event, shrinkingPotion, "shrinking_potion2", 20 * 5, 1);
        PotionType shrinking_potion2_long = registerPotionType(event, shrinkingPotion, "shrinking_potion2_long", 20 * 10, 1);
        PotionType shrinking_potion2_longer = registerPotionType(event, shrinkingPotion, "shrinking_potion2_longer", 20 * 15, 1);
        PotionType shrinking_potion3 = registerPotionType(event, shrinkingPotion, "shrinking_potion3", 20 * 5, 2);
        PotionType shrinking_potion3_long = registerPotionType(event, shrinkingPotion, "shrinking_potion3_long", 20 * 10, 2);
        
        PotionType growth_potion1 = registerPotionType(event, growthPotion, "growth_potion1", 20 * 5, 0);
        PotionType growth_potion1_long = registerPotionType(event, growthPotion, "growth_potion1_long", 20 * 10, 0);
        PotionType growth_potion1_longer = registerPotionType(event, growthPotion, "growth_potion1_longer", 20 * 15, 0);
        PotionType growth_potion2 = registerPotionType(event, growthPotion, "growth_potion2", 20 * 5, 1);
        PotionType growth_potion2_long = registerPotionType(event, growthPotion, "growth_potion2_long", 20 * 10, 1);
        PotionType growth_potion2_longer = registerPotionType(event, growthPotion, "growth_potion2_longer", 20 * 15, 1);
        PotionType growth_potion3 = registerPotionType(event, growthPotion, "growth_potion3", 20 * 5, 2);
        PotionType growth_potion3_long = registerPotionType(event, growthPotion, "growth_potion3_long", 20 * 10, 2);
        
        PotionType shrinking_venom = registerPotionType(event, shrinkingVenom, "shrinking_venom", 20 * 5, 0);
        PotionType growth_venom = registerPotionType(event, growthVenom, "growth_venom", 20 * 5, 0);
        
        PotionType size_stabilization_potion = registerPotionType(event, sizeStabilizationPotion, "size_stabilization_potion", 2147483647, 0);
        
        // Shrinking potions
        registerBrewing(shrinking_potion1, PotionTypes.THICK, new ItemStack(Items.CHORUS_FRUIT));
        registerBrewing(shrinking_potion2, shrinking_potion1, new ItemStack(Items.GLOWSTONE_DUST));
        registerBrewing(shrinking_potion3, shrinking_potion2, new ItemStack(Items.GLOWSTONE_DUST));
        registerBrewing(shrinking_potion1_long, shrinking_potion1, new ItemStack(Items.REDSTONE));
        registerBrewing(shrinking_potion2_long, shrinking_potion2, new ItemStack(Items.REDSTONE));
        registerBrewing(shrinking_potion3_long, shrinking_potion3, new ItemStack(Items.REDSTONE));
        registerBrewing(shrinking_potion1_longer, shrinking_potion1_long, new ItemStack(Items.REDSTONE));
        registerBrewing(shrinking_potion2_longer, shrinking_potion2_long, new ItemStack(Items.REDSTONE));
        registerBrewing(shrinking_potion2_long, shrinking_potion1_long, new ItemStack(Items.GLOWSTONE_DUST));
        registerBrewing(shrinking_potion3_long, shrinking_potion2_long, new ItemStack(Items.GLOWSTONE_DUST));
        registerBrewing(shrinking_potion2_longer, shrinking_potion1_longer, new ItemStack(Items.GLOWSTONE_DUST));
        
        // Growth potions
        registerBrewing(growth_potion1, PotionTypes.AWKWARD, new ItemStack(Items.CHORUS_FRUIT));
        registerBrewing(growth_potion2, growth_potion1, new ItemStack(Items.GLOWSTONE_DUST));
        registerBrewing(growth_potion3, growth_potion2, new ItemStack(Items.GLOWSTONE_DUST));
        registerBrewing(growth_potion1_long, growth_potion1, new ItemStack(Items.REDSTONE));
        registerBrewing(growth_potion2_long, growth_potion2, new ItemStack(Items.REDSTONE));
        registerBrewing(growth_potion3_long, growth_potion3, new ItemStack(Items.REDSTONE));
        registerBrewing(growth_potion1_longer, growth_potion1_long, new ItemStack(Items.REDSTONE));
        registerBrewing(growth_potion2_longer, growth_potion2_long, new ItemStack(Items.REDSTONE));
        registerBrewing(growth_potion2_long, growth_potion1_long, new ItemStack(Items.GLOWSTONE_DUST));
        registerBrewing(growth_potion3_long, growth_potion2_long, new ItemStack(Items.GLOWSTONE_DUST));
        registerBrewing(growth_potion2_longer, growth_potion1_longer, new ItemStack(Items.GLOWSTONE_DUST));
        
        // Growth -> shrinking reversing
        registerBrewing(shrinking_potion1, growth_potion1, new ItemStack(Items.FERMENTED_SPIDER_EYE));
        registerBrewing(shrinking_potion2, growth_potion2, new ItemStack(Items.FERMENTED_SPIDER_EYE));
        registerBrewing(shrinking_potion3, growth_potion3, new ItemStack(Items.FERMENTED_SPIDER_EYE));
        registerBrewing(shrinking_potion1_long, growth_potion1_long, new ItemStack(Items.FERMENTED_SPIDER_EYE));
        registerBrewing(shrinking_potion2_long, growth_potion2_long, new ItemStack(Items.FERMENTED_SPIDER_EYE));
        registerBrewing(shrinking_potion3_long, growth_potion3_long, new ItemStack(Items.FERMENTED_SPIDER_EYE));
        registerBrewing(shrinking_potion1_longer, growth_potion1_longer, new ItemStack(Items.FERMENTED_SPIDER_EYE));
        registerBrewing(shrinking_potion2_longer, growth_potion2_longer, new ItemStack(Items.FERMENTED_SPIDER_EYE));
        
        // Shrinking -> growth reversing
        registerBrewing(growth_potion1, shrinking_potion1, new ItemStack(Items.FERMENTED_SPIDER_EYE));
        registerBrewing(growth_potion2, shrinking_potion2, new ItemStack(Items.FERMENTED_SPIDER_EYE));
        registerBrewing(growth_potion3, shrinking_potion3, new ItemStack(Items.FERMENTED_SPIDER_EYE));
        registerBrewing(growth_potion1_long, shrinking_potion1_long, new ItemStack(Items.FERMENTED_SPIDER_EYE));
        registerBrewing(growth_potion2_long, shrinking_potion2_long, new ItemStack(Items.FERMENTED_SPIDER_EYE));
        registerBrewing(growth_potion3_long, shrinking_potion3_long, new ItemStack(Items.FERMENTED_SPIDER_EYE));
        registerBrewing(growth_potion1_longer, shrinking_potion1_longer, new ItemStack(Items.FERMENTED_SPIDER_EYE));
        registerBrewing(growth_potion2_longer, shrinking_potion2_longer, new ItemStack(Items.FERMENTED_SPIDER_EYE));
        
        // Shrinking and growth venom splash/lingering crafting
        registerBrewing(growth_venom, null, ItemStack.EMPTY);
        registerBrewing(shrinking_venom, null, ItemStack.EMPTY);
    }
    
    private static PotionType registerPotionType(RegistryEvent.Register<PotionType> event, Potion potion, String resourcePath, int duration, int amplifier)
    {
        PotionEffect effect = new PotionEffect(potion, duration, amplifier);
        PotionType type = new PotionType(effect);
        type.setRegistryName(new ResourceLocation(Mia.MODID, resourcePath));
        
        event.getRegistry().register(type);
        return type;
    }
    
    private static void registerBrewing(@Nonnull PotionType result, @Nullable PotionType previousLevel, @Nonnull ItemStack ingredient)
    {
        ThermalExpansion.addBrewingRecipe(result, previousLevel, ingredient);
        
        // Splash and lingering potion crafting
        BrewingRecipeRegistry.addRecipe(new StrictBrewingRecipe(
                PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM), result),
                new ItemStack(Items.GUNPOWDER),
                PotionUtils.addPotionToItemStack(new ItemStack(Items.SPLASH_POTION), result)
        ));
        BrewingRecipeRegistry.addRecipe(new StrictBrewingRecipe(
                PotionUtils.addPotionToItemStack(new ItemStack(Items.SPLASH_POTION), result),
                new ItemStack(Items.DRAGON_BREATH),
                PotionUtils.addPotionToItemStack(new ItemStack(Items.LINGERING_POTION), result)
        ));
        
        if (previousLevel == null || ingredient.isEmpty())
            return;
        
        // If we have a previous level and an ingredient then we set up the upgrade recipes
        BrewingRecipeRegistry.addRecipe(new StrictBrewingRecipe(
                PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM), previousLevel),
                ingredient,
                PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM), result)
        ));
        BrewingRecipeRegistry.addRecipe(new StrictBrewingRecipe(
                PotionUtils.addPotionToItemStack(new ItemStack(Items.SPLASH_POTION), previousLevel),
                ingredient,
                PotionUtils.addPotionToItemStack(new ItemStack(Items.SPLASH_POTION), result)
        ));
        BrewingRecipeRegistry.addRecipe(new StrictBrewingRecipe(
                PotionUtils.addPotionToItemStack(new ItemStack(Items.LINGERING_POTION), previousLevel),
                ingredient,
                PotionUtils.addPotionToItemStack(new ItemStack(Items.LINGERING_POTION), result)
        ));
    }
    
    private static class StrictBrewingRecipe extends BrewingRecipe
    {
        private StrictBrewingRecipe(@Nonnull ItemStack input, @Nonnull ItemStack ingredient, @Nonnull ItemStack output)
        {
            super(input, ingredient, output);
        }
        
        @Override
        public boolean isInput(@Nonnull ItemStack stack)
        {
            return com.github.sokyranthedragon.mia.utilities.PotionUtils.arePotionsSame(stack, getInput());
        }
    }
}
