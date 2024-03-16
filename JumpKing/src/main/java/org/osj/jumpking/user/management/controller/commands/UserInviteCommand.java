package org.osj.jumpking.user.management.controller.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.osj.jumpking.CustomItemManager;
import org.osj.jumpking.JumpKing;

public class UserInviteCommand implements CommandExecutor
{
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args)
    {
        Player player = (Player) sender;

        if(args.length != 1)
        {
            player.sendMessage(ChatColor.RED + "/초대 [플레이어 닉네임]");
            return false;
        }

        // 유저 초대 티켓 있는지 확인
        if(player.getInventory().containsAtLeast(CustomItemManager.userInviteTicket.getItemStack(), 1))
        {
            for(int i = 0; i < player.getInventory().getSize(); i++)
            {
                ItemStack currItem = player.getInventory().getItem(i);
                if(currItem == null)
                {
                    continue;
                }
                if(currItem.isSimilar(CustomItemManager.userInviteTicket.getItemStack()))
                {
                    currItem.setAmount(currItem.getAmount()-1);
                    break;
                }
            }
            JumpKing.getConfigManager().getConfig("whitelist").set("players." + args[0], true);
            JumpKing.getConfigManager().saveConfig("whitelist");
            player.sendMessage(ChatColor.GREEN + "유저를 성공적으로 초대했습니다.");
            JumpKing.getConfigManager().reloadConfig("whitelist");
        }
        else
        {
            player.sendMessage(ChatColor.RED + "유저 초대 티켓이 없습니다.");
        }

        return false;
    }
}
