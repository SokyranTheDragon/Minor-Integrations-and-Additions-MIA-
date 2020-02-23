package com.github.sokyranthedragon.mia.recipe;

import com.google.gson.JsonObject;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.JsonUtils;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.crafting.IIngredientFactory;
import net.minecraftforge.common.crafting.JsonContext;

import javax.annotation.Nonnull;

public class ConditionalIngredientFactory implements IIngredientFactory
{
    @Nonnull
    @Override
    public Ingredient parse(JsonContext context, JsonObject json)
    {
        if (CraftingHelper.processConditions(JsonUtils.getJsonArray(json, "conditions"), context))
            return CraftingHelper.getIngredient(json.get("ingredient"), context);
        else
            return Ingredient.EMPTY;
    }
}