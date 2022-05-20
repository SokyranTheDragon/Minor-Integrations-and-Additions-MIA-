package com.github.sokyranthedragon.mia.integrations.natura;

import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.integrations.futuremc.FutureMc;
import com.github.sokyranthedragon.mia.integrations.futuremc.IFutureMcIntegration;
import com.progwml6.natura.common.block.BlockEnumBerryBush;
import com.progwml6.natura.entities.NaturaEntities;
import com.progwml6.natura.nether.NaturaNether;
import com.progwml6.natura.overworld.NaturaOverworld;
import com.progwml6.natura.overworld.block.crops.BlockNaturaBarley;
import com.progwml6.natura.overworld.block.crops.BlockNaturaCotton;
import com.progwml6.natura.shared.NaturaCommons;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import thedarkcolour.futuremc.api.BeePollinationHandler;
import thedarkcolour.futuremc.api.BeePollinationHandlerJVM;

import static com.github.sokyranthedragon.mia.integrations.futuremc.FutureMc.addCampfireRecipe;
import static com.progwml6.natura.Natura.pulseManager;

@MethodsReturnNonnullByDefault
class FutureMcNaturaIntegration implements IFutureMcIntegration
{
    @Override
    public void addRecipes()
    {
        if (pulseManager.isPulseLoaded(NaturaEntities.PulseId) && pulseManager.isPulseLoaded(NaturaCommons.PulseId))
            addCampfireRecipe(new ItemStack(NaturaCommons.edibles, 1, 0), new ItemStack(NaturaCommons.edibles, 1, 1));
    
        BeePollinationHandler berryHandler = null;
        
        if (pulseManager.isPulseLoaded(NaturaOverworld.PulseId))
        {
            berryHandler = new FutureMc.GenericBeePollinationHandler(BlockEnumBerryBush.AGE, 3);
            
            BeePollinationHandlerJVM.registerHandler(NaturaOverworld.barleyCrop,
                new FutureMc.GenericBeePollinationHandler(BlockNaturaBarley.AGE, 3));
            BeePollinationHandlerJVM.registerHandler(NaturaOverworld.cottonCrop,
                new FutureMc.GenericBeePollinationHandler(BlockNaturaCotton.AGE, 4));
            BeePollinationHandlerJVM.registerHandler(NaturaOverworld.overworldBerryBushBlackberry, berryHandler);
            BeePollinationHandlerJVM.registerHandler(NaturaOverworld.overworldBerryBushBlueberry, berryHandler);
            BeePollinationHandlerJVM.registerHandler(NaturaOverworld.overworldBerryBushMaloberry, berryHandler);
            BeePollinationHandlerJVM.registerHandler(NaturaOverworld.overworldBerryBushRaspberry, berryHandler);
        }
        
        if (pulseManager.isPulseLoaded(NaturaNether.PulseId))
        {
            if (berryHandler == null)
                berryHandler = new FutureMc.GenericBeePollinationHandler(BlockEnumBerryBush.AGE, 3);
    
            BeePollinationHandlerJVM.registerHandler(NaturaNether.netherBerryBushBlightberry, berryHandler);
            BeePollinationHandlerJVM.registerHandler(NaturaNether.netherBerryBushDuskberry, berryHandler);
            BeePollinationHandlerJVM.registerHandler(NaturaNether.netherBerryBushSkyberry, berryHandler);
            BeePollinationHandlerJVM.registerHandler(NaturaNether.netherBerryBushStingberry, berryHandler);
        }
    }
    
    @Override
    public IBlockState[] registerPollinationFlowers()
    {
        if (pulseManager.isPulseLoaded(NaturaOverworld.PulseId))
            return new IBlockState[] { NaturaOverworld.bluebellsFlower.getDefaultState() };
        
        return new IBlockState[0];
    }
    
    @Override
    public ModIds getModId()
    {
        return ModIds.NATURA;
    }
}
