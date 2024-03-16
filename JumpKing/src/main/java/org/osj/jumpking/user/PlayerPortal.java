package org.osj.jumpking.user;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPortalEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.osj.jumpking.JumpKing;
import org.osj.jumpking.SpawnLocManager;
import org.osj.jumpking.user.management.entity.User;
import org.osj.jumpking.user.management.service.UserManager;

public class PlayerPortal implements Listener
{
    private final UserManager userManager;

    public PlayerPortal(UserManager userManager)
    {
        this.userManager = userManager;
    }
    @EventHandler
    public void onPlayerPortal(PlayerPortalEvent event)
    {
        if(event.getFrom().getWorld().getName().equals("JUMPMAP") || event.getFrom().getWorld().getName().equals("village"))
        {
            event.setCancelled(true);
            event.getPlayer().sendMessage(ChatColor.RED + "해당 월드에서는 포탈을 이용할 수 없습니다!");
            return;
        }

        Player player = event.getPlayer();

        User user = userManager.getUserData(player);
        int restrictionHeight = 0;

        switch (event.getTo().getWorld().getName())
        {
            case "wild_nether":
                restrictionHeight = 25;
                break;
            case "wild_the_end":
                restrictionHeight = 50;
                break;
            default:
                break;
        }
        if(user.getMaxHeight() < restrictionHeight)
        {
            player.sendMessage(ChatColor.RED + "조건을 만족하지 못했습니다.");
            player.sendMessage(ChatColor.RED + "해당 포탈은 " + restrictionHeight + "m 이상 등반해야 이용할 수 있습니다.");
            event.setCancelled(true);
        }
    }
}
