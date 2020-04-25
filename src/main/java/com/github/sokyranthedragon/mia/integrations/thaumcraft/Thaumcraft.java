package com.github.sokyranthedragon.mia.integrations.thaumcraft;

import com.github.sokyranthedragon.mia.Mia;
import com.github.sokyranthedragon.mia.block.BlockVoidCreator;
import com.github.sokyranthedragon.mia.core.MiaBlocks;
import com.github.sokyranthedragon.mia.dispenserbehavior.DispenserLootBag;
import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.integrations.base.IBaseMod;
import com.github.sokyranthedragon.mia.integrations.base.IModIntegration;
import com.github.sokyranthedragon.mia.tile.TileVoidCreator;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDispenser;
import net.minecraft.dispenser.IBehaviorDispenseItem;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.blocks.BlocksTC;
import thaumcraft.api.crafting.InfusionRecipe;
import thaumcraft.api.items.ItemsTC;
import thaumcraft.common.blocks.world.BlockLoot;
import thaumcraft.common.items.curios.ItemLootBag;
import thaumcraft.common.lib.utils.Utils;

import java.util.function.BiConsumer;

import static com.github.sokyranthedragon.mia.config.ThaumcraftConfiguration.*;
import static com.github.sokyranthedragon.mia.integrations.ModIds.*;

public class Thaumcraft implements IBaseMod
{
    @Override
    public void register(BiConsumer<ModIds, IModIntegration> modIntegration)
    {
        if (JEI.isLoaded)
            modIntegration.accept(JEI, new JeiThaumcraftIntegration());
        if (enableJerIntegration && JER.isLoaded)
            modIntegration.accept(JER, new JerThaumcraftIntegration());
        if (enableTeIntegration && THERMAL_EXPANSION.isLoaded)
            modIntegration.accept(THERMAL_EXPANSION, new ThermalExpansionThaumcraftIntegration());
        if (enableXu2Integration && EXTRA_UTILITIES.isLoaded)
            modIntegration.accept(EXTRA_UTILITIES, new ExtraUtilsThaumcraftIntegration());
        if (HATCHERY.isLoaded)
            modIntegration.accept(HATCHERY, new HatcheryThaumcraftIntegration(enableHatcheryIntegration));
        if (enableDungeonTacticsIntegration && DUNGEON_TACTICS.isLoaded)
            modIntegration.accept(DUNGEON_TACTICS, new DungeonTacticsThaumcraftIntegration());
        if (enableFutureMcIntegration && FUTURE_MC.isLoaded)
            modIntegration.accept(FUTURE_MC, new FutureMcThaumcraftIntegration());
        if (enableChiselIntegration && CHISEL.isLoaded)
            modIntegration.accept(CHISEL, new ChiselThaumcraftIntegration());
        if (INDUSTRIAL_FOREGOING.isLoaded)
            modIntegration.accept(INDUSTRIAL_FOREGOING, new IndustrialForegoingThaumcraftIntegration());
    }
    
    @Override
    public void preInit(FMLPreInitializationEvent event)
    {
        if (!thaumcraftAdditionsEnabled)
            return;
        
        ThaumcraftApi.registerResearchLocation(new ResourceLocation(Mia.MODID, "research/basics.json"));
    }
    
    @Override
    public void registerBlocks(RegistryEvent.Register<Block> event)
    {
        if (!thaumcraftAdditionsEnabled)
            return;
        
        MiaBlocks.voidCreator = MiaBlocks.registerBlock(new BlockVoidCreator());
        
        GameRegistry.registerTileEntity(TileVoidCreator.class, new ResourceLocation("mia", "void_creator"));
    }
    
    @SuppressWarnings("ConstantConditions")
    @Override
    public void registerItems(RegistryEvent.Register<Item> event)
    {
        if (!thaumcraftAdditionsEnabled)
            return;
        
        ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation(Mia.MODID, "void_creator"),
            new InfusionRecipe(
                "MIA.VOID_CREATOR",
                new ItemStack(MiaBlocks.voidCreator),
                9,
                new AspectList().add(Aspect.ELDRITCH, 50).add(Aspect.CRAFT, 50).add(Aspect.ENTROPY, 50).add(Aspect.VOID, 100),
                new ItemStack(Items.GHAST_TEAR),
                new ItemStack(BlocksTC.stoneArcane),
                new ItemStack(BlocksTC.stoneArcane),
                new ItemStack(ItemsTC.mechanismComplex),
                "plateBrass",
                "plateBrass",
                new ItemStack(Items.DIAMOND),
                new ItemStack(Items.DIAMOND),
                new ItemStack(Items.NETHER_STAR)
            ));
    }
    
    @Override
    public void registerDispenserBehaviors()
    {
        DispenserLootBag.getInstance().addListener(((source, stack) ->
        {
            if (stack.getItem() instanceof ItemLootBag)
            {
                stack.shrink(1);
                IBehaviorDispenseItem defaultDispenserBehavior = BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.getObject(null);
                int count = 8 + source.getWorld().rand.nextInt(5);
                
                for (int i = 0; i < count; i++)
                {
                    ItemStack lootItem = Utils.generateLoot(stack.getMetadata(), source.getWorld().rand);
                    if (!lootItem.isEmpty())
                        defaultDispenserBehavior.dispense(source, lootItem);
                }
                return true;
            }
            
            Block block = Block.getBlockFromItem(stack.getItem());
            if (block instanceof BlockLoot)
            {
                stack.shrink(1);
                IBehaviorDispenseItem defaultDispenserBehavior = BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.getObject(null);
                
                //noinspection deprecation
                for (ItemStack lootItem : block.getDrops(source.getWorld(), source.getBlockPos(), source.getBlockState(), 0))
                {
                    if (!lootItem.isEmpty())
                        defaultDispenserBehavior.dispense(source, lootItem);
                }
                
                return true;
            }
            
            return false;
        }), new ItemStack(ItemsTC.lootBag), new ItemStack(BlocksTC.lootCrateCommon), new ItemStack(BlocksTC.lootCrateUncommon), new ItemStack(BlocksTC.lootCrateRare), new ItemStack(BlocksTC.lootUrnCommon), new ItemStack(BlocksTC.lootUrnUncommon), new ItemStack(BlocksTC.lootUrnRare));
    }
}
