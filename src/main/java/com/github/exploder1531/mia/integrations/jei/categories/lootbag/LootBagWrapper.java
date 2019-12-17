package com.github.exploder1531.mia.integrations.jei.categories.lootbag;

import mezz.jei.api.gui.ITooltipCallback;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.text.DecimalFormat;
import java.util.List;
import java.util.stream.Collectors;

public class LootBagWrapper implements IRecipeWrapper, ITooltipCallback<ItemStack>
{
    private static final DecimalFormat round = new DecimalFormat("#.##");
    private final LootBagEntry entry;
    
    public LootBagWrapper(LootBagEntry entry)
    {
        this.entry = entry;
    }
    
    @Override
    public void getIngredients(@Nonnull IIngredients ingredients)
    {
        ingredients.setInput(VanillaTypes.ITEM, entry.getInput());
        ingredients.setOutputLists(VanillaTypes.ITEM, entry.getOutputs().stream().map(LootBagEntry.BagOutputEntry::getItems).collect(Collectors.toList()));
    }
    
    public LootBagEntry getEntry()
    {
        return entry;
    }
    
    @Override
    @ParametersAreNonnullByDefault
    public void onTooltip(int slotIndex, boolean input, ItemStack ingredient, List<String> tooltip)
    {
        if (slotIndex > 0 && slotIndex <= entry.getOutputs().size())
        {
            float chance = entry.getOutputs().get(slotIndex - 1).getChance();
            if (chance > 0)
                tooltip.add(I18n.format("mia.generic.chance", round.format(chance)));
        }
    }
}
