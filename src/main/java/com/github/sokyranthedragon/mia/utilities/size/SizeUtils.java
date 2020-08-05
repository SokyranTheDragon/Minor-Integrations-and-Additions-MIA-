package com.github.sokyranthedragon.mia.utilities.size;

import com.artemis.artemislib.util.attributes.ArtemisLibAttributes;
import com.github.sokyranthedragon.mia.config.GenericAdditionsConfig;
import com.github.sokyranthedragon.mia.enchantments.EnchantmentKobold;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AbstractAttributeMap;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.common.util.FakePlayer;

import java.util.Arrays;
import java.util.UUID;

public class SizeUtils
{
    private SizeUtils()
    {
    }
    
    // Height
    public static UUID uuidHeight = UUID.fromString("5440b01a-974f-4495-bb9a-c7c87424bca4");
    public static UUID uuidWidth = UUID.fromString("3949d2ed-b6cc-4330-9c13-98777f48ea51");
    // Reach big
    public static UUID uuidReach1 = UUID.fromString("854e0004-c218-406c-a9e2-590f1846d80b");
    // Reach small
    public static UUID uuidReach2 = UUID.fromString("216080dc-22d3-4eff-a730-190ec0210d5c");
    // Health
    public static UUID uuidHealth = UUID.fromString("3b901d47-2d30-495c-be45-f0091c0f6fb2");
    // Strength
    public static UUID uuidStrength = UUID.fromString("558f55be-b277-4091-ae9b-056c7bc96e84");
    // Speed
    public static UUID uuidSpeed = UUID.fromString("f2fb5cda-3fbe-4509-a0af-4fc994e6aeca");
    // Swim speed
    public static UUID uuidSwimSpeed = UUID.fromString("d88a6e6c-faa9-4337-bb7d-73451500f78f");
    
    public static boolean isSizeComponentEnabled = false;
    
    public static float getEntitySize(EntityLivingBase entity)
    {
        if (entity instanceof FakePlayer)
            return 1;
        
        // Get all the attributes from entity
        AbstractAttributeMap entityAttributes = entity.getAttributeMap();
        
        // We either try to get current size from attributes, or set it as 1 if it's not there
        float size = 1;
        IAttributeInstance heightAttribute = entityAttributes.getAttributeInstanceByName(ArtemisLibAttributes.ENTITY_HEIGHT.getName());
        if (heightAttribute != null)
        {
            AttributeModifier height = heightAttribute.getModifier(uuidHeight);
            
            if (height != null)
                size = (float) height.getAmount() + 1f;
        }
        
        return size;
    }
    
    public static void changeEntitySizeBy(EntityLivingBase entity, float changeValue)
    {
        setEntitySize(entity, getEntitySize(entity) + changeValue);
    }
    
    
    public static void setEntitySize(EntityLivingBase entity, float size)
    {
        setEntitySize(entity, size, false);
    }
    
    public static void setEntitySize(EntityLivingBase entity, float size, boolean ignoreSizeMatch)
    {
        if (!isSizeComponentEnabled)
            return;
        if (entity instanceof FakePlayer)
            return;
        
        float maxSize;
        
        if (entity instanceof EntityPlayer)
        {
            size = MathHelper.clamp(size, GenericAdditionsConfig.sizeModule.minPlayerSize, GenericAdditionsConfig.sizeModule.maxPlayerSize);
            maxSize = GenericAdditionsConfig.sizeModule.maxPlayerSize;
        }
        else if (GenericAdditionsConfig.sizeModule.canScaleMobs && Arrays.stream(GenericAdditionsConfig.sizeModule.bannedEntitiesSize).noneMatch(x ->
        {
            ResourceLocation e = EntityList.getKey(entity);
            return e == null || x.equals(e.toString());
        }))
        {
            size = MathHelper.clamp(size, GenericAdditionsConfig.sizeModule.minMobSize, GenericAdditionsConfig.sizeModule.maxMobSize);
            maxSize = GenericAdditionsConfig.sizeModule.maxMobSize;
        }
        else
        {
            if (getEntitySize(entity) == 1)
                return;
            size = maxSize = 1f;
        }
        
        // Let's skip all this stuff if our size wouldn't change at all
        if (!ignoreSizeMatch && size == getEntitySize(entity))
            return;
        
        Multimap<String, AttributeModifier> sharedAttributes = HashMultimap.create(5, 1);
        Multimap<String, AttributeModifier> bigAttributes = HashMultimap.create();
        Multimap<String, AttributeModifier> smallAttributes = HashMultimap.create();
        
        // Setting size for entity
        sharedAttributes.put(ArtemisLibAttributes.ENTITY_HEIGHT.getName(), new AttributeModifier(uuidHeight, "Player Height", size - 1, 2));
        // Width
        sharedAttributes.put(ArtemisLibAttributes.ENTITY_WIDTH.getName(), new AttributeModifier(uuidWidth, "Player Width", MathHelper.clamp(size - 1.0F, -0.6D, maxSize), 2));
        
        if (size < 1 && entity instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer) entity;
            size = EnchantmentKobold.getSizeModifier(player, size);
        }
        
