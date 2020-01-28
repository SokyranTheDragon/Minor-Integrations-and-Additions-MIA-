package com.github.sokyranthedragon.mia.integrations.natura;

import com.gendeathrow.hatchery.api.crafting.ShredderRecipe;
import com.gendeathrow.hatchery.block.shredder.ShredderTileEntity;
import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.integrations.hatchery.IHatcheryIntegration;
import com.progwml6.natura.overworld.NaturaOverworld;
import com.progwml6.natura.shared.NaturaCommons;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.item.ItemStack;

import static com.progwml6.natura.Natura.pulseManager;

@MethodsReturnNonnullByDefault
class HatcheryNaturaIntegration implements IHatcheryIntegration
{
    @Override
    public void registerShredder()
    {
        if (pulseManager.isPulseLoaded(NaturaOverworld.PulseId))
            ShredderTileEntity.shredderRecipes.add(new ShredderRecipe(new ItemStack(NaturaOverworld.bluebellsFlower), new ItemStack(NaturaCommons.materials, 2, 8)));
    }
    
    // Since we're not adding any lucky egg drops, we just drop true in here since we're only going to have this class created only if the actual integration is on
    @Override
    public boolean isModEnabled()
    {
        return true;
    }
    
    @Override
    public ModIds getModId()
    {
        return ModIds.NATURA;
    }
}
