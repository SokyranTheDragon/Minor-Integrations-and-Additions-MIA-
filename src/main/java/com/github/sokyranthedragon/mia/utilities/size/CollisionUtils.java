package com.github.sokyranthedragon.mia.utilities.size;

import com.google.common.base.Predicates;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.client.FMLClientHandler;

import java.util.List;

public class CollisionUtils
{
    public static RayTraceResult getMouseOverExtended(float distance)
    {
        Minecraft mc = FMLClientHandler.instance().getClient();
        Entity entity = mc.getRenderViewEntity();
        
        if (entity == null || mc.world == null)
            return null;
        
        double distOrg = distance;
        double dist = distOrg;
        
        RayTraceResult ray = entity.rayTrace(distOrg, 0);
        Vec3d vec = entity.getPositionEyes(0);
        
        if (ray != null)
            dist = ray.hitVec.distanceTo(vec);
        
        Vec3d vecEntity = entity.getLook(1.0F);
        Vec3d vecMoved = vec.add(vecEntity.x * distOrg, vecEntity.y * distOrg, vecEntity.z * distOrg);
        
        //noinspection Guava
        List<Entity> list = mc.world.getEntitiesInAABBexcluding(entity, entity.getEntityBoundingBox()
                                                                              .expand(vecEntity.x * distOrg, vecEntity.y * distOrg, vecEntity.z * distOrg)
                                                                              .grow(1.0D, 1.0D, 1.0D), Predicates.and(EntitySelectors.NOT_SPECTATING, e -> e != null && e.canBeCollidedWith()));
        
        double distCheck = dist;
        Vec3d tempVec = null;
        Entity closest = null;
        
        for (int j = 0; j < list.size(); j++)
        {
            Entity tempEntity = list.get(j);
            AxisAlignedBB axisalignedbb = tempEntity.getEntityBoundingBox().grow((double) tempEntity.getCollisionBorderSize());
            RayTraceResult raytraceresult = axisalignedbb.calculateIntercept(vec, vecMoved);
            
            if (axisalignedbb.contains(vec))
            {
                if (distCheck >= 0.0D)
                {
                    closest = tempEntity;
                    tempVec = raytraceresult == null ? vec : raytraceresult.hitVec;
                    distCheck = 0.0D;
                }
            }
            else if (raytraceresult != null)
            {
                double d3 = vec.distanceTo(raytraceresult.hitVec);
                
                if (d3 < distCheck || distCheck == 0.0D)
                {
                    if (tempEntity.getLowestRidingEntity() == entity.getLowestRidingEntity() && !tempEntity.canRiderInteract())
                    {
                        if (distCheck == 0.0D)
                        {
                            closest = tempEntity;
                            tempVec = raytraceresult.hitVec;
                        }
                    }
                    else
                    {
                        closest = tempEntity;
                        tempVec = raytraceresult.hitVec;
                        distCheck = d3;
                    }
                }
            }
        }
        
        if (closest != null && vec.distanceTo(tempVec) > distance)
        {
            closest = null;
            ray = new RayTraceResult(RayTraceResult.Type.MISS, tempVec, (EnumFacing) null, new BlockPos(tempVec));
        }
        
        if (closest != null && (distCheck < dist || ray == null))
        {
            ray = new RayTraceResult(closest, tempVec);
        }
        
        return ray;
    }
}
