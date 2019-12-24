package com.github.exploder1531.mia.integrations.iceandfire.client;

import com.github.alexthe666.iceandfire.client.render.entity.RenderHippocampus;
import net.minecraft.client.renderer.entity.RenderManager;

public class RenderHippocampusJer extends RenderHippocampus
{
    public RenderHippocampusJer(RenderManager renderManager)
    {
        super(renderManager);
        mainModel = new ModelHippocampusJer();
    }
}
