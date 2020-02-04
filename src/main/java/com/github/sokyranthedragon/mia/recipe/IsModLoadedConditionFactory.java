package com.github.sokyranthedragon.mia.recipe;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.util.JsonUtils;
import net.minecraftforge.common.crafting.IConditionFactory;
import net.minecraftforge.common.crafting.JsonContext;
import net.minecraftforge.fml.common.Loader;

import java.util.function.BooleanSupplier;

public class IsModLoadedConditionFactory implements IConditionFactory
{
    @Override
    public BooleanSupplier parse(JsonContext context, JsonObject json)
    {
        if (JsonUtils.hasField(json, "modids"))
        {
            for (JsonElement modid : JsonUtils.getJsonArray(json, "modids"))
            {
                if (!Loader.isModLoaded(modid.getAsString()))
                    return () -> false;
            }
            
            return () -> true;
        }
        else if (JsonUtils.hasField(json, "modid") && Loader.isModLoaded(JsonUtils.getString(json, "modid")))
            return () -> true;
        else
            return () -> false;
    }
}
