package com.github.exploder1531.mia.integrations.jei.categories.cauldron;

import com.google.common.collect.Lists;
import mezz.jei.api.gui.ITooltipCallback;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.client.config.GuiUtils;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

public class CauldronWrapper implements IRecipeWrapper, ITooltipCallback<ItemStack>
{
    private final CauldronEntry entry;
    
    public CauldronWrapper(CauldronEntry entry)
    {
        this.entry = entry;
    }
    
    @Override
    public void getIngredients(@Nonnull IIngredients ingredients)
    {
        ingredients.setOutputs(VanillaTypes.ITEM, Lists.newArrayList(entry.getOutput(), entry.getByproduct()));
        if (entry instanceof CauldronEntry.CauldronFluidEntry)
            ingredients.setOutput(VanillaTypes.FLUID, ((CauldronEntry.CauldronFluidEntry)entry).getTrueOutput());
        
        ingredients.setInputs(VanillaTypes.ITEM, entry.getInputs());
        if (entry.getPossibleFluids() == CauldronEntry.PossibleFluids.Water || entry.getPossibleFluids() == CauldronEntry.PossibleFluids.Lava)
            //noinspection ConstantConditions
            ingredients.setInput(VanillaTypes.FLUID, entry.getFluid());
    }
    
    public CauldronEntry getEntry()
    {
        return entry;
    }
    
    @Override
    @ParametersAreNonnullByDefault
    public void onTooltip(int i, boolean b, ItemStack itemStack, List<String> list)
    {
        if (i == 0)
        {
            if (entry instanceof CauldronEntry.SimpleCauldronCookingEntry)
            {
                CauldronEntry.SimpleCauldronCookingEntry entry = (CauldronEntry.SimpleCauldronCookingEntry) this.entry;
                if (entry.getMaxOutput() > entry.getOutput().getCount())
                    list.add(I18n.format("mia.jei.cauldron_drop_range", entry.getOutput().getCount(), entry.getMaxOutput()));
                else
                    list.add(I18n.format("mia.jei.cauldron_drop", entry.getMaxOutput()));
            }
            else if (entry instanceof CauldronEntry.CauldronImbuingEntry)
                list.add(I18n.format("mia.jei.cauldron_imbuing"));
        }
        else if (i == 6)
        {
            list.clear();
            list.add("Cannot contain water!");
        }
    }
    
    @Override
    public void drawInfo(Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX, int mouseY)
    {
//        if (entry.getPossibleFluids() == CauldronEntry.PossibleFluids.NoWater)
//        {
//            minecraft.getTextureManager().bindTexture(CauldronCategory.BACKGROUND.getResource());
//            GuiUtils.drawTexturedModalRect(39, 31, 0, 59, 14, 14, 0);
//        }
        if (entry instanceof CauldronEntry.CauldronObsidianEntry)
        {
            minecraft.getTextureManager().bindTexture(CauldronCategory.BACKGROUND.getResource());
            GuiUtils.drawTexturedModalRect(20, 47, 0, 59, 14, 14, 0);
        }
    }
}
