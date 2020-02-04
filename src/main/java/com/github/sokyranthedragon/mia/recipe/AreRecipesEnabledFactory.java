package com.github.sokyranthedragon.mia.recipe;

import com.github.sokyranthedragon.mia.config.HatcheryConfiguration;
import com.github.sokyranthedragon.mia.config.IceAndFireConfiguration;
import com.github.sokyranthedragon.mia.config.MiaConfig;
import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.google.gson.JsonObject;
import net.minecraft.util.JsonUtils;
import net.minecraftforge.common.crafting.IConditionFactory;
import net.minecraftforge.common.crafting.JsonContext;
import thedarkcolour.futuremc.init.FutureConfig;
import vazkii.quark.building.feature.MoreSandstone;

import java.util.function.BooleanSupplier;

import static com.github.sokyranthedragon.mia.config.GenericAdditionsConfig.enableMoreSandstone;
import static com.github.sokyranthedragon.mia.config.GenericAdditionsConfig.moreSandstone;
import static com.github.sokyranthedragon.mia.integrations.ModIds.FUTURE_MC;

public class AreRecipesEnabledFactory implements IConditionFactory
{
    @Override
    public BooleanSupplier parse(JsonContext context, JsonObject json)
    {
        if (JsonUtils.hasField(json, "category"))
        {
            switch (JsonUtils.getString(json, "category"))
            {
                case "sandstone":
                    if (!enableMoreSandstone)
                        return () -> false;
                    if (JsonUtils.hasField(json, "subtype") && JsonUtils.getString(json, "subtype").equals("white") && !moreSandstone.bopWhiteSandstoneEnabled)
                        return () -> false;
                    
                    if (JsonUtils.hasField(json, "recipe"))
                    {
                        switch (JsonUtils.getString(json, "recipe"))
                        {
                            case "block":
                                if (!moreSandstone.moreSandstoneQuarkEnabled && !moreSandstone.forceMoreSandstone)
                                    return () -> false;
                                break;
                            case "slab":
                            case "stairs":
                                if (!moreSandstone.moreSandstoneQuarkEnabled && !moreSandstone.forceMoreSandstone)
                                    return () -> false;
                                if (!moreSandstone.forceMoreSandstoneStairsAndSlabs && !(ModIds.QUARK.isLoaded && MoreSandstone.enableStairsAndSlabs))
                                    return () -> false;
                                break;
                            case "futuremc_wall":
                                if (!FUTURE_MC.isLoaded || !moreSandstone.sandstoneWallsFutureMcEnabled || (!FutureConfig.general.newWallVariants && !moreSandstone.forceMoreSandstoneFutureMcWalls))
                                    return () -> false;
                                break;
                        }
                    }
                    break;
                case "bark":
                    // Nothing yet
                    break;
            }
        }
        else if (JsonUtils.hasField(json, "recipe"))
        {
            switch (JsonUtils.getString(json, "recipe"))
            {
                case "music_player":
                    if (!MiaConfig.musicPlayerEnabled)
                        return () -> false;
                    break;
                case "egg_sorter":
                    if (!HatcheryConfiguration.hatcheryAdditionsEnabled)
                        return () -> false;
                    break;
                case "pixie_dust_collector":
                    if (!IceAndFireConfiguration.iceandfireAdditionsEnabled)
                        return () -> false;
                    break;
            }
        }
        else if (MiaConfig.disableAllRecipes)
            return () -> false;
//        if (JsonUtils.hasField(json, "oredicted"))
//        {
//            if (JsonUtils.getBoolean(json, "oredicted"))
//                return () -> false;
//        }
        
        return () -> true;
    }
}
