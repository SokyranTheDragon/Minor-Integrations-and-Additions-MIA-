package com.github.sokyranthedragon.mia.items.itemblocks;

import com.github.sokyranthedragon.mia.block.IMetaBlock;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

import javax.annotation.Nullable;

@MethodsReturnNonnullByDefault
public class ItemBlockMeta extends ItemBlock implements IMetaBlock
{
    protected final IMetaBlock block;
    
    public <T extends Block & IMetaBlock> ItemBlockMeta(T block)
    {
        super(block);
        this.block = block;
        if (block.getMaxMeta() > 0)
        {
            setMaxDamage(0);
            setHasSubtypes(true);
        }
    }
    
    @Override
    public int getMetadata(int damage)
    {
        return damage;
    }
    
    @Override
    public String getTranslationKey()
    {
        return getTranslationKey(super.getTranslationKey(), 0);
    }
    
    @Override
    public String getTranslationKey(ItemStack stack)
    {
        return getTranslationKey(super.getTranslationKey(stack), stack.getMetadata());
    }
    
    public String getTranslationKey(String baseKey, int meta)
    {
        String metaKey = block.getNameFromMeta(Math.min(meta, block.getMaxMeta()));
        
        if (metaKey.equals(""))
            return baseKey;
        else
            return baseKey + "_" + metaKey;
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
