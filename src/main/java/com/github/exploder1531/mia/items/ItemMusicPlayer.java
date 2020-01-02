package com.github.exploder1531.mia.items;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import com.github.exploder1531.mia.Mia;
import com.github.exploder1531.mia.capabilities.MusicPlayerCapabilityProvider;
import com.github.exploder1531.mia.gui.GuiHandler;
import com.github.exploder1531.mia.handlers.MusicPlayerStackHandler;
import com.github.exploder1531.mia.integrations.ModIds;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.fml.common.Optional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

@Optional.Interface(iface = "baubles.api.IBauble", modid = ModIds.ConstantIds.BAUBLES)
public class ItemMusicPlayer extends Item implements IBauble
{
    public ItemMusicPlayer()
    {
        setMaxStackSize(1);
        setCreativeTab(CreativeTabs.TOOLS);
        setRegistryName(Mia.MODID, "music_player");
        setTranslationKey("music_player");
    }
    
    @Nullable
    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable NBTTagCompound nbt)
    {
        return new MusicPlayerCapabilityProvider();
    }
    
    @Override
    public boolean getShareTag()
    {
        return true;
    }
    
    @Nullable
    @Override
    public NBTTagCompound getNBTShareTag(ItemStack stack)
    {
        NBTTagCompound nbt = super.getNBTShareTag(stack);
        
        if (nbt == null)
            nbt = new NBTTagCompound();
        
        MusicPlayerStackHandler capability = stack.getCapability(MusicPlayerCapabilityProvider.ITEM_HANDLER_CAPABILITY, null);
        if (capability != null)
            nbt.setTag("Capability", capability.serializeNBT());
        
        return nbt;
    }
    
    @Override
    public void readNBTShareTag(ItemStack stack, @Nullable NBTTagCompound nbt)
    {
        if (nbt != null && nbt.hasKey("Capability", Constants.NBT.TAG_COMPOUND))
        {
            NBTTagCompound capabilityNbt = nbt.getCompoundTag("Capability");
            nbt.removeTag("Capability");
    
            MusicPlayerStackHandler capability = stack.getCapability(MusicPlayerCapabilityProvider.ITEM_HANDLER_CAPABILITY, null);
            if (capability != null)
                capability.deserializeNBT(capabilityNbt);
        }
        
        super.readNBTShareTag(stack, nbt);
    }
    
    @Nonnull
    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, @Nonnull EnumHand hand)
    {
        ItemStack item = player.getHeldItem(hand);
        MusicPlayerStackHandler capability = item.getCapability(MusicPlayerCapabilityProvider.ITEM_HANDLER_CAPABILITY, null);
        
        if ((hand == EnumHand.MAIN_HAND || player.getHeldItemMainhand().isEmpty()) && capability != null)
        {
            if (!world.isRemote)
                player.openGui(Mia.instance, GuiHandler.MUSIC_PLAYER, world, hand.ordinal(), 0, 0);
            
            return new ActionResult<>(EnumActionResult.SUCCESS, item);
        }
        
        return new ActionResult<>(EnumActionResult.FAIL, item);
    }
    
    @Override
    @Optional.Method(modid = ModIds.ConstantIds.BAUBLES)
    public BaubleType getBaubleType(ItemStack itemStack)
    {
        return BaubleType.TRINKET;
    }
    
    @Override
    @Optional.Method(modid = ModIds.ConstantIds.BAUBLES)
    public void onEquipped(ItemStack itemstack, EntityLivingBase player)
    {
        player.playSound(SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, .75F, 1.9f);
    }
    
    @Override
    @Optional.Method(modid = ModIds.ConstantIds.BAUBLES)
    public void onUnequipped(ItemStack itemstack, EntityLivingBase player)
    {
        player.playSound(SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, .75F, 2f);
    }
}
