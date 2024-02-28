package org.osj.jumpking.shop.controller;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.osj.jumpking.user.management.controller.UserManagementController;
import org.osj.jumpking.user.management.entity.User;
import org.osj.jumpking.user.management.service.UserManager;

public class ItemTradeController
{
    private static UserManager userManager = UserManagementController.getUserManager();
    public static void SellItemGold(ItemStack targetItem, int price, int num, Player player)
    {
        if(!player.getInventory().containsAtLeast(targetItem, num))
        {
            player.sendMessage(ChatColor.RED + "아이템 개수가 부족합니다.");
            return;
        }

        int count = num;
        for(int i = 0; i < player.getInventory().getSize(); i++)
        {
            ItemStack currItem = player.getInventory().getItem(i);
            if(currItem == null)
            {
                continue;
            }
            if(currItem.isSimilar(targetItem))
            {
                if(currItem.getAmount() >= count)
                {
                    currItem.setAmount(currItem.getAmount() - count);
                    break;
                }
                else
                {
                    count -= currItem.getAmount();
                    currItem.setAmount(0);
                }
            }
        }

        User user = userManager.getUserData(player);
        user.setGold(user.getGold() + price * num);
        player.sendMessage(ChatColor.AQUA + "성공적으로 판매하였습니다. " + ChatColor.GOLD + "(+" + (price * num) + "G)");
    }

    public static void SellItemCoin(ItemStack targetItem, int price, int num, Player player)
    {
        if(!player.getInventory().containsAtLeast(targetItem, num))
        {
            player.sendMessage(ChatColor.RED + "아이템 개수가 부족합니다.");
            return;
        }

        int count = num;
        for(int i = 0; i < player.getInventory().getSize(); i++)
        {
            ItemStack currItem = player.getInventory().getItem(i);
            if(currItem == null)
            {
                continue;
            }
            if(currItem.isSimilar(targetItem))
            {
                if(currItem.getAmount() >= count)
                {
                    currItem.setAmount(currItem.getAmount() - count);
                    break;
                }
                else
                {
                    count -= currItem.getAmount();
                    currItem.setAmount(0);
                }
            }
        }

        User user = userManager.getUserData(player);
        user.setJumpingCoin(user.getJumpingCoin() + price * num);
        player.sendMessage(ChatColor.AQUA + "성공적으로 판매하였습니다. " + ChatColor.GREEN + "(+" + (price * num) + "JC)");
    }

    public static void PurchaseItemGold(ItemStack targetItem, int price, Player player)
    {
        if(player.getInventory().firstEmpty() == -1)
        {
            player.sendMessage(ChatColor.RED + "인벤토리에 빈 공간이 없습니다.");
            return;
        }

        User user = userManager.getUserData(player);
        if(user.getGold() < price)
        {
            player.sendMessage(ChatColor.RED + "골드가 부족합니다.");
            return;
        }

        player.getInventory().addItem(targetItem);
        user.setGold(user.getGold() - price);
        player.sendMessage(ChatColor.AQUA + "성공적으로 구매하였습니다. " + ChatColor.GOLD + "(-" + price + "G)");
    }

    public static void PurchaseItemCoin(ItemStack targetItem, int price, Player player)
    {
        if(player.getInventory().firstEmpty() == -1)
        {
            player.sendMessage(ChatColor.RED + "인벤토리에 빈 공간이 없습니다.");
            return;
        }

        User user = userManager.getUserData(player);
        if(user.getJumpingCoin() < price)
        {
            player.sendMessage(ChatColor.RED + "점핑코인이 부족합니다.");
            return;
        }

        player.getInventory().addItem(targetItem);
        user.setJumpingCoin(user.getJumpingCoin() - price);
        player.sendMessage(ChatColor.AQUA + "성공적으로 구매하였습니다. " + ChatColor.GREEN + "(-" + price + "JC)");
    }
}