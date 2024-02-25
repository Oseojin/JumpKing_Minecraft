package org.osj.jumpking.privateland.controller.commands;

import org.bukkit.ChatColor;
import org.bukkit.Chunk;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.osj.jumpking.privateland.service.UserLandData;

import java.util.UUID;

public class LandRemoveCommand implements CommandExecutor
{
    private final UserLandData userLandData;
    public LandRemoveCommand(UserLandData userLandData)
    {
        this.userLandData = userLandData;
    }
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args)
    {
        Player player = (Player) sender;
        UUID uuid = player.getUniqueId();
        Chunk currChunk = player.getChunk();

        if(!userLandData.containLand(uuid, currChunk))
        {
            player.sendMessage(ChatColor.RED + "소유중인 땅이 아닙니다.");
            return false;
        }

        userLandData.removeLand(uuid, currChunk);
        player.sendMessage(ChatColor.GREEN + "땅을 성공적으로 제거하였습니다.");
        return false;
    }
}
