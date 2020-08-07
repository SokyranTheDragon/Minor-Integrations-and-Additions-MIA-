package com.github.sokyranthedragon.mia.integrations.mia;

import com.github.sokyranthedragon.mia.core.MiaBlocks;
import com.github.sokyranthedragon.mia.core.MiaItems;
import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.integrations.base.IBaseMod;
import com.github.sokyranthedragon.mia.integrations.base.IModIntegration;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Optional;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectEventProxy;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.aspects.AspectRegistryEvent;
import vazkii.botania.common.block.ModBlocks;
import vazkii.botania.common.item.block.ItemBlockSpecialFlower;

import java.util.function.BiConsumer;

import static com.github.sokyranthedragon.mia.integrations.ModIds.TINKERS_CONSTRUCT;
import static com.github.sokyranthedragon.mia.integrations.thaumcraft.ThaumcraftHelpers.transferAspects;

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
            AspectList aspects = new AspectList();
            Aspect draco = Aspect.getAspect("draco");
            
            if (draco != null)
                aspects.add(draco, 2);
            aspects.add(Aspect.MAGIC, 5);
            
            transferAspects(new ItemStack(MiaItems.koboldRing), aspects, register, false);
        }
        if (MiaBlocks.torchGold != null)
            transferAspects(new ItemStack(MiaBlocks.torchGold), new ItemStack(Blocks.TORCH), register, false);
        if (MiaBlocks.flowerDead != null)
            transferAspects(new ItemStack(MiaBlocks.flowerDead), new AspectList().add(Aspect.PLANT, 1).add(Aspect.FIRE, 1), register, false);
        if (MiaBlocks.armoredGlass != null)
            transferAspects(new ItemStack(MiaBlocks.armoredGlass), new AspectList().add(Aspect.PROTECT, 3), register, false);
        if (MiaBlocks.eggSorter != null)
            transferAspects(new ItemStack(MiaBlocks.eggSorter), new AspectList().add(Aspect.PLANT, 18), register, false);
        if (MiaBlocks.doorStone != null)
            transferAspects(new ItemStack(MiaBlocks.doorStone), new AspectList().add(Aspect.MECHANISM, 5).add(Aspect.TRAP, 5), register, false);
        if (MiaBlocks.blockBotaniaSpecialFlower != null)
        {
            AspectList aspects = new AspectList().add(Aspect.PLANT, 15).add(Aspect.SENSES, 15).add(Aspect.DESIRE, 10).add(Aspect.METAL, 5).add(Aspect.VOID, 5).add(Aspect.MAGIC, 5);
            
            register.registerComplexObjectTag(ItemBlockSpecialFlower.ofType("orechidVacuam"), aspects);
            register.registerComplexObjectTag(ItemBlockSpecialFlower.ofType(new ItemStack(ModBlocks.floatingSpecialFlower), "orechidVacuam"), aspects);
        }
    }
}
