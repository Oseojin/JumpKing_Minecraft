package org.osj.jumpking.user.management.controller.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.osj.jumpking.user.management.entity.User;
import org.osj.jumpking.user.management.service.UserManager;

public class UserInfoCommand implements CommandExecutor
{
    private final UserManager userManager;

    public UserInfoCommand(UserManager userManager)
    {
        this.userManager = userManager;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args)
    {
        if(!(sender instanceof Player))
        {
            sender.sendMessage("플레이어만 입력이 가능합니다.");
            return false;
        }

        Player player = (Player) sender;
        User playerData = userManager.getUserData(player);

        player.sendMessage("---------------------------------");
        player.sendMessage(String.format("이름 : %s", playerData.getDisplayName()));
        player.sendMessage(String.format("칭호 : %s", playerData.getPrefix()));
        player.sendMessage(String.format("소지 금액 : %s", playerData.getGold()));
        player.sendMessage(String.format("소지 코인 : %s", playerData.getJumpingCoin()));
        player.sendMessage(String.format("최고 도달 높이 : %s", playerData.getMaxHeight()));
        player.sendMessage("---------------------------------");

        return false;
    }

}
