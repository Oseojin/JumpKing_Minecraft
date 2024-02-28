package org.osj.jumpking.jumpmap;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ShoesStatManager
{
    public static Integer getShoesLv(ItemStack playerShoes)
    {
        return Integer.parseInt(ChatColor.stripColor(playerShoes.getItemMeta().getDisplayName()).replace("점핑슈즈[+", "").replace("]", ""));
    }

    public static void applyShoesOption(ItemStack playerShoes, Player player)
    {

    }
}
