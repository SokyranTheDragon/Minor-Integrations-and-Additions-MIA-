package com.github.exploder1531.mia.integrations.iceandfire;

import com.github.alexthe666.iceandfire.core.ModBlocks;
import com.github.alexthe666.iceandfire.core.ModItems;
import com.github.alexthe666.iceandfire.entity.EntityHippocampus;
import com.github.exploder1531.mia.block.BlockPixieDustExtractor;
import com.github.exploder1531.mia.core.MiaBlocks;
import com.github.exploder1531.mia.integrations.ModIds;
import com.github.exploder1531.mia.integrations.base.IBaseMod;
import com.github.exploder1531.mia.integrations.base.IModIntegration;
import com.github.exploder1531.mia.integrations.iceandfire.client.RenderHippocampusJer;
import com.github.exploder1531.mia.tile.TilePixieDustExtractor;
import com.github.exploder1531.mia.utilities.RegisterUtils;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.registries.IForgeRegistry;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.aspects.AspectRegistryEvent;

import java.util.function.BiConsumer;

import static com.github.exploder1531.mia.config.IceAndFireConfiguration.*;
import static com.github.exploder1531.mia.integrations.ModIds.*;

public class IceAndFire implements IBaseMod
{
    @Override
    public void register(BiConsumer<ModIds, IModIntegration> modIntegration)
    {
        if (enableXu2Integration && EXTRA_UTILITIES.isLoaded)
            modIntegration.accept(EXTRA_UTILITIES, new ExtraUtilsIceAndFireIntegration());
        if (enableTeIntegration && THERMAL_EXPANSION.isLoaded)
            modIntegration.accept(THERMAL_EXPANSION, new ThermalExpansionIceAndFireIntegration());
        if (enableJerIntegration && JER.isLoaded)
            modIntegration.accept(JER, new JerIceAndFireIntegration());
        if (enableTConstructIntegration && TINKERS_CONSTRUCT.isLoaded)
            modIntegration.accept(TINKERS_CONSTRUCT, new TConstructIceAndFireIntegration());
        if (HATCHERY.isLoaded)
            modIntegration.accept(HATCHERY, new HatcheryIceAndFireIntegration(enableHatcheryIntegration));
        if (enableDungeonTacticsIntegration && DUNGEON_TACTICS.isLoaded)
            modIntegration.accept(DUNGEON_TACTICS, new DungeonTacticsIceAndFireIntegration());
        if (enableFutureMcIntegration && FUTURE_MC.isLoaded)
            modIntegration.accept(FUTURE_MC, new FutureMcIceAndFireIntegration());
    }
    
    @Override
    public void init(FMLInitializationEvent event)
    {
        if (!iceandfireAdditionsEnabled)
            return;
        
        FurnaceRecipes furnaceRecipes = FurnaceRecipes.instance();
        
        // Smelting weapons/armor to nuggets (vanilla smelting)
        furnaceRecipes.addSmeltingRecipe(new ItemStack(ModItems.silver_sword), new ItemStack(ModItems.silverNugget), 0.1f);
        furnaceRecipes.addSmeltingRecipe(new ItemStack(ModItems.silver_axe), new ItemStack(ModItems.silverNugget), 0.1f);
        furnaceRecipes.addSmeltingRecipe(new ItemStack(ModItems.silver_pickaxe), new ItemStack(ModItems.silverNugget), 0.1f);
        furnaceRecipes.addSmeltingRecipe(new ItemStack(ModItems.silver_hoe), new ItemStack(ModItems.silverNugget), 0.1f);
        furnaceRecipes.addSmeltingRecipe(new ItemStack(ModItems.silver_shovel), new ItemStack(ModItems.silverNugget), 0.1f);
        
        furnaceRecipes.addSmeltingRecipe(new ItemStack(ModItems.silver_helmet), new ItemStack(ModItems.silverNugget), 0.1f);
        furnaceRecipes.addSmeltingRecipe(new ItemStack(ModItems.silver_chestplate), new ItemStack(ModItems.silverNugget), 0.1f);
        furnaceRecipes.addSmeltingRecipe(new ItemStack(ModItems.silver_leggings), new ItemStack(ModItems.silverNugget), 0.1f);
        furnaceRecipes.addSmeltingRecipe(new ItemStack(ModItems.silver_boots), new ItemStack(ModItems.silverNugget), 0.1f);
    }
    
    @SuppressWarnings("deprecation")
    @Override
    public void postInit(FMLPostInitializationEvent event)
    {
        if (JER.isLoaded && event.getSide() == Side.CLIENT)
            RenderingRegistry.registerEntityRenderingHandler(EntityHippocampus.class, new RenderHippocampusJer(Minecraft.getMinecraft().getRenderManager()));
    }
    
    @Override
    public void registerBlocks(RegistryEvent.Register<Block> event)
    {
        if (!iceandfireAdditionsEnabled)
            return;
        
        final IForgeRegistry<Block> registry = event.getRegistry();
        
        MiaBlocks.pixie_dust_extractor = new BlockPixieDustExtractor();
        
        registry.register(MiaBlocks.pixie_dust_extractor);
        
        GameRegistry.registerTileEntity(TilePixieDustExtractor.class, new ResourceLocation("mia", "pixie_dust_extractor"));
    }
    
    @SuppressWarnings("ConstantConditions")
    @Override
    public void registerItems(RegistryEvent.Register<Item> event)
    {
        if (!iceandfireAdditionsEnabled)
            return;
        
        final IForgeRegistry<Item> registry = event.getRegistry();
        
        registry.register(new ItemBlock(MiaBlocks.pixie_dust_extractor).setRegistryName(MiaBlocks.pixie_dust_extractor.getRegistryName()));
    }
    
    @Override
    public void registerRenders(ModelRegistryEvent event)
    {
        if (!iceandfireAdditionsEnabled)
            return;
        
        RegisterUtils.registerItemblockRenderer(MiaBlocks.pixie_dust_extractor);
    }
    
    @Override
    @Optional.Method(modid = ConstantIds.THAUMCRAFT)
    public void registerAspects(AspectRegistryEvent event)
    {
        if (!iceandfireAdditionsEnabled)
            return;
        
        Aspect MYTHICAL = Aspect.getAspect("mythus");
        event.register.registerObjectTag(new ItemStack(ModBlocks.pixieHouse, 1, 0), (new AspectList()).add(Aspect.EARTH, 2).add(Aspect.CRAFT, 5).add(Aspect.PLANT, 2).add(MYTHICAL, 2).add(Aspect.MAGIC, 2).add(Aspect.METAL, 60).add(Aspect.LIGHT, 3).add(Aspect.SENSES, 1).add(Aspect.AIR, 1).add(Aspect.CRYSTAL, 1));
    }
}
