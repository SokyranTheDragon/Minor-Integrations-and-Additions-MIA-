package com.github.sokyranthedragon.mia.integrations.iceandfire;

import com.github.alexthe666.iceandfire.block.IafBlockRegistry;
import com.github.alexthe666.iceandfire.entity.EntityHippocampus;
import com.github.alexthe666.iceandfire.item.IafItemRegistry;
import com.github.sokyranthedragon.mia.block.BlockPixieDustExtractor;
import com.github.sokyranthedragon.mia.config.MiaConfig;
import com.github.sokyranthedragon.mia.core.MiaBlocks;
import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.integrations.base.IBaseMod;
import com.github.sokyranthedragon.mia.integrations.base.IModIntegration;
import com.github.sokyranthedragon.mia.integrations.iceandfire.client.RenderHippocampusJer;
import com.github.sokyranthedragon.mia.tile.TilePixieDustExtractor;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.aspects.AspectRegistryEvent;

import java.util.function.BiConsumer;

import static com.github.sokyranthedragon.mia.config.IceAndFireConfiguration.*;
import static com.github.sokyranthedragon.mia.integrations.ModIds.*;

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
        if (enableIFIntegration && INDUSTRIAL_FOREGOING.isLoaded)
            modIntegration.accept(INDUSTRIAL_FOREGOING, new IndustrialForegoingIceAndFireIntegration());
        if (enableChiselIntegration && CHISEL.isLoaded)
            modIntegration.accept(CHISEL, new ChiselIceAndFireIntegration());
    }
    
    @Override
    public void init(FMLInitializationEvent event)
    {
        if (!iceandfireAdditionsEnabled)
            return;
        
        if (!MiaConfig.disableAllRecipes)
        {
            FurnaceRecipes furnaceRecipes = FurnaceRecipes.instance();
            
            // Smelting weapons/armor to nuggets (vanilla smelting)
            furnaceRecipes.addSmeltingRecipe(new ItemStack(IafItemRegistry.silver_sword), new ItemStack(IafItemRegistry.silverNugget), 0.1f);
            furnaceRecipes.addSmeltingRecipe(new ItemStack(IafItemRegistry.silver_axe), new ItemStack(IafItemRegistry.silverNugget), 0.1f);
            furnaceRecipes.addSmeltingRecipe(new ItemStack(IafItemRegistry.silver_pickaxe), new ItemStack(IafItemRegistry.silverNugget), 0.1f);
            furnaceRecipes.addSmeltingRecipe(new ItemStack(IafItemRegistry.silver_hoe), new ItemStack(IafItemRegistry.silverNugget), 0.1f);
            furnaceRecipes.addSmeltingRecipe(new ItemStack(IafItemRegistry.silver_shovel), new ItemStack(IafItemRegistry.silverNugget), 0.1f);
            
            furnaceRecipes.addSmeltingRecipe(new ItemStack(IafItemRegistry.silver_helmet), new ItemStack(IafItemRegistry.silverNugget), 0.1f);
            furnaceRecipes.addSmeltingRecipe(new ItemStack(IafItemRegistry.silver_chestplate), new ItemStack(IafItemRegistry.silverNugget), 0.1f);
            furnaceRecipes.addSmeltingRecipe(new ItemStack(IafItemRegistry.silver_leggings), new ItemStack(IafItemRegistry.silverNugget), 0.1f);
            furnaceRecipes.addSmeltingRecipe(new ItemStack(IafItemRegistry.silver_boots), new ItemStack(IafItemRegistry.silverNugget), 0.1f);
        }
        
        if (!MiaConfig.disableOreDict)
        {
            OreDictionary.registerOre("logWood", new ItemStack(IafBlockRegistry.dreadwood_log));
            OreDictionary.registerOre("plankWood", new ItemStack(IafBlockRegistry.dreadwood_planks));
    
            OreDictionary.registerOre("stoneDreastone", new ItemStack(IafBlockRegistry.dread_stone));
            OreDictionary.registerOre("stoneDreastonePolished", new ItemStack(IafBlockRegistry.dread_stone_tile));
            OreDictionary.registerOre("brickDreadstone", new ItemStack(IafBlockRegistry.dread_stone_bricks));
            OreDictionary.registerOre("blockMossy", new ItemStack(IafBlockRegistry.dread_stone_bricks_mossy));
        }
    }
    
    @Override
    public void postInit(FMLPostInitializationEvent event)
    {
        if (enableJerIntegration && JER.isLoaded && event.getSide() == Side.CLIENT)
            registerRenderingOverride();
    }
    
    @SuppressWarnings("deprecation")
    @SideOnly(Side.CLIENT)
    private void registerRenderingOverride()
    {
        RenderingRegistry.registerEntityRenderingHandler(EntityHippocampus.class, new RenderHippocampusJer(Minecraft.getMinecraft().getRenderManager()));
    }
    
    @Override
    public void registerBlocks(RegistryEvent.Register<Block> event)
    {
        if (!iceandfireAdditionsEnabled)
            return;
        
        MiaBlocks.pixieDustExtractor = MiaBlocks.registerBlock(new BlockPixieDustExtractor());
        
        GameRegistry.registerTileEntity(TilePixieDustExtractor.class, new ResourceLocation("mia", "pixie_dust_extractor"));
    }
    
    @Override
    @Optional.Method(modid = ConstantIds.THAUMCRAFT)
    public void registerAspects(AspectRegistryEvent event)
    {
        if (!iceandfireAdditionsEnabled)
            return;
        
        Aspect MYTHICAL = Aspect.getAspect("mythus");
        event.register.registerObjectTag(new ItemStack(IafBlockRegistry.pixieHouse, 1, 0), (new AspectList()).add(Aspect.EARTH, 2).add(Aspect.CRAFT, 5).add(Aspect.PLANT, 2).add(MYTHICAL, 2).add(Aspect.MAGIC, 2).add(Aspect.METAL, 60).add(Aspect.LIGHT, 3).add(Aspect.SENSES, 1).add(Aspect.AIR, 1).add(Aspect.CRYSTAL, 1));
    }
}
