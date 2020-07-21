package com.github.sokyranthedragon.mia.integrations.mia;

import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.integrations.mia.modifiers.ModifierShrinking;
import com.github.sokyranthedragon.mia.integrations.mia.modifiers.ModifierSizeSteal;
import com.github.sokyranthedragon.mia.integrations.tconstruct.ITConstructIntegration;
import com.github.sokyranthedragon.mia.utilities.size.SizeUtils;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import slimeknights.mantle.util.RecipeMatch;

@MethodsReturnNonnullByDefault
public class MiaTConstructIntegration implements ITConstructIntegration
{
    public static ModifierShrinking modifierShrinking;
    public static ModifierSizeSteal modifierSizeSteal;
    
    @Override
    public void init(FMLInitializationEvent event)
    {
        if (SizeUtils.isSizeComponentEnabled)
        {
            modifierShrinking = new ModifierShrinking();
            modifierSizeSteal = new ModifierSizeSteal();
    
            modifierShrinking.addRecipeMatch(new RecipeMatch.ItemCombination(1, new ItemStack(Items.CHORUS_FRUIT), new ItemStack(Items.NETHER_WART)));
            modifierSizeSteal.addRecipeMatch(new RecipeMatch.ItemCombination(1, new ItemStack(Items.CHORUS_FRUIT), new ItemStack(Items.GLOWSTONE_DUST)));
        }
    }
    
    @Override
    public String[] registerBookPages()
    {
        if (SizeUtils.isSizeComponentEnabled)
        {
            return new String[]
                {
                    "shrinking",
                    "size_steal"
                };
        }
        
        return new String[0];
    }
    
    @Override
    public ModIds getModId()
    {
        return ModIds.MIA;
    }
}
