package com.github.sokyranthedragon.mia.items;

import baubles.api.BaubleType;
import baubles.api.BaublesApi;
import baubles.api.IBauble;
import baubles.api.cap.IBaublesItemHandler;
import com.gildedgames.the_aether.api.AetherAPI;
import com.gildedgames.the_aether.api.player.util.IAccessoryInventory;
import com.github.sokyranthedragon.mia.Mia;
import com.github.sokyranthedragon.mia.integrations.ModIds;
import com.github.sokyranthedragon.mia.utilities.size.SizeUtils;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Optional;

import javax.annotation.Nonnull;

@Optional.Interface(iface = "baubles.api.IBauble", modid = ModIds.ConstantIds.BAUBLES)
public class ItemRingKobold extends Item implements IBauble
{
    public ItemRingKobold()
    {
        super();
        this.setMaxStackSize(1);
        this.setCreativeTab(CreativeTabs.TOOLS);
        this.setRegistryName(Mia.MODID, "kobold_ring");
        this.setTranslationKey("ring_of_the_kobold");
    }
    
    @Override
    @Optional.Method(modid = ModIds.ConstantIds.BAUBLES)
    public BaubleType getBaubleType(ItemStack itemstack)
    {
        return BaubleType.RING;
    }
    
    @Override
    @Nonnull
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, @Nonnull EnumHand hand)
    {
        if (!ModIds.BAUBLES.isLoaded && !ModIds.AETHER.isLoaded)
            return new ActionResult<>(EnumActionResult.PASS, player.getHeldItem(hand));
        
        if (!world.isRemote)
        {
            boolean equipped = false;
            
            if (ModIds.BAUBLES.isLoaded)
            {
                IBaublesItemHandler baubles = BaublesApi.getBaublesHandler(player);
                for (int i = 0; i < baubles.getSlots(); i++)
                {
                    if (baubles.getStackInSlot(i).isEmpty() && baubles.isItemValidForSlot(i, player.getHeldItem(hand), player))
                    {
                        baubles.setStackInSlot(i, player.getHeldItem(hand).copy());
                        if (!player.capabilities.isCreativeMode)
                            player.inventory.setInventorySlotContents(player.inventory.currentItem, ItemStack.EMPTY);
                        onEquipped(player.getHeldItem(hand), player);
                        equipped = true;
                        break;
                    }
                }
            }
            if (!equipped && ModIds.AETHER.isLoaded)
            {
                IAccessoryInventory accessoryInventory = AetherAPI.getInstance().get(player).getAccessoryInventory();
                if (accessoryInventory.setAccessorySlot(player.getHeldItem(hand)))
                {
                    if (!player.capabilities.isCreativeMode)
                        player.inventory.setInventorySlotContents(player.inventory.currentItem, ItemStack.EMPTY);
                    onEquipped(player.getHeldItem(hand), player);
                }
            }
        }
        
        return new ActionResult<>(EnumActionResult.SUCCESS, player.getHeldItem(hand));
    }
    
    @Override
    public void onWornTick(ItemStack itemstack, EntityLivingBase player)
    {
        float size = SizeUtils.getEntitySize(player);
        if (size > 0.5f)
            SizeUtils.setEntitySize(player, Math.max(0.5f, size - 0.0025f));
        else if (size < 0.5f)
            SizeUtils.setEntitySize(player, Math.min(0.5f, size + 0.0025f));
    }
    
    @Override
    public boolean hasEffect(ItemStack par1ItemStack)
    {
        return ModIds.BAUBLES.isLoaded || ModIds.AETHER.isLoaded;
    }
    
    @SuppressWarnings("deprecation")
    @Override
    @Nonnull
    public EnumRarity getRarity(ItemStack par1ItemStack)
    {
        return EnumRarity.RARE;
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