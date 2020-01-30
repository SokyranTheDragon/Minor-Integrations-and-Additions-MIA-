package com.github.sokyranthedragon.mia.integrations.botania.lexicon;

import com.github.sokyranthedragon.mia.integrations.botania.crafting.MiaPetalRecipes;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.common.lexicon.AlfheimLexiconEntry;
import vazkii.botania.common.lexicon.page.PagePetalRecipe;
import vazkii.botania.common.lexicon.page.PageText;

public class MiaLexiconData
{
    private MiaLexiconData()
    {
    }
    
    public static LexiconEntry orechidVacuam;
    
    public static void init()
    {
        orechidVacuam = new AlfheimLexiconEntry("orechidVacuam", BotaniaAPI.categoryFunctionalFlowers);
        orechidVacuam.setLexiconPages(
                new PageText("0"),
                new PagePetalRecipe<>("1", MiaPetalRecipes.orechidVacuamRecipe));
    }
}
