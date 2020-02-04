package com.github.sokyranthedragon.mia.integrations.botania.wiki;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import vazkii.botania.api.wiki.SimpleWikiProvider;

public class PartialSimpleWikiProvider extends SimpleWikiProvider
{
    private static final String wikiUrl = "https://github.com/SokyranTheDragon/Minor-Integrations-and-Additions-MIA-/wiki/%s";
    
    public PartialSimpleWikiProvider()
    {
        super("MIA Wiki", wikiUrl, "-");
    }
    
    @Override
    public String getBlockName(World world, RayTraceResult pos, EntityPlayer player)
    {
        BlockPos bPos = pos.getBlockPos();
        IBlockState state = world.getBlockState(bPos);
        if (state.getBlock() instanceof IWikiProvider)
            return super.getBlockName(world, pos, player);
        return null;
    }
    
    @Override
    public String getWikiURL(World world, RayTraceResult pos, EntityPlayer player)
    {
        String url = super.getWikiURL(world, pos, player);
        
        if (url == null)
            return wikiUrl;
        return url;
    }
}
