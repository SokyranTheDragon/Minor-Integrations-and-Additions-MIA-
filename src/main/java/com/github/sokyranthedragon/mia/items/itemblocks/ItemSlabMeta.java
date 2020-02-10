package com.github.sokyranthedragon.mia.items.itemblocks;

import com.github.sokyranthedragon.mia.block.IMetaBlock;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.item.ItemSlab;
import net.minecraft.item.ItemStack;

import javax.annotation.Nullable;

@MethodsReturnNonnullByDefault
public class ItemSlabMeta extends ItemSlab implements IMetaBlock
{
    protected final IMetaBlock block;
    
    public <T extends Block & IMetaBlock> ItemSlabMeta(T block, BlockSlab singleSlab, BlockSlab doubleSlab)
    {
        super(block, singleSlab, doubleSlab);
        this.block = block;
    }
    
    @Override
    public int getMetadata(int damage)
    {
        return damage;
    }
    
    @Override
    public String getTranslationKey()
    {
        return super.getTranslationKey() + "_" + block.getNameFromMeta(0);
    }
    
    @Override
    public String getTranslationKey(ItemStack stack)
    {
        return super.getTranslationKey(stack) + "_" + block.getNameFromMeta(Math.min(stack.getMetadata(), block.getMaxMeta()));
    }
    
    @Override
    public int getMaxMeta()
    {
        return block.getMaxMeta();
    }
    
    @Override
    public String getNameFromMeta(int i)
    {
        return block.getNameFromMeta(i);
    }
    
    @Override
    public String getVariantName()
    {
        return block.getVariantName();
    }
    
    @Nullable
    @Override
    public String getDefaultVariantValue()
    {
        return block.getDefaultVariantValue();
    }
}
