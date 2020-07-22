package com.github.sokyranthedragon.mia.integrations.abyssalcraft;

import com.github.sokyranthedragon.mia.Mia;
import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.integrations.harvestcraft.IHarvestcraftIntegration;
import com.pam.harvestcraft.HarvestCraft;
import com.shinoow.abyssalcraft.api.item.ACItems;
import com.shinoow.abyssalcraft.common.items.ItemPlatefood;
import com.shinoow.abyssalcraft.init.InitHandler;
import com.shinoow.abyssalcraft.lib.ACTabs;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.util.ResourceLocation;

import java.lang.reflect.Field;
import java.util.List;

@MethodsReturnNonnullByDefault
public class HarvestcraftAbyssalcraftIntegration implements IHarvestcraftIntegration
{
    @Override
    public void replaceFood()
    {
        try
        {
            Field field = InitHandler.class.getDeclaredField("ITEMS");
            field.setAccessible(true);
            
            Object o = field.get(InitHandler.INSTANCE);
            
            if (o instanceof List)
            {
                //noinspection unchecked
                List<Item> items = (List<Item>)o;

                items.remove(ACItems.mre);
                items.remove(ACItems.chicken_on_a_plate);
                items.remove(ACItems.pork_on_a_plate);
                items.remove(ACItems.beef_on_a_plate);
                items.remove(ACItems.fish_on_a_plate);
                items.remove(ACItems.fried_egg);
                items.remove(ACItems.fried_egg_on_a_plate);
                
                ACItems.mre = new ItemPlatefood(5, HarvestCraft.config.mealsaturation, false, "mre");
                ACItems.chicken_on_a_plate = new ItemPlatefood(2, HarvestCraft.config.mealsaturation, false, "chickenp");
                ACItems.pork_on_a_plate = new ItemPlatefood(2, HarvestCraft.config.mealsaturation, false, "porkp");
                ACItems.beef_on_a_plate = new ItemPlatefood(2, HarvestCraft.config.mealsaturation, false, "beefp");
                ACItems.fish_on_a_plate = new ItemPlatefood(5, HarvestCraft.config.mealsaturation, false, "fishp");
                ACItems.fried_egg = (new ItemFood(3, HarvestCraft.config.mealsaturation, false)).setCreativeTab(ACTabs.tabFood).setTranslationKey("friedegg");
                ACItems.fried_egg_on_a_plate = new ItemPlatefood(4, HarvestCraft.config.mealsaturation, false, "eggp");

                items.add(ACItems.mre.setRegistryName(new ResourceLocation("abyssalcraft", "mre")));
                items.add(ACItems.chicken_on_a_plate.setRegistryName(new ResourceLocation("abyssalcraft", "chickenp")));
                items.add(ACItems.pork_on_a_plate.setRegistryName(new ResourceLocation("abyssalcraft", "porkp")));
                items.add(ACItems.beef_on_a_plate.setRegistryName(new ResourceLocation("abyssalcraft", "beefp")));
                items.add(ACItems.fish_on_a_plate.setRegistryName(new ResourceLocation("abyssalcraft", "fishp")));
                items.add(ACItems.fried_egg.setRegistryName(new ResourceLocation("abyssalcraft", "friedegg")));
                items.add(ACItems.fried_egg_on_a_plate.setRegistryName(new ResourceLocation("abyssalcraft", "eggp")));
            }
        }
        catch (NoSuchFieldException | IllegalAccessException e)
        {
            Mia.LOGGER.error("Could not replace AbyssalCraft food for balance");
        }
    }
    
    @Override
    public ModIds getModId()
    {
        return ModIds.ABYSSALCRAFT;
    }
}
