package com.github.sokyranthedragon.mia.integrations.aether;

import com.github.sokyranthedragon.mia.config.MiaConfig;
import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.integrations.base.IBaseMod;
import com.github.sokyranthedragon.mia.integrations.base.IModIntegration;
import com.legacy.aether.blocks.BlocksAether;
import com.legacy.aether.items.ItemsAether;
import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import thaumcraft.api.aspects.*;

import java.util.Map;
import java.util.function.BiConsumer;

import static com.github.sokyranthedragon.mia.config.AetherConfig.*;
import static com.github.sokyranthedragon.mia.integrations.ModIds.*;
import static net.minecraftforge.oredict.OreDictionary.registerOre;

public class Aether implements IBaseMod
{
    @Override
    public void register(BiConsumer<ModIds, IModIntegration> modIntegration)
    {
        if (FUTURE_MC.isLoaded && enableFutureMcIntegration)
            modIntegration.accept(FUTURE_MC, new FutureMcAetherIntegration());
        if (DUNGEON_TACTICS.isLoaded && enableDungeonTacticsIntegration)
            modIntegration.accept(DUNGEON_TACTICS, new DungeonTacticsAetherIntegration());
        if (THERMAL_EXPANSION.isLoaded && enableTeIntegration)
            modIntegration.accept(THERMAL_EXPANSION, new ThermalExpansionAetherIntegration());
        if (JER.isLoaded)
            modIntegration.accept(JER, new JerAetherIntegration());
        if (INDUSTRIAL_FOREGOING.isLoaded)
            modIntegration.accept(INDUSTRIAL_FOREGOING, new IndustrialForegoingAetherIntegration());
        if (CHISEL.isLoaded && enableChiselIntegration)
            modIntegration.accept(CHISEL, new ChiselAetherIntegration());
        if (EXTRA_UTILITIES.isLoaded && enableXu2Integration)
            modIntegration.accept(EXTRA_UTILITIES, new ExtraUtilsAetherIntegration());
    }
    
    @Override
    public void init(FMLInitializationEvent event)
    {
        if (!aetherAdditionsEnabled)
            return;
        
        BlocksAether.aether_dirt.setHarvestLevel("shovel", -1);
        BlocksAether.aether_grass.setHarvestLevel("shovel", -1);
        BlocksAether.quicksoil.setHarvestLevel("shovel", -1);
        
        if (!MiaConfig.disableOreDict)
        {
            registerOre("listAllMilk", new ItemStack(ItemsAether.skyroot_bucket, 1, 4));
            registerOre("slimeball", new ItemStack(ItemsAether.swetty_ball));
            registerOre("bookshelf", new ItemStack(BlocksAether.skyroot_bookshelf));
            registerOre("plankWood", new ItemStack(BlocksAether.skyroot_plank));
            registerOre("grass", new ItemStack(BlocksAether.aether_grass));
            registerOre("dirt", new ItemStack(BlocksAether.aether_dirt));
            registerOre("blockMossy", new ItemStack(BlocksAether.mossy_holystone));
            
            registerOre("listAllBerry", new ItemStack(ItemsAether.blue_berry));
            registerOre("listAllFruit", new ItemStack(ItemsAether.blue_berry));
        }
    }
    
    @Override
    public void registerAspects(AspectRegistryEvent event)
    {
        AspectEventProxy register = event.register;
        
        register.registerObjectTag(new ItemStack(BlocksAether.enchanted_aether_grass), new AspectList().add(Aspect.EARTH, 5).add(Aspect.PLANT, 2).add(Aspect.MAGIC, 2));
        register.registerObjectTag(new ItemStack(BlocksAether.holystone), new AspectList().add(Aspect.EARTH, 5));
        register.registerObjectTag(new ItemStack(BlocksAether.holystone), new AspectList().add(Aspect.EARTH, 5).add(Aspect.PLANT, 3).add(Aspect.ENTROPY, 1));
        register.registerObjectTag(new ItemStack(BlocksAether.icestone), new AspectList().add(Aspect.COLD, 5));
        
        register.registerObjectTag(new ItemStack(ItemsAether.zanite_gemstone), new AspectList().add(Aspect.DESIRE, 15).add(Aspect.CRYSTAL, 15).add(Aspect.MAGIC, 1));
        
        AspectList list = new AspectList().add(Aspect.PLANT, 5).add(Aspect.SENSES, 5).add(Aspect.LIFE, 1);
        register.registerObjectTag(new ItemStack(BlocksAether.purple_flower), list);
        register.registerObjectTag(new ItemStack(BlocksAether.white_flower), list);
        
        AspectList tempList = AspectHelper.getObjectAspects(new ItemStack(Items.CHAINMAIL_CHESTPLATE));
        list = new AspectList();
        for (Map.Entry<Aspect, Integer> entries : tempList.aspects.entrySet())
        {
            if (entries.getValue() / 2 > 0)
                list.add(entries.getKey(), entries.getValue());
        }
        register.registerObjectTag(new ItemStack(ItemsAether.chain_gloves), list);
        
        for (Item item : ItemsAether.itemRegistry)
        {
            ItemStack stack = new ItemStack(item);
            if (item.isDamageable() || item.getMaxDamage(stack) == 0)
                addAirAspect(stack, register);
            else
            {
                for (int i = 0; i < item.getMaxDamage(stack); i++)
                    addAirAspect(stack, register);
            }
        }
        
        for (Block block : BlocksAether.blockList)
        {
            NonNullList<ItemStack> stacks = NonNullList.create();
            block.getSubBlocks(null, stacks);
            
            for (ItemStack stack : stacks)
                addAirAspect(stack, register);
        }
    }
    
    private static void addAirAspect(ItemStack stack, AspectEventProxy register)
    {
        AspectList aspects = AspectHelper.getObjectAspects(stack);
        
        if (aspects.getAmount(Aspect.AIR) == 0)
            register.registerObjectTag(stack, aspects.add(Aspect.AIR, 1));
    }
}