        if (GenericAdditionsConfig.sizeModule.scaleMovementSpeed)
            sharedAttributes.put(SharedMonsterAttributes.MOVEMENT_SPEED.getName(), new AttributeModifier(uuidSpeed, "Player Speed", (size - 1.0F) / 2.0F, 2));
        if (GenericAdditionsConfig.sizeModule.scaleStrength)
            sharedAttributes.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(uuidStrength, "Player Strength", size - 1.0F, 0));
        if (GenericAdditionsConfig.sizeModule.scaleMaxHealth)
            sharedAttributes.put(SharedMonsterAttributes.MAX_HEALTH.getName(), new AttributeModifier(uuidHealth, "Player Health", Math.min(1024f, (size - 1.0F) * GenericAdditionsConfig.sizeModule.healthMultiplier), 2));
        if (GenericAdditionsConfig.sizeModule.scaleSwimSpeed)
            sharedAttributes.put(EntityLivingBase.SWIM_SPEED.getName(), new AttributeModifier(uuidSwimSpeed, "Player Swim Speed", (size - 1.0F) / 4.0F, 2));
        
        if (GenericAdditionsConfig.sizeModule.scaleReachDistance && entity instanceof EntityPlayer)
        {
            bigAttributes.put(EntityPlayer.REACH_DISTANCE.getName(), new AttributeModifier(uuidReach1, "Player Reach 1", size - 1.0F, 2));
            
            smallAttributes.put(EntityPlayer.REACH_DISTANCE.getName(), new AttributeModifier(uuidReach2, "Player Reach 2", -MathHelper.clamp(size - 1.0F, 0.33D, 1.7976931348623157E308D), 2));
            
            if (size > 1.0F)
                entity.getAttributeMap().applyAttributeModifiers(bigAttributes);
            else
                entity.getAttributeMap().removeAttributeModifiers(bigAttributes);
            
            if (size < 1.0F)
                entity.getAttributeMap().applyAttributeModifiers(smallAttributes);
            else
                entity.getAttributeMap().removeAttributeModifiers(smallAttributes);
        }
        
        float startingHealth = entity.getMaxHealth();
        entity.getAttributeMap().applyAttributeModifiers(sharedAttributes);
        if (GenericAdditionsConfig.sizeModule.scaleCurrentHealthWhenGrowing && entity.getMaxHealth() > startingHealth)
        {
            float healthPercent = entity.getHealth() / startingHealth;
            entity.setHealth(Math.max(entity.getMaxHealth() * healthPercent, 0.5f));
        }
    }
    
    public static void validateEntitySize(EntityLivingBase entity)
    {
        setEntitySize(entity, getEntitySize(entity), true);
    }
    
    public static boolean canEntityBeScaled(EntityLivingBase entity)
    {
        if (entity instanceof FakePlayer)
            return false;
        if (entity instanceof EntityPlayer)
            return true;
        if (!GenericAdditionsConfig.sizeModule.canScaleMobs)
            return false;
        return Arrays.stream(GenericAdditionsConfig.sizeModule.bannedEntitiesSize).noneMatch(x ->
            {
                ResourceLocation e = EntityList.getKey(entity);
                return e == null || x.equals(e.toString());
            });
    
    }
}
