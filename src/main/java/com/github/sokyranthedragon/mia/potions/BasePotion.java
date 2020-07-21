package com.github.sokyranthedragon.mia.potions;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;

public class BasePotion extends Potion
{
    protected BasePotion(boolean isBadEffectIn, int liquidColorIn)
    {
        super(isBadEffectIn, liquidColorIn);
    }

    private ResourceLocation effectIcon;

    // Called every tick to check if effect should be applied
    @Override
    public boolean isReady(int duration, int amplifier)
    {
        return true;
    }

//    @Override
//    public boolean hasStatusIcon()
//    {
//        if (effectIcon != null)
//            Minecraft.getMinecraft().getTextureManager().bindTexture(effectIcon);
//        return false;
//    }

    @Override
    @SideOnly(Side.CLIENT)
    public void renderHUDEffect(@Nonnull PotionEffect effect, Gui gui, int x, int y, float z, float alpha)
    {
        Minecraft.getMinecraft().renderEngine.bindTexture(effectIcon);

        Gui.drawModalRectWithCustomSizedTexture(x + 3, y + 3, 0, 0, 18, 18, 18, 18);
    }

    @Override
    public void renderInventoryEffect(@Nonnull PotionEffect effect, Gui gui, int x, int y, float z)
    {
        Minecraft.getMinecraft().renderEngine.bindTexture(effectIcon);

        Gui.drawModalRectWithCustomSizedTexture(x + 6, y + 7, 0, 0, 18, 18, 18, 18);
    }

    protected void setEffectIcon(ResourceLocation effectIcon)
    {
        this.effectIcon = effectIcon;
    }
}
