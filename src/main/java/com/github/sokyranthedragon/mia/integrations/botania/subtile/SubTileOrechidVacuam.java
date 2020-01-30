package com.github.sokyranthedragon.mia.integrations.botania.subtile;

import com.github.sokyranthedragon.api.botania.MiaBotaniaAPI;
import com.github.sokyranthedragon.mia.integrations.botania.lexicon.MiaLexiconData;
import com.google.common.base.Predicate;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.DimensionType;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.common.block.subtile.functional.SubTileOrechid;

import java.util.Map;

public class SubTileOrechidVacuam extends SubTileOrechid
{
    private static final int COST = 22500;
    
    @Override
    public boolean canOperate()
    {
        return supertile.getWorld().provider.getDimensionType() == DimensionType.THE_END;
    }
    
    @Override
    public Map<String, Integer> getOreMap()
    {
        return MiaBotaniaAPI.oreWeightsEnd;
    }
    
    @Override
    @SuppressWarnings("Guava")
    public Predicate<IBlockState> getReplaceMatcher()
    {
        return (state) -> state != null && state.getBlock() == Blocks.END_STONE;
    }
    
    @Override
    public int getCost()
    {
        return COST;
    }
    
    @Override
    public int getColor()
    {
        return 14540709; // DDDFA5 - from color picker on End Stone over entire texture
    }
    
    @Override
    public LexiconEntry getEntry()
    {
        return MiaLexiconData.orechidVacuam;
    }
}
