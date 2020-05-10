package com.github.sokyranthedragon.mia.recipe;

import com.github.sokyranthedragon.mia.config.HatcheryConfiguration;
import com.github.sokyranthedragon.mia.config.IceAndFireConfiguration;
import com.github.sokyranthedragon.mia.config.MiaConfig;
import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.google.gson.JsonObject;
import net.minecraft.util.JsonUtils;
import net.minecraftforge.common.crafting.IConditionFactory;
import net.minecraftforge.common.crafting.JsonContext;
import vazkii.quark.building.feature.MoreSandstone;

import java.util.function.BooleanSupplier;

import static com.github.sokyranthedragon.mia.config.GenericAdditionsConfig.*;
import static com.github.sokyranthedragon.mia.integrations.ModIds.FUTURE_MC;

public class AreRecipesEnabledFactory implements IConditionFactory
{
    @Override
    public BooleanSupplier parse(JsonContext context, JsonObject json)
    {
        boolean isEnabled = true;
        
        if (JsonUtils.hasField(json, "category"))
        {
            switch (JsonUtils.getString(json, "category"))
            {
                case "sandstone":
                    if (!enableMoreSandstone)
                        isEnabled = false;
                    if (JsonUtils.hasField(json, "subtype") && JsonUtils.getString(json, "subtype").equals("white") && !moreSandstone.bopWhiteSandstoneEnabled)
                        isEnabled = false;
                    
                    if (isEnabled && JsonUtils.hasField(json, "recipe"))
                    {
                        switch (JsonUtils.getString(json, "recipe"))
                        {
                            case "block":
                                if (!moreSandstone.moreSandstoneQuarkEnabled && !moreSandstone.forceMoreSandstone)
                                    isEnabled = false;
                                break;
                            case "slab":
                            case "stairs":
                                if (!moreSandstone.moreSandstoneQuarkEnabled && !moreSandstone.forceMoreSandstone)
                                    isEnabled = false;
                                if (!moreSandstone.forceMoreSandstoneStairsAndSlabs && !(ModIds.QUARK.isLoaded && MoreSandstone.enableStairsAndSlabs))
                                    isEnabled = false;
                                break;
                            case "futuremc_wall":
                                if (!FUTURE_MC.isLoaded || !moreSandstone.sandstoneWallsFutureMcEnabled)
                                    isEnabled = false;
                                break;
                        }
                    }
                    break;
                case "evtp":
                    if (!enableEvtp)
                        isEnabled = false;
                    else if (JsonUtils.hasField(json, "recipe"))
                    {
                        switch (JsonUtils.getString(json, "recipe"))
                        {
                            case "armored_glass":
                                isEnabled = evtp.armoredGlassEnabled;
                                break;
                            case "door_stone":
                                isEnabled = evtp.stoneDoorsEnabled;
                                break;
                            case "redstone_lantern":
                                isEnabled = evtp.redstoneLanternEnabled;
                                break;
                            case "packed_paper":
                                isEnabled = evtp.packedPaperEnabled;
                                break;
                            case "torch_gold":
                                isEnabled = evtp.goldenTorchEnabled;
                                break;
                        }
                        break;
                    }
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
                        isEnabled = false;
                    break;
                case "egg_sorter":
                    if (!HatcheryConfiguration.hatcheryAdditionsEnabled)
                        isEnabled = false;
                    break;
                case "pixie_dust_collector":
                    if (!IceAndFireConfiguration.iceandfireAdditionsEnabled)
                        isEnabled = false;
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
        
        if (isEnabled)
            return () -> true;
        else
            return () -> false;
    }
}
