package org.osj.jumpking.user;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.osj.jumpking.user.management.service.UserManager;

public class PlayerChatEvent implements Listener
{
    private final UserManager userManager;
    public PlayerChatEvent(UserManager userManager)
    {
        this.userManager = userManager;
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event)
    {
        Player player = event.getPlayer();
        String prefix = userManager.getUserData(player).getPrefix();
        String name = userManager.getUserData(player).getDisplayName();
        event.setFormat(prefix + ChatColor.WHITE + name + ": " + event.getMessage());
    }

}
