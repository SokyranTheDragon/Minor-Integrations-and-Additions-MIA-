package com.github.sokyranthedragon.mia.integrations.mia;

import com.github.sokyranthedragon.mia.core.MiaBlocks;
import com.github.sokyranthedragon.mia.core.MiaItems;
import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.integrations.base.IBaseMod;
import com.github.sokyranthedragon.mia.integrations.base.IModIntegration;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Optional;
import thaumcraft.api.aspects.*;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.common.block.ModBlocks;
import vazkii.botania.common.item.block.ItemBlockSpecialFlower;

import java.util.function.BiConsumer;

import static com.github.sokyranthedragon.mia.integrations.ModIds.TINKERS_CONSTRUCT;

public class MiaSelfIntegrations implements IBaseMod
{
    @Override
    public void register(BiConsumer<ModIds, IModIntegration> modIntegration)
    {
        if (TINKERS_CONSTRUCT.isLoaded)
            modIntegration.accept(TINKERS_CONSTRUCT, new MiaTConstructIntegration());
    }
    
    @Override
    @Optional.Method(modid = ModIds.ConstantIds.THAUMCRAFT)
    public void registerAspects(AspectRegistryEvent event)
    {
        AspectEventProxy register = event.register;
    
        if (MiaItems.koboldRing != null)
        {
            ItemStack stack = new ItemStack(MiaItems.koboldRing);
            AspectList aspects = AspectHelper.getObjectAspects(stack);
    
            Aspect draco = Aspect.getAspect("draco");
            
            if (draco != null)
                aspects.add(draco, 2);
            aspects.add(Aspect.MAGIC, 5);
            
            register.registerObjectTag(stack, aspects);
        }
        if (MiaBlocks.flowerDead != null)
            register.registerObjectTag(new ItemStack(MiaBlocks.flowerDead), AspectHelper.getObjectAspects(new ItemStack(MiaBlocks.flowerDead)).add(Aspect.PLANT, 1).add(Aspect.FIRE, 1));
        if (MiaBlocks.armoredGlass != null)
            register.registerObjectTag(new ItemStack(MiaBlocks.armoredGlass), AspectHelper.getObjectAspects(new ItemStack(MiaBlocks.armoredGlass)).add(Aspect.PROTECT, 3));
        if (MiaBlocks.eggSorter != null)
            register.registerObjectTag(new ItemStack(MiaBlocks.eggSorter), AspectHelper.getObjectAspects(new ItemStack(MiaBlocks.eggSorter)).add(Aspect.PLANT, 18));
        if (MiaBlocks.blockBotaniaSpecialFlower != null)
        {
            AspectList aspects = new AspectList().add(Aspect.PLANT, 15).add(Aspect.SENSES, 15).add(Aspect.DESIRE, 10).add(Aspect.METAL, 5).add(Aspect.VOID, 5).add(Aspect.MAGIC, 5);
    
            register.registerComplexObjectTag(ItemBlockSpecialFlower.ofType("orechidVacuam"), aspects);
            register.registerComplexObjectTag(ItemBlockSpecialFlower.ofType(new ItemStack(ModBlocks.floatingSpecialFlower), "orechidVacuam"), aspects);
        }
    }
}
