package com.github.sokyranthedragon.mia.integrations.quark;

import com.github.sokyranthedragon.mia.Mia;
import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.integrations.dungeontactics.DungeonTactics;
import com.github.sokyranthedragon.mia.integrations.dungeontactics.IDungeonTacticsIntegration;
import com.github.sokyranthedragon.mia.utilities.LootUtils;
import com.github.sokyranthedragon.mia.utilities.QuarkUtils;
import vazkii.quark.misc.feature.AncientTomes;
import vazkii.quark.misc.feature.ExtraArrows;
import vazkii.quark.world.block.BlockVariantLeaves;
import vazkii.quark.world.feature.Biotite;
import vazkii.quark.world.feature.Crabs;
import vazkii.quark.world.feature.Frogs;
import vazkii.quark.world.feature.TreeVariants;

import javax.annotation.Nonnull;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

class DungeonTacticsQuarkIntegration implements IDungeonTacticsIntegration
{
    @Override
    public DungeonTactics.ILootBagListener registerLootBagListener()
    {
        return (type, loot) ->
        {
            switch (type)
            {
                case ARBOUR:
                    if (QuarkUtils.isFeatureEnabled(TreeVariants.class))
                    {
                        if (TreeVariants.enableSakura && TreeVariants.enableSwamp)
                            LootUtils.addDtLoot(loot, TreeVariants.variant_sapling, LootUtils.setMetadataFunction(0, 1));
                        else if (TreeVariants.enableSwamp)
                            LootUtils.addDtLoot(loot, TreeVariants.variant_sapling, LootUtils.setMetadataFunction(BlockVariantLeaves.Variant.SWAMP_LEAVES.ordinal()));
                        else if (TreeVariants.enableSakura)
                            LootUtils.addDtLoot(loot, TreeVariants.variant_sapling, LootUtils.setMetadataFunction(BlockVariantLeaves.Variant.SAKURA_LEAVES.ordinal()));
                    }
                    break;
                case BOOK:
                    if (QuarkUtils.isFeatureEnabled(AncientTomes.class))
                    {
                        try
                        {
                            Constructor<AncientTomes.EnchantTomeFunction> constructor = AncientTomes.EnchantTomeFunction.class.getDeclaredConstructor();
                            constructor.setAccessible(true);
                            LootUtils.addDtLoot(loot, AncientTomes.ancient_tome, 1, constructor.newInstance());
                        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e)
                        {
                            Mia.LOGGER.error("Could not access Quark AncientTomes.EnchantTomeFunction constructor, it won't be added to loot bags.");
                        }
                    }
                    break;
                case QUIVER:
                    if (QuarkUtils.isFeatureEnabled(ExtraArrows.class))
                    {
                        if (ExtraArrows.enableEnder)
                            LootUtils.addDtLoot(loot, ExtraArrows.arrow_ender, 7, LootUtils.setCountFunction(1, 16));
                        if (ExtraArrows.enableExplosive)
                            LootUtils.addDtLoot(loot, ExtraArrows.arrow_explosive, 7, LootUtils.setCountFunction(1, 16));
                        if (ExtraArrows.enableTorch)
                            LootUtils.addDtLoot(loot, ExtraArrows.arrow_torch, 7, LootUtils.setCountFunction(1, 16));
                    }
                    break;
                case FOOD:
                    if (QuarkUtils.isFeatureEnabled(Crabs.class))
                    {
                        LootUtils.addDtLoot(loot, Crabs.crabLeg);
                        LootUtils.addDtLoot(loot, Crabs.cookedCrabLeg);
                    }
                    if (QuarkUtils.isFeatureEnabled(Frogs.class))
                    {
                        LootUtils.addDtLoot(loot, Frogs.frogLeg);
                        LootUtils.addDtLoot(loot, Frogs.cookedFrogLeg);
                    }
                    break;
                case ORE:
                    if (QuarkUtils.isFeatureEnabled(Biotite.class))
                        LootUtils.addDtLoot(loot, Biotite.biotite_ore);
                    break;
            }
        };
    }
    
    @Nonnull
    @Override
    public ModIds getModId()
    {
        return ModIds.QUARK;
    }
}
