package com.github.sokyranthedragon.mia.integrations.thaumcraft.foci;

import com.github.sokyranthedragon.mia.potions.ModPotions;
import net.minecraft.potion.Potion;
import thaumcraft.api.casters.NodeSetting;
import thaumcraft.client.fx.particles.FXGeneric;

public class FocusEffectSizeStabilization extends FocusEffectSizeChange
{
    @Override
    public NodeSetting[] createSettings()
    {
        return new NodeSetting[] {
            new NodeSetting("duration", "focus.common.duration", new NodeSetting.NodeSettingIntRange(1, 5))};
    }
    
    @Override
    public int getComplexity()
    {
        return 5 * getSettingValue("duration");
    }
    
    @Override
    public String getKey()
    {
        return "mia.focus.size_stabilization";
    }
    
    @Override
    public String getResearch()
    {
        return "MIA.FOCUS_SIZE_NEUTRAL@2";
    }
    
    @Override
    protected Potion getEffect()
    {
        return ModPotions.sizeStabilizationPotion;
    }
    
    @Override
    protected void setParticleRgb(FXGeneric fx)
    {
        fx.setRBGColorF(0.88f, 0.88f, 0.88f);
    }
}
