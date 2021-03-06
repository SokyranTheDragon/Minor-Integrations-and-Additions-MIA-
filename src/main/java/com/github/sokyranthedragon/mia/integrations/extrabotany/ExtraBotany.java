package com.github.sokyranthedragon.mia.integrations.extrabotany;

import com.github.sokyranthedragon.mia.config.ExtraBotanyConfig;
import com.github.sokyranthedragon.mia.dispenserbehavior.DispenserLootBag;
import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.integrations.base.IBaseMod;
import com.github.sokyranthedragon.mia.integrations.base.IModIntegration;
import com.meteor.extrabotany.api.item.WeightCategory;
import com.meteor.extrabotany.common.item.ModItems;
import com.meteor.extrabotany.common.item.bonus.ItemBonusBase;
import net.minecraft.block.BlockDispenser;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.oredict.OreIngredient;

import java.util.List;
import java.util.function.BiConsumer;

public class ExtraBotany implements IBaseMod
{
    @Override
    public void register(BiConsumer<ModIds, IModIntegration> modIntegration)
    {
        if (ModIds.JEI.isLoaded)
            modIntegration.accept(ModIds.JEI, new JeiExtraBotany());
        if (ExtraBotanyConfig.enableDungeonTacticsIntegration && ModIds.DUNGEON_TACTICS.isLoaded)
            modIntegration.accept(ModIds.DUNGEON_TACTICS, new DungeonTacticsExtraBotanyIntegration());
        if (ExtraBotanyConfig.enableTeIntegration && ModIds.THERMAL_EXPANSION.isLoaded)
            modIntegration.accept(ModIds.THERMAL_EXPANSION, new ThermalExpansionExtraBotanyIntegration());
        if (ModIds.JER.isLoaded)
            modIntegration.accept(ModIds.JER, new JerExtraBotanyIntegration());
        if (ModIds.EXTRABOTANY.isLoaded)
            modIntegration.accept(ModIds.EXTRABOTANY, new HatcheryExtraBotanyIntegration(ExtraBotanyConfig.enableHatcheryIntegration));
    }
    
    @Override
    public void init(FMLInitializationEvent event)
    {
//        if (!MiaConfig.disableOreDict)
//        {
//            try
//            {
//                Field input = ShapedOreRecipe.class.getDeclaredField("input");
//                input.setAccessible(true);
//
//                if (ModCraftingRecipe.KINGGARDEN2 instanceof ShapedOreRecipe)
//                {
//                    Object o = input.get(ModCraftingRecipe.KINGGARDEN2);
//                    if (o instanceof NonNullList)
//                        replaceItemIngredient((NonNullList<Ingredient>)o, new ItemStack(Blocks.CHEST), "chestWood");
//                }
//                if (ModCraftingRecipe.MINIHANDBAG instanceof ShapedOreRecipe)
//                {
//                    Object o = input.get(ModCraftingRecipe.MINIHANDBAG);
//                    if (o instanceof NonNullList)
//                        replaceItemIngredient((NonNullList<Ingredient>)o, new ItemStack(Blocks.CHEST), "woodChest");
//                }
//            } catch (NoSuchFieldException | IllegalAccessException e)
//            {
//                Mia.LOGGER.error("Could not access ExtraBotany recipes, can't oredict them.");
//                e.printStackTrace();
//            }
//        }
    }
    
    @Override
    public void registerDispenserBehaviors()
    {
        DispenserLootBag.getInstance().addListener(((source, stack) ->
        {
            if (!(stack.getItem() instanceof ItemBonusBase))
                return false;
            ItemBonusBase item = (ItemBonusBase) stack.getItem();
            List<WeightCategory> loot = item.getWeightCategory(stack);
            if (loot == null || loot.isEmpty())
                return false;
            
            BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.getObject(null).dispense(source, item.rollItem(stack, null, loot));
            stack.shrink(1);
            return true;
        }), ModItems.rewardbag, ModItems.rewardbag943, ModItems.candybag);
    }
    
    private static void replaceItemIngredient(NonNullList<Ingredient> ingredients, ItemStack toReplace, @SuppressWarnings("SameParameterValue") String oredictReplacement)
    {
        replaceItemIngredient(ingredients, toReplace, oredictReplacement, true);
    }
    
    private static void replaceItemIngredient(NonNullList<Ingredient> ingredients, ItemStack toReplace, String oredictReplacement, @SuppressWarnings("SameParameterValue") boolean shortCircuitOnSuccess)
    {
        for (int i = 0; i < ingredients.size(); i++)
        {
            Ingredient ingredient = ingredients.get(i);
            
            if (ingredient.apply(toReplace))
            {
                ingredients.set(i, new OreIngredient(oredictReplacement));
                if (shortCircuitOnSuccess)
                    return;
            }
        }
    }
}
