package com.github.sokyranthedragon.mia.integrations.dungeontactics;

import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.integrations.futuremc.IFutureMcIntegration;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import pegbeard.dungeontactics.blocks.DTBushIncindiberry;
import pegbeard.dungeontactics.handlers.DTBlocks;
import pegbeard.dungeontactics.handlers.DTItems;
import thedarkcolour.futuremc.api.BeePollinationHandlerJVM;

import javax.annotation.Nonnull;

import static com.github.sokyranthedragon.mia.integrations.futuremc.FutureMc.*;

class FutureMcDungeonTacticsIntegration implements IFutureMcIntegration
{
    @Override
    public void addRecipes()
    {
        // Same order as mod IDs
        oreDictBlastFurnaceRecipe(DTItems.CLUSTER_TIN, "ingotTin");
        oreDictBlastFurnaceRecipe(DTItems.CLUSTER_COPPER, "ingotCopper");
        oreDictBlastFurnaceRecipe(DTItems.CLUSTER_ALUMINIUM, "ingotAluminium", "ingotAluminum");
        oreDictBlastFurnaceRecipe(DTItems.CLUSTER_NICKEL, "ingotNickel");
        oreDictBlastFurnaceRecipe(DTItems.CLUSTER_LEAD, "ingotLead");
        oreDictBlastFurnaceRecipe(DTItems.CLUSTER_PLATINUM, "ingotPlatinum");
        oreDictBlastFurnaceRecipe(DTItems.CLUSTER_TUNGSTEN, "ingotTungsten");
        oreDictBlastFurnaceRecipe(DTItems.CLUSTER_TITANIUM, "ingotTitanium");
        oreDictBlastFurnaceRecipe(DTItems.CLUSTER_OSMIUM, "ingotOsmium");
        
        // Same order as mod IDs
        addCampfireRecipe(new ItemStack(DTItems.FISH_SWIFT), new ItemStack(DTItems.FISH_SWIFT_COOKED));
        addCampfireRecipe(new ItemStack(DTItems.FISH_FLYING), new ItemStack(DTItems.FISH_FLYING_COOKED));
        addCampfireRecipe(new ItemStack(DTItems.FISH_LAVA), new ItemStack(DTItems.FISH_LAVA_COOKED));
        addCampfireRecipe(new ItemStack(DTItems.FISH_MUSCLE), new ItemStack(DTItems.FISH_MUSCLE_COOKED));
        addCampfireRecipe(new ItemStack(DTItems.FISH_LUNG), new ItemStack(DTItems.FISH_LUNG_COOKED));
        addCampfireRecipe(new ItemStack(DTItems.FISH_OBSIDIAN), new ItemStack(DTItems.FISH_OBSIDIAN_COOKED));
        addCampfireRecipe(new ItemStack(DTItems.FISH_TUNNEL), new ItemStack(DTItems.FISH_TUNNEL_COOKED));
    
        GenericBeePollinationHandler berryHandler = new GenericBeePollinationHandler(DTBushIncindiberry.AGE, 3);
    
        BeePollinationHandlerJVM.registerHandler(DTBlocks.CHERRYBOMB_BUSH, berryHandler);
        BeePollinationHandlerJVM.registerHandler(DTBlocks.INCINDIBERRY_BUSH, berryHandler);
        BeePollinationHandlerJVM.registerHandler(DTBlocks.GLOWCURRENT_BUSH, berryHandler);
    }
    
    @Override
    public IBlockState[] registerPollinationFlowers()
    {
        return new IBlockState[]
            {
                DTBlocks.FLOWER_BARK.getDefaultState(),
                DTBlocks.FLOWER_BRAMBLE.getDefaultState(),
                DTBlocks.FLOWER_CINDER.getDefaultState(),
                DTBlocks.FLOWER_FADE.getDefaultState(),
                DTBlocks.FLOWER_SANGUINE.getDefaultState(),
                DTBlocks.FLOWER_TANGLE.getDefaultState(),
                DTBlocks.FLOWER_AILMENT.getDefaultState(),
                DTBlocks.FLOWER_FEATHER.getDefaultState(),
                DTBlocks.FLOWER_XP.getDefaultState()
            };
    }
    
    @Override
    @Nonnull
    public ModIds getModId()
    {
        return ModIds.DUNGEON_TACTICS;
    }
}
