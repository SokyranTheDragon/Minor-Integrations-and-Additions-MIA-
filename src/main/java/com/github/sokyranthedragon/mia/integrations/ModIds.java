package com.github.sokyranthedragon.mia.integrations;

import baubles.common.Baubles;
import biomesoplenty.core.BiomesOPlenty;
import cofh.CoFHCore;
import cofh.thermalexpansion.ThermalExpansion;
import cofh.thermalfoundation.ThermalFoundation;
import com.buuz135.industrial.api.IndustrialForegoingHelper;
import com.gendeathrow.hatchery.Hatchery;
import com.github.alexthe666.iceandfire.IceAndFire;
import com.github.sokyranthedragon.mia.Mia;
import com.github.sokyranthedragon.mia.integrations.jer.ResourceLocationWrapper;
import com.legacy.aether.Aether;
import com.legacy.aether.addon.AetherAddon;
import com.legacy.lostaether.LostAetherContent;
import com.meteor.extrabotany.ExtraBotany;
import com.progwml6.natura.Natura;
import com.rwtema.extrautils2.ExtraUtils2;
import com.shinoow.abyssalcraft.AbyssalCraft;
import crafttweaker.mc1120.CraftTweaker;
import drzhark.mocreatures.MoCConstants;
import mcjty.theoneprobe.TheOneProbe;
import mezz.jei.config.Constants;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.ModContainer;
import pegbeard.dungeontactics.reference.Reference;
import slimeknights.tconstruct.TConstruct;
import team.chisel.Chisel;
import team.chisel.ctm.CTM;
import thaumcraft.Thaumcraft;
import thedarkcolour.futuremc.FutureMC;

public enum ModIds
{
    MIA(Mia.MODID),
    ARTEMISLIB(ConstantIds.ARTEMISLIB),
    EXTRA_UTILITIES(ConstantIds.EXTRA_UTILITIES),
    COFH_CORE(ConstantIds.COFH_CORE),
    THERMAL_FOUNDATION(ConstantIds.THERMAL_FOUNDATION),
    THERMAL_EXPANSION(ConstantIds.THERMAL_EXPANSION),
    TINKERS_CONSTRUCT(ConstantIds.TINKERS_CONSTRUCT),
    JEI(ConstantIds.JEI),
    JER(ConstantIds.JER),
    ICE_AND_FIRE(ConstantIds.ICE_AND_FIRE),
    HATCHERY(ConstantIds.HATCHERY),
    BAUBLES(ConstantIds.BAUBLES),
    THAUMCRAFT(ConstantIds.THAUMCRAFT),
    THE_ONE_PROBE(ConstantIds.THE_ONE_PROBE),
    MO_CREATURES(ConstantIds.MO_CREATURES),
    HARVESTCRAFT(ConstantIds.HARVESTCRAFT),
    DUNGEON_TACTICS(ConstantIds.DUNGEON_TACTICS),
    BOTANIA(ConstantIds.BOTANIA),
    EXTRABOTANY(ConstantIds.EXTRABOTANY),
    QUARK(ConstantIds.QUARK),
    CRAFT_TWEAKER(ConstantIds.CRAFT_TWEAKER),
    FUTURE_MC(ConstantIds.FUTURE_MC),
    NATURA(ConstantIds.NATURA),
    BIOMES_O_PLENTY(ConstantIds.BIOMES_O_PLENTY),
    CONNECTED_TEXTURES(ConstantIds.CONNECTED_TEXTURES),
    CHISEL(ConstantIds.CHISEL),
    INDUSTRIAL_FOREGOING(ConstantIds.INDUSTRIAL_FOREGOING),
    ABYSSALCRAFT(ConstantIds.ABYSSALCRAFT),
    AETHER(ConstantIds.AETHER),
    AETHER_CONTINUATION(ConstantIds.AETHER_CONTINUATION),
    AETHER_LOST_CONTENT(ConstantIds.AETHER_LOST_CONTENT);
    
    public final String modId;
    public final boolean isLoaded;
    
    ModIds(String modId)
    {
        this.modId = modId;
        isLoaded = Loader.isModLoaded(modId);
    }
    
    public ResourceLocation loadSimple(String path)
    {
        return new ResourceLocation(modId, path);
    }
    
    public ResourceLocationWrapper loadResource(String path)
    {
        return new ResourceLocationWrapper(modId, path);
    }
    
    public ResourceLocationWrapper loadResource(String path, int id)
    {
        return new ResourceLocationWrapper(modId, path, id);
    }
    
    public ModContainer getModContainer()
    {
        return Loader.instance().getIndexedModList().get(modId);
    }
    
    @Override
    public String toString()
    {
        return modId;
    }
    
    public static class ConstantIds
    {
        private ConstantIds()
        {
        }
        
        public static final String ARTEMISLIB = com.artemis.artemislib.Reference.MODID;
        public static final String EXTRA_UTILITIES = ExtraUtils2.MODID;
        public static final String COFH_CORE = CoFHCore.MOD_ID;
        public static final String THERMAL_FOUNDATION = ThermalFoundation.MOD_ID;
        public static final String THERMAL_EXPANSION = ThermalExpansion.MOD_ID;
        public static final String TINKERS_CONSTRUCT = TConstruct.modID;
        public static final String JEI = Constants.MOD_ID;
        public static final String JER = jeresources.reference.Reference.ID;
        public static final String ICE_AND_FIRE = IceAndFire.MODID;
        public static final String HATCHERY = Hatchery.MODID;
        public static final String BAUBLES = Baubles.MODID;
        public static final String THAUMCRAFT = Thaumcraft.MODID;
        public static final String THE_ONE_PROBE = TheOneProbe.MODID;
        public static final String MO_CREATURES = MoCConstants.MOD_ID;
        public static final String HARVESTCRAFT = com.pam.harvestcraft.Reference.MODID;
        public static final String DUNGEON_TACTICS = Reference.MOD_ID;
        public static final String BOTANIA = vazkii.botania.common.lib.LibMisc.MOD_ID;
        public static final String EXTRABOTANY = ExtraBotany.MODID;
        public static final String QUARK = vazkii.quark.base.lib.LibMisc.MOD_ID;
        public static final String CRAFT_TWEAKER = CraftTweaker.MODID;
        public static final String FUTURE_MC = FutureMC.ID;
        public static final String NATURA = Natura.modID;
        public static final String BIOMES_O_PLENTY = BiomesOPlenty.MOD_ID;
        public static final String CONNECTED_TEXTURES = CTM.MOD_ID;
        public static final String CHISEL = Chisel.MOD_ID;
        public static final String INDUSTRIAL_FOREGOING = IndustrialForegoingHelper.MOD_ID;
        public static final String ABYSSALCRAFT = AbyssalCraft.modid;
        public static final String AETHER = Aether.modid;
        public static final String AETHER_CONTINUATION = AetherAddon.modid;
        public static final String AETHER_LOST_CONTENT = LostAetherContent.MODID;
    }
}
