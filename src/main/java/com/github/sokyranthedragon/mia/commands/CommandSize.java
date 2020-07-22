package com.github.sokyranthedragon.mia.commands;

import com.github.sokyranthedragon.mia.utilities.size.SizeUtils;
import com.google.common.primitives.Floats;
import com.mojang.authlib.GameProfile;
import net.minecraft.command.*;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.UserListOpsEntry;
import net.minecraft.util.math.BlockPos;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Collections;
import java.util.List;

public class CommandSize extends CommandBase
{
    @Override
    @Nonnull
    public String getName()
    {
        return "size";
    }
    
    @Override
    @Nonnull
    @ParametersAreNonnullByDefault
    public String getUsage(ICommandSender sender)
    {
        if (sender.getServer() != null && canUseCommandOp(sender.getServer(), sender))
            return "mia.commands.size.usage_op";
        else
            return "mia.commands.size.usage";
    }
    
    @Override
    public int getRequiredPermissionLevel()
    {
        return 0;
    }
    
    @Override
    public boolean checkPermission(MinecraftServer server, ICommandSender sender)
    {
        return true;
    }
    
    @Override
    @ParametersAreNonnullByDefault
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException
    {
        boolean isOp = canUseCommandOp(server, sender);
        
        if (sender.getCommandSenderEntity() instanceof EntityPlayer)
        {
            EntityLivingBase player = (EntityLivingBase) sender.getCommandSenderEntity();
            
            if (!isOp)
            {
                notifyCommandListener(sender, this, "mia.commands.size.success_1", sender.getName(),
                        SizeUtils.getEntitySize((EntityLivingBase) sender.getCommandSenderEntity()), player.height);
                return;
            }
            else if (args.length == 0)
            {
                notifyCommandListener(sender, this, "mia.commands.size.success_1", sender.getName(),
                        SizeUtils.getEntitySize((EntityLivingBase) sender.getCommandSenderEntity()), player.height);
                return;
            }
        }
        else if (args.length == 0)
        {
            if (isOp)
                throw new WrongUsageException("mia.commands.size.usage_op");
            else
                throw new WrongUsageException("mia.commands.size.usage");
        }
        
        if (!isOp)
            throw new CommandException("commands.generic.permission");
        
        EntityPlayer entityPlayer = null;
        
        try
        {
            entityPlayer = getPlayer(server, sender, args[0]);
        } catch (PlayerNotFoundException e)
        {
            if (!(sender.getCommandSenderEntity() instanceof EntityPlayer))
            {
                throw e;
            }
        }
        
        if (entityPlayer == null)
        {
            float targetSize;
            
            try
            {
                targetSize = parseFloat(args[0]);
            } catch (NumberInvalidException e)
            {
                throw new CommandException("mia.commands.size.failure_1", args[0]);
            }
            
            entityPlayer = (EntityPlayer) sender.getCommandSenderEntity();
            
            SizeUtils.setEntitySize(entityPlayer, targetSize);
            notifyCommandListener(sender, this, "mia.commands.size.success_2",
                    entityPlayer.getName(), SizeUtils.getEntitySize(entityPlayer));
        }
        else if (args.length == 1)
        {
            notifyCommandListener(sender, this, "mia.commands.size.success_1",
                    entityPlayer.getName(), SizeUtils.getEntitySize(entityPlayer), entityPlayer.height);
        }
        else
        {
            float targetSize = parseFloat(args[1]);
            SizeUtils.setEntitySize(entityPlayer, targetSize);
            notifyCommandListener(sender, this, "mia.commands.size.success_2",
                    entityPlayer.getName(), SizeUtils.getEntitySize(entityPlayer));
        }
    }
    
    @Override
    @Nonnull
    public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos)
    {
        if (args.length == 1 && canUseCommandOp(server, sender))
            return getListOfStringsMatchingLastWord(args, server.getOnlinePlayerNames());
        else
            return Collections.emptyList();
    }
    
    private static float parseFloat(String input) throws NumberInvalidException
    {
        try
        {
            float number = Float.parseFloat(input);
            
            if (!Floats.isFinite(number))
            {
                throw new NumberInvalidException("commands.generic.num.invalid", input);
            }
            else
            {
                return number;
            }
        } catch (NumberFormatException e)
        {
            throw new NumberInvalidException("commands.generic.num.invalid", input);
        }
    }
    
    private boolean canUseCommandOp(MinecraftServer server, ICommandSender sender)
    {
        boolean isPlayer = (sender.getCommandSenderEntity() instanceof EntityPlayerMP);
        
        if (!(sender.getCommandSenderEntity() instanceof EntityLivingBase) && !isPlayer)
            return true;
        if (!isPlayer)
            return false;
        if (server.isSinglePlayer())
            return true;
        
        EntityPlayerMP player = (EntityPlayerMP) sender.getCommandSenderEntity();
        GameProfile gameProfile = player.getGameProfile();
        
        if (!server.getPlayerList().canSendCommands(gameProfile))
            return false;
        
        UserListOpsEntry userListOpSentry = server.getPlayerList().getOppedPlayers().getEntry(gameProfile);
        
        // userListOpSentry can actually be null
        //noinspection ConstantConditions
        return userListOpSentry != null && userListOpSentry.getPermissionLevel() >= getRequiredPermissionLevel();
    }
}
