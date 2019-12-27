package com.github.exploder1531.mia.integrations.jer;

import net.minecraft.util.ResourceLocation;

import java.util.Objects;

public class ResourceLocationWrapper extends ResourceLocation
{
    protected int resourceId;
    
    public ResourceLocationWrapper(ResourceLocation base)
    {
        this(base, 0);
    }
    
    public ResourceLocationWrapper(ResourceLocation base, int resourceId)
    {
        this(base.getNamespace(), base.getPath(), resourceId);
    }
    
    public ResourceLocationWrapper(String namespace, String path)
    {
        this(namespace, path, 0);
    }
    
    public ResourceLocationWrapper(String namespace, String path, int resourceId)
    {
        super(namespace, path);
        this.resourceId = resourceId;
    }
    
    @SuppressWarnings("unused")
    public int getResourceId()
    {
        return resourceId;
    }
    
    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof ResourceLocationWrapper)) return false;
        ResourceLocationWrapper that = (ResourceLocationWrapper) o;
        return resourceId == that.resourceId && path.equals(that.path) && namespace.equals(that.namespace);
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(super.hashCode(), resourceId);
    }
    
    @Override
    public int compareTo(ResourceLocation other)
    {
        int i = 0;
        if (other instanceof ResourceLocationWrapper)
            i = Integer.compare(resourceId, ((ResourceLocationWrapper)other).resourceId);
        if (i == 0)
            i = super.compareTo(other);
        
        return i;
    }
}
