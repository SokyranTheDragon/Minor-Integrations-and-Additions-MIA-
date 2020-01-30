package com.github.sokyranthedragon.mia.block;

import com.github.sokyranthedragon.mia.MiaCreativeTab;
import com.github.sokyranthedragon.mia.config.MiaConfig;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.common.block.BlockSpecialFlower;
import vazkii.botania.common.block.ModBlocks;
import vazkii.botania.common.core.BotaniaCreativeTab;
import vazkii.botania.common.item.block.ItemBlockSpecialFlower;

import javax.annotation.Nonnull;

public class BlockBotaniaSpecialFlower extends BlockSpecialFlower
{
    public BlockBotaniaSpecialFlower()
    {
        if (MiaConfig.miaCreativeTab)
            setCreativeTab(MiaCreativeTab.INSTANCE);
        else
            setCreativeTab(BotaniaCreativeTab.INSTANCE);
        BotaniaAPI.addSubTileToCreativeMenu("orechidVacuam");
    }
    
    @Override
    public void getSubBlocks(CreativeTabs tab, @Nonnull NonNullList<ItemStack> stacks)
    {
        stacks.add(ItemBlockSpecialFlower.ofType("orechidVacuam"));
        stacks.add(ItemBlockSpecialFlower.ofType(new ItemStack(ModBlocks.floatingSpecialFlower), "orechidVacuam"));
    }
}
