package org.osj.jumpking.privateland.controller.commands;

import dev.lone.itemsadder.api.CustomStack;
import org.bukkit.ChatColor;
import org.bukkit.Chunk;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.osj.jumpking.CustomItemManager;
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

        if(!player.getWorld().getName().equals("village"))
        {
            player.sendMessage(ChatColor.RED + "마을 월드에서만 땅을 구매할 수 있습니다.");
            return false;
        }

        UUID uuid = player.getUniqueId();
        Chunk currChunk = player.getChunk();
        if(userLandData.containLandAllOfPlayer(currChunk))
        {
            player.sendMessage(ChatColor.RED + "이미 주인이 있는 땅입니다.");
            return false;
        }

        // 땅 구매 증서 있는지 확인
        if(player.getInventory().containsAtLeast(CustomItemManager.landPurchaseTicket.getItemStack(), 1))
        {
            for(int i = 0; i < player.getInventory().getSize(); i++)
            {
                ItemStack currItem = player.getInventory().getItem(i);
                if(currItem == null)
                {
                    continue;
                }
                if(currItem.isSimilar(CustomItemManager.landPurchaseTicket.getItemStack()))
                {
                    currItem.setAmount(currItem.getAmount()-1);
                    break;
                }
            }
            userLandData.addLand(uuid, currChunk);
            player.sendMessage(ChatColor.GREEN + "땅을 성공적으로 구매했습니다.");
        }
        else
        {
            player.sendMessage(ChatColor.RED + "땅 구매 증서가 없습니다.");
        }

        return false;
    }
}
