package com.github.exploder1531.mia.integrations.quark;

import com.github.exploder1531.mia.Mia;
import com.github.exploder1531.mia.integrations.ModIds;
import com.github.exploder1531.mia.integrations.dungeontactics.IDungeonTacticsIntegration;
import com.github.exploder1531.mia.utilities.LootUtils;
import net.minecraft.world.storage.loot.LootPool;
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
    public void insertBagLoot(BagTypes type, LootPool loot)
    {
        switch (type)
        {
            case ARBOUR:
                if (TreeVariants.variant_sapling != null)
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
                if (AncientTomes.ancient_tome != null)
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
                if (ExtraArrows.arrow_ender != null)
                    LootUtils.addDtLoot(loot, ExtraArrows.arrow_ender, 7, LootUtils.setCountFunction(1, 16));
                if (ExtraArrows.arrow_explosive != null)
                    LootUtils.addDtLoot(loot, ExtraArrows.arrow_explosive, 7, LootUtils.setCountFunction(1, 16));
                if (ExtraArrows.arrow_torch != null)
                    LootUtils.addDtLoot(loot, ExtraArrows.arrow_torch, 7, LootUtils.setCountFunction(1, 16));
                break;
            case FOOD:
                if (Crabs.crabLeg != null)
                {
                    LootUtils.addDtLoot(loot, Crabs.crabLeg);
                    LootUtils.addDtLoot(loot, Crabs.cookedCrabLeg);
                }
                if (Frogs.frogLeg != null)
                {
                    LootUtils.addDtLoot(loot, Frogs.frogLeg);
                    LootUtils.addDtLoot(loot, Frogs.cookedFrogLeg);
                }
                break;
            case ORE:
                if (Biotite.biotite_ore != null)
                    LootUtils.addDtLoot(loot, Biotite.biotite_ore);
                break;
        }
    }
    
    @Nonnull
    @Override
    public String getModId()
    {
        return ModIds.QUARK;
    }
}
