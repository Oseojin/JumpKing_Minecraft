package org.osj.jumpking.user;

import dev.lone.itemsadder.api.CustomStack;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.osj.jumpking.CustomItemManager;
import org.osj.jumpking.user.management.controller.UserManagementController;
import org.osj.jumpking.user.management.entity.User;

import java.util.Random;

public class UseGoldBundle implements Listener
{
    @EventHandler
    public void UseGoldBundleEvent(PlayerInteractEvent event)
    {
        ItemStack playerHand = event.getPlayer().getInventory().getItemInMainHand();
        if (event.getAction().isRightClick() && playerHand != null)
        {
            CustomStack goldBundle = CustomStack.byItemStack(playerHand);
            if (goldBundle !=null && goldBundle.getPermission().equals("ia.jumpking:gold_bundle"))
            {
                User user = UserManagementController.getUserManager().getUserData(event.getPlayer());
                long gold = Long.parseLong(ChatColor.stripColor(playerHand.getLore().get(0)).replace("[", "").replace("골드]", ""));
                user.setGold(user.getGold() + gold);
                playerHand.setAmount(playerHand.getAmount() - 1);
                event.getPlayer().sendMessage(ChatColor.GOLD + "" + gold + "G를 획득했습니다.");
            }
        }
    }
}
