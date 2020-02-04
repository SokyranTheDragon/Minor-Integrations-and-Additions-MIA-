package com.github.sokyranthedragon.mia.items.itemblocks;

import com.github.sokyranthedragon.mia.block.IMetaBlock;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

@MethodsReturnNonnullByDefault
public class ItemBlockMeta extends ItemBlock implements IMetaBlock
{
    protected final IMetaBlock block;
    
    public <T extends Block & IMetaBlock> ItemBlockMeta(T block)
    {
        super(block);
        this.block = block;
        setMaxDamage(0);
        setHasSubtypes(true);
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
}
