package com.github.sokyranthedragon.mia;

import com.github.sokyranthedragon.mia.block.BlockBotaniaSpecialFlower;
import com.github.sokyranthedragon.mia.core.MiaBlocks;
import com.github.sokyranthedragon.mia.core.MiaItems;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class MiaCreativeTab extends CreativeTabs
{
    public static final MiaCreativeTab INSTANCE = new MiaCreativeTab();
    
    public MiaCreativeTab()
    {
        super("mia");
    }
    
    @Override
    @Nonnull
    @SideOnly(Side.CLIENT)
    public ItemStack createIcon()
    {
        if (!MiaBlocks.blocks.isEmpty())
        {
            Block block = MiaBlocks.blocks.get(0);
            if (block instanceof BlockBotaniaSpecialFlower)
            {
                NonNullList<ItemStack> stacks = NonNullList.create();
                block.getSubBlocks(this, stacks);
                for (ItemStack stack : stacks)
                    if (!stack.isEmpty()) return stack;
                if (MiaBlocks.blocks.size() > 1)
                    return new ItemStack(MiaBlocks.blocks.get(1));
            }
            else
                return new ItemStack(block);
        }
        else if (!MiaItems.items.isEmpty())
            return new ItemStack(MiaItems.items.get(0));
        
        
        return ItemStack.EMPTY;
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void displayAllRelevantItems(NonNullList<ItemStack> list)
    {
        for (Block block : MiaBlocks.blocks)
            addBlock(block, list);
        for (Item item : MiaItems.items)
            addItem(item, list);
    }
    
    @SideOnly(Side.CLIENT)
    private void addItem(Item item, NonNullList<ItemStack> list)
    {
        item.getSubItems(this, list);
    }
    
    @SideOnly(Side.CLIENT)
    private void addBlock(Block block, NonNullList<ItemStack> list)
    {
        new ItemStack(block);
        block.getSubBlocks(this, list);
    }
}
