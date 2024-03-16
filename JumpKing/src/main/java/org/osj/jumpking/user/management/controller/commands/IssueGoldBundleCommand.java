package org.osj.jumpking.user.management.controller.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import org.osj.jumpking.CustomItemManager;
import org.osj.jumpking.user.management.controller.UserManagementController;
import org.osj.jumpking.user.management.entity.User;

import java.util.ArrayList;
import java.util.List;

public class IssueGoldBundleCommand implements CommandExecutor
{

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args)
    {
        Player player = (Player) sender;
        User user = UserManagementController.getUserManager().getUserData(player);

        if(args.length != 1)
        {
            player.sendMessage(ChatColor.RED + "/출금 [금액]");
            return false;
        }
        if(user.getGold() < Long.parseLong(args[0]))
        {
            player.sendMessage(ChatColor.RED + "골드가 부족합니다.");
            return false;
        }
        if(player.getInventory().firstEmpty() == -1)
        {
            player.sendMessage(ChatColor.RED + "인벤토리에 공간이 부족합니다.");
            return false;
        }

        user.setGold(user.getGold() - Long.parseLong(args[0]));
        ItemStack goldBundle = CustomItemManager.goldBundle.getItemStack();
        ItemMeta goldBundleMeta = goldBundle.getItemMeta();

        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GOLD + "[" + args[0] + "골드]");
        lore.add("우클릭하면 해당되는 골드를 획득할 수 있다.");
        goldBundleMeta.setLore(lore);

        goldBundle.setItemMeta(goldBundleMeta);

        player.getInventory().addItem(goldBundle);

        return false;
    }
}
