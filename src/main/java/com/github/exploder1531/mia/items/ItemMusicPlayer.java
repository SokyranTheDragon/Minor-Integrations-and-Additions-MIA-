package com.github.exploder1531.mia.items;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import com.github.exploder1531.mia.Mia;
import com.github.exploder1531.mia.capabilities.MusicPlayerCapabilityProvider;
import com.github.exploder1531.mia.gui.GuiHandler;
import com.github.exploder1531.mia.handlers.MusicPlayerStackHandler;
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
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.items.IItemHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

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
    
    @Nonnull
    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, @Nonnull EnumHand hand)
    {
        ItemStack item = player.getHeldItem(hand);
        IItemHandler capability = item.getCapability(MusicPlayerCapabilityProvider.ITEM_HANDLER_CAPABILITY, null);
        
        if ((hand == EnumHand.MAIN_HAND || player.getHeldItemMainhand().isEmpty()) && capability instanceof MusicPlayerStackHandler)
        {
            BlockPos pos = player.getPosition();
            if (!world.isRemote)
                player.openGui(Mia.instance, GuiHandler.MUSIC_PLAYER, world, pos.getX(), pos.getY(), pos.getZ());
            
            return new ActionResult<>(EnumActionResult.SUCCESS, item);
        }
        
        return new ActionResult<>(EnumActionResult.FAIL, item);
    }
    
    @Override
    public BaubleType getBaubleType(ItemStack itemStack)
    {
        return BaubleType.TRINKET;
    }
    
    @Override
    public void onEquipped(ItemStack itemstack, EntityLivingBase player)
    {
        player.playSound(SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, .75F, 1.9f);
    }
    
    @Override
    public void onUnequipped(ItemStack itemstack, EntityLivingBase player)
    {
        player.playSound(SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, .75F, 2f);
    }
}
