package com.github.sokyranthedragon.mia.integrations.botania.crafting;

import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.recipe.RecipePetals;
import vazkii.botania.common.item.block.ItemBlockSpecialFlower;

import static vazkii.botania.common.crafting.ModPetalRecipes.*;

public class MiaPetalRecipes
{
    public static RecipePetals orechidVacuamRecipe;
    
    public static void init()
    {
        orechidVacuamRecipe = BotaniaAPI.registerPetalRecipe(
                ItemBlockSpecialFlower.ofType("orechidVacuam"),
                yellow, yellow, purple, purple, black, runePride, runeGreed, "redstoneRoot", "elvenPixieDust");
    }
}
