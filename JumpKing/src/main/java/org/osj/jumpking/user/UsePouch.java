package org.osj.jumpking.user;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.osj.jumpking.CustomItemManager;
import org.osj.jumpking.user.management.controller.UserManagementController;
import org.osj.jumpking.user.management.entity.User;

import java.util.Random;

public class UsePouch implements Listener
{
    @EventHandler
    public void UsePouchEvent(PlayerInteractEvent event)
    {
        ItemStack playerHand = event.getPlayer().getInventory().getItemInMainHand();
        if (event.getAction().isRightClick() && playerHand != null)
        {
            if (playerHand.isSimilar(CustomItemManager.goldPouch.getItemStack()))
            {
                User user = UserManagementController.getUserManager().getUserData(event.getPlayer());
                Random random = new Random();
                Long randomGold = random.nextLong(9000) + 1001; // 1000~10000
                user.setGold(user.getGold() + randomGold);
                playerHand.setAmount(playerHand.getAmount() - 1);
                event.getPlayer().sendMessage(ChatColor.GOLD + "" + randomGold + "G를 획득했습니다.");
            }
            else if (playerHand.isSimilar(CustomItemManager.jumpingCoinPouch.getItemStack()))
            {
                User user = UserManagementController.getUserManager().getUserData(event.getPlayer());
                Random random = new Random();
                Long randomJumpingCoin = random.nextLong(50) + 1; // 1~50
                user.setJumpingCoin(user.getJumpingCoin() + randomJumpingCoin);
                playerHand.setAmount(playerHand.getAmount() - 1);
                event.getPlayer().sendMessage(ChatColor.GREEN + "" + randomJumpingCoin + "코인을 획득했습니다.");
            }
        }
    }
}
