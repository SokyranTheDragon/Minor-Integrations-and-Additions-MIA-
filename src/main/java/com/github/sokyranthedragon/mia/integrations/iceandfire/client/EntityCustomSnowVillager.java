package com.github.sokyranthedragon.mia.integrations.iceandfire.client;

import com.github.alexthe666.iceandfire.entity.EntitySnowVillager;
import com.github.alexthe666.iceandfire.entity.IafVillagerRegistry;
import net.minecraft.world.World;

public class EntityCustomSnowVillager extends EntitySnowVillager
{
    public EntityCustomSnowVillager(World worldIn)
    {
        super(worldIn);
    }
    
    public EntityCustomSnowVillager(World worldIn, int profession)
    {
        super(worldIn, profession);
    }
    
    @Override
    public void setProfession(int professionId)
    {
        super.setProfession(professionId);
        getEntityData().setString("ProfessionName", IafVillagerRegistry.INSTANCE.professions.get(professionId).getCareer(0).getName());
    }
}