package com.github.sokyranthedragon.mia.integrations.iceandfire.client;

import com.github.alexthe666.iceandfire.entity.EntitySnowVillager;
import com.github.alexthe666.iceandfire.entity.IafVillagerRegistry;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

import java.lang.reflect.Field;

public class EntityCustomSnowVillager extends EntitySnowVillager
{
    protected static final DataParameter<Integer> PROFESSION;
    protected static final Field professionField;
    
    static
    {
        DataParameter<Integer> professionData;
        Field profession;
        
        try
        {
            //noinspection unchecked
            professionData = (DataParameter<Integer>) ObfuscationReflectionHelper.findField(EntityVillager.class, "field_184752_bw").get(null);
            profession = EntitySnowVillager.class.getDeclaredField("prof");
            profession.setAccessible(true);
        } catch (IllegalAccessException | NoSuchFieldException e)
        {
            professionData = null;
            profession = null;
        }
        
        PROFESSION = professionData;
        professionField = profession;
    }
    
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
    
//    @Override
//    public void notifyDataManagerChange(DataParameter<?> key)
//    {
//        super.notifyDataManagerChange(key);
//
//        if (key.equals(PROFESSION))
//            setProfession(this.dataManager.get(PROFESSION));
//    }
}