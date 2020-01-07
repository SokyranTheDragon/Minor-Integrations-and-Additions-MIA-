package com.github.exploder1531.mia.integrations.botania;

import com.gendeathrow.hatchery.api.crafting.ShredderRecipe;
import com.gendeathrow.hatchery.block.shredder.ShredderTileEntity;
import com.gendeathrow.hatchery.core.config.ConfigLootHandler;
import com.github.exploder1531.mia.integrations.ModIds;
import com.github.exploder1531.mia.integrations.hatchery.IHatcheryIntegration;
import com.google.common.collect.Lists;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.item.ItemStack;
import vazkii.botania.common.block.ModBlocks;
import vazkii.botania.common.item.ModItems;

import javax.annotation.Nonnull;
import java.util.List;

import static com.github.exploder1531.mia.integrations.hatchery.Hatchery.getDrop;

@MethodsReturnNonnullByDefault
class HatcheryBotaniaIntegration implements IHatcheryIntegration
{
    private final boolean modEnabled;
    
    HatcheryBotaniaIntegration(boolean modEnabled)
    {
        this.modEnabled = modEnabled;
    }
    
    @Override
    public void registerShredder()
    {
        for (int meta = 0; meta <= 15; meta++)
        {
            ShredderTileEntity.shredderRecipes.add(new ShredderRecipe(new ItemStack(ModBlocks.flower, 1, meta), new ItemStack(ModItems.dye, 3, meta)));
            ShredderTileEntity.shredderRecipes.add(new ShredderRecipe(new ItemStack(ModBlocks.mushroom, 1, meta), new ItemStack(ModItems.dye, 2, meta)));
        }
    }
    
    @Override
    public boolean isModEnabled()
    {
        return modEnabled;
    }
    
    @Override
    public int getCurrentLootVersion()
    {
        return 0;
    }
    
    @Nonnull
    @Override
    public List<ConfigLootHandler.ItemDrop> getDefaultEggDrops()
    {
        List<ConfigLootHandler.ItemDrop> drops = Lists.newLinkedList();
        
        // Several loops just to keep the order in JEI tabs, hopefully
        for (int meta = 0; meta <= 15; meta++)
            drops.add(getDrop(ModItems.petal, meta, 2, 1, 4));
        for (int meta = 0; meta <= 15; meta++)
            drops.add(getDrop(ModItems.rune, meta, 1, 1, 4));
        drops.add(getDrop(ModItems.recordGaia1, 1));
        drops.add(getDrop(ModItems.recordGaia2, 1));
        drops.add(getDrop(ModBlocks.enchantedSoil, 1));
        for (int meta = 0; meta <= 2; meta++)
            drops.add(getDrop(ModItems.manaResource, meta, 1));
        
        return drops;
    }
    
    @Override
    public ModIds getModId()
    {
        return ModIds.BOTANIA;
    }
}
