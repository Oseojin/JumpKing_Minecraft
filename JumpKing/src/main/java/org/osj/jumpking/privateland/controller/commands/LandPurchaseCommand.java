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

public class LandPurchaseCommand implements CommandExecutor
{
    private final UserLandData userLandData;
    public LandPurchaseCommand(UserLandData userLandData)
    {
        this.userLandData = userLandData;
    }
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args)
    {
        Player player = (Player) sender;
        UUID uuid = player.getUniqueId();
        Chunk currChunk = player.getChunk();
        /*if(player.getInventory().getItemInOffHand().isSimilar(땅구매증서))
        {
            player.sendMessage(ChatColor.RED + "땅구매증서가 없습니다.");
            return false;
        }*/
        if(userLandData.containLandAllOfPlayer(currChunk))
        {
            player.sendMessage(ChatColor.RED + "이미 주인이 있는 땅입니다.");
            return false;
        }

        userLandData.addLand(uuid, currChunk);
        player.sendMessage(ChatColor.GREEN + "땅을 성공적으로 구매했습니다.");

        return false;
    }
}
