package com.github.sokyranthedragon.mia.tile;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.text.ITextComponent;

import javax.annotation.Nonnull;

public abstract class TileBase extends TileEntity
{
    @Nonnull
    @Override
    public abstract ITextComponent getDisplayName();
}
